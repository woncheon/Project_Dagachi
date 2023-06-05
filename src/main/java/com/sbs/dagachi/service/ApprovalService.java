package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.ApprovalRepository;
import com.sbs.dagachi.vo.Approval;

@Service
public class ApprovalService {

	@Autowired
	private ApprovalRepository approvalRepository;
	
	public void GetApprovalRepository(ApprovalRepository approvalRepository) {
		this.approvalRepository=approvalRepository;
	}
	
	public List<Approval> getApprovalListByApproval_id(int approval_id){
		
		return  approvalRepository.getApprovalListByApproval_id(approval_id);
	}
	public void insertApproval(Approval approval) {
		approvalRepository.insertApproval(approval);
		
	}
	
	
	
	
}
