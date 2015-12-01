package com.ralen.activity3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

import com.ralen.activity3.service.LoanBrokerService

class Main{
	static void main(args){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		LoanBrokerGUI gui = context.getBean(LoanBrokerGUI.class)
		gui.show();
	}
}
