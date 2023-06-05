package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.DepartmentRepository;
import com.sbs.dagachi.vo.DepartmentVO;
@Service
public class DepartmentService {
	private DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository=departmentRepository;
	}	
	
	
	public List<DepartmentVO>getDepList(){
		return departmentRepository.getDepList();
	}
	
	public DepartmentVO getDep(int depCode) {
		return departmentRepository.getDep(depCode);
	}
}
