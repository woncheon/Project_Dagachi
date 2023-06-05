package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.Approval_DocumentRepository;
import com.sbs.dagachi.vo.Approval_Document;

@Service
public class Approval_DocumentService {

	
	@Autowired
	private Approval_DocumentRepository approval_documentRepository;
	
	public Approval_DocumentService(Approval_DocumentRepository approval_documentRepository) {
			this.approval_documentRepository=approval_documentRepository;
	}
	
	public List<Approval_Document> getA_documentListByRegister(String approval_register ,String st,String sk ,String status,int save,int itemsCountInAPage,int page){
		int limitStart = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;
		
		List<Approval_Document> A_documentList=approval_documentRepository.getA_documentListByRegister(approval_register,st,sk,status,save,limitStart,limitTake);
		
		return A_documentList;
		
	}
	public List<Approval_Document> getReceiveA_documentListByApprover(String approval_approver,String st,String sk,String status,int itemsCountInAPage,int page){
		int limitStart = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;
		List<Approval_Document>A_documentList=approval_documentRepository.getReceiveA_documentListByApprover(approval_approver,st,sk,status,limitStart,limitTake);
		return A_documentList;
		
	}
	public void insertA_document(Approval_Document approval_document) {
		 approval_documentRepository.insertA_document(approval_document);
		
	}
	public Approval_Document getA_document(int approval_id) {
		
		return approval_documentRepository.getA_document(approval_id);
	}
	
	public void updateA_status(int approval_id,int approval_status) {
		
		approval_documentRepository.updataA_status(approval_id, approval_status);
	}
	
	public int CountSaveDocument(String approval_register) {
		
		return approval_documentRepository.CountSaveDocument(approval_register);
		
	}
	public int Count1(String approval_register ,String st,String sk ,String status,int save) {
		
		return approval_documentRepository.CountA_documentListByRegister(approval_register, st, sk, status, save);
	}
	
	public int Count2(String approval_approver,String st,String sk,String status) {
		
		return approval_documentRepository.CountReceiveA_documentListByApprover(approval_approver, st, sk, status);
	}
	
	
	
	
	
}
