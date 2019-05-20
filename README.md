## REST service (developed with Jersey and Spring Core) for monitoring and control of [Data Coordinator App](https://github.com/dasBikash84/news_server_data_coordinator) 

### Technologies used are as below:
* Language: `Kotlin`.
* Database: `MySQL`
* Database access Api: `Spring JPA`
* REST framework: `Spring Core` and `Jersey`.
* AOP framework: `Spring AOP` and `Aspectj`.

### This rest service supports following operations. Details are [here](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/REST_END_POINT%20details.md):

* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/GeneralLogResource.kt)
 and Delete for [`General Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/GeneralLog.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/ErrorLogResource.kt)
 and Delete for [`Error Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/ErrorLog.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/ArticleDownloadLogResource.kt)
 and Delete for [`Article Download Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/ArticleDownloadLog.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/ArticleUploadLogResource.kt)
 and Delete for [`Article Upload Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/ArticleUploadLog.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/SettingsUpdateLogResource.kt)
 and Delete for [`Settings Update Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/SettingsUpdateLog.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/SettingsUploadLogResource.kt)
 and Delete for [`Settings Upload Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/SettingsUploadLog.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/ArticleUploaderStatusChangeLogResource.kt)
 and Post for [`Article Uploader Status Change Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/ArticleUploaderStatusChangeLog.kt) entries.

##### All `Delete` and `Post` operations has to be performed via *OTP*. Details are [here](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/REST_END_POINT%20details.md)

### Beside that it can also act as an back-up data source for Newspaper Settings/Data and supports following end points:
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/CountryResource.kt)
 for [`Country`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/Country.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/LanguageResource.kt)
 for [`Language`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/Language.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/NewsPaperResource.kt)
 for [`Newspaper`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/Newspaper.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/PageResource.kt)
 for [`Page`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/Page.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/PageGroupResource.kt)
 for [`PageGroup`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/PageGroup.kt) entries.
* [Get](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/ArticleResource.kt)
 for [`Article`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/Article.kt) data.
