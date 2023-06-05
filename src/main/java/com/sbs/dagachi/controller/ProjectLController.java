package com.sbs.dagachi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.dagachi.service.MemberService;
import com.sbs.dagachi.service.PMService;
import com.sbs.dagachi.service.ProjectLService;
import com.sbs.dagachi.service.TeamService;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.PM;
import com.sbs.dagachi.vo.ProjectL;
import com.sbs.dagachi.vo.TeamVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProjectLController {
	private ProjectLService projectLService;
	private TeamService teamService;
	private MemberService memberService;
	private PMService pMService;
	
	public ProjectLController(ProjectLService projectLService, TeamService teamService, MemberService memberService, PMService pMService) {
		this.projectLService=projectLService;
		this.teamService=teamService;
		this.memberService=memberService;
		this.pMService=pMService;
	}
	
	
	
	@RequestMapping("dagachi/projectL/list")
	public String showProjectL(Model model, HttpSession session) {
		Member loginUser=(Member)session.getAttribute("loginUser");
		String userId=loginUser.getMember_id();
		
		int dep=memberService.getMemberById(userId).getMember_department();//부서를 스트링으로 받아오기
		int depInt=dep;
		int memberAuth=memberService.getMemberById(userId).getMember_auth();//권한 인트값으로 받아오기 2=임원 1=직원
		List<TeamVO>teamlist=teamService.getTeamListByDep(depInt);
		
		model.addAttribute("teamlist", teamlist);
		model.addAttribute("memberAuth", memberAuth);
		
		return "projectL/projectL_list";
	}
	
	@RequestMapping("dagachi/projectL/moveAnotherTeamForm")
	public String moveAnotherTeamForm(int pl_id, Model model,HttpSession session) {
		//피엘 아이디를 통해서 해당 부서를 알아야함
		String teamName=projectLService.getTeamName(pl_id);
		List<ProjectL>plList=projectLService.getPLlistByTeam(teamName);//이거는 해당팀이 무슨 프로젝트를 진행중인지 해당 pl을 갖고온다.
		
		
		
		
		Member loginUser=(Member)session.getAttribute("loginUser");
		String userId=loginUser.getMember_id();
		int dep=memberService.getMemberById(userId).getMember_department();//부서를 스트링으로 받아오기
		int depInt=dep;
		List<TeamVO>teamlist=teamService.getTeamListByDep(depInt);
		
		model.addAttribute("teamlist", teamlist);
		
		model.addAttribute("plList", plList);
		model.addAttribute("teamName", teamName);
		return"projectL/moveAnotherTeam";
	}
	
	
	@RequestMapping("dagachi/projectL/plList")
	public String showResultList(Model model, String teamName,HttpSession session) {
		List<ProjectL>plList=projectLService.getPLlistByTeam(teamName);
		for(ProjectL pl:plList) {
			int pl_id=pl.getPl_Id();
			List<PM>pmList=pMService.getPMListByPLId(pl_id);
			for(PM pm:pmList) {
				String pm_manager=pm.getPm_manager();
				if(pm_manager==null||pm_manager==""||pm_manager.isEmpty()) {
					System.out.println("없음");
				}else {
					System.out.println(pm_manager);
					String name=memberService.getMemberById(pm_manager).getMember_name();
					pm.setExtra_pm_manager_name(name);
				}
			}
			pl.setExtra_pmList(pmList);
		}
		
		Member loginUser=(Member)session.getAttribute("loginUser");
		String userId=loginUser.getMember_id();
		int dep=memberService.getMemberById(userId).getMember_department();//부서를 스트링으로 받아오기
		int depInt=dep;
		List<TeamVO>teamlist=teamService.getTeamListByDep(depInt);
		
		model.addAttribute("teamlist", teamlist);
		model.addAttribute("plList", plList);
		
		return "projectL/resultPLlist";
		
		
	}
	
	@RequestMapping("dagachi/projectL/registForm")
	public String showRegistForm(HttpSession session,Model model) {
		Member loginUser=(Member)session.getAttribute("loginUser");
		String pl_register=loginUser.getMember_id();
		int dep=memberService.getMemberById(pl_register).getMember_department();//부서를 스트링으로 받아오기
		int depInt=dep;
		List<TeamVO>teamlist=teamService.getTeamListByDep(depInt);
		model.addAttribute("teamlist", teamlist);
		return "projectL/registForm";
	}
	
	@RequestMapping("dagachi/projectL/regist")
	public String plRegist(HttpSession session, String pl_name,  @DateTimeFormat(pattern="yyyy-MM-dd")Date pl_endDate, 
			String pl_manager, String pl_team, @RequestParam(value="pl_bodyArr")List<String>pl_bodyArr,Model model) {
		Member loginUser=(Member)session.getAttribute("loginUser");
		String pl_register=loginUser.getMember_id();
		
		
		
		
		
		
		projectLService.registPL(pl_name, pl_endDate, pl_register, pl_manager, pl_team);
		int last_PL=projectLService.getLastInsertedId();
		for(String pm_detail:pl_bodyArr) {
			pMService.registPM(pm_detail, pl_endDate, pl_register, "", last_PL);
		}
		return showResultList(model, pl_team, session);
	}
	
	
	@RequestMapping("dagachi/projectL/removePl")
	@ResponseBody
	public int removePl(String[] selectArr) {
		for(String str:selectArr) {
			int pl_id=Integer.parseInt(str);
			projectLService.removePl(pl_id);
		}
		//System.out.println(selectArr);
		
		return 1;
	}
	
	@RequestMapping("dagachi/projectL/movePl")
	@ResponseBody
	public int movePl(String[]selectArr,String team_name) {
		for(String str:selectArr) {
			int pl_id=Integer.parseInt(str);
			projectLService.moveAnotherTeam(pl_id, team_name);
		}
		
		return 1;
	}
	
}
