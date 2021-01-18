package com.poc.operations.exceloperations.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.poc.operations.exceloperations.dto.ExcelDto;

@Service
public class ExcelGenerator {
	
	public File generateExcel(ExcelDto excelDto) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(); 
		String[] headers = excelDto.getHeaders(); //{"Col-A", "Col-B", "Col-C"};
		Row headerRow = sheet.createRow(0);
		int headerIndex = 0;
		for (String header : headers) {
			Cell cell = headerRow.createCell(headerIndex);
			cell.setCellValue(header);
			headerIndex = headerIndex + 1;
		}
		String[][] rowData = excelDto.getRowData();
			/*{
				{"A1", "B1", "C1"},
				{"A2", "B2", "C2"}
			};*/
		
		for(int i=0; i<rowData.length; i++) {
			Row dataRow = sheet.createRow(1+i);
			for(int j=0; j<rowData[0].length; j++) {
				dataRow.createCell(j).setCellValue(rowData[i][j]);
			}
		}
		
		FileOutputStream fileOutputStream = null;
		File excelFile = null;
		try {
			excelFile = new File(excelDto.getFileName());
			fileOutputStream = new FileOutputStream(excelFile);
			workbook.write(fileOutputStream);
			fileOutputStream.flush();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			IOUtils.closeQuietly(fileOutputStream);
			try {
				workbook.close();
				if(Objects.nonNull(fileOutputStream))
					fileOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return excelFile;
	}

}
