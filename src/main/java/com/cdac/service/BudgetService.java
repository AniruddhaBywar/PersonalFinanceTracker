package com.cdac.service;

import java.util.List;

import com.cdac.dto.BudgetReqDTO;
import com.cdac.dto.BudgetRespDTO;

public interface BudgetService {
    BudgetRespDTO createBudget(BudgetReqDTO dto);
    BudgetRespDTO updateBudget(Long budgetId, BudgetReqDTO dto);
    BudgetRespDTO getBudgetById(Long budgetId);
    
    List<BudgetRespDTO> getAllBudgets();
    
    List<BudgetRespDTO> getBudgetsByUserId(Long userId);
    
   // List<BudgetRespDTO> getBudgetsByUser(Long userId);
    void deleteBudget(Long budgetId);
}
