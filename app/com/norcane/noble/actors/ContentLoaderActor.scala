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

package com.norcane.noble.actors

import akka.actor.{Actor, ActorLogging, Props}
import com.norcane.noble.actors.BlogActor.{LoadAsset, RenderPageContent}
import com.norcane.noble.api.models.{Blog, BlogPostMeta, StaticPageMeta}
import com.norcane.noble.api.{BlogStorage, ContentStream}

import scala.concurrent.blocking

class ContentLoaderActor(storage: BlogStorage) extends Actor with ActorLogging {

  import BlogActor.RenderPostContent

  override def receive: Receive = {
    case RenderPostContent(blog, post, placeholders) =>
      sender ! loadPostContent(blog, post, placeholders)
    case RenderPageContent(blog, page, placeholders) =>
      sender ! loadPageContent(blog, page, placeholders)
    case LoadAsset(blog, path) =>
      sender ! loadAsset(blog, path)
  }

  private def loadAsset(blog: Blog, path: String): Option[ContentStream] = blocking {
    storage.loadAsset(blog.versionId, path) match {
      case Right(stream) => Some(stream)
      case Left(err) =>
        log.error(err.cause.orNull, err.message)
        None
    }
  }

  private def loadPostContent(blog: Blog, post: BlogPostMeta,
                              placeholders: Map[String, Any]): Option[String] = blocking {
    storage.loadPostContent(blog.versionId, post, placeholders) match {
      case Right(content) => Some(content)
      case Left(err) =>
        log.error(err.cause.orNull, err.message)
        None
    }
  }

  private def loadPageContent(blog: Blog, page: StaticPageMeta,
                              placeholders: Map[String, Any]): Option[String] = blocking {
    storage.loadPageContent(blog.versionId, page, placeholders) match {
      case Right(content) => Some(content)
      case Left(err) =>
        log.error(err.cause.orNull, err.message)
        None
    }
  }
}

object ContentLoaderActor {
  def props(storage: BlogStorage): Props = Props(new ContentLoaderActor(storage))
}
