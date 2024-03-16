package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class IndexController {

	@RequestMapping("/index")
	public void showIndex() {
		log.info("showIndex ½ÇÇàµÊ");
	}
}
