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
			
			// 메세지가 한번만 나오게 사용  session에 저장했다가 한번만 쓰고 지워짐 
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
//			model.addAttribute("boardDto", boardDto);  아래 문장과 동일 타입의 첫글자를 소문자로 해서 이름을 저장함
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
			return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동
		
		
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
		
		return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
	}

	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 얻어서
		HttpSession session = request.getSession();
		// 2. 세션에 id가 있는지 확인, 있으면 true를 반환
		return session.getAttribute("id")!=null;
	}
	
	
}
