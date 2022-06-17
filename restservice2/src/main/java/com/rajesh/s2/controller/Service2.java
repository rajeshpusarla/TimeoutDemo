package com.rajesh.s2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s2")
public class Service2 {
	@PostMapping(path = "/receive")	
	public void receiveMessage(@RequestBody String msg) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("message received " + msg);
	}
	
	@GetMapping("/ping")
	public String test() {
		return "s2 ready ...";
	}
}
