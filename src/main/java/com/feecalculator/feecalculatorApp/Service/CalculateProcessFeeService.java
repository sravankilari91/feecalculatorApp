package com.feecalculator.feecalculatorApp.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feecalculator.feecalculatorApp.Model.InputTransactions;
import com.feecalculator.feecalculatorApp.Model.SummaryReport;
import com.feecalculator.feecalculatorApp.Repository.InputTransactionsRepository;

@Component
public class CalculateProcessFeeService {

	public static final BigDecimal INTRA_DAY_PROCESS_FEE = BigDecimal.valueOf(10);
	public static final BigDecimal HIGH_PRIORITY_PROCESS_FEE = BigDecimal.valueOf(500);
	public static final BigDecimal NRML_PRI_SELL_WD_PROCESS_FEE = BigDecimal.valueOf(100);
	public static final BigDecimal NRML_PRI_BUY_DEP_PROCESS_FEE = BigDecimal.valueOf(50);

	@Autowired
	InputTransactionsRepository inputRepository;

	public List<SummaryReport> calculateIntraDayProcessFee(List<InputTransactions> inputTransactionList) {
		List<SummaryReport> summaryReportsList = new ArrayList<SummaryReport>();
		List<String> buysellList = new ArrayList<>();
		buysellList.add("SELL");
		buysellList.add("BUY");
		List<InputTransactions> tempInputList = inputTransactionList;
		ListIterator<InputTransactions> iterator = inputTransactionList.listIterator();
		while (iterator.hasNext()) {
			InputTransactions input = iterator.next();
			ListIterator<InputTransactions> tempIterator = tempInputList.listIterator();
			boolean removeFlag = false;
			while (tempIterator.hasNext()) {
				InputTransactions tempinput = tempIterator.next();
				if (tempinput.getClient_Id().equals(input.getClient_Id())) {
					if (tempinput.getSecurity_Id().equals(input.getSecurity_Id())) {
						if (tempinput.getTransaction_Date().equals(input.getTransaction_Date())) {
							if (buysellList.contains(input.getTransaction_Type())
									&& buysellList.contains(tempinput.getTransaction_Type())) {
								if (!tempinput.getTransaction_Type().equals(input.getTransaction_Type())) {
									tempinput.setFee_calculated("Y");
									input.setFee_calculated("Y");
									inputRepository.save(tempinput);
									inputRepository.save(input);
									SummaryReport sr = new SummaryReport();
									sr.setClient_Id(input.getClient_Id());
									sr.setPriority(input.getPriority_Flag());
									sr.setProcessing_Fee(INTRA_DAY_PROCESS_FEE);
									sr.setTransaction_Date(input.getTransaction_Date());
									sr.setTransaction_Type(input.getTransaction_Type());
									summaryReportsList.add(sr);
									SummaryReport sr1 = new SummaryReport();
									sr1.setClient_Id(tempinput.getClient_Id());
									sr1.setPriority(tempinput.getPriority_Flag());
									sr1.setProcessing_Fee(INTRA_DAY_PROCESS_FEE);
									sr1.setTransaction_Date(tempinput.getTransaction_Date());
									sr1.setTransaction_Type(tempinput.getTransaction_Type());
									summaryReportsList.add(sr1);
									removeFlag = true;
								}
							}
						}
					}
				}
			}
			if (removeFlag) {
				iterator.remove();
			}
		}
		return summaryReportsList;
	}

	public List<SummaryReport> calculateProcessFee(List<InputTransactions> inputTransactionList) {

		List<SummaryReport> summaryReportsList = calculateIntraDayProcessFee(inputTransactionList);
		inputTransactionList = inputRepository.findAll();
		// inputTransactionList = inputTransactionList1;
		Set<String> clientSet = new HashSet<>();
		for (InputTransactions input : inputTransactionList) {
			if (input.getFee_calculated() == null) {
				SummaryReport summaryReport = new SummaryReport();
				summaryReport.setClient_Id(input.getClient_Id());
				summaryReport.setTransaction_Type(input.getTransaction_Type());
				summaryReport.setPriority(input.getPriority_Flag());
				summaryReport.setTransaction_Date(input.getTransaction_Date());
				BigDecimal processing_Fee = null;
				boolean flag = true;
				if (input.getPriority_Flag().equals("Y")) {
					processing_Fee = HIGH_PRIORITY_PROCESS_FEE;
				} else if (input.getTransaction_Type().equals("SELL")
						|| input.getTransaction_Type().equals("WITHDRAW")) {
					processing_Fee = NRML_PRI_SELL_WD_PROCESS_FEE;
				} else if (input.getTransaction_Type().equals("BUY") || input.getTransaction_Type().equals("DEPOSIT")) {
					processing_Fee = NRML_PRI_BUY_DEP_PROCESS_FEE;
				}
				if (clientSet.contains(input.getClient_Id())) {
					for (SummaryReport report : summaryReportsList) {
						if (report.getClient_Id().equals(input.getClient_Id())) {
							if (report.getTransaction_Date().equals(input.getTransaction_Date())
									&& report.getTransaction_Type().equals(input.getTransaction_Type())) {
								BigDecimal tempFee = report.getProcessing_Fee();
								processing_Fee = tempFee.add(processing_Fee);
								summaryReport.setProcessing_Fee(processing_Fee);
								ListIterator<SummaryReport> summary_iterator = summaryReportsList.listIterator();

								while (summary_iterator.hasNext()) {
									SummaryReport sr = summary_iterator.next();
									if (sr.getClient_Id().equals(summaryReport.getClient_Id())) {
										// Replace element
										summary_iterator.set(summaryReport);
										flag = false;
										break;
									}
								}
							}
						}
					}
				} else {
					clientSet.add(input.getClient_Id());
				}
				if (flag) {
					summaryReport.setProcessing_Fee(processing_Fee);

					summaryReportsList.add(summaryReport);
				}
			}
		}
		return summaryReportsList;
	}

}
