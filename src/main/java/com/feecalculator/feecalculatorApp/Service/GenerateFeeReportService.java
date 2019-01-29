package com.feecalculator.feecalculatorApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feecalculator.feecalculatorApp.Model.InputTransactions;
import com.feecalculator.feecalculatorApp.Model.SummaryReport;

@Service
public class GenerateFeeReportService {
	@Autowired
	ReadFileService readFileService;
	@Autowired
	ConvertToCSVService convertToCsv;
	@Autowired
	CalculateProcessFeeService calculateProcessFee;

	public void generateReport() {

		List<InputTransactions> inputTransactionList = readFileService.readInputTransactionFromFile();
		List<SummaryReport> summaryReportsList = calculateProcessFee.calculateProcessFee(inputTransactionList);
		convertToCsv.convertToCSV(summaryReportsList);

	}

}
