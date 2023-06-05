package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.NotificationRepository;
import com.sbs.dagachi.vo.Notification;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getForPrintLiveNotification(String member_id) {
    	return notificationRepository.getForPrintLiveNotification(member_id);
	}

    public String deleteNotification(String member_id) {
        
		return notificationRepository.setDelStatusToOne(member_id);
    }

	
	public int getCountNotificationsDelstatusZero() {
	    return notificationRepository.getCountNotificationsDelstatusZero();
	}
	
	public void registNotification(String body, String receiver, String mcode, String register) {
		notificationRepository.registNotification(body, receiver, mcode, register);
	}

}