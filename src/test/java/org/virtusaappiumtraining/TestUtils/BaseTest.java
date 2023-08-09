package org.virtusaappiumtraining.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.virtusaappiumtraining.pageobjects.android.FormPage;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public FormPage formPage;
	@BeforeClass
	public void ConfigureAppium() throws InterruptedException, IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\virtusaappiumtraining\\resources\\data.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("IpAddress");
		String port = prop.getProperty("Port");
		//start server automatically
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\ADARSH M M\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress).usingPort(Integer.parseInt(port)).build();
		service.start();
				
		//Android Driver
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
//		options.setApp("C:\\Users\\ADARSH M M\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\org\\virtusaappiumtraining\\resources\\General-Store.apk");	
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(23));
		Thread.sleep(5000);
		formPage = new FormPage(driver);
	}
	
	//longpressgesture
	public void longPressAction(WebElement element) {
		//RemoteWebElement - webelement that will be supported by any browser, app, etc(eg : web browser, mobilea app ...)
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId(),
			    "duration",2000
			));
	}
	
	//Scrolling till the end
	public void scrollTillEnd() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				   "left", 100, "top", 100, "width", 200, "height", 200,
				   "direction", "down",
				   "percent", 3.0
				));
		}while(canScrollMore);
	}
	
	//swipe
	public void swipeGestureAction(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId",((RemoteWebElement) element).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
	}
	
	@AfterClass
	public void Teardown() {
		driver.quit();
		service.stop();
	}
}
