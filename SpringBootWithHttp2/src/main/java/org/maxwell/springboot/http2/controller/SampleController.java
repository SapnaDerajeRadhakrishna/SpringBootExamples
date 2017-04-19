package org.maxwell.springboot.http2.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SampleController {

	@Autowired
	ObjectMapper mapper;

	@PostConstruct
	public void init() {
		LOGGER.info("loading");
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

	@GetMapping("/getDetails")
	public void getStudentDetails(@RequestParam(value = "reset", defaultValue = "false") boolean reset) {
		LOGGER.info("0; received GET request for updating the student details");
	}

	@PostMapping("/updateDetails")
	public void updateStudentDetails(@RequestBody Object object) {
		LOGGER.info("0; recieved request for updating the student details");
		StudentDetails details1 = mapper.convertValue(object, StudentDetails.class);
		TeacherDetails details2 = mapper.convertValue(object, TeacherDetails.class);
		LOGGER.info("0; Student Details: ID: {} Name:{}", details1.getStudentID(), details1.getStudentName());
		LOGGER.info("0; Teacher Details: ID: {} Name:{}", details2.getTeacherID(), details2.getTeacherName());

	}

}
