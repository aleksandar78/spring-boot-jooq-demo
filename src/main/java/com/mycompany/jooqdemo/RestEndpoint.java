package com.mycompany.jooqdemo;

import com.mycompany.jooq.model.tables.records.UsersRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEndpoint {
	
	private UserService userService;
	
	public RestEndpoint(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/user")
	public String getUser() {
		UsersRecord rec = userService.getUser();
		rec.setName(null); // This will throw org.springframework.dao.DataIntegrityViolationException
		rec.update();
		return "";
	}
	
}
