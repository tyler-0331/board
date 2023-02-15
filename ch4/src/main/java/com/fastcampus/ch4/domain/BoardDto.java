package com.fastcampus.ch4.domain;

import java.util.Date;

public class BoardDto {

	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Integer view_cnt;
	private Integer comment_cnt;
	private Date reg_date;
	
	public BoardDto() {}
	
	public BoardDto(String title, String content, String writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Integer getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(Integer view_cnt) {
		this.view_cnt = view_cnt;
	}

	public Integer getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(Integer comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "BoardDto [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", view_cnt=" + view_cnt + ", comment_cnt=" + comment_cnt + ", reg_date=" + reg_date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bno == null) ? 0 : bno.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardDto other = (BoardDto) obj;
		if (bno == null) {
			if (other.bno != null)
				return false;
		} else if (!bno.equals(other.bno))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	
	
}
