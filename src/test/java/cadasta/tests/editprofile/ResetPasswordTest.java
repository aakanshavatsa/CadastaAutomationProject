package cadasta.tests.editprofile;

import org.testng.annotations.Test;

import cadasta.main.profilehelpers.ConfigurationConstants;
import cadasta.main.profilehelpers.ProfileUtils;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

/**
 * 
 * @author Aakansha Vatsa
 * 
 * Cadasta Outreachy task : 
 * Bug: Password reset button present in Edit Profile screen along with Change password
 * 
 * Expected : It is redundant behavior to have both Change password and Edit Password in the Edit Profile section
 * Ideally, Edit profile should only have Change password and Reset password should only be available on clicking 'Forgot Password'
 *
 */
public class ResetPasswordTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	  
	// Data for test method
	private String username = "aakanshavatsa94@gmail.com";
	private String password = "Nokia5233";
	   
	  @BeforeClass(alwaysRun = true)
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = ConfigurationConstants.STAGING_BASEURL;
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
  
	/**
	 * Method to test if Password Reset Link is present
	 * If present, then throw AssertionError
	 */
	@Test
	public void testResetButtonPresent(){
		driver.get(baseUrl + ConfigurationConstants.LOGIN_PATH);
	    ProfileUtils.logIn(driver, username, password );
	    ProfileUtils.clickEditProfile(driver);
	    
	    Assert.assertFalse(driver.findElement(By.linkText("Reset password")).isDisplayed());
	    ProfileUtils.logOut(driver);

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
