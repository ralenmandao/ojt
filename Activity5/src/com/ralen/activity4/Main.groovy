package com.ralen.activity4

import com.ralen.activity4.model.Person

class Main {
	static void main(args){
		println "Person's Methods before add : ${Person.metaClass.methods*.name}"
		Person.metaClass.printName{-> println delegate.name }
		def person = new Person(name: 'ralen')
		person.printName()
		println "Person's Methods after add : ${Person.metaClass.methods*.name}"
		
		Object.metaClass.explode{-> println "Boom! ${delegate} Exploded!"}
		person.explode()
		"SomeString".explode()
		12345.explode()
		"LOl".explode()
		
		String.metaClass.splitify{-> delegate.split('').each{ print "${it}      " } }
		"Ralen".splitify()
		"Mandap".splitify()
		person.toString().splitify()
	}
}
