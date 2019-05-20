package com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DataCoordinatorRestEntity
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DatabaseTableNames
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.XmlRootElement

@Entity
@Table(name = DatabaseTableNames.ARTICLE_UPLOADER_STATUS_CHANGE_LOG_TABLE_NAME)
@XmlRootElement
data class ArticleUploaderStatusChangeLog(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column(columnDefinition = "enum('REAL_TIME_DB','FIRE_STORE_DB','MONGO_REST_SERVICE')")
        @Enumerated(EnumType.STRING)
        var articleDataUploaderTarget: ArticleUploadTarget? = null,

        @UpdateTimestamp
        var created: Date? = null,

        @Column(columnDefinition = "enum('ON','OFF')")
        @Enumerated(EnumType.STRING)
        var status: TwoStateStatus? = null
):DataCoordinatorRestEntity