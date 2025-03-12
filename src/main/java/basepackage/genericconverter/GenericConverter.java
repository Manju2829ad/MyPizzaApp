package basepackage.genericconverter;

import javax.management.RuntimeErrorException;

import org.springframework.beans.BeanUtils;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

public class GenericConverter {

	
	
	
	public <D,E> D convertToDto(E entity,Class<D> dtoClass) {
		
		
		try {
			
	                 D dto=dtoClass.getDeclaredConstructor().newInstance();
	                 
	               BeanUtils.copyProperties(entity, dto);
	               return dto;
			    
		} catch(Exception e) {
			
		 throw new RuntimeException("Failed to convert to DTO ",e);
		}
	}
	
	
	public <D,E> E convertToEntity(D dto,Class<E> entityClass) {
		try {
			E entity=entityClass.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(dto, entity);
			return entity;
			
		} catch(Exception e) {
			
			throw new RuntimeException("Failed to Convert to Entity");
		}
		
		
	}
 	
	
	
	
	
	
	
}
