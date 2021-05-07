package com.Team.Dao;

import java.util.ArrayList;

import com.Team.List.ReViewList;
import com.Team.Vo.ReViewVO;

public interface ReViewDAO {

	int selectCount();

	ArrayList<ReViewVO> selectList(ReViewList list);

	ReViewVO selectByIdx(int idx);

	void ReViewUpdate(ReViewVO vo);

	void ReViewDelete(int idx);

}
