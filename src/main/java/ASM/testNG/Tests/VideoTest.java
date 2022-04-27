package ASM.testNG.Tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ASM.testNG.logs.VideoData;
import ASM.testNG.utils.ExcelUtils;
import ASM.testNG.utils.ScreenRecorderUtil;

public class VideoTest {
	public WebDriver driver;
	private final String SRC = ExcelUtils.DATA_SRC + "Videos_Test.xlsx";
	private Set<VideoData> logs;// Map
	private VideoData data;
	
	@BeforeClass
	public void init() throws Exception {
		ScreenRecorderUtil.startRecord("CRUDVideoTest");
		System.setProperty("webdriver.chrome.driver", ExcelUtils.CHROME_DRIVER_SRC);
		logs = new LinkedHashSet<>();
		// Đảm bảo thứ tự dữ liệu test không bị thay đổi
	}

	@BeforeMethod
	public void setup() {
		// Khởi tạo và thiết lập thông số cho web driver
		driver = new ChromeDriver();
		driver.get("http://localhost:8181/ASSM-Nhom/client/login");
		driver.manage().window().maximize();
		data = new VideoData();// Khời tạo từ đầu dữ liệu mới
	}

	// Login
	private void processLogin() {
		WebElement unameInput = driver.findElement(By.name("username"));
		unameInput.sendKeys("anhtuan");

		WebElement pwordInput = driver.findElement(By.name("password"));
		pwordInput.sendKeys("456");

		WebElement loginBtn = driver.findElement(By.name("loginBtn"));
		loginBtn.submit();

	}

	// processNewTest
	private void processNewTest() {
		WebElement userManagerBtn = driver.findElement(By.name("videoManager"));
		userManagerBtn.click();

		WebElement editBtn = driver.findElement(By.xpath("//*[@id=\"table\"]/div/table/tbody/tr[1]/td[5]/button[1]"));
		editBtn.click();

		WebElement newBtn = driver.findElement(By.name("newBtn"));
		newBtn.click();
	}

	// processUpdateTest
	private void processUpdateTest(String id, String title, String href, String view, String description, Boolean active) {
		WebElement videoManager = driver.findElement(By.name("videoManager"));
		videoManager.click();

		WebElement editBtn = driver.findElement(By.name("editBtn"));
		editBtn.click();
		
		processNewTest();
		
		WebElement idInput = driver.findElement(By.name("id"));
		idInput.sendKeys(id);

		WebElement titleInput = driver.findElement(By.name("title"));
		titleInput.sendKeys(title);

		WebElement hrefInput = driver.findElement(By.name("href"));
		hrefInput.sendKeys(href);

		WebElement viewInput = driver.findElement(By.name("view"));
		viewInput.sendKeys(String.valueOf(view));
		
		WebElement descriptionInput = driver.findElement(By.xpath("//*[@id=\"London\"]/div/div[2]/div/div/form/div[5]/div/input"));
		descriptionInput.sendKeys(String.valueOf(description));
		
		WebElement activeRadio=driver.findElement(By.id("active"));
		WebElement nonactiveRadio=driver.findElement(By.id("nonactive"));
		if (active==true) {
			activeRadio.click();
		}else {
			nonactiveRadio.click();
		}

		WebElement updateBtn = driver.findElement(By.name("updateBtn"));
		updateBtn.click();
	}

	// processDeleteTest
	private void processDeleteTest() {
		processNewTest();
		WebElement deleteBtn = driver.findElement(By.name("deleteBtn"));
		deleteBtn.click();
	}

	// process Create Test
	private void processCreateTest(String id, String title, String href, String view, String description, boolean active) {
		WebElement videoManagerBtn = driver.findElement(By.name("videoManager"));
		videoManagerBtn.click();

		WebElement editBtn = driver.findElement(By.name("editBtn"));
		editBtn.click();

		WebElement newBtn = driver.findElement(By.name("newBtn"));
		newBtn.click();

		WebElement idInput = driver.findElement(By.name("id"));
		idInput.sendKeys(id);

		WebElement titleInput = driver.findElement(By.name("title"));
		titleInput.sendKeys(title);

		WebElement hrefInput = driver.findElement(By.name("href"));
		hrefInput.sendKeys(href);

		WebElement viewInput = driver.findElement(By.name("view"));
		viewInput.clear();
		viewInput.sendKeys(view);
		
		WebElement descriptionInput = driver.findElement(By.xpath("//*[@id=\"London\"]/div/div[2]/div/div/form/div[5]/div/input"));
		descriptionInput.sendKeys(description);
		
		WebElement activeRadio=driver.findElement(By.id("active"));
		WebElement nonactiveRadio=driver.findElement(By.id("nonactive"));
		if (active) {
			activeRadio.click();
		}else {
			nonactiveRadio.click();
		}

		WebElement createBtn = driver.findElement(By.name("createBtn"));
		createBtn.click();
	}

	// Test themmoi
	@Test(priority = 0)
	public void ThemmoiTest() {
		processLogin();
		processNewTest();

		String actual = driver.getCurrentUrl();
		String expected = "http://localhost:8181/ASSM-Nhom/admin/video?action=new";

		data.setId("Null");
		data.setTitle("Null");
		data.setHref("Null");
		data.setView(0);
		data.setAction("Test New function");
		data.setLogTime(new Date());
		data.setExpected(expected);
		data.setActual(actual);

		assertEquals(actual, expected);
	}

	// Test Delete 1
	@Test(priority = 3, enabled = false)
	public void DeleteTest_0() {
		processLogin();

		WebElement userManagerBtn = driver.findElement(By.name("userManager"));
		userManagerBtn.click();

		WebElement editBtn = driver.findElement(By.name("editBtn"));
		editBtn.click();
		
		WebElement idInput = driver.findElement(By.name("id"));
		String id=idInput.getAttribute("value");

		WebElement titleInput = driver.findElement(By.name("title"));
		String title=titleInput.getAttribute("value");

		WebElement hrefInput = driver.findElement(By.name("href"));
		String href=hrefInput.getAttribute("value");

		WebElement viewInput = driver.findElement(By.name("view"));
		Integer view=Integer.parseInt(viewInput.getAttribute("value"));
		
		WebElement deleteBtn = driver.findElement(By.name("deleteBtn"));
		deleteBtn.click();

		String actual = driver.getCurrentUrl();
		String expected = "http://localhost:8181/ASSM-Nhom/admin/video?action=delete";

		data.setId(id);
		data.setTitle(title);
		data.setHref(href);
		data.setView(view);
		data.setAction("Test Delete function");
		data.setLogTime(new Date());
		data.setExpected(expected);
		data.setActual(actual);

		assertEquals(actual, expected);
	}
	
	// Test Delete 2
		@Test(priority = 5, enabled = false)
		public void DeleteTest_1() {
			processLogin();

			WebElement userManagerBtn = driver.findElement(By.name("userManager"));
			userManagerBtn.click();

			WebElement editBtn = driver.findElement(By.name("editBtn"));
			editBtn.click();
			
			WebElement idInput = driver.findElement(By.name("id"));
			String id=idInput.getAttribute("value");

			WebElement titleInput = driver.findElement(By.name("title"));
			String title=titleInput.getAttribute("value");

			WebElement hrefInput = driver.findElement(By.name("href"));
			String href=hrefInput.getAttribute("value");

			WebElement viewInput = driver.findElement(By.name("view"));
			Integer view=Integer.parseInt(viewInput.getAttribute("value"));
			
			WebElement deleteBtn = driver.findElement(By.name("deleteBtn"));
			deleteBtn.click();

			String actual = driver.getCurrentUrl();
			String expected = "http://localhost:8181/ASSM-Nhom/admin/video?action=delete";

			data.setId(id);
			data.setTitle(title);
			data.setHref(href);
			data.setView(view);
			data.setAction("Test Delete function");
			data.setLogTime(new Date());
			data.setExpected(expected);
			data.setActual(actual);

			assertEquals(actual, expected);
		}
		
	// Test Delete 3
	@Test(priority = 4, enabled = false)
	public void DeleteTest_2() {
		processLogin();
		processDeleteTest();

		String actual = driver.getCurrentUrl();
		String expected = "http://localhost:8181/ASSM-Nhom/admin/user/delete";

		data.setId(null);
		data.setTitle(null);
		data.setHref(null);
		data.setView(0);
		data.setAction("Test Delete function");
		data.setLogTime(new Date());
		data.setExpected(expected);
		data.setActual(actual);

		assertEquals(actual, expected);
	}

	// Test multipleUpdate
	@Test(dataProvider = "updateData", priority = 2)
	public void UpdateTests(String id, String title, String href, String view, String description, String active, String expected)
			throws InterruptedException {
		boolean activeBoolean;
		if (active.equals("0")) {
			activeBoolean=false;
		}else {
			activeBoolean=true;
		}
		processLogin();
		processUpdateTest(id, title, href, view, description, activeBoolean);

		// actual
		String currentUrl = driver.getCurrentUrl();

		// Gán dữ liệu cho các Fields của đối tượng
		data.setId(id);
		data.setTitle(title);
		data.setHref(href);
		data.setView(Integer.parseInt(view));
		data.setAction("Test Update function");
		data.setLogTime(new Date());
		data.setExpected(expected);
		data.setActual(currentUrl);

		// Thread.sleep(3000);
		assertEquals(expected, currentUrl);
	}

	// Test multipleCreate
	@Test(dataProvider = "createData", priority = 1)
	public void CreateTests(String id, String title, String href, String view, String description, String active, String expected)
			throws InterruptedException {
		boolean activeBoolean;
		if (active.equals("0")) {
			activeBoolean=false;
		}else {
			activeBoolean=true;
		}
		processLogin();
		processCreateTest(id, title, href, view, description, activeBoolean);

		// actual
		String currentUrl = driver.getCurrentUrl();

		// Gán dữ liệu cho các Fields của đối tượng
		data.setId(id);
		data.setTitle(title);
		data.setHref(href);
		int viewInt=Integer.parseInt(view);
		data.setView(viewInt);
		data.setAction("Test Create function");
		data.setLogTime(new Date());
		data.setExpected(expected);
		data.setActual(currentUrl);

		// Thread.sleep(3000);
		assertEquals(expected, currentUrl);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		data.setTestMethod(result.getName());

		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			data.setStatus("SUCCESS");
			break;
		case ITestResult.FAILURE:
			data.setStatus("FAILURE");
			data.setException(result.getThrowable().getMessage());

			// Chỉ định đường dẫn File ảnh
			String path = ExcelUtils.IMAGES_SRC + "failure-" + System.currentTimeMillis() + ".png"; // Tên hình

			// gọi hàm chụp ảnh màn hình
			ExcelUtils.takeScreenShot(driver, path);

			// ghi dữ liệu vào workbook
			data.setImage(path);
			break;
		case ITestResult.SKIP:
			data.setStatus("SKIP");
			break;
		default:
			break;
		}
		logs.add(data);
		driver.close();
	}

	@AfterClass
	public void destroy() throws Exception {
		data.writeLog(SRC, "Result_Test", logs);
		ScreenRecorderUtil.stopRecord();
	}

//	// Data update function
	@DataProvider(name = "updateData")
	public Object[][] data() throws IOException {
		// Mở Excel để lấy dữ liệu
		XSSFWorkbook workbook = ExcelUtils.getWorkbook(SRC);
		// Thay đổi tên sheet phù hợp
		XSSFSheet sheet = workbook.getSheet("Update_Data");
		// Đọc dữ liệu test bằng hàm tiện ích
		Object[][] data = ExcelUtils.readSheetData(sheet);
		return data;
	}

	// Data create function
	@DataProvider(name = "createData")
	public Object[][] createData() throws IOException {
		// Mở Excel để lấy dữ liệu
		XSSFWorkbook workbook = ExcelUtils.getWorkbook(SRC);
		// Thay đổi tên sheet phù hợp
		XSSFSheet sheet = workbook.getSheet("Create_Data");
		// Đọc dữ liệu test bằng hàm tiện ích
		Object[][] data = ExcelUtils.readSheetData(sheet);
		return data;
	}
}
