package com.sbs.dagachi.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.ProjectL;

@Mapper
public interface ProjectLRepository {
	List<ProjectL>getPLlistByTeam(String teamName);
	
	void moveAnotherTeam(int pl_id,String teamName);
	
	void registPL(String pl_name, Date pl_endDate, String pl_register, String pl_manager, String pl_team);
	
	String getTeamName(int pl_Id);
	
	void removePl(int pl_Id);
	
	ProjectL getPLByPLId(int pl_Id);

	int getLastInsertedId();
}
																								