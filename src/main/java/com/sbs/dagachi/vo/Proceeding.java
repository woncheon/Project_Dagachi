package com.sbs.dagachi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proceeding {
	int proceeding_id;
	int form_id;
	int form_proceeding_type;
	int form_proceeding_scope;
	String form_proceeding_title;
	Date form_proceeding_regDate;
	Date form_proceeding_updateDate;
	String form_proceeding_topic;
	String form_proceeding_body;
	String form_proceeding_result;
	String form_proceeding_attender;
	String form_proceeding_register;
	
	String extra_member_name;
	
	
	
}
