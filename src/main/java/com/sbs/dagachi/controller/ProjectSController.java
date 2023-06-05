package com.sbs.dagachi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.dagachi.service.PMService;
import com.sbs.dagachi.service.PSService;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.PS;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProjectSController {
	private PSService pSService;
	private PMService pMService;

	public ProjectSController(PSService pSService,PMService pMService ) {
		this.pSService=pSService;
		this.pMService=pMService;
	}
	
	
	
	@RequestMapping("dagachi/projectS/list")
	public String showPsMain(HttpSession session, Model model) {
		Member loginUser=(Member)session.getAttribute("loginUser");
		String loginId=loginUser.getMember_id();
		
		List<PS>pSWaitList=pSService.getPSListWait(loginId);
		List<PS>pSdoingList=pSService.getPSdoingList(loginId);
		List<PS>pScompleteList=pSService.getPScompleteList(loginId);
		List<PS>pSCancelList=pSService.getPScancelList(loginId);
		
		model.addAttribute("pSWaitList", pSWaitList);
		model.addAttribute("pSdoingList", pSdoingList);
		model.addAttribute("pScompleteList", pScompleteList);
		model.addAttribute("pSCancelList", pSCancelList);
		
		return "projectS/projectS_list";
	}
	
	@RequestMapping("dagachi/projectS/modify")
	public String modify(String stat, String ps_id, HttpSession session, Model model) {
		pSService.modifyPs(stat, ps_id);
		int ps_idInt=Integer.parseInt(ps_id);
		PS ps=pSService.getPSByPsId(ps_idInt);
		
		int pm_id=ps.getPm_Id();
		pMService.modifyStatus(stat,pm_id);
		
		Member loginUser=(Member)session.getAttribute("loginUser");
		String loginId=loginUser.getMember_id();
		
		List<PS>pSWaitList=pSService.getPSListWait(loginId);
		List<PS>pSdoingList=pSService.getPSdoingList(loginId);
		List<PS>pScompleteList=pSService.getPScompleteList(loginId);
		List<PS>pSCancelList=pSService.getPScancelList(loginId);
		
		model.addAttribute("pSWaitList", pSWaitList);
		model.addAttribute("pSdoingList", pSdoingList);
		model.addAttribute("pScompleteList", pScompleteList);
		model.addAttribute("pSCancelList", pSCancelList);
		
		
		return "projectS/resultPS";
	}
	
	@RequestMapping("dagachi/projectS/remove")
	public String remove(String ps_id, HttpSession session, Model model) {
		int ps_idInt=Integer.parseInt(ps_id);
		pSService.removePS(ps_id);
		Member loginUser=(Member)session.getAttribute("loginUser");
		String loginId=loginUser.getMember_id();
		
		List<PS>pSWaitList=pSService.getPSListWait(loginId);
		List<PS>pSdoingList=pSService.getPSdoingList(loginId);
		List<PS>pScompleteList=pSService.getPScompleteList(loginId);
		List<PS>pSCancelList=pSService.getPScancelList(loginId);
		
		model.addAttribute("pSWaitList", pSWaitList);
		model.addAttribute("pSdoingList", pSdoingList);
		model.addAttribute("pScompleteList", pScompleteList);
		model.addAttribute("pSCancelList", pSCancelList);
		
		return "projectS/resultPS";
	}
	
	
	
	
	
	
}
