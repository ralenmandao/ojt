package com.server.entity

import org.springframework.data.annotation.Id

class Person {
	@Id
	String id
	String firstName
	String lastName
}
