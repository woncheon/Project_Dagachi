package com.sbs.dagachi.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.dagachi.vo.Attendence;

@Mapper
public interface AttendenceRepository {
	
	//전체 리스트
	public List<Attendence> getForPrintAttendence(int attendence_id, int limitStart, int limitTake,
			String searchKeywordTypeCode, String searchKeyword);
	
	//전체 카운트
	public int getAttendenceCount(int attendence_id, String searchKeywordTypeCode, String searchKeyword);


	//로그인유저 리스트
	public List<Attendence> getForPrintMyAttendence(int attendence_id, int limitStart, int limitTake,
			String searchKeywordTypeCode, String searchKeyword, @Param("attendence_member" )String attendence_member);

	//로그인유저리스트 카운트
	public int getMyAttendenceCount(int attendence_id, String searchKeywordTypeCode, String searchKeyword, String attendence_member);

	//부서별 총 인원수
	public List<Map<String, Object>> getDepartmentTotal();
	
	//날짜,부서별 총 출근 인원수
	public List<Map<String, Object>>getDepartmentTodayTotal(String searchKeywordTypeCode,String searchKeyword,int limitStart, int limitTake);
	
	//부서별 totalcount
	public int getDepartmentTotalCount(int attendence_id,String searchKeywordTypeCode,String searchKeyword);
	
	//팀별,날짜 총 출근 인원수
	public List<Map<String,Object>>getTeamTodayTotal(int member_department,int limitStart, int limitTake,String searchKeywordTypeCode,String searchKeyword);
	
	//부서에따라 팀별 총 인원수
	public List<Map<String,Object>>getTeamtotal(int member_department);
	
	//위의쿼리문 다른방식으로 합친것
	public List<Map<String,Object>>getTeamtotallist(int member_department);
	
	//팀별근태 리스트 개수
	public int getTeamlistCount(int attendence_id, String searchKeywordTypeCode, String searchKeyword);
		
	//팀별 이달 근무일
	public List<Map<String,Object>>getTotalWorkDay(int member_department);
	
	//부서에따른 모든팀 총 출근 인원수 구하기
	public int getTodayTeamStatusCount(int member_department);
	
	//부서에따른 모든팀 총 지각 인원수 구하기 없으면 (0)임
	public int getTodayTeamStatus7Count(int member_department);
	
	//부서에따른 모든팀의 총 인원수 합
	public int getTeamTotalCount(int member_department);
	
	//부서에따른 모든팀의 조퇴 인원수
	public int getTodayStatus5Count(int member_department);
	
	//부서에따른 모든팀의 연월차 인원수
	public int getTodaystatus4Count(int member_department);
	
	//팀별상세 이름직급날짜상태이유
	public List<Map<String,Object>>getTeamDetail(int member_department,int member_team,String attendence_regDate);

	//팀별상세 이름으로 총 출근수
	public int getMemberNameTotalStatus(String member_name);
	
	//팀별상세 이름으로 총 지각수
	public int getMemberNameTotalStatus7Count(String member_name);
	
	//팀별상세 이름으로 총 조퇴수
	public int getMemberNameTotalStatus5Count(String member_name);
	
	//팀별상세 이름으로 해당사원의 날짜별 리스트 가져오기
	public List<Map<String,Object>>getMemberNameTotalList(String member_name);

	public void attendenceInsert(String attendence_member, int attendence_status, Integer attendence_month_vacation_cnt,
            Integer attendence_year_vacation_cnt, String attendence_regDate, String member_department,
            String member_team);

}
