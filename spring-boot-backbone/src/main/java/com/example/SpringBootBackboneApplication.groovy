package com.example;

import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBackboneApplication {

    public SpringBootBackboneApplication(){

    }
    
    public void start(){
    	SpringApplication.run(SpringBootBackboneApplication.class);
    }
}
