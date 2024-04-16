/**
 * 
 */
/**
 * @author kanni
 *
 */
package testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import excelRead.ReadExcel;

import excelRead.ReadTestCaseName;

class SampleTestCases{
	
	private static final Logger logger = LogManager.getLogger(SampleTestCases.class);
	
//    public static void main(String args[]) throws InterruptedException{  
//	public void verifySetup() {
//     System.out.println("Hello Java");  
//     ChromeOptions chromeoption=new ChromeOptions();
//     chromeoption.addArguments("--remote-allow-origins=*");
//     
//     System.setProperty("webdriver.chrome.driver", "C:\\Users\\kanni\\OneDrive\\Desktop\\Interview\\FrameWork\\src\\main\\resources\\Drivers\\chromedriver.exe");
//     
//     WebDriver driver = new ChromeDriver(chromeoption);
	
	
     
    @Parameters("browser")
    @Test
    public void verifySetup(String browser) throws IOException {
    	
    	
		ReadTestCaseName ReadTestCaseName = new ReadTestCaseName();
		
		ArrayList<String> cars =  ReadTestCaseName.getTestCaseDetails("Sample Test Case");
		
		System.out.print("XXXXXXXXXXXXXXXXXXXXXXX");
		
		System.out.println(cars);
    	
    	

        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\kanni\\OneDrive\\Desktop\\Interview\\FrameWork\\src\\main\\resources\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver(chromeOptions);
            
            logger.info("We are using chrome driver");
        } else if (browser.equalsIgnoreCase("firefox")) {
//        	FirefoxOptions firefoxOptions = new FirefoxOptions();
        	
        	System.setProperty("webdriver.gecko.driver", "C:\\Users\\kanni\\OneDrive\\Desktop\\Interview\\FrameWork\\src\\main\\resources\\Drivers\\geckodriver.exe");
        	
        	
            FirefoxOptions options = new FirefoxOptions();
            
            options.setCapability("marionette", true);

            driver = new FirefoxDriver(options);
        	
//            FirefoxOptions options = new FirefoxOptions();
//            options.setCapability("marionette", true);
//
//             driver = new FirefoxDriver(options);
            
            logger.info("We are using Firefox driver");
        } else {
            throw new IllegalArgumentException("Invalid browser value: " + browser);
        }
	
	
//     driver.get("https://www.selenium.dev/selenium/web/web-form.html");
//
//     driver.getTitle();
//
//     driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//
//     WebElement textBox = driver.findElement(By.name("my-text"));
//     WebElement submitButton = driver.findElement(By.cssSelector("button"));
//     
//     textBox.sendKeys("Selenium");
//     
//     submitButton.click();
//
//     WebElement message = driver.findElement(By.id("message"));
//     message.getText();

//     driver.quit();
        
        
        driver.get(cars.get(4));

        driver.getTitle();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.findElement(By.name(cars.get(6))).sendKeys(cars.get(8));
        driver.findElement(By.cssSelector(cars.get(10))).click();

        WebElement message = driver.findElement(By.id("message"));
        message.getText();
     
    }
    }  
