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

import ASM.testNG.logs.LoginData;
import ASM.testNG.utils.ExcelUtils;
import ASM.testNG.utils.ScreenRecorderUtil;

public class LoginTest {
	private WebDriver driver;
	private final String SRC=ExcelUtils.DATA_SRC+"Login_Test.xlsx";
	private Set<LoginData> logs;//Map
	private LoginData data;
	
	@BeforeClass
	public void init() throws Exception {
		ScreenRecorderUtil.startRecord("loginData");
		System.setProperty("webdriver.chrome.driver", ExcelUtils.CHROME_DRIVER_SRC);
		logs=new LinkedHashSet<>();
		//Đảm bảo thứ tự dữ liệu test không bị thay đổi
		
	}
	
	@BeforeMethod
	public void setup(){
		// Khởi tạo và thiết lập thông số cho web driver
		driver=new ChromeDriver();
		driver.get("http://localhost:8181/ASSM-Nhom/client/login");
		data=new LoginData();//Khời tạo từ đầu dữ liệu mới
		driver.manage().window().maximize();
	}
	
	private void processLogin(String username, String password) {
		WebElement unameInput=driver.findElement(By.name("username"));
		unameInput.sendKeys(username);
		
		WebElement pwordInput=driver.findElement(By.name("password"));
		pwordInput.sendKeys(password);
		
		WebElement loginBtn=driver.findElement(By.name("loginBtn"));
		loginBtn.submit();
	}
	
	@Test(dataProvider = "loginData")
	public void multipleLogin(String username, String password, String expected) throws Exception {
		processLogin(username, password);
		//actual
		String currentUrl=driver.getCurrentUrl();
		
		//Gán dữ liệu cho các Fields của đối tượng
		data.setUsername(username);
		data.setPassword(password);
		data.setAction("Test Login function");
		data.setLogTime(new Date());
		data.setExpected(expected);
		data.setActual(currentUrl);
		
		// Thread.sleep(3000);
		assertEquals(expected, currentUrl);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		data.setTestMethod(result.getName());
		
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			data.setStatus("SUCCESS");
			break;
		case ITestResult.FAILURE:
			data.setStatus("FAILURE");
			data.setException(result.getThrowable().getMessage());
			
			//Chỉ định đường dẫn File ảnh
			String path=ExcelUtils.IMAGES_SRC+"failure-"+System.currentTimeMillis()+".png"; //Tên hình
			
			//gọi hàm chụp ảnh màn hình
			ExcelUtils.takeScreenShot(driver, path);
			
			//ghi dữ liệu vào workbook
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
	
	@DataProvider(name = "loginData")
	public Object[][] data() throws IOException{
		//Mở Excel để lấy dữ liệu 
		XSSFWorkbook workbook=ExcelUtils.getWorkbook(SRC);
		//Thay đổi tên sheet phù hợp
		XSSFSheet sheet=workbook.getSheet("Login_Data");
		//Đọc dữ liệu test bằng hàm tiện ích
		Object[][] data=ExcelUtils.readSheetData(sheet);
		return data;
	}
}
