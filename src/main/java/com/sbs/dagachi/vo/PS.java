package com.sbs.dagachi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PS {
	int ps_Id;
	String ps_title;
	String ps_Sender;
	String ps_Receiver;
	Date ps_SendDate;
	Date ps_EndDate;
	int ps_Status;
	int pm_Id;
	
	String extra_pl_name;
	
}
