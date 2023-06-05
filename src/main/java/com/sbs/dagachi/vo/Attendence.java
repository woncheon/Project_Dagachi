package com.sbs.dagachi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendence {
	
	private int attendence_id;
	private String attendence_member;
	private int attendence_status;
	private int attendence_month_vacation_cnt;
	private int attendence_year_vacation_cnt;
	private Date attendence_regDate;
	private String attendence_reason;
	
	private String member_department;
	private String member_team;

	private String member_id;

}
