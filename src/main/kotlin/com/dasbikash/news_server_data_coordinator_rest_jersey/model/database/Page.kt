/*
 * Copyright 2019 das.bikash.dev@gmail.com. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dasbikash.news_server_data_coordinator_rest_jersey.model.database

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlTransient

@Entity
@Table(name = DatabaseTableNames.PAGE_TABLE_NAME)
@XmlRootElement
data class Page(
        @Id
        var id: String="",

        @ManyToOne(targetEntity = Newspaper::class,fetch = FetchType.EAGER)
        @JoinColumn(name="newsPaperId")
        private var newspaper: Newspaper?=null,

        var parentPageId: String?=null,
        var name: String?=null,

        private var active: Boolean = true,

        @OneToMany(fetch = FetchType.LAZY,mappedBy = "page",targetEntity = Article::class)
        private var articleList: List<Article>?=null
):DataCoordinatorRestEntity{
    var hasData: Boolean?=null
    var hasChild: Boolean?=null
    var topLevelPage: Boolean?=null

    @Transient
    @JsonProperty(value = "newspaperId")
    @XmlElement
    fun getNewsPaperId():String{
        return newspaper?.id ?: ""
    }

    @JsonIgnore
    @XmlTransient
    fun getActive(): Boolean {
        return active
    }

    fun setActive(active: Boolean) {
        this.active = active
    }

    @JsonIgnore
    @XmlTransient
    fun getNewspaper():Newspaper?{
        return newspaper
    }
    fun setNewspaper(newsPaper: Newspaper?){
        this.newspaper=newsPaper
    }

    @JsonIgnore
    @XmlTransient
    fun getArticleList():List<Article>?{
        return articleList
    }
    fun setArticleList(articleList: List<Article>?){
        this.articleList=articleList
    }

    override fun toString(): String {
        return "Page(id='$id', newspaper=${newspaper?.name}, parentPageId=$parentPageId, name=$name, active=$active,hasChild = $hasChild)"
    }
}