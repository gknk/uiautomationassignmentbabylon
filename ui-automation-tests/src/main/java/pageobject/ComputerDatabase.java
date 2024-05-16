package pageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Helper;

/**
 * Page object class for Computer databse page.
 *
 * @author Gopal Krushna Nayak
 */
public class ComputerDatabase {

	WebDriver driver;
	private final String ADD_NEW_COMPUTER_BUTTON_ID = "add";
	private final String COMPUTER_NAME_ID = "name";
	private final String INTRODUCED_DATE_ID = "introduced";
	private final String DISCONTINUED_DATE_ID = "discontinued";
	private final String COMPANY_ID = "company";
	private final String CREATE_THIS_COMPUTER_BUTTON_XPATH = "//input[@type='submit']";
	private final String CANCEL_BUTTON_XPATH = "//a[text()='Cancel']";
	private final String SEARCHBOX_ID = "searchbox";
	private final String FILTER_BY_NAME_BUTTON_ID = "searchsubmit";
	private final String PREVIOUS_BUTTON_XPATH = "//a[contains(text(),'Previous')]";
	private final String NEXT_BUTTON_XPATH = "//a[contains(text(),'Next')]";
	private final String SAVE_COMPUTER_BUTTON_XPATH = "//input[@value='Save this computer']";
	private final String ERROR_CLASS_NAME = "error";
	private final String MAIN_ID = "main";
	private final String ALERT_MESSAGE_CLASS_NAME = "alert-message";
	private final String HEADING_MAIN_XPATH = "//section[@id='main']/h1";
	private final String NO_MATCH_MESSAGE_CLASS_NAME = "well";
	private final String DELETE_COMPUTER_BUTTON_XPATH = "//input[@value='Delete this computer']";
	private final String MSG_DISCONTINUED_BEFORE_INTRODUCED_XPATH = "//input[@id = 'discontinued']/following-sibling::span";
	private final String PREVIOUS_BUTTON_CLASS_NAME = "prev";
	private final String CURRENT_BUTTON_CLASS_NAME = "current";

	public ComputerDatabase(final WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Click on "Add a new computer" button.
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void clickAddNewComputer() {
		driver.findElement(By.id(ADD_NEW_COMPUTER_BUTTON_ID)).click();
	}

	/**
	 * Click on "Create this computer" button.
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void clickCreateComputerButton() {
		driver.findElement(By.xpath(CREATE_THIS_COMPUTER_BUTTON_XPATH)).click();
	}

	/**
	 * Click on "Cancel" button.
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void clickCancelButton() {
		driver.findElement(By.xpath(CANCEL_BUTTON_XPATH)).click();
	}

	/**
	 * Click on "Save this computer" button.
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void clickSaveComputerButton() {
		driver.findElement(By.xpath(SAVE_COMPUTER_BUTTON_XPATH)).click();
	}

	/**
	 * Fill new computer form. Enter computer name, Introduced date, Discontinued
	 * date and Company
	 * 
	 * @param computerName     A string containing computer name.
	 * @param IntroducedDate   A string containing Introduced date.
	 * @param discontinuedDate A string containing Discontinued date.
	 * @param company          A string containing company name.
	 * @return No return value.
	 */
	public void fillNewComputerForm(String computerName, String IntroducedDate, String discontinuedDate,
			String company) {
		String companyValue = "";
		if(!company.isEmpty())
			companyValue = Helper.getValueForCompany(company);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(COMPUTER_NAME_ID))));
		driver.findElement(By.id(COMPUTER_NAME_ID)).click();
		driver.findElement(By.id(COMPUTER_NAME_ID)).sendKeys(computerName);
		driver.findElement(By.id(INTRODUCED_DATE_ID)).sendKeys(IntroducedDate);
		driver.findElement(By.id(DISCONTINUED_DATE_ID)).sendKeys(discontinuedDate);
		Select companyName = new Select(driver.findElement(By.id(COMPANY_ID)));
		companyName.selectByValue(companyValue);
	}

	/**
	 * Add a new computer. Click on "Add a new computer" button. Fill the New
	 * computer form. Click on Create this computer button. Wait till the main page
	 * loads
	 * 
	 * @param computerName     A string containing computer name.
	 * @param IntroducedDate   A string containing Introduced date.
	 * @param discontinuedDate A string containing Discontinued date.
	 * @param company          A string containing company name.
	 * @return No return value.
	 */
	public void AddNewcomputer(String computerName, String IntroducedDate, String discontinuedDate, String company) {
		clickAddNewComputer();
		fillNewComputerForm(computerName, IntroducedDate, discontinuedDate, company);
		clickCreateComputerButton();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(MAIN_ID))));
	}

	/**
	 * Search a computer by name. Type a computer name on the "Filter by computer
	 * name..." text field. Click on "Filter by name" button
	 * 
	 * @param computerName A string containing the name of the computer.
	 * @return No return value.
	 */
	public void filterComputerByName(String computerName) {
		driver.findElement(By.id(SEARCHBOX_ID)).click();
		driver.findElement(By.id(SEARCHBOX_ID)).sendKeys(computerName);
		driver.findElement(By.id(FILTER_BY_NAME_BUTTON_ID)).click();
	}

	/**
	 * Navigate to next page. Click "Next ->" shown in pagination section.
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void navigateToNextPage() {
		driver.findElement(By.xpath(NEXT_BUTTON_XPATH)).click();
	}

	/**
	 * Navigate to previous page. Click "<- Previous" shown in pagination section.
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void navigateToPreviousPage() {
		driver.findElement(By.xpath(PREVIOUS_BUTTON_XPATH)).click();
	}

	/**
	 * Get total number of computers available in db. Get the text displayed in UI.
	 * Parse the string to get the total numbers.
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public int getTotalNoOfComputers() {
		String text = getHeaderText();
		String[] tokens = text.split(" ");
		return Integer.parseInt(tokens[0]);
	}

	/**
	 * Get all the texts displayed under a column. Identify the table. Computer name
	 * refers to 0th column, Introduced date refers to 1st column, Discontinued date
	 * refers to 2nd column and Company refers to 3rd column. If the field is empty,
	 * ignore it.
	 * 
	 * @param columnName A string containing name of the column.
	 * @return List<String> A list of strings containing the texts displayed under
	 *         the column name.
	 */
	public List<String> getColumnTextsFromGrid(String columnName) {
		List<String> columnTexts = new ArrayList<String>();
		WebElement table = driver.findElement(By.tagName("tbody"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			switch (columnName) {
			case "Computer name":
				columnTexts.add(cols.get(0).getText());
				break;
			case "Introduced":
				if (!cols.get(1).getText().equals("-"))
					columnTexts.add(cols.get(1).getText());
				break;
			case "Discontinued":
				if (!cols.get(2).getText().equals("-"))
					columnTexts.add(cols.get(2).getText());
				break;
			case "Company":
				if (!cols.get(3).getText().equals("-"))
					columnTexts.add(cols.get(3).getText());
				break;
			default:
				break;
			}
		}
		return columnTexts;
	}

	/**
	 * Check, whether the page contains an error message.
	 * 
	 * @param No parameter.
	 * @return true if error presents or false.
	 */
	public boolean isErrorPresent() {
		List<WebElement> error = driver.findElements(By.className(ERROR_CLASS_NAME));
		if (error.size() == 1)
			return true;
		else
			return false;
	}

	/**
	 * Get the alert message displayed in UI. This message gets displayed after
	 * successful creation, deletion and update of a Computer name
	 * 
	 * @param No parameter.
	 * @return String containing the alert message.
	 */
	public String getAlertMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(ALERT_MESSAGE_CLASS_NAME))));
		return driver.findElement(By.className(ALERT_MESSAGE_CLASS_NAME)).getText();
	}

	/**
	 * Get the text displayed in heading of main section. It represents how many
	 * computers available in db.
	 * 
	 * @param No parameter.
	 * @return String containing the number of computers found.
	 */
	public String getHeaderText() {
		String text = driver.findElement(By.xpath(HEADING_MAIN_XPATH)).getText();
		return text;
	}

	/**
	 * Get the text when no match(Computer name) found.
	 * 
	 * @param No parameter.
	 * @return String containing the message when no match found.
	 */
	public String getTextWhenNoMatch() {
		String text = driver.findElement(By.className(NO_MATCH_MESSAGE_CLASS_NAME)).getText();
		return text;
	}

	/**
	 * Click on the Computer name displayed on the grid. If the grid displays
	 * multiple rows, iterates through all the rows. Looks for exact match and then
	 * clicks on the computer name link.
	 * 
	 * @param computerName A string represents the name of the computer.
	 * @return No return value.
	 */
	public void clickComputerOnGrid(String computerName) {
		WebElement table = driver.findElement(By.tagName("tbody"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for (WebElement col : cols) {
				if (col.getText().equals(computerName)) {
					col.findElement(By.tagName("a")).click();
					break;
				}
			}
		}
	}

	/**
	 * Click on the Delete this computer button
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void clickDeleteComputerButton() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(DELETE_COMPUTER_BUTTON_XPATH))));
		driver.findElement(By.xpath(DELETE_COMPUTER_BUTTON_XPATH)).click();
	}

	/**
	 * Delete the computer. Search the computer by computer name. Click on the
	 * computer name Click on the Delete this computer button
	 * 
	 * @param computerName A string represents the name of a computer.
	 * @return No return value.
	 */
	public void deleteComputer(String computerName) {
		filterComputerByName(computerName);
		clickComputerOnGrid(computerName);
		clickDeleteComputerButton();
	}

	/**
	 * Edit a computer. Search the computer. Click on the computer name. Fill the
	 * Edit computer form. Click on Save this computer button. Wait till the main
	 * page loads
	 * 
	 * @param computerName        A string containing computer name.
	 * @param updatedComputerName A string containing updated computer name.
	 * @param IntroducedDate      A string containing Introduced date.
	 * @param discontinuedDate    A string containing Discontinued date.
	 * @param company             A string containing company name.
	 * @return No return value.
	 */
	public void editComputer(String computerName, String updatedComputerName, String introducedDate,
			String discontinuedDate, String company) {
		filterComputerByName(computerName);
		clickComputerOnGrid(computerName);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(COMPUTER_NAME_ID))));
		if (!updatedComputerName.isEmpty()) {
			WebElement computerNameField = driver.findElement(By.id(COMPUTER_NAME_ID));
			computerNameField.click();
			computerNameField.clear();
			computerNameField.sendKeys(updatedComputerName);
		}
		if (!introducedDate.isEmpty()) {
			WebElement introducedDateField = driver.findElement(By.id(INTRODUCED_DATE_ID));
			introducedDateField.click();
			introducedDateField.clear();
			introducedDateField.sendKeys(introducedDate);
		}
		if (!discontinuedDate.isEmpty()) {
			WebElement discontinuedDateField = driver.findElement(By.id(DISCONTINUED_DATE_ID));
			discontinuedDateField.click();
			discontinuedDateField.clear();
			discontinuedDateField.sendKeys(discontinuedDate);
		}
		if (!company.isEmpty()) {
			Select companyName = new Select(driver.findElement(By.id(COMPANY_ID)));
			companyName.selectByValue(Helper.getValueForCompany(company));
		}
		clickSaveComputerButton();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(MAIN_ID))));
	}

	/**
	 * Get the error message displayed in UI when discontinued date is before the
	 * introduced date.
	 * 
	 * @param No parameter.
	 * @return String containing the alert message.
	 */
	public String discontinueDtBeforeIntroducedDtAlertMsg() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath(MSG_DISCONTINUED_BEFORE_INTRODUCED_XPATH))));
		return driver.findElement(By.xpath(MSG_DISCONTINUED_BEFORE_INTRODUCED_XPATH)).getText();
	}

	/**
	 * Sort a column by clicking on the column header.
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void sortColumn(String columnName) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String xPath = String.format("//a[text() = '%s']", columnName);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xPath))));
		driver.findElement(By.xpath(xPath)).click();
	}

	/**
	 * Get text displayed on current button in pagination section.
	 * 
	 * @param No parameter.
	 * @return string containing the text displayed in pagination section.
	 */
	public String getTextFromPaginationSection() {
		return driver.findElement(By.className(CURRENT_BUTTON_CLASS_NAME)).getText();
	}

	/**
	 * Returns true if Previous button is enabled else returns false
	 * 
	 * @param No parameter.
	 * @return true if Previous button is enabled or false
	 */
	public boolean isPreviousEnabled() {
		String str = driver.findElement(By.className(PREVIOUS_BUTTON_CLASS_NAME)).getAttribute("class");
		if (!str.contains("disabled"))
			return true;
		else
			return false;
	}
}
