package com_hackethon_test;

import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com_hackethon_base.BaseUI;
import com_hackethon_utils.TestDataProvider;

public class InsuranceTest extends BaseUI {

	@Test(dataProvider = "getTestOneData")

	public void testOne(Hashtable<String, String> dataTable) {

		logger = report.createTest("Travel Insurance");

		invokeproperties();
		invokeBrowser("opera");
		openURL("websiteURL");
		howerMouse("other_Insurance_LinkText"); // Hower the mouse to "Other //
												// Insurance Tab"
		howerAndClick("travel_insurance_xpath"); // Hower and Click the "Travel
													// // Insurance Tab
		// Fill the form 1
		clickElement("student_xpath");
		enterText("destination_Text_Box_xpath", dataTable.get("Col1"));
		pressKeys("destination_Text_Box_xpath", "Enter");
		enterText("student_one_cssSelector", dataTable.get("Col2"));
		enterText("student_two_cssSelector", dataTable.get("Col3"));
		setCalenderDate("trip_start_date_xpath", "trip_end_date_xpath");
		clickElement("proceed_xpath");
		// Fill the form 2
		clickElement("salutation_cssSelector");
		SelectElementInList("salutation_cssSelector", dataTable.get("Col4"));
		enterText("name_text_box_cssSelector", dataTable.get("Col5"));
		enterText("phone_text_box_cssSelector", dataTable.get("Col6"));
		enterText("email_text_box_cssSelector", dataTable.get("Col7"));
		clickElement("quotes_xpath");
		// Finding details of the insurance company along with the price.
		waiting("preference_xpath");
		SelectElementInList("preference_xpath", dataTable.get("Col8"));
		getAttribute("insurance_one_xpath");
		getText("price_one_xpath");
		getAttribute("insurance_two_xpath");
		getText("price_two_xpath");
		getAttribute("insurance_three_xpath");
		getText("price_three_xpath");
		// Navigate to the landing page
		navigateTohome();
		waitLoad(1);
		quitBrowser();

	}

	@DataProvider
	public Object[][] getTestOneData() {
		return TestDataProvider.getTestData("Testdata.xlsx", "Feature1", "TestOne");
	}

	@AfterTest
	public void endReport() {
		report.flush();
	}

}
