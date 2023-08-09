package org.virtusaappiumtraining.AppiumFrameworkDesign;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.virtusaappiumtraining.TestUtils.BaseTest;
import org.virtusaappiumtraining.pageobjects.android.CartPage;
import org.virtusaappiumtraining.pageobjects.android.FormPage;
import org.virtusaappiumtraining.pageobjects.android.ProductCatalogue;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_tc_4 extends BaseTest {
	@Test
	public void FillForm() throws InterruptedException {
		formPage.setNameField("Anakha");
		formPage.setGender("female");
		formPage.setCountry("Australia");
		ProductCatalogue productcatalogue = formPage.submitForm();
		productcatalogue.addItemToCartByIndex(0);
		productcatalogue.addItemToCartByIndex(0);
		CartPage cartpage = productcatalogue.goToCartPage();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		double TotalProductPriceCalculated = cartpage.getTotalProductPriceCalculated();
		double TotalAmountFromCart = cartpage.getTotalAmountFromCart();
		Assert.assertEquals(TotalProductPriceCalculated, TotalAmountFromCart);
		cartpage.longPressTerms();
		cartpage.cancelTerms();
		cartpage.tickCheckBox();
		cartpage.goToNextPage();
		Thread.sleep(9000);
	}
}
