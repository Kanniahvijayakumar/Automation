package execution;

import java.util.Map;

import excelRead.ReadTestCaseName;
import testcases.SampleTestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class UIOperation{

WebDriver driver;	

private static final Logger logger = LogManager.getLogger(UIOperation.class);

public void UIOperationdriver(String browser){
	
	
    if (browser.equalsIgnoreCase("chrome")) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kanni\\OneDrive\\Desktop\\Interview\\FrameWork\\src\\main\\resources\\Drivers\\chromedriver.exe");
        this.driver  = new ChromeDriver(chromeOptions);
        
        logger.info("We are using Chrome driver");
        

    } else if (browser.equalsIgnoreCase("firefox")) {
    	
    	System.setProperty("webdriver.gecko.driver", "C:\\Users\\kanni\\OneDrive\\Desktop\\Interview\\FrameWork\\src\\main\\resources\\Drivers\\geckodriver.exe");
    	
    	
        FirefoxOptions options = new FirefoxOptions();
        
        options.setCapability("marionette", true);

        this.driver = new FirefoxDriver(options);
        
        logger.info("We are using Firefox driver");
    } else {
        throw new IllegalArgumentException("Invalid browser value: " + browser);
    }
	
	}	
	
public void Execution(String TestCaseName) throws Exception {
		
	
	System.out.println("Inside ************************************");
	
	ReadTestCaseName ReadTestCaseName = new ReadTestCaseName();
	
	Map<String, Map<String, String>> testCaseMap = ReadTestCaseName.getTestCaseDetails(TestCaseName);
	
//	driver.get("https://www.selenium.dev/selenium/web/web-form.html");
	
	
    // Iterate over the outer map
    for (Map.Entry<String, Map<String, String>> entry : testCaseMap.entrySet()) {
//        String testCaseName = entry.getKey();
        Map<String, String> detailsMap = entry.getValue();

//        // Print the test case name
//        System.out.println("Test Case Name: " + testCaseName);
        
        String Keyword ="", Object ="", ObjectType ="", Value ="";

        // Iterate over the inner map
        for (Map.Entry<String, String> innerEntry : detailsMap.entrySet()) {
            String key = innerEntry.getKey();
            String value = innerEntry.getValue();
            
            
            // Print the key-value pair of the inner map
            System.out.println("Key: " + key + ", Value: " + value);

            
            if (key.equalsIgnoreCase("Keyword")) {
            	
            	Keyword = value;
            	
            }
            
            if (key.equalsIgnoreCase("Object")) {
            	
            	Object = value;
            	
            }
            
            if (key.equalsIgnoreCase("ObjectType")) {
            	
            	ObjectType = value;
            	
            }
            
            if (key.equalsIgnoreCase("value")) {
            	
            	Value =value;
            	
            }
            
            
            
        }
        
        perform(Object,Keyword,Object,ObjectType,Value);
    }
}


private void perform(String p,String operation,String objectName,String objectType,String value) throws Exception{
    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    System.out.println(operation);
    switch (operation.toUpperCase()) {
    case "CLICK":
        //Perform click
        driver.findElement(this.getObject(p,objectName,objectType)).click();
        break;
    case "SETTEXT":
        //Set text on control
        driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
        break;
        
    case "GOTOURL":
        //Get url of application
        driver.get((value));
        break;
    case "GETTEXT":
        //Get text of an element
        driver.findElement(this.getObject(p,objectName,objectType)).getText();
        break;
    default:
        break;
    }
}

/**
 * Find element BY using object type and value
 * @param p
 * @param objectName
 * @param objectType
 * @return
 * @throws Exception
 */
private By getObject(String p,String objectName,String objectType) throws Exception{
    //Find by xpath
    if(objectType.equalsIgnoreCase("XPATH")){
        
        return By.xpath(p);
    }
    //find by class
    else if(objectType.equalsIgnoreCase("CLASSNAME")){
        
        return By.className(p);
        
    }
    //find by name
    else if(objectType.equalsIgnoreCase("NAME")){
        
        return By.name(p);
        
    }
    //Find by css
    else if(objectType.equalsIgnoreCase("CSS")){
        
        return By.cssSelector(p);
        
    }
    //find by link
    else if(objectType.equalsIgnoreCase("LINK")){
        
        return By.linkText(p);
        
    }
    //find by partial link
    else if(objectType.equalsIgnoreCase("PARTIALLINK")){
        
        return By.partialLinkText(p);
        
    }else
    {
        throw new Exception("Wrong object type");
    }
}


}