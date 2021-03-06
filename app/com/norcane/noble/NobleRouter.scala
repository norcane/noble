/*
 *              _     _
 *  _ __   ___ | |__ | | ___
 * | '_ \ / _ \| '_ \| |/ _ \       noble :: norcane blog engine
 * | | | | (_) | |_) | |  __/       Copyright (c) 2016-2018 norcane
 * |_| |_|\___/|_.__/|_|\___|
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.norcane.noble

import javax.inject.{Inject, Singleton}

import com.norcane.noble.api.BlogReverseRouter
import com.norcane.noble.api.models.Page
import controllers.{Assets, AssetsFinder}
import play.api.mvc.{ControllerComponents, Handler, RequestHeader}
import play.api.routing.Router.Routes
import play.api.routing.{Router, SimpleRouter}

/**
  * This router represents the entry point of the ''noble'' application, that handles all blog
  * requests. In order to embed the ''noble'' into the ''Play'' application, all requests not
  * handled by the application routes must be directed to this router using the following syntax:
  *
  * {{{
  *   ->  /<nobleRoot>   com.norcane.noble.NobleRouter
  * }}}
  *
  * Where `nobleRoot` is the *noble* root path.
  *
  * @param assets Play's assets
  * @param noble  Noble application singleton
  * @param cc     Play's Controller components
  */
@Singleton
class NobleRouter @Inject()(assets: Assets,
                            assetsFinder: AssetsFinder,
                            noble: Noble,
                            cc: ControllerComponents)
  extends SimpleRouter {

  private var prefix: String = ""

  override def routes: Routes = {
    import play.api.routing.sird._

    val blogRouters = noble.blogs map { blog =>
      val blogPath = prefix + blog.config.path
      val globalAssetsPath = s"$prefix/${Keys.Defaults.GlobalAssetsPrefix}"
      val reverseRouter = new BlogReverseRouter(assetsFinder, blogPath, globalAssetsPath)
      val controller = new BlogController(
        blog.actor, blog.config, noble.themes, reverseRouter, blogPath, cc)
      new BlogRouter(controller).withPrefix(blog.config.path)
    }

    val blogRoutes = blogRouters
      .map(_.routes)
      .foldLeft(PartialFunction.empty[RequestHeader, Handler])(_ orElse _)

    val globalRoutes: PartialFunction[RequestHeader, Handler] = {
      case GET(p"/${Keys.Defaults.GlobalAssetsPrefix}/lib/$path*") =>
        assets.versioned("/public/lib", path)
    }

    globalRoutes orElse blogRoutes
  }

  override def withPrefix(prefix: String): Router = {
    this.prefix = if (prefix == "/") "" else prefix
    super.withPrefix(prefix)
  }

}

/**
  * Represents the router of one particular blog. *Noble* creates own instance for every configured
  * blog.
  *
  * @param controller blog controller
  * @author Vaclav Svejcar (v.svejcar@norcane.cz)
  */
class BlogRouter(controller: BlogController) extends SimpleRouter {
  self =>

  import play.api.routing.sird._

  override def routes: Routes = {

    // list all blog posts
    case GET((p"/" | p"")) ? Page(page) => controller.index(page)

    // specific blog post
    case GET(p"""/${int(year)}<\d{4}>/${int(month)}<\d{2}>/${int(day)}<\d{2}>/$permalink""") =>
      controller.post(year, month, day, permalink)

    // blog posts for specific year
    case GET(p"""/${int(year)}<\d{4}>""" ? Page(page)) =>
      controller.year(year, page)

    // blog posts for specific month
    case GET(p"""/${int(year)}<\d{4}>/${int(month)}<\d{2}>""" ? Page(page)) =>
      controller.month(year, month, page)

    // blog posts for specific day
    case GET(p"""/${int(year)}<\d{4}>/${int(month)}<\d{2}>/${int(day)}<\d{2}>""" ? Page(page)) =>
      controller.day(year, month, day, page)

    // blog posts by author
    case GET(p"/author/$authorId" ? Page(page)) =>
      controller.author(authorId, page)

    // blog posts for specific tag
    case GET(p"/tags/$name" ? Page(page)) =>
      controller.tag(name, page)

    // assets
    case GET(p"/blog-assets/$path*") =>
      controller.asset(path)

    // atom feed
    case GET(p"/atom.xml") =>
      controller.atom

    // blog reload requests
    case POST(p"/reload/$reloadToken") =>
      controller.reload(reloadToken)

    // static page
    case GET(p"/$page*") =>
      controller.page(page)

    // in case that none of routes match, return 404 not found
    case _ =>
      controller.notFound
  }

  override def withPrefix(prefix: String): Router =
    if (prefix == "/") self
    else new Router {
      override def routes: Routes = {
        val p = if (prefix.endsWith("/")) prefix else prefix + "/"
        val prefixed: PartialFunction[RequestHeader, RequestHeader] = {
          case header: RequestHeader if header.path.startsWith(p) || header.path.equals(prefix) =>
            header.withTarget(header.target.withPath(header.path.drop(p.length - 1)))
        }
        Function.unlift(prefixed.lift andThen (_ flatMap self.routes.lift))
      }

      override def documentation: Seq[(String, String, String)] = self.documentation

      override def withPrefix(prefix: String): Router = self.withPrefix(prefix)
    }
}