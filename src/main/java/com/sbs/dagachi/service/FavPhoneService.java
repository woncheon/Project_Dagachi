package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.FavPhoneRepository;
import com.sbs.dagachi.vo.FavPhoneVO;

@Service
public class FavPhoneService {
	
	FavPhoneRepository favPhoneRepository;
	
	public FavPhoneService(FavPhoneRepository favPhoneRepository) {
		this.favPhoneRepository=favPhoneRepository;
	}

	public List<FavPhoneVO> getFavList(String register) {
		
		return favPhoneRepository.getFavList(register);
	}
	
	public void removeFav(int favId) {
		favPhoneRepository.removeFav(favId);
	}
	
	public void removeFavAtPhone(int phone_book_id, String register) {
		favPhoneRepository.removeFavAtPhone(phone_book_id,register);
	}
	
	public FavPhoneVO getFav(int favId) {
		return favPhoneRepository.getFav(favId);
	}
	
	public void registFav(int favId, String register) {
		favPhoneRepository.registFav(favId, register);
	}
	
	public List<FavPhoneVO>getFavApp(String register){
		return favPhoneRepository.getFavApp(register);
	}
}
