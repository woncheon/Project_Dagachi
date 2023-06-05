package com.sbs.dagachi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleVO {

	private int schedule_Code;
	private String schedule_Name;
	private String schedule_Body;
	private Date schedule_StartDate;
	private Date schedule_EndDate;
	private Date schedule_RegDate;
	private String schedule_Register;
	private int schedule_Type;
	
	
}
