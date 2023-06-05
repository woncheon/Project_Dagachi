package com.sbs.dagachi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.dagachi.service.ScheduleService;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.ScheduleVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class ScheduleController {
	private ScheduleService scheduleService;
	
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	


	   
	   /////////////개인일정
	   
	   //jsp
	   @RequestMapping("/schedule/personalCal")
		public String getPersonalCalendar() {
			return "schedule/personalCal";
		}
	   	
	   //fullCalendar에서 가져올 달력
	   @RequestMapping("/getPersonalCalendar")
	   @ResponseBody
	   public List<Map<String, Object>> getPersonalCalendar(HttpSession session) {
		   Member loginUser = (Member) session.getAttribute("loginUser");
	       String regist_member = loginUser.getMember_id();
		   
	       List<ScheduleVO> list = scheduleService.getPersonalSchedule(regist_member);
	       List<Map<String, Object>> scheduleDataJson = new ArrayList<>();
	       for (ScheduleVO item : list) {
	           Map<String, Object> scheduleJson = new HashMap<>();
	           scheduleJson.put("schedule_code", item.getSchedule_Code());
	           scheduleJson.put("title", item.getSchedule_Name());
	           scheduleJson.put("content", item.getSchedule_Body());
	           scheduleJson.put("start", item.getSchedule_StartDate());
	           scheduleJson.put("end", item.getSchedule_EndDate());
	           scheduleJson.put("regdate", item.getSchedule_RegDate());
	           scheduleJson.put("register", item.getSchedule_Register());
	           scheduleDataJson.add(scheduleJson);
	       }

	       return scheduleDataJson;
	   }

	   
	   //개인일정 insert
	   @RequestMapping("/schedule/personal/saveEvent")
	   public String insertPeronalSchedule(HttpSession session,
	           @RequestParam("title") String schedule_name,
	           @RequestParam("content") String schedule_body,
	           @RequestParam("start") String schedule_startdate,
	           @RequestParam("end") String schedule_enddate,
	           @RequestParam("regtime") Date schedule_regdate,
	           @RequestParam("register") String schedule_register) {
		   
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	       Date schedule_start_date = null;
	       Date schedule_end_date = null;
	       Date schedule_regtime_date = new Date();
	       
	       try {
	    	   schedule_start_date = format.parse(schedule_startdate);
	    	   schedule_end_date = format.parse(schedule_enddate);
	    	   schedule_regtime_date = schedule_regdate;
	       } catch (ParseException e) {
	           e.printStackTrace();
	       }
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_name);
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_body);
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_start_date);
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_regtime_date);
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_end_date);
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_register);

	   
	       try {
	    	   schedule_start_date = format.parse(schedule_startdate);
	    	   schedule_end_date = format.parse(schedule_enddate);
	       } catch (ParseException e) {
	           e.printStackTrace();
	       }

	       int schedule_type=2;

	       scheduleService.scheduleInsert(schedule_name, schedule_body, schedule_startdate, schedule_enddate, schedule_register, schedule_type);

	       return "/schedule/personalCal";

	   }
	   
	   @RequestMapping("/schedule/modify")
	   @ResponseBody
	   public String modifySchedule(@RequestBody Map<String, String> requestData) {
	   String schedule_code = requestData.get("schedule_code");
	   String schedule_title = requestData.get("title");
	   String schedule_content = requestData.get("content");
	   String schedule_start = requestData.get("start");
	   String schedule_end = requestData.get("end");
	   
	     System.out.println("!!!!!!!!!코드:"+schedule_code);
	     System.out.println("!!!!!!!!!제목:"+schedule_title);
	     System.out.println("!!!!!!!!!내용"+schedule_content);
	     System.out.println("!!!!!!!!!시작:"+schedule_start);
	     System.out.println("!!!!!!!!!끝:"+schedule_end);
	     
	     scheduleService.scheduleModify(schedule_code, schedule_title, schedule_content, schedule_start, schedule_end);
	     return "/schedule/personalCal";
	   }
	   
	   @RequestMapping("/schedule/delete")
		@ResponseBody
		public double deleteSchedule(@RequestParam("schedule_code")String schedule_code) {
			System.out.println("!!!!!!!!!"+schedule_code);
			
			scheduleService.deleteSchedule(schedule_code);
			
			return 0;
		}
	   


		  //공용일정 
	   @RequestMapping("/schedule/commonCal")
		public String showCommonCalendar() {
			return "schedule/commonCal";
		}
	   @RequestMapping("getCommonCalendar")
	   @ResponseBody
	   
	   public List<Map<String, Object>> getCommonCalendar() {
		   
	       List<ScheduleVO> list = scheduleService.getCommonSchedule();
	       List<Map<String, Object>> scheduleDataJson = new ArrayList<>();
	       for (ScheduleVO item : list) {
	           Map<String, Object> scheduleJson = new HashMap<>();
	           scheduleJson.put("schedule_code", item.getSchedule_Code());
	           scheduleJson.put("title", item.getSchedule_Name());
	           scheduleJson.put("content", item.getSchedule_Body());
	           scheduleJson.put("start", item.getSchedule_StartDate());
	           scheduleJson.put("end", item.getSchedule_EndDate());
	           scheduleJson.put("regdate", item.getSchedule_RegDate());
	           scheduleJson.put("register", item.getSchedule_Register());
	           scheduleDataJson.add(scheduleJson);
	       }

	       return scheduleDataJson;
	   }
	   
	 //공용일정 insert
	   @RequestMapping("/schedule/common/saveEvent")
	   public String insertSchedule(HttpSession session,
	           @RequestParam("title") String schedule_name,
	           @RequestParam("content") String schedule_body,
	           @RequestParam("start") String schedule_startdate,
	           @RequestParam("end") String schedule_enddate,
	           @RequestParam("regtime") Date schedule_regdate,
	           @RequestParam("register") String schedule_register) {
		   
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	       Date schedule_start_date = null;
	       Date schedule_end_date = null;
	       Date schedule_regtime_date = new Date();
	       
	       try {
	    	   schedule_start_date = format.parse(schedule_startdate);
	    	   schedule_end_date = format.parse(schedule_enddate);
	    	   schedule_regtime_date = schedule_regdate;
	       } catch (ParseException e) {
	           e.printStackTrace();
	       }
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_name);
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_body);
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_start_date);
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_regtime_date);
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_end_date);
	       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"+schedule_register);

	   
	       try {
	    	   schedule_start_date = format.parse(schedule_startdate);
	    	   schedule_end_date = format.parse(schedule_enddate);
	       } catch (ParseException e) {
	           e.printStackTrace();
	       }

	       int schedule_type=1;

	       scheduleService.scheduleInsert(schedule_name, schedule_body, schedule_startdate, schedule_enddate, schedule_register, schedule_type);

	       return "/schedule/commonCal";

	   }
	   
	   
	   
	   
	   
	



	
	
	
	
}
