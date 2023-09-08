package com.links;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.base.Base;
import com.objectrespo.AppObjectRespo;

@Listeners(com.listener.TestNgListener.class)
public class AllLinks extends Base {

	@Test
	public static void getAllLinks() {
		test = extent.createTest("Getting All thelinks");

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));

		for (WebElement link : allLinks) {

			String linkText = link.getText();
			String linkUrl = link.getAttribute("href");

			if (linkUrl == null || linkUrl.isEmpty()) {
				test.log(Status.INFO,
						MarkupHelper.createLabel(
								linkText + " : " + linkUrl
										+ " :URL is either not configured for anchor tag or it is empty",
								ExtentColor.BROWN));
				continue;
			}
			if (!(linkUrl.startsWith(AppObjectRespo.baseUrl))) {
				test.log(Status.INFO,
						MarkupHelper.createLabel(
								linkText + " : " + linkUrl
										+ " :URL does not belong to the same/base domain",
								ExtentColor.ORANGE));
				continue;
			}

			test.log(Status.PASS, linkText + " : " + linkUrl);
		}
	}
}
