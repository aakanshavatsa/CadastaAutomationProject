package cadasta.tests.editprofile;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cadasta.main.profilehelpers.ConfigurationConstants;
import cadasta.main.profilehelpers.ProfileConstants;
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

	  //This method will return two dimensional array.
	  //This method behaves as data provider for testChangepassword method.
	  @DataProvider
	  public Object[][] loginCredentials(){
	   Object[][] cred = new Object[2][3];
	   
	   cred[0][0] = "aakanshavatsa94@gmail.com";
	   cred[0][1] = "Nokia5233";
	   cred[0][2] = "nokia5233";

	   cred[1][0] = "aakanshavatsa94@gmail.com";
	   cred[1][1] = "nokia5233";
	   cred[1][2] = "Nokia5233";
	   return cred; //Returned cred
	  }
	  
	  @DataProvider
	  public Object[][] updateEditProfileDataProvider(){
	   Object[][] cred = new Object[1][5];
	   
	   cred[0][0] = "aakanshavatsa94@gmail.com";
	   cred[0][1] = "Nokia5233";
	   cred[0][2] = "aakansha";
	   cred[0][3] = "aakanshavatsa94@gmail.com";
	   cred[0][4] = "aakanshaVatsa";
	   return cred; //Returned cred
	  }
	  
	  /**
	   * Method to test the Reset Password flow
	   * 
	   * Steps : 
	   * 	1. User is signed into account
	   *  	2. Click "Edit profile"
	   *  	3. Click "Change password"
	   *  	4. Enter Current password, enter new password and new password confirmation
	   *	5. Click "Change Password"
	   *	6. Click "Log out"
	   *	7. Enter Username and Password
	   *	8. Click "Sign In"
	   *
	   * Expected : Password changed and User is able to successfully login with the updated Password
	   *
	   * @param username
	   * @param oPass
	   * @param nPass
	   * @throws Exception
	   */
	  @Test(dataProvider="loginCredentials")
	  public void testChangepassword(String username, String oPass, String nPass) throws Exception {
	    driver.get(baseUrl + ConfigurationConstants.LOGIN_PATH);
	    ProfileUtils.logIn(driver, username, oPass );
	    ProfileUtils.clickEditProfile(driver);
	    ProfileUtils.updatePassword(driver, oPass, nPass, nPass);
	    ProfileUtils.logOut(driver);
	    ProfileUtils.logIn(driver, username, nPass );
	    
	    //check if user is successfully able to login
	    Assert.assertTrue(driver.findElement(By.xpath(ProfileConstants.PROFILE_XPATH)).isDisplayed());
	    ProfileUtils.logOut(driver);
	  }
	  
	  /**
	   * method to test Username change in Edit Profile
	   * 1. Sign in
	   * 2. Click on Edit Profile
	   * 3. Change Username
	   * 4. Click Update
	   * 5. Go back to Edit Profile screen
	   * 
	   * Expected : The current value of the Username field should be the updated one
	   * 
	   * @param username
	   * @param password
	   * @param newUsername
	   * @param newEmail
	   * @param newFullname
	   * @throws Exception
	   */
	  @Test(dataProvider="updateEditProfileDataProvider")
	  public void testChangeUsername(String username, String password, String newUsername, String newEmail, String newFullname) throws Exception {
	    driver.get(baseUrl + ConfigurationConstants.LOGIN_PATH);
	    ProfileUtils.logIn(driver, username, password );
	    ProfileUtils.clickEditProfile(driver);
	    ProfileUtils.updateUsername(driver, newUsername);
	    ProfileUtils.clickEditProfile(driver);
	    String actualText = driver.findElement(By.id(ProfileConstants.UPDATEUSERNAME_ID)).getAttribute("value");
	    String expectedText = "aakansha";
	    Assert.assertEquals(actualText, expectedText);
	    ProfileUtils.logOut(driver);
	  }
	  
	  /**
	   * method to test Email change in Edit Profile
	   * 1. Sign in
	   * 2. Click on Edit Profile
	   * 3. Change Email
	   * 4. Click Update
	   * 5. Go back to Edit Profile screen
	   * 
	   * Expected : The current value of the Email field should be the updated one
	   * 
	   * @param username
	   * @param password
	   * @param newUsername
	   * @param newEmail
	   * @param newFullname
	   * @throws Exception
	   */
	  @Test(dataProvider="updateEditProfileDataProvider")
	  public void testChangeEmail(String username, String password, String newUsername, String newEmail, String newFullname) throws Exception {
	    driver.get(baseUrl + ConfigurationConstants.LOGIN_PATH);
	    ProfileUtils.logIn(driver, username, password );
	    ProfileUtils.clickEditProfile(driver);
	    ProfileUtils.updateEmail(driver, newEmail);
	    ProfileUtils.clickEditProfile(driver);
	    String actualText = driver.findElement(By.id(ProfileConstants.UPDATEMAIL_ID)).getAttribute("value");
	    String expectedText = "aakanshavatsa94@gmail.com";
	    Assert.assertEquals(actualText, expectedText);
	    ProfileUtils.logOut(driver);
	  }
	  
	  /**
	   * method to test FullName change in Edit Profile
	   * 1. Sign in
	   * 2. Click on Edit Profile
	   * 3. Change FullName
	   * 4. Click Update
	   * 5. Go back to Edit Profile screen
	   * 
	   * Expected : The current value of the FullName field should be the updated one
	   * 
	   * @param username
	   * @param password
	   * @param newUsername
	   * @param newEmail
	   * @param newFullname
	   * @throws Exception
	   */
	  @Test(dataProvider="updateEditProfileDataProvider")
	  public void testChangeFullName(String username, String password, String newUsername, String newEmail, String newFullname) throws Exception {
	    driver.get(baseUrl + ConfigurationConstants.LOGIN_PATH);
	    ProfileUtils.logIn(driver, username, password );
	    ProfileUtils.clickEditProfile(driver);
	    ProfileUtils.updateFullName(driver, newFullname);
	    ProfileUtils.clickEditProfile(driver);
	    String actualText = driver.findElement(By.id(ProfileConstants.UPDATEFULLNAME_ID)).getAttribute("value");
	    String expectedText = "aakanshaVatsa";
	    Assert.assertEquals(actualText, expectedText);
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
