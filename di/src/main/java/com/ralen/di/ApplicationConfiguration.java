package com.ralen.di;

import com.ralen.di.annotation.Bean;
import com.ralen.di.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	@Bean
	public int width(){
		return 30;
	}
}
