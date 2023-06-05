package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.TeamRepository;
import com.sbs.dagachi.vo.TeamVO;

@Service
public class TeamService {
	private TeamRepository teamRepository;

	public TeamService(TeamRepository teamRepository) {
		this.teamRepository=teamRepository;
	}

	public List<TeamVO> getTeamListByDep(int dep) {
		return teamRepository.getTeamListByDep(dep);
	}
	
	public TeamVO getTeamByCode(int team) {
		return teamRepository.getTeamByCode(team);
	}
	
	
	
}
