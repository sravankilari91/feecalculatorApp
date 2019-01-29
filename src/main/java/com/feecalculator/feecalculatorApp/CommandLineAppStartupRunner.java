package com.feecalculator.feecalculatorApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.feecalculator.feecalculatorApp.Service.GenerateFeeReportService;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	@Autowired
	GenerateFeeReportService generateFee;

	@Override
	public void run(String... arg0) throws Exception {

		generateFee.generateReport();

	}
}