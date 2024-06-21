package com.kh.reMerge.feed.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.reMerge.feed.model.service.StoryService;
import com.kh.reMerge.feed.model.vo.Story;

@Controller
public class StoryController {
	
	@Autowired
	private StoryService storyService;

	//스토리 추가하기
	@PostMapping("insertStory.fe")
	public String insertStory(Story s,MultipartFile storyFile,HttpSession session) {
		
		//스토리 기능은 사진이 필수 이기에 사진 유무는 처리 하지 않음	
		//만들어둔 파일 업로드 처리 메소드 사용
		String changeName = saveFile(storyFile,session);
		
		s.setOriginName(storyFile.getOriginalFilename());
		s.setChangeName("resources/uploadFiles/"+changeName);
		
		int result = storyService.insertStory(s);
		
		if(result>0) {
			session.setAttribute("alertMsg", "성공적으로 등록되었습니다.");
			return "feed/mainFeed";
		}else {
			session.setAttribute("alertMsg", "등록되지 않았습니다. 관리자에게 문의하세요.");
			return "feed/mainFeed";
		}
		
	}
	
	//피드페이지에서 스토리 조회하기
	@ResponseBody
	@PostMapping(value="selectStory.fe",produces="application/json;charset=UTF-8")
	public ArrayList<Story> selectStory(String userId){
		
		return storyService.selectStory(userId);
	}
	
	//파일 업로드 처리 메소드 (파일 이름 바꿔주기 모듈화)
	public String saveFile(MultipartFile upfile,HttpSession session) {
			//파일명 수정작업하기
			//1.원본파일명 추출
			String originName = upfile.getOriginalFilename();
			//2.시간형식 문자열로 만들기
			//20240527162730
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			//3.확장자 추출하기 파일명 뒤에서부터 . 찾아서 뒤로 잘라내기
			String ext = originName.substring(originName.lastIndexOf("."));
			//4.랜덤값 5자리 뽑기
			int ranNum = (int)(Math.random()*90000+10000);
			//5.하나로 합쳐주기
			String changeName = currentTime+ranNum+ext;
			//6.업로드하고자하는 물리적인 경로 알아내기 (프로젝트 내에 저장될 실제 경로 찾기)
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
			try {
			//7.경로와 수정 파일명을 합쳐서 파일 업로드 처리하기
			upfile.transferTo(new File(savePath+changeName));
			} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			return changeName;
		}
	
	
	
}
