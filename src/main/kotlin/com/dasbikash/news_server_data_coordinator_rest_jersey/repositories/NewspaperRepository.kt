package com.dasbikash.news_server_data_coordinator_rest_jersey.repositories

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.Newspaper
import org.springframework.data.jpa.repository.JpaRepository

interface NewspaperRepository : JpaRepository<Newspaper, String>{
    fun findAllByActive(active:Boolean=true):List<Newspaper>
}