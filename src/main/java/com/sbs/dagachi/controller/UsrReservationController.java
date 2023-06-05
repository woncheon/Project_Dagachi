package com.sbs.dagachi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.dagachi.service.ReservationService;
import com.sbs.dagachi.service.RoomService;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.Reservation;
import com.sbs.dagachi.vo.Room;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrReservationController {

	public ReservationService reservationService;

	public RoomService roomService;

	public UsrReservationController(ReservationService reservationService, RoomService roomService) {
		this.reservationService = reservationService;
		this.roomService = roomService;
	}

	@RequestMapping("/reservation/list")
	public String showList(HttpSession session,HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") int room_code,
	        @RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "room_code,reservation_member") String searchKeywordTypeCode,
	        @RequestParam(defaultValue = "") String searchKeyword) {
		   Member loginUser = (Member) session.getAttribute("loginUser");
	    int reservationCount = reservationService.getReservationCount(room_code, searchKeywordTypeCode, searchKeyword);
	    int itemsCountInAPage = 10;
	    int pagesCount = (int) Math.ceil((double) reservationCount / itemsCountInAPage);

	    List<Reservation> reservations = reservationService.getForPrintReservations(room_code, searchKeywordTypeCode,
	            searchKeyword, itemsCountInAPage, page);

	    model.addAttribute("room_code", room_code);
	    model.addAttribute("page", page);
	    model.addAttribute("pagesCount", pagesCount);
	    model.addAttribute("reservationCount", reservationCount);
	    model.addAttribute("reservations", reservations);
	    model.addAttribute("loginUser",loginUser);
	   
	    List<Reservation> reservationsData = reservations; 
	    List<Room> room = roomService.getRoomList();
	    model.addAttribute("reservationsData", reservationsData);
	    model.addAttribute("room", room);
	    System.out.println("!!!!!!!!!!!!!!!!!!!Member Status: " + loginUser.getMember_auth());
	    return "/reservation/list";
	    
	    
	}
	@RequestMapping("/reservation/getCalendar")
	@ResponseBody
	public List<Map<String, Object>> getCalendar(HttpSession session,
	    @RequestParam(defaultValue = "1") int room_code,
	    @RequestParam(defaultValue = "room_code,reservation_member") String searchKeywordTypeCode,
	    @RequestParam(defaultValue = "") String searchKeyword) {
	    
	    // 로그인 사용자 정보 가져오기
	    Member loginUser = (Member) session.getAttribute("loginUser");
	    String reservation_member = loginUser.getMember_id();

	    List<Reservation> list = reservationService.getReservations(room_code, searchKeywordTypeCode, searchKeyword);
	    List<Map<String, Object>> reservationsDataJson = new ArrayList<>();
	    for (Reservation item : list) {
	        Map<String, Object> reservationJson = new HashMap<>();
	        reservationJson.put("reservation_code", item.getReservation_code());
	        reservationJson.put("room_code", item.getRoom_code());
	        reservationJson.put("start", item.getReservation_start());
	        reservationJson.put("end", item.getReservation_end());
	        reservationJson.put("regtime", item.getReservation_regtime());
	        reservationJson.put("member", item.getReservation_member());
	        reservationJson.put("title", item.getReservation_title());

	        // 로그인 사용자와 reservation_member 정보가 같으면 초록색으로 표시
	        if (reservation_member != null && loginUser.getMember_id().equals(item.getReservation_member())) {
	            reservationJson.put("color", "green");
	        }else {
	        	reservationJson.put("color", "red");
	        }

	        reservationsDataJson.add(reservationJson);
	    }

	    if (!searchKeywordTypeCode.isEmpty() && !searchKeyword.isEmpty()) {
	        List<Map<String, Object>> filteredReservationsDataJson = new ArrayList<>();
	        for (Map<String, Object> reservation : reservationsDataJson) {
	            if (reservation.get(searchKeywordTypeCode).toString().equals(searchKeyword)) {
	                filteredReservationsDataJson.add(reservation);
	            }
	        }
	        return filteredReservationsDataJson;
	    }

	    return reservationsDataJson;
	}



	@RequestMapping("/reservation/saveEvent")
	public String reservationInsert(HttpSession session,
	        @RequestParam("room_code") String room_code,
	        @RequestParam("title") String reservation_title,
	        @RequestParam("reservation_start") String reservation_start,
	        @RequestParam("reservation_end") String reservation_end,
	        @RequestParam("reservation_regtime") Date reservation_regtime,
	        @RequestParam("user") String reservation_member) {

	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	    Date reservation_start_date = null;
	    Date reservation_end_date = null;
	    Date reservation_regtime_date = new Date();
	    
	    try {
	        reservation_start_date = format.parse(reservation_start);
	        reservation_end_date = format.parse(reservation_end);
	        reservation_regtime_date = reservation_regtime;
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    System.out.println(reservation_start_date);
	    System.out.println(reservation_regtime_date);
	    System.out.println(reservation_end_date);
	    System.out.println(room_code);

	 // 겹치는 예약 유효성 체크
	    if (reservationService.isOverlappingEvents(room_code, reservation_start, reservation_end)) {
	        System.out.println("해당 회의실은 이미 예약된 시간과 겹칩니다. 다시 선택해주세요.");
	        return "isOverlappingEventserror";
	    }

	    // 기존 예약 유효성 체크
	    if (reservationService.hasExistingReservations(room_code)) {
	        List<Reservation> existingReservations = reservationService.getReservationsByRoom(room_code);
	        for (Reservation existingReservation : existingReservations) {
	            Date existingStart = existingReservation.getReservation_start();
	            Date existingEnd = existingReservation.getReservation_end();

	            // 선택한 시간과 기존 예약 시간이 겹치는지 확인
	            if (existingStart.equals(reservation_start_date) || existingEnd.equals(reservation_end_date) ||
	                    (existingStart.before(reservation_end_date) && existingEnd.after(reservation_start_date))) {
	                System.out.println("해당 회의실은 이미 예약된 시간과 겹칩니다. 다시 선택해주세요.");
	                return "isOverlappingEventserror";
	            }
	        }
	    }

	    // 예약 삽입
	    reservationService.reservationInsert(room_code, reservation_start, reservation_end, reservation_member,
	            reservation_regtime, reservation_title);

	    return "/reservation/list";

	}
	
	@RequestMapping("/reservation/modify")
	@ResponseBody
	public String modifyReservation(HttpSession session, @RequestBody Map<String, String> requestData) {
	    String room_code = requestData.get("room_code");
	    String reservation_start = requestData.get("reservation_start");
	    String reservation_end = requestData.get("reservation_end");
	    String reservation_title = requestData.get("reservation_title");
	    String reservation_code = requestData.get("reservation_code");

	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	    Date reservation_start_date;
	    Date reservation_end_date;

	    try {
	        reservation_start_date = format.parse(reservation_start);
	        reservation_end_date = format.parse(reservation_end);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return "error";
	    }

	    System.out.println(reservation_start_date);
	    System.out.println(reservation_end_date);
	    System.out.println(room_code);

	    Member loginUser = (Member) session.getAttribute("loginUser");
	    String reservation_member = loginUser.getMember_id();

	    if (!loginUser.getMember_id().equals(reservation_member)) {
	        return "error";
	    }

	    if (reservationService.isOverlappingEvents(room_code, reservation_start, reservation_end)) {
	        if (loginUser.getMember_id().equals(reservation_member)) {
	            // loginUser.getMember_id() and reservation_member are the same, consider it a success
	        	System.out.println("test222222222222");
	            reservationService.reservationmodify(room_code, reservation_start, reservation_end, reservation_title, reservation_code);
	            return "success";
	        }
	        	
	        
	       
	    }

	    if (reservationService.getReservationsByRoomnotloginUser(room_code,reservation_member,reservation_code)) {
	        List<Reservation> existingReservations = reservationService.getReservationsRoom(room_code,reservation_member);
	        for (Reservation existingReservation : existingReservations) {
	            Date existingStart = existingReservation.getReservation_start();
	            Date existingEnd = existingReservation.getReservation_end();

	            if (existingStart.equals(reservation_start_date) || existingEnd.equals(reservation_end_date) ||
	                    (existingStart.before(reservation_end_date) && existingEnd.after(reservation_start_date))) {
	            	System.out.println("test11");
	            	return "isOverlappingEventserror";
	            }
	          
	        }
	    }


	    reservationService.reservationmodify(room_code, reservation_start, reservation_end, reservation_title, reservation_code);
	    return "success";
	}


	@RequestMapping("/reservation/delete")
	@ResponseBody
	public double delete(@RequestParam("reservation_code")String reservation_code) {
		 System.out.println("!!!!!!!!!"+reservation_code);
		
		reservationService.delete(reservation_code);
		
		return 0;
	}



	@RequestMapping("/reservation/doinsert")
	public String showinsert() {
		return "/reservation/insert";
	}

	@RequestMapping("/reservation/insert")
	public String insert(@RequestParam("room_name") String room_name) {
		roomService.insertRoom(room_name);
		return "redirect:/reservation/insert";
	}
	
	@RequestMapping("/reservation/domodify")
	public String showModify(Model model) {
	    List<Room> room = roomService.getRoomList();
	    model.addAttribute("room", room);
	  
	    return "/reservation/modify";
	}

	@RequestMapping("/reservation/roommodify")
	@ResponseBody
	public double roomModify(Model model, @RequestParam("room_name") String room_name, @RequestParam("room_code") String room_code) {
	    roomService.modifyRoom(room_code, room_name);
	    
	    return 0;
	}
	
	@RequestMapping("/reservation/roomdelete")
	@ResponseBody
	public double roomdelete(@RequestParam("room_code")String room_code) {
		
		roomService.delete(room_code);
		
		return 0;
	}


}
