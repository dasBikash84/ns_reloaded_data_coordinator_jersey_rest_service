package com.dasbikash.news_server_data_coordinator_rest_jersey.model.database

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlTransient

@Entity
@Table(name = DatabaseTableNames.ARTICLE_DELETE_REQUEST_TABLE_NAME)
@XmlRootElement
data class ArticleDeleteRequest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        @Transient
//        @JsonIgnore
        var authToken:String? = null,
        @Transient
//        @JsonIgnore
        var targetPageId:String?=null,
        var deleteRequestCount:Int?=null,
        var created:Date?= Date()
):DataCoordinatorRestEntity{

    @ManyToOne(targetEntity = Page::class, fetch = FetchType.EAGER)
    @JoinColumn(name = "pageId")
    private var page: Page? = null

    @JsonIgnore
    @XmlTransient
    fun getPage():Page?{
        return page
    }
    fun setPage(page: Page){
        this.page = page
    }

    @JsonProperty
    @XmlElement
    fun getPageId():String?{
        return page?.id
    }

    companion object{
        const val MAX_ARTICLE_DELETE_REQUEST_COUNT = 400
        const val MIN_ARTICLE_DELETE_REQUEST_COUNT = 5
    }
}
@XmlRootElement
class ArticleDeleteRequestFormat:DataCoordinatorRestEntity {
    var authToken:String = "Emailed token"
    var targetPageId:String="Target Page ID"
    var deleteRequestCount:String = "Article Delete Request Count(${ArticleDeleteRequest.MIN_ARTICLE_DELETE_REQUEST_COUNT} - "+
                                    "${ArticleDeleteRequest.MAX_ARTICLE_DELETE_REQUEST_COUNT})"
}