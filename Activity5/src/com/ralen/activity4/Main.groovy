package com.ralen.activity4

import com.ralen.activity4.model.Person

class Main {
	static void main(args){
		Person.metaClass.printName{-> println delegate.name }
		def person = new Person(name: 'ralen')
		person.printName()
		
		Object.metaClass.explode{-> println "Boom! ${delegate} Exploded!"}
		"SomeString".explode();
		12345.explode();
		
		String.metaClass.splitify{-> delegate.split('').each{ print "${it}      " } }
		"Ralen".splitify()
	}
}
