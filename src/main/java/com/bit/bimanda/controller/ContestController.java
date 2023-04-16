package com.bit.bimanda.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.bit.bimanda.service.CategoryService;
import com.bit.bimanda.vo.CategoryVo;


@Controller
@RequestMapping("/Contest")
public class ContestController{
	@Autowired
	CategoryService categoryServiceImpl;
	
	@RequestMapping({"","/"})	// 공모전 현황 누르면 처음 뜨는 페이지
	public String list(Model model,CategoryVo vo,
			@RequestParam(value="pageNo", required=false, defaultValue="1") int pageNo,
			@RequestParam(value="c", required=false, defaultValue="1") Long cateNo,	// 페이지에서 cateNo을 넘겨받음
			@RequestParam(value="next", required=false, defaultValue="0") Integer next
			) {
		List<List<String>> ret = new ArrayList<List<String>>();	// [제목, 링크, 이미지경로]의 리스트로 되어있는 공모전들을 담을 리스트
		List<List<String>> ret2 = new ArrayList<List<String>>();
		BufferedReader br = null;
		List<CategoryVo> cateList = categoryServiceImpl.getList();	//	category 리스트 받을 리스트
		String path = "C:\\Users\\BIT\\gongmo\\test";	// cateNo이 변할 때마다 파일을 바꿔주기 위해 기본 path 설정
		try {
			br = Files.newBufferedReader(Paths.get(path+cateNo+".txt"));	// cateNo에 해당하는 txt파일 불러오기
			Charset.forName("UTF-8");
			String line="";
			while ((line = br.readLine()) != null) {
				List<String> tmpList = new ArrayList<String>();
				String array[] = line.split(",");	// 한 줄씩 읽어서 ,로 split
				tmpList = Arrays.asList(array);		// split한 것들을 tmpList에 담음
				String myRegex = "[^a-zA-Z0-9\\/\\:ㄱ-ㅎ|ㅏ-ㅣ|가-힣\\s\\.\\_]"; // 필요없는 문자 제거하는 정규식
				String myRegex2 = "tjdgjs";	// 저장된 파일을 불러올 때 ,로 구분하므로 제목에 ,가 들어가있으면 그것도 구분해버린다. 그래서 임시로 특정 문자열에 저장했다가 나중에 다시 바꿔줌

				int index = 0; 
				int index2 = 0;
				for (String s : tmpList) 
				    tmpList.set(index++, s.replaceAll(myRegex, "")); 	// tmpList에 하나씩 돌면서 정규식 적용
				for (String ss : tmpList) 
				    tmpList.set(index2++, ss.replaceAll(myRegex2, ","));	
				System.out.println(tmpList);
                ret.add(tmpList);
			}
			ret.remove(0);
		}catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(br != null){
                    br.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
		List numbers = new ArrayList();
		for (int i=1; i<=10; i++) {
			numbers.add(i);
		}
		int listCnt = ret.size(); // 공모전 리스트 개수
		System.out.println("리스트 길이:" +ret.size());
		int pageSize = 10;
		int pageRange;
		if (listCnt % pageSize == 0) {
			pageRange = listCnt/pageSize;
		}else {
			pageRange = listCnt/pageSize + 1;
		}
		int startPage;
		int endPage;
		if (next==1) {
			startPage = 5*(pageNo/6+1)+1;
			endPage = startPage + 4;
			if (startPage+9 >= pageRange) {
				endPage = pageRange;
			}else {
				endPage = startPage+4;
			}
			pageNo = startPage;
		}else if(next==-1) {
			startPage = 5*(pageNo/6-1)+1;
			if (startPage <= 0) {
				startPage = 1;
			}
			endPage = startPage + 4;
			if (startPage+4 >= pageRange) {
				endPage = pageRange;
			}else {
				endPage = startPage+4;
			}
			pageNo = endPage;
		}else if(next==-2) {
			startPage = 1;
			endPage = startPage + 4;
			pageNo = startPage;
			
		}else if(next==2) {
			if ((listCnt/10)%pageSize == 0) {
				startPage = listCnt/(2*pageSize) + 1;
			}else {
				startPage = listCnt/pageSize - listCnt/(5*pageSize) + 1;
			}
			endPage = listCnt/pageSize;
			pageNo = endPage;
		}else {
			startPage = 5*(pageNo/6)+1;
			if (startPage+4 >= pageRange) {
				endPage = pageRange;
			}else {
				endPage = startPage+4;
			}
		}
		
		if (pageNo*pageSize > listCnt) {
			for (int i=(pageNo-1)*pageSize; i<listCnt; i++) {
				ret2.add(ret.get(i));
			}
		}else {
			for (int i=(pageNo-1)*pageSize; i<pageNo*pageSize; i++) {
				ret2.add(ret.get(i));
			}
		}
		
		
		System.out.println("페이징 리스트 길이:" +ret2.size());
		System.out.println("listCnt :"+listCnt);
		model.addAttribute("startPage", startPage);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("listCnt", listCnt);
		model.addAttribute("pageRange", pageRange);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNum", numbers);
		model.addAttribute("file",ret2);	
		model.addAttribute("cateList",cateList);
		model.addAttribute("cateNo",cateNo);
		return "contest/list";
	}
	
	@RequestMapping("/detail")	// 공모전 클릭했을 때 상세보기 페이지
	public String detail(Model model,
			@RequestParam(value="n") int contNo,
			@RequestParam(value="c", required=false, defaultValue="1") int cateNo) {
		List<List<String>> ret = new ArrayList<List<String>>();
		List<CategoryVo> cateList = categoryServiceImpl.getList();
		BufferedReader br = null;
		List<String> tmp = new ArrayList<String>();
		String path = "C:\\Users\\BIT\\gongmo\\test";	
		String path2 = "C:\\Users\\BIT\\gongmo\\tt";	// 공모전 상세 내용 불러오는 txt
		try {
			br = Files.newBufferedReader(Paths.get(path+cateNo+".txt"));
			Charset.forName("UTF-8");
			System.out.println("데이터 경로: "+ path+cateNo+".txt");
			String line="";
			BufferedReader in = new BufferedReader(new FileReader(path2+cateNo+".txt"));
			String s2="";
			String line2;
			while ((line2 = in.readLine()) != null) {	// 내용을 s2라는 string에 한 줄로 쭉 담음
				s2 += line2;
			}
	
			String array2[] = s2.split("qlaksek");		// 크롤링할 때 각 내용 사이에 qlaksek라는 특정문자를 넣어둬서 이것으로 split
			System.out.println("array[]의 길이: "+array2.length);
			tmp = Arrays.asList(array2);
			in.close();
			while ((line = br.readLine()) != null) {
				List<String> tmpList = new ArrayList<String>();
				String array[] = line.split(",");
				tmpList = Arrays.asList(array);
				String myRegex = "[^a-zA-Z0-9\\/\\:ㄱ-ㅎ|ㅏ-ㅣ|가-힣\\s\\.\\_\\?\\&\\=\\-]"; 
				String myRegex2 = "tjdgjs";

				int index = 0; 
				int index2 = 0;
				for (String s : tmpList) 
				    tmpList.set(index++, s.replaceAll(myRegex, "")); 
				for (String ss : tmpList) 
				    tmpList.set(index2++, ss.replaceAll(myRegex2, ","));
				System.out.println(tmpList);
                ret.add(tmpList);
			}
			ret.remove(0);
		}catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(br != null){
                    br.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
		model.addAttribute("cateList",cateList);
		model.addAttribute("file",ret);
		model.addAttribute("contNo",contNo);
		model.addAttribute("content",tmp);
		
		return "contest/detail";
	}
	@RequestMapping("/링크없음")
	public String nopage() {
		return "contest/Error";
	}
	
	
}