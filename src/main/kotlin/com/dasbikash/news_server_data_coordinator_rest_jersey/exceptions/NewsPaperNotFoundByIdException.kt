package com.dasbikash.news_server_data_coordinator_rest_jersey.exceptions

class NewsPaperNotFoundByIdException : DataNotFoundException {
    constructor(newspaperId: String) : super("Newspaper with id: ${newspaperId} was not found.")
}