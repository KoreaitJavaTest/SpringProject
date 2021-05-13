package com.Team.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.Team.List.AdminUserMangementList;
import com.Team.Vo.ClientVo;
import com.Team.Vo.ServerConnectionIPVO;

public interface AdminDao {

	int userTotalCount();

	ArrayList<ClientVo> AdminUserSelectList(HashMap<String, Integer> hmap);

	void AdminUserDelete(String userId);

	void adminUpserUpdate(ClientVo updateVo);

	ArrayList<ServerConnectionIPVO> selectEnterList();

}
