package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.FavPhoneVO;

@Mapper
public interface FavPhoneRepository {

	List<FavPhoneVO> getFavList(String register);
	
	void removeFav(int favId);
	
	FavPhoneVO getFav(int favId);
	
	void registFav(int phoneBookId, String register);
	
	List<FavPhoneVO> getFavApp(String register);

	void removeFavAtPhone(int phone_book_id, String register);
}
