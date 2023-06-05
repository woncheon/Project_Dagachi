package com.sbs.dagachi.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.PS;

@Mapper
public interface PSRepository {
	void registPs(String ps_Title, String ps_Sender, String ps_Receiver,Date ps_SendDate, 
			Date ps_EndDate, int ps_Status, int pm_Id);
	
	List<PS>getPSListWait(String ps_Receiver);
	List<PS>getPSListDoing(String ps_Receiver);
	List<PS>getPSListComplete(String ps_Receiver);
	List<PS>getPSListCancel(String ps_Receiver);
	
	
	void modifyPs(int stat, int ps_id);
	
	void modifyPsUser(int ps_id, String ps_Receiver);

	PS getPSByPmId(int pm_id);

	List<PS> getPSListByPmId(int pm_id);
	
	PS getPSByPsId(int ps_id);

	void removePS(int ps_idInt);
	
	
}
