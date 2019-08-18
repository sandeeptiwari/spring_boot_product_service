package co.in.techiesandeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@SpringBootApplication
@ComponentScan({"co.in.techiesandeep.controller", "co.in.techiesandeep.model", "co.in.techiesandeep.repository",
		"co.in.techiesandeep.service", "co.in.techiesandeep.aspects", "co.in.techiesandeep.events",
		 "co.in.techiesandeep.listeners"})
public class SpringBootWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWorkApplication.class, args);
	}

}
