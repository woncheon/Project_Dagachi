package com.sbs.dagachi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
	
	private int article_id;
	private int board_id;
	private String article_regdate;
	private String article_updatedate;
	private String article_register;
	private String article_title;
	private String article_body;
	private String article_viewcnt;
	private String article_attach;
	private int article_important;
	private String member_name;
	private String member_department1;
	private String member_team1;
	private String member_rank;
	
}
