package com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities

enum class ExceptionClassNames {
    AppInitException,
    ArticleFetcherInterruptedException,
    ArticleUploadException,
    DataCoordinatorException,
    ParserUnavailableException,
    SettingsUploadException,
    ArticleFetcherException,
    ArticleDeleteException
}