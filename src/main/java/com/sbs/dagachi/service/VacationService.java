package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.VacationRepository;
import com.sbs.dagachi.vo.Vacation;


@Service
public class VacationService {
	
	private VacationRepository vacationRepository;
	
	public VacationService(VacationRepository vacationRepository) {
		this.vacationRepository = vacationRepository;
	}

	public Integer getTotalYear(String vacation_member) {
		return vacationRepository.getTotalYear(vacation_member);
	}
	
	public int getTotalMonth(String vacation_member) {
		return vacationRepository.getTotalMonth(vacation_member);
	}
	
	public int getDelYear(String vacation_member) {
		return vacationRepository.getDelYear(vacation_member);
	}
	
	public int getDelMonth(String vacation_member) {
		return vacationRepository.getDelMonth(vacation_member);
	}
	
	public List<Vacation> getVacation(String vacation_member) {
		return vacationRepository.getVacation(vacation_member);
	}

}
