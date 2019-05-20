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
import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.XmlRootElement

@Entity
@Table(name = DatabaseTableNames.SETTINGS_UPLOAD_LOG_TABLE_NAME)
@XmlRootElement
class SettingsUploadLog():DataCoordinatorRestEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var uploadTime: Date? = null

    @Column(columnDefinition = "enum('REAL_TIME_DB','FIRE_STORE_DB','MONGO_REST_SERVICE')")
    @Enumerated(EnumType.STRING)
    var uploadTarget: ArticleUploadTarget? = null
}