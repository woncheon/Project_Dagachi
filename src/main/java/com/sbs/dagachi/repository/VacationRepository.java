package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.dagachi.vo.Vacation;


@Mapper
public interface VacationRepository {
	
	public Integer getTotalYear(@Param("vacation_member" )String vacation_member );
	
	public int getTotalMonth(@Param("vacation_member" )String vacation_member );
	
	public int getDelYear(@Param("vacation_member" )String vacation_member );
	
	public int getDelMonth(@Param("vacation_member" )String vacation_member);
	
	public List<Vacation> getVacation(@Param("vacation_member" )String vacation_member);

}
