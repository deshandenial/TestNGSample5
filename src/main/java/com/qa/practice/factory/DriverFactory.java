package com.qa.practice.factory;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory 
{

public WebDriver ddriver;


	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();

	public WebDriver init_driver(String browser)
	{
		System.out.println("browser value is: " + browser);
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver());
			
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver());
		}
		else
		{
			System.out.println("Please pass correct browser value: " + browser);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		//getDriver().get(prop.getProperty("url").trim());
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver()
	{
		return tldriver.get();
	}
			


}
