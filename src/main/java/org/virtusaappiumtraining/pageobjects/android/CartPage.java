package org.virtusaappiumtraining.pageobjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.virtusaappiumtraining.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {
	public AndroidDriver driver;
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrice;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement cancelButton;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;
	
	public double getTotalProductPriceCalculated() {
		int products = productPrice.size();
		double TotalProductPrice = 0;
		for(int i=0;i<products;i++) {
			String price = productPrice.get(i).getText().substring(1);
			double pr = Double.parseDouble(price);
			TotalProductPrice = TotalProductPrice + pr;
		}
		return TotalProductPrice;
	}
	
	public double getTotalAmountFromCart() {
		String cartTotal = totalAmount.getText().substring(1);
		double totalCartAmount = Double.parseDouble(cartTotal);
		return totalCartAmount;
	}
	
	public void longPressTerms() {
		longPressAction(terms);
	}
	
	public void cancelTerms() {
		cancelButton.click();
	}
	
	public void tickCheckBox() {
		checkBox.click();
	}
	
	public void goToNextPage() {
		proceed.click();
	}
}
