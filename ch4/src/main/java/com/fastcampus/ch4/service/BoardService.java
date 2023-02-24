package com.fastcampus.ch4.service;

import java.util.List;
import java.util.Map;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;

public interface BoardService {

	int getCount() throws Exception;

	int remove(Integer bno, String writer) throws Exception;

	int write(BoardDto boardDto) throws Exception;

	List<BoardDto> getList() throws Exception;

	BoardDto read(Integer bno) throws Exception;

	List<BoardDto> getPage(Map map) throws Exception;

	int modify(BoardDto boardDto) throws Exception;

	int searchResultCnt(SearchCondition sc) throws Exception;

	List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;


}