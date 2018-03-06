package lomt.pearson.page_object;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductTocPOM {

	private WebDriver driver;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div[3]/span[3]/a")
	private WebElement intermediaryStructure;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[7]/button")
	private WebElement updateResultButton;
	
	@FindBy(xpath = "//*[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/button")
	private WebElement productInnerUpdateResultButton;
					 
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[6]/input")
	private WebElement enterSearchTerm;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div[1]/div/div[2]/div/span/span[1]")
	private WebElement actionLink;
	
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[3]/span")
	private WebElement export;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[5]/input")
	private WebElement tocHEenterSearchTerm;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[6]/button")
	private WebElement tocUpdateResultButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div/span/span[1]")
	private WebElement tocActionLink;
	
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[3]/span")
	private WebElement tocExport;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[4]/input")
	private WebElement tocEnglishEenterSearchTerm;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[5]/button")
	private WebElement tocEnglishUpdateResultButton;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div/span/span[1]")
	private WebElement tocEnglishActionLink;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[1]/input")
	private WebElement innerEnterSearchTerm;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/input")
	private WebElement innerEnterSearchTermEnglishTOC;
	
	@FindBy(xpath = "//div[@id='browse-action-container']/div[2]/div[3]/span")
	private WebElement tocEnglishExport;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[2]/div[2]/div[1]/div/div[1]/div/span[2]/span[2]/a")
	private WebElement tocFirstGF;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/button")
	private WebElement tocInnerUpdateBtn;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[3]/button")
	private WebElement tocInnerUpdateBtnEnglish;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[1]/input")
	private WebElement innerEnterSearchTermSchoolTOC;
	
	@FindBy(xpath = "//div[@id='lomtAppId']/div/div/div[2]/div/div[1]/div[2]/button")
	private WebElement tocInnerUpdateBtnSchool;
	
	public static List<String> getTOCList() {
		List<String> tocList = new ArrayList<String>();
		tocList.add("US DNealian 2008 Student Edition, Grade K");
		tocList.add("Tracing and writing n ");
		tocList.add("Print awareness_8");
		tocList.add("Tracing and writing Y");
		tocList.add("Strokes for writing 4 and 5");
		
		return tocList;
	}
	
	public ProductTocPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getIntermediaryStructure() {
		return intermediaryStructure;
	}

	public WebElement getUpdateResultButton() {
		return updateResultButton;
	}
	
	public WebElement getProductInnerUpdateResultButton() {
		return productInnerUpdateResultButton;
	}

	public WebElement getEnterSearchTerm() {
		return enterSearchTerm;
	}
	
	public WebElement getInnerEnterSearchTerm() {
		return innerEnterSearchTerm;
	}

	public WebElement getExport() {
		return export;
	}

	public WebElement getActionLink() {
		return actionLink;
	}

	public WebElement getTocHEenterSearchTerm() {
		return tocHEenterSearchTerm;
	}

	public WebElement getTocUpdateResultButton() {
		return tocUpdateResultButton;
	}

	public WebElement getTocActionLink() {
		return tocActionLink;
	}

	public WebElement getTocExport() {
		return tocExport;
	}

	public WebElement getTocEnglishEenterSearchTerm() {
		return tocEnglishEenterSearchTerm;
	}

	public WebElement getTocEnglishUpdateResultButton() {
		return tocEnglishUpdateResultButton;
	}

	public WebElement getTocEnglishActionLink() {
		return tocEnglishActionLink;
	}

	public WebElement getTocEnglishExport() {
		return tocEnglishExport;
	}

	public WebElement getTocFirstGF() {
		return tocFirstGF;
	}

	public WebElement getTocInnerUpdateBtn() {
		return tocInnerUpdateBtn;
	}

	public WebElement getInnerEnterSearchTermEnglishTOC() {
		return innerEnterSearchTermEnglishTOC;
	}

	public WebElement getTocInnerUpdateBtnEnglish() {
		return tocInnerUpdateBtnEnglish;
	}

	public WebElement getInnerEnterSearchTermSchoolTOC() {
		return innerEnterSearchTermSchoolTOC;
	}

	public WebElement getTocInnerUpdateBtnSchool() {
		return tocInnerUpdateBtnSchool;
	}

}
