package com.sdet.helperClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.sdet.actionClass.actionClass;
import com.sdet.stepDefination.stepDefination;

import org.apache.poi.ss.usermodel.DataFormatter;

public class helperClass {

	static stepDefination stepObj = new stepDefination();

	public <String> List<String> readExcel(int rowNum, String browser) throws IOException {

		DataFormatter formatter = new DataFormatter();
		List<String> list = new ArrayList<String>();
		String mailId, password, searchString;
		// Create an object of File class to open xlsx file
		File file = new File(
				System.getProperty("user.dir") + "/src/test/java/com/sdet/resources/datapool/OpenCart_"+browser+".xlsx");

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbookObj = new XSSFWorkbook(inputStream);

		// Read sheet inside the workbook by its name
		Sheet sheetObj = workbookObj.getSheet("Sheet1");

		Row row = sheetObj.getRow(rowNum);

		// Create a loop to print cell values in a row
		for (int j = 0; j < row.getLastCellNum(); j++) {
			Cell cell = sheetObj.getRow(rowNum).getCell(j);
			String str = (String) formatter.formatCellValue(cell);

			if (j == 0) {
				mailId = str;
				list.add(mailId);
			}

			if (j == 1) {
				password = str;
				list.add(password);
			}

			if (j == 2) {
				searchString = str;
				list.add(searchString);

			}

		}
		workbookObj.close();
		return list;
	}

	public static void takeScreenShot(String str) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) stepObj.driver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ss");
		// Move image file to new destination
		File DestFile = new File(System.getProperty("user.dir") + "/src/test/java/com/sdet/resources/screenshots/" + str
				+ "_" + dateFormat.format(new Date()) + ".png");
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}

}
