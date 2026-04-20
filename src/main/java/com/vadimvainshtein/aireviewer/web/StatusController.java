package com.vadimvainshtein.aireviewer.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
class StatusController {

	@GetMapping
	StatusResponse getStatus() {
		return new StatusResponse("ai-reviewer", "UP");
	}

	record StatusResponse(String application, String status) {
	}
}
