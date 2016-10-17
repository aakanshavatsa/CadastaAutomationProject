package cadasta.main.profilehelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileUtils {
	
	public static void logIn(WebDriver driver, String username, String password){
		if(!username.isEmpty() && !password.isEmpty()){
			driver.findElement(By.id(ProfileConstants.USERNAME_ID)).clear();
			driver.findElement(By.id(ProfileConstants.USERNAME_ID)).sendKeys(username);
			driver.findElement(By.id(ProfileConstants.PASSWORD_ID)).clear();
			driver.findElement(By.id(ProfileConstants.PASSWORD_ID)).sendKeys(password);
			driver.findElement(By.name(ProfileConstants.SIGNIN_BUTTON_NAME)).click();	
		}
	}
	
	public static void logOut(WebDriver driver){
		driver.findElement(By.xpath(ProfileConstants.PROFILE_XPATH)).click();
	    driver.findElement(By.linkText(ProfileConstants.LOGOUT_LINKTEXT)).click();
	}
	
	public static void clickEditProfile(WebDriver driver){
	    driver.findElement(By.xpath(ProfileConstants.PROFILE_XPATH)).click();
	    driver.findElement(By.linkText(ProfileConstants.EDITPROFILE_LINKTEXT)).click();
	}
	
	public static void updatePassword(WebDriver driver, String oldPassword, String newPassword, String confirmNewPassword){
		if(!confirmNewPassword.isEmpty() && !oldPassword.isEmpty()){
			driver.findElement(By.linkText(ProfileConstants.CHANGEPASSWORD_LINKTEXT)).click();
			driver.findElement(By.id(ProfileConstants.OLDPASSWORD_ID)).clear();
			driver.findElement(By.id(ProfileConstants.OLDPASSWORD_ID)).sendKeys(oldPassword);
			driver.findElement(By.id(ProfileConstants.NEWPASSWORD_ID)).clear();
			driver.findElement(By.id(ProfileConstants.NEWPASSWORD_ID)).sendKeys(newPassword);
			driver.findElement(By.id(ProfileConstants.NEWPASSWORD_CONFIRMATION_ID)).clear();
			driver.findElement(By.id(ProfileConstants.NEWPASSWORD_CONFIRMATION_ID)).sendKeys(confirmNewPassword);
			driver.findElement(By.name(ProfileConstants.UPDATEPASSWORD_BUTTON_NAME)).click();	
		}
	}
	
}
