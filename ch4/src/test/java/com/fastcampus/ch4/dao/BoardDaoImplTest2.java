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
public class BoardDaoImplTest2 {
	@Autowired
	BoardDao boardDao;
	
	@Test
	public void testSelect() throws Exception {
		assertTrue(boardDao != null);
		System.out.println("boardDao =" + boardDao);
		
		BoardDto boardDto = boardDao.select(2);
		assertTrue(boardDto.getBno().equals(2));
		System.out.println("boardDto =" + boardDto);
	}

}
