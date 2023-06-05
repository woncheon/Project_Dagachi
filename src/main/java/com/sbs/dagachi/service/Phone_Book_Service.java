package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.Phone_Book_Repository;
import com.sbs.dagachi.vo.Phone_BookVO;

@Service
public class Phone_Book_Service {
	Phone_Book_Repository phone_Book_Repository;
	public Phone_Book_Service(Phone_Book_Repository phone_Book_Repository) {
		this.phone_Book_Repository=phone_Book_Repository;
	}

	public List<Phone_BookVO>getPhoneBookList(String searchType, String keyword, int phone_book_type, String phone_book_register, int itemsCountInAPage, int page){
		int limitStart = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;
		List<Phone_BookVO>phoneList=phone_Book_Repository.getPhoneBookList(searchType,keyword,phone_book_type,phone_book_register,limitStart,limitTake);
		
		return phoneList;
	}

	public Phone_BookVO getPhoneBookDetail(int phone_Book_Id) {
		return phone_Book_Repository.getPhoneBookDetail(phone_Book_Id);
	}

	public void registPhone_Book(String phone_Book_Name, String phone_Book_Email, String phone_Book_Phone,
			String phone_Book_Company, String phone_Book_Tag, String phone_Book_Fax, String phone_Book_Dep,
			String phone_Book_Rank, String phone_Book_Address, String phone_Book_Web,
			String phone_Book_Register, int phone_Book_Type) {

		if(phone_Book_Email==null)phone_Book_Email="";
		if(phone_Book_Phone==null)phone_Book_Phone="";
		if(phone_Book_Company==null)phone_Book_Company="";
		if(phone_Book_Tag==null)phone_Book_Tag="";
		if(phone_Book_Fax==null)phone_Book_Fax="";
		if(phone_Book_Dep==null)phone_Book_Dep="";
		if(phone_Book_Rank==null)phone_Book_Rank="";
		if(phone_Book_Address==null)phone_Book_Address="";
		if(phone_Book_Web==null)phone_Book_Web="";
		
		//로그인 세션 받으면 지워라
		if(phone_Book_Register==null)phone_Book_Register="user2";
		
		phone_Book_Repository.registPhone_Book(phone_Book_Name, phone_Book_Email, phone_Book_Phone,
				phone_Book_Company, phone_Book_Tag, phone_Book_Fax, phone_Book_Dep,
				phone_Book_Rank, phone_Book_Address, phone_Book_Web,
				phone_Book_Register, phone_Book_Type);

	}

	public int getLastInsertId() {

		return phone_Book_Repository.getLastInsertId();
	}

	public void modifyPhone_Book(int phone_Book_Id, String phone_Book_Name, String phone_Book_Email, String phone_Book_Phone,
			String phone_Book_Company, String phone_Book_Tag, String phone_Book_Fax, String phone_Book_Dep,
			String phone_Book_Team, String phone_Book_Rank, String phone_Book_Address, String phone_Book_Web,
			String phone_Book_Register, int phone_Book_Type) {

		if(phone_Book_Email==null)phone_Book_Email="";
		if(phone_Book_Phone==null)phone_Book_Phone="";
		if(phone_Book_Company==null)phone_Book_Company="";
		if(phone_Book_Tag==null)phone_Book_Tag="";
		if(phone_Book_Fax==null)phone_Book_Fax="";
		if(phone_Book_Dep==null)phone_Book_Dep="";
		if(phone_Book_Team==null)phone_Book_Team="";
		if(phone_Book_Rank==null)phone_Book_Rank="";
		if(phone_Book_Address==null)phone_Book_Address="";
		if(phone_Book_Web==null)phone_Book_Web="";

		phone_Book_Repository.modifyPhone_Book(phone_Book_Id, phone_Book_Name, phone_Book_Email, phone_Book_Phone,
				phone_Book_Company, phone_Book_Tag, phone_Book_Fax, phone_Book_Dep,
				phone_Book_Team, phone_Book_Rank, phone_Book_Address, phone_Book_Web,
				phone_Book_Register, phone_Book_Type);
	}

	public void removePhone_Book(int phone_Book_Id) {

		phone_Book_Repository.removePhone_Book(phone_Book_Id);
	}

	public int getPhoneBookCount(String searchType, String keyword, int phone_book_type,String phone_book_register) {
		return phone_Book_Repository.phoneBookCount(searchType, keyword, phone_book_type,phone_book_register);
	}

	public void moveTrashPhone_Book(int phone_Book_Id) {
		phone_Book_Repository.moveTrashPhone_Book(phone_Book_Id);
	}

	public List<String> getTagList() {
		List<String>tags=phone_Book_Repository.getTagList();
		return tags;
	}

	public void modifyType(int phoneBookId, String phone_Book_Type) {
		phone_Book_Repository.modifyType(phoneBookId, phone_Book_Type);
	}


}