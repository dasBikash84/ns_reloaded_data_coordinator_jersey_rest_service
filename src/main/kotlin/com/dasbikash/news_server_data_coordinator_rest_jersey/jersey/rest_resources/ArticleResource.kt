package com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.rest_resources

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.Articles
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.RequestDetailsBean
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.ArticleDeleteRequest
import com.dasbikash.news_server_data_coordinator_rest_jersey.services.ArticleService
import com.dasbikash.news_server_data_coordinator_rest_jersey.utills.RestControllerUtills
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("articles")
@Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
@Component
open class ArticleResource constructor(@Autowired open var articleService: ArticleService?=null,
                                       @Autowired open var restControllerUtills: RestControllerUtills?=null) {

    @Value("\${article.default_page_size}")
    open var defaultPageSize: Int = 5

    @Value("\${article.max_page_size}")
    open var maxPageSize: Int = 10

    @GET
    @Path("/page-id/{pageId}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getLatestArticlesByPageIdEndPoint(@PathParam("pageId") pageId: String,
                                               @QueryParam("article_count") articleCount:Int?,
                                               @BeanParam requestDetails: RequestDetailsBean)
            : Response {

        var pageSize = defaultPageSize

        articleCount?.let {
            when{
                it>=maxPageSize -> pageSize = maxPageSize
                it>0 -> pageSize = it

            }
        }
        return restControllerUtills!!.entityToResponseEntity(Articles(articleService!!.getLatestArticlesByPageId(pageId,pageSize)))
    }

    @GET
    @Path("/page-id/{pageId}/before/last-article-id/{lastArticleId}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getArticlesForPageBeforeArticleIdEndPoint(@PathParam("pageId") pageId: String,
                                                       @PathParam("lastArticleId") lastArticleId: String,
                                                       @QueryParam("article_count") articleCount:Int?,
                                                       @BeanParam requestDetails: RequestDetailsBean): Response {

        var pageSize = defaultPageSize

        articleCount?.let {
            when{
                it>=maxPageSize -> pageSize = maxPageSize
                it>0 -> pageSize = it
            }
        }
        return restControllerUtills!!.entityToResponseEntity(Articles(articleService!!.getArticlesForPageBeforeArticleId(pageId,pageSize,lastArticleId)))
    }

    @GET
    @Path("/page-id/{pageId}/after/last-article-id/{lastArticleId}")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getArticlesForPageAfterArticleIdEndPoint(@PathParam("pageId") pageId: String,
                                                       @PathParam("lastArticleId") lastArticleId: String,
                                                       @QueryParam("article_count") articleCount:Int?,
                                                       @BeanParam requestDetails: RequestDetailsBean): Response {

        var pageSize = defaultPageSize

        articleCount?.let {
            when{
                it>=maxPageSize -> pageSize = maxPageSize
                it>0 -> pageSize = it
            }
        }
        return restControllerUtills!!.entityToResponseEntity(Articles(articleService!!.getArticlesForPageAfterArticleId(pageId,pageSize,lastArticleId)))
    }

    @GET
    @Path("/top-level-page-id/{topLevelPageId}/latest-article")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun getLatestArticleForTopLevelPageEndPoint(@PathParam("topLevelPageId") topLevelPageId:String,
                                                     @BeanParam requestDetails: RequestDetailsBean)
            :Response{
        return restControllerUtills!!.entityToResponseEntity(articleService!!.getLatestArticleForTopLevelPage(topLevelPageId))
    }

    @DELETE
    @Path("request_article_delete_request_token_generation")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun generateLogDeletionTokenEndPoint(@BeanParam requestDetails: RequestDetailsBean): Response {
        return restControllerUtills!!.generateArticleDeleteRequestToken(this::class.java)
    }

    @DELETE
    @Path("article_delete_request")
    @Produces(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    @Consumes(value = arrayOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
    open fun submitArticleDeleteRequestEndPoint(articleDeleteRequest: ArticleDeleteRequest?,
                                                @BeanParam requestDetails: RequestDetailsBean)
            : Response {
        return restControllerUtills!!.entityToResponseEntity(
                articleService!!.submitArticleDeleteRequest(articleDeleteRequest))
    }
}
