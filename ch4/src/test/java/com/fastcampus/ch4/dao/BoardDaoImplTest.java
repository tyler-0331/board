package com.fastcampus.ch4.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fastcampus.ch4.domain.BoardDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest {
	@Autowired
	private BoardDao boardDao;
	
	@Test
	public void test() throws Exception {
		boardDao.deleteAll();
		for (int i = 1; i < 220; i++) {
			BoardDto boardDto = new BoardDto("title" + i, "no content","asdf");
			boardDao.insert(boardDto);
		}
	}

}
