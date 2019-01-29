package com.feecalculator.feecalculatorApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.feecalculator.feecalculatorApp.Model.InputTransactions;

@Repository
public interface InputTransactionsRepository extends JpaRepository<InputTransactions, String> {

}
