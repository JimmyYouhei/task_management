package com.hvcg.api.task_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main run class that is auto created by SpringBoot
 * 
 * 
 * @author JY
 *
 */


@SpringBootApplication
@EnableSwagger2

public class TaskManagementApplication {


	
	public static void main(String[] args) {

		SpringApplication.run(TaskManagementApplication.class, args);
	}

}



