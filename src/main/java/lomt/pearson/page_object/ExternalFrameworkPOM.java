package lomt.pearson.page_object;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExternalFrameworkPOM {

	private WebDriver driver;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[1]/span/span")
	private WebElement desciptionText;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[1]/input")
	private WebElement desciptionVal;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[2]/span/span")
	private WebElement subjectText;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[2]/div/div")
	private WebElement subjectDropdown;

	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement> subjectList;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[3]/span/span")
	private WebElement issueDateText;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[3]/input")
	private WebElement issueDateVal;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[4]/span/span")
	private WebElement setText;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[4]/div/div")
	private WebElement setDropdown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement> setList;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[5]/span/span")
	private WebElement statusText;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[5]/div/div")
	private WebElement statusDropdown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement> statusList;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[6]/span/span")
	private WebElement applicationLevelText;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[6]/div/div")
	private WebElement applicationLevelDropdown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement> applicationLevelList;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[7]/span/span")
	private WebElement frameworkPurposeText;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[7]/div/div")
	private WebElement frameworkPurposeDropdown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement> purposeList;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[8]/span/span")
	private WebElement definedByText;

	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[8]/div/div")
	private WebElement definedByDropdown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement> definedByList;
	
	//Valdiation
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[1]/td[3]")
	private WebElement errorRowOne;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[2]/td[3]")
	private WebElement errorRowTwo;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[3]/td[3]")
	private WebElement errorRowThree;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[4]/td[3]")
	private WebElement errorRowFour;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[5]/td[3]")
	private WebElement errorRowFive;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[6]/td[3]")
	private WebElement errorRowSix;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[7]/td[3]")
	private WebElement errorRowSeven;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[8]/td[3]")
	private WebElement errorRowEight;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[9]/td[3]")
	private WebElement errorRowNine;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[10]/td[3]")
	private WebElement errorRowTen;
	
	@FindBy(xpath = "//div[@class='log-data-block-container']/table/tbody/tr[11]/td[3]")
	private WebElement errorRowEleven;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[2]/a")
	private WebElement externalFrameworkStructureEnglish;
	
	@FindBy(xpath = "//div[@class='filter-container']/div[1]/input")
	private WebElement enterSearchTerm;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/input")
	private WebElement enterSearchTermInnerCS;
	
	@FindBy(xpath = "//div[@class='filter-container']/div[2]/button")
	private WebElement updateResultButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[3]/button")
	private WebElement innerUpdateResultButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div/div/div[1]/div/span[2]/span[2]/a")
	private WebElement SearchedEXFTitle;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[1]/input")
	private WebElement externalFrameworkStructureHE;
					
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[1]/a")
	private WebElement externalFrameworkStructureBrowseHE;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[2]/a")
	private WebElement externalFrameworkStructureBrowseEnglish;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[1]/a")
	private WebElement curriculumStandardStructureBrowseSchool;
	
	@FindBy(xpath = "//div[@class='list-row row']/div[2]/div/span")
	private WebElement actionLink;
	
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[3]/span")
	private WebElement exportButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[1]/a")
	private WebElement externalFrameworkStructureCS;
	

	public ExternalFrameworkPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getDesciptionText() {
		return desciptionText;
	}

	public WebElement getDesciptionVal() {
		return desciptionVal;
	}

	public WebElement getSubjectText() {
		return subjectText;
	}

	public WebElement getSubjectDropdown() {
		return subjectDropdown;
	}

	public WebElement getIssueDateText() {
		return issueDateText;
	}

	public WebElement getIssueDateVal() {
		return issueDateVal;
	}

	public WebElement getSetText() {
		return setText;
	}

	public WebElement getSetDropdown() {
		return setDropdown;
	}

	public WebElement getStatusText() {
		return statusText;
	}

	public WebElement getStatusDropdown() {
		return statusDropdown;
	}

	public WebElement getApplicationLevelText() {
		return applicationLevelText;
	}

	public WebElement getApplicationLevelDropdown() {
		return applicationLevelDropdown;
	}

	public List<WebElement> getSubjectList() {
		return subjectList;
	}

	public WebElement getFrameworkPurposeText() {
		return frameworkPurposeText;
	}

	public WebElement getFrameworkPurposeDropdown() {
		return frameworkPurposeDropdown;
	}

	public WebElement getDefinedByText() {
		return definedByText;
	}

	public WebElement getDefinedByDropdown() {
		return definedByDropdown;
	}

	public List<WebElement> getStatusList() {
		return statusList;
	}

	public List<WebElement> getApplicationLevelList() {
		return applicationLevelList;
	}

	public List<WebElement> getPurposeList() {
		return purposeList;
	}

	public List<WebElement> getDefinedByList() {
		return definedByList;
	}

	public WebElement getErrorRowOne() {
		return errorRowOne;
	}

	public WebElement getErrorRowTwo() {
		return errorRowTwo;
	}

	public WebElement getErrorRowThree() {
		return errorRowThree;
	}

	public WebElement getErrorRowFour() {
		return errorRowFour;
	}

	public WebElement getErrorRowFive() {
		return errorRowFive;
	}

	public WebElement getExternalFrameworkStructureEnglish() {
		return externalFrameworkStructureEnglish;
	}

	public WebElement getEnterSearchTerm() {
		return enterSearchTerm;
	}

	public WebElement getUpdateResultButton() {
		return updateResultButton;
	}

	public WebElement getSearchedEXFTitle() {
		return SearchedEXFTitle;
	}

	public WebElement getExternalFrameworkStructureHE() {
		return externalFrameworkStructureHE;
	}

	public WebElement getExternalFrameworkStructureBrowseHE() {
		return externalFrameworkStructureBrowseHE;
	}

	public List<WebElement> getSetList() {
		return setList;
	}

	public WebElement getActionLink() {
		return actionLink;
	}

	public WebElement getExportButton() {
		return exportButton;
	}

	public WebElement getExternalFrameworkStructureBrowseEnglish() {
		return externalFrameworkStructureBrowseEnglish;
	}

	public WebElement getCurriculumStandardStructureBrowseSchool() {
		return curriculumStandardStructureBrowseSchool;
	}

	public WebElement getErrorRowSix() {
		return errorRowSix;
	}

	public WebElement getErrorRowSeven() {
		return errorRowSeven;
	}

	public WebElement getErrorRowEight() {
		return errorRowEight;
	}

	public WebElement getExternalFrameworkStructureCS() {
		return externalFrameworkStructureCS;
	}

	public WebElement getEnterSearchTermInnerCS() {
		return enterSearchTermInnerCS;
	}

	public WebElement getErrorRowNine() {
		return errorRowNine;
	}

	public WebElement getErrorRowTen() {
		return errorRowTen;
	}

	public WebElement getErrorRowEleven() {
		return errorRowEleven;
	}

	public WebElement getInnerUpdateResultButton() {
		return innerUpdateResultButton;
	}

}
