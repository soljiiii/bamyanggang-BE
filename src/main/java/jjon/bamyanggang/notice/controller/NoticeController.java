package jjon.bamyanggang.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jjon.bamyanggang.model.NoticeDto;
import jjon.bamyanggang.notice.service.NoticeServiceImpl;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeServiceImpl noticeService;
	
	//공지사항 목록
	@GetMapping("/noticelist")
	public ResponseEntity<Map<String, Object>> noticelist(){
		
		List<NoticeDto> noticeList = noticeService.getNoticeList();
		System.out.println("글목록 불러오기"+noticeList);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("notices", noticeList);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	//상세페이지(조회수 1증가 + 상세내용)
	@GetMapping("/noticecontent/{postNo}")
	public ResponseEntity<Map<String, Object>> noticecontent(@PathVariable("postNo") int postNo){
		
		noticeService.updateVw(postNo); // 조회수 1 증가
		NoticeDto notice = noticeService.getNotice(postNo); // 상세내용 구하기
		
		Map<String, Object> map = new HashMap<>();
		map.put("notice", notice);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
		
	}

}