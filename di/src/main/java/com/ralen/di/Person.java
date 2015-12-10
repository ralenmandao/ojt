package com.ralen.di;

import com.ralen.di.annotation.Bean;
import com.ralen.di.annotation.Inject;

@Bean
public class Person {
	@Inject("width")
	private int width;
	
	public void printWidth(){
		System.out.println(width);
	}
}
