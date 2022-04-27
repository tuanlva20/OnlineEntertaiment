package ASM.testNG.logs;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ASM.testNG.utils.ExcelUtils;

public class VideoData extends TestData implements Log<VideoData> {
	private String id;
	private String title;
	private String href;
	private Integer view;
	private String description;
	private Boolean active;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public void writeLog(String src, String sheetName, Set<VideoData> logs) throws IOException {
		XSSFWorkbook workbook = ExcelUtils.getWorkbook(src);
		XSSFSheet sheet = ExcelUtils.getSheet(workbook, sheetName);

		// Xử lý ghi tiếp từ dòng hiện tại
		int startRow = -1;
		int lastRow = sheet.getPhysicalNumberOfRows();
		if (lastRow < startRow) {
			lastRow = startRow;
		}

		CellStyle rowStyle = ExcelUtils.getrowStyle(workbook);

		// Duyệt qua bộ dũ liệu
		for (VideoData log : logs) {
			Row row = sheet.createRow(lastRow);
			row.setHeightInPoints(60);
			row.setRowStyle(rowStyle);
			log.writeDataRow(log, row, sheet);
			lastRow++;
		}
		// Xuất File
		ExcelUtils.export(src, workbook);
	}

	@Override
	public void writeDataRow(VideoData log, Row row, XSSFSheet sheet) throws IOException {
		CellStyle globalStyle = row.getRowStyle();

		Cell cell;

		cell = row.createCell(0);
		cell.setCellValue(log.getId());
		cell.setCellStyle(globalStyle);

		cell = row.createCell(1);
		cell.setCellValue(log.getTitle());
		cell.setCellStyle(globalStyle);
		
		cell = row.createCell(2);
		cell.setCellValue(log.getHref());
		cell.setCellStyle(globalStyle);
		
		cell = row.createCell(3);
		cell.setCellValue(log.getView());
		System.out.println("long view: "+log.getView());
		cell.setCellStyle(globalStyle);

		writeTestData(4, row, sheet);
	}
	
}
