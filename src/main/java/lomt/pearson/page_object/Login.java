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

	//@FindBy(xpath = "//input[@placeholder='username']")
	@FindBy(xpath = "//*[@id='lomtAppId']/div/div/div/div[2]/div/div[1]/div/input")
	private WebElement userName;

	//@FindBy(xpath = "//input[@placeholder='password']")
	@FindBy(xpath = "//*[@id='lomtAppId']/div/div/div/div[2]/div/div[2]/div/input")
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
