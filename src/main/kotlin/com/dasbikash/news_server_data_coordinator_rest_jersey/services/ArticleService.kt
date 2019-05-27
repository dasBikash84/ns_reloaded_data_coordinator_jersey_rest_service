package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions.DataNotFoundException
import com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions.IllegalRequestBodyException
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Article
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.ArticleDeleteRequest
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Page
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.ArticleDeleteRequestRepository
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.ArticleRepository
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.PageRepository
import org.springframework.stereotype.Service

@Service
open class ArticleService constructor(open var pageRepository: PageRepository,
                                      open var articleRepository: ArticleRepository,
                                      open var articleDeleteRequestRepository: ArticleDeleteRequestRepository,
                                      open var authTokenService: AuthTokenService){

    fun getLatestArticlesByPageId(pageId: String,pageSize:Int):List<Article>{
        val pageOptional = pageRepository.findById(pageId)
        if (!pageOptional.isPresent || !pageOptional.get().hasData!!) {
            throw DataNotFoundException()
        }
        return articleRepository.getLatestArticlesByPageId(pageId,pageSize)
    }


    fun getArticlesForPageBeforeArticleId(pageId: String, pageSize: Int, lastArticleId: String): List<Article> {

        val pageOptional = pageRepository.findById(pageId)
        if (!pageOptional.isPresent || !pageOptional.get().hasData!!) {
            throw DataNotFoundException()
        }

        val articleOptional = articleRepository.findById(lastArticleId)
        if (!articleOptional.isPresent) {
            throw DataNotFoundException()
        }
        val lastArticle = articleOptional.get()

        return articleRepository.getArticlesForPageBeforeArticleId(pageId, pageSize, lastArticle.publicationTime!!)
    }

    fun getLatestArticleForTopLevelPage(topLevelPageId: String): Article? {
        val pageOptional = pageRepository.findById(topLevelPageId)
        if (!pageOptional.isPresent || !pageOptional.get().topLevelPage!!) {
            throw DataNotFoundException()
        }
        val topLevelPage = pageOptional.get()
        val page: Page
        if (topLevelPage.hasData!!) {
            page = topLevelPage
        } else {
            page = pageRepository.findPagesByParentPageIdAndActiveOrderByIdAsc(topLevelPage.id).first()
        }
        val articleList = articleRepository.findAllByPageAndArticleTextIsNotNullOrderByPublicationTimeDesc(page)
        if (articleList.size == 0) {
            return null
        }
        val article = articleList.first()
        if (article.previewImageLink == null && article.imageLinkList.size > 0) {
            article.previewImageLink = article.imageLinkList.first().link
        }
        return article
    }


    fun validateArticleDeleteRequest(articleDeleteRequest: ArticleDeleteRequest?):Page {
        if (articleDeleteRequest == null ||
                articleDeleteRequest.authToken == null ||
                articleDeleteRequest.targetPageId == null ||
                articleDeleteRequest.deleteRequestCount == null ||
                articleDeleteRequest.articleUploadTarget == null ||
                articleDeleteRequest.deleteRequestCount!! < ArticleDeleteRequest.MIN_ARTICLE_DELETE_REQUEST_COUNT ||
                articleDeleteRequest.deleteRequestCount!! > ArticleDeleteRequest.MAX_ARTICLE_DELETE_REQUEST_COUNT
        ) {
            throw IllegalRequestBodyException()
        }
        val pageOptional = pageRepository.findById(articleDeleteRequest.targetPageId!!)
        if (!pageOptional.isPresent || !pageOptional.get().hasData!!){
            throw IllegalRequestBodyException()
        }
        return pageOptional.get()
    }

    fun submitArticleDeleteRequest(articleDeleteRequest: ArticleDeleteRequest?): ArticleDeleteRequest {
        val page = validateArticleDeleteRequest(articleDeleteRequest)
        authTokenService.invalidateAuthToken(articleDeleteRequest!!.authToken!!)
        articleDeleteRequest.setPage(page)
        articleDeleteRequestRepository.save(articleDeleteRequest)
        return articleDeleteRequest
    }
}