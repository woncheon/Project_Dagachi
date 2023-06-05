package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.Proceeding;

@Mapper
public interface ProceedingRepository {
	
	List<Proceeding> getProceedingImpList();
	
	List<Proceeding> getProceedingList(String searchType, String keyword, int limitStart, int limitTake);

	int getProceedingCount(String searchType, String keyword);

	Proceeding getProceedingById(int proceeding_id);
	
	void registProceeding(String title,String topic,String register, int type, int scope, String attenders, String body, String result);
	
	
	
	
}
