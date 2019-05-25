package com.dasbikash.news_server_data_coordinator_rest_jersey

import com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.exception_mappers.GenericExceptionMapper
import com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.rest_resources.*
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration

@Configuration
open class JerseyConfig : ResourceConfig() {
    init {
        registerClasses(GenericExceptionMapper::class.java)
        registerClasses( LanguageResource::class.java)
        registerClasses(CountryResource::class.java)
        registerClasses( NewsPaperResource::class.java)
        registerClasses(PageResource::class.java)
        registerClasses( ArticleResource::class.java)
        registerClasses(PageGroupResource::class.java)
        registerClasses( ArticleDownloadLogResource::class.java)
        registerClasses(ArticleUploadLogResource::class.java)
        registerClasses( ArticleUploaderStatusChangeLogResource::class.java)
        registerClasses(ErrorLogResource::class.java)
        registerClasses( GeneralLogResource::class.java)
        registerClasses(SettingsUpdateLogResource::class.java)
        registerClasses( SettingsUploadLogResource::class.java)
        registerClasses(ArticleDeleteRequestResource::class.java)
    }
}
