package com.bit.bimanda.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.bimanda.vo.CommentsVo;
@Repository
public class CommentsDaoImpl implements CommentsDao{
	@Autowired
	SqlSession sqlSession;

	@Override
	public int insert(Long boardNo, Long userNo, String commentContent) {
		// TODO Auto-generated method stub
		int insertedCount = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("boardNo", boardNo);
		map.put("userNo", userNo);
		map.put("commentContent",commentContent);
		System.out.println("찍힘");
		insertedCount = sqlSession.insert("comments.insert", map);
		System.out.println("찍힘2");
		return insertedCount;
	}
	@Override
	public List<CommentsVo> getList(Long boardNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("comments.getList",boardNo);
	}
	@Override
	public CommentsVo getContent(Long commentNo) {
		CommentsVo cVo = sqlSession.selectOne("comments.selectOne", commentNo);
		return cVo;
	}
	@Override
	public int update(CommentsVo cVo) {
		int updatedCount = 0;
		updatedCount = sqlSession.update("comments.update", cVo);
		return updatedCount;
	}
	@Override
	public int delete(Long commentsNo) {
		int deletedCount = 0;
		deletedCount = sqlSession.delete("comments.delete", commentsNo);
		return deletedCount;
	}
}
