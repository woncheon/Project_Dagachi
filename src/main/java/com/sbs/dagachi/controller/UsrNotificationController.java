package com.sbs.dagachi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.dagachi.service.NotificationService;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.Notification;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrNotificationController {

	private NotificationService notificationService;

	public UsrNotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@RequestMapping("/notification/check")
	public String checkNotification(HttpSession session, Model model) {
	    Member loginUser = (Member) session.getAttribute("loginUser");

	    String loginId = loginUser.getMember_id();
	    List<Notification> notifications = notificationService.getForPrintLiveNotification(loginId);
	    model.addAttribute("notifications", notifications);
	    model.addAttribute("notificationsWhereDelstatusIsZeroCount", notifications.size());
	    return "/notification/check"; // Return the JSP file name without the extension
	}

	@RequestMapping("/notification/delete")
	public String deleteAllNotificationsForUser(HttpSession session, Model model) {
	    Member loginUser = (Member) session.getAttribute("loginUser");
	    
	    String loginId = loginUser.getMember_id();
	    String delno = notificationService.deleteNotification(loginId);
	    
	    model.addAttribute("delno", delno);
	    System.out.println("dsdsd"+delno);
	    return "/notification/delete";
	}
	
	@RequestMapping("/notification/path")
	@ResponseBody
	public ResponseEntity<List<Notification>> pathNotification(HttpSession session, Model model) {
	    Member loginUser = (Member) session.getAttribute("loginUser");

	    String loginId = loginUser.getMember_id();
	    List<Notification> notifications = notificationService.getForPrintLiveNotification(loginId);
	    model.addAttribute("notifications", notifications);
	    model.addAttribute("notificationsWhereDelstatusIsZeroCount", notifications.size());

	    return new ResponseEntity<>(notifications, HttpStatus.OK);
	}



}
