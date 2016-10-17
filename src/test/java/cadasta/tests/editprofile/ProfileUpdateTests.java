package cadasta.tests.editprofile;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cadasta.main.profilehelpers.ConfigurationConstants;
import cadasta.main.profilehelpers.ProfileUtils;

public class ProfileUpdateTests {
	private WebDriver driver;
	  private String baseUrl;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  @BeforeClass(alwaysRun = true)
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = ConfigurationConstants.STAGING_BASEURL;
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testChangepassword() throws Exception {
	    driver.get(baseUrl + ConfigurationConstants.LOGIN_PATH);
	    ProfileUtils.logIn(driver,"aakanshavatsa94@gmail.com","nokia5233@" );
	    ProfileUtils.clickEditProfile(driver);
	    ProfileUtils.updatePassword(driver, "Nokia5233@", "nokia5233", "nokia5233");
	    ProfileUtils.logOut(driver);
	    ProfileUtils.logIn(driver,"aakanshavatsa94@gmail.com","nokia5233" );
	    
	    //check if user is successfully able to login
	    //Assert.assertTrue(driver.findElement(By.name()).equals(""));
	  }

	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
}
