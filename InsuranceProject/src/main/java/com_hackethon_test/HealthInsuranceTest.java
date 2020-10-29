package com_hackethon_test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com_hackethon_base.BaseUI;

public class HealthInsuranceTest extends BaseUI {

	@Test()
	public void testThree() {
		logger = report.createTest("Health Insurance");
		invokeproperties();
		invokeBrowser("firefox"); // Opening the browser
		openURL("websiteURL");
		waitLoad(1);
		howerMouse("health_Insurance_xpath");
		waitLoad(3);
		SelectSubMenuFromMainMenu("myElements_xpath");
		quitBrowser();
	}

	@AfterTest
	public void endReport() {
		report.flush();
	}

}
