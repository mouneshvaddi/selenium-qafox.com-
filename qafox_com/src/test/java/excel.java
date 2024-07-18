import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class excel {
	@Test(dataProvider = "excel1")
	
	public void s(String username ,String  password) {
		System.out.println(username  +password);
		
	}
	@DataProvider(name="excel1")
	public Object[][] book1() throws IOException {
		
		File excelfile = new File(System.getProperty("user.dir") +"\\b1.xlsx");
		FileInputStream  fil = new FileInputStream(excelfile);
		XSSFWorkbook  workbook = new XSSFWorkbook(fil);
		
		
		XSSFSheet sheet = workbook.getSheet("sheet1");
		
		int rowcount = sheet.getLastRowNum();
		short columncount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rowcount][columncount];
		
		for(int r=0;r<rowcount;r++) {
			XSSFRow row = sheet.getRow(r+1);
			
			for (int c =0;c<columncount;c++) {
			XSSFCell cell = row.getCell(c);
			CellType celltype = cell.getCellType();
			
			switch (celltype) {
			case STRING:
			  data[r][c]=   cell.getStringCellValue();
				
				break;
				 
			case NUMERIC:	
				data[r][c]=  Integer.toString((int)cell.getNumericCellValue());
			
			
//			case BOOLEAN:
//				data[r][c]=	cell.getBooleanCellValue();
//			break;
//			
			

			
			
		
			}
			}
		}
	
	return data;
}
}
	

	
