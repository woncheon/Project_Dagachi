package com.sbs.dagachi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.dagachi.service.ApprovalService;
import com.sbs.dagachi.service.Approval_DocumentService;
import com.sbs.dagachi.service.FormService;
import com.sbs.dagachi.service.MemberService;
import com.sbs.dagachi.vo.Approval;
import com.sbs.dagachi.vo.Approval_Document;
import com.sbs.dagachi.vo.Member;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("approval")
public class UsrApprovalController {
	
	@Autowired
	private Approval_DocumentService approval_documentService;
	public UsrApprovalController(Approval_DocumentService approval_documentService) {
		this.approval_documentService=approval_documentService;
	}
	@Autowired
	private MemberService memberService;
	public void GetMemberService(MemberService memberService) {
		this.memberService= memberService;
	}
	@Autowired
	private FormService formService;
	public void GetFormService(FormService formService) {
		this.formService=formService;
	}
	@Autowired
	private ApprovalService approvalService;
	public void GetApprovalService(ApprovalService approvalService) {
		this.approvalService =approvalService;
		
	}
	
	@GetMapping("/main")

	public void main(Model model,HttpSession session,String st,String sk ,String status) {
	Member member=(Member)session.getAttribute("loginUser");
	String user= member.getMember_id();
	List<Member> memberList= memberService.getMember();
	st= "";
	sk= "";
	status= "";
	int save=1;
	int itemsCountInAPage =1000;
	int page = 1;
	int saveCount = approval_documentService.CountSaveDocument(user);
	List<Approval_Document> list=approval_documentService.getA_documentListByRegister(user, st, sk, status,save,itemsCountInAPage,page);
	List<Approval_Document> list2=approval_documentService.getReceiveA_documentListByApprover(user, st, sk, status,itemsCountInAPage,page);
	List<Approval_Document> alist = new ArrayList<Approval_Document>();
	List<Approval_Document> alist2 = new ArrayList<Approval_Document>();
	List<Approval_Document> alist3 = new ArrayList<Approval_Document>();

	if(list.size() != 0) {
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getApproval_status()==0) {
				alist.add(list.get(i));
			}
			
		}
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getApproval_status()==2) {
				alist3.add(list.get(i));
			}
			
		}
	}
	if(list2.size() != 0 ) {
		for(int i=0;i<list2.size();i++) {
			if(list2.get(i).getApproval_status()==0) {
				alist2.add(list2.get(i));
			}
			
		}
		
	}
	model.addAttribute("num4",saveCount);
	model.addAttribute("num",alist.size());
	model.addAttribute("num2",alist2.size());
	model.addAttribute("num3",alist3.size());
	model.addAttribute("form",formService.getFormList());
	
	model.addAttribute("approvalList",approval_documentService.getA_documentListByRegister(user, st, sk, status,save,itemsCountInAPage,page));
	model.addAttribute("approverList",approval_documentService.getReceiveA_documentListByApprover(user, st, sk, status,itemsCountInAPage,page));
	model.addAttribute("memberList",memberList);
	  
		
	}
	
	@GetMapping("list")
	public void list(Model model,HttpSession session,@RequestParam(defaultValue = "1") int page,String st,String sk,String status, String Type,String save) {
		List<Member> memberList= memberService.getMember();
		String loginUser="loginUser";
		Member member = (Member) session.getAttribute(loginUser);
		String user= member.getMember_id();
		String title="";
		if(st==null)st="";
		if(sk==null)sk="";
		if(status==null)status="";
		model.addAttribute("Type",Type);
		model.addAttribute("st",st);
		model.addAttribute("status",status);
		model.addAttribute("save",save);
		int documentCount = 0;
		int itemsCountInAPage = 10;
		int pagesCount = (int) Math.ceil((double) documentCount / itemsCountInAPage);

		
	    if(Type.equals("save")) {
	    	title="임시 저장한 문서";
	    	model.addAttribute("title",title);
	    	model.addAttribute("approvalList",approval_documentService.getA_documentListByRegister(user, st, sk, status,Integer.parseInt(save),itemsCountInAPage,page));
	    	documentCount=approval_documentService.Count1(st, st, sk, status,Integer.parseInt(save));
	    	
	    	pagesCount = (int) Math.ceil((double) documentCount / itemsCountInAPage);
	    	
	    	
	    }
	
		
		if(Type.equals("approval")) {
			title="내가 상신한 문서";
			model.addAttribute("approvalList",approval_documentService.getA_documentListByRegister(user, st, sk, status,Integer.parseInt(save),itemsCountInAPage,page));
			model.addAttribute("title",title);
			documentCount=approval_documentService.Count1(user, st, sk, status,Integer.parseInt(save));
					
			pagesCount = (int) Math.ceil((double) documentCount / itemsCountInAPage);
	
			
		}
		if(Type.equals("approver")) {
		    title="내가 결재할 문서";
			model.addAttribute("approvalList",approval_documentService.getReceiveA_documentListByApprover(user, st, sk, status,itemsCountInAPage,page));
			model.addAttribute("title",title);
			documentCount=approval_documentService.Count2(user, st, sk, status);
			pagesCount = (int) Math.ceil((double) documentCount / itemsCountInAPage);
		}
		
	

		model.addAttribute("form",formService.getFormList());
		model.addAttribute("memberList",memberList);
		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		
		
	}
	@GetMapping("/registForm")
	public void registForm(Model model) {
		model.addAttribute("form",formService.getFormList());
		
	}
	
	
	@PostMapping("/regist")
	public String regist(HttpSession session,String approver1,String approver2,String approver3,String title,String content, String save,String level,String form) {
	
		Member member= (Member)session.getAttribute("loginUser");
		String user=member.getMember_id();
		Approval_Document ad = new Approval_Document();
				

		ad.setApproval_approver1(approver1);
		ad.setApproval_approver2(approver2);
		ad.setApproval_lastapprover(approver3);
		ad.setApproval_content(content);
		ad.setApproval_level(Integer.parseInt(level));
		ad.setApproval_title(title);
		ad.setApproval_save(Integer.parseInt(save));
		ad.setApproval_status(0);
		ad.setForm_id(Integer.parseInt(form));
		ad.setApproval_register(user);
		
		approval_documentService.insertA_document(ad);
		
		return "/approval/registSuccess";
	}
	@GetMapping("/detail")
	public void detail(Model model,String approval_id) {
		 Approval_Document ap=approval_documentService.getA_document(Integer.parseInt(approval_id));
		 Member member= memberService.getMemberById(ap.getApproval_register());
         
		 Member member1 =memberService.getMemberById(ap.getApproval_approver1());
		 Member member2 =memberService.getMemberById(ap.getApproval_approver2());
		 Member member3 =memberService.getMemberById(ap.getApproval_lastapprover());
		 
		List<Approval> approvers = approvalService.getApprovalListByApproval_id(Integer.parseInt(approval_id));
		 
		 model.addAttribute("approvers",approvers);
		 model.addAttribute("member1",member1);
		 model.addAttribute("member2",member2);
		 model.addAttribute("member3",member3);
		 model.addAttribute("member",member);
		
		 model.addAttribute("ap",ap);
		
	}
	@PostMapping("/approvalRegist")
	public String approvalRegist(Model model,String approver,String status,String approval_id,String comment) {
		
		Approval approval = new Approval(); 
		approval.setA_approver(approver);
		approval.setA_comment(comment);
		approval.setA_status(Integer.parseInt(status));
		approval.setApproval_id(Integer.parseInt(approval_id));
		approvalService.insertApproval(approval);
		int approval_status =0;
		
		Approval_Document ap = approval_documentService.getA_document(Integer.parseInt(approval_id));
		
		if(ap.getApproval_lastapprover().equals(approver) && status.equals("0")) {
			approval_status=1;
			approval_documentService.updateA_status(Integer.parseInt(approval_id), approval_status);
		}
		if(status.equals("1")) {
			approval_status=2;
			approval_documentService.updateA_status(Integer.parseInt(approval_id), approval_status);
		}
		
		model.addAttribute("approval_id",approval_id);
			   
		return "/approval/approvalRegistSuccess";
	}

	@GetMapping("/sendNameList")
	@ResponseBody
	public List<Member> sendNameList(Model model, String teamname1) {
	    if (teamname1 == null) {
	        teamname1 = "";
	    }
	    List<Member> nameList = memberService.getMembersNameByteam(teamname1);
	    
	    
	   return nameList;
	}

}
