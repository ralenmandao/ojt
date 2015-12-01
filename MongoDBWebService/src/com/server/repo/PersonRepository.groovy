package com.server.repo

import org.springframework.data.mongodb.repository.MongoRepository

import com.server.entity.Person

interface PersonRepository extends MongoRepository<Person, String>{

}
