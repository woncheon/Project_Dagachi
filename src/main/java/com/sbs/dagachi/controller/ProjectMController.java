package com.sbs.dagachi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.dagachi.service.MemberService;
import com.sbs.dagachi.service.NotificationService;
import com.sbs.dagachi.service.PMService;
import com.sbs.dagachi.service.PSService;
import com.sbs.dagachi.service.ProjectLService;
import com.sbs.dagachi.service.TeamService;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.PM;
import com.sbs.dagachi.vo.PS;
import com.sbs.dagachi.vo.ProjectL;
import com.sbs.dagachi.vo.TeamVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProjectMController {
	private MemberService memberService;
	private ProjectLService projectLService;
	private TeamService teamService;
	private PMService pMService;
	private PSService pSService;
	private NotificationService notificationService;

	public ProjectMController(MemberService memberService,ProjectLService projectLService, TeamService teamService, PMService pMService,PSService pSService,NotificationService notificationService) {
		this.memberService=memberService;
		this.projectLService=projectLService;
		this.teamService=teamService;
		this.pMService=pMService;
		this.pSService=pSService;
		this.notificationService=notificationService;
	}
	
	
	@RequestMapping("dagachi/projectM/list")
	public String showMList(HttpSession session, Model model) {
		Member loginUser=(Member)session.getAttribute("loginUser");
		int teamCode=loginUser.getMember_team();
		List<Member>memberList=memberService.getMemberListByTeam(teamCode);
		TeamVO team=teamService.getTeamByCode(teamCode);
		List<ProjectL>plList=projectLService.getPLlistByTeam(team.getTeam_name());
		model.addAttribute("teamName",team.getTeam_name());
		model.addAttribute("memberList", memberList);
		model.addAttribute("plList", plList);
		
		
		return "projectM/projectM_list";
	}
	
	@RequestMapping("dagachi/projectM/resultPL")
	public String resultPL(Model model,HttpSession session) {
		Member loginUser=(Member)session.getAttribute("loginUser");
		int teamCode=loginUser.getMember_team();
		
		TeamVO team=teamService.getTeamByCode(teamCode);
		List<ProjectL>plList=projectLService.getPLlistByTeam(team.getTeam_name());
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
		
		model.addAttribute("pMService", pMService);
		model.addAttribute("plList", plList);
		return "projectM/resultPLlist";
	}
	
	@RequestMapping("dagachi/projectM/resultPM")
	public String resultPM(Model model, String member_id){
		
		  //큰 분류 갖고오기 
		  List<PM>PLListForMember=pMService.getPLlist(member_id);//자신에게 해당되는 PM중 PL이중복되지 않게 갖고오기 이거 길이 만큼 오른쪽 섹션에서 반복함
		  //List<Integer>pl_IdList=new ArrayList<Integer>();
		  List<ProjectL>plList=new ArrayList<ProjectL>();
		  
		  for(PM distinctPM:PLListForMember) {
			  int pl_id=distinctPM.getPl_Id();
			  //pl_IdList.add(pl_id);//이렇게 하면 갖고온 pl들의 아이디를 갖고온다.
			  ProjectL pl=projectLService.getPLByPLId(pl_id);
			  List<PM>pmList=pMService.getPMList(member_id);
			  pl.setExtra_pmList(pmList);
			  plList.add(pl); //pl들의 리스트를 갖고왔다.
		  }
		  
		  model.addAttribute("plList", plList);
		  
		  
		  
		
		return "projectM/resultPMlist";
	}
	
	
	
	@RequestMapping("dagachi/projectM/modify")
	public String modify(Model model, String member_id, int[] pm_ids,HttpSession session) {
		
		Member loginUser=(Member)session.getAttribute("loginUser");
		
		
		for(int pm_id:pm_ids) {
			pMService.modifyKSPM(member_id, pm_id);
			PM pm=pMService.getPMByPmId(pm_id);
			List<PS>psList=pSService.getPSListByPmId(pm_id);
			if(psList.size()!=0) {
				for(PS ps:psList) {
					pSService.modifyPs(3,ps.getPs_Id());
				}
			}
			pSService.registPs(pm.getPm_name(), 
					loginUser.getMember_id(), member_id, pm.getPm_startDate(), pm.getPm_endDate(),0,pm.getPm_Id());
			//pSService.modifyPs(3,ps.getPs_Id());
			notificationService.registNotification("새 업무가 등록되었습니다.", member_id, "/dagachi/projectS/list", loginUser.getMember_id());
			
//			if(ps==null) {
//				pSService.registPs(pm.getPm_name(), 
//						loginUser.getMember_id(), member_id, pm.getPm_startDate(), pm.getPm_endDate(),0,pm.getPm_Id());
//			}else {
//				pSService.modifyPsUser(ps.getPs_Id(), member_id);
//			}
			
		}
		
		return resultPM(model, member_id);
	}
	
	

}
