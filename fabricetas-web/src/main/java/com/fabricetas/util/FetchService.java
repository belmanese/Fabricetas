package com.fabricetas.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Service;


/** It is responsible for loading fetch associations for any entity
 * Useful service to fetch dependencies
 * Created on 19/04/2017.
 * @author belman
 */
@Service
public class FetchService<T, R> {

	/**
	 * 
	 * @param containerType Entity containing the reference field
	 * @param contentType Reference field contained
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public R doFetch(T containerType, String contentType) {
		
		if(containerType == null)
			return null;
		
		R containerDto;
		
		try {
			Method methodDto = containerType.getClass().getMethod("getDto", null);
			containerDto = (R) methodDto.invoke(containerType, null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | 
				 IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		if(contentType == null)
			return containerDto;
		
		for(String content : contentType.split("-"))
			for(Method method : containerType.getClass().getMethods())
				if(method.getName().equalsIgnoreCase("set"+content))
					try {
						Method getContainer = containerType.getClass().getMethod("g"+method.getName().substring(1));
						Class<?> returnType = getContainer.getReturnType();
						Method setContent = containerDto.getClass().getMethod(method.getName(), returnType);
						setContent.invoke(containerDto, getContainer.invoke(containerType, null));
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | 
							 IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						return null;
					}
		
		return containerDto;
	}
	
}
