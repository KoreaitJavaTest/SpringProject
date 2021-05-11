package com.Team.Dao;

import java.util.ArrayList;
import java.util.Map;

import com.Team.List.ShopList;
import com.Team.Vo.ShopVO;

public interface ShopDAO {

	int selectCount(ShopDAO mapper);
	ArrayList<ShopVO> selectAllProduct(ShopList list);
	ArrayList<ShopVO> selectCategoryDetail(ShopList shopList);
	void shopInsertProduct(ShopVO vo);
	void shopInsertProduct1(ShopVO vo);
	void shopInsertProduct2(ShopVO vo);
	
	ShopVO selectProduct(int sh_idx);
	int likeCount(int sh_idx);
	int likeCheck(Map<String, Object> likeMap);
	void likeUpdate(Map<String, Object> likeMap);
	void likeDelete(Map<String, Object> likeMap);
	void productLikeInsert(Map<String, Object> map);
	void deleteProduct(int sh_idx);
	void updateProduct(ShopVO vo);
	void updateProduct1(ShopVO vo);
	void updateProduct2(ShopVO vo);
	ArrayList<ShopVO> goodKingShop();

}
