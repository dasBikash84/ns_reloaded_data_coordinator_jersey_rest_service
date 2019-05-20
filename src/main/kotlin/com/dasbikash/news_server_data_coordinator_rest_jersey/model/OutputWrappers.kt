package com.dasbikash.news_server_data_coordinator_rest_jersey.model

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.*
import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.log_entities.*
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class ArticleDownloadLogs(
        var articleDownloadLogs:List<ArticleDownloadLog>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return articleDownloadLogs?.size ?: 0
    }
}

@XmlRootElement
class ArticleUploaderStatusChangeLogs(
        var articleUploaderStatusChangeLogs:List<ArticleUploaderStatusChangeLog>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return articleUploaderStatusChangeLogs?.size ?: 0
    }
}

@XmlRootElement
class ArticleUploadLogs(
        var articleUploadLogs:List<ArticleUploadLog>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return articleUploadLogs?.size ?: 0
    }
}

@XmlRootElement
class ErrorLogs(
        var errorLogs:List<ErrorLog>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return errorLogs?.size ?: 0
    }
}

@XmlRootElement
class GeneralLogs(
        var generalLogs:List<GeneralLog>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return generalLogs?.size ?: 0
    }
}
@XmlRootElement
class SettingsUpdateLogs(
        var settingsUpdateLogs:List<SettingsUpdateLog>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return settingsUpdateLogs?.size ?: 0
    }
}
@XmlRootElement
class SettingsUploadLogs(
        var settingsUploadLogs:List<SettingsUploadLog>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return settingsUploadLogs?.size ?: 0
    }
}

@XmlRootElement
class Articles(
        var articles:List<Article>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return articles?.size ?: 0
    }
}
@XmlRootElement
class Countries(
        var countries:List<Country>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return countries?.size ?: 0
    }
}
@XmlRootElement
class Languages(
        var languages:List<Language>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return languages?.size ?: 0
    }
}

@XmlRootElement
class Newspapers(
        var newspapers:List<Newspaper>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return newspapers?.size ?: 0
    }
}

@XmlRootElement
class Pages(
        var pages:List<Page>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return pages?.size ?:0
    }
}

@XmlRootElement
class PageGroups(
        var pageGroupMap:Map<String,PageGroup>?=null
): DataCoordinatorRestEntity,OutputWrapper{
    override fun getOutPutCount(): Int {
        return pageGroupMap?.values?.size ?: 0
    }
}

interface OutputWrapper{
    fun getOutPutCount():Int
}