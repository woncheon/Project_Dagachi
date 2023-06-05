package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.BookMarkRepository;
import com.sbs.dagachi.vo.BookMark;

@Service
public class BookMarkService {
	private BookMarkRepository bookMarkRepository;

	public BookMarkService(BookMarkRepository bookMarkRepository) {
		this.bookMarkRepository=bookMarkRepository;
	}
	
	public void registFav(String register, String mcode, String page_name) {
		bookMarkRepository.registFav(register, mcode,page_name);
	}
	
	public BookMark dupCheck(String register, String mcode) {
		return bookMarkRepository.dupCheck(register, mcode);
	
	}
	
	public void deleteFav(String register, String mcode) {
		bookMarkRepository.deleteFav(register, mcode);
	}
	
	public List<BookMark>getFavLIst(String register){
		return bookMarkRepository.getFavList(register);
	}
	
}
