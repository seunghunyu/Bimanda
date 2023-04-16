package com.bit.bimanda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.bimanda.repository.CommentsDao;
import com.bit.bimanda.vo.CommentsVo;

@Service
public class CommentsServiceImpl implements CommentsService{
	@Autowired
	CommentsDao commentsDaoImpl;

	@Override
	public boolean insert(Long boardNo, Long userNo, String commentContent) {
		// TODO Auto-generated method stub
		int insertCount = commentsDaoImpl.insert(boardNo,userNo,commentContent);
		return 1==insertCount;
	}
	@Override
	public List<CommentsVo> getList(Long boardNo) {
		// TODO Auto-generated method stub
		return commentsDaoImpl.getList(boardNo);
	}
	@Override
	public CommentsVo getContent(Long commentNo) {
		CommentsVo cVo = commentsDaoImpl.getContent(commentNo);
		return cVo;
	}
	@Override
	public boolean update(CommentsVo cVo) {
		int updatedCount = commentsDaoImpl.update(cVo);
		return 1==updatedCount;
	}
	@Override
	public boolean delete(Long commentsNo) {
		int deletedCount = commentsDaoImpl.delete(commentsNo);
		return 1==deletedCount;
	}
}
