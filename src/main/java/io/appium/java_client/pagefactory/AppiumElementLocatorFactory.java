package io.appium.java_client.pagefactory;

import java.lang.reflect.Field;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import static io.appium.java_client.pagefactory.AppiumElementLocator.getAutomation;

class AppiumElementLocatorFactory implements CustomElementLocatorFactory {
    private final SearchContext searchContext;
	private final TimeOutDuration timeOutDuration;
	private final String automation;

	public AppiumElementLocatorFactory(SearchContext searchContext,
			TimeOutDuration timeOutDuration) {
		this.searchContext = searchContext;
		this.timeOutDuration = timeOutDuration;
		this.automation = searchContext.getClass().isAssignableFrom(WebDriver.class) ?
				getAutomation(searchContext) : null;
	}
	
	public AppiumElementLocatorFactory(SearchContext searchContext) {
		this(searchContext, new TimeOutDuration(AppiumFieldDecorator.DEFAULT_IMPLICITLY_WAIT_TIMEOUT,
				AppiumFieldDecorator.DEFAULT_TIMEUNIT));
	}	

	public ElementLocator createLocator(Field field) {
		return new AppiumElementLocator(searchContext, field, timeOutDuration, automation);
	}

	public ElementLocator createLocator(Class clazz) {
		return new AppiumElementLocator(searchContext, clazz, timeOutDuration, automation);
	}
}