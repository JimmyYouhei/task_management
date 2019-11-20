package com.hvcg.api.task_management.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hvcg.api.task_management.dao.InternalStaffRepository;
import com.hvcg.api.task_management.dao.SecurityRepository;
import com.hvcg.api.task_management.dto.StaffDetailDto;
import com.hvcg.api.task_management.entity.Staff;
import com.hvcg.api.task_management.entity.User;

@RestController
@RequestMapping("/detail")
public class ChangeStaffDetailController {
	
	@Autowired
	private SecurityRepository securityRepository;
	
	
	@Autowired
	private InternalStaffRepository internalStaffRepository;

	
	@GetMapping("/currentStaff")
	public Staff getLoggedInStaffDetail() {
	
		return getCurrentLoggedInStaffDetail();
		
		
	}

	@PatchMapping("/currentStaff")
	public Staff changeCurrentStaffDetail (@RequestBody StaffDetailDto staffDetailDto) {
		Staff currentStaffDetail = getCurrentLoggedInStaffDetail();
		
		if(modifiedCurrentStaffDetail(currentStaffDetail, staffDetailDto)) {
			currentStaffDetail.setUpdateTime(new Date());
			currentStaffDetail.setUpdateBy(currentStaffDetail);
			
			internalStaffRepository.saveAndFlush(currentStaffDetail);
		}
		
		return currentStaffDetail;
	}

	private Staff getCurrentLoggedInStaffDetail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = securityRepository.findByUsername(username).get();
		
		Staff currentStaffDetail = user.getStaff();
		return currentStaffDetail;
	}

	private boolean modifiedCurrentStaffDetail(Staff currentStaffDetail , StaffDetailDto staffDetailDto) {
		boolean modified = false;
		
		if(staffDetailDto.getFullName() != null) {
			currentStaffDetail.setFullName(staffDetailDto.getFullName());
			modified = true;
		}
		
		if(staffDetailDto.getDateOfBirth() != null) {
			currentStaffDetail.setDateOfBirth(staffDetailDto.getDateOfBirth());
			modified = true;
		}
		
		if(staffDetailDto.getPhoneNumber() != null) {
			currentStaffDetail.setPhoneNumber(staffDetailDto.getPhoneNumber());
			modified = true;
		}
		
		if(staffDetailDto.getEmail() != null) {
			currentStaffDetail.setEmail(staffDetailDto.getEmail());
			modified = true;
		}
		
		if(staffDetailDto.getFacebook() != null) {
			currentStaffDetail.setFacebook(staffDetailDto.getFacebook());
			modified = true;
		}
		
		return modified;
		
	} 

	
}

