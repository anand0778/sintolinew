package com.sintoli.ExpenseApi;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sintoli.entity.Expenses;
import com.sintoli.repository.ExpenseRepository;
import com.sintoli.service.ExpenseService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExpenseApiApplicationTests {

	@Autowired
	ExpenseService service;
	
	   @Autowired
      ExpenseRepository repository;	
	@Test
	public void getAllExpensesTest() {
	List<Expenses>	expenses=repository.findAll();
	Assertions.assertThat(expenses.size()).isGreaterThan(0);
	}

}
