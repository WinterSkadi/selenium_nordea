package tests;

import com.interview.exercise.FrequentlyAskedQuestionsPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFAQCards {
    private static final String driverPath = "src\\test\\java\\resources\\chromedriver.exe";
    private static final String pageURL = "https://www.nordea.fi/en/personal/get-help/";
    private WebDriver driver;
    private FrequentlyAskedQuestionsPage frequentlyAskedQuestionsPage;

    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        frequentlyAskedQuestionsPage = new FrequentlyAskedQuestionsPage(driver);
    }

    @Test
    public void testFAQCards() {
        driver.manage().window().maximize();
        driver.get(pageURL);

        frequentlyAskedQuestionsPage.closeCookiesPopup(Duration.ofSeconds(10));

        int actualNumberOfCards = frequentlyAskedQuestionsPage.getCardsNumber();
        Assertions.assertTrue(actualNumberOfCards > 0, "Number of Cards should be greater than 0.");

        String actualHeaderText = frequentlyAskedQuestionsPage.getCardHeaderText(2);
        System.out.println("Card header text: " + actualHeaderText);
        Assertions.assertEquals("Cards", actualHeaderText, "Header text should be 'Card'");

        int actualCardsLinksNumber = frequentlyAskedQuestionsPage.getCardsLinksNumber();
        System.out.println("Number of links presented on card: " + actualCardsLinksNumber);
        Assertions.assertEquals(4, actualCardsLinksNumber, "Number of links on card should be 4.");

        String secondLinkHref = frequentlyAskedQuestionsPage.getLinkHref(1);
        System.out.println(secondLinkHref);
    }

    @AfterAll
    public void cleanUp() {
        driver.quit();
    }
}