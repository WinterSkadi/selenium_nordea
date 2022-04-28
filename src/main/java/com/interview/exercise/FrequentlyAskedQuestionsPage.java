package com.interview.exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FrequentlyAskedQuestionsPage {
    private final WebDriver driver;
    @FindBy(id = "CookieReportsBanner")
    WebElement cookiesPopup;
    @FindBy(xpath = "//section[@class='faq-cards section row']")
    List<WebElement> faqSections;
    @FindBy(xpath = "//a[(@class='block-link ' or @class='faq-card__see-all ') and ancestor::div[@class = 'card']/h2[text() = 'Cards']]")
    List<WebElement> links;

    public FrequentlyAskedQuestionsPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void closeCookiesPopup(Duration waitTimeInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, waitTimeInSeconds);
        WebElement popup = webDriverWait.until(ExpectedConditions.visibilityOf(cookiesPopup));
        popup.findElement(By.xpath("//a[text() = 'Accept all cookies']")).click();
    }

    public int getCardsNumber(){
        List<WebElement> cards = getChildElements(faqSections, "//div[@class = 'card']");
        return cards.size();
    }

    public String getCardHeaderText(int index) {
        List<WebElement> headers = getChildElements(faqSections, "//div[@class = 'card']/h2");
        return headers.get(index).getText();
    }

    public int getCardsLinksNumber() {
        return links.size();
    }

    public String getLinkHref(int index) {
        List<String> linksHrefs = getAllElementsAttributeValues(links, "href");
        return linksHrefs.get(index);
    }

    private List<String> getAllElementsAttributeValues(List<WebElement> webElements, String attributeName) {
        List<String> attributeValues = new ArrayList<>();
        for (int i = 0; i < webElements.size(); i++) {
            WebElement webElement = webElements.get(i);
            String attributeValue = webElement.getAttribute(attributeName);
            if (attributeValue != null && !attributeValue.isEmpty()) {
                attributeValues.add(webElement.getAttribute(attributeName));
            }
        }
        return attributeValues;
    }

    private List<WebElement> getChildElements(List<WebElement> webElements, String xpath) {
        List<WebElement> elements = new ArrayList<>();
        for (int i = 0; i < webElements.size(); i++) {
            WebElement webElement = webElements.get(i);
            elements.addAll(webElement.findElements(By.xpath(xpath)));
        }
        return elements;
    }
}
