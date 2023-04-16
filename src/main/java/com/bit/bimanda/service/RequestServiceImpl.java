package com.bit.bimanda.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.RequestDao;
import com.bit.bimanda.vo.RequestVo;

@Service
public class RequestServiceImpl implements RequestService {
	@Autowired
	RequestDao RequestDaoImpl;
	
	@Override
	public boolean request(RequestVo vo) {
		int requestCount = RequestDaoImpl.insert(vo);
		return 1==requestCount;
	}
	@Override
	public List<RequestVo> getList(Long no) {
		List<RequestVo> list = RequestDaoImpl.getList(no);
		return list;
	}
	@Override
	public RequestVo getContent(Long reqNo) {
		RequestVo vo = RequestDaoImpl.getContent(reqNo);
		return vo;
	}
	@Override
	public RequestVo getRequest(Map<String, Long> map) {
		RequestVo vo = RequestDaoImpl.getRequest(map);
		return vo;
	}
	@Override
	public boolean update(RequestVo vo) {
		int updatedCount = RequestDaoImpl.update(vo);
		return 1==updatedCount;
	}
	@Override
	public boolean delete(Long reqNo) {
		int deletedCount = RequestDaoImpl.delete(reqNo);
		return 1==deletedCount;
	}
}
