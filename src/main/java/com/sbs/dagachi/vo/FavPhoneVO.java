package com.sbs.dagachi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavPhoneVO {
	int fav_Phone_Book_Id;
	int phone_Book_Id;
	String fav_Phone_Book_Register;
	
	String phone_Book_Name;
	String phone_Book_Email;
	String phone_Book_Phone;
	String phone_Book_Company;
	String phone_Book_Tag;
	String phone_Book_Fax;
	String phone_Book_Dep;
	String phone_Book_Rank;
	String phone_Book_Address;
	String phone_Book_Web;
	String phone_Book_Register;
	int phone_Book_Type;
	
}
