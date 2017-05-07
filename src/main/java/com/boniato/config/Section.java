package com.boniato.config;

/**
 * Created by Lee on 2017. 5. 5..
 */
public enum Section {
	HOME("Home"), POST("Post"), CATEGORY("Category");

	private String value;

	Section(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
