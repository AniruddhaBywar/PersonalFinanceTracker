package com.cdac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dao.BudgetRepository;
import com.cdac.dao.CategoryRepository;
import com.cdac.dao.UserDao;
import com.cdac.dto.BudgetReqDTO;
import com.cdac.dto.BudgetRespDTO;
import com.cdac.entities.Budget;
import com.cdac.entities.Category;
import com.cdac.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserDao userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public BudgetRespDTO createBudget(BudgetReqDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Budget budget = Budget.builder()
                .amount(dto.getAmount())
                .month(dto.getMonth())
                .user(user)
                .category(category)
                .build();

        return mapToRespDTO(budgetRepository.save(budget));
    }

    @Override
    public BudgetRespDTO updateBudget(Long budgetId, BudgetReqDTO dto) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));

        budget.setAmount(dto.getAmount());
        budget.setMonth(dto.getMonth());
        // allow updating category/user if required

        return mapToRespDTO(budgetRepository.save(budget));
    }

    @Override
    public BudgetRespDTO getBudgetById(Long budgetId) {
        return mapToRespDTO(
                budgetRepository.findById(budgetId)
                        .orElseThrow(() -> new ResourceNotFoundException("Budget not found"))
        );
    }
    
    
    @Override
    public List<BudgetRespDTO> getAllBudgets() {
        List<Budget> budgets = budgetRepository.findAll();
        return budgets.stream()
                .map(this::mapToRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BudgetRespDTO> getBudgetsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        
        List<Budget> budgets = budgetRepository.findByUser(user);
        return budgets.stream()
                .map(this::mapToRespDTO)
                .collect(Collectors.toList());
    }


//    @Override
//    public List<BudgetRespDTO> getBudgetsByUser(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        return budgetRepository.findByUser(user)
//                .stream()
//                .map(this::mapToRespDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public void deleteBudget(Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        budgetRepository.delete(budget);
    }

    private BudgetRespDTO mapToRespDTO(Budget budget) {
        return BudgetRespDTO.builder()
                .budgetId(budget.getBudgetId())
                .amount(budget.getAmount())
                .month(budget.getMonth())
                .userId(budget.getUser().getUserId())
                .userName(budget.getUser().getName())
                .categoryId(budget.getCategory().getCategoryId())
                .categoryName(budget.getCategory().getName())
                .build();
    }
}
