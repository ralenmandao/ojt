package com.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import com.google.common.util.concurrent.MoreExecutors.Application

@SpringBootApplication
public class ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}