package com.hocvoichuyengia.spring.task_management.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hocvoichuyengia.spring.task_management.entity.Staff;

@RestController
@RequestMapping("/assignable")
public class AssignableStaff {

	
	
	@GetMapping("/test")
	public  Staff testStaff() {
		
		return null;
	}
	
}
