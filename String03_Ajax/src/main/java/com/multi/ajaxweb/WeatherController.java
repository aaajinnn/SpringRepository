package com.multi.ajaxweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class WeatherController {

	@RequestMapping("/weather")
	public void weatherView() {
		
	}
}
