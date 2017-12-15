package lomt.pearson.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NALSPom {
	
	WebDriver driver;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[2]")
	private WebElement nalsBannar;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[1]/a")
	private WebElement curriculumStandardLink;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[2]/a")
	private WebElement productLink;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[3]/a")
	private WebElement IntermediariesLink;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[2]/div[1]")
	private WebElement secondTextImage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[2]/div[2]")
	private WebElement secondTextNALS;
	
	public NALSPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getNalsBannar() {
		return nalsBannar;
	}

	public void setNalsBannar(WebElement nalsBannar) {
		this.nalsBannar = nalsBannar;
	}

	public WebElement getCurriculumStandardLink() {
		return curriculumStandardLink;
	}

	public void setCurriculumStandardLink(WebElement curriculumStandardLink) {
		this.curriculumStandardLink = curriculumStandardLink;
	}

	public WebElement getProductLink() {
		return productLink;
	}

	public void setProductLink(WebElement productLink) {
		this.productLink = productLink;
	}

	public WebElement getIntermediariesLink() {
		return IntermediariesLink;
	}

	public void setIntermediariesLink(WebElement intermediariesLink) {
		IntermediariesLink = intermediariesLink;
	}

	public WebElement getSecondTextImage() {
		return secondTextImage;
	}

	public void setSecondTextImage(WebElement secondTextImage) {
		this.secondTextImage = secondTextImage;
	}

	public WebElement getSecondTextNALS() {
		return secondTextNALS;
	}

	public void setSecondTextNALS(WebElement secondTextNALS) {
		this.secondTextNALS = secondTextNALS;
	}

}
