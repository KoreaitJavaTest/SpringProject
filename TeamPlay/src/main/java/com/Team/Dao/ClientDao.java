package com.Team.Dao;

import java.util.ArrayList;

import com.Team.Vo.AttentionPointVO;
import com.Team.Vo.ClientVo;

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

	ClientVo ClientInfo(ClientDao mapper, ClientVo vo);

	void ClientUpdate(ClientDao mapper, ClientVo vo);

	
	//진호추가 05-08
	ArrayList<AttentionPointVO> selectPoint(String userId);

	void insertPointLog(AttentionPointVO logVo);

	void depositAttentionPoint(AttentionPointVO logVo);

	int userPointSelect(String userId);
	//---

}
