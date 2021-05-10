package com.Team.Dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.Team.List.AdminUserMangementList;
import com.Team.Vo.ClientVo;

public interface AdminDao {

	int userTotalCount();

	ArrayList<ClientVo> AdminUserSelectList(HashMap<String, Integer> hmap);

	void AdminUserDelete(String userId);

	void adminUpserUpdate(ClientVo updateVo);

}
