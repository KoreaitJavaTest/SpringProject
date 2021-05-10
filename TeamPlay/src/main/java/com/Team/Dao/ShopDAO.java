package com.Team.Dao;

import java.util.ArrayList;

import com.Team.List.ShopList;
import com.Team.Vo.ShopVO;

public interface ShopDAO {

	int selectCount(ShopDAO mapper);
	ArrayList<ShopVO> selectAllProduct(ShopList list);
	void shopInsertProduct(ShopVO vo);
	void shopInsertProduct1(ShopVO vo);
	void shopInsertProduct2(ShopVO vo);

}
