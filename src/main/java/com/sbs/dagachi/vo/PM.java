package com.sbs.dagachi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PM {
	int pm_Id;
	String pm_name;
	int pm_kind_status;
	Date pm_startDate;
	Date pm_endDate;
	int pm_status;
	String pm_register;
	String pm_manager;
	int pl_Id;
	
	String extra_pm_manager_name;
}
