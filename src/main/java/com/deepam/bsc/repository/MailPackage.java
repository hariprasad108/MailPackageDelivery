package com.deepam.bsc.repository;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MailPackage {

	// weight in kg
	private BigDecimal weightKg;

	// postal code
	private String postalCode;

	public MailPackage() {

	}

	public MailPackage(String postalCode, BigDecimal weightKg) {
		this.weightKg = weightKg;
		this.postalCode = postalCode;
	}

	public BigDecimal getWeightKg() {
		return weightKg;
	}

	public void setWeightKg(BigDecimal weightKg) {
		this.weightKg = weightKg;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#,###.000");
		return postalCode + " " + df.format(weightKg);
	}
}
