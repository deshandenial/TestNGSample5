package com.qa.practice.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ElementUtil 
{


	private WebDriver driver;
	private Actions act;
	private JavaScriptUtil JsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(this.driver);
		JsUtil = new JavaScriptUtil(this.driver);
		
	}

	//  driver navigation commands
	
	public void navigationBack()
	{
		driver.navigate().back();
	}
	public void navigationForward()
	{
		driver.navigate().forward();
	}
	public void navigationRefresh()
	{
		driver.navigate().refresh();
	}
	public void navigationTo()
	{
		driver.navigate().to("");
	}
	
	// get commands 
	
	public void getText(By locator)
	{
		WebElement element =getElement(locator);
		String s = element.getText();
		System.out.println("Get Text value is : " + s);
	}
	
	public void getLocation(By locator)
	{
		WebElement element =getElement(locator);
		Point location = element.getLocation();
		System.out.println("Location of X :"+ location.getX()+ "," +"Location of Y :" +location.getY());
		
	}
	
	public void getSize(By locator)
	{
		WebElement element =getElement(locator);
		Dimension dim = element.getSize();
		System.out.println("button dimension are : " + dim);
	}
	
	public void getColor(By locator)
	{
		WebElement element = getElement(locator);
		String buttonBackground =element.getCssValue("background-color");
		String buttonText =element.getCssValue("color");
		System.out.println("Button Backround color is : " + buttonBackground);
		System.out.println("Button Text color or : " + buttonText);
	}
	
	public WebElement getElement(By locator) 
	{
		WebElement ele = driver.findElement(locator);
		return ele;
		}
	


	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	
	public void doClick1(By locator)
	{
		WebElement element  = getElement(locator);
		boolean status1 = element.isDisplayed();
		boolean status2 = element.isEnabled();
		if(status1==true)
		{
			System.out.println("Button is displayed : " + status1);
			
			if(status2==true)
			{
				System.out.println("Button is enabled : " + status2);
				element.click();
			}
			else
			{
				System.out.println("Button is disabled : " + status2);
			}
		}
	}
	
	
	public void doSendKeys1(By locator,String value)
	{
		WebElement ele = getElement(locator);
		boolean status1 = ele.isDisplayed();
		boolean status2 = ele.isEnabled();
	
	if(status1==true)
	{
		System.out.println("Field is displayed : " + status1);
		if(status2==true)
		{
			 System.out.println("Field is enabled : " + status2);
			 ele.clear();
			 ele.sendKeys(value);
			 String data = ele.getAttribute("value");
			 System.out.println("User Entered Input is : " + data);
		}else
		 {
			 System.out.println("Field is enabled : " + status2);
			 String data = ele.getAttribute("placeholder");
			 System.out.println("Field is Disable & Value : " + data);
			 
		 }
	}
	}
	
	

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void doLinkClick(By locator, String value) {
		List<WebElement> linksList = getElements(locator);
		System.out.println("total links : " + linksList.size());
		for (WebElement e : linksList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	// **********************************Drop Down Utils *************************

	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void deSelectByVisibleText(By locator) {
		Select select = new Select(getElement(locator));
		select.deselectAll();
	}
	
	public void getFirstSelectedOption(By locator)
	{
		Select select = new Select(getElement(locator));
		String label = select.getFirstSelectedOption().getText();
		System.out.println("print : " + label);
	}
	
	public void doIsMultiple(By locator1,String text1,String text2)
	{
		Select select = new Select(getElement(locator1));
		if(select.isMultiple())
		{
			select.selectByVisibleText(text1);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			select.selectByVisibleText(text2);
		}
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public List<String> getDropDownOptionsList(By locator) {
		Select select = new Select(getElement(locator));
		List<String> optionsTextList = new ArrayList<String>();
		List<WebElement> optionsList = select.getOptions();
		// System.out.println(optionsList.size());
		for (WebElement e : optionsList) {
			optionsTextList.add(e.getText());
		}
		return optionsTextList;
	}

	public void doSelectByTextOption(By locator, String text) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
	
		for (WebElement e : optionsList) {
			System.out.println("Print value are : " + e.getText());
			if (e.getText().equals(text)) 
			{
				e.click();
				System.out.println("print selected value : " + e.getText());
				break;
			}
		}
	}

	/**
	 * this method is used to select the value from drop down without select class
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSelectDropDownWithoutSelectClass(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	// *************************Actions Class Utils *************
	// 2 level manu and sub menu
	public void doMoveToElement(By locator) {
		act.moveToElement(getElement(locator)).perform();
	}

	// 3 level
	public void doMoveToElement(By locator1, By locator2) {
		act.moveToElement(getElement(locator1)).perform();
		act.moveToElement(getElement(locator2)).perform();

	}

	// 3 level with click
	public void doMoveToElement(By locator1, By locator2, By locator3) {
		act.moveToElement(getElement(locator1)).perform();
		act.moveToElement(getElement(locator2)).perform();
		doClick1(locator3);
	}

	public void doActionsClick(By locator) {
		act.click(getElement(locator)).perform();
	}

	public void doActionsSendKeys(By locator, String value) {
		act.sendKeys(getElement(locator), value).perform();
	}

	// ****************Calendar Util*************

	public void selectDate(String day, String tagName) {
		String xpath = "//" + tagName + "[text()='" + day + "']";
		doClick1(By.xpath(xpath));
	}

	public void selectDate(By locator, String day) {
		boolean flag = false;
		List<WebElement> daysList = getElements(locator);
		for (WebElement e : daysList) {
			if (e.getText().equals(day)) {
				System.out.println("right date...." + day);
				e.click();
				flag = true;
				break;
			}
		}

		if (!flag) {
			System.out.println("wrong date..." + day);
		}
	}

	// *****************************Wait Util ***************************
	
	
	public Alert waitForAlertPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		String text = waitForAlertPresent(timeOut).getText();
		acceptAlert(timeOut);
		System.out.println("Alert is : " + text);
		return text;
	}

	public void acceptAlert(int timeOut) {
		waitForAlertPresent(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlertPresent(timeOut).dismiss();
	}

	public String waitForTitleContains(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public String waitForTitleIs(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public boolean waitForTitle(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.titleIs(title));
	}

	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element, known to be present on the DOM
	 * of a page, is visible. Visibility means that the element is not only
	 * displayed but also has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForVisiblilityOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	public List<WebElement> waitForVisiblilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void printElementsTexts(By locator, int timeOut) {
		waitForVisiblilityOfElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementToBeClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void clickWhenReady(By locator, int timeOut) {
		waitForElementToBeClickable(locator, timeOut).click();
	}

	public String getElementAttribute(By locator, int timeOut, String name) {
		return waitForElementToBeClickable(locator, timeOut).getAttribute(name);
	}

	public boolean waitForElementTextToBe(By locator, int timeOut, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.textToBe(locator, value));
	}

	public boolean waitForUrlToBe(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(urlValue));
	}

	public boolean waitForExactUrlToBe(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlToBe(urlValue));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 * 
	 * @param timeOut
	 * @param urlValue
	 * @return
	 */
	public boolean waitForElementToBeSelected(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeSelected(locator));
	}
	
	

	public void waitForFrameToBeAvailable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	
	public void parentFrame() {
		
		driver.switchTo().parentFrame();
		
	}
	
	public void waitForFrameToBeAvailable(String nameOrId, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}

	public void waitForFrameToBeAvailable(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	

	
	
	
	
}
