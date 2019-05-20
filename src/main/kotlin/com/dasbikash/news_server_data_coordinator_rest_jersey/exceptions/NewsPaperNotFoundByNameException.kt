package com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions

class NewsPaperNotFoundByNameException(newsPaperName: String) :
        DataNotFoundException("Newspaper with name: ${newsPaperName} was not found.")
