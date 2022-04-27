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

import ASM.testNG.logs.UserData;
import ASM.testNG.utils.ExcelUtils;
import ASM.testNG.utils.ScreenRecorderUtil;

public class UserTest {
	private WebDriver driver;
	private final String SRC = ExcelUtils.DATA_SRC + "Users_Test.xlsx";
	private Set<UserData> logs;// Map
	private UserData data;

	@BeforeClass
	public void init() throws Exception {
		ScreenRecorderUtil.startRecord("CRUDUserTest");
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
		data = new UserData();// Khời tạo từ đầu dữ liệu mới
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
		WebElement userManagerBtn = driver.findElement(By.name("userManager"));
		userManagerBtn.click();

		WebElement editBtn = driver.findElement(By.name("edit"));
		editBtn.click();

		WebElement newBtn = driver.findElement(By.name("newBtn"));
		newBtn.click();
	}

	// processUpdateTest
	private void processUpdateTest(String username, String password, String fullname, String email) {
		WebElement userManagerBtn = driver.findElement(By.name("userManager"));
		userManagerBtn.click();

		WebElement editBtn = driver.findElement(By.name("edit"));
		editBtn.click();

		WebElement unameInput = driver.findElement(By.name("username"));
		unameInput.sendKeys(username);

		WebElement pwordInput = driver.findElement(By.name("password"));
		pwordInput.clear();
		pwordInput.sendKeys(password);

		WebElement fullnameInput = driver.findElement(By.name("fullname"));
		fullnameInput.clear();
		fullnameInput.sendKeys(fullname);

		WebElement emailInput = driver.findElement(By.name("email"));
		emailInput.clear();
		emailInput.sendKeys(email);

		WebElement updateBtn = driver.findElement(By.name("updateBtn"));
		updateBtn.click();
	}

	// processDeleteTest
	private void processDeleteTest() {
		WebElement userManagerBtn = driver.findElement(By.name("userManager"));
		userManagerBtn.click();

		WebElement editBtn = driver.findElement(By.name("edit"));
		editBtn.click();

		WebElement pwordInput = driver.findElement(By.name("password"));
		pwordInput.clear();

		WebElement fullnameInput = driver.findElement(By.name("fullname"));
		fullnameInput.clear();

		WebElement emailInput = driver.findElement(By.name("email"));
		emailInput.clear();

		WebElement deleteBtn = driver.findElement(By.name("deleteBtn"));
		deleteBtn.click();
	}

	// process Create Test
	private void processCreateTest(String username, String password, String fullname, String email) {
		WebElement userManagerBtn = driver.findElement(By.name("userManager"));
		userManagerBtn.click();

		WebElement editBtn = driver.findElement(By.name("edit"));
		editBtn.click();

		WebElement newBtn = driver.findElement(By.name("newBtn"));
		newBtn.click();

		WebElement unameInput = driver.findElement(By.name("username"));
		unameInput.sendKeys(username);

		WebElement pwordInput = driver.findElement(By.name("password"));
		pwordInput.sendKeys(password);

		WebElement fullnameInput = driver.findElement(By.name("fullname"));
		fullnameInput.sendKeys(fullname);

		WebElement emailInput = driver.findElement(By.name("email"));
		emailInput.sendKeys(email);

		WebElement createBtn = driver.findElement(By.name("createBtn"));
		createBtn.click();
	}

	// Test themmoi
	@Test(priority = 0)
	public void ThemmoiTest() {
		processLogin();
		processNewTest();

		String actual = driver.getCurrentUrl();
		String expected = "http://localhost:8181/ASSM-Nhom/admin/user/new";

		data.setUsername("Null");
		data.setPassword("Null");
		data.setFullname("Null");
		data.setEmail("Null");
		data.setAction("Test New function");
		data.setLogTime(new Date());
		data.setExpected(expected);
		data.setActual(actual);

		assertEquals(actual, expected);
	}

	// Test Delete
	@Test(priority = 3)
	public void DeleteTest_0() {
		processLogin();

		WebElement userManagerBtn = driver.findElement(By.name("userManager"));
		userManagerBtn.click();

		WebElement editBtn = driver.findElement(By.name("edit"));
		editBtn.click();

		WebElement unameInput = driver.findElement(By.name("username"));
		String uname = unameInput.getAttribute("value");

		WebElement pwordInput = driver.findElement(By.name("password"));
		String password = pwordInput.getAttribute("value");

		WebElement fullnameInput = driver.findElement(By.name("fullname"));
		String fullname = fullnameInput.getAttribute("value");

		WebElement emailInput = driver.findElement(By.name("email"));
		String email = emailInput.getAttribute("value");

		WebElement deleteBtn = driver.findElement(By.name("deleteBtn"));
		deleteBtn.click();

		String actual = driver.getCurrentUrl();
		String expected = "http://localhost:8181/ASSM-Nhom/admin/user/view";

		data.setUsername(uname);
		data.setPassword(password);
		data.setFullname(fullname);
		data.setEmail(email);
		data.setAction("Test Delete function");
		data.setLogTime(new Date());
		data.setExpected(expected);
		data.setActual(actual);

		assertEquals(actual, expected);
	}

	// Test Delete
	@Test(priority = 4)
	public void DeleteTest_1() {
		processLogin();
		processDeleteTest();

		String actual = driver.getCurrentUrl();
		String expected = "http://localhost:8181/ASSM-Nhom/admin/user/delete";

		data.setUsername(null);
		data.setPassword(null);
		data.setFullname(null);
		data.setEmail(null);
		data.setAction("Test Delete function");
		data.setLogTime(new Date());
		data.setExpected(expected);
		data.setActual(actual);

		assertEquals(actual, expected);
	}

	// Test multipleUpdate
	@Test(dataProvider = "updateData", priority = 2)
	public void UpdateTests(String username, String password, String fullname, String email, String expected)
			throws InterruptedException {
		processLogin();
		processUpdateTest(username, password, fullname, email);

		// actual
		String currentUrl = driver.getCurrentUrl();

		// Gán dữ liệu cho các Fields của đối tượng
		data.setUsername(username);
		data.setPassword(password);
		data.setFullname(fullname);
		data.setEmail(email);
		data.setAction("Test Update function");
		data.setLogTime(new Date());
		data.setExpected(expected);
		data.setActual(currentUrl);

		// Thread.sleep(3000);
		assertEquals(expected, currentUrl);
	}

	// Test multipleCreate
	@Test(dataProvider = "createData", priority = 1)
	public void CreateTests(String username, String password, String fullname, String email, String expected)
			throws InterruptedException {
		processLogin();
		processCreateTest(username, password, fullname, email);

		// actual
		String currentUrl = driver.getCurrentUrl();

		// Gán dữ liệu cho các Fields của đối tượng
		data.setUsername(username);
		data.setPassword(password);
		data.setFullname(fullname);
		data.setEmail(email);
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

	// Data update function
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
