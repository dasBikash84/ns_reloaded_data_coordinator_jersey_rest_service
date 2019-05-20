### REST service End point details:

#### [`General Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/GeneralLog.kt) [*endpoints*](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/GeneralLogResource.kt):

<a name="latest_log"></a>  
* For latest *General Log* entries:
   * Type: `GET`
   * Path: http://localhost:8097/general-logs?page-size={page-size}
   * Query Param: page-size(result-count) | optional | Default 10 | Max 50
   * Response: A [`list`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/OutputWrappers.kt) of latest *General Log* entries if any or [`HttpStatus.NOT_FOUND`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html#NOT_FOUND).
<a name="log_before_given_id"></a>    
* For *General Log* entries `Before` Given Id:
  * Type: `GET`
  * Path: http://localhost:8097/general-logs/before/{log-id}?page-size={page-size}
  * Path Param: *log-id* (id of last *General Log* entry)
  * Query Param: page-size(result-count) | optional | Default 10 | Max 50
  * Response: A list of latest *General Log* entries if any or `HttpStatus.NOT_FOUND` for invalid *log-id*.  
    
* For *General Log* entries `After` Given Id:
  * Type: `GET`
  * Path: http://localhost:8097/general-logs/after/{log-id}?page-size={page-size}
  * Path Param: *log-id* (id of last *General Log* entry)
  * Query Param: page-size(result-count) | optional | Default 10 | Max 50
  * Response: A list of latest *General Log* entries if any or `HttpStatus.NOT_FOUND` for invalid *log-id*.
  
<a name="request_log_delete_token_generation"></a>   
* For *General Log* entries deletion token generation request:
  * Type: `DELETE`
  * Path: http://localhost:8097/general-logs/request_log_delete_token_generation
  * Response: [`LogEntryDeleteRequestFormat`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/LogEntryDeleteRequest.kt) 
  (and generated token will be emailed to authorized email addresses) or 
  [`HttpStatus.INTERNAL_SERVER_ERROR`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html#INTERNAL_SERVER_ERROR) for failure.
<a name="log_delete_request"></a>   
* For *General Log* entries deletion:
  * Type: `DELETE`
  * Path: http://localhost:8097/general-logs
  * RequestBody : [`LogEntryDeleteRequest`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/LogEntryDeleteRequest.kt)
  * Response: List of deleted *General Log* entries or 
  [`HttpStatus.BAD_REQUEST`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html#BAD_REQUEST) for invalid `LogEntryDeleteRequest`.
  
#### [`Error Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/ErrorLog.kt) [*endpoints*](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/ErrorLogResource.kt):
* For latest *Error Log* entries:
    * Type: `GET`
    * Path: http://localhost:8097/error-logs?page-size={page-size}
    * Query Param & Response: Format same as [`General Log`](#latest_log) but for `Error Log`
     
* For *Error Log* entries `Before` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/error-logs/before/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Error Log`.  
    
* For *Error Log* entries `After` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/error-logs/after/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Error Log`.  
    
* For *Error Log* entries deletion token generation request:
    * Type: `DELETE`
    * Path: http://localhost:8097/error-logs/request_log_delete_token_generation
    * Response: Format same as [`General Log`](#request_log_delete_token_generation) but for `Error Log`. 
    
* For *Error Log* entries deletion:
    * Type: `DELETE`
    * Path: http://localhost:8097/error-logs 
    * Response: Format same as [`General Log`](#log_delete_request) but for `Error Log`. 
    
  
#### [`Article Upload Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/ArticleUploadLog.kt) [*endpoints*](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/ArticleUploadLogResource.kt):
* For latest *Article Upload Log* entries:
    * Type: `GET`
    * Path: http://localhost:8097/article-upload-logs?page-size={page-size}
    * Query Param & Response: Format same as [`General Log`](#latest_log) but for `Article Upload Log`
     
* For *Article Upload Log* entries `Before` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/article-upload-logs/before/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Article Upload Log`.  
     
* For *Article Upload Log* entries `After` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/article-upload-logs/after/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Article Upload Log`.  
    
* For *Article Upload Log* entries deletion token generation request:
    * Type: `DELETE`
    * Path: http://localhost:8097/article-upload-logs/request_log_delete_token_generation
    * Response: Format same as [`General Log`](#request_log_delete_token_generation) but for `Article Upload Log`. 
    
* For *Article Upload Log* entries deletion:
    * Type: `DELETE`
    * Path: http://localhost:8097/article-upload-logs 
    * Response: Format same as [`General Log`](#log_delete_request) but for `Article Upload Log`. 
    
#### [`Article Download Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/ArticleDownloadLog.kt) [*endpoints*](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/ArticleDownloadLogResource.kt):
* For latest *Article Download Log* entries:
    * Type: `GET`
    * Path: http://localhost:8097/article-download-logs?page-size={page-size}
    * Query Param & Response: Format same as [`General Log`](#latest_log) but for `Article Download Log`
     
* For *Article Download Log* entries `Before` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/article-download-logs/before/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Article Download Log`.  
    
* For *Article Download Log* entries `After` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/article-download-logs/after/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Article Download Log`.  
    
* For *Article Download Log* entries deletion token generation request:
    * Type: `DELETE`
    * Path: http://localhost:8097/article-download-logs/request_log_delete_token_generation
    * Response: Format same as [`General Log`](#request_log_delete_token_generation) but for `Article Download Log`. 
    
* For *Article Download Log* entries deletion:
    * Type: `DELETE` 
    * Path: http://localhost:8097/article-download-logs 
    * Response: Format same as [`General Log`](#log_delete_request) but for `Article Download Log`. 
    
#### [`Settings Update Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/SettingsUpdateLog.kt) [*endpoints*](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/SettingsUpdateLogResource.kt):
* For latest *Settings Update Log* entries:
    * Type: `GET`
    * Path: http://localhost:8097/settings-update-logs?page-size={page-size}
    * Query Param & Response: Format same as [`General Log`](#latest_log) but for `Settings Update Log`
     
* For *Settings Update Log* entries `Before` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/settings-update-logs/before/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Settings Update Log`.  
    
* For *Settings Update Log* entries `After` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/settings-update-logs/after/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Settings Update Log`.  
    
* For *Settings Update Log* entries deletion token generation request:
    * Type: `DELETE`
    * Path: http://localhost:8097/settings-update-logs/request_log_delete_token_generation
    * Response: Format same as [`General Log`](#request_log_delete_token_generation) but for `Settings Update Log`. 
    
* For *Settings Update Log* entries deletion:
    * Type: `DELETE` 
    * Path: http://localhost:8097/settings-update-logs 
    * Response: Format same as [`General Log`](#log_delete_request) but for `Settings Update Log`. 
    
#### [`Settings Upload Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/SettingsUploadLog.kt) [*endpoints*](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/SettingsUploadLogResource.kt):
* For latest *Settings Upload Log* entries:
    * Type: `GET`
    * Path: http://localhost:8097/settings-upload-logs?page-size={page-size}
    * Query Param & Response: Format same as [`General Log`](#latest_log) but for `Settings Upload Log`
     
* For *Settings Upload Log* entries `Before` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/settings-upload-logs/before/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Settings Upload Log`.  
    
* For *Settings Upload Log* entries `After` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/settings-upload-logs/after/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Settings Upload Log`.  
    
* For *Settings Upload Log* entries deletion token generation request:
    * Type: `DELETE`
    * Path: http://localhost:8097/settings-upload-logs/request_log_delete_token_generation
    * Response: Format same as [`General Log`](#request_log_delete_token_generation) but for `Settings Upload Log`. 
    
* For *Settings Upload Log* entries deletion:
    * Type: `DELETE` 
    * Path: http://localhost:8097/settings-upload-logs 
    * Response: Format same as [`General Log`](#log_delete_request) but for `Settings Upload Log`.
    
#### [`Article Uploader Status Change Log`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/database/log_entities/ArticleUploaderStatusChangeLog.kt) [*endpoints*](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/jersey/rest_resources/ArticleUploaderStatusChangeLogResource.kt):
* For latest *Article Uploader Status Change Log* entries:
    * Type: `GET`
    * Path: http://localhost:8097/article-uploader-status-change-logs?page-size={page-size}
    * Query Param & Response: Format same as [`General Log`](#latest_log) but for `Article Uploader Status Change Log`
     
* For *Article Uploader Status Change Log* entries `Before` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/article-uploader-status-change-logs/before/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Article Uploader Status Change Log`.  
    
* For *Article Uploader Status Change Log* entries `After` Given Id:
    * Type: `GET`
    * Path: http://localhost:8097/article-uploader-status-change-logs/after/{log-id}?page-size={page-size}
    * Path Param, Query Param & Response: Format same as [`General Log`](#log_before_given_id) but for `Article Uploader Status Change Log`.  
    
* For *Article Uploader Status Change Log* entry insert token generation request:
    * Type: `GET`
    * Path: http://localhost:8097/article-uploader-status-change-logs/request_status_change_token_generation
    * Response: [`ArticleUploaderStatusChangeRequestFormat`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/ArticleUploaderStatusChangeRequest.kt) 
                  (and generated token will be emailed to authorized email addresses) or 
                  `HttpStatus.INTERNAL_SERVER_ERROR` for failure. 
    
* For *Article Uploader Status Change Log* entries deletion:
    * Type: `POST` 
    * Path: http://localhost:8097/article-uploader-status-change-logs/status_change_request 
  * RequestBody : [`ArticleUploaderStatusChangeRequest`](https://github.com/dasBikash84/ns_reloaded_data_coordinator_jersey_rest_service/blob/master/src/main/kotlin/com/dasbikash/news_server_data_coordinator_rest_jersey/model/ArticleUploaderStatusChangeRequest.kt)
  * Response: Inserted *Article Uploader Status Change Log* entry or `HttpStatus.BAD_REQUEST` for invalid `ArticleUploaderStatusChangeRequest`.
    
