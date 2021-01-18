package com.poc.operations.exceloperations.api;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.operations.exceloperations.dto.ExcelDto;
import com.poc.operations.exceloperations.service.ExcelGenerator;

@RestController
public class ExcelController {
	
	@Autowired
	ExcelGenerator excelGenerator;
	
	@RequestMapping(value ="generateExcel", method= RequestMethod.POST)
	public ResponseEntity<ByteArrayResource> generateExcel(@RequestBody ExcelDto excelDto) throws IOException {
		File excelFile = excelGenerator.generateExcel(excelDto);
		byte[] byteArray = readFileToByteArray(excelFile);
		String fileName = excelFile.getName();
		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
			.header( "Content-disposition", "attachment;filename=" + fileName + ".xlsx" )
			.body(new ByteArrayResource(byteArray));
		
	}


}
