package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.dagachi.vo.Phone_BookVO;

@Mapper
public interface Phone_Book_Repository {

	public List<Phone_BookVO>getPhoneBookList(String searchType, String keyword, int phone_book_type, String phone_book_register, int limitStart, int limitTake);

	public Phone_BookVO getPhoneBookDetail(int phone_Book_Id);

	public void registPhone_Book(String phone_Book_Name, String phone_Book_Email, String phone_Book_Phone,
			String phone_Book_Company, String phone_Book_Tag, String phone_Book_Fax, String phone_Book_Dep,
			String phone_Book_Rank, String phone_Book_Address, String phone_Book_Web,
			String phone_Book_Register, int phone_Book_Type);

	public int getLastInsertId();

	public void modifyPhone_Book(int phone_Book_Id, String phone_Book_Name, String phone_Book_Email, String phone_Book_Phone,
			String phone_Book_Company, String phone_Book_Tag, String phone_Book_Fax, String phone_Book_Dep,
			String phone_Book_Team, String phone_Book_Rank, String phone_Book_Address, String phone_Book_Web,
			String phone_Book_Register, int phone_Book_Type);

	public void removePhone_Book(int phone_Book_Id);
	public int phoneBookCount(String searchType, String keyword, int phone_book_type,String phone_book_register);
	public void moveTrashPhone_Book(int phone_Book_Id);

	public List<String> getTagList();

	public void modifyType(int phoneBookId, String phone_Book_Type);

}