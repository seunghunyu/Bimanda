package com.bit.bimanda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.bimanda.service.BoardService;
import com.bit.bimanda.service.CategoryService;
import com.bit.bimanda.service.CommentsService;
import com.bit.bimanda.service.UserService;
import com.bit.bimanda.vo.BoardVo;
import com.bit.bimanda.vo.CategoryVo;
import com.bit.bimanda.vo.CommentsVo;
import com.bit.bimanda.vo.ReviewBoardVo;
import com.bit.bimanda.vo.UserVo;

@Controller
@RequestMapping("/community")
public class CommunityController {
	UserVo authUser;
	@Autowired
	UserService UserServiceImpl;
	@Autowired
	BoardService BoardServiceImpl;
	@Autowired
	CommentsService CommentsServiceImpl;
	@Autowired
	CategoryService CategoryServiceImpl;
	String hitCount="false"; //게시판 내용 리다이렉트 될때 카운팅 여부 변수 
	
	@RequestMapping(value="/communitymain") //커뮤니티 메인화화면 
	public String communitymain() {
		return "community/communitymain";
	}
	@RequestMapping(value="jisikwrite")//지식게시판 글쓰기화면 
	public String jisikwrite() {
		return "community/jisikwrite";
	}
	@RequestMapping(value="ideawrite")//아이디어 게시판 글쓰기화면
	public String ideawrite() {
		return "community/ideawrite";
	}
	@RequestMapping(value="freewrite")//자유게시판 글쓰기 화면 
	public String freewrite() {
		return "community/freewrite";
	}
	@RequestMapping(value="noticewrite")//공지사항 글쓰기 화면 
	public String noticewrite() {
		return "community/noticewrite";
	}
	@RequestMapping(value="reviewwrite") //공모전후기 화면 글쓰기 화면 
	public String reviewboardwrite(Model model) {
		List<CategoryVo> cList = CategoryServiceImpl.getList();  //공모전후기쓸때 셀렉트 태그 쓰기위해서 카테고리를 모델로 넘겼음 
		model.addAttribute("cList",cList);
		return "community/reviewwrite";
	}
	@RequestMapping(value="/jisikboard")//질문게시판  메인 
	public String jisikboard(Model model) {
		String boardCategory="질문게시판";
		List<BoardVo> list = BoardServiceImpl.getList(boardCategory);
		hitCount="true";
		List numbers = new ArrayList();
		for (int i=1; i<=10; i++) {
			numbers.add(i);
		}
		model.addAttribute("pageNum", numbers);
	    model.addAttribute("list",list);
		return "community/jisikboard";
	}
	@RequestMapping(value="/writeaction" ,method= {RequestMethod.POST,RequestMethod.GET})  //글작성 버튼 클릭했을 시에 실행되는 메소드 
	public String writeaction(@ModelAttribute BoardVo Bvo, @ModelAttribute ReviewBoardVo rVo,
							Model model) {
		boolean success=false;
		
		if((Bvo!=null)&&(!Bvo.getBoardCategory().equals("공모전후기"))) { //공모전 후기가 아닌 경우
			success=BoardServiceImpl.insert(Bvo);
		} else if(rVo!=null){  // 공모전 후기 글 삽입 
			success=BoardServiceImpl.insert(rVo);
		}
		if((success=true)&&(Bvo.getBoardCategory().equals("질문게시판"))) {//각 boardCategory에 해당하는 페이지로 리턴  
			System.out.println("질문게시판 글 게시 성공");
			return "redirect:/community/jisikboard";
		}else if((success=true)&&(Bvo.getBoardCategory().equals("아이디어공유"))) {
			System.out.println("아이디어공유 글 게시 성공");
			return "redirect:/community/ideaboard";
		}else if((success=true)&&(Bvo.getBoardCategory().equals("자유게시판"))) {
			System.out.println("자유게시판 글 게시 성공");
			return "redirect:/community/freeboard";
		}else if((success=true)&&(Bvo.getBoardCategory().equals("공모전후기"))) {
			List<CategoryVo> clist = CategoryServiceImpl.getList();
			System.out.println("공모전후기 글 게시 성공");
			return "redirect:/community/reviewboard";
		}else if((success=true)&&(Bvo.getBoardCategory().equals("공지사항"))) {
			System.out.println("공지사항 글 게시 성공");
			return "redirect:/community/noticeboard";
		}
		else{
			return "home";
		}
	}
	@RequestMapping(value="/readreview", method= {RequestMethod.POST,RequestMethod.GET})	// 리뷰게시물을 읽을 때
	public String readReview(@RequestParam(value="boardNo",required=false) Long boardNo,
						@RequestParam(value="starting",required=false) String starting,
							   Model model) {
		ReviewBoardVo vo = BoardServiceImpl.getReviewBoard(boardNo);
		List<CommentsVo> clist=CommentsServiceImpl.getList(boardNo);
		if(hitCount.equals("true")) {
			BoardServiceImpl.reviewupdatehit(boardNo); //조회수 올리는것
		}
		hitCount="false";
		model.addAttribute("vo", vo);
		model.addAttribute("clist",clist);
		return "community/reviewcontent";
	}
	@RequestMapping(value="/readcontent",method= {RequestMethod.POST,RequestMethod.GET}) //게시물을 읽을 때 실행되는 메소드 
	public String readcontent(
			                  @RequestParam(value="boardNo",required=false) Long boardNo,
							  @RequestParam(value="starting",required=false) String starting,
							   Model model) {
		System.out.println(boardNo+starting);
		BoardVo bVo=BoardServiceImpl.getBoard(boardNo);
		
		List<CommentsVo> clist=CommentsServiceImpl.getList(boardNo);
		
		if(hitCount.equals("true")) {
			BoardServiceImpl.updatehit(boardNo); //조회수 올리는것
		}
		hitCount="false";
		
		model.addAttribute("clist",clist);
		model.addAttribute("starting",starting);
		
		if(bVo==null) {
			ReviewBoardVo rVo=BoardServiceImpl.getReviewBoard(boardNo);	
			System.out.println(rVo.toString());
			model.addAttribute("rVo",rVo);
			return "community/readreview";
			
		}else if(bVo!=null) {
			model.addAttribute("bVo",bVo);
		}
		if(bVo.getBoardCategory().equals("질문게시판")) {
			return "community/jisikcontent";
		}else if(bVo.getBoardCategory().equals("자유게시판")) {
			return "community/freecontent";
		}else if(bVo.getBoardCategory().equals("아이디어공유")) {
			return "community/ideacontent";
		}else if(bVo.getBoardCategory().equals("공지사항")) {
			return "community/noticecontent";
		}
		else {
			return "home";
		}
	}
	@RequestMapping(value="/commentswrite", method= {RequestMethod.POST,RequestMethod.GET})
	public String commentswrite(@RequestParam(value="boardNo",required=false) Long boardNo,
							   @RequestParam(value="userNo",required=false) Long userNo,
							   @RequestParam(value="commentContent",required=false) String commentContent,
							   Model model) {  //댓글 다는 메소드 
		
		List<CommentsVo> clist=CommentsServiceImpl.getList(boardNo);
		model.addAttribute("clist",clist);
		
		boolean success=false;
		if(commentContent!=null) {
			success=CommentsServiceImpl.insert(boardNo,userNo,commentContent);
			
			BoardVo bVo = BoardServiceImpl.getBoard(boardNo);
			ReviewBoardVo rVo = BoardServiceImpl.getReviewBoard(boardNo);
			
			if ((bVo!=null)&&(!bVo.getBoardCategory().equals("공모전후기"))) {
				return "redirect:/community/readcontent?boardNo="+boardNo;
			}
			else {
				return "redirect:/community/readreview?boardNo="+boardNo;
			}
		}else {
			return "community/communitymain";
		}
	}
	@RequestMapping(value="/noticeboard")  //공지사항 게시판 메인 
	public String noticeboard(Model model) {
		String boardCategory="공지사항";
		List<BoardVo> list = BoardServiceImpl.getList(boardCategory);
		hitCount="true";
	    model.addAttribute("list",list);
	    List numbers = new ArrayList();
		for (int i=1; i<=10; i++) {
			numbers.add(i);
		}
		model.addAttribute("pageNum", numbers);
	    System.out.println("공지사항 Category list:"+list);
		return "community/noticeboard";
	}
	@RequestMapping(value="/freeboard") //자유게시판 메인 
	public String freeboard(Model model) {
		String boardCategory="자유게시판";
		List<BoardVo> list = BoardServiceImpl.getList(boardCategory);
		hitCount="true";
		List numbers = new ArrayList();
		for (int i=1; i<=10; i++) {
			numbers.add(i);
		}
		model.addAttribute("pageNum", numbers);
	    model.addAttribute("list",list);
	    System.out.println("자유게시판 Category list:"+list);
		return "community/freeboard";
	}
	@RequestMapping(value="/ideaboard")//아이디어 게시판 메인 
	public String ideaboard(Model model) {
		String boardCategory="아이디어공유";
		List<BoardVo> list = BoardServiceImpl.getList(boardCategory);
		hitCount="true";
		List numbers = new ArrayList();
		for (int i=1; i<=10; i++) {
			numbers.add(i);
		}
		model.addAttribute("pageNum", numbers);
	    model.addAttribute("list",list);
	    System.out.println("아이디어방  Category list:"+list);
		return "community/ideaboard";
	}
	@RequestMapping(value="/reviewboard")//공모전 후기 게시판 메인  
	public String reviewboard(Model model) {
		String boardCategory="공모전후기";
		List<ReviewBoardVo> reviewList = BoardServiceImpl.getReviewList(boardCategory);
		List numbers = new ArrayList();
		for (int i=1; i<=10; i++) {
			numbers.add(i);
		}
		model.addAttribute("pageNum", numbers);
	    model.addAttribute("reviewList",reviewList);
	    System.out.println("공모전 후기 Category list:"+reviewList);
		
	    return "community/reviewboard";
	}
	// 커뮤니티글 수정페이지로 이동
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(
			@RequestParam(value="userNo",required=false) Long userNo,
			@RequestParam(value="boardNo",required=false) Long boardNo,
			@RequestParam(value="starting",required=false) String starting,
			Model model) {
		if (userNo != null) {
			authUser=UserServiceImpl.getUserIdName(userNo);
		}
		BoardVo bVo = BoardServiceImpl.getBoard(boardNo);
		ReviewBoardVo rVo = BoardServiceImpl.getReviewBoard(boardNo);
		
		if(starting != null && starting.equals("mypage")) {
			model.addAttribute("starting", starting);
		}
		if ((bVo!=null)&&(!bVo.getBoardCategory().equals("공모전후기"))) {
			model.addAttribute("bVo", bVo);
			return "community/modify";
		} else {
			model.addAttribute("rVo", rVo);
			return "community/reviewmodify";
		}
	}
	// 수정 액션
	@RequestMapping(value="/modify")
	public String modifyAction(@ModelAttribute BoardVo bVo,
			@ModelAttribute ReviewBoardVo rVo,
			@RequestParam(value="starting", required=false) String starting,
			Model model) {	
		
		boolean success = false;
		
		try {
			if ((bVo!=null)&&(!bVo.getBoardCategory().equals("공모전후기"))) {
				System.out.println("수정액션에서"+bVo);
				success = BoardServiceImpl.update(bVo);
				List<CommentsVo> clist=CommentsServiceImpl.getList(bVo.getBoardNo());
				model.addAttribute("bVo", bVo);
				model.addAttribute("clist", clist);
			} else {
				System.out.println("수정액션에서"+rVo);
				success = BoardServiceImpl.reviewupdate(rVo);
				List<CommentsVo> clist=CommentsServiceImpl.getList(rVo.getBoardNo());
				model.addAttribute("rVo", rVo);
				model.addAttribute("clist",clist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		if (success) {
			if(starting != null && starting.equals("mypage")) {
				return "redirect:/users/myboards?userNo="+authUser.getUserNo();
			}
			else {
				if((bVo!=null)&&(!bVo.getBoardCategory().equals("공모전후기"))){
					return "redirect:/community/readcontent?boardNo="+bVo.getBoardNo();
				} else {
					return "redirect:/community/readreview?boardNo="+rVo.getBoardNo();
				}
			}
		} else {
			return "community/communitymain";
		}
	}
	// 삭제
	@RequestMapping(value="/delete")
	public String delete(
			@RequestParam(value="userNo",required=false) Long userNo,
			@RequestParam(value="boardNo",required=false) Long boardNo,
			@RequestParam(value="starting",required=false) String starting, 
			Model model) {
		
		if (userNo != null) {
			authUser=UserServiceImpl.getUserIdName(userNo);
		}
		
		boolean success = false;
		BoardVo bVo = BoardServiceImpl.getBoard(boardNo);
		ReviewBoardVo rVo = BoardServiceImpl.getReviewBoard(boardNo);
		
		try {
			if ((bVo!=null)&&(!bVo.getBoardCategory().equals("공모전후기"))) {
				success = BoardServiceImpl.delete(boardNo);
				List<BoardVo> list = BoardServiceImpl.getList(bVo.getBoardCategory());
			    model.addAttribute("list",list);
			} else {
				success = BoardServiceImpl.reviewdelete(boardNo);
				List<BoardVo> list = BoardServiceImpl.getList(rVo.getBoardCategory());
			    model.addAttribute("list",list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		if (success) {
			if (starting != null && starting.equals("mypage")) {
				return "redirect:/users/myboards?userNo="+authUser.getUserNo();
			} else {
				if(bVo.getBoardCategory().equals("질문게시판")) {
					List numbers = new ArrayList();
					for (int i=1; i<=10; i++) {
						numbers.add(i);
					}
					model.addAttribute("pageNum", numbers);
					return "community/jisikboard";
				} else if(bVo.getBoardCategory().equals("자유게시판")) {
					List numbers = new ArrayList();
					for (int i=1; i<=10; i++) {
						numbers.add(i);
					}
					model.addAttribute("pageNum", numbers);
					return "community/freeboard";
				} else if(bVo.getBoardCategory().equals("아이디어공유")) {
					List numbers = new ArrayList();
					for (int i=1; i<=10; i++) {
						numbers.add(i);
					}
					model.addAttribute("pageNum", numbers);
					return "community/ideaboard";
				} else if(bVo.getBoardCategory().equals("공지사항")) {
					List numbers = new ArrayList();
					for (int i=1; i<=10; i++) {
						numbers.add(i);
					}
					model.addAttribute("pageNum", numbers);
					return "community/noticeboard";
				} else {
					List numbers = new ArrayList();
					for (int i=1; i<=10; i++) {
						numbers.add(i);
					}
					model.addAttribute("pageNum", numbers);
					return "community/reviewboard";
				}
			}
		} else {
			return "redirect:/";
		}
	}
	// 댓글 수정페이지로 이동
	@RequestMapping(value="/comment/modify/{commentNo}", method=RequestMethod.GET)
	public String reqModify(@PathVariable("commentNo") Long commentNo, Model model) {
		CommentsVo cVo = CommentsServiceImpl.getContent(commentNo);
		model.addAttribute("cVo", cVo);
		return "community/commentModify";
	}
	// 자신이 쓴 댓글 수정
	@RequestMapping(value="/comment/modify")
	public String reqUpdate(@ModelAttribute CommentsVo cVo, Model model) {
		boolean success = false;
		try {
			success = CommentsServiceImpl.update(cVo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		if (success) {
			BoardVo bVo=BoardServiceImpl.getBoard(cVo.getBoardNo());
			
			ReviewBoardVo rVo = BoardServiceImpl.getReviewBoard(cVo.getBoardNo());
			List<CommentsVo> clist=CommentsServiceImpl.getList(cVo.getBoardNo());
			
			model.addAttribute("clist",clist);
			
			if((bVo!=null)&&(!bVo.getBoardCategory().equals("공모전후기"))) {
				model.addAttribute("bVo", bVo);
				return "redirect:/community/readcontent?boardNo="+bVo.getBoardNo();
			} else {
				model.addAttribute("rVo", rVo);
				return "redirect:/community/readreview?boardNo="+rVo.getBoardNo();
			}
		} else {
			return "redirect:/";
		}
	}
	// 자신이 쓴 댓글 삭제
	@RequestMapping(value="/comment/delete/{commentNo}")
	public String reqDelete(@PathVariable("commentNo") Long commentNo,
			Model model) {
		boolean success = false;
		CommentsVo cVo = CommentsServiceImpl.getContent(commentNo);
		BoardVo bVo=BoardServiceImpl.getBoard(cVo.getBoardNo());
		ReviewBoardVo rVo = BoardServiceImpl.getReviewBoard(cVo.getBoardNo());
		try {
			success = CommentsServiceImpl.delete(commentNo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		if (success) {
			List<CommentsVo> clist=CommentsServiceImpl.getList(cVo.getBoardNo());
			model.addAttribute("clist",clist);
			
			if((bVo!=null)&&(!bVo.getBoardCategory().equals("공모전후기"))) {
				return "redirect:/community/readcontent?boardNo="+bVo.getBoardNo();
			} else {
				return "redirect:/community/readreview?boardNo="+rVo.getBoardNo();
			}
		} else {
			return "redirect:/";
		}
		
	}
	// 내가 쓴 댓글에서 댓글 내용 클릭 시 해당 글로 이동
	   @RequestMapping(value="/comment/findboard")
	   public String findboard(@RequestParam(value="boardNo",required=false) Long boardNo,
	         Model model) {
	      BoardVo bVo = BoardServiceImpl.getBoard(boardNo);
	      ReviewBoardVo rVo = BoardServiceImpl.getReviewBoard(boardNo);
	      
	      if((bVo!=null)&&(!bVo.getBoardCategory().equals("공모전후기"))){
	         return "redirect:/community/readcontent?boardNo="+bVo.getBoardNo();
	      } else {
	         return "redirect:/community/readreview?boardNo="+rVo.getBoardNo();
	      }
	   }
}
