package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.TeamVO;

@Mapper
public interface TeamRepository {

	List<TeamVO> getTeamListByDep(int dep);
	TeamVO getTeamByCode(int team);
	
}
