package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.Approval_Document;


@Mapper
public interface Approval_DocumentRepository {
   
	Approval_Document getA_document(int approval_id);
	void insertA_document(Approval_Document approval_document);
	void updateA_document(Approval_Document approval_document);
	List<Approval_Document> getA_documentListByRegister(String approval_register,String st,String sk,String status,int save,int limitStart, int limitTake);
	List<Approval_Document> getReceiveA_documentListByApprover(String approval_approver,String st,String sk,String status,int limitStart, int limitTake); 
	void updataA_status(int approval_id,int approval_status);
	int CountSaveDocument(String approval_register);
	int CountA_documentListByRegister(String approval_register,String st,String sk,String status,int save);
	int CountReceiveA_documentListByApprover(String approval_approver,String st,String sk,String status);
	
}
