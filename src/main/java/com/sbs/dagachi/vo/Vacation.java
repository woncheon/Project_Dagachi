package com.sbs.dagachi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vacation {
	
	private int vacation_id;
	private String vacation_member;
	private Date year_vacation_regDate;
	private Date month_vacation_regDate;
	private Date year_vacation_finish;
	private Date month_vacation_finish;
	private int year_vacation_count;
	private int month_vacation_count;
	private int vacation_type;
	private String vacation_reason;
	private int del_year_vacation_count;
	private int del_month_vacation_count;
	private Date vacation_updateDate;
	

}
