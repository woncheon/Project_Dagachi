package com.sbs.dagachi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.dagachi.service.FavPhoneService;
import com.sbs.dagachi.service.Phone_Book_Service;
import com.sbs.dagachi.vo.FavPhoneVO;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.Phone_BookVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class FavPhoneController {
	
	FavPhoneService favPhoneService;
	Phone_Book_Service phone_Book_Service;

	public FavPhoneController(FavPhoneService favPhoneService, Phone_Book_Service phone_Book_Service) {
		this.favPhoneService = favPhoneService;
		this.phone_Book_Service = phone_Book_Service;
	}
	
	@RequestMapping("dagachi/favPhoneBook/favList")
	public String showFavList(Model model,HttpSession session) {
		Member loginUser= (Member)session.getAttribute("loginUser");
		String register=loginUser.getMember_id();
		List<FavPhoneVO> favList = favPhoneService.getFavList(register);

		model.addAttribute("phoneBookList", favList);

		return "favPhoneBook/list";

	}

	
	@RequestMapping("dagachi/favPhoneBook/removeFavPhone")
	public String removeFavPhone(String favId,HttpSession session,Model model) {
		int favIdInt=Integer.parseInt(favId);
		favPhoneService.removeFav(favIdInt);
		
		Member loginUser= (Member)session.getAttribute("loginUser");
		String register=loginUser.getMember_id();
		

		List<FavPhoneVO> favList = favPhoneService.getFavList(register);

		model.addAttribute("phoneBookList", favList);

		
		return "favPhoneBook/ResultTable";
	}
	
	@RequestMapping("dagachi/favPhoneBook/removeCb")
	public String removeCb(String[] favIdArr,HttpSession session,Model model) {
		for(int i=0;i<favIdArr.length;i++) {
			int favIdInt=Integer.parseInt(favIdArr[i]);
			favPhoneService.removeFav(favIdInt);
			
		}
		
		Member loginUser= (Member)session.getAttribute("loginUser");
		String register=loginUser.getMember_id();
		
		List<FavPhoneVO> favList = favPhoneService.getFavList(register);

		model.addAttribute("phoneBookList", favList);

		
		return "favPhoneBook/ResultTable";
	}
	
	@RequestMapping("dagachi/favPhoneBook/detail")
	public String showDetail(String favId,Model model) {
		int favIdInt=Integer.parseInt(favId);
		
		FavPhoneVO favPhone=favPhoneService.getFav(favIdInt);
		Phone_BookVO phoneBook=phone_Book_Service.getPhoneBookDetail(favPhone.getPhone_Book_Id());
		
		String phones=phoneBook.getPhone_Book_Phone();
		String[]phoneArr=phones.split("-");
		if(phoneArr.length==3){
			
			model.addAttribute("phone1",phoneArr[0]);
			model.addAttribute("phone2",phoneArr[1]);
			model.addAttribute("phone3",phoneArr[2]);
		}
		
		
		String emails=phoneBook.getPhone_Book_Email();
		String[]emailArr=emails.split("@");
		if(emailArr.length==2){
			model.addAttribute("email1",emailArr[0]);
			model.addAttribute("email2",emailArr[1]);
		}
		
				
		model.addAttribute("phoneBook", phoneBook);
		
		return "favPhoneBook/detail";
		
	}
	 

}
