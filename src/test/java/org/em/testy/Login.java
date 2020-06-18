package org.em.testy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login extends TestBase{
    @Test
    // Logowanie poprawne username i hasło
    //Sprawdza czy jest komunikat błędu
    //Sprawdza, czy url się nie zmienił ???
    public void LoginValidAll () {
        this.registerUserNew();
        getLogOut();
        getLogIn();
        login(this.currentRegisteredUserEmail, this.currentRegisteredUserPassword);
        var wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.urlToBe("https://stage.samplepage.pl/"));
    }


    @Test
    //Logowanie:niepoprawny username, poprawne hasło
    public void LoginInvailidUser () {
        registerUserNew();
        getLogOut();
        getLogIn();
        this.generateEmail();
        login(this.currentRegisteredUserEmail, this.currentRegisteredUserPassword);

        String expectedErrorMsgLogin = "Logowanie nie powiodło się. Popraw błędy i spróbuj ponownie.\n" +
                "Nie znaleziono konta";
        WebElement expLogin = driver.findElement(By.cssSelector(".validation-summary-errors"));
        String actualErrorMsgLogin = expLogin.getText();
        Assertions.assertEquals(actualErrorMsgLogin, expectedErrorMsgLogin);
    }

    @Test
    //Logowanie: poprawny username, niepoprawne hasło
    public void LoginInvalidPassword () {
    this.registerUserNew();
    getLogOut();
    getLogIn();
    login(this.currentRegisteredUserEmail, this.currentRegisteredUserPassword+"1");
    String expectedErrorMsgLogin = "Logowanie nie powiodło się. Popraw błędy i spróbuj ponownie.\n" +
                "Podane dane są nieprawidłowe";
    WebElement expLogin = driver.findElement(By.cssSelector(".validation-summary-errors"));
    String actualErrorMsgLogin = expLogin.getText();
    Assertions.assertEquals(actualErrorMsgLogin, expectedErrorMsgLogin);
    }

    @Test
    public void passwordRecovery () {

    }

    //Logowanie przy pomocy SSO - facebook
    //Strona logowania
    //Wciśnij przycisk "Sign in with facebook"
    //Czekaj aż strona się przeładuje a url będzie zawierał frazę "facebook"
    @Test
    public void facebookLogin () {
        openPage("https://stage.samplepage.pl/login");
        findAndClick(".facebook-btn");
        var wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlContains("facebook"));
    }


    //Logowanie przy pomocy SSO - google
    //Strona logowania
    //Wciśnij przycisk "Sign in with google"
    //Czekaj aż strona się przeładuje a url będzie zawierał frazę "google"
    @Test
    public void googleLogin() {
        openPage("https://stage.samplepage.pl/login");
        findAndClick(".google-btn");
        var wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlContains("google"));
    }
}
