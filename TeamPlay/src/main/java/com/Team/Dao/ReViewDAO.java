package com.Team.Dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.Team.List.ReViewList;
import com.Team.Vo.ReViewCommentVO;
import com.Team.Vo.ReViewSearchVO;
import com.Team.Vo.ReViewVO;

public interface ReViewDAO {

	int selectCount();

	ArrayList<ReViewVO> selectList(ReViewList list);

	ReViewVO selectByIdx(int idx);

	void ReViewUpdate(ReViewVO vo);

	void ReViewDelete(int idx);

	void ReViewInsert(ReViewVO vo);

	int ReViewTotalCount(ReViewSearchVO searchVo);

	ArrayList<ReViewVO> ReViewSearch(ReViewSearchVO searchVo);

	void CommentUp(int refIdx);

	void insertComment(ReViewCommentVO vo);

	int CommentTotalCount(int idx);

	ArrayList<ReViewCommentVO> selectCommentList(HashMap<String, Integer> hmap);

	void updateComment(HashMap<String, String> hmap);

	void likeUp(int idx);

	void checkUserUpdate(ReViewVO vo);

	void likeDown(int idx);

	void minusCommentCount(int idx);

	void deleteComment(int commentIdx);

}
