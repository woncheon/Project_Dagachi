package com.sbs.dagachi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.RoomRepository;
import com.sbs.dagachi.vo.Room;


@Service
public class RoomService {
	
	private RoomRepository roomRepository;
	
	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	
	public void insertRoom(String room_name) {
		roomRepository.insert(room_name);

	}
	
	public List<Room> getRoomList() {
		return roomRepository.getRoomList();
	}


	public void modifyRoom(String room_code,String room_name) {
		
		roomRepository.modify(room_code,room_name);
		
	}
	public void delete(@Param("room_code") String room_code) {
		roomRepository.delete(room_code);
	}
	

}
