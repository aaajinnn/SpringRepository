package com.multi.ajaxweb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.domain.NewsVO;
import com.multi.domain.UserVO;
import com.multi.service.SampleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequiredArgsConstructor
public class AjaxController {

	private final SampleService sampleService;
	
	@RequestMapping("/ajaxView")
	public void ajaxView() {
		
	}//--------------------------
	
	@GetMapping(value="/ajaxText", produces = {"text/plain; charset=utf-8"})
	@ResponseBody 
	public String ajaxResponseText(@RequestParam(defaultValue="") String phone) {
		log.info("phone : " + phone);
		
		//return "10#김철수#서울#" + phone;
		if(phone.startsWith("1")) {
			return "<img src='resources/img.jpg'>";
		}else {
			return "<img src='resources/img2.jpg'>";
		}
	}//--------------------------
	
	@GetMapping(value="/ajaxXml", produces = {"text/xml; charset=utf-8"})
	@ResponseBody
	public Map<String, String> ajaxResponseXml(@RequestParam(defaultValue="") String phone){
		Map<String, String> map = new HashMap<>();
		map.put("no", "55");
		map.put("name", "이영희");
		map.put("addr", "서울 광진구");
		map.put("phone", "phone");
		
		return map;
	}//--------------------------
	
	@RequestMapping(value = "/ajaxJson", produces = {"application/json; charset=utf-8"})
	@ResponseBody
	public UserVO ajaxResponseJson(UserVO user) {
		log.info("user : " + user); //파라미터 데이터를 받는다(연락처, 주소)
		user.setNo(88);
		user.setName("배현경");
		return user; //파라미터 데이터에 이 값들을 포함해서 넘기기
	}//--------------------------
	
	@RequestMapping(value="/ajaxJsonList",
			produces = {"application/json; charset=utf-8"})
	@ResponseBody
	public List<UserVO> ajaxResponseJsonList(UserVO user){
		log.info("json형식 파라미터 user : " + user);
		
		List<UserVO> arr = new ArrayList<>();
		arr.add(new UserVO(10,"홍길동","수원 1번지","2222"));
		arr.add(new UserVO(20,"김길동","수원 2번지","3222"));
		arr.add(new UserVO(30,"송길동","수원 3번지","4222"));
		user.setNo(40);
		user.setName("배길동");
		arr.add(user);
		
		return arr;
		
	}//--------------------------
	
	@PostMapping(value = "news_dbCreate", produces = {"application/json; charset=utf-8"})
	@ResponseBody
	public ModelMap ajaxRssNewsInsert(NewsVO vo) {
		log.info("vo : " + vo);
		int n = sampleService.createNews(vo);
		
		ModelMap map = new ModelMap();
		map.addAttribute("result", "ok"); // mpa.put() 도 가능
		
		return map;
	}//--------------------------
	
}
