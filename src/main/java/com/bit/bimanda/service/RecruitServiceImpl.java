package com.bit.bimanda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.RecruitDao;
import com.bit.bimanda.vo.RecruitVo;

@Service
public class RecruitServiceImpl implements RecruitService {
	@Autowired
	RecruitDao RecruitDaoImpl;
	
	@Override
	public boolean recruit(RecruitVo vo) {
		int recruitCount = RecruitDaoImpl.insert(vo);
		return 1==recruitCount;
	}
	@Override
	public List<RecruitVo> getList() {
		List<RecruitVo> list = RecruitDaoImpl.getList();
		return list;
	}
	@Override
	public RecruitVo getDetail(Long no) {
		RecruitVo vo = RecruitDaoImpl.detail(no);
		return vo;
	}
	@Override
	public boolean update(RecruitVo vo) {
		int updatedCount = RecruitDaoImpl.update(vo);
		return 1==updatedCount;
	}
	@Override
	public boolean delete(Long no) {
		int deletedCount = RecruitDaoImpl.delete(no);
		return 1==deletedCount;
	}
	@Override
	public boolean updatehit(Long no) {
		return 1==RecruitDaoImpl.updatehit(no);
	}
}
