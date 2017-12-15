package lomt.pearson.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SGPom {
	
	WebDriver driver;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[2]")
	private WebElement sgBanner;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[2]/div[1]")
	private WebElement secondTextImage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[2]/div[2]")
	private WebElement secondTextSG;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[1]/td[3]")
	private WebElement errorMessage1;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[2]/td[3]")
	private WebElement errorMessage2;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[3]/td[3]")
	private WebElement errorMessage3;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[4]/td[3]")
	private WebElement errorMessage4;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/tbody/tr[5]/td[3]")
	private WebElement errorMessage5;
	
	public SGPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSgBanner() {
		return sgBanner;
	}

	public void setSgBanner(WebElement sgBanner) {
		this.sgBanner = sgBanner;
	}

	public WebElement getSecondTextImage() {
		return secondTextImage;
	}

	public void setSecondTextImage(WebElement secondTextImage) {
		this.secondTextImage = secondTextImage;
	}

	public WebElement getSecondTextSG() {
		return secondTextSG;
	}

	public void setSecondTextSG(WebElement secondTextSG) {
		this.secondTextSG = secondTextSG;
	}

	public WebElement getErrorMessage1() {
		return errorMessage1;
	}

	public void setErrorMessage1(WebElement errorMessage1) {
		this.errorMessage1 = errorMessage1;
	}

	public WebElement getErrorMessage2() {
		return errorMessage2;
	}

	public void setErrorMessage2(WebElement errorMessage2) {
		this.errorMessage2 = errorMessage2;
	}

	public WebElement getErrorMessage3() {
		return errorMessage3;
	}

	public void setErrorMessage3(WebElement errorMessage3) {
		this.errorMessage3 = errorMessage3;
	}

	public WebElement getErrorMessage4() {
		return errorMessage4;
	}

	public void setErrorMessage4(WebElement errorMessage4) {
		this.errorMessage4 = errorMessage4;
	}

	public WebElement getErrorMessage5() {
		return errorMessage5;
	}

}
