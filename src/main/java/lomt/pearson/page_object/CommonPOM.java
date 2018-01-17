package lomt.pearson.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonPOM {

	private WebDriver driver;
	
	@FindBy(xpath = "//div[@class='unit-container']/div/div[3]/div/a")
	private WebElement englishLOB;
	
	@FindBy(xpath = "//div[@class='unit-container']/div/div[2]/div/a") //UAT, current
	private WebElement heLOB;
	
	/*@FindBy(xpath = "//div[@class='unit-container']/div/div[3]/div/a") //PPE
	private WebElement heLOB;*/

	@FindBy(xpath = "//div[@class='unit-container']/div/div[1]/div/a")
	private WebElement schoolGlobalLOB;

	@FindBy(xpath = "//div[@class='unit-container']/div/div[1]/div/a")
	private WebElement nalsLOB;
	
	//@FindBy(xpath = "//div[@id='browse-grids']/div[1]/span[1]/div/span[1]")
	@FindBy(xpath = "//div[@id='browse-grids']/div[1]/div[1]/span[1]")
	private WebElement manageIngestion;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div/ol/li/a")
	private WebElement backLinkFirst;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div/div[1]/div/ol/li/a")
	private WebElement backLinkSec;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/span")
	private WebElement createUploadStructureHeader;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div/div[1]/span")
	private WebElement createUploadStructureHeader2;
	
	@FindBy(xpath = "//div[@class='ingestion-type-container']/span")
	private WebElement selectLOBTitle;
	
	@FindBy(xpath = "//div[@class='ingestion-type-container']/div[1]/input")
	private WebElement englishLOBRadioButton;
	
	//HE LOB info###
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[1]/div[2]/input")
	private WebElement heLOBRadioButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/div[3]/span")
	private WebElement eoStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[1]/div[3]/input")
	private WebElement schoolGlobalLOBRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[1]/div[4]/input")
	private WebElement nalsLOBRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[2]/div[2]/input")
	private WebElement nalsEXFStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[2]/div[2]/input")
	private WebElement sgEXFStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[2]/div[4]/input")
	private WebElement schoolIntermeidaryStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/span")
	private WebElement selectStructureTitle;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[1]/input")
	private WebElement gseStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[2]/input")
	private WebElement  gseExternalFrameworkStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[2]/input")
	private WebElement  englishExternalFrameworkStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[3]/input")
	private WebElement gseProductStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[1]/input")
	private WebElement  productExternalFrameworkStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[2]/input")
	private WebElement productStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[3]/input")
	private WebElement schoolProductStructureRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[3]/input")
	private WebElement productHERadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[1]/input")
	private WebElement curriculumStandardStructure;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[3]/input")
	private WebElement intermediaryStructure;
	
	@FindBy(xpath = "//div[@class='ingestion-footer']/button")
	private WebElement nextButtonFirst;
	
	@FindBy(xpath = "//div[@class='ingestion-footer']/button[1]")
	private WebElement nextButton;
	
	@FindBy(xpath = "//div[@class='ingestion-footer']/button[2]")
	private WebElement backButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[1]/input")
	private WebElement curriculumStandardRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[2]/input")
	private WebElement productRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[3]/input")
	private WebElement educationalObjRadioButton;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div/div[2]/div[4]/input")
	private WebElement intermediaryRadioButton;
	
	//@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[2]/button")
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[1]/div[1]/div[2]/button")
	private WebElement viewIngestFullLogButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[3]/button")
	private WebElement cancelButton;
	
	@FindBy(xpath = "//div[@class='upload-file-container']/div/div/p/span[2]")
	private WebElement uploadFileLink;
	
	@FindBy(xpath = "//div[@class='ingestion']/div[2]/button[2]")
	private WebElement backButtonSt2;
	
	@FindBy(xpath = "//div[@class='ingestion']/div[2]/button[1]")
	private WebElement nextButtonSt2;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[1]/div[1]/div[1]/div/label")
	private WebElement ingestFailed;
	
	@FindBy(xpath = "//div[@class='ingestion']/div[1]/div[1]/div/div/label")
	private WebElement ingestSucessful; 
	
	@FindBy(xpath = "//div[contains(@class,'ingestion-status')]/div[1]")
	private WebElement waitForIngestionStatusText;
	
	@FindBy(xpath = "//div[@class='ingestion']/div[2]/button")
	private WebElement doneButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/header/a/img")
	private WebElement pearsonLogo;
	
	//browse page
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/span")
	private WebElement welcomeTitle;
		
	//right side message
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[1]/div[1]")
	private WebElement firstTextImage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[1]/div[2]")
	private WebElement firstTextMessage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[3]/div[1]/img")
	private WebElement thirdTextImage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[3]/div[2]")
	private WebElement thirdTextMessage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[4]/div[2]")
	private WebElement fourthTextMessage;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[2]/div/div[3]/div[1]/img")
	private WebElement fourthTextImage;
	
	//upload files page	
	@FindBy(xpath = "//div[@class='upload-file-text']/p[1]")
	private WebElement dragAndDropFilesText;
	
	@FindBy(xpath = "//div[@class='upload-file-text']/span/img")
	private WebElement plusSign;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/thead/tr/th[1]")
	private WebElement rowNo;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/thead/tr/th[2]")
	private WebElement type;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/thead/tr/th[3]")
	private WebElement message;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/header/span/span")
	private WebElement logoutOption;
	
	//@FindBy(xpath = "//div[@id='userInfoPopoverId']/div[2]/div/div[4]/button")
	@FindBy(xpath = "//div[@id='userInfoPopoverId']/div[2]/div/div[5]/button")
	private WebElement logout;
	
	@FindBy(xpath = "//div[@class='list-common-header']/span[1]")
	private By resultCountBy;
	
	@FindBy(xpath = "//div[@class='list-common-header']/span[1]")
	private WebElement resultCount;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[12]/button")
	private WebElement updateResultButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div/div/div[1]/div/input")
	private WebElement egCheckbox;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[1]/div/div[2]/div/button[2]")
	private WebElement exportButton;
	
	@FindBy(xpath = "//div[@class='filter-container']/div[2]/input")
	private WebElement enterSearchTerm;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div[1]")
	private WebElement egFirstDiv;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[8]/input")
	private WebElement descriptiveIdADSearch;
	
	//validation mesages
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/tbody/tr[1]/td[3]")
	private WebElement errorMessage1;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/tbody/tr[1]/td[3]")
	private WebElement errorMessage2;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/tbody/tr[1]/td[3]")
	private WebElement errorMessage3;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/tbody/tr[1]/td[3]")
	private WebElement errorMessage4;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/tbody/tr[1]/td[3]")
	private WebElement errorMessage5;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/tbody/tr[1]/td[3]")
	private WebElement errorMessage6;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/table/tbody/tr[1]/td[3]")
	private WebElement errorMessage7;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div/ol/li[1]/a")
	private WebElement homeLink;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[2]/a")
	private WebElement schoolProductTOCStructure;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[3]/a")
	private WebElement englishProductTOCStructure;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[4]/input")
	private WebElement tocEngEnterSearchTerm;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[5]/button/span[2]")
	private WebElement tocEngUpdateBtn;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[2]/a")
	private WebElement heProductTOCStructure;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div/ol/li[2]/a/span")
	private WebElement commonLOMTStructurePath;
					 
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div/span")
	private WebElement resultFountText;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[1]/div/div[2]/div/span[1]")
	private WebElement showingText;
					  
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div/div/div[1]/div/span[2]/span[2]/a")
	private WebElement searchGoalframework;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div/div[2]/div/div[1]/div[1]/input")
	private WebElement productTOCMetaDataDesc;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[2]/button")
	private WebElement previousButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[1]/div[1]/div/div[2]/h4/span[2]")
	private WebElement xlsFileExtention;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div[1]/div/div[1]/div/span[2]/span[2]/a")
	private WebElement exfFirtBrowsedGF;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/span")
	private WebElement exfFirtBrowsedGFTitle;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[1]/div/div[2]/div/span[1]")
	private WebElement showingGFText;   
	
	@FindBy(xpath = "//div[@id='app-header']/span/span/span") 
	private WebElement loggedInUserLink;
	
	@FindBy(xpath = "//div[@id='userInfoPopoverId']/div[2]/div/div[1]/div")
	private WebElement loggedInUser;
	
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[2]/span")
	private WebElement exfExport;
	
	public WebElement getPearsonLogo() {
		return pearsonLogo;
	}

	public void setPearsonLogo(WebElement pearsonLogo) {
		this.pearsonLogo = pearsonLogo;
	}

	public CommonPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSchoolGlobalLOB() {
		return schoolGlobalLOB;
	}

	public void setSchoolGlobalLOB(WebElement schoolGlobalLOB) {
		this.schoolGlobalLOB = schoolGlobalLOB;
	}

	public WebElement getNalsLOB() {
		return nalsLOB;
	}

	public WebElement getEnglishLOB() {
		return englishLOB;
	}

	public void setEnglishLOB(WebElement englishLOB) {
		this.englishLOB = englishLOB;
	}

	public void setNalsLOB(WebElement nalsLOB) {
		this.nalsLOB = nalsLOB;
	}

	public WebElement getManageIngestion() {
		return manageIngestion;
	}

	public void setManageIngestion(WebElement manageIngestion) {
		this.manageIngestion = manageIngestion;
	}

	public WebElement getBackLinkFirst() {
		return backLinkFirst;
	}

	public void setBackLinkFirst(WebElement backLinkFirst) {
		this.backLinkFirst = backLinkFirst;
	}

	public WebElement getCreateUploadStructureHeader() {
		return createUploadStructureHeader;
	}

	public void setCreateUploadStructureHeader(WebElement createUploadStructureHeader) {
		this.createUploadStructureHeader = createUploadStructureHeader;
	}

	public WebElement getSelectLOBTitle() {
		return selectLOBTitle;
	}

	public void setSelectLOBTitle(WebElement selectLOBTitle) {
		this.selectLOBTitle = selectLOBTitle;
	}

	public WebElement getEnglishLOBRadioButton() {
		return englishLOBRadioButton;
	}

	public void setEnglishLOBRadioButton(WebElement englishLOBRadioButton) {
		this.englishLOBRadioButton = englishLOBRadioButton;
	}

	public WebElement getHeLOBRadioButton() {
		return heLOBRadioButton;
	}

	public void setHeLOBRadioButton(WebElement heLOBRadioButton) {
		this.heLOBRadioButton = heLOBRadioButton;
	}

	public WebElement getSchoolGlobalLOBRadioButton() {
		return schoolGlobalLOBRadioButton;
	}

	public void setSchoolGlobalLOBRadioButton(WebElement schoolGlobalLOBRadioButton) {
		this.schoolGlobalLOBRadioButton = schoolGlobalLOBRadioButton;
	}

	public WebElement getNalsLOBRadioButton() {
		return nalsLOBRadioButton;
	}

	public void setNalsLOBRadioButton(WebElement nalsLOBRadioButton) {
		this.nalsLOBRadioButton = nalsLOBRadioButton;
	}

	public WebElement getSelectStructureTitle() {
		return selectStructureTitle;
	}
	
	public WebElement getDragAndDropFilesText() {
		return dragAndDropFilesText;
	}

	public void setDragAndDropFilesText(WebElement dragAndDropFilesText) {
		this.dragAndDropFilesText = dragAndDropFilesText;
	}

	public void setSelectStructureTitle(WebElement selectStructureTitle) {
		this.selectStructureTitle = selectStructureTitle;
	}

	public WebElement getWelcomeTitle() {
		return welcomeTitle;
	}

	public void setWelcomeTitle(WebElement welcomeTitle) {
		this.welcomeTitle = welcomeTitle;
	}

	public WebElement getGseStructureRadioButton() {
		return gseStructureRadioButton;
	}

	public void setGseStructureRadioButton(WebElement gseStructureRadioButton) {
		this.gseStructureRadioButton = gseStructureRadioButton;
	}

	public WebElement getNextButtonFirst() {
		return nextButtonFirst;
	}

	public void setNextButtonFirst(WebElement nextButtonFirst) {
		this.nextButtonFirst = nextButtonFirst;
	}

	public WebElement getCurriculumStandardRadioButton() {
		return curriculumStandardRadioButton;
	}

	public void setCurriculumStandardRadioButton(WebElement sgCurriculumStandardRadioButton) {
		this.curriculumStandardRadioButton = sgCurriculumStandardRadioButton;
	}

	public WebElement getProductRadioButton() {
		return productRadioButton;
	}

	public void setProductRadioButton(WebElement sgProductRadioButton) {
		this.productRadioButton = sgProductRadioButton;
	}

	public WebElement getIntermediaryRadioButton() {
		return intermediaryRadioButton;
	}

	public void setIntermediaryRadioButton(WebElement sgIntermediaryRadioButton) {
		this.intermediaryRadioButton = sgIntermediaryRadioButton;
	}

	public WebElement getViewIngestFullLogButton() {
		return viewIngestFullLogButton;
	}

	public void setViewIngestFullLogButton(WebElement viewIngestFullLogButton) {
		this.viewIngestFullLogButton = viewIngestFullLogButton;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(WebElement cancelButton) {
		this.cancelButton = cancelButton;
	}

	public WebElement getUploadFileLink() {
		return uploadFileLink;
	}

	public void setUploadFileLink(WebElement uploadFileLink) {
		this.uploadFileLink = uploadFileLink;
	}
	
	public WebElement getBackButtonSt2() {
		return backButtonSt2;
	}

	public void setBackButtonSt2(WebElement backButtonSt2) {
		this.backButtonSt2 = backButtonSt2;
	}

	public WebElement getNextButtonSt2() {
		return nextButtonSt2;
	}

	public void setNextButtonSt2(WebElement nextButtonSt2) {
		this.nextButtonSt2 = nextButtonSt2;
	}

	public WebElement getIngestFailed() {
		return ingestFailed;
	}

	public void setIngestFailed(WebElement ingestFailed) {
		this.ingestFailed = ingestFailed;
	}

	public WebElement getIngestSucessful() {
		return ingestSucessful;
	}

	public void setIngestSucessful(WebElement ingestSucessful) {
		this.ingestSucessful = ingestSucessful;
	}

	public WebElement getDoneButton() {
		return doneButton;
	}

	public void setDoneButton(WebElement doneButton) {
		this.doneButton = doneButton;
	}

	public WebElement getHeLOB() {
		return heLOB;
	}

	public void setHeLOB(WebElement heLOB) {
		this.heLOB = heLOB;
	}

	public WebElement getGseExternalFrameworkStructureRadioButton() {
		return gseExternalFrameworkStructureRadioButton;
	}

	public void setGseExternalFrameworkStructureRadioButton(WebElement gseExternalFrameworkStructureRadioButton) {
		this.gseExternalFrameworkStructureRadioButton = gseExternalFrameworkStructureRadioButton;
	}

	public WebElement getGseProductStructureRadioButton() {
		return gseProductStructureRadioButton;
	}

	public void setGseProductStructureRadioButton(WebElement gseProductStructureRadioButton) {
		this.gseProductStructureRadioButton = gseProductStructureRadioButton;
	}

	public WebElement getProductExternalFrameworkStructureRadioButton() {
		return productExternalFrameworkStructureRadioButton;
	}

	public void setProductExternalFrameworkStructureRadioButton(WebElement productExternalFrameworkStructureRadioButton) {
		this.productExternalFrameworkStructureRadioButton = productExternalFrameworkStructureRadioButton;
	}

	public WebElement getProductStructureRadioButton() {
		return productStructureRadioButton;
	}

	public void setProductStructureRadioButton(WebElement productStructureRadioButton) {
		this.productStructureRadioButton = productStructureRadioButton;
	}

	public WebElement getProductHERadioButton() {
		return productHERadioButton;
	}

	public void setProductHERadioButton(WebElement productHERadioButton) {
		this.productHERadioButton = productHERadioButton;
	}

	public WebElement getCurriculumStandardStructure() {
		return curriculumStandardStructure;
	}

	public void setCurriculumStandardStructure(WebElement curriculumStandardStructure) {
		this.curriculumStandardStructure = curriculumStandardStructure;
	}

	public WebElement getIntermediaryStructure() {
		return intermediaryStructure;
	}

	public void setIntermediaryStructure(WebElement intermediaryStructure) {
		this.intermediaryStructure = intermediaryStructure;
	}

	public WebElement getFirstTextImage() {
		return firstTextImage;
	}

	public void setFirstTextImage(WebElement firstTextImage) {
		this.firstTextImage = firstTextImage;
	}

	public WebElement getFirstTextMessage() {
		return firstTextMessage;
	}

	public void setFirstTextMessage(WebElement firstTextMessage) {
		this.firstTextMessage = firstTextMessage;
	}

	public WebElement getThirdTextImage() {
		return thirdTextImage;
	}

	public void setThirdTextImage(WebElement thirdTextImage) {
		this.thirdTextImage = thirdTextImage;
	}

	public WebElement getFourthTextMessage() {
		return fourthTextMessage;
	}

	public void setFourthTextMessage(WebElement fourthTextMessage) {
		this.fourthTextMessage = fourthTextMessage;
	}

	public WebElement getFourthTextImage() {
		return fourthTextImage;
	}

	public void setFourthTextImage(WebElement fourthTextImage) {
		this.fourthTextImage = fourthTextImage;
	}

	public WebElement getThirdTextMessage() {
		return thirdTextMessage;
	}

	public void setThirdTextMessage(WebElement thirdTextMessage) {
		this.thirdTextMessage = thirdTextMessage;
	}

	public WebElement getPlusSign() {
		return plusSign;
	}

	public void setPlusSign(WebElement plusSign) {
		this.plusSign = plusSign;
	}

	public WebElement getRowNo() {
		return rowNo;
	}

	public void setRowNo(WebElement rowNo) {
		this.rowNo = rowNo;
	}

	public WebElement getType() {
		return type;
	}

	public void setType(WebElement type) {
		this.type = type;
	}

	public WebElement getMessage() {
		return message;
	}

	public void setMessage(WebElement message) {
		this.message = message;
	}

	public WebElement getLogoutOption() {
		return logoutOption;
	}

	public void setLogoutOption(WebElement logoutOption) {
		this.logoutOption = logoutOption;
	}

	public WebElement getLogout() {
		return logout;
	}

	public void setLogout(WebElement logout) {
		this.logout = logout;
	}

	public By getResultCountBy() {
		return resultCountBy;
	}

	public void setResultCountBy(By resultCountBy) {
		this.resultCountBy = resultCountBy;
	}

	public WebElement getResultCount() {
		return resultCount;
	}

	public void setResultCount(WebElement resultCount) {
		this.resultCount = resultCount;
	}

	public WebElement getUpdateResultButton() {
		return updateResultButton;
	}

	public void setUpdateResultButton(WebElement updateResultButton) {
		this.updateResultButton = updateResultButton;
	}

	public WebElement getEgCheckbox() {
		return egCheckbox;
	}

	public void setEgCheckbox(WebElement egCheckbox) {
		this.egCheckbox = egCheckbox;
	}

	public WebElement getExportButton() {
		return exportButton;
	}

	public void setExportButton(WebElement exportButton) {
		this.exportButton = exportButton;
	}

	public WebElement getEnterSearchTerm() {
		return enterSearchTerm;
	}

	public void setEnterSearchTerm(WebElement enterSearchTerm) {
		this.enterSearchTerm = enterSearchTerm;
	}

	public WebElement getEgFirstDiv() {
		return egFirstDiv;
	}

	public void setEgFirstDiv(WebElement egFirstDiv) {
		this.egFirstDiv = egFirstDiv;
	}

	public WebElement getDescriptiveIdADSearch() {
		return descriptiveIdADSearch;
	}

	public void setDescriptiveIdADSearch(WebElement descriptiveIdADSearch) {
		this.descriptiveIdADSearch = descriptiveIdADSearch;
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

	public WebElement getEducationalObjRadioButton() {
		return educationalObjRadioButton;
	}

	public void setEducationalObjRadioButton(WebElement educationalObjRadioButton) {
		this.educationalObjRadioButton = educationalObjRadioButton;
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

	public WebElement getSchoolProductStructureRadioButton() {
		return schoolProductStructureRadioButton;
	}

	public void setSchoolProductStructureRadioButton(WebElement schoolProductStructureRadioButton) {
		this.schoolProductStructureRadioButton = schoolProductStructureRadioButton;
	}

	public WebElement getEnglishExternalFrameworkStructureRadioButton() {
		return englishExternalFrameworkStructureRadioButton;
	}

	public WebElement getBackLinkSec() {
		return backLinkSec;
	}

	public WebElement getCreateUploadStructureHeader2() {
		return createUploadStructureHeader2;
	}

	public WebElement getWaitForIngestionStatusText() {
		return waitForIngestionStatusText;
	}

	public WebElement getHomeLink() {
		return homeLink;
	}

	public WebElement getNalsEXFStructureRadioButton() {
		return nalsEXFStructureRadioButton;
	}

	public WebElement getSgEXFStructureRadioButton() {
		return sgEXFStructureRadioButton;
	}

	public WebElement getSchoolIntermeidaryStructureRadioButton() {
		return schoolIntermeidaryStructureRadioButton;
	}

	public WebElement getSchoolProductTOCStructure() {
		return schoolProductTOCStructure;
	}

	public WebElement getCommonLOMTStructurePath() {
		return commonLOMTStructurePath;
	}

	public WebElement getResultFountText() {
		return resultFountText;
	}

	public WebElement getShowingText() {
		return showingText;
	}

	public WebElement getSearchGoalframework() {
		return searchGoalframework;
	}

	public WebElement getProductTOCMetaDataDesc() {
		return productTOCMetaDataDesc;
	}

	public WebElement getEnglishProductTOCStructure() {
		return englishProductTOCStructure;
	}

	public WebElement getHeProductTOCStructure() {
		return heProductTOCStructure;
	}

	public WebElement getEoStructureRadioButton() {
		return eoStructureRadioButton;
	}

	public WebElement getPreviousButton() {
		return previousButton;
	}

	public WebElement getXlsFileExtention() {
		return xlsFileExtention;
	}

	public WebElement getExfFirtBrowsedGF() {
		return exfFirtBrowsedGF;
	}

	public WebElement getExfFirtBrowsedGFTitle() {
		return exfFirtBrowsedGFTitle;
	}

	public WebElement getShowingGFText() {
		return showingGFText;
	}

	public WebElement getLoggedInUserLink() {
		return loggedInUserLink;
	}

	public WebElement getLoggedInUser() {
		return loggedInUser;
	}

	public WebElement getExfExport() {
		return exfExport;
	}

	public WebElement getTocEngEnterSearchTerm() {
		return tocEngEnterSearchTerm;
	}

	public WebElement getTocEngUpdateBtn() {
		return tocEngUpdateBtn;
	}

	public void setTocEngUpdateBtn(WebElement tocEngUpdateBtn) {
		this.tocEngUpdateBtn = tocEngUpdateBtn;
	}

}
