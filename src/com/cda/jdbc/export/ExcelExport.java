package com.cda.jdbc.export;

import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExport {
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Map<String, Object[]> data;
    
    public ExcelExport() {
    	this.workbook = new XSSFWorkbook();
    	this.sheet = this.workbook.createSheet("Most Recent Vehicule per Model");
    	this.data = new TreeMap<String, Object[]>();
    }

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}
	public XSSFSheet getSheet() {
		return sheet;
	}
	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}
	public Map<String, Object[]> getData() {
		return data;
	}
	public void setData(Map<String, Object[]> data) {
		this.data = data;
	}
}
