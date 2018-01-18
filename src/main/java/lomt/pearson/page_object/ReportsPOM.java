package lomt.pearson.page_object;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportsPOM {
	
	private WebDriver driver;
	
	//Admin
	@FindBy(xpath = "//div[@id='browse-grids']/div[1]/div[2]/span[1]")
	private WebElement reportsExportLink;
		
	@FindBy(xpath = "//div[@id='browse-grids']/div[1]/div/span[1]")
	//@FindBy(xpath = "//*[@id='browse-grids']/div[1]/div[2]/span[1]")
	private WebElement reportsExportLinkNonAdmin;
	
	@FindBy(xpath = "//div[@class='fixed-header-container']/div/div[2]/div/span[1]")
	private WebElement repotCountText;
	
	@FindBy(xpath = "//div[@class='list-data-container']")
	private List<WebElement> reportList;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[1]/div[3]/input")
	private WebElement enterSearchTerm;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[1]/div[5]/button")
	private WebElement updateResult;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/span/span[1]")
	private WebElement reportActionLink;
	
	@FindBy(xpath = "//div[@id='reports']/div[2]/div[1]/span")
	private WebElement reportExportButton;
	
	@FindBy(xpath = "//div[@class='list-data-container']/div")
	private List<WebElement> curriculumStandardList;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[3]/div/div")
	private WebElement curriculumLoadMoreButton;
	
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[4]/span")
	private WebElement schoolCreateReportLink;
	
	@FindBy(xpath = "//div[@class='modal-footer']/button")
	private WebElement schoolModelWindowNextButton;
	
	@FindBy(xpath = "//div[@class='report-utility-container']/div[6]/div[1]")
	private WebElement forwardIndirectIntermediaryReport;
	
	@FindBy(xpath = "//*[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[1]/div[1]/span")
	private WebElement reverseToCStandardintermediaryReport;
	
	@FindBy(xpath = "//*[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div[1]/span")
	private WebElement productToCIntermediaryReport;
	
	@FindBy(xpath = "//*[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[8]/div[1]/span")
	private WebElement reverseSharedIntermediaryReport;
	
	@FindBy(xpath = "//*[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[3]/div[1]/span")
	private WebElement standardToCIntermediaryReport;
	
	@FindBy(xpath = "//*[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[4]/div[1]/span")
	private WebElement forwardDirectReport;
	
	@FindBy(xpath = "//*[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[6]/div[1]/span")
	private WebElement reverseDirectReport;
	
	@FindBy(xpath = "//div[@id='report-target-container']/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div/span[1]")
	private WebElement firstIntermediaryPivot;
	
	@FindBy(xpath = "//div[@id='report-target-container']/div[2]/div/div[2]/div[2]/div/div/div[2]/div/span[1]")
	private WebElement selectTarget;
	
	@FindBy(xpath = "//input[@id='_reportName']")
	private WebElement reportName;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[2]/button")
	private WebElement runReport;
	
	public ReportsPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getReportsExportLink() {
		return reportsExportLink;
	}

	public WebElement getRepotCountText() {
		return repotCountText;
	}

	public List<WebElement> getReportList() {
		return reportList;
	}

	public WebElement getEnterSearchTerm() {
		return enterSearchTerm;
	}

	public WebElement getUpdateResult() {
		return updateResult;
	}

	public WebElement getReportActionLink() {
		return reportActionLink;
	}

	public WebElement getReportExportButton() {
		return reportExportButton;
	}

	public List<WebElement> getCurriculumStandardList() {
		return curriculumStandardList;
	}

	public WebElement getCurriculumLoadMoreButton() {
		return curriculumLoadMoreButton;
	}

	public WebElement getSchoolCreateReportLink() {
		return schoolCreateReportLink;
	}

	public WebElement getSchoolModelWindowNextButton() {
		return schoolModelWindowNextButton;
	}

	public WebElement getForwardIndirectIntermediaryReport() {
		return forwardIndirectIntermediaryReport;
	}

	public WebElement getReverseToCStandardintermediaryReport() {
		return reverseToCStandardintermediaryReport;
	}

	public WebElement getProductToCIntermediaryReport() {
		return productToCIntermediaryReport;
	}

	public WebElement getReverseSharedIntermediaryReport() {
		return reverseSharedIntermediaryReport;
	}
	
	public WebElement getStandardToCIntermediaryReport() {
		return standardToCIntermediaryReport;
	}

	public WebElement getForwardDirectReport() {
		return forwardDirectReport;
	}

	public WebElement getReverseDirectReport() {
		return reverseDirectReport;
	}

	public WebElement getFirstIntermediaryPivot() {
		return firstIntermediaryPivot;
	}
	
	public WebElement getSelectTarget() {
		return selectTarget;
	}
	
	public WebElement getReportName() {
		return reportName;
	}

	public WebElement getRunReport() {
		return runReport;
	}
	
	public WebElement getReportsExportLinkNonAdmin() {
		return reportsExportLinkNonAdmin;
	}

}
