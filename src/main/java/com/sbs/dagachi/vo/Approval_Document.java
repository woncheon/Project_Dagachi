package com.sbs.dagachi.vo;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Approval_Document {

	
	private int approval_id;
	private String approval_title;
	private String approval_content;
	private String approval_register;
	private Date approval_registdate;
	private Date approval_updatedate;
	private Date approval_appdate;
	private int approval_status;
	private int approval_save;
	private String approval_attach;
	private int form_id;
	private String approval_approver1;
	private String approval_approver2;
	private String approval_lastapprover;
	private int approval_level;
	
	
	
	
 
}
