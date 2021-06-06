package com.qa.practice.base;


import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import com.qa.practice.factory.DriverFactory;
import com.qa.practice.pages.UI_Page;

import com.qa.practice.utils.ConfigReader;


public class BaseTest 
{
	private WebDriver driver;
	public Properties prop;
	DriverFactory df;
	private ConfigReader configReader;
	public UI_Page uiPage;
	
	
	
	@BeforeTest
	public void setUp() 
	{
		configReader =new ConfigReader();
		 prop = configReader.init_prop();
		String browser = prop.getProperty("browser");
		df = new DriverFactory();
		driver =df.init_driver(browser);
		driver.get("https://letcode.in/");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
	    uiPage = new UI_Page(driver);
	}
	
	@AfterTest
	public void tearDown() 
	{
		
	 driver.quit();	
	}
	
	

}
