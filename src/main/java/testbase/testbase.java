package testbase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testbase {

	public WebDriver driver;
	public static Properties OR;
	public File f1;
	public FileInputStream file;

	@BeforeTest
	public void launchBrowser() throws IOException {
		loadPropertiesFile();
		String getBr = OR.getProperty("browser");
		String getUrls = OR.getProperty("url");
		getBrowser(getBr);
		driver.get(getUrls);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	
	public void getTitle() {
		
		System.out.println(driver.getTitle());
	}
	
	public void loadPropertiesFile() throws IOException {
		OR = new Properties();
		try {

			f1 = new File(System.getProperty("user.dir") + "\\src\\main\\java\\prop\\Application.properties");

			file = new FileInputStream(f1);
			OR.load(file);
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	public void getBrowser(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println(System.getProperty("user.dir"));
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe");;
			driver = new ChromeDriver();
		}

	}
}
