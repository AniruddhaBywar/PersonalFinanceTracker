package com.cdac;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PersonalFinanceTracker {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceTracker.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		System.out.println("in model mapper creation");
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
				.setPropertyCondition(Conditions.isNotNull());// use case - PUT
		return mapper;
	}

}
