package org.em.testy;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Universal {
    public WebDriver driver;

    @BeforeEach
    public void initiate() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void openPage(String adres) {
        driver.get(adres);
    }

    @AfterEach
    public void closePage() {
        driver.close();
        driver.quit();
    }

    //Znajdź po css selectorze i kliknij
    //Czekaj aż element będzię klikalny
    //Daj komunikat błędu jeśli nie będzie spełniony warunek wyżej
    public void findAndClick(String cssSelector) {

        try {
            var wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
        } catch (Exception e) {
            throw new RuntimeException("Element jest niewidoczny lub nieklikalny");
        }
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void findAndClickByXpath(String xpath) {

        try {
            var wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception e) {
            throw new RuntimeException("Element jest niewidoczny lub nieklikalny");
        }
        driver.findElement(By.xpath(xpath)).click();
    }


}
