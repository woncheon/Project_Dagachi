package com.sbs.dagachi.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.PSRepository;
import com.sbs.dagachi.vo.PS;

@Service
public class PSService {
	private PSRepository pSRepository;

	public PSService(PSRepository pSRepository) {
		this.pSRepository=pSRepository;
	}
	
	public void registPs(String ps_Title, String ps_Sender, String ps_Receiver,Date ps_SendDate, 
			Date ps_EndDate, int ps_Status, int pm_Id) {
		pSRepository.registPs(ps_Title, ps_Sender, ps_Receiver, ps_SendDate, ps_EndDate, ps_Status, pm_Id);
	}
	
	public List<PS>getPSListWait(String member_id){
		return pSRepository.getPSListWait(member_id);
	}
	public List<PS>getPSdoingList(String member_id){
		return pSRepository.getPSListDoing(member_id);
	}
	public List<PS>getPScompleteList(String member_id){
		return pSRepository.getPSListComplete(member_id);
	}
	public List<PS>getPScancelList(String member_id){
		return pSRepository.getPSListCancel(member_id);
	}
	
	public void modifyPs(String stat, String ps_id) {
		int statInt=Integer.parseInt(stat);
		int ps_idInt=Integer.parseInt(ps_id);
		
		pSRepository.modifyPs(statInt, ps_idInt);
	}
	public void modifyPs(int stat, int ps_id) {
		int statInt=stat;
		int ps_idInt=ps_id;
		
		pSRepository.modifyPs(statInt, ps_idInt);
	}

	public PS getPSByPmId(int pm_id) {
		return pSRepository.getPSByPmId(pm_id);
	}
	
	public void modifyPsUser(int ps_id, String ps_Receiver) {
		pSRepository.modifyPsUser(ps_id, ps_Receiver);
	}

	public List<PS> getPSListByPmId(int pm_id) {
		// TODO Auto-generated method stub
		return pSRepository.getPSListByPmId(pm_id);
	}
	
	public PS getPSByPsId(int ps_id) {
		return pSRepository.getPSByPsId(ps_id);
	}

	public void removePS(String ps_id) {
		int ps_idInt=Integer.parseInt(ps_id);
		pSRepository.removePS(ps_idInt);
	}
	
	
}
