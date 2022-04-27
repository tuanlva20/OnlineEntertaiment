package ASM.testNG.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ExcelUtils {
	public static final String CHROME_DRIVER_SRC=System.getProperty("user.dir")+"/test-resources/chromedriver";
	public static final String DATA_SRC=System.getProperty("user.dir")+"/test-resources/data/";
	public static final String IMAGES_SRC=System.getProperty("user.dir")+"/test-resources/images/";
	
	//getWorkbook
	public static XSSFWorkbook getWorkbook(String filePath) throws IOException {
		File src=new File(filePath);
		if (!src.exists()) {
			throw new IOException("Không tồn tại đường dẫn: "+filePath);
		}
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		fis.close();
		return workbook;
	}
	
	//getSheet
	public static XSSFSheet getSheet(XSSFWorkbook workbook, String sheetName) {
		XSSFSheet sheet=workbook.getSheet(sheetName);
		if (sheet==null) {
			throw new NullPointerException("Sheet "+sheetName+" không tồn tại, thêm dữ liệu thất bại!");
		}
		return sheet;
	}
	
	//Định dạng cell
	public static CellStyle getrowStyle(XSSFWorkbook workbook) {
		CellStyle rowStyle=workbook.createCellStyle();
		rowStyle.setAlignment(HorizontalAlignment.CENTER);
		rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		rowStyle.setWrapText(true);
		return rowStyle;
	}
	
	//Lấy giá trị từ hàng và cột
	public static String getCellValue(XSSFSheet sheet, int row, int col) {
		String returnValue;
		XSSFCell cell=sheet.getRow(row).getCell(col);//row-hàng, col-cột
		try {
			if (cell.getCellType() == CellType.STRING) {
				returnValue=cell.getStringCellValue();
			}else if (cell.getCellType()==CellType.NUMERIC) {
				returnValue=String.format("%.0f", cell.getNumericCellValue());
			}else {
				returnValue=cell.getStringCellValue();
			}
		} catch (Exception e) {
			returnValue="";
		}
		return returnValue;
	}
	
	//Chụp hình
	public static void takeScreenShot(WebDriver driver, String outputSrc) throws IOException {
		File screenShot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File(outputSrc));
	}
	
	//Chạy for lấy dữ liệu từ Excel
	public static Object[][] readSheetData(XSSFSheet sheet){
		int rows=sheet.getPhysicalNumberOfRows(); //Tổng cộng số row
		int columns=sheet.getRow(0).getLastCellNum(); //Tổng cộng số column
		
		Object[][] data=new Object[rows-1][columns]; //Tạo mảng 2 chiều
		
		for(int row=1;row<rows;row++) {//Tương ứng vs hàng 2 trong Excel (Hàng 1 là title)
			for(int col=0; col<columns;col++) {
				data[row-1][col]=ExcelUtils.getCellValue(sheet, row, col);
			}
		}
		return data;//Lấy dữ liệu
	}
	
	//Ghi hình vào Excel
	public static void writeImage(String image,Row row, Cell cell, XSSFSheet sheet) throws IOException {
		InputStream is=new FileInputStream(image);//Lấy hình (image là đường dẫn)
		byte[] bytes=IOUtils.toByteArray(is);//Trung gian
		
		// dùng addPicture  để đưa hình ảnh vào Workbook (chưa có hình ảnh thật)
		int pictureId=sheet.getWorkbook().addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
		
		XSSFDrawing drawing=sheet.createDrawingPatriarch();//Khởi tạo để đưa hình ảnh lên Excel
		ClientAnchor anchor=new XSSFClientAnchor();
		
		anchor.setCol1(cell.getColumnIndex());
		anchor.setRow1(row.getRowNum());
		anchor.setCol2(cell.getColumnIndex()+1);
		anchor.setRow2(row.getRowNum()+1);
		
		drawing.createPicture(anchor, pictureId);//PictureId để xác định hình bỏ vào
	}
	
	//Xuất file Excel
	public static void export(String outputsrc, XSSFWorkbook workbook) throws IOException {
		FileOutputStream out=new FileOutputStream(outputsrc);
		workbook.write(out);
		out.close();
	}
}
