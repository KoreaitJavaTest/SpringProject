package com.Team.Dao;

import java.util.ArrayList;

import com.Team.List.ShopList;
import com.Team.Vo.ShopVO;

public interface ShopDAO {

	int selectCount(ShopDAO mapper);
	
	ArrayList<ShopVO> selectAllProduct(ShopList list);


}
