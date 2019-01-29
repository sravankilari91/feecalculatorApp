package com.feecalculator.feecalculatorApp.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.feecalculator.feecalculatorApp.Model.SummaryReport;

@Component
public class ConvertToCSVService {

	private static final String CSV_SEPARATOR = ",";

	public void convertToCSV(List<SummaryReport> summaryReportsList) {

		try {
			String fileName = "C:\\Users\\Public\\SummaryReport.csv";
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
			StringBuffer oneLine = new StringBuffer();
			oneLine.append("Client Id");
			oneLine.append(CSV_SEPARATOR);
			oneLine.append("Transaction Type");
			oneLine.append(CSV_SEPARATOR);
			oneLine.append("Transaction Date");
			oneLine.append(CSV_SEPARATOR);
			oneLine.append("Priority");
			oneLine.append(CSV_SEPARATOR);
			oneLine.append("Processing Fee");
			bw.write(oneLine.toString());
			bw.newLine();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			for (SummaryReport summaryReport : summaryReportsList) {
				StringBuffer line = new StringBuffer();
				line.append(summaryReport.getClient_Id());
				line.append(CSV_SEPARATOR);
				line.append(summaryReport.getTransaction_Type());
				line.append(CSV_SEPARATOR);
				line.append(sdf.format(summaryReport.getTransaction_Date()));
				line.append(CSV_SEPARATOR);
				line.append(summaryReport.getPriority());
				line.append(CSV_SEPARATOR);
				line.append(summaryReport.getProcessing_Fee().toString());

				bw.write(line.toString());
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (UnsupportedEncodingException e) {
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

}
