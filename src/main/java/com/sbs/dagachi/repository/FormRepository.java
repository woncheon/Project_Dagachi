package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.Form;

@Mapper
public interface FormRepository {

	List<Form> getFormList();
	
	Form getForm(int form_id);

}
