package lomt.pearson.page_object;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IntermediaryPOM {

	WebDriver driver;
	
	@FindBy(xpath = "//div[@class='items']/span[3]/a")
	private WebElement intermediaryLink;
	
	@FindBy(xpath = "//div[@class='list-data-container']/child::div")
	private List<WebElement> intermediaryGFList;
	
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[2]/span")
	private WebElement intermediaryExportButton;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[1]")
	private WebElement intermediaryChooseMessage;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[2]")
	private WebElement intermediaryProvideMessage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[2]/div[1]/img")
	private WebElement secondTextImage;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[3]")
	private WebElement intermediaryUploadMessage;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[4]")
	private WebElement intermediaryReviewMessage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div/span")
	private WebElement structure2Tile;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div/div[1]/input")
	private WebElement businessRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div/div[9]/input") 
	private WebElement economicsRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div/div[3]/input")
	private WebElement frenchRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div/div[4]/input")
	private WebElement geographyRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div/div[5]/input")
	private WebElement historyRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div/div[6]/input")
	private WebElement literacyRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div/div[7]/input")
	private WebElement mathematicsRadioButton; 
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div/div[8]/input")
	private WebElement scienceRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div/div[9]/input")
	private WebElement socialStudiesRadioButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[3]/a")
	private WebElement intermediaryStructure;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div/div")
	private WebElement loadMoreButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div/div[2]/div[1]/div/div[1]/div/div/a")
	private WebElement fistSubject;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div/div[2]/div[1]/div/div[2]/div/span/span[1]")
	private WebElement action;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/div")
	private WebElement  firstDisRow;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/div/span/span[2]/a")
	private WebElement  firstDisGoalframework;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[3]/input")
	private WebElement  enterSearchTerm;
				     
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[4]/button")
	private WebElement  updateResultButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[1]/div/div[2]/div/span[1]")
	private WebElement  searchResultText;
	
	public IntermediaryPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getIntermediaryChooseMessage() {
		return intermediaryChooseMessage;
	}

	public void setIntermediaryChooseMessage(WebElement intermediaryChooseMessage) {
		this.intermediaryChooseMessage = intermediaryChooseMessage;
	}

	public WebElement getIntermediaryProvideMessage() {
		return intermediaryProvideMessage;
	}

	public void setIntermediaryProvideMessage(WebElement intermediaryProvideMessage) {
		this.intermediaryProvideMessage = intermediaryProvideMessage;
	}

	public WebElement getIntermediaryUploadMessage() {
		return intermediaryUploadMessage;
	}

	public void setIntermediaryUploadMessage(WebElement intermediaryUploadMessage) {
		this.intermediaryUploadMessage = intermediaryUploadMessage;
	}

	public WebElement getIntermediaryReviewMessage() {
		return intermediaryReviewMessage;
	}

	public void setIntermediaryReviewMessage(WebElement intermediaryReviewMessage) {
		this.intermediaryReviewMessage = intermediaryReviewMessage;
	}

	public WebElement getBusinessRadioButton() {
		return businessRadioButton;
	}

	public void setBusinessRadioButton(WebElement businessRadioButton) {
		this.businessRadioButton = businessRadioButton;
	}

	public WebElement getStructure2Tile() {
		return structure2Tile;
	}

	public void setStructure2Tile(WebElement structure2Tile) {
		this.structure2Tile = structure2Tile;
	}

	public WebElement getEconomicsRadioButton() {
		return economicsRadioButton;
	}

	public void setEconomicsRadioButton(WebElement economicsRadioButton) {
		this.economicsRadioButton = economicsRadioButton;
	}

	public WebElement getFrenchRadioButton() {
		return frenchRadioButton;
	}

	public void setFrenchRadioButton(WebElement frenchRadioButton) {
		this.frenchRadioButton = frenchRadioButton;
	}

	public WebElement getGeographyRadioButton() {
		return geographyRadioButton;
	}

	public void setGeographyRadioButton(WebElement geographyRadioButton) {
		this.geographyRadioButton = geographyRadioButton;
	}

	public WebElement getHistoryRadioButton() {
		return historyRadioButton;
	}

	public void setHistoryRadioButton(WebElement historyRadioButton) {
		this.historyRadioButton = historyRadioButton;
	}

	public WebElement getLiteracyRadioButton() {
		return literacyRadioButton;
	}

	public void setLiteracyRadioButton(WebElement literacyRadioButton) {
		this.literacyRadioButton = literacyRadioButton;
	}

	public WebElement getMathematicsRadioButton() {
		return mathematicsRadioButton;
	}

	public void setMathematicsRadioButton(WebElement mathematicsRadioButton) {
		this.mathematicsRadioButton = mathematicsRadioButton;
	}

	public WebElement getScienceRadioButton() {
		return scienceRadioButton;
	}

	public void setScienceRadioButton(WebElement scienceRadioButton) {
		this.scienceRadioButton = scienceRadioButton;
	}

	public WebElement getSocialStudiesRadioButton() {
		return socialStudiesRadioButton;
	}

	public void setSocialStudiesRadioButton(WebElement socialStudiesRadioButton) {
		this.socialStudiesRadioButton = socialStudiesRadioButton;
	}

	public WebElement getSecondTextImage() {
		return secondTextImage;
	}

	public void setSecondTextImage(WebElement secondTextImage) {
		this.secondTextImage = secondTextImage;
	}

	public WebElement getIntermediaryLink() {
		return intermediaryLink;
	}

	public void setIntermediaryLink(WebElement intermediaryLink) {
		this.intermediaryLink = intermediaryLink;
	}

	public List<WebElement> getIntermediaryGFList() {
		return intermediaryGFList;
	}

	public void setIntermediaryGFList(List<WebElement> intermediaryGFList) {
		this.intermediaryGFList = intermediaryGFList;
	}

	public WebElement getIntermediaryExportButton() {
		return intermediaryExportButton;
	}

	public void setIntermediaryExportButton(WebElement intermediaryExportButton) {
		this.intermediaryExportButton = intermediaryExportButton;
	}

	public WebElement getIntermediaryStructure() {
		return intermediaryStructure;
	}

	public WebElement getLoadMoreButton() {
		return loadMoreButton;
	}

	public WebElement getFistSubject() {
		return fistSubject;
	}

	public WebElement getAction() {
		return action;
	}

	public WebElement getFirstDisRow() {
		return firstDisRow;
	}

	public WebElement getFirstDisGoalframework() {
		return firstDisGoalframework;
	}

	public WebElement getEnterSearchTerm() {
		return enterSearchTerm;
	}

	public WebElement getUpdateResultButton() {
		return updateResultButton;
	}

	public WebElement getSearchResultText() {
		return searchResultText;
	}
	
	/*public static void main(String[] args) {
		String name = "IntermediarySpreadsheetImport\n"
				+ " Accounting";
		int i = name.indexOf("\n");
		System.out.println(name.substring(i, name.length()));
	}*/

}
