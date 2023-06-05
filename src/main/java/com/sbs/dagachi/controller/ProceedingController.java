package com.sbs.dagachi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.dagachi.service.MemberService;
import com.sbs.dagachi.service.ProceedingService;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.Proceeding;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProceedingController {
	private ProceedingService proceedingService;
	private MemberService memberService;
	
	
	public ProceedingController(ProceedingService proceedingService, MemberService memberService) {
		this.proceedingService=proceedingService;
		this.memberService=memberService;
	}


	@RequestMapping("dagachi/proceeding/list")
	public String showList(Model model, String searchType, String keyword,  @RequestParam(defaultValue = "1")int page) {
		List<Proceeding>proceedingImpList=proceedingService.getProceedingImpList();
		if(searchType==null) {
			searchType="";
		}
		if(keyword==null) {
			keyword="";
		}
		
		int proceedingCount=proceedingService.getProceedingCount(searchType,keyword);
		int itemsCountInAPage=7;
		int pagesCount=(int)Math.ceil((double)proceedingCount/itemsCountInAPage);
		
		List<Proceeding>proceedingList=proceedingService.getProceedingList(searchType, keyword, itemsCountInAPage, page);
		model.addAttribute("proceedingImpList", proceedingImpList);
		model.addAttribute("proceedingList", proceedingList);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("phoneBookCount",proceedingCount);
		
		
		return "proceeding/proceeding_list";
	}
	
	@RequestMapping("dagachi/proceeding/regist")
	public String showRegistForm() {
		return "proceeding/registForm";
	}
	
	@RequestMapping("dagachi/proceeding/registSuccess")
	public String registProceeding(String title,String topic,HttpSession session, int type, 
			int scope, String attenders, String body, String result) {
		String register=((Member)session.getAttribute("loginUser")).getMember_id();
		proceedingService.registProceeding(title, topic, register, type, scope, attenders, body, result);
		return "proceeding/registSuccess";
	}
	@RequestMapping("dagachi/proceeding/detail")
	public String showRegistForm(int proceeding_id,Model model) {
		Proceeding proceeding=proceedingService.getProceedingById(proceeding_id);
		Member member=memberService.getMemberById(proceeding.getForm_proceeding_register());
		
		model.addAttribute("proceeding", proceeding);
		model.addAttribute("member", member);
		return "proceeding/detail";
	}
	
	@Value(value = "${picturePath}")
	   private String picturePath;
	
    
	@RequestMapping("dagachi/proceeding/getPicture")
    @ResponseBody
    public byte[] getPicture(String id) throws Exception {
       
       Member member = memberService.getMemberById(id);
       if(member==null) return null;
       
       String picture = member.getMember_pic();
       String imgPath = this.picturePath;
       
       InputStream in = new FileInputStream(new File(imgPath, picture));
       
       
       return IOUtils.toByteArray(in);
    
 }
	
	@RequestMapping("dagachi/proceeding/recommend")
	public String showRecommend(String member_name,Model model) {
		List<Member>memberList=memberService.almostName(member_name);
		model.addAttribute("memberList", memberList);
		return "proceeding/recommend";
	}
	
	@RequestMapping("dagachi/proceeding/authCheck")
	@ResponseBody
	public String authCheck(String att, String register,HttpSession session) {
		String[]attArr=att.split(",");
		Member member=memberService.getMemberById(register);//register 정보
		int team=member.getMember_team();//레지스터의 팀
		List<Member>teamMembers=memberService.getMemberListByTeam(team); //같은 팀 사람들
		
		String data="";
		String loginUser=((Member)session.getAttribute("loginUser")).getMember_id();
		
		for(Member teamMember: teamMembers) {//같은 팀 소속인지 확인
			if(teamMember.getMember_id().equals(loginUser)) {
				data="ok";
				System.out.println("1");
				return data;
			}else {
				System.out.println("2");
				for(String att2:attArr) {
					if(att2==loginUser) {
						data="ok";
					}else {
						data="nooo";
					}
				}
			}
		}
		
		return data;
	}
	
	
	
	
}
