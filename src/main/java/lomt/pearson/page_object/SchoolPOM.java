package lomt.pearson.page_object;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SchoolPOM {
	
	String employeeName;

	WebDriver driver;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/div[1]/span")
	private WebElement  curriculumStructure;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/div[2]/span")
	private WebElement  curriculumStructureCustom;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/div[3]/span")
	private WebElement  productStructure;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/div[4]/span")
	private WebElement  intermediaryStructure;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[1]/div/div")
	private WebElement  subjectDropdown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement>  subjectDropdownList;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[2]/div/div")
	private WebElement  countryDropdown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement>  countryDropdownList;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[3]/div/div")
	private WebElement  authorityDropdown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement>  authorityDropdownList;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[4]/div/div")
	private WebElement  curriculumSetDropdown;
	
	@FindBy(xpath = "//div[@class='select-dropdown']/div/div[@class='Select-menu-outer']/div/child::div")
	private List<WebElement>  curriculumSetDropdownList;
	
	@FindBy(xpath = "//div[@class='ingestion']/div/div[1]/div[5]/input")
	private WebElement  adoptedYear;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[1]/a")
	private WebElement  curriculumSt;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[1]/div/div[2]/div/span[1]")
	private WebElement  resultFound;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div/span")
	private WebElement  innerResultFound;	
	
	//@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div/div[2]/div/div/div[1]/div/div/a")
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div[1]/div/div[1]/div/span[2]/span[2]/a")
	private WebElement  curriculumGoalFramework;
	
	@FindBy(xpath = "//div[@id='curriculum-tree']/ul/li[1]/span[1]")
	private WebElement  curriculumGrade;
					  
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div/span/span[1]")
	private WebElement  action;
	
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[3]/span")
	private WebElement  export;
					 			
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[4]/div/div[2]/input")
	private WebElement  innerEnterSearch;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[7]/input")
	private WebElement  enterEnterSearch;
				
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[8]/button")
	private WebElement  schoolUpdateResultButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[5]/button")
	private WebElement  schoolInnerUpdateResultButton;
	
	@FindBy(xpath = "//div[@id='curriculum-tree']/ul/li[1]/span[3]/span/div/span/span/span/span")
	private WebElement  gradeText1;
	
	@FindBy(xpath = "//div[@id='curriculum-tree']/ul/li[2]/span[3]/span/div/span/span/span/span")
	private WebElement  gradeText2;
	
	@FindBy(xpath = "//div[@id='curriculum-tree']/ul/li[3]/a/span/div/span/span/span/span")
	private WebElement  gradeText3;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div[1]/div[1]/div[2]/div[1]/div[3]/div")
	private List<WebElement>  parentChildList;
	
	@FindBy(xpath = "//*[@id='curriculum-tree']/ul/li[3]/span[1]")
	private WebElement  expandGrade3;
	
	@FindBy(xpath = "//div[@id='curriculum-tree']/ul/li[3]/ul/li[5]/a/span/div/span/span/span/span[2]")
	private WebElement  newAddedNode;
	
	@FindBy(xpath = "//div[@class='upload-intermediary-type-container']")
	private List<WebElement>  disciplineList;
	
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[2]/span")
	private WebElement  intExport;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div/div[2]/div[1]/div/div[1]/div/div/a") //css changed need to check it
	private WebElement  firstDisRow;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div/div[2]/div[1]/div/div[2]/div/span/span[1]")
	private WebElement  intAction;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div/div[2]/div/div[1]/div[6]/input")
	private WebElement  csSourceURL;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div/div[2]/div/div[1]/div[7]/input")
	private WebElement  csInfoURL;
	
	//GOALFRAMEWORK META DATA SECTION
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/span")
	private WebElement  goalframeworkHeaderTitle;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[1]/span[2]")
	private WebElement  CSMetaDataArrowAndText;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[1]/div/div/div[1]/div")
	private WebElement  urnLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[1]/div/div/div[2]/div")
	private WebElement  urnLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div[1]/div")
	private WebElement  titleLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[2]/div/div/div[2]/div")
	private WebElement  titleLevelVal;

	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[3]/div/div/div[1]/div")
	private WebElement  descriptionLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[4]/div/div/div[1]/div")
	private WebElement  definedByLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[4]/div/div/div[2]/div")
	private WebElement  definedByLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[5]/div/div/div[1]/div")
	private WebElement  subjectLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[5]/div/div/div[2]/div")
	private WebElement  subjectLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[6]/div/div/div[1]/div")
	private WebElement  countryLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[6]/div/div/div[2]/div")
	private WebElement  countryLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[7]/div/div/div[1]/div")
	private WebElement  issueDateLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[7]/div/div/div[2]/div")
	private WebElement  issueDateLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[8]/div/div/div[1]/div")
	private WebElement  setsLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[8]/div/div/div[2]/div")
	private WebElement  setsLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[9]/div/div/div[1]/div")
	private WebElement  statusLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[9]/div/div/div[2]/div")
	private WebElement  statusLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[10]/div/div/div[1]/div")
	private WebElement  frameworkLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[10]/div/div/div[2]/div")
	private WebElement  frameworkLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[11]/div/div/div[1]/div")
	private WebElement  lastUpdatedLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[11]/div/div/div[2]/div")
	private WebElement  lastUpdatedLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[12]/div/div/div[1]/div")
	private WebElement  ingestionTypeLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[12]/div/div/div[2]/div")
	private WebElement  ingestionTypeLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[13]/div/div/div[1]/div")
	private WebElement  sourceURLLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[13]/div/div/div[2]/div")
	private WebElement  sourceURLLevelVal;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[14]/div/div/div[1]/div")
	private WebElement  curriculumInfoLevel;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[1]/div[2]/div[2]/div[14]/div/div/div[2]/div")
	private WebElement  curriculumInfoLevelVal;
	
	public SchoolPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getProductStructure() {
		return productStructure;
	}

	public WebElement getIntermediaryStructure() {
		return intermediaryStructure;
	}

	public WebElement getSubjectDropdown() {
		return subjectDropdown;
	}

	public List<WebElement> getSubjectDropdownList() {
		return subjectDropdownList;
	}

	public WebElement getAuthorityDropdown() {
		return authorityDropdown;
	}

	public List<WebElement> getAuthorityDropdownList() {
		return authorityDropdownList;
	}

	public WebElement getCurriculumSetDropdown() {
		return curriculumSetDropdown;
	}

	public List<WebElement> getCurriculumSetDropdownList() {
		return curriculumSetDropdownList;
	}

	public WebElement getAdoptedYear() {
		return adoptedYear;
	}

	public WebElement getCurriculumStructure() {
		return curriculumStructure;
	}

	public WebElement getCurriculumStructureCustom() {
		return curriculumStructureCustom;
	}

	public WebElement getCurriculumSt() {
		return curriculumSt;
	}

	public WebElement getResultFound() {
		return resultFound;
	}

	public WebElement getAction() {
		return action;
	}

	public WebElement getExport() {
		return export;
	}

	public WebElement getCurriculumGoalFramework() {
		return curriculumGoalFramework;
	}

	public WebElement getCurriculumGrade() {
		return curriculumGrade;
	}

	public WebElement getInnerEnterSearch() {
		return innerEnterSearch;
	}

	public WebElement getInnerResultFound() {
		return innerResultFound;
	}

	public WebElement getGradeText1() {
		return gradeText1;
	}

	public WebElement getGradeText2() {
		return gradeText2;
	}

	public WebElement getGradeText3() {
		return gradeText3;
	}

	public List<WebElement> getParentChildList() {
		return parentChildList;
	}

	public WebElement getExpandGrade3() {
		return expandGrade3;
	}

	public WebElement getNewAddedNode() {
		return newAddedNode;
	}

	public List<WebElement> getDisciplineList() {
		return disciplineList;
	}

	public WebElement getIntExport() {
		return intExport;
	}

	public WebElement getFirstDisRow() {
		return firstDisRow;
	}

	public WebElement getIntAction() {
		return intAction;
	}

	public WebElement getEnterEnterSearch() {
		return enterEnterSearch;
	}

	public WebElement getSchoolUpdateResultButton() {
		return schoolUpdateResultButton;
	}

	public WebElement getSchoolInnerUpdateResultButton() {
		return schoolInnerUpdateResultButton;
	}

	public WebElement getCsSourceURL() {
		return csSourceURL;
	}

	public WebElement getCsInfoURL() {
		return csInfoURL;
	}

	public WebElement getCountryDropdown() {
		return countryDropdown;
	}

	public List<WebElement> getCountryDropdownList() {
		return countryDropdownList;
	}

	public WebElement getGoalframeworkHeaderTitle() {
		return goalframeworkHeaderTitle;
	}

	public WebElement getCSMetaDataArrowAndText() {
		return CSMetaDataArrowAndText;
	}

	public WebElement getUrnLevel() {
		return urnLevel;
	}

	public WebElement getUrnLevelVal() {
		return urnLevelVal;
	}

	public WebElement getTitleLevel() {
		return titleLevel;
	}

	public WebElement getTitleLevelVal() {
		return titleLevelVal;
	}

	public WebElement getDescriptionLevel() {
		return descriptionLevel;
	}

	public WebElement getDefinedByLevel() {
		return definedByLevel;
	}

	public WebElement getDefinedByLevelVal() {
		return definedByLevelVal;
	}

	public WebElement getSubjectLevel() {
		return subjectLevel;
	}

	public WebElement getSubjectLevelVal() {
		return subjectLevelVal;
	}

	public WebElement getCountryLevel() {
		return countryLevel;
	}

	public WebElement getCountryLevelVal() {
		return countryLevelVal;
	}

	public WebElement getIssueDateLevel() {
		return issueDateLevel;
	}

	public WebElement getSetsLevel() {
		return setsLevel;
	}

	public WebElement getSetsLevelVal() {
		return setsLevelVal;
	}

	public WebElement getStatusLevel() {
		return statusLevel;
	}

	public WebElement getStatusLevelVal() {
		return statusLevelVal;
	}

	public WebElement getFrameworkLevel() {
		return frameworkLevel;
	}

	public WebElement getFrameworkLevelVal() {
		return frameworkLevelVal;
	}

	public WebElement getLastUpdatedLevel() {
		return lastUpdatedLevel;
	}

	public WebElement getLastUpdatedLevelVal() {
		return lastUpdatedLevelVal;
	}

	public WebElement getIngestionTypeLevel() {
		return ingestionTypeLevel;
	}

	public WebElement getIngestionTypeLevelVal() {
		return ingestionTypeLevelVal;
	}

	public WebElement getSourceURLLevel() {
		return sourceURLLevel;
	}

	public WebElement getCurriculumInfoLevelVal() {
		return curriculumInfoLevelVal;
	}

	public WebElement getSourceURLLevelVal() {
		return sourceURLLevelVal;
	}

	public WebElement getCurriculumInfoLevel() {
		return curriculumInfoLevel;
	}

}
