package basepackage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import basepackage.dto.PizzaDTO;
import basepackage.dto.PriceDTO;
import basepackage.genericconverter.GenericConverter;
import basepackage.model.Pizza;
import basepackage.model.Price;
import basepackage.repo.PizzaRepository;
import basepackage.repo.PriceRepository;


@Service
public class PriceServiceImpl   implements PriceService{

	
	@Autowired
	private PriceRepository priceRepo;
	
	private final GenericConverter gc=new GenericConverter();


	
	
	@Override
	public List<PriceDTO> findByPizzaId(Long pizzaId) {

		List<PriceDTO> priceDTO= new ArrayList();
		
		try {
			          List<Price> price =priceRepo.findByPizzaId(pizzaId);
			          priceDTO= price.stream().map(p->gc.convertToDto(p,PriceDTO.class )).collect(Collectors.toList());
			            
		} catch(HibernateException e) {
			
			throw new HibernateException("unable to fetch the prices",e);
			
		}
		
		
		return priceDTO;
	}
	
	

}
