package com.bit.bimanda.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bit.bimanda.File.FileValidator;
import com.bit.bimanda.service.KeywordService;
import com.bit.bimanda.service.ProjectService;
import com.bit.bimanda.service.TaskService;
import com.bit.bimanda.service.TeamService;
import com.bit.bimanda.service.Team_ChatService;
import com.bit.bimanda.service.UserService;
import com.bit.bimanda.vo.KeywordVo;
import com.bit.bimanda.vo.MentorVo;
import com.bit.bimanda.vo.ProjectVo;
import com.bit.bimanda.vo.TaskVo;
import com.bit.bimanda.vo.TeamKeywordVo;
import com.bit.bimanda.vo.TeamVo;
import com.bit.bimanda.vo.Team_ChatVo;
import com.bit.bimanda.vo.UploadFile;
import com.bit.bimanda.vo.UserVo;
import com.google.gson.JsonArray;

@Controller
@RequestMapping("/team")
public class TeamController {
   UserVo authUser;
   Long upTeamNo;
   @Autowired
    private FileValidator fileValidator;
   @Autowired
   TaskService taskServiceImpl;
   @Autowired
   ProjectService projectServiceImpl;
   @Autowired
   UserService UserServiceImpl;
   @Autowired
   Team_ChatService Team_ChatServiceImpl;
   @Autowired
   TeamService teamServiceImpl;
   @Autowired
   KeywordService keywordServiceImpl;
   
   // 팀원확인 페이지에서 팀명 입력 후 '팀만들기' 버튼을 누르면 팀이 생성됨
   @RequestMapping(value="/complete")
   public String complete(HttpSession session) {
      String teamName = (String)session.getAttribute("teamName");
      String authUserId = (String)session.getAttribute("authUserId");
      @SuppressWarnings("unchecked")
      List<Long> userNos = (List<Long>)session.getAttribute("userNos");
      Map<String, Long> map = new HashMap<String, Long>();
            
      boolean success = false;
      try {
         // 팀명을 넣어 팀테이블에 팀 추가
         success = teamServiceImpl.createTeam(teamName);
      } catch (Exception e) {
         e.printStackTrace();
         return "redirect:/";
      }
      if (success) { // 성공 시
         Long no = teamServiceImpl.getNo(teamName);
               
         for (Long userNo: userNos) {
            map.put("teamNo", no);
            map.put("userNo", userNo);
            // 넘어온 유저 리스트를 해당 팀원 테이블에 저장
            teamServiceImpl.insertMem(map);
            // user정보에서 teamNo을 해당 팀번호로 변경
            UserServiceImpl.updateTeam(map);
         }
         authUser = UserServiceImpl.getUser(authUserId);
            
         session.removeAttribute("authUserId");
         session.removeAttribute("teamName");
         session.removeAttribute("userNos");
//         session.removeAttribute("authUser");
//         UserVo authUser = (UserVo)session.getAttribute("authUser");
         session.setAttribute("authUser", authUser);
         return "recruit/teamsuccess";
      } else {
         return "redirect:/";
      }
   }
   @RequestMapping(value="/teammain")
   public String communitymain(HttpSession session,
         @RequestParam(value="projNo",required=false) Long projNo,
         @RequestParam(value="teamNo",required=false) Long teamNo,
         @ModelAttribute ProjectVo pVo, Model model) {
      if(teamNo!=null) {
         upTeamNo=teamNo;
         Map<String, Long> map = new HashMap<String, Long>();
         map.put("userNo", authUser.getUserNo());
         map.put("teamNo", teamNo);      
         UserServiceImpl.updateTeam(map); // 멘토의 팀넘버 업데이트  
      }
      
      int doneCount = 0;
      UserVo authUser = (UserVo)session.getAttribute("authUser");
      UserVo vo = (UserVo)session.getAttribute("authUser");
      if (authUser != null) {
         if (authUser.getUserNo() != vo.getUserNo()) {
            authUser = vo;
         }
      }else {
         authUser = vo;
      }
      
      if(authUser != null) {
         if(authUser.getTeamNo() != null) {
            // teamNo으로 teamName 받아오기
            String teamName = teamServiceImpl.getName(authUser.getTeamNo());
            
            // 팀 directory 경로
            String path;
            // 팀 directory 경로
            if(projNo==null) {

               path = "D:\\fileUpload\\"+teamName;
            }else {
               path = "D:\\fileUpload\\"+teamName+"\\"+Long.toString(projNo);
            }
            
              File directory = new File(path);
              
              if (!directory.exists()) {   // 팀 폴더가 없는 경우
                 try {
                    directory.mkdir();   // 폴더 생성
                    System.out.println("폴더가 생성되었습니다.");
                 } catch (Exception e) {
                    e.getStackTrace();
                 }
              } else {
                 System.out.println("이미 생성되어있는 폴더입니다.");
              }
              //특정 directory 내 파일 목록 가져오기
              File[] things = directory.listFiles();
              List<String> files = new ArrayList<>();

              for (File file : things) {
                 //파일이 directory 가 아닌 file 일때
                 if (file.isFile()) {
                    files.add(file.getName());
                 }
              }
              model.addAttribute("files", files);
              
              List<ProjectVo> pvolist = projectServiceImpl.selectList(authUser.getTeamNo());
            
            
            if (pvolist != null) { //프로젝트리스트에 값이있으면 
               model.addAttribute("pvolist",pvolist);
               if(projNo!= null) { // 프로젝트 선택했을때 
                  List<TaskVo> list = taskServiceImpl.getList(projNo);
                  model.addAttribute("list", list);
                  
                  for (TaskVo tVo: list) {
                     if (tVo.getTaskState() == 2) {
                        doneCount++;
                     }
                  }
                  ProjectVo pvo=projectServiceImpl.getProject(projNo);
                  model.addAttribute("doneCount", doneCount);
                  model.addAttribute("list",list);
                  model.addAttribute("pvo",pvo); //선택한 프로젝트의  vo 넘김 
               }else
                  System.out.println("프로젝트넘버 없음");
               
            }
            List<Team_ChatVo> chatlist=Team_ChatServiceImpl.getList(authUser.getTeamNo());
            model.addAttribute("chatlist", chatlist);
            List<UserVo> teamMemList = teamServiceImpl.getTeamMemList(authUser.getTeamNo());
            model.addAttribute("teamMemlist", teamMemList);
            System.out.println("팀리: "+teamMemList);
            System.out.println("ㅋㅋ: "+authUser.getUserNo());
            return "team/teammain";
         }
         else 
            return "team/teammainfalse"; // 팀이없을때 이동하는페이지
      }
      else
         return "team/loginfirst";
   }
   
   @RequestMapping(value="/upload", method= {RequestMethod.GET,RequestMethod.POST})
    public String submitUpload(
          @RequestParam(value="projNo",required=false) Long projNo,
            @ModelAttribute("uploadFile") UploadFile uploadFile,
            HttpSession session, BindingResult result) {
       System.out.println(projNo);
       UserVo vo = (UserVo) session.getAttribute("authUser");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        int i = 0;
        
        // 에러 검사 : 업로드 파일 유무
        MultipartFile file = uploadFile.getFile();
        fileValidator.validate(uploadFile, result);
        String fileName = file.getOriginalFilename();
        if(result.hasErrors()) {
            //업로드할 파일을 선택하지 않은 경우 form에 에러 메세지를 출력함
            return "team/teammain";
        }
        try {
            inputStream = file.getInputStream();
            
          String teamName = teamServiceImpl.getName(vo.getTeamNo());
          File newFile = new File("D:/fileUpload/" + teamName + "/" + Long.toString(projNo) + "/"+ fileName);
            
            while (newFile.exists()) {
               i++;
               int idx = fileName.lastIndexOf(".");
               String _fileName = fileName.substring(0, idx);
               String ext = fileName.substring(idx);
               
               newFile = new File("D:/fileUpload/" + teamName + "/" + Long.toString(projNo) + "/" +_fileName + "(" + i + ")" + ext);
               continue;
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];
            
            while((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes,0,read);
            }
            
        } catch (Exception e) {
            System.out.println("err : " + e);
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return "redirect:/team/teammain?projNo="+projNo;
    }
    
   @RequestMapping(value="/download")   
   @ResponseBody
    public byte[] downProcess(HttpSession session,HttpServletResponse response,
       @RequestParam(value="projNo",required=false) Long projNo,   
       @RequestParam(value="file",required=false) String filename) throws IOException{
      
      UserVo vo = (UserVo) session.getAttribute("authUser");
      String teamName = teamServiceImpl.getName(vo.getTeamNo());
      System.out.println("다운로드쪽 "+ projNo +"        " + filename);
      File file = new File("D:/fileUpload/" + teamName + "/" + Long.toString(projNo) + "/"+ filename);
        byte[] bytes = FileCopyUtils.copyToByteArray(file);
        
        String fn = new String(file.getName().getBytes(), "iso_8859_1");
        
        response.setHeader("Content-Disposition",
                "attachment;filename=\"" + fn + "\"");
        response.setContentLength(bytes.length);
        return bytes;
        
        
    }
   @RequestMapping(value="/delete")
   public String delete(HttpSession session,HttpServletResponse response,
          @RequestParam(value="projNo",required=false) Long projNo,   
          @RequestParam(value="file",required=false) String filename){
      
      UserVo vo = (UserVo) session.getAttribute("authUser");
      String teamName = teamServiceImpl.getName(vo.getTeamNo());
      
        File file = new File("D:/fileUpload/" + teamName + "/" + Long.toString(projNo) + "/" + filename );
         
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("파일삭제 성공");
                return "redirect:/team/teammain?projNo="+projNo;
            } else {
                System.out.println("파일삭제 실패");
                return "redirect:/";
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
            return "redirect:/";
        }
    }
   @ResponseBody
   @RequestMapping(value="/move", method=RequestMethod.POST)
   public Long move(@RequestParam("taskNo") Long taskNo, 
         @RequestParam("taskState") Long taskState,
         @RequestParam("test") long[] idx) {         // Ajax로 전달받는 변수 값
      Map<String, Object> map = new HashMap<>();
      Long oldState = taskServiceImpl.checkState(taskNo);   // 프로그레스 바를 위해
      map.put("taskNo",taskNo);
      map.put("taskState",taskState);
      map.put("test",idx);
      
      boolean success;
      boolean success2=false;
      Long res=0L;
      Long index=(long)0;
      
      for(int i=0;i<idx.length;i++) {
         success2 = taskServiceImpl.setIndex(idx[i],index);   // 칸반보드의 task 목록을 하나씩 불러와서 해당 인덱스를 DB에 저장
         index++;
      }
      success = taskServiceImpl.move(map);
      if(success==true && success2==true) {   // move도 성공하고 setIndex도 성공했을 때
         if (oldState == taskState) {
            res=0L;
         } else if (taskState == 2) {
            res=1L;
         } else if (oldState == 2) {
            res=-1L;
         }
         
      }else {
         res=null;
      }
      return res;
   }
   @RequestMapping(value="/add", method= {RequestMethod.POST,RequestMethod.GET})
   public String add(@ModelAttribute ProjectVo vo,
         @RequestParam("taskTitle") String taskTitle,
         @RequestParam("projNo") Long projNo,TaskVo vo2,
         @RequestParam("userId") String userId,Model model) {
      System.out.println("넘어와"+projNo+" "+taskTitle+" "+userId);
      boolean success;
      List<TaskVo> list = taskServiceImpl.getList(projNo);
      vo2.setProjNo(projNo);
      vo2.setTaskTitle(taskTitle);
      vo2.setUserId(userId);
      success = taskServiceImpl.insert(vo2);
      model.addAttribute("list",list);
      
      return "redirect:/team/teammain?projNo="+Long.toString(projNo);
   }
   @RequestMapping(value="/modify", method=RequestMethod.POST)
   public String modify(@ModelAttribute ProjectVo vo,
         @RequestParam(value="userId", required=false) String userId,
         @RequestParam(value="taskTitle", required=false) String taskTitle,
         @RequestParam(value="projNo", required=false) Long projNo,
         @RequestParam(value="taskNo", required=false) Long taskNo) {
      if(taskTitle.isEmpty()) {
         taskServiceImpl.delete(taskNo);
      } else {
         Map<String, Object> map = new HashMap<>();
         map.put("userId", userId);
         map.put("taskTitle",taskTitle);
         map.put("taskNo",taskNo);
         taskServiceImpl.update(map);
      }
      return "redirect:/team/teammain?projNo="+projNo;
   }
   @RequestMapping(value="/reg", method=RequestMethod.POST)
   public String register(@ModelAttribute ProjectVo vo, HttpSession session) {
//      UserVo authUser = (UserVo)session.getAttribute("authUser");
//      vo.setTeamNo(authUser.getTeamNo());
      System.out.println(vo.getProjDescription());
      projectServiceImpl.insert(vo);
      return "redirect:/team/teammain";
   }
   // 팀페이지에서 멘토찾기 누르면 멘토 리스트 띄워줌
   @RequestMapping(value="/findmentor/{teamNo}")
   public String mentorList(@PathVariable("teamNo") Long teamNo, Model model) {
      List<KeywordVo> klist = keywordServiceImpl.getTeamKeyList(teamNo);
         List<MentorVo> mlist = teamServiceImpl.getMentorList();
            
         for (MentorVo mVo: mlist) {
            Long score = 0L;
            Map<String, Long> map = new HashMap<String, Long>();
               
            List<KeywordVo> mentorKlist = keywordServiceImpl.getList(mVo.getUserNo());
               
            for (KeywordVo mkVo: mentorKlist) {
               for (KeywordVo tkVo: klist) {
                  System.out.println(tkVo.getKeyName().replaceAll("\\p{Z}", ""));
                  if (mkVo.getKeyName().replaceAll("\\p{Z}", "").matches(".*"+tkVo.getKeyName().replaceAll("\\p{Z}", "")+".*")) {
                     score++;
                  }
               }
            }
            map.put("userNo", mVo.getUserNo());
            map.put("score", score);
            boolean success = UserServiceImpl.updateScore(map);
            System.out.println(success);
         }
      mlist = teamServiceImpl.getMentorList();
      System.out.println(mlist);
      model.addAttribute("mlist", mlist);
         
      return "team/mentors";
   }
   // 맘에 드는 멘토에게 신청버튼 누르기
   @RequestMapping(value="/apply/{teamNo}/{userNo}")
   public String mentorApply(@PathVariable("userNo") Long userNo, 
         @PathVariable("teamNo") Long teamNo) {
      Map<String, Long> map = new HashMap<String, Long>();
      map.put("userNo", userNo);
      map.put("teamNo", teamNo);
         
      teamServiceImpl.mentorReq(map);
         
      return "redirect:/team/teammain";
   }
   // 멘토가 팀페이지 클릭 시 속해있는 팀 리스트 띄워주기
   @RequestMapping(value="/teamlist", method={RequestMethod.GET})
   public String teamList(HttpSession session, Model model,
         @RequestParam(value="userId",required=false,defaultValue="") String userId) {
      upTeamNo=null;
      
      authUser = (UserVo)session.getAttribute("authUser");
      UserVo vo = (UserVo)session.getAttribute("authUser");
      if (authUser != null) {
         if (authUser.getUserNo() != vo.getUserNo()) {
            authUser = vo;
         }
      }else {
         authUser = vo;
      }
      
      
      List<TeamVo> tlist = teamServiceImpl.getTeamList(authUser.getUserNo());
      model.addAttribute("tlist", tlist);
         
      return "team/teamlist";
   }
   // 선택한 팀의 팀메인페이지로 이동
   @RequestMapping(value="/teamchoose/{teamNo}")
   public String teamchoose(@PathVariable("teamNo") Long teamNo,
         @RequestParam(value="userId",required=false,defaultValue="") String userId,
         @RequestParam(value="projNo",required=false) Long projNo,
         @ModelAttribute ProjectVo pVo, 
         HttpSession session, Model model) {
      System.out.println(teamNo+userId+"teamchoose");   
      upTeamNo=teamNo;
      Map<String, Long> map = new HashMap<String, Long>();
      map.put("userNo", authUser.getUserNo());
      map.put("teamNo", upTeamNo);      
      UserServiceImpl.updateTeam(map);
      if(userId!="") {
         authUser = UserServiceImpl.getUser(userId);
         session.setAttribute("authUser", authUser);
         System.out.println(authUser);
      }
      return "redirect:/team/teammain";
   }
}