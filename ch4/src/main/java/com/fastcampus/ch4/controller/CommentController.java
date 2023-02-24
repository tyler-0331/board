package com.fastcampus.ch4.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import com.mysql.cj.Session;

@Controller
public class CommentController {
	@Autowired 
	CommentService service;
	
	 // 댓글을 수정하는 메서드
	@ResponseBody
    @PatchMapping("/comments/{cno}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto) {
//        String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";
        dto.setCommenter(commenter);
        dto.setCno(cno);
        System.out.println("dto = " + dto);

        try {
            if(service.modify(dto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }
	
	@ResponseBody
	@RequestMapping(value = "/comments", method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody CommentDto dto, Integer bno, HttpSession session) {
		String commenter = "asdf";
		dto.setCommenter(commenter);
		dto.setBno(bno);
		System.out.println("dto = " + dto);
		
		try {
			if (service.write(dto)!= 1) {
				throw new Exception("Write failed.");
			}
			return new ResponseEntity<String>("WRT_OK",HttpStatus.OK);
				
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("WRT_ERR",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	// 지정된 댓글을 삭제하는 메서드
	@DeleteMapping("/comments/{cno}")   // coments/1  <-- 삭제할 댓글 번호
	@ResponseBody
	public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
		
//		String commenter = (String)session.getAttribute("id");
		String commenter = "asdf";
		try {
			int rowCnt = service.remove(cno, bno, commenter);
			
			if (rowCnt != 1) {
				throw new Exception("Delete Failed");
			}
			return new ResponseEntity<String>("DEL_OK",HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("DEL_ERR",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	// 지정된 게시물의 모든 댓글을 가져오는 메서드
	@RequestMapping("/comments")
	@ResponseBody
	public ResponseEntity<List<CommentDto>> list(Integer bno) {
		List<CommentDto> list = null;
		try {
			list = service.getList(bno);
			return new ResponseEntity<List<CommentDto>> (list,HttpStatus.OK); //200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CommentDto>> (HttpStatus.BAD_REQUEST); // 400
		}
	}
}
