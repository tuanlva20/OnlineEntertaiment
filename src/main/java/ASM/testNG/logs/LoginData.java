package ASM.testNG.logs;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ASM.testNG.utils.ExcelUtils;

public class LoginData extends TestData implements Log<LoginData> {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void writeLog(String src, String sheetName, Set<LoginData> logs) throws IOException {
		XSSFWorkbook workbook=ExcelUtils.getWorkbook(src);
		XSSFSheet sheet=ExcelUtils.getSheet(workbook, sheetName);
		
		//Xử lý ghi tiếp từ dòng hiện tại
		int startRow=-1;
		int lastRow=sheet.getPhysicalNumberOfRows();//lastRow bằng hàng cuối cùng
		if (lastRow<startRow) {
			lastRow=startRow;
		}
		
		CellStyle rowStyle=ExcelUtils.getrowStyle(workbook);
		
		//Duyệt qua bộ dũ liệu
		for (LoginData log : logs) {
			Row row=sheet.createRow(lastRow);
			row.setHeightInPoints(60);
			row.setRowStyle(rowStyle);
			log.writeDataRow(log, row, sheet);
			lastRow++;
		}
		//Xuất File
		ExcelUtils.export(src, workbook);
	}

	@Override
	public void writeDataRow(LoginData log, Row row, XSSFSheet sheet) throws IOException {
		CellStyle globalStyle=row.getRowStyle();
		
		Cell cell;
		
		cell=row.createCell(0);
		cell.setCellValue(log.getUsername());
		cell.setCellStyle(globalStyle);
		
		cell=row.createCell(1);
		cell.setCellValue(log.getPassword());
		cell.setCellStyle(globalStyle);
		
		writeTestData(2, row, sheet);
	}

}
