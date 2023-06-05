package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.FormRepository;
import com.sbs.dagachi.vo.Form;

@Service
public class FormService {
	@Autowired
	private FormRepository formRepository;
	
	public FormService(FormRepository formRepository) {
		this.formRepository=formRepository;
	}
	
	public List<Form> getFormList(){
		return formRepository.getFormList();
		
	}
	public Form getForm(int form_id) {
		return formRepository.getForm(form_id);
	}
	
}
