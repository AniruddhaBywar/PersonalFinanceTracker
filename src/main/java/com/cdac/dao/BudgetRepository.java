package com.cdac.dao;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entities.Budget;
import com.cdac.entities.Category;
import com.cdac.entities.User;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUser(User user);
    Optional<Budget> findByUserAndCategoryAndMonth(User user, Category category, YearMonth month);
}
