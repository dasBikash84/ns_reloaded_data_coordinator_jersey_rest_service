package com.dasbikash.news_server_data_coordinator_rest_jersey.repositories

import com.dasbikash.news_server_data_coordinator_rest_jersey.model.database.PageGroup
import org.springframework.data.jpa.repository.JpaRepository

interface PageGroupRepository : JpaRepository<PageGroup, Int>