package com.zcx.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.zcx.exam.dao")
public class SpringApplicationApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationApp.class, args);
	}

}
