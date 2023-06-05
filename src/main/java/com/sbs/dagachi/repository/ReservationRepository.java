package com.sbs.dagachi.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sbs.dagachi.vo.Reservation;

@Mapper
public interface ReservationRepository {

	public List<Reservation> getForPrintReservations(int room_code, int limitStart, int limitTake,
			String searchKeywordTypeCode, String searchKeyword);

	public int getReservationCount(int room_code, String searchKeywordTypeCode, String searchKeyword);

	public void reservationInsert(String room_code, String reservation_start, String reservation_end, String reservation_member, Date reservation_regtime, String reservation_title);

	public List<Reservation>getReservationforroomname(int room_code);
	
	public List<Reservation> getReservations (int room_code, String searchKeywordTypeCode, String searchKeyword);
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	public Reservation getoverlappingEvents(String room_code, String reservation_start, String reservation_end);
	
	public Reservation isOverlappingEvent(String room_code, String reservation_start, String reservation_end,String reservation_code);

	public List<Reservation> getReservationsByRoom(String room_code);
	
	public List<Reservation> getReservationsByRoomnotloginUser(String room_code,String reservation_member,String reservation_code);
	
	public List<Reservation> getEvent(String reservation_code);
	
	public void reservationmodify(String room_code,String reservation_start, String reservation_end,String reservation_title,String reservation_code);

	public void delete(String reservation_code);

	public List<Reservation> getReservationsRoom(String room_code, String reservation_member);
}
