package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.Notification;

@Mapper
public interface NotificationRepository {
	List<Notification> getForPrintLiveNotification(String member_id);

	public String setDelStatusToOne(String member_id);

	
	int getCountNotificationsDelstatusZero();
	
	void registNotification(String body, String receiver, String mcode, String register);
}
