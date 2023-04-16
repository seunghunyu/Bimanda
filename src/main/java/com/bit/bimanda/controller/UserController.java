package com.bit.bimanda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.bimanda.service.CategoryService;
import com.bit.bimanda.service.KeywordService;
import com.bit.bimanda.service.TeamService;
import com.bit.bimanda.service.UserService;
import com.bit.bimanda.vo.BoardVo;
import com.bit.bimanda.vo.CommentsVo;
import com.bit.bimanda.vo.KeywordVo;
import com.bit.bimanda.vo.LocationVo;
import com.bit.bimanda.vo.MentorRequestVo;
import com.bit.bimanda.vo.MentorVo;
import com.bit.bimanda.vo.RecruitVo;
import com.bit.bimanda.vo.RequestVo;
import com.bit.bimanda.vo.ReviewBoardVo;
import com.bit.bimanda.vo.UserVo;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


@Controller
@RequestMapping("/users")
public class UserController {
	String AuthNumber;
	UserVo authUser; //회원정보 수정후 메인들어갔을때 세션 새로설정해서 유지하게하기위해  일부러 전역변수로 설정해놨음(수정 후 메인 돌아가면 기존정보로 떴었음 수정 후정보말고) 
	@Autowired
	UserService UserServiceImpl;
	@Autowired 
	CategoryService CategoryServiceImpl;
	@Autowired
	KeywordService KeywordServiceImpl;
	@Autowired
	TeamService teamServiceImpl;
	
	
	@RequestMapping(value="/find", method=RequestMethod.GET) // 메인 페이지에서 회원가입 눌렀을 때 joinform.jsp로 보내줌
	public String find(@ModelAttribute UserVo vo) {
		return "users/find";
	}
	@RequestMapping(value="/memberupdate")
	public String memberupdate() {
		return "users/mypage";	// 마이페이지 메인이랑 memberupdate랑 같은 내용이라 mypage로 보냄
	}
	@RequestMapping(value="/withdraw")
	public String withdraw() {
		return "users/withdraw";
	}
	@RequestMapping(value="/memberupdateaction", method=RequestMethod.POST)
	public String memberupdateaction(@RequestParam(value="userId",required=true,defaultValue="") String userId,
									  @RequestParam(value="userPassword",required=true,defaultValue="") String userPassword,
									   @RequestParam(value="userPhone",required=true,defaultValue="") String userPhone,
									   HttpSession session,Model model) {
		int success=0;
		success=UserServiceImpl.update(userId, userPassword,userPhone);
		if(success==1) {
			authUser=UserServiceImpl.getUser(userId);
			session.setAttribute("authUser",authUser);
			return "users/memberupdatesuccess";
		}else {
			return "users/memberupdate";
		}
	}
	
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage(@ModelAttribute KeywordVo vo,Model model) {
		
//		System.out.println(klist.get(0));
		
		if(authUser != null) {
			List<KeywordVo> klist=KeywordServiceImpl.getList(authUser.getUserNo());
			model.addAttribute("klist", klist);
			List<LocationVo> loList=UserServiceImpl.selectLocation();
			model.addAttribute("loList", loList);
			return "users/mypage";
		}else {
			return "users/mypagefail";
		}
		
	}
	
	@RequestMapping(value="/mypageaction", method=RequestMethod.POST)
	public String mypageaction(@ModelAttribute KeywordVo vo,Model model) {
		boolean success=KeywordServiceImpl.insert(vo);
		return "redirect:/users/mypage";
	}
	@RequestMapping(value="/withdrawaction", method=RequestMethod.POST)
	public String withdrawaction(@RequestParam(value="userId",required=true,defaultValue="") String userId,
							@RequestParam(value="userPassword",required=true,defaultValue="") String userPassword,
							HttpSession session) {
		System.out.println(userId+" "+userPassword);
		int success=0;
		System.out.println("success 1:"+success);
		success=UserServiceImpl.delete(userId, userPassword);
		System.out.println("success 2:"+success);
		if(success==1) { //삭제 성공시 authUser에 해당하는 session 삭제후 삭제 성공 페이지로 이동 
			session.removeAttribute("authUser");
			session.invalidate();  
			return "users/withdrawsuccess";              
		}else {
			return "users/withdraw";//삭제 실패시 해당 페이지에 그대로 
		}
	}
	@ResponseBody
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public String auth(@RequestParam(value="authNumber",required=true,defaultValue="") String authNumber,
						@RequestParam(value="userName",required=true,defaultValue="") String userName,
			            @RequestParam(value="userPhone",required=true,defaultValue="") String userPhone) {
		System.out.println("최종비교"+userName+" "+userPhone+"//////"+AuthNumber+"/////"+authNumber);
    	boolean success=false;
		String res=null;
		if(AuthNumber.equals(authNumber)) {
    		UserVo vo = UserServiceImpl.getAuthUser(userName,userPhone);
    		res="ID :"+" "+vo.getUserId()+"  PASSWORD :"+vo.getUserPassword();
    		System.out.println(res);
    		success=true;
		}
		
		if(success==true) {
			return res;
		}else {
			return "fail";
		}
		
		
	}
    @ResponseBody
	@RequestMapping(value = "/sendSms", method={RequestMethod.GET, RequestMethod.POST})
	public Object sendSms(HttpServletRequest request,Model model,
			              @RequestParam(value="userName",required=true,defaultValue="") String userName,
			              @RequestParam(value="userPhone",required=true,defaultValue="") String userPhone) throws Exception {
    	String api_key = "NCSUA4GKGEXFNO6L";
        String api_secret = "2BRAUXPU9L8QS862MHUECLKTLLR4CRHG";
        Message coolsms = new Message(api_key, api_secret);
    	System.out.println(userName+" "+userPhone);
    	UserVo vo = UserServiceImpl.getAuthUser(userName,userPhone);
    	System.out.println(vo.toString());
    	Map<String, Object> map = new HashMap<>();
		map.put("result","success");
		map.put("data",vo != null ? "exists": "not exists");
		
		System.out.println(map);
        
		int num=randomRange(99,1000); // 인증 숫자 3자리 랜덤
        String phoneNum=userPhone;
        String authNum=Integer.toString(num); // 랜덤값 돌린 숫자 문자열 변환 
		AuthNumber=authNum;
		map.put("authNum",authNum);
		System.out.println("지역변수 authNum"+" : "+authNum);
        System.out.println("전역변수 authNumber"+" : "+AuthNumber);
        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNum);  //find 페이지에서 입력받은 번호로 메시지보내기 
        params.put("from", "01062051495");
        params.put("type", "SMS");
        params.put("text", authNum);//세자리수 랜덤값 
        params.put("app_version", "test app 1.2"); // application name and version

        try {
          JSONObject obj = (JSONObject) coolsms.send(params);
          System.out.println(obj.toString());
        } catch (CoolsmsException e) {
          System.out.println(e.getMessage());
          System.out.println(e.getCode());
        }
        
        model.addAttribute("authNumber", authNum);
        if(vo!=null)
        	return authNum;
        else 
        	return "fail"; 
	}
    public static int randomRange(int n1, int n2) { // n1~n2까지 범위의 랜덤숫자 뽑아내기(세자리수 랜덤숫자뽑아내기)
	    return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}
	@RequestMapping(value="/join",method= {RequestMethod.POST,RequestMethod.GET}) // 메인 페이지에서 회원가입 눌렀을 때 joinform.jsp로 보내줌
	public String join(@ModelAttribute UserVo vo,Model model) {
		
		List<LocationVo> loList=UserServiceImpl.selectLocation();
		System.out.println(loList);
		model.addAttribute("loList", loList);
		
		return "users/joinform";
	}
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String login() {
		
		return "users/loginform";
	}
	
	@RequestMapping(value="/joinaction",method=RequestMethod.POST) // joinform.jsp에서 가입 form을 전송했을 때 처리
	public String joinAction(@ModelAttribute UserVo vo) {
		boolean success = false;
		System.out.println(vo.toString());
		try {
			success = UserServiceImpl.join(vo);
		}catch(Exception e) {
			System.err.println("ERROR");
			e.printStackTrace();
			return "redirect:/users/join";
		}
		System.out.println("가입신청 결과: "+success);
		if (success) {
			vo = UserServiceImpl.getUser(vo.getUserId());
			return "redirect:/users/joinsuccess";	// 회원가입이 성공 시 joinsuccess.jsp로 리다이렉트
		}else {
			return "redirect:/users/join";	// 회원가입 실패 시 joinform.jsp로 리다이렉트
		}
	}
	@RequestMapping("/joinsuccess")	// 가입 성공 시 joinsuccess.jsp로 리다이렉트
	public String joinSuccess() {
		return "users/joinsuccess";
	}
	
	@RequestMapping(value="/login",method= RequestMethod.POST)	// 메인 Page에서 아이디, 비밀번호 form 보내면 처리
	public Object loginAction(
			@RequestParam(value="userId", required=false) String userId,
			@RequestParam(value="userPassword", required=false) String userPassword,
			HttpSession session
			) {
		authUser = UserServiceImpl.getUser(userId, userPassword);
		Map<String, Object> map = new HashMap<>();
		map.put("result","success");
		map.put("data",authUser != null ? "exists": "not exists");
		if (authUser != null) {
			session.setAttribute("authUser", authUser);	// 로그인 성공 시 세션에 정보를 담아 홈으로 리다이렉트
			return "redirect:/";	
		}else {
			return "redirect:/users/login";	// 로그인 실패 시 다시 로그인 페이지로 리다이렉트
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)	// 로그인 한 상태에서 로그아웃을 누르면 세션에 담은 정보들을 제거, 무효화하고 홈으로 리다이렉트 
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";	
	}
	
	
	@ResponseBody
	@RequestMapping(value="/idcheck", method=RequestMethod.GET)
	public Object exists(@RequestParam(value="userId",required=true,defaultValue="") String userId) {	// ID 중복확인
		UserVo vo = UserServiceImpl.getUser(userId);
		Map<String, Object> map = new HashMap<>();
		map.put("result","success");
		map.put("data",vo != null ? "exists": "not exists");
		System.out.println(map);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/checkform", method=RequestMethod.GET)
	public Object exist(@RequestParam(value="userId",required=true,defaultValue="") String userId,
			@RequestParam(value="userPassword",required=true,defaultValue="") String userPassword) {	// 로그인 할 때 폼 검증
		System.out.println(userId + userPassword);
		UserVo vo = UserServiceImpl.getUser(userId,userPassword);
		System.out.println(vo);
		Map<String, Object> map = new HashMap<>();
		map.put("result","success");
		map.put("data",vo != null ? "exists": "not exists");
		return map;
	}
	
	// 멘토'되기' 신청 누르면 신청폼으로 이동
		@RequestMapping(value="/mentor", method=RequestMethod.GET)
		public String mentorform() {
			return "users/mentor";
		}
		// 멘토 신청글 작성
		@RequestMapping(value="/mentor", method=RequestMethod.POST)
		public String mentorInsert(@ModelAttribute MentorVo mentorVo) {
			boolean success = false;
			System.out.println(mentorVo.toString());
			try {
				success = UserServiceImpl.insertMentor(mentorVo);
			}catch(Exception e) {
				System.err.println("ERROR");
				e.printStackTrace();
				return "redirect:/users/mypage";
			}
			if (success) {
				System.out.println("성공");
				return "redirect:/users/mypage";
			}else {
				System.out.println("실패");
				return "redirect:/users/mypage";
			}
		}
		// 관리자가 '멘토 신청 관리' 누르면 멘토'되기' 신청한 사람들 목록으로 이동
		@RequestMapping(value="/mentorconfirm", method=RequestMethod.GET)
		public String mentorconfirm(Model model) {
			List<MentorVo> mlist = UserServiceImpl.getMentorList();
			model.addAttribute("mlist", mlist);
			
			return "users/mentorconfirm";
		}
		// 멘토신청 승인
		@RequestMapping(value="/confirm/{userNo}")
		public String mentorconfirm(@PathVariable("userNo") Long userNo) {
			boolean success = false;
			
			try {
				success = UserServiceImpl.updateMentor(userNo);
			} catch(Exception e) {
				System.err.println("ERROR");
				e.printStackTrace();
				return "redirect:/users/mentorconfirm";
			}
			if (success) {
				System.out.println("성공");
				UserServiceImpl.confirmMentor(userNo);
				
				return "redirect:/users/mentorconfirm";
			}else {
				System.out.println("실패");
				return "redirect:/users/mypage";
			}
		}
		// 멘토신청 거절
		@RequestMapping(value="/deletementor/{userNo}")
		public String deletementor(@PathVariable("userNo") Long userNo) {
			boolean success = false;
			
			try {
				success = UserServiceImpl.deleteMentor(userNo);
			} catch (Exception e) {
				System.err.println("ERROR");
				e.printStackTrace();
				return "redirect:/users/mentorconfirm";
			}
			if (success) {
				System.out.println("멘토 삭제 성공");
				return "redirect:/users/mentorconfirm";
			} else {
				System.out.println("멘토 삭제 실패");
				return "redirect:/users/mentorconfirm";
			}
		}
		// 멘토 자신에게 멘토 해달라고 신청한 멘티들 목록창으로 이동
		@RequestMapping(value="/menteechoose/{userNo}")
		public String menteechoose(@PathVariable("userNo") Long userNo, 
				Model model) {
			System.out.println("유저넘: "+ userNo);
			List<MentorRequestVo> mrlist = UserServiceImpl.getMenteeList(userNo);
			model.addAttribute("mrlist", mrlist);
			for (int i=0; i<mrlist.size(); i++) {
				System.out.println("멘티들: "+mrlist.get(i));
			}
			return "users/menteeReqList";
		}
		// 멘토멘티 매칭 승인
		@RequestMapping(value="/choosementee/{teamNo}")
		public String choosementee(@PathVariable("teamNo") Long teamNo,
				HttpSession session) { 
			boolean success1;
			boolean success2;
			boolean success3;
			Map<String, Long> map = new HashMap<String, Long>();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			System.out.println("asdasd"+teamNo+authUser.getUserNo());
			map.put("teamNo", teamNo);
			map.put("userNo", authUser.getUserNo());
			success1=teamServiceImpl.insertMem(map);
			success2=teamServiceImpl.updateMentor(map);
			success3=UserServiceImpl.deleteMentorReq(map);
			System.out.println("멘토멘티매칭승인ㅕup"+ success1+success2+success3);
			
			return "redirect:/users/menteechoose/"+authUser.getUserNo();
		}
		// 멘토멘티 매칭 거절
		@RequestMapping(value="/deletementee/{teamNo}")
		public String deletementee(@PathVariable("teamNo") Long teamNo,
				HttpSession session) {
			Map<String, Long> map = new HashMap<String, Long>();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			map.put("teamNo", teamNo);
			map.put("userNo", authUser.getUserNo());
			UserServiceImpl.deleteMentorReq(map);
			
			return "redirect:/users/menteechoose/"+authUser.getUserNo();
		}
		
		@RequestMapping("/leaveTeam")	// 팀 탈퇴 페이지
		public String leaveTeam() {
			return "users/leaveTeam";
		}
		
		@RequestMapping(value="/leaveTeamaction", method=RequestMethod.POST)
		public String leaveTeamaction(@RequestParam(value="userId",required=true,defaultValue="") String userId,
								@RequestParam(value="userPassword",required=true,defaultValue="") String userPassword,
								@RequestParam(value="userNo",required=true,defaultValue="") Long userNo,
								@RequestParam(value="teamNo",required=true,defaultValue="") Long teamNo,
								HttpSession session) {
			System.out.println(userId+" "+userPassword);
			int success=0;
			boolean success2 = false;
			System.out.println("success 1:"+success);
			System.out.println("success 2:"+success2);
			Map<String, Long> map = new HashMap<String, Long>();
			map.put("userNo", userNo);
			map.put("teamNo", teamNo);
			System.out.println("맵:: "+map);
			success=UserServiceImpl.leaveTeam(userId, userPassword);
			success2=teamServiceImpl.leaveTeam(map);
			System.out.println("success 1:"+success);
			System.out.println("success 2:"+success2);
			if(success==1 && success2==true) { //삭제 성공시 authUser에 해당하는 session 삭제후 삭제 성공 페이지로 이동 
				authUser=UserServiceImpl.getUser(userId);
				session.setAttribute("authUser",authUser);
				return "redirect:/users/leaveTeamsuccess";              
			}else {
				return "users/leaveTeam";//삭제 실패시 해당 페이지에 그대로 
			}
		}
		
		@RequestMapping("/leaveTeamsuccess")	// 가입 성공 시 leaveTeamsuccess.jsp로 리다이렉트
		public String leaveTeamSuccess() {
//			authUser=UserServiceImpl.getUser(userId);
//			session.setAttribute("authUser",authUser);
			return "users/leaveTeamsuccess";
		}
		
		// 내가 쓴 게시물 확인 페이지로 이동
		@RequestMapping(value="/myboards", method=RequestMethod.GET)
		public String myboardspage(Model model, 
				@RequestParam(value="userNo",required=true,defaultValue="") Long userNo) {
			List<BoardVo> blist = UserServiceImpl.getmyboard(userNo);
			List<ReviewBoardVo> rvlist = UserServiceImpl.getmyreviews(userNo);
			List<RecruitVo> rlist = UserServiceImpl.getmyrecruit(userNo);
			List<MentorVo> mlist = UserServiceImpl.getmymentor(userNo);
			
			model.addAttribute("blist", blist);
			model.addAttribute("rvlist", rvlist);
			model.addAttribute("rlist", rlist);
			model.addAttribute("mlist", mlist);
			
			return "users/myboards";
		}
		// 내가 쓴 댓글 확인 페이지로 이동
		@RequestMapping(value="/mycomments", method=RequestMethod.GET)
		public String mycommentspage(Model model,
				@RequestParam(value="userNo",required=false) Long userNo) {
			List<CommentsVo> clist = UserServiceImpl.getmycomments(userNo);
			List<RequestVo> rqlist = UserServiceImpl.getmyrequest(userNo);
			model.addAttribute("clist", clist);
			model.addAttribute("rqlist", rqlist);
			
			return "users/mycomments";
		}
}
