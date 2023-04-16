package com.bit.bimanda.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.bimanda.service.RecruitService;
import com.bit.bimanda.service.RequestService;
import com.bit.bimanda.service.TeamService;
import com.bit.bimanda.service.UserService;
import com.bit.bimanda.vo.RecruitVo;
import com.bit.bimanda.vo.RequestVo;
import com.bit.bimanda.vo.UserVo;

@Controller
@RequestMapping("/recruit")
public class RecruitController {
	UserVo authUser;
	@Autowired
	RecruitService RecruitServiceImpl;
	@Autowired
	RequestService RequestServcieImpl;
	@Autowired
	UserService UserServiceImpl;
	@Autowired
	TeamService teamServiceImpl;
	String hitCount="false"; //게시판 내용 리다이렉트 될때 카운팅 여부 변수
	
	// 메인에서 '팀만들기' 클릭 시 팀모집글 리스트 화면으로 이동
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String rlist(Model model) {
		List<RecruitVo> list = RecruitServiceImpl.getList();
	      
		for(Iterator<RecruitVo> it = list.iterator(); it.hasNext();) { // 
	    	RecruitVo value = it.next();
	      		
	    	UserVo user = UserServiceImpl.getUserIdName(value.getUserNo());
	    	if(user.getTeamNo()!=null)
	      		it.remove();
	    }
		hitCount="true";
	      List numbers = new ArrayList(); // 번호 매기기
		  for (int i=1; i<=30; i++) {
			  numbers.add(i);
		  }
	      model.addAttribute("pageNum", numbers);
	    model.addAttribute("rlist", list);
	    return "recruit/rlist";
	}
	   
	   // 팀모집글 리스트에서 제목 클릭 시 내용 띄워주는 창으로 이동
	   @RequestMapping(value="/detail")
	   public String detail(@RequestParam(value="no",required=false,defaultValue="") Long no,
	         Model model) {
	      RecruitVo vo = RecruitServiceImpl.getDetail(no);      // 글번호에 해당하는 내용 불러오기
	      List<RequestVo> list = RequestServcieImpl.getList(no);   // 해당글의 댓글 리스트 불러오기
	      System.out.println(list);
	      
	      if(hitCount.equals("true")) {
				RecruitServiceImpl.updatehit(no); //조회수 올리는것
			}
	      hitCount="false";
	      for (RequestVo rvo: list) {
	         UserVo user = UserServiceImpl.getUserIdName(rvo.getUserNo());
	         
	         if (user.getTeamNo() != null) {
	            list.remove(rvo);
	            System.out.println(list);
	            if (list.size() == 0) {
	               break;
	            }
	         }
	      }
	      model.addAttribute("vo", vo);
	      model.addAttribute("list", list);
	      return "recruit/detail";
	   }

	// 팀모집 글쓰기 페이지로 이동
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String recruit(@ModelAttribute RecruitVo vo, HttpSession session) {
		if (session.getAttribute("authUser") == null) {		// 로그인을 하지 않은 경우 이동안함
			return "team/loginfirst";
		}
		else {
			return "recruit/recruit";
		}
	}
	// 팀모집 글쓰기 액션
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String recruitWrite(@ModelAttribute RecruitVo vo) {
		boolean success = false;
		try {
			success = RecruitServiceImpl.recruit(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/recruit/write";
		}
		if (success) {
			return "redirect:/recruit/list";
		} else {
			return "redirect:/recruit/write";
		}
	}
	// 댓글 쓴 사람인지 확인
	@ResponseBody
	@RequestMapping(value="/req/check", method=RequestMethod.POST)
	public String requestCheck(@RequestParam(value="no",required=true,defaultValue="") Long no,
			@RequestParam(value="userNo",required=true,defaultValue="") Long userNo,
			@RequestParam(value="content",required=true,defaultValue="") String content) {
		
		Map<String, Long> map = new HashMap<String, Long>();
		
		map.put("no", no);
		map.put("userNo", userNo);
		
		String result = "null";
		
		RequestVo rvo = RequestServcieImpl.getRequest(map);
		
		if (rvo != null) {
			result = "fail";
		}
		else {
			result = "null";
		}
		return result;
	}
	// 댓글쓰기
	@RequestMapping(value="/req/write", method=RequestMethod.POST)
	public String requestWrite(@ModelAttribute RequestVo vo) {
		boolean success = false;
		try {
			success = RequestServcieImpl.request(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/recruit/write";
		}
		if (success) {
			return "redirect:/recruit/detail?no="+vo.getNo();
		} else {
			return "redirect:/";
		}
	}
	// 팀모집글 수정페이지로 이동
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(
			@RequestParam(value="userNo",required=false) Long userNo,
			@RequestParam(value="no",required=false) Long no,
			@RequestParam(value="starting",required=false) String starting,
			Model model) {
		System.out.println(starting);
		RecruitVo vo = RecruitServiceImpl.getDetail(no);
		if (userNo != null) {
			authUser=UserServiceImpl.getUserIdName(userNo);
		}
		model.addAttribute("vo", vo);
			
		if(starting != null && starting.equals("mypage")) {
			model.addAttribute("starting", starting);
		}
			
		return "recruit/modify";
	}
	// 자신이 쓴 팀모집글 수정
	@RequestMapping(value="/modify")
	public String update(@RequestParam(value="starting",required=false) String starting,
			@ModelAttribute RecruitVo vo) {
		boolean success = false;
		try {
			success = RecruitServiceImpl.update(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		if (success) {
			if(starting != null && starting.equals("mypage")) {
				return "redirect:/users/myboards?userNo="+authUser.getUserNo();
			} else {
				return "redirect:/recruit/list";
			}
		} else {
			return "redirect:/recruit/detail?no="+vo.getNo();
		}
	}
	// 자신이 쓴 팀모집글 삭제
	@RequestMapping(value="/delete")
	public String delete(@RequestParam(value="userNo",required=false) Long userNo,
			@RequestParam(value="no",required=false) Long no,
			@RequestParam(value="starting",required=false) String starting) {
		if (userNo != null) {
			authUser=UserServiceImpl.getUserIdName(userNo);
		}
		boolean success = false;
		try {
			success = RecruitServiceImpl.delete(no);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		if (success) {
			if (starting != null && starting.equals("mypage")) {
				return "redirect:/users/myboards?userNo="+authUser.getUserNo();
			} else {
				return "redirect:/recruit/list";
			}
		} else {
			return "redirect:/";
		}
	}
	// 댓글 수정페이지로 이동
	@RequestMapping(value="/req/modify/{reqNo}", method=RequestMethod.GET)
	public String reqModify(@PathVariable("reqNo") Long reqNo, Model model) {
		RequestVo vo = RequestServcieImpl.getContent(reqNo);
		model.addAttribute("vo", vo);
		return "recruit/reqModify";
	}
	// 자신이 쓴 댓글 수정
	@RequestMapping(value="/req/modify")
	public String reqUpdate(@ModelAttribute RequestVo vo) {
		boolean success = false;
		try {
			success = RequestServcieImpl.update(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		if (success) {
			return "redirect:/recruit/detail?no="+vo.getNo();
		} else {
			return "redirect:/recruit/list";
		}
	}
	// 자신이 쓴 댓글 삭제
	@RequestMapping(value="/req/delete/{reqNo}")
	public String reqDelete(@PathVariable("reqNo") Long reqNo) {
		RequestVo vo = RequestServcieImpl.getContent(reqNo);
		boolean success = false;
		try {
			success = RequestServcieImpl.delete(reqNo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		if (success) {
			return "redirect:/recruit/detail?no="+vo.getNo();
		} else {
			return "redirect:/recruit/list";
		}
	}
	// 내가 쓴 팀원모집글에 댓글을 단 사람들 중 같이 팀을 하고 싶은 사람들을 선택한 뒤 '팀만들기' 버튼을 누르면
	// 확인페이지로 이동
	@RequestMapping(value="/confirm")
	public String confirm(HttpServletRequest request, Model model) {
		// 'detail.jsp'에서 name = userSelect 인 체크박스들 중 checked 인 값만 가져와서 저장
		// String 형태로 넘어옴
		String[] userNos = request.getParameterValues("userSelect");
		
		// Long 타입으로 변환하여 리스트에 다시 저장
		List<Long> nos = new ArrayList<Long>();  // userNo 배열 
		// 해당 userNo을 가진 user정보를 불러와서 저장
		List<UserVo> vos = new ArrayList<UserVo>(); //userVo 배열
		
		for (String s: userNos) {
			nos.add(Long.parseLong(s));
		}
		for (Long l: nos) {
			UserVo vo = UserServiceImpl.getUserIdName(l);
			vos.add(vo);
		}
		model.addAttribute("nlist", nos);
		model.addAttribute("vlist", vos);
		
		return "recruit/confirm";
	}
	// 팀이름 중복체크
	@ResponseBody
	@RequestMapping(value="/name/check", method=RequestMethod.POST)
	public String nameCheck(@RequestParam(value="teamName",required=true,defaultValue="") String teamName) {
		String result = "null";
		Long no = teamServiceImpl.getNo(teamName);
		Long nullValue = null;
		
		if (no != nullValue) {
			result = "fail";
		}
		return result;
	}
	// 팀을 생성할 수 있는지 확인
	@RequestMapping(value="/team/check", method=RequestMethod.POST)
	public String teamCheck(HttpServletRequest request,
			HttpSession session) {
		// String 형태로 넘어옴
		String[] userNos = request.getParameterValues("userNo");
		String teamName = request.getParameter("teamName");
		List<Long> nos = new ArrayList<Long>();
		Long nullValue = null;
		
		for (String s: userNos) {
			nos.add(Long.parseLong(s));
		}
		for (Long userNo: nos) {
			UserVo vo = UserServiceImpl.getUserIdName(userNo);
			
			if (vo.getTeamNo() != nullValue) {
				return "recruit/teamfail";
			}
		}
		UserVo vo = UserServiceImpl.getUserIdName(nos.get(0));
		
		session.setAttribute("authUserId", vo.getUserId());
		session.setAttribute("userNos", nos);
		session.setAttribute("teamName", teamName);
		session.setAttribute("authUser", vo);
//		authUser=UserServiceImpl.getUser(userId);
//		session.setAttribute("authUser",authUser);
		return "redirect:/team/complete";
	}
}