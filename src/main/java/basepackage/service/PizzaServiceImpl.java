package basepackage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;

import basepackage.dto.PizzaDTO;
import basepackage.genericconverter.GenericConverter;
import basepackage.model.Pizza;
import basepackage.repo.PizzaRepository;


@Service
public class PizzaServiceImpl implements PizzaService {

	
	@Autowired
	private PizzaRepository pizzaRepo;
	
	private final GenericConverter gc=new GenericConverter();
	
	
	@Override
	public  List<PizzaDTO> getPizzaByCategory(String category) {
		
		System.out.println(category);
		
		System.out.println("Hello");
		
		
		
List<PizzaDTO> pizzaDTO=new ArrayList();
		try {
	                  List<Pizza> pizza	=pizzaRepo.getPizzaByCategory(category);
		
	                  //converting the pizza object to DTO 
		 pizzaDTO=  pizza.stream().map(piz->gc.convertToDto(piz,PizzaDTO.class )).collect(Collectors.toList());
		
		} catch(HibernateException e) {
			
			throw new HibernateException("unable to fetch pizza"+category,e);
		}
		
		return pizzaDTO;
	}

	
	
}
