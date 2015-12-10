package com.ralen.di;

public class Main {
	public static void main(String[] args){
		ApplicationContext context = new ApplicationContextImp("com.ralen.di");
		Person ralen = context.getBean("person");
		ralen.printWidth();
	}
}
