package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.Approval;

@Mapper
public interface ApprovalRepository {

	
	 List<Approval> getApprovalListByApproval_id(int approval_id);
	 
	 void insertApproval(Approval approval);
	 
	Approval getApprovalByApprover(String a_approvar);
	
	
}
