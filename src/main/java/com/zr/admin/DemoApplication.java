package com.zr.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan({"com.zr.admin.dao"})
public class DemoApplication {
	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}
