package tests;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import pageobject.ComputerDatabase;
import uicommon.UITestConfiguration;
import utility.Helper;

/**
 * Test class for Computer databse page.
 *
 * @author Gopal Krushna Nayak
 */
public class ComputerDatabaseTest extends UITestConfiguration {
	
	private static WebDriver driver;
	private static Properties property;
	private static String baseUrl;
	private final String COMPUTER_NAME_COLUMN = "Computer name";
	private final String COMPANY_COLUMN = "Company";
	private final String INTRODUCED_COLUMN = "Introduced";
	private final String DISCONTINUED_COLUMN = "Discontinued";
	private final String msgDiscontDtBeforeIntroDt = "Discontinued date is before introduction date";
	
	/**
	 * Below are the steps performed as part of BeforeClass annotation execution. 
	 * 1) Reading the property file 
	 * 2) Creating the driver instance based on the browser mentioned in property file 
	 * 3) Read the base URL 
	 */
	@BeforeAll
	public static void beforeClass() {
		UITestConfiguration uiTestConfiguration = new UITestConfiguration();
		property = uiTestConfiguration.readPropertyFile();
		driver = uiTestConfiguration.createDriver();
		baseUrl = property.getProperty("baseUrl");
	}

	@AfterAll
	public static void afterClass() {
		driver.quit();
	}
	
	/**
	 * Below are the steps performed as part of BeforeClass annotation execution. 
	 * 1) Open the base URL
	 * 2) Wait for the page to load 
	 */
	@BeforeEach
	public void beforeMethod() {
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("main"))));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on "Add a new computer" button which opens up Add a computer form
	 * 2) Fill only computer name field
	 * 3) Click on "Create this computer" button
	 * 4) Verify the success message in the UI
	 */
	@Test
	@Tag("smoke")
	@Tag("regression")
	public void addNewComputerWithRequiredFields() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		//int noOfComputersBeforeAdd = computerDatabase.getTotalNoOfComputers();
		String computerName = "TestComputer-"+UUID.randomUUID().toString();
		computerDatabase.AddNewcomputer(computerName,"","","");
		//assertEquals(computerDatabase.getTotalNoOfComputers(), noOfComputersBeforeAdd+1);
		//computerDatabase.filterComputerByName(computerName);
		//List<String> computerNames = computerDatabase.getComputerNameFromGrid();
		//assertTrue(computerNames.size() == 1);
		//assertTrue(computerNames.get(0).equals(computerName));
		String alertMessage = "Done ! Computer "+computerName+" has been created";
		assertTrue(alertMessage.equals(computerDatabase.getAlertMessage()));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on "Add a new computer" button which opens up Add a computer form
	 * 2) Fill all the fields
	 * 3) Click on "Create this computer" button
	 * 4) Verify the success message in the UI
	 */
	@Test	
	@Tag("regression")
	public void addNewComputerWithAllFields() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		String computerName = "TestComputer-"+UUID.randomUUID().toString();
		computerDatabase.AddNewcomputer(computerName,"2020-07-03","2021-07-03","IBM");
		String alertMessage = "Done ! Computer "+computerName+" has been created";
		assertTrue(alertMessage.equals(computerDatabase.getAlertMessage()));
	}	
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on "Add a new computer" button which opens up Add a computer form
	 * 2) Fill all the fields apart from Computer name field
	 * 3) Click on "Create this computer" button
	 * 4) Verify the error message in the UI
	 */
	@Test
	@Tag("regression")
	public void addNewComputerWithoutRequiredFields() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		int noOfComputersBeforeAdd = computerDatabase.getTotalNoOfComputers();
		computerDatabase.AddNewcomputer("","2020-07-03","2021-07-03","IBM");
		assertTrue(computerDatabase.isErrorPresent());		
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("main"))));
		assertEquals(computerDatabase.getTotalNoOfComputers(), noOfComputersBeforeAdd);
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on "Add a new computer" button which opens up Add a computer form
	 * 2) Fill the Computer name field with an existing computer name
	 * 3) Click on "Create this computer" button
	 * 4) Verify the success message in the UI
	 */
	@Test	
	@Tag("regression")
	public void addNewComputerWithExistingCompName() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		String computerName = "ACE";
		computerDatabase.AddNewcomputer(computerName,"","","");
		String alertMessage = "Done ! Computer "+computerName+" has been created";
		assertTrue(alertMessage.equals(computerDatabase.getAlertMessage()));
	}	
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on "Add a new computer" button which opens up Add a computer form
	 * 2) Fill all the fields
	 * 3) Click on "Cancel" button
	 * 4) Verify there is no changes in the no of computers
	 */
	@Test
	@Tag("regression")
	public void addNewComputerCancelFunctionality() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		int noOfComputersBeforeAdd = computerDatabase.getTotalNoOfComputers();
		String computerName = "TestComputer-"+UUID.randomUUID().toString();
		computerDatabase.clickAddNewComputer();
		computerDatabase.fillNewComputerForm(computerName,"2020-07-03","2021-07-03","IBM");
		computerDatabase.clickCancelButton();
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("main"))));
		assertEquals(computerDatabase.getTotalNoOfComputers(), noOfComputersBeforeAdd);
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Type the computer name on the search box
	 * 2) Click on the "Filter by name" button
	 * 3) Verify the computer name is displayed on the grid 
	 */
	@Test
	@Tag("smoke")
	@Tag("regression")
	public void filterComputerByName() {
		String computerName = "ASCI Red";
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.filterComputerByName(computerName);

		List<String> computerNames = computerDatabase.getColumnTextsFromGrid(COMPUTER_NAME_COLUMN);
		for(String name : computerNames) {
			assertTrue(name.contains(computerName));
		}
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Type the computer name which is not present in the db on the search box
	 * 2) Click on the "Filter by name" button
	 * 3) Verify the message displayed
	 */
	@Test
	@Tag("regression")
	public void filterComputerByInvalidName() {
		String computerName = "!@#$ERD";
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.filterComputerByName(computerName);		
		assertTrue(computerDatabase.getHeaderText().equals("No computer"));
		assertTrue(computerDatabase.getTextWhenNoMatch().equals("Nothing to display"));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Type the computer name on the search box
	 * 2) Click on the "Filter by name" button
	 * 3) Verify the computer name is displayed on the grid
	 */
	@Test
	@Tag("regression")
	public void filterComputerByNameCaseInsensitive() {
		String computerName = "arra";
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.filterComputerByName(computerName);
		List<String> computerNames = computerDatabase.getColumnTextsFromGrid(COMPUTER_NAME_COLUMN);
		for(String name : computerNames) {
			assertTrue(name.contains(computerName.toUpperCase()));
		}
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Type the partial computer name on the search box
	 * 2) Click on the "Filter by name" button
	 * 3) Verify the computer names are displayed on the grid
	 */
	@Test
	@Tag("regression")
	public void filterComputerByNamePartialMatch() {
		String computerName = "ASCI";
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.filterComputerByName(computerName);
		List<String> computerNames = computerDatabase.getColumnTextsFromGrid(COMPUTER_NAME_COLUMN);
		for(String name : computerNames) {
			assertTrue(name.contains(computerName.toUpperCase()));
		}
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Filter the computer name to be deleted
	 * 2) Click on the computer name displayed on the grid which opens up Edit computer form
	 * 3) Click on "Delete this computer" button
	 * 4) Verify the alert message which confirms the deletion
	 */
	@Test
	@Tag("smoke")
	@Tag("regression")
	public void deleteComputer() {
		String computerName = "ASCI Red";
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.deleteComputer(computerName);
		String alertMessage = "Done ! Computer "+computerName+" has been deleted";
		assertTrue(alertMessage.equals(computerDatabase.getAlertMessage()));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Filter the computer name to be edited
	 * 2) Click on the computer name displayed on the grid which opens up Edit computer form
	 * 3) Fill all the fields
	 * 4) Click on "Save this computer" button
	 * 5) Verify the alert message which confirms the update
	 */	
	@Test
	@Tag("smoke")
	@Tag("regression")
	public void editComputerByUpdatingAllFields() {
		String computerName = "ASCI Blue Mountain";	
		String updatedComputerName = "Updated-"+computerName;
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.editComputer(computerName,updatedComputerName,"2000-06-02","2022-07-03","Xerox");
		String alertMessage = "Done ! Computer "+updatedComputerName+" has been updated";
		assertTrue(alertMessage.equals(computerDatabase.getAlertMessage()));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Filter the computer name to be edited
	 * 2) Click on the computer name displayed on the grid which opens up Edit computer form
	 * 3) Fill only the computer name field
	 * 4) Click on "Save this computer" button
	 * 5) Verify the alert message which confirms the update
	 */
	@Test
	@Tag("regression")
	public void editComputerByUpdatingComputerName() {
		String computerName = "Apple IIc Plus";	
		String updatedComputerName = "Updated-"+computerName;
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.editComputer(computerName,updatedComputerName,"","","");
		String alertMessage = "Done ! Computer "+updatedComputerName+" has been updated";
		assertTrue(alertMessage.equals(computerDatabase.getAlertMessage()));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Filter the computer name to be edited
	 * 2) Click on the computer name displayed on the grid which opens up Edit computer form
	 * 3) Fill only the Introduced field
	 * 4) Click on "Save this computer" button
	 * 5) Verify the alert message which confirms the update
	 */
	@Test
	@Tag("regression")
	public void editComputerByUpdatingIntroducedDate() {
		String computerName = "ASCI Purple";		
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.editComputer(computerName,"","2000-06-02","","");
		String alertMessage = "Done ! Computer "+computerName+" has been updated";
		assertTrue(alertMessage.equals(computerDatabase.getAlertMessage()));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Filter the computer name to be edited
	 * 2) Click on the computer name displayed on the grid which opens up Edit computer form
	 * 3) Fill only the Discontinued field
	 * 4) Click on "Save this computer" button
	 * 5) Verify the alert message which confirms the update
	 */
	@Test
	@Tag("regression")
	public void editComputerByUpdatingDiscontinuedDate() {
		String computerName = "ASCI Purple";		
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.editComputer(computerName,"","","2022-06-02","");
		String alertMessage = "Done ! Computer "+computerName+" has been updated";
		assertTrue(alertMessage.equals(computerDatabase.getAlertMessage()));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Filter the computer name to be edited
	 * 2) Click on the computer name displayed on the grid which opens up Edit computer form
	 * 3) Fill only the Company field
	 * 4) Click on "Save this computer" button
	 * 5) Verify the alert message which confirms the update
	 */
	@Test
	@Tag("regression")
	public void editComputerByUpdatingCompany() {
		String computerName = "ASCI Purple";		
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.editComputer(computerName,"","","","Tandy Corporation");
		String alertMessage = "Done ! Computer "+computerName+" has been updated";
		assertTrue(alertMessage.equals(computerDatabase.getAlertMessage()));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Filter the computer name to be edited
	 * 2) Click on the computer name displayed on the grid which opens up Edit computer form
	 * 3) Fill all the fields apart from Computer name field
	 * 4) Click on "Save this computer" button
	 * 5) Verify the alert message which confirms the update
	 */
	@Test
	@Tag("regression")
	public void editComputerWithoutUpdatingComputerName() {
		String computerName = "ASCI Thors Hammer";		
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.editComputer(computerName,"","2019-06-02","2021-06-02","Tandy Corporation");
		String alertMessage = "Done ! Computer "+computerName+" has been updated";
		assertTrue(alertMessage.equals(computerDatabase.getAlertMessage()));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Create random string which will be our computer name
	 * 2) Click "Add a new computer" button which opens Add a computer form
	 * 3) Fill the Add a computer form. Here Discontinue date is before the Introduced date
	 * 4) Click on "Create this computer" button
	 * 5) Verify the alert message which says "Discontinued date is before introduction date"
	 */
	@Test
	@Tag("smoke")
	@Tag("regression")
	public void discontinueDateBeforeIntroducedDate() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		String computerName = "TestComputer-"+UUID.randomUUID().toString();
		computerDatabase.clickAddNewComputer();
		computerDatabase.fillNewComputerForm(computerName, "2020-06-02", "2019-06-02", "Tandy Corporation");
		computerDatabase.clickCreateComputerButton();
		assertTrue(computerDatabase.discontinueDtBeforeIntroducedDtAlertMsg().equals(msgDiscontDtBeforeIntroDt));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on the column header (Computer name) twice
	 * 2) Verify the dates displayed in Computer name column are in ascending order
	 */
	@Test
	@Tag("smoke")
	@Tag("regression")
	public void sortComputerNameColumnByAsc() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.sortColumn(COMPUTER_NAME_COLUMN);
		computerDatabase.sortColumn(COMPUTER_NAME_COLUMN);
		assertTrue(Helper.isTextSortedByAscending(computerDatabase.getColumnTextsFromGrid(COMPUTER_NAME_COLUMN)));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on the column header (Computer name) once
	 * 2) Verify the dates displayed in Computer name column are in descending order
	 */
	@Test
	@Tag("smoke")
	@Tag("regression")
	public void sortComputerNameColumnByDesc() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.sortColumn(COMPUTER_NAME_COLUMN);
		assertTrue(Helper.isTextSortedByDescending(computerDatabase.getColumnTextsFromGrid(COMPUTER_NAME_COLUMN)));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on the column header (Company) once
	 * 2) Verify the dates displayed in Company column are in ascending order
	 */
	@Test
	@Tag("regression")
	public void sortCompanyColumnByAsc() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.sortColumn(COMPANY_COLUMN);
		assertTrue(Helper.isTextSortedByAscending(computerDatabase.getColumnTextsFromGrid(COMPANY_COLUMN)));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on the column header (Company) twice
	 * 2) Verify the dates displayed in Company column are in descending order
	 */
	@Test
	@Tag("regression")
	public void sortCompanyColumnByDesc() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		computerDatabase.sortColumn(COMPANY_COLUMN);
		computerDatabase.sortColumn(COMPANY_COLUMN);
		assertTrue(Helper.isTextSortedByDescending(computerDatabase.getColumnTextsFromGrid(COMPANY_COLUMN)));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on the column header (Introduced) once
	 * 2) Verify the dates displayed in Introduced column are in ascending order
	 */
	@Test
	@Tag("regression")
	public void sortIntroducedColumnAsc() {	
			ComputerDatabase computerDatabase = new ComputerDatabase(driver);
			computerDatabase.sortColumn(INTRODUCED_COLUMN);
			assertTrue(Helper.isDateSortedByAscending(computerDatabase.getColumnTextsFromGrid(INTRODUCED_COLUMN)));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on the column header (Introduced) twice
	 * 2) Verify the dates displayed in Introduced column are in descending order
	 */
	@Test
	@Tag("regression")
	public void sortIntroducedColumnDesc() {	
			ComputerDatabase computerDatabase = new ComputerDatabase(driver);
			computerDatabase.sortColumn(INTRODUCED_COLUMN);
			computerDatabase.sortColumn(INTRODUCED_COLUMN);
			assertTrue(Helper.isDateSortedByDescending(computerDatabase.getColumnTextsFromGrid(INTRODUCED_COLUMN)));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on the column header (Discontinued) once
	 * 2) Verify the dates displayed in Discontinued column are in ascending order
	 */
	@Test
	@Tag("regression")
	public void sortDiscontinuedColumnAsc() {	
			ComputerDatabase computerDatabase = new ComputerDatabase(driver);
			computerDatabase.sortColumn(DISCONTINUED_COLUMN);
			assertTrue(Helper.isDateSortedByAscending(computerDatabase.getColumnTextsFromGrid(DISCONTINUED_COLUMN)));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Click on the column header (Discontinued) twice
	 * 2) Verify the dates displayed in Discontinued column are in descending order
	 */
	@Test
	@Tag("regression")
	public void sortDiscontinuedColumnDesc() {	
			ComputerDatabase computerDatabase = new ComputerDatabase(driver);
			computerDatabase.sortColumn(DISCONTINUED_COLUMN);
			computerDatabase.sortColumn(DISCONTINUED_COLUMN);
			assertTrue(Helper.isDateSortedByDescending(computerDatabase.getColumnTextsFromGrid(DISCONTINUED_COLUMN)));
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Get the total number of computers found
	 * 2) Get the text displayed on the current button
	 * 3) Generate the expected text to be displayed after clicking on the Next button
	 * 4) Click on the Next button 
	 * 5) Verify that the text displaying on current button is matching with the expected text
	 * 6) Verify Previous button is enabled
	 */
	@Test
	@Tag("smoke")
	@Tag("regression")
	public void navigateToNextPage() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		int noOfComputers = computerDatabase.getTotalNoOfComputers();
		String paginationText = computerDatabase.getTextFromPaginationSection();
		String tokens[] = paginationText.split(" ");
		String expectedMessage = "Displaying " + Integer.toString(Integer.parseInt(tokens[1])+10) + " to " + Integer.toString(Integer.parseInt(tokens[3])+10) + " of " +noOfComputers;
		computerDatabase.navigateToNextPage();		
		assertTrue(computerDatabase.getTextFromPaginationSection().equals(expectedMessage));
		assertTrue(computerDatabase.isPreviousEnabled());
	}
	
	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Get the total number of computers found
	 * 2) If Previous button is not enabled, click on Next button
	 * 3) Get the text displayed on the current button
	 * 4) Generate the expected text to be displayed after clicking on the Previous button
	 * 5) Click on the Previous button 
	 * 5) Verify that the text displaying on current button is matching with the expected text
	 */
	@Test
	@Tag("smoke")
	@Tag("regression")
	public void navigateToPreviousPage() {
		ComputerDatabase computerDatabase = new ComputerDatabase(driver);
		int noOfComputers = computerDatabase.getTotalNoOfComputers();
		if(!computerDatabase.isPreviousEnabled()) {
			computerDatabase.navigateToNextPage();
		}		
		String paginationText = computerDatabase.getTextFromPaginationSection();
		String tokens[] = paginationText.split(" ");
		String expectedMessage = "Displaying " + Integer.toString(Integer.parseInt(tokens[1])-10) + " to " + Integer.toString(Integer.parseInt(tokens[3])-10) + " of " +noOfComputers;
		computerDatabase.navigateToPreviousPage();
		assertTrue(computerDatabase.getTextFromPaginationSection().equals(expectedMessage));
	}
}
