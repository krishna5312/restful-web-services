package com.rest.service.restful.web.services.json.filter.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.service.restful.web.services.json.filter.data.Student;

@RestController
public class FilteringController {
	
	@GetMapping("filter-cred")
	public MappingJacksonValue getAllStudents() {
		List<Student> student = Arrays.asList(new Student("Chaitanya","absjasd","JNTU",1));
		String filterNames[]  = {"name","id","college"};
		MappingJacksonValue mapping = getTheFilteredMapping(student, filterNames);
		return mapping;
	}
	
	@GetMapping("filter-id")
	public MappingJacksonValue getStudentsWithoutId() {
		
		  List<Student> students = Arrays.asList(new
				  Student("Chaitanya","absjasd","JNTU",1),new
				  Student("Krishna","absjasd","JNTUCEH",2));
		  String filterNames[]  = {"name","college"};
		MappingJacksonValue mapping = getTheFilteredMapping(students,filterNames);
		return mapping;
	}

	private MappingJacksonValue getTheFilteredMapping(List<Student> students, String ... filedNames) {
		MappingJacksonValue mapping = new MappingJacksonValue(students);
		SimpleBeanPropertyFilter idFilter = SimpleBeanPropertyFilter.filterOutAllExcept(filedNames);
		FilterProvider filters = new SimpleFilterProvider().addFilter("studentFilter", idFilter);
		mapping.setFilters(filters );
		return mapping;
	}

}
