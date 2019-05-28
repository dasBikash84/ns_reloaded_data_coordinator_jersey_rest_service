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

package com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DataCoordinatorRestEntity
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DatabaseTableNames
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Page
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlTransient

@Entity
@Table(name = DatabaseTableNames.ARTICLE_DOWNLOAD_LOG_TABLE_NAME)
@XmlRootElement
class ArticleDownloadLog():DataCoordinatorRestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @Column(columnDefinition = "text")
    var logMessage: String?=null
    var parents: String?=null
    @UpdateTimestamp
    var created: Date? = null

    @ManyToOne(targetEntity = Page::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "pageId")
    private var page: Page? = null

    @JsonProperty(value = "pageId")
    @XmlElement
    @Transient
    fun getPageId(): String? {
        return page?.id
    }

    @JsonIgnore
    @XmlTransient
    fun getPage():Page?{
        return page
    }
    fun setPage(page: Page?){
        this.page=page
    }


}