package com.bit.bimanda.crawlhandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bit.bimanda.vo.CafeVo;
import com.bit.bimanda.vo.DaumVo;
import com.bit.bimanda.vo.NblogVo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Crawlkeyword {
	//네이버 카페가져오기 
		public List<List<CafeVo>> readkeyword(String userId,String keyName,int num,Long teamNo) { //키워드에해당하는 엑셀 읽어오는 함수 
			List<List<CafeVo>> cafelist=new ArrayList<List<CafeVo>>();
			String line="";
			String xlsx;
			int a=0;
			int i;
			while(a<num+2) {
				try {
					System.out.println(userId+keyName+teamNo);
					List<CafeVo> calist = new ArrayList<CafeVo>();
					if(teamNo!=null) {
						xlsx="C:\\Users\\BIT\\Desktop\\navercafe\\"+Long.toString(teamNo)+keyName+Integer.toString(a+1)+".xlsx";
					}else {
						xlsx="C:\\Users\\BIT\\Desktop\\navercafe\\"+userId+keyName+Integer.toString(a+1)+".xlsx";
					}
					 
					//해당아이디+키워드 의 파일을 읽어온다.
					FileInputStream fis = new FileInputStream(xlsx);
				
					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					XSSFSheet sheet = workbook.getSheetAt(0); //해당 액셀파일의 시트 수 
					int rows = sheet.getPhysicalNumberOfRows();// 해당 시트의 행의 개수 
					for(int rowIndex=1; rowIndex<rows;rowIndex++) {
						XSSFRow row=sheet.getRow(rowIndex); //각 행을 읽어온다.
						if(row !=null) {
							CafeVo cafe=new CafeVo();
							int cells = row.getPhysicalNumberOfCells();
							for(int columnIndex = 0;columnIndex <= cells;columnIndex++) {
								XSSFCell cell=row.getCell(columnIndex); //셀에 담겨져있는 값을 읽는다.
//								System.out.print(cell+"!!!!!!!!!!! ");
								if(columnIndex==1) {
									cafe.setLinks(cell);
//									System.out.print(cell+"   ");
								}
								if(columnIndex==2) {
									cafe.setContents(cell);
//									System.out.print(cell+"   ");
								}
							}
							calist.add(cafe);	
							
						}
					
			        cafelist.add(calist);
			        
//					br.close();
					fis.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
				a=a+10;
			}
			
			for(int number=0;number<cafelist.size();number++) {
				cafelist.get(number).toString();
			}
			return cafelist;
		}
		//네이버 블로그 가져오기 
		public List<List<NblogVo>> readNblogKeyword(String userId,String keyName,int num,Long teamNo) { //키워드에해당하는 엑셀 읽어오는 함수 
			List<List<NblogVo>> nbloglist=new ArrayList<List<NblogVo>>();
			String line="";
			String xlsx;
			int a=0;
			int i;
			while(a<num+2) {
				try {
					System.out.println(userId+keyName+teamNo);
					List<NblogVo> nblist = new ArrayList<NblogVo>();
					if(teamNo!=null) {
						xlsx="C:\\Users\\BIT\\Desktop\\naverblog\\"+Long.toString(teamNo)+keyName+Integer.toString(a+1)+".xlsx";
					}else {
						xlsx="C:\\Users\\BIT\\Desktop\\naverblog\\"+userId+keyName+Integer.toString(a+1)+".xlsx";
					}
					 
					//해당아이디+키워드 의 파일을 읽어온다.
					FileInputStream fis = new FileInputStream(xlsx);
				
					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					XSSFSheet sheet = workbook.getSheetAt(0); //해당 액셀파일의 시트 수 
					int rows = sheet.getPhysicalNumberOfRows();// 해당 시트의 행의 개수 
					for(int rowIndex=1; rowIndex<rows;rowIndex++) {
						XSSFRow row=sheet.getRow(rowIndex); //각 행을 읽어온다.
						if(row !=null) {
							NblogVo cafe=new NblogVo();
							int cells = row.getPhysicalNumberOfCells();
							for(int columnIndex = 0;columnIndex <= cells;columnIndex++) {
								XSSFCell cell=row.getCell(columnIndex); //셀에 담겨져있는 값을 읽는다.
//								System.out.print(cell+"!!!!!!!!!!! ");
								if(columnIndex==1) {
									cafe.setLinks(cell);
//									System.out.print(cell+"   ");
								}
								if(columnIndex==2) {
									cafe.setContents(cell);
//									System.out.print(cell+"   ");
								}
							}
							nblist.add(cafe);	
							
						}
					}
			        nbloglist.add(nblist);
			        
//					br.close();
					fis.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				}
				a=a+10;
			}
			
			for(int number=0;number<nbloglist.size();number++) {
				nbloglist.get(number).toString();
			}
			return nbloglist;
		}
		//keyName은 키워드이고 search 는 블로그인지 카페인지를 구분하는 변수 
		public List<List<DaumVo>> readDaumKeyword(String keyName,String search) { // daum 키워드 가져오기 
			List<List<DaumVo>> daumlist=new ArrayList<List<DaumVo>>();
			JsonParser Parser = new JsonParser();
	    	String text;
	        String appKey ="KakaoAK "+"fe82ebc8a6286dc1750e8f3d6cc2f880";//애플리케이션 클라이언트 아이디값";
//	        String clientSecret = "np8bCwYAAj";//애플리케이션 클라이언트 시크릿값";
	       
	        try {
	        	
	        	for(int page=1;page<6;page++) { 	
	        		List<DaumVo> dmlist = new ArrayList<DaumVo>();
	        		
	        		text = URLEncoder.encode(keyName, "UTF-8");
		            String apiURL="https://dapi.kakao.com/v2/search/"+search+"?query="+text+"&page="+Integer.toString(page);
		            URL Url = new URL(apiURL);
		            
		            HttpURLConnection con = (HttpURLConnection)Url.openConnection();
		            con.setRequestMethod("GET");
		            con.setRequestProperty("Authorization", appKey);
		            int responseCode = con.getResponseCode();
		            
		            BufferedReader br;
		            if(responseCode==200) { // 정상 호출
		                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		            } else {  // 에러 발생
		                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		            }
		            int a=0;
		            String inputLine;
		            StringBuffer response = new StringBuffer();
		            inputLine=br.readLine();
		//            while ((inputLine = br.readLine()) != null) {
		//                System.out.println(inputLine);
		//            }
		            JsonObject jsonObj = (JsonObject) Parser.parse(inputLine);
		            JsonArray jsonArray = (JsonArray) jsonObj.get("documents");
		            System.out.println(jsonArray.size()); // 10개씩 표시하니까 10출력 
		            for (int i = 0; i < jsonArray.size(); i++) {
		            	DaumVo daumVo=new DaumVo();
		            	JsonObject object = (JsonObject) jsonArray.get(i);
		            	String title = object.get("title").getAsString();
		            	String contents = object.get("contents").getAsString();
		            	String url = object.get("url").getAsString();
		//            	String datetime = object.get("datetime").getAsString();
//		            	if(search.equals("cafe")) {	
//		            		String thumbnail = object.get("thumbnail").getAsString();
//		            		System.out.println(thumbnail);
//		            		daumVo.setThumbnail(thumbnail);
//		            	}
		            	String thumbnail = object.get("thumbnail").getAsString();
	            		System.out.println(thumbnail);
	            		daumVo.setThumbnail(thumbnail);
		            	daumVo.setTitle(title);
		            	daumVo.setUrl(url);
		            	daumVo.setContents(contents);
		            	System.out.println(title);
		            	System.out.println(contents);
		            	System.out.println(url);
		            	System.out.println();            	
		            	dmlist.add(daumVo);
		            }
		            br.close();
		            daumlist.add(dmlist);
	        	}
	        	
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        
	        return daumlist;
		}
		
}
