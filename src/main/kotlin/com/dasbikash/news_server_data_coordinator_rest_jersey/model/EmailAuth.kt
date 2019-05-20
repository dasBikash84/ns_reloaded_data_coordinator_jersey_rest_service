package com.dasbikash.news_server_data_coordinator_rest_jersey.model

data class EmailAuth (
    var userName:String? = null,
    var passWord:String? = null,
    var properties:Map<String,String>?=null
)