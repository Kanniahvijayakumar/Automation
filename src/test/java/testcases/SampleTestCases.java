/**
 * 
 */
/**
 * @author kanni
 *
 */
package testcases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;

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

import execution.UIOperation;

public class SampleTestCases{
	
	private static final Logger logger = LogManager.getLogger(SampleTestCases.class);
	UIOperation Execution = new UIOperation();
	
    @Parameters("browser")
    @BeforeTest
	public void browserSetup(String browser) {
        Execution.UIOperationdriver(browser); 

	}

    @Test
    public void verifySetup() throws Exception {
    	Execution.Execution("Login In Application");
    }
    }  
