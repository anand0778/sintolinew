package com.sintoli.repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sintoli.entity.Expenses;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses,Long>{
	//SELECT * FROM tbl_expenses WHERE category=?
 Page<Expenses> findByCategory(String category, Pageable page);
 
 //SELECT * FORM tbl_expenses WHERE name LIKE '%keyword'
 Page<Expenses> findByNameContaining(String keyword,Pageable page);
 
 // SELECT * FROM tbl_expenses WHERE date BETWEEN 'startDate' and 'endDate'
 Page<Expenses> findByDateBetween(Date startDate, Date endDate, Pageable page);
 
}
