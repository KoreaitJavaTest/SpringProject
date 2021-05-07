package com.Team.Dao;

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

}
