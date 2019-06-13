package com.dasbikash.news_server_data_coordinator_rest_jersey.repositories

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Article
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.DatabaseTableNames
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ArticleRepository : JpaRepository<Article, String>{
    fun findAllByPageAndArticleTextIsNotNullOrderByPublicationTimeDesc(page: Page):List<Article>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_TABLE_NAME} WHERE pageId=:pageId order by publicationTime DESC limit :pageSize",
            nativeQuery = true)
    fun getLatestArticlesByPageId(pageId: String, pageSize: Int): List<Article>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_TABLE_NAME} WHERE pageId=:pageId and publicationTime < :lastArticlePublicationTime " +
                    "order by publicationTime DESC limit :pageSize",
            nativeQuery = true)
    fun getArticlesForPageBeforeArticleId(pageId: String, pageSize: Int, lastArticlePublicationTime: Date): List<Article>

    @Query(value = "SELECT * FROM ${DatabaseTableNames.ARTICLE_TABLE_NAME} WHERE pageId=:pageId and publicationTime > :lastArticlePublicationTime " +
            "order by publicationTime DESC limit :pageSize",
            nativeQuery = true)
    fun getArticlesForPageAfterArticleId(pageId: String, pageSize: Int, lastArticlePublicationTime: Date): List<Article>?
}