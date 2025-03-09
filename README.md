# Bimanda 
비만다(비트인은 아무나 만나지 않는다)

개발인원 및 담당 개발 : 유승훈(비즈니스 로직 및 플라스크 서버 구축, 크롤링) 
                       노성헌(화면 레이아웃 설계 및 프론트 개발)
                       김다혜(ERD 설계) 

기간 : 2019.03 ~ 2019.06

프로젝트 소개
- 비트 교육센터에서 교육을 받았을 시절 최종프로젝트로 개발한 프로젝트입니다.
- 여러 협업 사이트들의 각 장점만을 살려서 협업이 가능한 공간을 만들자라는 것이 시발점이 되어서 시작된 프로젝트입니다.
- 이용자들은 사이트내에서 개인의 팀을 꾸릴 수 있고 그 안에서 팀원끼리의 파일 관리 및 멀티 채팅 그리고 칸반보드를 이용한 to do, doing, done 세가지의 업무 현황을
  수정 할 수 있습니다.
- 또한 키워드를 설정한다면 자신이 설정해 놓은 키워드들을 토대로 웹사이트에서의 정보들을 사이트내에서 한번에 확인이 가능하고 공모전 현황까지 한번에 확인 할 수 있습니다.


개발환경 
- windows10, centos7

사용언어
-java, python, jsp, javascript, css, jquery, html

사용도구 tool 
- eclipse , pycharm, jupyter notebook

사용기술 
- spring framework, mybatis, jstl 

사용DB
- oracle DB

시스템 구성도
![image](https://user-images.githubusercontent.com/40010035/232291468-a7237575-8ce9-4340-9438-9311925df823.png)


기능
1.회원인증 서비스(coolsms api 이용)

![image](https://user-images.githubusercontent.com/40010035/232300908-07e29912-60e1-43cd-bd7d-894f7d3d2ae9.png)

2.게시판 기능 

![image](https://user-images.githubusercontent.com/40010035/232301125-568d70f8-186d-4239-817e-e5875d2686c1.png)

3.게시물 작성 템플릿 (summernote api 이용)

![image](https://user-images.githubusercontent.com/40010035/232304237-9969b1dc-edcf-48cf-9ef1-4d4932fe402e.png)


4.공모전 현황 확인
- 파이썬 beautiful soup을 이용하여 씽굿 홈페이지를 크롤링 한 후 pandas로 데이터를 처리하여 카테고리별 공모전 현황 및 내용을 확인 할 수 있습니다.
- 
![image](https://user-images.githubusercontent.com/40010035/232301479-ce2b01d7-e471-4f46-876a-e3c7433b2c37.png)

5.컨텐츠 페이지
- 개인별, 팀별 키워드 설정을 통해 Naver, Daoum 카페, 블로그 정보 확인 가능

- Naver 카페, 블로그의 경우에는 따로 api를 제공하지 않았어서 데이터 크롤링을 위해 따로 띄워놓은 Flask 서버에 크롤링 요청을 하고 
  크롤링된 데이터를 파일시스템에 저장 후 해당 csv파일의 데이터를 읽어서 페이지에 보여주도록 처리하였습니다.

- Daum 카페, 블로그는 카카오에서 제공하는 rest api 인증 요청 후 return 받은 json 데이터를 파싱해서 리스트를 띄워 주었습니다.

![image](https://user-images.githubusercontent.com/40010035/232301799-dfc580b3-e9bf-45d8-b1e5-ab30ec1554fc.png)

6.멘토 신청 및 승인
- 보통의 공모전에서 멘토를 껴서 하는 경우가 종종있고 해당 멘토들이 멘티들에게 프로젝트 제안이나 사용기술들에대해서 피드백을 주는 경우를 보고 그것에 착안해서 개발했습니다. 
- 마이페이지에서 멘토가 되고 싶은 사람은 신청 할 수 있습니다. 

![image](https://user-images.githubusercontent.com/40010035/232302468-8f63c540-b726-426d-9c74-87c79bfe7ad9.png)

- 관리자가 멘토 신청리스트에서 승인을 누른 사람은 멘토로 선정이 됩니다.

![image](https://user-images.githubusercontent.com/40010035/232302538-9294b2af-5de5-449a-8242-efc3dccc286c.png)

7.멘토 추천 
- 멘토 찾기에서 팀별 등록된 키워드등을 기반으로 사용된 키워드들의 갯수가 많은 순서대로 점수를 줘서 띄워줬습니다.  

![image](https://user-images.githubusercontent.com/40010035/232302624-89caea69-fd98-45ca-88ce-45e2ced725cd.png)

- 팀 페이지에서 팀원이 멘토 신청을 하면 멘토는 승인, 거절을 할 수 있습니다.

![image](https://user-images.githubusercontent.com/40010035/232303099-a1d579c7-bafd-4252-9109-b49e14f33e60.png)

8.팀 페이지 
- 웹소켓통신을 이용하여 팀원간 실시간 채팅 기능을 지원합니다.
- 칸반 보드를 통해 진행률을 확인 가능합니다.
- 웹소켓통신을 통해서 칸반보드 진행상황 수정시 팀원들의 페이지도 수정되도록 처리했습니다.
- 팀원간 공유가 가능하도록 파일서버에 파일 업로드, 파일 다운로드 기능이 가능합니다.
- 팀원목록에서 멘토와 팀원을 확인 가능합니다. 

![image](https://user-images.githubusercontent.com/40010035/232303227-ca2295bb-33ae-43e3-b5a7-e2befb51c941.png)

