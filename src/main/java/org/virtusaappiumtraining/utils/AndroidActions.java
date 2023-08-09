package org.virtusaappiumtraining.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {
	public AndroidDriver driver;
	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
	}
	//longpressgesture
		public void longPressAction(WebElement element) {
			//RemoteWebElement - webelement that will be supported by any browser, app, etc(eg : web browser, mobilea app ...)
			((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
				    "elementId", ((RemoteWebElement) element).getId(),
				    "duration",2000
				));
		}
		
		//Scrolling till the end
		public void scrollTillEnd() {
			boolean canScrollMore;
			do {
				canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					   "left", 100, "top", 100, "width", 200, "height", 200,
					   "direction", "down",
					   "percent", 3.0
					));
			}while(canScrollMore);
		}
		
		//Scroll into a text
		public void scrollToText(String text) {
			driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
		}
		
		//swipe
		public void swipeGestureAction(WebElement element, String direction) {
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				    "elementId",((RemoteWebElement) element).getId(),
				    "direction", direction,
				    "percent", 0.75
				));
		}
}
