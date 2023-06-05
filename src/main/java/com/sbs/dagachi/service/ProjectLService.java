package com.sbs.dagachi.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.PMRepository;
import com.sbs.dagachi.repository.ProjectLRepository;
import com.sbs.dagachi.vo.PM;
import com.sbs.dagachi.vo.ProjectL;

@Service
public class ProjectLService {
	private ProjectLRepository projectLRepository;
	private PMRepository pMRepository;
	
	public ProjectLService(ProjectLRepository projectLRepository, PMRepository pMRepository) {
		this.projectLRepository=projectLRepository;
		this.pMRepository=pMRepository;
	}
	
	public List<ProjectL>getPLlistByTeam(String teamName){
		List<ProjectL>plList=projectLRepository.getPLlistByTeam(teamName);
		for(ProjectL pl:plList) {
			List<PM>pm_List=pMRepository.getPM_nameList(pl.getPl_Id());
			if(pm_List.size()==0) {
				pm_List=pMRepository.getPM_nameListSub(pl.getPl_Id());//p
			}
			
//			else {
//				List<PM>pm_ListNull=pMRepository.getPM_nameListSub(pl.getPl_Id());
//				for(PM pmNUll:pm_ListNull) {
//					String check=pmNUll.getPm_manager();
//					if(check==null||check=="") {
//						pmNUll.setPm_manager("");
//						pmNUll.setExtra_pm_manager_name("");
//						pm_List.add(pmNUll);
//						System.out.println("추가했엉");
//					}
//					
//				}
//			}
			
			
			pl.setExtra_pmList(pm_List);
		}
		
		return  plList;
		
	}
	
	public void moveAnotherTeam(int pl_id, String teamName) {
		projectLRepository.moveAnotherTeam(pl_id, teamName);
	}
	
	public void registPL (String pl_name, Date pl_endDate, String pl_register, String pl_manager, String pl_team) {
		projectLRepository.registPL(pl_name, pl_endDate, pl_register, pl_manager, pl_team);
		
	}
	
	public String getTeamName(int pl_id) {
		return projectLRepository.getTeamName(pl_id);
				
	}
	
	public void removePl(int pl_id) {
		projectLRepository.removePl(pl_id);
		
	}
	
	public ProjectL getPLByPLId(int pl_id) {
		return projectLRepository.getPLByPLId(pl_id);
	}

	public int getLastInsertedId() {
		// TODO Auto-generated method stub
		return projectLRepository.getLastInsertedId();
	}
	
	
}
