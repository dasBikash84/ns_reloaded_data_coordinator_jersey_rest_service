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
import javax.xml.bind.annotation.XmlTransient

@Entity
@Table(name = DatabaseTableNames.PAGE_GROUP_TABLE_NAME)
data class PageGroup(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private var id: Int?=null,
        var name: String?=null
){

    @JsonIgnore
    @XmlTransient
    fun getId(): Int? {
        return this.id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    var active: Boolean = true
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Page::class)
    @JoinTable(
            name="page_group_entries",
            joinColumns = arrayOf(JoinColumn(name = "pageGroupId")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "pageId"))
    )
    private var pageList: List<Page>?=null

    @JsonIgnore
    @XmlTransient
    fun getPageList(): List<Page>? {
        return this.pageList
    }
    fun setPageList(pageList: List<Page>?) {
        this.pageList = pageList
    }

    @Transient
    private var pageIdList: List<String>?=null
    @JsonProperty(value = "pageList")
    @XmlElement(name = "pageList")
    fun getPageIdList():List<String>{
        return pageList?.map { it.id }?.toList() ?: emptyList()
    }

    override fun toString(): String {
        return "PageGroup(name=$name, active=$active, pageList=${pageList?.map { it.id }?.toCollection(mutableListOf())})"
    }
}