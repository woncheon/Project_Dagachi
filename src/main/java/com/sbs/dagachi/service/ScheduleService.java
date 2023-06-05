package com.sbs.dagachi.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.ScheduleRepository;
import com.sbs.dagachi.vo.ScheduleVO;

@Service
public class ScheduleService {
	private ScheduleRepository scheduleRepository;

	public ScheduleService(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	public List<ScheduleVO> getCalendarList() {
		List<ScheduleVO> schedule = scheduleRepository.getCalendarList();
		return schedule;
	}

	public List<ScheduleVO> getCommonSchedule() {
		List<ScheduleVO> schedule = scheduleRepository.getCommonSchedule();
		return schedule;
	}

	public List<ScheduleVO> getPersonalSchedule(String member_id) {
		List<ScheduleVO> schedule = scheduleRepository.getPersonalSchedule(member_id);
		return schedule;
	}
	

	// 등록

	public void scheduleInsert(String schedule_name, String schedule_body, String schedule_startdate,
			String schedule_enddate, String schedule_register, int schedule_type) {
		scheduleRepository.registSchedule(schedule_name, schedule_body, schedule_startdate, schedule_enddate,
				schedule_register, schedule_type);

	}

	public void scheduleModify(String schedule_code, String schedule_title, String schedule_content,
			String schedule_start, String schedule_end) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!22222"+schedule_code+schedule_title+schedule_content);

		scheduleRepository.updateSchedule(schedule_code, schedule_title, schedule_content, schedule_start,
				schedule_end);
		
		

	}

	public void deleteSchedule(String schedule_code) {
		scheduleRepository.deleteSchedule(schedule_code);

	}

}
