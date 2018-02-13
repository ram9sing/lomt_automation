package lomt.pearson.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnglishPOM {

	WebDriver driver;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[2]")
	private WebElement englishBanner;	
		
	@FindBy(xpath = "//div[@class='structures-container']/div[3]/span[1]/a")
	private WebElement gseLink;
	
	@FindBy(xpath = "//div[@class='list-row row']/div[2]/div/span")
	private By gseActionLink;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/div/div/div[1]/div/span/span/a")
	private WebElement gseStructure;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[1]/div/div/span[1]/span")
	private WebElement cancelFilterBySet;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[10]/input")
	private WebElement descriptiveIdWithFilterSet;
	
	@FindBy(xpath = "//div[@class='structures-container']/div[3]/span[2]/a")
	private WebElement externalFrameworkLink;
	
	@FindBy(xpath = "//div[@class='structures-container']/div[3]/span[3]/a")
	private WebElement productLink;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[2]/div[1]/img")
	private WebElement secondTextImage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[2]/div[2]")
	private WebElement secondTextEnglish;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/span/div/div[2]")
	private WebElement loader;
					
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[1]/div/div[2]/div/div[1]/span[1]")
	private WebElement selectAll;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[1]/div/div[2]/div/div[2]/span/span[4]/a/span[2]")
	private WebElement renderedLink;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[1]/ol/li[2]/a/span")
	private WebElement englishLink;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/span[2]/div/div/div/div[1]")
	private WebElement auidenceDropDown;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/span[1]/div/div/div/span[1]/span")
	private WebElement clearFunLink;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[10]/button/span[1]")
	private WebElement updateResultButton;
	
	@FindBy(xpath = "//*[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div[1]/div/div[2]/div/div/div/a")
	private WebElement firstGSENode;
	
	public EnglishPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getGseLink() {
		return gseLink;
	}

	public void setGseLink(WebElement gseLink) {
		this.gseLink = gseLink;
	}

	public WebElement getProductLink() {
		return productLink;
	}

	public void setProductLink(WebElement productLink) {
		this.productLink = productLink;
	}

	public WebElement getExternalFrameworkLink() {
		return externalFrameworkLink;
	}

	public void setExternalFrameworkLink(WebElement externalFrameworkLink) {
		this.externalFrameworkLink = externalFrameworkLink;
	}

	public WebElement getEnglishBanner() {
		return englishBanner;
	}

	public void setEnglishBanner(WebElement englishBanner) {
		this.englishBanner = englishBanner;
	}

	public WebElement getSecondTextImage() {
		return secondTextImage;
	}

	public void setSecondTextImage(WebElement secondTextImage) {
		this.secondTextImage = secondTextImage;
	}

	public WebElement getSecondTextEnglish() {
		return secondTextEnglish;
	}

	public void setSecondTextEnglish(WebElement secondTextEnglish) {
		this.secondTextEnglish = secondTextEnglish;
	}

	public WebElement getGseStructure() {
		return gseStructure;
	}

	public void setGseStructure(WebElement gseStructure) {
		this.gseStructure = gseStructure;
	}

	public By getGseActionLink() {
		return gseActionLink;
	}

	public void setGseActionLink(By gseActionLink) {
		this.gseActionLink = gseActionLink;
	}

	public WebElement getLoader() {
		return loader;
	}

	public WebElement getSelectAll() {
		return selectAll;
	}

	public WebElement getRenderedLink() {
		return renderedLink;
	}

	public WebElement getEnglishLink() {
		return englishLink;
	}

	public WebElement getAuidenceDropDown() {
		return auidenceDropDown;
	}

	public WebElement getClearFunLink() {
		return clearFunLink;
	}

	public WebElement getCancelFilterBySet() {
		return cancelFilterBySet;
	}

	public WebElement getDescriptiveIdWithFilterSet() {
		return descriptiveIdWithFilterSet;
	}

	public WebElement getUpdateResultButton() {
		return updateResultButton;
	}

	public WebElement getFirstGSENode() {
		return firstGSENode;
	}

}
