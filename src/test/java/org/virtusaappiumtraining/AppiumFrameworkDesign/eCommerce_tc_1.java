package org.virtusaappiumtraining.AppiumFrameworkDesign;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.virtusaappiumtraining.TestUtils.BaseTest;

import io.appium.java_client.android.Activity;

public class eCommerce_tc_1 extends BaseTest {
	
//	@BeforeMethod
//	public void InitialSetUp() throws InterruptedException {
//		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
//		driver.startActivity(activity);
//	}
	@Test
	public void FormSubmitionErrorValidation() {
		formPage.setGender("female");
		formPage.setCountry("Australia");
		formPage.submitForm();
		String errorMessage = formPage.getErrorMessage();
		Assert.assertEquals(errorMessage, "Please enter your name");	
		
	}
	
	@Test(dataProvider="getData")
	public void PositiveFormSubmition(String name,String gender,String country) {
		formPage.setNameField(name);
		formPage.setGender(gender);
		formPage.setCountry(country);
		formPage.submitForm();
		Assert.assertTrue(driver.findElements(By.xpath("//android.widget.Toast[1]")).size()<1);	
		
	}
	
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {{"Soumya","female","Australia"}};
	}
}
