package com.sbs.dagachi.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.ReservationRepository;
import com.sbs.dagachi.repository.RoomRepository;
import com.sbs.dagachi.vo.Reservation;
import com.sbs.dagachi.vo.Room;

@Service
public class ReservationService {
	private ReservationRepository reservationRepository;
	private RoomRepository roomRepository;
	
	public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
		this.reservationRepository = reservationRepository;
		this.roomRepository = roomRepository;
	}
	
	
	
	public List<Reservation> getForPrintReservations(int room_code,String searchKeywordTypeCode, String searchKeyword, int itemsCountInAPage, int page) {
	    int limitStart = (page - 1) * itemsCountInAPage;
	    int limitTake = itemsCountInAPage;
	    
	    List<Reservation> reservations = reservationRepository.getForPrintReservations(room_code, limitStart, limitTake, searchKeywordTypeCode, searchKeyword);
	    
	    return reservations;
	}
	
	public List<Reservation> getReservations (int room_code, String searchKeywordTypeCode, String searchKeyword){
		return reservationRepository.getReservations(room_code, searchKeywordTypeCode, searchKeyword);
	}

	public int getReservationCount(int room_code, String searchKeywordTypeCode, String searchKeyword) {
	    return reservationRepository.getReservationCount(room_code, searchKeywordTypeCode, searchKeyword);
	}
	public List<Room> getRoomList(){
		return roomRepository.getRoomList();
	}
	

	
	public List<Reservation>getReservationforroomname(int room_code){
		return reservationRepository.getReservationforroomname(room_code);
	}

	public void reservationInsert(String room_code, String reservation_start, String reservation_end, String reservation_member, Date reservation_regtime, String reservation_title) {
		reservationRepository.reservationInsert(room_code, reservation_start, reservation_end, reservation_member, reservation_regtime, reservation_title);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + room_code + reservation_start + reservation_end
				+ reservation_member + reservation_regtime + reservation_title);
		
		
	}
	
	public boolean isOverlappingEvents(String room_code, String reservation_start, String reservation_end) {
	    Reservation overlappingEvent = reservationRepository.getoverlappingEvents(room_code, reservation_start, reservation_end);
	    return overlappingEvent != null;
	}
	
	public boolean isOverlappingEvent(String room_code, String reservation_start, String reservation_end,String reservation_code) {
	    Reservation overlappingEvent = reservationRepository.isOverlappingEvent(room_code, reservation_start, reservation_end,reservation_code);
	    return overlappingEvent != null;
	}


	public boolean hasExistingReservations(String room_code) {
	    List<Reservation> reservations = reservationRepository.getReservationsByRoom(room_code);
	    return !reservations.isEmpty();
	}
	public boolean getReservationsByRoomnotloginUser(String room_code,String reservation_member,String reservation_code){
		 List<Reservation> reservation = reservationRepository.getReservationsByRoomnotloginUser(room_code,reservation_member,reservation_code);
		    return !reservation.isEmpty();
	}

	public List<Reservation> getReservationsByRoom(String room_code) {
		return reservationRepository.getReservationsByRoom(room_code);
	}
	
	public List<Reservation> getReservationsRoom(String room_code, String reservation_member) {
		return reservationRepository.getReservationsRoom(room_code,reservation_member);
	}
	
	public void reservationmodify(String room_code,String reservation_start, String reservation_end,String reservation_title,String reservation_code) {
		
		System.out.println("!!!!!!!!!"+room_code);
		System.out.println("!!!!!!!!!"+reservation_start);
		System.out.println("!!!!!!!!!"+reservation_end);
		System.out.println("!!!!!!!!!"+reservation_title);
		System.out.println("!!!!!!!!!"+reservation_code);
		reservationRepository.reservationmodify(room_code, reservation_start, reservation_end, reservation_title, reservation_code);
	}



	public void delete(String reservation_code) {
		 System.out.println("!!!!!!!!!"+reservation_code);
		reservationRepository.delete(reservation_code);
		
	}
}