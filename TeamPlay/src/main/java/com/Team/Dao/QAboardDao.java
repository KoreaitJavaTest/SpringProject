package com.Team.Dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.Team.Vo.AnswerVO;
import com.Team.Vo.QAboardVo;

public interface QAboardDao {

	int QAselectCount(QAboardDao mapper);

	ArrayList<AnswerVO> selectAlist();

	ArrayList<QAboardVo> QAselectList(HashMap<String, Integer> hmap);

	void insert(QAboardVo vo);

	QAboardVo QAselectByIdx(int q_idx);

	AnswerVO AnswerByIdx(int q_idx);

	void update(QAboardVo vo);

	void delete(int idx);

	void ansReply(AnswerVO vo);

	AnswerVO selectAnswarbyref(int q_idx);

	void editqaflag(AnswerVO voref);

	void Aupdate(AnswerVO vo);

	AnswerVO selectAnswar(int a_idx);

	void ansdelete(int a_idx);

}
