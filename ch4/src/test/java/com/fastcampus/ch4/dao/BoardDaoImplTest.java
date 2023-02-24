package com.fastcampus.ch4.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest {
	@Autowired
	private BoardDao boardDao;
	
//	@Test
//	public void test() throws Exception {
//		boardDao.deleteAll();
//		for (int i = 1; i < 220; i++) {
//			BoardDto boardDto = new BoardDto("title" + i, "no content","asdf");
//			boardDao.insert(boardDto);
//		}
//	}
	
	@Test
	public void test2() throws Exception {
		boardDao.deleteAll();
		for (int i = 0; i <= 20 ; i++) {
			BoardDto boardDto = new BoardDto("title" + i, "abcdefggg","asdf" + i);
			boardDao.insert(boardDto);
		}
		
		SearchCondition sc = new SearchCondition(1,10,"title2","T"); // title2%
		List<BoardDto> list = boardDao.searchSelectPage(sc);
		System.out.println("list =" + list);
		assertTrue(list.size() == 2);
		
		sc = new SearchCondition(1,10,"asdf2","W"); // asdf2%
		list = boardDao.searchSelectPage(sc);
		System.out.println("list =" + list);
		assertTrue(list.size() == 2);
	}
	
	@Test
	public void test3() throws Exception {
		boardDao.deleteAll();
		for (int i = 0; i <= 20 ; i++) {
			BoardDto boardDto = new BoardDto("title" + i, "abcdefggg","asdf");
			boardDao.insert(boardDto);
		}
		
		SearchCondition sc = new SearchCondition(1,10,"title2","T");
		int cnt = boardDao.searchResultCnt(sc);
		System.out.println("cnt= " + cnt);
		assertTrue(cnt == 2);
		
	}
}
