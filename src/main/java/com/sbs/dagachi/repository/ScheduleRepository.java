package com.sbs.dagachi.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sbs.dagachi.vo.ScheduleVO;

@Mapper
public interface ScheduleRepository {
	public List<ScheduleVO> getCommonSchedule();
	

	
	public List<ScheduleVO> getPersonalSchedule(@Param("schedule_register") String schedule_register);
	


	public void modifySchedule(@Param("schedule_Code") int schedule_Code, @Param("Name") String schedule_Name,
			@Param("Body") String schedule_Body, @Param("StartDate") Date startDate, @Param("EndDate") Date endDate);


	public List<ScheduleVO> getCalendarList();

	public void registSchedule(@Param("schedule_name")String schedule_name,
			@Param("schedule_body")String schedule_body,
			@Param("schedule_start")String schedule_startdate,
			@Param("schedule_end")String schedule_enddate,
			@Param("register")String schedule_register,
			@Param("type")int schedule_type);

	public void updateSchedule(@Param("schedule_code")String schedule_code,
			@Param("schedule_name") String schedule_title,
			@Param("schedule_body") String schedule_content,
			@Param("start")String schedule_start,
			@Param("end") String schedule_end);

	public void deleteSchedule(@Param("schedule_code") String schedule_code);

}
