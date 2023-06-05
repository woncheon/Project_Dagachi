package com.sbs.dagachi.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.AttendenceRepository;
import com.sbs.dagachi.repository.MemberRepository;
import com.sbs.dagachi.repository.VacationRepository;
import com.sbs.dagachi.vo.Attendence;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.Vacation;

@Service
public class AttendenceService {
	private AttendenceRepository attendenceRepository;
	private VacationRepository vacationRepository;
	private MemberRepository memberRepository;
	
	public AttendenceService(AttendenceRepository attendenceRepository, VacationRepository vacationRepository, MemberRepository memberRepository) {
		this.attendenceRepository = attendenceRepository;
		this.vacationRepository = vacationRepository;
		this.memberRepository =memberRepository;
	}
	
	
	
	public List<Attendence> getForPrintAttendence(int attendence_id,String searchKeywordTypeCode, String searchKeyword, int itemsCountInAPage, int page) {
	    int limitStart = (page - 1) * itemsCountInAPage;
	    int limitTake = itemsCountInAPage;
	    
	    List<Attendence> attendences = attendenceRepository.getForPrintAttendence(attendence_id, limitStart, limitTake, searchKeywordTypeCode, searchKeyword);
	    
	    return attendences;
	}
	
	public int getAttendenceCount(int attendence_id, String searchKeywordTypeCode, String searchKeyword) {
	    return attendenceRepository.getAttendenceCount(attendence_id, searchKeywordTypeCode, searchKeyword);
	}
	

	public List<Attendence> getForPrintMyAttendence(int attendence_id,String searchKeywordTypeCode, String searchKeyword, int itemsCountInAPage, int page,String attendence_member) {
	    int limitStart = (page - 1) * itemsCountInAPage;
	    int limitTake = itemsCountInAPage;
	    
	    List<Attendence> attendences = attendenceRepository.getForPrintMyAttendence(attendence_id, limitStart, limitTake, searchKeywordTypeCode, searchKeyword, attendence_member);
	    
	    return attendences;
	}

	public int getMyAttendenceCount(int attendence_id, String searchKeywordTypeCode, String searchKeyword, String attendence_member) {
	    return attendenceRepository.getMyAttendenceCount(attendence_id, searchKeywordTypeCode, searchKeyword, attendence_member);
	}
	
	public int getTotalYear(String vacation_member) {
		return vacationRepository.getTotalYear(vacation_member);
	}
	
	public int getDelYear(String vacation_member) {
		return vacationRepository.getDelYear(vacation_member);
	}

	public int getTotalMonth(String vacation_member) {
		return vacationRepository.getTotalMonth(vacation_member);
	}
	
	public int getDelMonth(String vacation_member) {
		return vacationRepository.getDelMonth(vacation_member);
	}
	
	public List<Vacation> getVacation(String vacation_member){
		return vacationRepository.getVacation(vacation_member);
	}
	
	public List<Map<String, Object>> getDepartmentTotal(){

		return attendenceRepository.getDepartmentTotal();
	}
	public int getDepartmentTotalCount(int attendence_id,String searchKeywordTypeCode,String searchKeyword) {
		return attendenceRepository.getDepartmentTotalCount(attendence_id, searchKeywordTypeCode, searchKeyword);
	}
	public List<Map<String,Object>>getTeamTodayTotal(int member_department,int itemsCountInAPage, int page,String searchKeywordTypeCode,String searchKeyword){
		
		int limitStart = (page - 1) * itemsCountInAPage;
	    int limitTake = itemsCountInAPage;
		
	    
		return attendenceRepository.getTeamTodayTotal( member_department,limitStart, limitTake,searchKeywordTypeCode,searchKeyword);
	}
	public List<Map<String,Object>>getTeamtotal(int member_department){
		return attendenceRepository.getTeamtotal(member_department);
	}
	
	public List<Map<String,Object>>getTeamtotallist(int member_department){
		return attendenceRepository.getTeamtotallist(member_department);
	}
	
	
	public int getTeamlistCount(int attendence_id, String searchKeywordTypeCode, String searchKeyword) {
		return attendenceRepository.getTeamlistCount(attendence_id, searchKeywordTypeCode, searchKeyword);
	}
	public List<Map<String,Object>>getTotalWorkDay(int member_department){
		return attendenceRepository.getTotalWorkDay(member_department);
	}
	public int getTodayTeamStatusCount(int member_department) {
		return attendenceRepository.getTodayTeamStatusCount(member_department);
	}
	public int getTodayTeamStatus7Count(int member_department) {
		return attendenceRepository.getTodayTeamStatus7Count(member_department);
	}
	public int getTeamTotalCount(int member_department) {
		return attendenceRepository.getTeamTotalCount(member_department);
	}
	public int getTodayStatus5Count(int member_department) {
		return attendenceRepository.getTodayStatus5Count(member_department);
	}
	public int getTodaystatus4Count(int member_department) {
		return attendenceRepository.getTodaystatus4Count(member_department);
	}
	
	public List<Map<String,Object>>getTeamDetail(int member_department,int member_team,String attendence_regDate){
		return attendenceRepository.getTeamDetail(member_department,member_team,attendence_regDate);
	}
	
	public List<Member> getMembername(String member_name) {
	    return memberRepository.getMembername(member_name);
	}
	public int getMemberNameTotalStatus(String member_name) {
		return attendenceRepository.getMemberNameTotalStatus(member_name);
	}
	public int getMemberNameTotalStatus7Count(String member_name) {
		return attendenceRepository.getMemberNameTotalStatus7Count(member_name);
	}
	public int getMemberNameTotalStatus5Count(String member_name) {
		return attendenceRepository.getMemberNameTotalStatus5Count(member_name);
	}
	public List<Map<String,Object>>getMemberNameTotalList(String member_name){
		return attendenceRepository.getMemberNameTotalList(member_name);
	}
	
	public List<Map<String, Object>> getDepartmentTodayTotal(String searchKeywordTypeCode, String searchKeyword,int itemsCountInAPage, int page) {
	    int limitStart = (page - 1) * itemsCountInAPage;
	    int limitTake = itemsCountInAPage;

	    return attendenceRepository.getDepartmentTodayTotal(searchKeywordTypeCode,searchKeyword,limitStart, limitTake);
	}
	
	public List<Member> getDepartmentRepresentative(int member_department){
		return memberRepository.getDepartmentRepresentative(member_department);

	}



	public void attendenceInsert(String attendence_member, int member_status, Integer attendece_month_vacation_cnt,
			Integer attendence_year_vacation_cnt, String attendence_regDate, String member_department,
			String member_team) {
		 attendenceRepository.attendenceInsert(attendence_member,member_status,attendece_month_vacation_cnt,attendence_year_vacation_cnt,attendence_regDate,member_department,member_team);
		 System.out.println("@@@@@@@@@@@@"+attendence_member+member_status+attendece_month_vacation_cnt+attendence_year_vacation_cnt+attendence_regDate+member_department+member_team);
	}
}