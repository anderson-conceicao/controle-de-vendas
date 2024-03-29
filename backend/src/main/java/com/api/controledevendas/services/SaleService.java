package com.api.controledevendas.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.controledevendas.dto.SalesByDateDTO;
import com.api.controledevendas.dto.SalesByPaymentMethodDTO;
import com.api.controledevendas.dto.SalesByStoreDTO;
import com.api.controledevendas.dto.SalesDTO;
import com.api.controledevendas.dto.SalesSummaryDTO;
import com.api.controledevendas.entities.Gender;
import com.api.controledevendas.entities.Sale;
import com.api.controledevendas.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	@Transactional(readOnly = true)
	public Page<SalesDTO> sales(String minDate, String maxDate, String gender, Pageable pageable) {
		LocalDate min = "".equals(minDate) ? null : LocalDate.parse(minDate);
		LocalDate max = "".equals(maxDate) ? null : LocalDate.parse(maxDate);
		Gender genderEnum = "".equals(gender) ? null : Gender.valueOf(gender);
		Page<Sale> page = repository.searchPage(min, max, genderEnum, pageable);
		repository.salesWithOtherEntities(page.getContent());
		return page.map(x -> new SalesDTO(x));
	}
	
	@Transactional(readOnly = true)
	public List<SalesByStoreDTO> salesByStore(String minDate, String maxDate, String gender) {
		LocalDate min = "".equals(minDate) ? null : LocalDate.parse(minDate);
		LocalDate max = "".equals(maxDate) ? null : LocalDate.parse(maxDate);
		Gender genderEnum = "".equals(gender) ? null : Gender.valueOf(gender);
		return repository.salesByStore(min, max, genderEnum);
	}

	@Transactional(readOnly = true)
	public List<SalesByPaymentMethodDTO> salesByPaymentMethod(String minDate, String maxDate, String gender) {
		LocalDate min = "".equals(minDate) ? null : LocalDate.parse(minDate);
		LocalDate max = "".equals(maxDate) ? null : LocalDate.parse(maxDate);
		Gender genderEnum = "".equals(gender) ? null : Gender.valueOf(gender);
		return repository.salesByPaymentMethod(min, max, genderEnum);
	}
	
	@Transactional(readOnly = true)
	public List<SalesByDateDTO> salesByDate(String minDate, String maxDate, String gender) {
		LocalDate min = "".equals(minDate) ? null : LocalDate.parse(minDate);
		LocalDate max = "".equals(maxDate) ? null : LocalDate.parse(maxDate);
		Gender genderEnum = "".equals(gender) ? null : Gender.valueOf(gender);
		return repository.salesByDate(min, max, genderEnum);
	}
	
	@Transactional(readOnly = true)
	public SalesSummaryDTO salesSummary(String minDate, String maxDate, String gender) {
		LocalDate min = "".equals(minDate) ? null : LocalDate.parse(minDate);
		LocalDate max = "".equals(maxDate) ? null : LocalDate.parse(maxDate);
		Gender genderEnum = "".equals(gender) ? null : Gender.valueOf(gender);
		return repository.salesSummary(min, max, genderEnum);		
	}
}
