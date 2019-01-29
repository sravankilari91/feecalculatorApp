package com.feecalculator.feecalculatorApp.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.feecalculator.feecalculatorApp.Model.InputTransactions;
import com.feecalculator.feecalculatorApp.Repository.InputTransactionsRepository;

@Component
public class ReadFileService {

	@Autowired
	InputTransactionsRepository inputRepository;

	public static InputTransactions getTransactionsFromFile(String[] transactions) {
		InputTransactions inputTransactions = new InputTransactions();
		inputTransactions.setExternal_Transaction_Id(transactions[0]);
		inputTransactions.setClient_Id(transactions[1]);
		inputTransactions.setSecurity_Id(transactions[2]);
		inputTransactions.setTransaction_Type(transactions[3]);
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("MM/dd/yyyy").parse(transactions[4]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inputTransactions.setTransaction_Date(date1);
		inputTransactions.setMarket_Value(BigDecimal.valueOf(Double.parseDouble(transactions[5])));
		inputTransactions.setPriority_Flag(transactions[6]);
		return inputTransactions;
	}

	public List<InputTransactions> readInputTransactionFromFile() {
		// String fileName = "InputData.csv";
		 //File resource = new ClassPathResource("data/employees.dat").getFile();
		String fileName = "C:\\Users\\Public\\InputData.csv";
		
		List<InputTransactions> inputTransactionList = new ArrayList<InputTransactions>();
		String line = "";
		BufferedReader br = null;
		try {
			int count = 1;
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {

				String[] transactions = line.split(",");
				if (count > 1) {
					InputTransactions inputTrans = ReadFileService.getTransactionsFromFile(transactions);
					inputTransactionList.add(inputTrans);
					inputRepository.save(inputTrans);
				}
				count++;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException s) {
			System.out.println(s);
		}

		return inputTransactionList;
	}

}
