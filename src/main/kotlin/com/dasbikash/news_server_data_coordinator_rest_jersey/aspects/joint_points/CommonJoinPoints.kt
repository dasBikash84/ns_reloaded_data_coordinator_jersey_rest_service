package com.dasbikash.news_server_data_coordinator_rest_jersey.aspects.joint_points

import org.aspectj.lang.annotation.Pointcut

class CommonJoinPoints {

    @Pointcut("execution(* com.dasbikash.news_server_data_coordinator_rest_jersey.jersey.rest_controllers..*EndPoint(..))")
    fun allControllersEndPoints() {}
}
