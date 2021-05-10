package com.Team.Dao;

import java.util.ArrayList;

import com.Team.List.QAboardList;
import com.Team.Vo.AttentionPointVO;
import com.Team.Vo.ClientVo;
import com.Team.Vo.QAboardVo;

public interface ClientDao {

	int join(ClientVo vo);

	String getClientEmail(String client_id);

	void emailCheckAction(String code);

	int idoverlapcheck(String client_id);

	ClientVo login(ClientVo vo);

	String SearchMyIdByEmailDo(String email);

	ClientVo checkidandemail(String id);

	void ChangePassword(ClientVo vo);

	void deleteId(String id);

	ClientVo ClientInfo(ClientVo vo);

	void ClientUpdate(ClientVo vo);

	
	//진호추가 05-08
	ArrayList<AttentionPointVO> selectPoint(String userId);

	void insertPointLog(AttentionPointVO logVo);

	void depositAttentionPoint(AttentionPointVO logVo);

	int userPointSelect(String userId);
	//---

	int qnaTotalCount(String id);

	ArrayList<QAboardVo> QAselectList(QAboardList qaBoardList);

}
