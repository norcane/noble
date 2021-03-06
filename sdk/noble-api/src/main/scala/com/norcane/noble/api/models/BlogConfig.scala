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

package com.norcane.noble.api.models

/**
  * Model class representing the configuration of one blog.
  *
  * @param name          internal name of the blog
  * @param path          the path of the blog
  * @param reloadToken   token used to authenticate blog reload requests
  * @param storageConfig configuration of blog storage
  * @author Vaclav Svejcar (v.svejcar@norcane.cz)
  */
case class BlogConfig(name: String, path: String, reloadToken: Option[String],
                      storageConfig: StorageConfig)
