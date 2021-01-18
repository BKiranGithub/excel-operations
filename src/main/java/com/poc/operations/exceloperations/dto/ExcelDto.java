package com.poc.operations.exceloperations.dto;

public class ExcelDto {
	
	String fileName;
	String[] headers;
	String[][] rowData;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String[] getHeaders() {
		return headers;
	}
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	public String[][] getRowData() {
		return rowData;
	}
	public void setRowData(String[][] rowData) {
		this.rowData = rowData;
	}

}
