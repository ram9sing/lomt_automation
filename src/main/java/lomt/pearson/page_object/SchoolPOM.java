package lomt.pearson.page_object;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SchoolPOM {

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
	
	@FindBy(xpath = "//div[@id='curriculum-tree']/ul/li[1]/a/span/div/span/span/span/span")
	private WebElement  gradeText1;
	
	@FindBy(xpath = "//div[@id='curriculum-tree']/ul/li[2]/a/span/div/span/span/span/span")
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

}
