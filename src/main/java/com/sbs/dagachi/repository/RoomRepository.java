package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sbs.dagachi.vo.Room;


@Mapper
public interface RoomRepository {
	
	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Insert("INSERT INTO room SET room_name = #{room_name}")
	public void insert(@Param("room_name") String room_name);
	
	@Select("SELECT * FROM room WHERE room_name")
	public List<Room> getRoomList();

	@Update("UPDATE ROOM SET ROOM_NAME = #{room_name} WHERE ROOM_CODE = #{room_code}")
	public void modify(String room_code, String room_name);

	@Delete("DELETE FROM ROOM WHERE ROOM_CODE = #{room_code}")
	public void delete(@Param("room_code") String room_code);

}
