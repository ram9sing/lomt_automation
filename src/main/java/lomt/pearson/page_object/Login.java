package lomt.pearson.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page object model class, holds XPATH
 * 
 * @author ram.sin
 *
 */
public class Login {

	WebDriver driver;

	@FindBy(xpath = "//input[@placeholder='username']")
	private WebElement userName;

	@FindBy(xpath = "//input[@placeholder='password']")
	private WebElement password;

	@FindBy(xpath = "//div[@class='login-btn-panel']/button")
	private WebElement loginButton;

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

}
