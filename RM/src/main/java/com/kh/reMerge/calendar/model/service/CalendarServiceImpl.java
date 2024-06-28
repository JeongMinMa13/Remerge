package com.kh.reMerge.calendar.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.reMerge.calendar.model.dao.CalendarDao;
import com.kh.reMerge.calendar.model.vo.Schedule;
import com.kh.reMerge.user.model.vo.FollowList;
import com.kh.reMerge.user.model.vo.User;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private CalendarDao calendarDao;
	
	//일정 추가구문
	@Override
	public int insertSchedule(Schedule s) {
		
		return calendarDao.insertSchedule(sqlSession,s);
	}
	
	//일정 조회 구문
	@Override
	public ArrayList<Schedule> selectSchedule(String userId) {
		
		return calendarDao.selectSchedule(sqlSession,userId);
	}
	
	//일정 세부 조회 구문
	@Override
	public Schedule detailSchedule(int scheduleNo) {

		return calendarDao.detailSchedule(sqlSession,scheduleNo);
	}
	
	//일정 삭제 구문
	@Override
	public int deleteSchedule(int scheduleNo) {

		return calendarDao.deleteSchedule(sqlSession,scheduleNo);
	}
	
	//일정 수정 구문
	@Override
	public int updateSchedule(Schedule s) {

		return calendarDao.updateSchedule(sqlSession,s);
	}
	
	//팔로우 리스트로 이동하기 위한 팔로우 리스트 조회 
	@Override
	public ArrayList<User> followList(String userId) {

		return calendarDao.followList(sqlSession,userId);
	}
	
	//공유 캘린더 조회
	@Override
	public ArrayList<Schedule> selectShareSchedule(String userId) {

		return calendarDao.selectShareSchedule(sqlSession,userId);
	}
}
