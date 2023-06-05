package com.sbs.dagachi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.dagachi.service.ApprovalService;
import com.sbs.dagachi.service.Approval_DocumentService;
import com.sbs.dagachi.service.ArticleService;
import com.sbs.dagachi.service.BookMarkService;
import com.sbs.dagachi.service.FormService;
import com.sbs.dagachi.service.MemberService;
import com.sbs.dagachi.service.PMService;
import com.sbs.dagachi.service.PSService;
import com.sbs.dagachi.service.ProjectLService;
import com.sbs.dagachi.service.ScheduleService;
import com.sbs.dagachi.vo.Article;
import com.sbs.dagachi.vo.BookMark;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.PM;
import com.sbs.dagachi.vo.PS;
import com.sbs.dagachi.vo.ProjectL;

import jakarta.servlet.http.HttpSession;




@Controller
public class UsrHomeController {
	
	private ScheduleService scheduleService;
	
	private ArticleService articleService;
	
	private MemberService memberService;

	private PSService psService;
	
	private ProjectLService projectLService;
	
	private PMService pMService;
	
	private BookMarkService bookMarkService;
	
	private ApprovalService approvalService;
	
	private Approval_DocumentService approval_documentService;
	
	private FormService formService;

	
	public UsrHomeController(FormService formService,Approval_DocumentService approval_documentService,BookMarkService bookMarkService,ScheduleService scheduleService,ArticleService articleService, MemberService memberService,PSService psService,ProjectLService projectLService,PMService pMService) {
		this.scheduleService = scheduleService;
		this.articleService =articleService;
		this.memberService = memberService;
		this.psService=psService;
		this.projectLService=projectLService;
		this.pMService=pMService;
		this.bookMarkService=bookMarkService;
		this.formService=formService;
		this.approval_documentService=approval_documentService;
	}
	
	

	
@RequestMapping("/usr/home/main")
	
	public String showBoard1(HttpSession session,Model model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "article_title,article_impotrent,article_register") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword){
		String loginUser=((Member)session.getAttribute("loginUser")).getMember_id();
		int save=1;
		int articleCount = articleService.getArticleboardId1Count(searchKeywordTypeCode, searchKeyword);
		int itemsCountInAPage = 5;
		int pagesCount = (int) Math.ceil((double) articleCount / itemsCountInAPage);
		
		List<Article> borad1 = articleService.getBoardId1(searchKeywordTypeCode, searchKeyword, itemsCountInAPage, page);
		
		model.addAttribute("borad1",borad1);
		model.addAttribute("articleCount",articleCount);
		model.addAttribute("page",page);
		model.addAttribute("pagesCount",pagesCount);
		
		
		//받은 업무 섹션 시작
		List<PS>psList=psService.getPSListWait(loginUser);
		model.addAttribute("psList", psList);
		//받은 업무 섹션 끝
		
		//업무 진척도 시작
		String teamName=((Member)session.getAttribute("loginUser")).getMember_team1();
		
		List<ProjectL>plList=projectLService.getPLlistByTeam(teamName);//로그인한 유저의 팀의 pl가져오기
		for(ProjectL pl:plList) {
			int plProgress=0;
			List<PM>pmList=pMService.getPMListByPLId(pl.getPl_Id());
			double pmSize=pmList.size();//해당 pl에 속해있는 총 pm의 갯수
			int completeSize=0;
			for(PM pm:pmList) {
				if(pm.getPm_status()==2) {
					completeSize++;
				}
			}
			plProgress= (int)(completeSize/pmSize*100.0);
			
			pl.setPlProgress(plProgress);
		}
		model.addAttribute("plList", plList);
		
		//업무 진척도 끝
		
		
		
		String st="";
		String sk="";
	
		String status="";
		
		
		model.addAttribute("memberList",memberService.getMember());
		model.addAttribute("form",formService.getFormList());
		model.addAttribute("approvalList",approval_documentService.getA_documentListByRegister(loginUser,st,sk,status,save,itemsCountInAPage, page));
		model.addAttribute("approverList", approval_documentService.getReceiveA_documentListByApprover(loginUser,st,sk,status,itemsCountInAPage,page));
		
		
		
		
		
		
		
		return "/usr/home/main";
		
	
	}
	
	
@RequestMapping("/usr/home/favList")
public String showFavList(HttpSession session,Model model) {
   String register=((Member)session.getAttribute("loginUser")).getMember_id();
   List<BookMark>favList=bookMarkService.getFavLIst(register);
   
   model.addAttribute("favList", favList);
   
   return "include/favList";
   
}

@RequestMapping("/usr/home/registFav")
@ResponseBody
public String registFav(HttpSession session, String mcode, String pageTitle) {
   String register=((Member)session.getAttribute("loginUser")).getMember_id();
   BookMark dup=bookMarkService.dupCheck(register, mcode);
   String msg="";
   if(dup==null) {
      bookMarkService.registFav(register, mcode,pageTitle);
      msg="ok";
   }else {
      bookMarkService.deleteFav(register,mcode);
      msg="no";
   }
   
   
   return msg;
   
}

@RequestMapping("usr/home/dupCheck")
@ResponseBody
public String dupCheck(String mcode, HttpSession session) {
   String register=((Member)session.getAttribute("loginUser")).getMember_id();
   BookMark dup=bookMarkService.dupCheck(register, mcode);
   if(dup==null) {
      return "ok";
   }else {
      return "no";
   }
   
   
   
   
}

	
	
	
	
	
	
	  
}
