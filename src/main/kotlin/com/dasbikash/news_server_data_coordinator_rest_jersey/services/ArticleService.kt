package com.dasbikash.news_server_data_coordinator_rest_jersey.services

import com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions.DataNotFoundException
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Article
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Page
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.ArticleRepository
import com.dasbikash.news_server_data_coordinator_rest_jersey.repositories.PageRepository
import org.springframework.stereotype.Service

@Service
open class ArticleService constructor(open var pageRepository: PageRepository,
                                      open var articleRepository: ArticleRepository){

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
}