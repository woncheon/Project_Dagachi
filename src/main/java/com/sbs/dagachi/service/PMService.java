package com.sbs.dagachi.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.PMRepository;
import com.sbs.dagachi.vo.PM;

@Service
public class PMService {
	private PMRepository pMRepository;

	public PMService(PMRepository pMRepository) {
		this.pMRepository=pMRepository;
	}
	
	public List<PM>getPMList(String member_id){
		
		return pMRepository.getPMList(member_id);
	}
	
	public void registPM(String pm_name, Date pm_endDate, String pm_register, String pm_manager, int pl_id) {
		pMRepository.registPM(pm_name, pm_endDate, pm_register, pm_manager, pl_id);
		
	}
	
	public List<PM>getPLlist(String member_id){
		return pMRepository.getPLlist(member_id);
	}
	
	public int last_insert_id() {
		return pMRepository.last_insert_id();
	}
	
	public PM getPMByPmId(int pm_id) {
		return pMRepository.getPMByPmId(pm_id);
	}
	
	public void modifyKSPM(String pm_manager, int pm_id) {
		pMRepository.modifyKSPM(pm_manager, pm_id);
	}

	public List<PM> getPMListByPLId(int pl_id) {
		return pMRepository.getPMListByPLId(pl_id);
	}

	public void modifyStatus(String stat, int pm_id) {
		int statInt=Integer.parseInt(stat);
		pMRepository.modifyStatus(statInt, pm_id);
	}
}
