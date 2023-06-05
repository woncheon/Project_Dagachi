package com.sbs.dagachi.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.PM;

@Mapper
public interface PMRepository {
	List<PM>getPMList(String member_id);
	
	void registPM(String pm_name, Date pm_endDate, String pm_register, String pm_manager, int pl_id);
	
	List<PM>getPLlist(String member_id);
	
	int last_insert_id();
	
	List<PM> getPM_nameList(int pl_id);

	List<PM> getPM_nameListSub(int pl_id);
	
	PM getPMByPmId(int pm_id);
	
	void modifyKSPM(String pm_manager, int pm_id);

	List<PM> getPMListByPLId(int pl_id);

	void modifyStatus(int statInt, int pm_id);
}
