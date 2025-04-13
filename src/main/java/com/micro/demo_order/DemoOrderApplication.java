package com.micro.demo_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class DemoOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOrderApplication.class, args);
	}

}
