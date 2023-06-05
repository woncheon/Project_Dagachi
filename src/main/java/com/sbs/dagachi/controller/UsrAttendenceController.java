package com.sbs.dagachi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.dagachi.service.AttendenceService;
import com.sbs.dagachi.service.MemberService;
import com.sbs.dagachi.service.VacationService;
import com.sbs.dagachi.vo.Attendence;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.Vacation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrAttendenceController {

	public AttendenceService attendenceService;
	public VacationService vacationService;
	public MemberService memberService;

	public UsrAttendenceController(AttendenceService attendenceService, VacationService vacationService,
			MemberService memberService) {
		this.attendenceService = attendenceService;
		this.vacationService = vacationService;
		this.memberService = memberService;

	}

	@RequestMapping("/attendence/departmentlist")
	public String showAttendence(Model model, @RequestParam(defaultValue = "1") int attendence_id,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "attendence_status,attendence_regDate,member_department,member_team") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword) {
		int attendenceCount = attendenceService.getDepartmentTotalCount(attendence_id, searchKeywordTypeCode,
				searchKeyword);
		int itemsCountInAPage = 10;
		int pagesCount = (int) Math.ceil((double) attendenceCount / itemsCountInAPage);
		
		

		List<Map<String, Object>> attendence = attendenceService.getDepartmentTotal();

		List<Map<String, Object>> departments = new ArrayList<>();
		List<Map<String, Object>> totalCounts = attendenceService.getDepartmentTotal();

		for (Map<String, Object> count : totalCounts) {
			Map<String, Object> department = new HashMap<>();
			department.put("name", count.get("department"));
			department.put("totalCount", count.get("total_count"));
			departments.add(department);
		}

		List<Map<String, Object>> todaytotal = attendenceService.getDepartmentTodayTotal(searchKeywordTypeCode,
				searchKeyword, itemsCountInAPage, page);
		List<Map<String, Object>> todayDepartments = new ArrayList<>();
		List<Map<String, Object>> todayTotalCounts = attendenceService.getDepartmentTodayTotal(searchKeywordTypeCode,
				searchKeyword, itemsCountInAPage, page);
		for (Map<String, Object> totalcount : todayTotalCounts) {
			Map<String, Object> todaydepartment = new HashMap<>();
			todaydepartment.put("name", totalcount.get("department"));
			todaydepartment.put("total_count", totalcount.get("total_count"));
			todaydepartment.put("attendence_regdate", totalcount.get("attendence_regdate"));
			todayDepartments.add(todaydepartment);
		}

		model.addAttribute("totalCounts", totalCounts);
		model.addAttribute("departments", departments);
		model.addAttribute("attendence", attendence);
		model.addAttribute("todaytotal", todaytotal);
		model.addAttribute("todayDepartments", todayDepartments);
		model.addAttribute("todayTotalCounts", todayTotalCounts);
		model.addAttribute("attendence_id", attendence_id);
		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("attendenceCount", attendenceCount);

		return "attendence/departmentlist";
	}

	@RequestMapping("/attendence/departmentdetail")
	public String showDepartment(Model model, @RequestParam(defaultValue = "1") int attendence_id,
			int member_department, @RequestParam(defaultValue = "1") int attendence_member,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "attendence_regDate,member_department,member_team") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword) {

		List<Member> memberdepartmentname = memberService.getDepartmentRepresentative(member_department);
		
		
		int attendenceCount = attendenceService.getDepartmentTotalCount(attendence_id, searchKeywordTypeCode,
				searchKeyword);
		int itemsCountInAPage = 10;
		int pagesCount = (int) Math.ceil((double) attendenceCount / itemsCountInAPage);

		List<Map<String, Object>> todaytotal = attendenceService.getTeamTodayTotal(member_department, itemsCountInAPage,
				page, searchKeywordTypeCode, searchKeyword);

		List<Map<String, Object>> todayTeams = new ArrayList<>();
		List<Map<String, Object>> todayTotalCounts = attendenceService.getTeamTodayTotal(member_department,
				itemsCountInAPage, page, searchKeywordTypeCode, searchKeyword);

		for (Map<String, Object> totalcount : todayTotalCounts) {
			Map<String, Object> todaydepartment = new HashMap<>();
			todaydepartment.put("name", totalcount.get("department"));
			todaydepartment.put("team", totalcount.get("team"));
			todaydepartment.put("attendence_regdate", totalcount.get("attendence_regdate"));
			todaydepartment.put("team_count", totalcount.get("team_count"));
			todayTeams.add(todaydepartment);
		}

		List<Map<String, Object>> attendence = attendenceService.getTeamtotal(member_department);

		List<Map<String, Object>> Teams = new ArrayList<>();
		List<Map<String, Object>> TotalCounts = attendenceService.getTeamtotal(member_department);

		for (Map<String, Object> Teamcount : TotalCounts) {
			Map<String, Object> Team = new HashMap<>();
			Team.put("name", Teamcount.get("department"));
			Team.put("team", Teamcount.get("team"));
			Team.put("total", Teamcount.get("total"));
			Team.put("team_count", Teamcount.get("team_count"));
			Teams.add(Team);
		}

		List<Map<String, Object>> totalwork = attendenceService.getTotalWorkDay(attendence_member);

		List<Map<String, Object>> totalworks = new ArrayList<>();
		List<Map<String, Object>> totalworkcounts = attendenceService.getTotalWorkDay(attendence_member);

		for (Map<String, Object> workcount : totalworkcounts) {
			Map<String, Object> todaytotalwork = new HashMap<>();
			todaytotalwork.put("team_id", workcount.get("team_id"));
			todaytotalwork.put("total_work_days", workcount.get("total_work_days"));
			totalworks.add(todaytotalwork);
		}
		int totayStatusTotalCount = attendenceService.getTodayTeamStatusCount(member_department);
		int todaystatus7TotalCount = attendenceService.getTodayTeamStatus7Count(member_department);
		int totalTeamCount = attendenceService.getTeamTotalCount(member_department);
		int todaystatus5TotalCount = attendenceService.getTodayStatus5Count(member_department);
		int todayStatus4TotalCount = attendenceService.getTodaystatus4Count(member_department);
		  
		LocalDate startDate = LocalDate.now().withDayOfMonth(1);
		LocalDate today = LocalDate.now();

		int weekdaysCount = 0;

		while (!startDate.isAfter(today)) {
		    DayOfWeek dayOfWeek = startDate.getDayOfWeek();
		    if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
		        weekdaysCount++;
		    }
		    startDate = startDate.plusDays(1);
		}
		
		model.addAttribute("todaytotal", todaytotal);
		model.addAttribute("todayTeams", todayTeams);
		model.addAttribute("attendence", attendence);
		model.addAttribute("Teams", Teams);
		model.addAttribute("TotalCounts", TotalCounts);
		model.addAttribute("totalwork", totalwork);
		model.addAttribute("totalworks", totalworks);
		model.addAttribute("totalworkcounts", totalworkcounts);
		model.addAttribute("attendence_member", attendence_member);
		model.addAttribute("totayStatusTotalCount", totayStatusTotalCount);
		model.addAttribute("todaystatus7TotalCount", todaystatus7TotalCount);
		model.addAttribute("totalTeamCount", totalTeamCount);
		model.addAttribute("todaystatus5TotalCount", todaystatus5TotalCount);
		model.addAttribute("todayStatus4TotalCount", todayStatus4TotalCount);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("memberdepartmentname",memberdepartmentname);
		model.addAttribute("weekdaysCount",weekdaysCount);
		
		return "/attendence/departmentdetail";
	}

	@RequestMapping("/attendence/teamlist")
	public String showTeamAttendence(HttpSession session, Model model, @RequestParam(defaultValue = "1") int attendence_id,
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "attendence_regDate,member_department,member_team") String searchKeywordTypeCode,
	        @RequestParam(defaultValue = "") String searchKeyword, @RequestParam(required = false) Integer member_department) {

	    Member loginUser = (Member) session.getAttribute("loginUser");
	    member_department = loginUser.getMember_department();
		
		int attendenceCount = attendenceService.getTeamlistCount(attendence_id, searchKeywordTypeCode, searchKeyword);
		int itemsCountInAPage = 10;
		int pagesCount = (int) Math.ceil((double) attendenceCount / itemsCountInAPage);
	
		List<Map<String, Object>> todaytotal = attendenceService.getTeamTodayTotal(member_department, itemsCountInAPage,
				page, searchKeywordTypeCode, searchKeyword);

		List<Map<String, Object>> todayTeams = new ArrayList<>();
		List<Map<String, Object>> todayTotalCounts = attendenceService.getTeamTodayTotal(member_department,
				itemsCountInAPage, page, searchKeywordTypeCode, searchKeyword);

		for (Map<String, Object> totalcount : todayTotalCounts) {
			Map<String, Object> todaydepartment = new HashMap<>();
			todaydepartment.put("name", totalcount.get("department"));
			todaydepartment.put("team", totalcount.get("team"));
			todaydepartment.put("attendence_regdate", totalcount.get("attendence_regdate"));
			todaydepartment.put("team_count", totalcount.get("team_count"));
			todayTeams.add(todaydepartment);
		}

		List<Map<String, Object>> attendence = attendenceService.getTeamtotal(member_department);

		List<Map<String, Object>> Teams = new ArrayList<>();
		List<Map<String, Object>> TotalCounts = attendenceService.getTeamtotal(member_department);

		for (Map<String, Object> Teamcount : TotalCounts) {
			Map<String, Object> Team = new HashMap<>();
			Team.put("name", Teamcount.get("department"));
			Team.put("team", Teamcount.get("team"));
			Team.put("total", Teamcount.get("total"));
			Team.put("team_count", Teamcount.get("team_count"));
			Teams.add(Team);
		}

		model.addAttribute("todaytotal", todaytotal);
		model.addAttribute("todayTeams", todayTeams);
		model.addAttribute("attendence", attendence);
		model.addAttribute("Teams", Teams);
		model.addAttribute("TotalCounts", TotalCounts);
		model.addAttribute("attendence_id", attendence_id);
		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("attendenceCount", attendenceCount);

		return "/attendence/teamlist";
	}

	@RequestMapping("/attendence/teamdetail")
	public String showteamdetail(Model model ,@RequestParam(defaultValue = "1") int attendence_id,@RequestParam(defaultValue = "2")int member_department,int member_team,String attendence_regDate ,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "attendence_status,attendence_regDate") String searchKeywordTypeCode,
	        @RequestParam(defaultValue = "") String searchKeyword) {
	
		int attendenceCount = attendenceService.getDepartmentTotalCount(attendence_id, searchKeywordTypeCode, searchKeyword);
	    int itemsCountInAPage = 10;
	    int pagesCount = (int) Math.ceil((double) attendenceCount / itemsCountInAPage);
	    List<Member> memberdepartmentname = memberService.getDepartmentRepresentative(member_department);
		
		List<Map<String,Object>> attendence = attendenceService.getTeamtotal(member_department);
		 
		 List<Map<String, Object>> Teams = new ArrayList<>();
		 List<Map<String, Object>> TotalCounts =  attendenceService.getTeamtotal(member_department);

		 for (Map<String, Object> Teamcount : TotalCounts) {
		        Map<String, Object> Team = new HashMap<>();
		        Team.put("name", Teamcount.get("department"));
		        Team.put("team", Teamcount.get("team"));
		        Team.put("total", Teamcount.get("total"));
		        Team.put("team_count", Teamcount.get("team_count"));
		        Teams.add(Team);
		    }
		 
		 List<Map<String, Object>> todaytotal = attendenceService.getTeamTodayTotal(member_department, itemsCountInAPage, pagesCount, searchKeywordTypeCode, searchKeyword);
			
		 List<Map<String, Object>> todayTeams = new ArrayList<>();
		 List<Map<String, Object>> todayTotalCounts = attendenceService.getTeamTodayTotal(member_department, itemsCountInAPage, pagesCount, searchKeywordTypeCode, searchKeyword);

		 for (Map<String, Object> totalcount : todayTotalCounts) {
		  Map<String, Object> todaydepartment = new HashMap<>();
		  todaydepartment.put("name", totalcount.get("department"));
		  todaydepartment.put("team", totalcount.get("team"));
		  todaydepartment.put("attendence_regdate", totalcount.get("attendence_regdate"));
		  todaydepartment.put("team_count", totalcount.get("team_count"));
		  todayTeams.add(todaydepartment);
		 }
		 
		List<Map<String,Object>>Teamdetail = attendenceService.getTeamDetail( member_department, member_team,attendence_regDate);
		 List<Map<String, Object>> TeamDetails = new ArrayList<>();
		 List<Map<String, Object>> todayTeamDetails= attendenceService.getTeamDetail(member_department,member_team,attendence_regDate);
		 
		 for (Map<String, Object> todayteamdetail : todayTeamDetails) {
			  Map<String, Object> teamdetail = new HashMap<>();
			  teamdetail.put("member_name", todayteamdetail.get("member_name"));
			  teamdetail.put("member_rank", todayteamdetail.get("member_rank"));
			  teamdetail.put("attendence_regdate", todayteamdetail.get("attendence_regdate"));
			  teamdetail.put("attendence_status", todayteamdetail.get("attendence_status"));
			  teamdetail.put("attendence_reason", todayteamdetail.get("attendence_reason"));
			  TeamDetails.add(teamdetail);
			 }
		
		 
		 model.addAttribute("attendence", attendence);
		 model.addAttribute("Teams", Teams);
		 model.addAttribute("TotalCounts", TotalCounts);
		 model.addAttribute("todaytotal", todaytotal);
		 model.addAttribute("todayTeams", todayTeams);
		 model.addAttribute("Teamdetail", Teamdetail);
		 model.addAttribute("TeamDetails", TeamDetails);
		 model.addAttribute("todayTeamDetails", todayTeamDetails);
		 model.addAttribute("memberdepartmentname",memberdepartmentname);
		
		return "/attendence/teamdetail";
	}

	@RequestMapping("/attendence/teamdetailgetmember")
	public String showteamdetailmember(Model model, String member_name, String attendence_regDate) {
		
		
		List<Member> member = attendenceService.getMembername(member_name);
		int TotalStatus1 = attendenceService.getMemberNameTotalStatus(member_name);
		int TotalStatus7Count = attendenceService.getMemberNameTotalStatus7Count(member_name);
		int TotalStatus5Count = attendenceService.getMemberNameTotalStatus5Count(member_name);

		List<Map<String, Object>> memberTotalList = attendenceService.getMemberNameTotalList(member_name);
		List<Map<String, Object>> getMemberLists = new ArrayList<>();
		List<Map<String, Object>> todayMemberList = attendenceService.getMemberNameTotalList(member_name);

		for (Map<String, Object> todaymember : todayMemberList) {
			Map<String, Object> getMemberList = new HashMap<>();
			getMemberList.put("attendence_regdate", todaymember.get("attendence_regdate"));
			getMemberList.put("출근시간", todaymember.get("출근시간"));
			getMemberList.put("퇴근시간", todaymember.get("퇴근시간"));
			getMemberList.put("근태상태", todaymember.get("근태상태"));
			getMemberLists.add(getMemberList);
		}

		model.addAttribute("member", member);
		model.addAttribute("TotalStatus1", TotalStatus1);
		model.addAttribute("TotalStatus7Count", TotalStatus7Count);
		model.addAttribute("TotalStatus5Count", TotalStatus5Count);
		model.addAttribute("memberTotalList", memberTotalList);
		model.addAttribute("getMemberLists", getMemberLists);
		model.addAttribute("todayMemberList", todayMemberList);
		return "/attendence/teamdetailgetmember";
	}

	@RequestMapping("/attendence/list")
	public String showAttendence(HttpSession session, HttpServletRequest request, Model model,
			@RequestParam(defaultValue = "1") int attendence_id, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "attendence_status,attendence_regDate") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword, String attendence_member,
			@RequestParam(defaultValue = "") String vacation_member) {

		int attendenceCount = attendenceService.getMyAttendenceCount(attendence_id, searchKeywordTypeCode,
				searchKeyword, attendence_member);
		int itemsCountInAPage = 10;
		int pagesCount = (int) Math.ceil((double) attendenceCount / itemsCountInAPage);

		// Retrieve the loginUser value from Model or HttpSession
		Member loginUser = (Member) session.getAttribute("loginUser"); // Assuming it is stored in the session

		vacation_member = loginUser.getMember_id();// Get the member ID from loginUser

		int vacationYear = vacationService.getTotalYear(vacation_member);
		int vacationMonth = vacationService.getTotalMonth(vacation_member);
		int DelYear = vacationService.getDelYear(vacation_member);
		int DelMonth = vacationService.getDelMonth(vacation_member);

		// attendence_member=user2 리스트를 조회
		List<Attendence> attendences = attendenceService.getForPrintMyAttendence(attendence_id, searchKeywordTypeCode,
				searchKeyword, itemsCountInAPage, page, attendence_member);

		model.addAttribute("attendence_id", attendence_id);
		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("attendenceCount", attendenceCount);
		model.addAttribute("vacationYear", vacationYear);
		model.addAttribute("vacationMonth", vacationMonth);
		model.addAttribute("DelYear", DelYear);
		model.addAttribute("DelMonth", DelMonth);
		model.addAttribute("attendences", attendences);

		return "/attendence/list";
	}

	@RequestMapping("/attendence/mylist")
	public String showMyAttendence(HttpSession session,Model model, @RequestParam(defaultValue = "1") int attendence_id,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "attendence_status,attendence_regDate") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword, String attendence_member, String vacation_member) {

		Member loginUser = (Member) session.getAttribute("loginUser");
		
		vacation_member = loginUser.getMember_id();
		attendence_member = loginUser.getMember_id();
		
		int attendenceCount = attendenceService.getMyAttendenceCount(attendence_id, searchKeywordTypeCode,
				searchKeyword, attendence_member);
		int itemsCountInAPage = 10;
		int pagesCount = (int) Math.ceil((double) attendenceCount / itemsCountInAPage);
		int vacationYear = vacationService.getTotalYear(vacation_member);
		int vacationMonth = vacationService.getTotalMonth(vacation_member);
		int DelYear = vacationService.getDelYear(vacation_member);
		int DelMonth = vacationService.getDelMonth(vacation_member);

		// attendence_member=user2 리스트 조회
		List<Attendence> attendences = attendenceService.getForPrintMyAttendence(attendence_id, searchKeywordTypeCode,
				searchKeyword, itemsCountInAPage, page, attendence_member);

		model.addAttribute("attendence_id", attendence_id);
		model.addAttribute("page", page);
		model.addAttribute("pagesCount", pagesCount);
		model.addAttribute("attendenceCount", attendenceCount);
		model.addAttribute("vacationYear", vacationYear);
		model.addAttribute("vacationMonth", vacationMonth);
		model.addAttribute("DelYear", DelYear);
		model.addAttribute("DelMonth", DelMonth);
		model.addAttribute("attendences", attendences);

		return "/attendence/mylist";
	}

	@RequestMapping("/attendence/vacationdetail")
	public String showVacation(HttpSession session,Model model, String vacation_member) {

		Member loginUser = (Member) session.getAttribute("loginUser");
		
		vacation_member = loginUser.getMember_id();
		
		List<Vacation> vacation = attendenceService.getVacation(vacation_member);

		int vacationYear = vacationService.getTotalYear(vacation_member);
		int vacationMonth = vacationService.getTotalMonth(vacation_member);
		int DelYear = vacationService.getDelYear(vacation_member);
		int DelMonth = vacationService.getDelMonth(vacation_member);

		model.addAttribute("vacationYear", vacationYear);
		model.addAttribute("vacationMonth", vacationMonth);
		model.addAttribute("DelYear", DelYear);
		model.addAttribute("DelMonth", DelMonth);
		model.addAttribute("vacation", vacation);

		return "/attendence/vacationdetail";

	}
	
	@Value(value = "${picturePath}")
	private String picturePath;
	
	@GetMapping("/attendence/getPicture")
	@ResponseBody
	public byte[] getPicture(@RequestParam("id")String member_name) throws Exception {
	   
	   Member member = memberService.getMemberByName(member_name);
	   if(member==null) return null;
	  
	   String picture = member.getMember_pic();
	   String imgPath = this.picturePath;
	   
	   InputStream in = new FileInputStream(new File(imgPath, picture));
	   
	   System.out.println("###############"+member_name);
	   return IOUtils.toByteArray(in);
	
	}
	

}
