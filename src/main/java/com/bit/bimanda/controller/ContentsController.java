package com.bit.bimanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.bimanda.crawlhandler.Crawlkeyword;
import com.bit.bimanda.service.KeywordService;
import com.bit.bimanda.service.KeywordServiceImpl;
import com.bit.bimanda.service.UserService;
import com.bit.bimanda.vo.CafeVo;
import com.bit.bimanda.vo.DaumVo;
import com.bit.bimanda.vo.KeywordVo;
import com.bit.bimanda.vo.NblogVo;
import com.bit.bimanda.vo.UserVo;

@Controller
@RequestMapping("/contents")
public class ContentsController {
   String keyName;
   UserVo authUser; //회원정보 수정후 메인들어갔을때 세션 새로설정해서 유지하게하기위해  일부러 전역변수로 설정해놨음(수정 후 메인 돌아가면 기존정보로 떴었음 수정 후정보말고)  
   Long GteamNo;
   String pleaseStay;
   List<KeywordVo> klist;
   List<KeywordVo> teamklist;
   @Autowired
   UserService UserServiceImpl;
   @Autowired
   KeywordService KeywordServiceImpl;
   
   @RequestMapping(value="/contentsmain", method=RequestMethod.GET) //커뮤니티 메인화화면 
   public String contentsmain(@ModelAttribute KeywordVo vo,
         @RequestParam(value="userId",required=false) String userId,
         @RequestParam(value="keyName",required=false) String keyName,
         @RequestParam(value="teamNo",required=false) Long teamNo,
         @RequestParam(value="stay",required=false) String stay,
         @RequestParam(value="kvoteamNo",required=false) Long kvoteamNo,Model model) {
	   authUser=UserServiceImpl.getUser(userId);
	   if (authUser != null) {
	      
	      GteamNo=authUser.getTeamNo();
	      if(stay!=null) {
	         pleaseStay=stay;
	         if(stay.equals("person")) {
	            pleaseStay=stay;
	            model.addAttribute("pleaseStay",pleaseStay);
	         }else if(stay.equals("team")) {
	            pleaseStay=stay;
	            model.addAttribute("pleaseStay",pleaseStay);
	         }
	      }
	      if(stay==null) {
	         pleaseStay="person";
	         model.addAttribute("pleaseStay", pleaseStay);
	      }
	      System.out.println("나오는지확인2"+vo.getUserNo()+vo.getKeyName()+vo.getTeamNo()+kvoteamNo+teamNo+pleaseStay);
	      System.out.println("컨텐츠메인"+userId+"  "+authUser.toString());
	      Crawlkeyword crawlkeyword=new Crawlkeyword(); // 카페랑 블로그 넣는 함수들 넣어놨음 
	      keyName=vo.getKeyName();
	      if(authUser != null) {
	         klist=KeywordServiceImpl.getList(authUser.getUserNo());
	         model.addAttribute("klist", klist);
	         if(authUser.getTeamNo()!= null) {
	            teamklist=KeywordServiceImpl.getTeamKeyList(authUser.getTeamNo());
	            model.addAttribute("teamklist",teamklist);
	         }
	         
	         if(keyName!=null) {    //키워드리스트에서 키워드 클릭했을때 키워드에 해당되는 카페크롤링내용 읽어서 모델로 보내기  
	            System.out.println("키리스트 불러와져라제발"+keyName);
	            keyName=vo.getKeyName();
	            List<List<CafeVo>> cafelist=crawlkeyword.readkeyword(authUser.getUserId(),keyName,50,teamNo);
	            List<List<NblogVo>> nbloglist=crawlkeyword.readNblogKeyword(authUser.getUserId(),keyName,50,teamNo);
	            List<List<DaumVo>> daumcafelist=crawlkeyword.readDaumKeyword(keyName,"cafe");
	            List<List<DaumVo>> daumbloglist=crawlkeyword.readDaumKeyword(keyName,"blog");
	            
	            model.addAttribute("selectKeyName",keyName);
	            System.out.println("골라졌냐 keyName"+keyName);
	            model.addAttribute("cafelist",cafelist);
	            model.addAttribute("nbloglist",nbloglist);
	            model.addAttribute("daumcafelist",daumcafelist);
	            model.addAttribute("daumbloglist",daumbloglist);
	            
	      
	         }
	         return "contents/contentsmain";
	      }else {
	         return "users/mypagefail";
	      }
	   }else {
	   	  return "contents/loginfirst";
	   }
      
   }
   @RequestMapping(value="/insertKeyword", method= {RequestMethod.POST,RequestMethod.GET})
   public String insertKeyword(@ModelAttribute KeywordVo vo,Model model) {
      System.out.println("인서트키워드쪽"+pleaseStay);
      GteamNo=authUser.getTeamNo();
      System.out.println(GteamNo);
      System.out.println("keywordvo.getUserNo"+vo.getUserNo());
      System.out.println("keywordvo.getKeyName"+vo.getKeyName());
      System.out.println("keywordvo.getTeamNo"+vo.getTeamNo());
      
      boolean success=false;
      if(GteamNo!=null) {
         success=KeywordServiceImpl.insertTeamKeyword(vo); //팀키워드 삽입  
         model.addAttribute("pleaseStay",pleaseStay);
         return "redirect:/contents/contentsmain?userId="+authUser.getUserId()+"&kvoteamNo="+vo.getTeamNo()+"&stay=team";
      }
      else if(authUser.getUserNo()==vo.getUserNo() ) {
         success=KeywordServiceImpl.insert(vo);
         model.addAttribute("pleaseStay",pleaseStay);
         return "redirect:/contents/contentsmain?userId="+authUser.getUserId()+"&kvoteamNo="+vo.getTeamNo()+"&stay=person";
      }else 
         return "contents/contentsmain";
   }
   
   @RequestMapping(value="/deleteKeyword", method= {RequestMethod.POST,RequestMethod.GET})
   public String deleteKeyword(@ModelAttribute KeywordVo vo,Model model) {
      System.out.println(vo.toString());
      System.out.println("딜리트키워드쪽"+pleaseStay);
      GteamNo=authUser.getTeamNo();
      System.out.println("GteamNo"+GteamNo);
      System.out.println("voteamNo"+vo.getTeamNo());
      System.out.println("vo.getUser"+vo.getUserNo());
      
      boolean success=false;
      if(GteamNo != null && GteamNo==vo.getTeamNo()) {
         success=KeywordServiceImpl.deleteTeamKeyword(vo);
         model.addAttribute("pleaseStay",pleaseStay);
         return "redirect:/contents/contentsmain?userId="+authUser.getUserId()+"&kvoteamNo="+vo.getTeamNo()+"&stay=team";
      }
      else if(authUser.getUserNo()==vo.getUserNo() ) {
         success=KeywordServiceImpl.delete(vo);
         model.addAttribute("pleaseStay",pleaseStay);
         return "redirect:/contents/contentsmain?userId="+authUser.getUserId()+"&kvoteamNo="+vo.getTeamNo()+"&stay=person";
      }else 
         return "contents/contentsmain";
   }
   @ResponseBody
   @RequestMapping(value="/stay", method= {RequestMethod.POST,RequestMethod.GET})
   public String stay(@RequestParam(value="userId",required=false) String userId,
         @RequestParam(value="stay",required=false) String stay,Model model) {
      System.out.println(stay); 
      /*   
      if(stay!=null) {
         if(stay.equals("person")) {
            pleaseStay=stay;
            model.addAttribute("pleaseStay",pleaseStay);
         }else if(stay.equals("team")) {
            pleaseStay=stay;
            model.addAttribute("pleaseStay",pleaseStay);
         }
      }
      */
      
      return "responsebody"+stay;
      
   }
}
   