package org.maxwell.springboot.http2.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SampleController {


	@PostConstruct
	public void init() {
		LOGGER.info("loading");
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

	@GetMapping("/getDetails")
	public void getStudentDetails(@RequestParam(value = "reset", defaultValue = "false") boolean reset) {
		LOGGER.info("0; received GET request for updating the student details");
	}

}
