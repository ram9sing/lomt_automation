package lomt.pearson.page_object;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HEPom {
	
	WebDriver driver;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[2]")
	private WebElement heBanner;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[1]/a")
	private WebElement externalFrameworkLink;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[3]/a")
	private WebElement eoStructure;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[2]/a")
	private WebElement productLink;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[3]/a")
	private WebElement eoLink;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[2]/div[1]")
	private WebElement secondTextImage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[2]/div[2]")
	private WebElement secondTextHE;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[1]/span/span")
	private WebElement learningTitleName;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[1]/input")
	private WebElement learningTitleInputText;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[2]/span/span")
	private WebElement domainName;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[2]/div/div")
	private WebElement domainNameDropDown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement> domainList;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[3]/span/span")
	private WebElement statusName;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[3]/div/div")
	private WebElement statusDropDown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement> statusDropDownList;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[4]/span/span")
	private WebElement objectiveHierarchySetName;
	
	@FindBy(xpath = "")
	private WebElement objectiveHierarchySetDropDown;
	
	@FindBy(xpath = "//div[@class='ingestion-footer']/button[1]")
	private WebElement nextButton;
	
	@FindBy(xpath = "//div[@class='ingestion-footer']/button[2]")
	private WebElement backButton;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[1]/td[3]")
	private WebElement errorMessage1;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[2]/td[3]")
	private WebElement errorMessage2;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[3]/td[3]")
	private WebElement errorMessage3;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[4]/td[3]")
	private WebElement errorMessage4;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[5]/td[3]")
	private WebElement errorMessage5;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[6]/td[3]")
	private WebElement errorMessage6;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[7]/td[3]")
	private WebElement errorMessage7;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[8]/td[3]")
	private WebElement errorMessage8;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[9]/td[3]")
	private WebElement errorMessage9;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[10]/td[3]")
	private WebElement errorMessage10;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[11]/td[3]")
	private WebElement errorMessage11;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[12]/td[3]")
	private WebElement errorMessage12;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[13]/td[3]")
	private WebElement errorMessage13;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[14]/td[3]")
	private WebElement errorMessage14;
	
	//@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div/div[2]/div/div/div[1]/div/div/a")
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div/div/div[1]/div/span[2]/span[2]/a")
	private WebElement hegoalframework;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[7]/input")
	private WebElement heInnerSearchTerm;
	
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[2]/span")
	private WebElement heExport;
				      
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[3]/span")
	private WebElement heEXFExport;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[7]/input")
	private WebElement heEnterSearchTerm;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[8]/button")
	private WebElement heUpdateResultButton;
				        
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[3]/input")
	private WebElement heInnerEnterSearchTerm;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[4]/button")
	private WebElement heInnerUpdateResultButton;
	
	public HEPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getHeBanner() {
		return heBanner;
	}

	public void setHeBanner(WebElement heBanner) {
		this.heBanner = heBanner;
	}

	public WebElement getExternalFrameworkLink() {
		return externalFrameworkLink;
	}

	public void setExternalFrameworkLink(WebElement externalFrameworkLink) {
		this.externalFrameworkLink = externalFrameworkLink;
	}

	public WebElement getProductLink() {
		return productLink;
	}

	public void setProductLink(WebElement productLink) {
		this.productLink = productLink;
	}

	public WebElement getEoLink() {
		return eoLink;
	}

	public void setEoLink(WebElement eoLink) {
		this.eoLink = eoLink;
	}

	public WebElement getSecondTextImage() {
		return secondTextImage;
	}

	public void setSecondTextImage(WebElement secondTextImage) {
		this.secondTextImage = secondTextImage;
	}

	public WebElement getSecondTextHE() {
		return secondTextHE;
	}

	public void setSecondTextHE(WebElement secondTextHE) {
		this.secondTextHE = secondTextHE;
	}

	public WebElement getLearningTitleName() {
		return learningTitleName;
	}

	public void setLearningTitleName(WebElement learningTitleName) {
		this.learningTitleName = learningTitleName;
	}

	public WebElement getDomainName() {
		return domainName;
	}

	public void setDomainName(WebElement domainName) {
		this.domainName = domainName;
	}

	public WebElement getStatusName() {
		return statusName;
	}

	public void setStatusName(WebElement statusName) {
		this.statusName = statusName;
	}

	public WebElement getLearningTitleInputText() {
		return learningTitleInputText;
	}

	public void setLearningTitleInputText(WebElement learningTitleInputText) {
		this.learningTitleInputText = learningTitleInputText;
	}

	public WebElement getDomainNameDropDown() {
		return domainNameDropDown;
	}

	public void setDomainNameDropDown(WebElement domainNameDropDown) {
		this.domainNameDropDown = domainNameDropDown;
	}

	public WebElement getStatusDropDown() {
		return statusDropDown;
	}

	public void setStatusDropDown(WebElement statusDropDown) {
		this.statusDropDown = statusDropDown;
	}

	public WebElement getObjectiveHierarchySetName() {
		return objectiveHierarchySetName;
	}

	public void setObjectiveHierarchySetName(WebElement objectiveHierarchySetName) {
		this.objectiveHierarchySetName = objectiveHierarchySetName;
	}

	public WebElement getObjectiveHierarchySetDropDown() {
		return objectiveHierarchySetDropDown;
	}

	public void setObjectiveHierarchySetDropDown(WebElement objectiveHierarchySetDropDown) {
		this.objectiveHierarchySetDropDown = objectiveHierarchySetDropDown;
	}

	public List<WebElement> getDomainList() {
		return domainList;
	}

	public void setDomainList(List<WebElement> domainList) {
		this.domainList = domainList;
	}
	
	public List<WebElement> getStatusDropDownList() {
		return statusDropDownList;
	}

	public void setStatusDropDownList(List<WebElement> statusDropDownList) {
		this.statusDropDownList = statusDropDownList;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public void setNextButton(WebElement nextButton) {
		this.nextButton = nextButton;
	}

	public WebElement getBackButton() {
		return backButton;
	}

	public void setBackButton(WebElement backButton) {
		this.backButton = backButton;
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

	public void setErrorMessage5(WebElement errorMessage5) {
		this.errorMessage5 = errorMessage5;
	}

	public WebElement getErrorMessage6() {
		return errorMessage6;
	}

	public void setErrorMessage6(WebElement errorMessage6) {
		this.errorMessage6 = errorMessage6;
	}

	public WebElement getErrorMessage7() {
		return errorMessage7;
	}

	public void setErrorMessage7(WebElement errorMessage7) {
		this.errorMessage7 = errorMessage7;
	}

	public WebElement getErrorMessage8() {
		return errorMessage8;
	}

	public void setErrorMessage8(WebElement errorMessage8) {
		this.errorMessage8 = errorMessage8;
	}

	public WebElement getErrorMessage9() {
		return errorMessage9;
	}

	public void setErrorMessage9(WebElement errorMessage9) {
		this.errorMessage9 = errorMessage9;
	}

	public WebElement getErrorMessage10() {
		return errorMessage10;
	}

	public void setErrorMessage10(WebElement errorMessage10) {
		this.errorMessage10 = errorMessage10;
	}

	public WebElement getErrorMessage11() {
		return errorMessage11;
	}

	public void setErrorMessage11(WebElement errorMessage11) {
		this.errorMessage11 = errorMessage11;
	}

	public WebElement getErrorMessage12() {
		return errorMessage12;
	}

	public void setErrorMessage12(WebElement errorMessage12) {
		this.errorMessage12 = errorMessage12;
	}

	public WebElement getErrorMessage13() {
		return errorMessage13;
	}

	public void setErrorMessage13(WebElement errorMessage13) {
		this.errorMessage13 = errorMessage13;
	}

	public WebElement getErrorMessage14() {
		return errorMessage14;
	}

	public void setErrorMessage14(WebElement errorMessage14) {
		this.errorMessage14 = errorMessage14;
	}

	public WebElement getEoStructure() {
		return eoStructure;
	}

	public WebElement getHegoalframework() {
		return hegoalframework;
	}

	public WebElement getHeInnerSearchTerm() {
		return heInnerSearchTerm;
	}

	public WebElement getHeExport() {
		return heExport;
	}

	public WebElement getHeEnterSearchTerm() {
		return heEnterSearchTerm;
	}

	public WebElement getHeUpdateResultButton() {
		return heUpdateResultButton;
	}

	public WebElement getHeInnerEnterSearchTerm() {
		return heInnerEnterSearchTerm;
	}

	public WebElement getHeInnerUpdateResultButton() {
		return heInnerUpdateResultButton;
	}

	public WebElement getHeEXFExport() {
		return heEXFExport;
	}

}
