package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.BookMark;

@Mapper
public interface BookMarkRepository {
	void registFav(String register, String mcode, String page_name);
	
	BookMark dupCheck(String register,String mcode);
	
	void deleteFav(String register, String mcode);
	
	List<BookMark>getFavList(String register);
	
}
