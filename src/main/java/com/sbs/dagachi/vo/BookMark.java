package com.sbs.dagachi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMark {
	private int book_mark_code;
	private String book_mark_register;
	private String mcode;
	private String page_name;
	
}
