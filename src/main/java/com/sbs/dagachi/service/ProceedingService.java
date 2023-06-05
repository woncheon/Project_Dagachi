package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.ProceedingRepository;
import com.sbs.dagachi.vo.Proceeding;

@Service
public class ProceedingService {
	private ProceedingRepository proceedingRepository;

	public ProceedingService(ProceedingRepository proceedingRepository) {
		
		this.proceedingRepository=proceedingRepository;
	}
	
	public List<Proceeding> getProceedingImpList(){
		return proceedingRepository.getProceedingImpList();
	}
	
	public List<Proceeding>getProceedingList(String searchType, String keyword, int itemsCountInAPage, int page){
		int limitStart = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;
		return proceedingRepository.getProceedingList(searchType, keyword, limitStart, limitTake);
	}

	public int getProceedingCount(String searchType, String keyword) {
		return proceedingRepository.getProceedingCount(searchType,keyword);
	}

	public Proceeding getProceedingById(int proceeding_id) {
		return proceedingRepository.getProceedingById(proceeding_id);
	}
	
	public void registProceeding(String title,String topic,String register, 
			int type, int scope, String attenders, String body, String result) {
		proceedingRepository.registProceeding(title, topic, register, type, scope, attenders, body, result);
	}
	
	
	
	
	
}
