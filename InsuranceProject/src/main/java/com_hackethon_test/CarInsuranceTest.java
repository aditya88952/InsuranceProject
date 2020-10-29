package com_hackethon_test;

import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com_hackethon_base.BaseUI;
import com_hackethon_utils.TestDataProvider;

public class CarInsuranceTest extends BaseUI {

	@Test(dataProvider = "getTestOneData")
	public void testTwo(Hashtable<String, String> dataTable) {

		logger = report.createTest("Car Insurance");

		invokeproperties();
		invokeBrowser("Chrome");
		openURL("websiteURL");
		// Hower the mouse to Motor Insurance and select Car Insurance
		waitLoad(1);
		howerMouse("motor_Insurance_xpath");
		implicitWait(50);
		howerAndClick("car_insurance_xpath");
		implicitWait(50);
		// Fill the Car details without Car Number
		clickElement("without_car_number_xpath");
		implicitWait(50);
		waitLoad(1);
		enterText("city_plate_xpath", dataTable.get("Col1"));
		implicitWait(10);
		waitLoad(1);
		pressKeys("city_plate_xpath", "Down");
		implicitWait(10);
		waitLoad(1);
		pressKeys("city_plate_xpath", "Enter");
		implicitWait(50);
		waitLoad(1);
		clickElement("car_brand_xpath");
		implicitWait(50);
		waitLoad(1);
		clickElement("car_Model_xpath");
		implicitWait(50);
		waitLoad(1);
		clickElement("fuel_type_xpath");
		implicitWait(50);
		waitLoad(1);
		clickElement("car_variant_xpath");
		implicitWait(50);
		waitLoad(1);
		clickElement("car_year_xpath");
		implicitWait(50);
		waitLoad(1);
		// Fill the Owner Details
		enterText("car_owner_xpath", dataTable.get("Col2"));
		enterText("owner_email_xpath", dataTable.get("Col3"));
		enterText("owner_mobile_xpath",dataTable.get("Col4") );
		clickElement("view_price_xpath");
	
		validationError("error_message_xpath", "error_message1_xpath");
		implicitWait(50);
		waitLoad(1);
		
		quitBrowser();

	}

	@DataProvider
	public Object[][] getTestOneData() {
		return TestDataProvider.getTestData("Testdata.xlsx", "Feature1", "TestTwo");
	}

	
	
	@AfterTest
	public void endReport() {
		report.flush();
	}

}
