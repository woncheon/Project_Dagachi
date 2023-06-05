package com.sbs.dagachi.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectL {
	
	
	
	int pl_Id;
	String pl_Name;
	int pl_Kind_Status;
	Date pl_StartDate;
	Date pl_EndDate;
	String pl_Register;
	String pl_Manager;
	String pl_Team;
	String pl_Body;
	
	List<PM>extra_pmList;
	double plProgress;
}
