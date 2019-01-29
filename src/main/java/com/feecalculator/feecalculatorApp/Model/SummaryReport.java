package com.feecalculator.feecalculatorApp.Model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class SummaryReport {

	private String client_Id;
	private String transaction_Type;
	private Date transaction_Date;
	private String priority;
	private BigDecimal processing_Fee;

	public SummaryReport() {

	}

	public SummaryReport(String client_Id, String transaction_Type, Date transaction_Date, String priority,
			BigDecimal processing_Fee) {
		super();
		this.client_Id = client_Id;
		this.transaction_Type = transaction_Type;
		this.transaction_Date = transaction_Date;
		this.priority = priority;
		this.processing_Fee = processing_Fee;
	}

	public String getClient_Id() {
		return client_Id;
	}

	public void setClient_Id(String client_Id) {
		this.client_Id = client_Id;
	}

	public String getTransaction_Type() {
		return transaction_Type;
	}

	public void setTransaction_Type(String transaction_Type) {
		this.transaction_Type = transaction_Type;
	}

	public Date getTransaction_Date() {
		return transaction_Date;
	}

	public void setTransaction_Date(Date transaction_Date) {
		this.transaction_Date = transaction_Date;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public BigDecimal getProcessing_Fee() {
		return processing_Fee;
	}

	public void setProcessing_Fee(BigDecimal processing_Fee) {
		this.processing_Fee = processing_Fee;
	}

	@Override
	public String toString() {
		return "SummaryReport [client_Id=" + client_Id + ", transaction_Type=" + transaction_Type
				+ ", transaction_Date=" + transaction_Date + ", priority=" + priority + ", processing_Fee="
				+ processing_Fee + "]";
	}

}
