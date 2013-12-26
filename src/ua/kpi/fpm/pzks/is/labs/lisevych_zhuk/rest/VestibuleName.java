package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk.rest;

public class VestibuleName {
	private String number;
	private String name;

	public VestibuleName() {
	}

	public VestibuleName(String number, String name) {
		this.name = name;
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
