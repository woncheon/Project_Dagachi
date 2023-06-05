package com.sbs.dagachi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Approval {

	private int a_id;
	private int approval_id;
	private String a_approver;
	private Date a_approvaldate;
	private String a_comment;
	private int a_status;
	
	
}
