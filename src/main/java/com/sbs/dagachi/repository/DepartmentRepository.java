package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.DepartmentVO;
@Mapper
public interface DepartmentRepository {
	public List<DepartmentVO>getDepList();
	
	public DepartmentVO getDep(int depCode);
}
