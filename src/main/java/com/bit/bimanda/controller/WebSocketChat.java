package com.bit.bimanda.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.bit.bimanda.service.Team_ChatService;
import com.bit.bimanda.service.UserService;
import com.bit.bimanda.vo.UserVo; 
@Controller
@ServerEndpoint(value="/echo.do", configurator = SpringConfigurator.class)  //configurator 추가안하면 autowired된 서비스 호출불가 
//@RequestMapping("/echo.do")
public class WebSocketChat {
   
   @Autowired
   UserService UserServiceImpl;
   @Autowired
   Team_ChatService Team_ChatServiceImpl;
   
   
    private static final List<Session> sessionList=new ArrayList<Session>();;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);
    
    @PostConstruct  // 이것도 추가해야 autowired 된 service 호출 가능 
    public void init(){
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    public WebSocketChat() {
        // TODO Auto-generated constructor stub
        System.out.println("웹소켓(서버) 객체생성");
    }
    @RequestMapping(value="/chat.do")
    public ModelAndView getChatViewPage(ModelAndView mav) {
        mav.setViewName("chat");
        return mav;
    }
    @OnOpen
    public void onOpen(Session session) {
        
       logger.info("Open session id:"+session.getId());
        try {
            final Basic basic=session.getBasicRemote();
            System.out.println(session.getId()+"  Connection Established");
//          basic.sendText("Connection Established");
            
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        sessionList.add(session);
    }
    /*
     * 모든 사용자에게 메시지를 전달한다.
     * @param self
     * @param message
     */
    private void sendAllSessionToMessage(Session self,String message) {
         
       try {
            for(Session session : WebSocketChat.sessionList) {
                
               if(!self.getId().equals(session.getId())) { //자기자신이 아닌 사람들에게 텍스트보내기  
                    if(!message.substring(0,3).equals("kan")&&!message.substring(0,10).equals("taskModify")&&!message.substring(0,10).equals("taskDelete")&&!message.substring(0,7).equals("taskAdd")) { //-> 메시지는 kan + projNo 로넘어옴 
                       session.getBasicRemote().sendText("<div class=\"bubbleyou\">"+message.split(":")[0]+" : "+message.split(":")[1]+"</div>");
                       System.out.println("sendAllmessage 채팅");
                    }
                    else if(message.substring(0,3).equals("kan")){ //message가 칸반이면 칸반 실행~  {
                        session.getBasicRemote().sendText(message);
                    }
                    else if(message.substring(0,10).equals("taskModify")){ //message가 칸반이면 칸반 실행~  {
                       session.getBasicRemote().sendText(message);
                    }
                    else if(message.substring(0,10).equals("taskDelete")){ //message가 칸반이면 칸반 실행~  {
                        session.getBasicRemote().sendText(message);
                     }
                    else if(message.substring(0,7).equals("taskAdd")){ //message가 칸반이면 칸반 실행~  {
                        session.getBasicRemote().sendText(message);
                    }
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
           System.out.println("메소드에서 걸리나");
            System.out.println(e.getMessage());
        }
    }
    @OnMessage
    public void onMessage(String message,Session session) {
       if(!message.substring(0,3).equals("kan")&&!message.substring(0,10).equals("taskModify")&&!message.substring(0,10).equals("taskDelete")&&!message.substring(0,7).equals("taskAdd")) { // message가 kan이 아니면 일반채팅 
           logger.info("Message From "+message.split(":")[0]);
           System.out.println(message);
           String id=message.split(":")[0];
           id=id.substring(1, id.length()-1);  // 순수하게 userId 값을 위해 다른문자 자르기 
           System.out.println("글쓴 사람 아이디   "+id); //id 값은 넘어오는데 서비스가 호출이안된다.11111111111111111
           String chatContent=message.split(":")[1];
           UserVo vo = UserServiceImpl.getUser(id);
           System.out.println(vo.getTeamNo()+" "+vo.getUserNo()+" "+chatContent);
           boolean success=false;
           success=Team_ChatServiceImpl.insert(vo.getTeamNo(),vo.getUserNo(),vo.getUserId(), chatContent);        
           System.out.println(vo.getUserId()+"vo 호출 돼 ?");
           System.out.println(success);
           try {
               final Basic basic=session.getBasicRemote();
               basic.sendText("<div class=\"bubble\">"+message+"</div>"); //writeResponse(text)에서 text부분 
           }catch (Exception e) {
               // TODO: handle exception
              System.out.println("chat여기인가");
               System.out.println(e.getMessage());
           }
       }
       else if(message.substring(0,3).equals("kan")){ //message가 칸반이면 칸반 실행~ 
          System.out.println("kanbanmove onMessage" + message);
          try {
               final Basic basic=session.getBasicRemote();
               basic.sendText(message);
           }catch (Exception e) {
               // TODO: handle exception
              System.out.println("move여기인가");
              System.out.println(e.getMessage());
           }
       }
       else if(message.substring(0,10).equals("taskModify")){ //message가 칸반이면 칸반 실행~ 
           System.out.println("taskModify onMessage" + message);
           try {
                final Basic basic=session.getBasicRemote();
                basic.sendText(message);
            }catch (Exception e) {
                // TODO: handle exception
               System.out.println("modify 여기인가");
                System.out.println(e.getMessage());
            }
       }else if(message.substring(0,10).equals("taskDelete")){ //message가 칸반이면 칸반 실행~ 
           System.out.println("taskDelete onMessage" + message);
           try {
                final Basic basic=session.getBasicRemote();
                basic.sendText(message);
            }catch (Exception e) {
                // TODO: handle exception
               System.out.println("delete여기인가");
                System.out.println(e.getMessage());
            }
        }else if(message.substring(0,7).equals("taskAdd")){ //message가 칸반이면 칸반 실행~ 
            System.out.println("taskAdd onMessage" + message);
            try {
                 final Basic basic=session.getBasicRemote();
                 basic.sendText(message);
             }catch (Exception e) {
                 // TODO: handle exception
                System.out.println("add여기인가");
                 System.out.println(e.getMessage());
             }
         }
       sendAllSessionToMessage(session, message);
    }
    @OnError
    public void onError(Throwable e,Session session) {
       System.out.println(e.getMessage());
    }
    @OnClose
    public void onClose(Session session) {
        logger.info("Session "+session.getId()+" has ended");
        sessionList.remove(session);
    }
}
 