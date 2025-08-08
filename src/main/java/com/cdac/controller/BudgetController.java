package com.cdac.controller;

import com.cdac.dto.BudgetReqDTO;
import com.cdac.dto.BudgetRespDTO;
import com.cdac.service.BudgetService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
@CrossOrigin
public class BudgetController {

    private final BudgetService budgetService;

    // Create a new budget
    @PostMapping
    public ResponseEntity<BudgetRespDTO> createBudget(@RequestBody BudgetReqDTO dto) {
        BudgetRespDTO saved = budgetService.createBudget(dto);
        return ResponseEntity.ok(saved);
    }

    // Get budget by ID
    @GetMapping("/{id}")
    public ResponseEntity<BudgetRespDTO> getBudgetById(@PathVariable Long id) {
        BudgetRespDTO dto = budgetService.getBudgetById(id);
        return ResponseEntity.ok(dto);
    }

    // Get all budgets
    @GetMapping
    public ResponseEntity<List<BudgetRespDTO>> getAllBudgets() {
        List<BudgetRespDTO> list = budgetService.getAllBudgets();
        return ResponseEntity.ok(list);
    }

    // Get budgets by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BudgetRespDTO>> getBudgetsByUser(@PathVariable Long userId) {
        List<BudgetRespDTO> list = budgetService.getBudgetsByUserId(userId);
        return ResponseEntity.ok(list);
    }

    // Update budget by ID
    @PutMapping("/{id}")
    public ResponseEntity<BudgetRespDTO> updateBudget(@PathVariable Long id, @RequestBody BudgetReqDTO dto) {
        BudgetRespDTO updated = budgetService.updateBudget(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete budget by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.ok("Budget deleted successfully.");
    }
}
