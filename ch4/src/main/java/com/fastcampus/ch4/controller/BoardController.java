package com.fastcampus.ch4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	
	@PostMapping("/modify")
	public String modify(BoardDto boardDto, HttpSession session, Model model, RedirectAttributes rattr) {
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {
			int modCnt = boardService.modify(boardDto); // insert 
			if(modCnt != 1) {
				throw new Exception("Modify failed");
			}
			
			rattr.addFlashAttribute("msg","MOD_OK");
			
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("boardDto", boardDto);
			rattr.addAttribute("msg","MOD_ERR");
			return "modify";
		}
		
	}
	
	
	@GetMapping("/write")
	public String write(Model model) {
		model.addAttribute("mode", "new");
		return "board";
	}
	
	
	@PostMapping("/write")
	public String write(BoardDto boardDto, HttpSession session, Model model, RedirectAttributes rattr) {
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {
			int insertCnt = boardService.write(boardDto); // insert 
			if(insertCnt != 1) {
				throw new Exception("Write failed");
			}
			
			rattr.addFlashAttribute("msg","WRT_OK");
			
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("boardDto", boardDto);
			rattr.addAttribute("msg","WRT_ERR");
			return "board";
		}
		
	}
	
	
	@PostMapping("/remove")
	public String remove(Integer bno, Integer page, Integer pageSize, HttpSession session,Model model, RedirectAttributes rattr) {
		String wrtier = (String)session.getAttribute("id");
		System.out.println(bno);
		
		try {
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			
			int removeCnt = boardService.remove(bno, wrtier);
			
			if (removeCnt != 1) {
				throw new Exception("board remove error");
			}
			
			// ???????????? ????????? ????????? ??????  session??? ??????????????? ????????? ?????? ????????? 
			rattr.addFlashAttribute("msg", "ok");

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "not_ok");

		}
		
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/read")
	public String read(Integer bno, Integer page, Integer pageSize, Model model) {
		try {
			BoardDto boardDto = boardService.read(bno);
//			model.addAttribute("boardDto", boardDto);  ?????? ????????? ?????? ????????? ???????????? ???????????? ?????? ????????? ?????????
			model.addAttribute(boardDto);
			model.addAttribute("page", page);
			model.addAttribute("pageSize", pageSize);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "board";
	}
	
	
	@GetMapping("/list")
	public String list(SearchCondition sc, Model model, HttpServletRequest request) {
		if(!loginCheck(request))
			return "redirect:/login/login?toURL="+request.getRequestURL();  // ???????????? ???????????? ????????? ???????????? ??????
		
		
		try {
			int totalCnt = boardService.searchResultCnt(sc);
			model.addAttribute("totalCnt", totalCnt);
			
			PageHandler ph = new PageHandler(totalCnt,sc);
			List<BoardDto> list = boardService.searchSelectPage(sc);
			model.addAttribute("list", list);
			model.addAttribute("ph", ph);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "boardList"; // ???????????? ??? ????????????, ????????? ???????????? ??????
	}

	private boolean loginCheck(HttpServletRequest request) {
		// 1. ????????? ?????????
		HttpSession session = request.getSession();
		// 2. ????????? id??? ????????? ??????, ????????? true??? ??????
		return session.getAttribute("id")!=null;
	}
	
	
}
