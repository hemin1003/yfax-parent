package com.yfax.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Minbo.He
 */
@SpringBootApplication
@EnableScheduling
public class Application {
	
	// 服务启动入口
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
