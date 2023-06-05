package com.sbs.dagachi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareArticle {
	private int article_id;
	private String article_regdate;
	private String article_updatedate;
	private String article_register;
	private String article_title;
	private String article_body;
	private int article_viewcnt;
	private String member_name;
	private int article_important;
	private String article_attach;
	
	private int board_id;
	
}
