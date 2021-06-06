package com.qa.practice.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.practice.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Story("US-101: design the all web element for practice")
@Epic("Epic-100: design ui feature")
public class UI_Page_Test extends BaseTest
{
SoftAssert softAssert = new SoftAssert();
	

	@BeforeClass
	public void AccSetUp() 
	{
	
	uiPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	
	}
  
	@Description("Input Text Area Web Element Test!!!")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void inputTest() 
	{
		
		uiPage.inputUI();
	}
	
	@Description("Button Web Element Test!!!")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void buttonTest() 
	{
		
		uiPage.buttonUI();
	}
	
	@Description("Select Web Element Test!!!")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void selectTest() 
	{
		
		uiPage.selectUI();
	}
	
	@Description("Alert Test!!!")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void AlertTest() 
	{
		
		uiPage.alertUI();
	}
	
	@Description("IFrames Test!!!")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 5)
	public void FramesTest() 
	{
		
		uiPage.frameUI();
	}
	
	
}
