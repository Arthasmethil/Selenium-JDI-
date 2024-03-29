package com.epam.tc.hw2.ex1;

import com.epam.tc.hw2.constants.ExpectedValuesForTests;
import com.epam.tc.hw2.constants.User;
import com.epam.tc.hw2.steps.LogInSteps;
import com.epam.tc.hw2.steps.SeleniumAbstractCore;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ExerciseSoftAssertsTest extends SeleniumAbstractCore {

    @Test
    public void checkAndSearchElementsOnPageWithSoftAsserts() {
        SoftAssertions softly = new SoftAssertions();
        // 1. Open test site by URL
        driver.get(ExpectedValuesForTests.SITE_ADDRESS);
        // 2. Assert Browser title
        softly.assertThat(driver.getTitle()).isEqualTo(ExpectedValuesForTests.EXPECTED_TITLE);
        // 3. User is logged
        LogInSteps.signIn(driver, wait, User.USERNAME, User.PASS);
        // 4. Name is displayed and equals to expected result
        softly.assertThat(driver.findElement(By.id("user-name")))
              .matches(WebElement::isDisplayed)
              .extracting(WebElement::getText)
              .isEqualTo(User.EXPECTED_DISPLAYED_NAME);
        // 5. Assert that there are 4 items on the header section are displayed and they have proper texts
        List<WebElement> headerMenu = driver.findElements(
            By.cssSelector("ul.uui-navigation.nav.navbar-nav.m-l8 > li"));
        softly.assertThat(headerMenu)
              .hasSize(ExpectedValuesForTests.EXPECTED_TEXT_HEADER_MENU.size())
              .allMatch(WebElement::isDisplayed, "WebElement should be display")
              .extracting(WebElement::getText)
              .containsExactlyElementsOf(ExpectedValuesForTests.EXPECTED_TEXT_HEADER_MENU);
        // 6. Assert that there are 4 images on the Index Page and they are displayed
        List<WebElement> benefitIcons = driver.findElements(By.cssSelector(".benefit-icon"));
        softly.assertThat(benefitIcons)
              .hasSize(ExpectedValuesForTests.EXPECTED_ICON_TEXTS.size())
              .allMatch(WebElement::isDisplayed, "WebElement should be display");
        // 7. Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> benefitText = driver.findElements(By.cssSelector(".benefit-txt"));
        softly.assertThat(benefitText)
              .hasSize(ExpectedValuesForTests.EXPECTED_ICON_TEXTS.size())
              .allMatch(WebElement::isDisplayed, "WebElement should be display")
              .extracting(WebElement::getText)
              .containsExactlyElementsOf(ExpectedValuesForTests.EXPECTED_ICON_TEXTS);
        // 8. Assert that there is the iframe with “Frame Button” exist
        softly.assertThat(driver.findElements(By.id("frame")))
              .hasAtLeastOneElementOfType(WebElement.class)
              .allMatch(WebElement::isDisplayed);
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        driver.switchTo().frame("frame");
        softly.assertThat(driver.findElements(By.id("frame-button")))
              .hasAtLeastOneElementOfType(WebElement.class)
              .allMatch(WebElement::isDisplayed);
        // 10. Switch to original window back
        driver.switchTo().defaultContent();
        // 11. Assert that there are 5 items in the Left Section are displayed and they have proper text
        List<WebElement> homeLeftBar = driver.findElements(
            By.cssSelector("#mCSB_1_container > ul > *"));
        softly.assertThat(homeLeftBar)
              .hasSize(ExpectedValuesForTests.EXPECTED_TEXT_LEFT_MENU.size())
              .allMatch(WebElement::isDisplayed, "WebElement should be display")
              .extracting(WebElement::getText)
              .containsExactlyElementsOf(ExpectedValuesForTests.EXPECTED_TEXT_LEFT_MENU);
        // 12. Assert all
        softly.assertAll();
        // 12.1 Browser is closed by tearDown method, which is located into utils.SeleniumCoreTest
    }
}
