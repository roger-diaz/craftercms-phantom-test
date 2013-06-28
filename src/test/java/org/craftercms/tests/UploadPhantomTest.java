package org.craftercms.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadPhantomTest {
	private static final Logger logger = Logger.getLogger("UploadPhantomTest.class");
	private static final String TEST_PROPERTIES = "test.properties";
	private static Properties testProperties = new Properties();
	static {
		try {
		testProperties.load(UploadPhantomTest.class.getClassLoader().getResourceAsStream(TEST_PROPERTIES));
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
    private String validationString;
    
    protected String uploadPageUrl;
    protected String fileName;
    protected String filePath;
    protected String uploadPath;
    

	@Before
	public void setUp() throws Exception {
		uploadPageUrl = testProperties.getProperty("craftercms.upload.page.url");
		fileName = testProperties.getProperty("craftercms.upload.file");
		filePath = testProperties.getProperty("craftercms.upload.file.path");
		uploadPath = testProperties.getProperty("craftercms.upload.path");
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability("takesScreenshot", true);
        desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, 
        		testProperties.getProperty("craftercms.phantomjs.path"));
		driver = new PhantomJSDriver(desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testUploadFile() {
		logger.info("Upload File");
		driver.get(uploadPageUrl);
		//Open modal window
		driver.findElement(By.id("openPopup")).click();
    	//Set the file and upload
    	driver.findElement(By.id("uploadFileNameId")).sendKeys(filePath + fileName);
        driver.findElement(By.id("uploadButton")).click();
        
        WebElement element = (new WebDriverWait(driver, 30)).until(
        		ExpectedConditions.presenceOfElementLocated(By.id("msg")));
        //Check the upload was successful
        assertTrue(element.getText().contains("File uploaded"));
        
        File file = new File(uploadPath + fileName);
        assertTrue(file.exists());
	}
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	    driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
	}
}
