package com.feecalculator.feecalculatorApp.Model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InputTransactions {

	@Id
	private String external_Transaction_Id;
	private String client_Id;
	private String security_Id;
	private String transaction_Type;
	private Date transaction_Date;
	private BigDecimal market_Value;
	private String priority_Flag;
	private String fee_calculated;

	public InputTransactions() {

	}

	public InputTransactions(String external_Transaction_Id, String client_Id, String security_Id,
			String transaction_Type, Date transaction_Date, BigDecimal market_Value, String priority_Flag,
			String fee_calculated) {
		super();
		this.external_Transaction_Id = external_Transaction_Id;
		this.client_Id = client_Id;
		this.security_Id = security_Id;
		this.transaction_Type = transaction_Type;
		this.transaction_Date = transaction_Date;
		this.market_Value = market_Value;
		this.priority_Flag = priority_Flag;
		this.fee_calculated = fee_calculated;
	}

	public String getExternal_Transaction_Id() {
		return external_Transaction_Id;
	}

	public void setExternal_Transaction_Id(String external_Transaction_Id) {
		this.external_Transaction_Id = external_Transaction_Id;
	}

	public String getClient_Id() {
		return client_Id;
	}

	public void setClient_Id(String client_Id) {
		this.client_Id = client_Id;
	}

	public String getSecurity_Id() {
		return security_Id;
	}

	public void setSecurity_Id(String security_Id) {
		this.security_Id = security_Id;
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

	public BigDecimal getMarket_Value() {
		return market_Value;
	}

	public void setMarket_Value(BigDecimal market_Value) {
		this.market_Value = market_Value;
	}

	public String getPriority_Flag() {
		return priority_Flag;
	}

	public void setPriority_Flag(String priority_Flag) {
		this.priority_Flag = priority_Flag;
	}

	public String getFee_calculated() {
		return fee_calculated;
	}

	public void setFee_calculated(String fee_calculated) {
		this.fee_calculated = fee_calculated;
	}

	@Override
	public String toString() {
		return "InputTransactions [external_Transaction_Id=" + external_Transaction_Id + ", client_Id=" + client_Id
				+ ", security_Id=" + security_Id + ", transaction_Type=" + transaction_Type + ", transaction_Date="
				+ transaction_Date + ", market_Value=" + market_Value + ", priority_Flag=" + priority_Flag
				+ ", fee_calculated=" + fee_calculated + "]";
	}

}
