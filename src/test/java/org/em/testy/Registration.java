package org.em.testy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Registration extends TestBase   {



    //Rejestracja
    //Logout
    //Rejestracja
    //Próba rejestracji przy użyciu adresu email już zarejestrowanego użytkownika
    //Komunikat "Podany e-mail już istnieje."
    @Test
    public void allreadyRegistered (){
        this.registerUserNew();
        getLogOut();
        getRegister();
        registrationForm("Em", "C", this.currentRegisteredUserEmail,
                "djoadjoa 15/15", "10-431", "Wawa",
                "5e5fda3c239effb96cff1ec3", "668522459", this.currentRegisteredUserPassword, this.currentRegisteredUserPassword);
        clickRegisterButton();
        WebElement actualErrorMsgRegistered  = driver.findElement(By.cssSelector(".validation-summary-errors"));
        String expectedErrorMsgAllreadyRegistered = "Podany e-mail już istnieje.";
        String actualErrorMsgAllreadyRegistered  = actualErrorMsgRegistered.getText();
        Assertions.assertEquals(expectedErrorMsgAllreadyRegistered , actualErrorMsgAllreadyRegistered );
    }


    //Zostawe puste i sprawdź komunikaty błędu dla każdego z wymaganych pól
    // imię, nazwisko, email, adres, kod, miasto, kraj, hasło1, powtórz hasło
    //Sprawdź, czy nadal jesteś na stronie rejestracji
    @Test
    public void mandatoryFieldsEmptyRegistration () {
        openPage("https://stage.samplepage.pl/register");
        registrationForm("", "", "",
                "", "", "",
                "", "", "", "");
        clickRegisterButton();

        String URL = driver.getCurrentUrl();
        Assertions.assertEquals(URL, "https://stage.samplepage.pl/register");

        String expErrorMsgEmptyFirstName = "Imię jest wymagane.";
        List<WebElement> errorMsg= driver.findElements(By.cssSelector(".field-validation-error"));
        var actualErrorMsgFirst = errorMsg.get(0);
        String actualErrorMsgFirstName = actualErrorMsgFirst.getText();
        Assertions.assertEquals(expErrorMsgEmptyFirstName, actualErrorMsgFirstName);

        String expErrorMsgEmptyLastName = "Nazwisko jest wymagane.";
        var actualErrorMsgLast =errorMsg.get(1);
        String actualErrorMsgLastName = actualErrorMsgLast.getText();
        Assertions.assertEquals(expErrorMsgEmptyLastName, actualErrorMsgLastName);

        String expectedErrorMsgEmptyEmail = "Adres e-mail jest wymagany.";
        var actualErrorMsgEmail =errorMsg.get(2);
        String actualErrorMsgEmptyEmail = actualErrorMsgEmail.getText();
        Assertions.assertEquals(expectedErrorMsgEmptyEmail, actualErrorMsgEmptyEmail);

        String expectedErrorMsgEmptyAddress = "Adres jest wymagany.";
        var actualErrorMsgAddress =errorMsg.get(3);
        String actualErrorMsgEmptyAddress = actualErrorMsgAddress.getText();
        Assertions.assertEquals(expectedErrorMsgEmptyAddress, actualErrorMsgEmptyAddress);

        String expectedErrorMsgEmptyCode= "Kod pocztowy jest wymagany.";
        var actualErrorMsgCode =errorMsg.get(4);
        String actualErrorMsgEmptyCode = actualErrorMsgCode.getText();
        Assertions.assertEquals(expectedErrorMsgEmptyCode, actualErrorMsgEmptyCode);

        String expectedErrorMsgEmptyCity = "Miasto jest wymagane.";
        var actualErrorMsgCity =errorMsg.get(5);
        String actualErrorMsgEmptyCity = actualErrorMsgCity.getText();
        Assertions.assertEquals(expectedErrorMsgEmptyCity, actualErrorMsgEmptyCity);

        String expectedErrorMsgEmptyCountry = "Kraj jest wymagany.";
        var actualErrorMsgCountry =errorMsg.get(6);
        String actualErrorMsgEmptyCountry = actualErrorMsgCountry.getText();
        Assertions.assertEquals(expectedErrorMsgEmptyCountry, actualErrorMsgEmptyCountry);

        String expectedErrorMsgEmptyPassword1 = "Hasło jest wymagane.";
        var actualErrorMsgPassword1 =errorMsg.get(7);
        String actualErrorMsgEmptyPassword1 = actualErrorMsgPassword1.getText();
        Assertions.assertEquals(expectedErrorMsgEmptyPassword1, actualErrorMsgEmptyPassword1);

        String expectedErrorMsgEmptyPassword2 = "Hasło jest wymagane.";
        var actualErrorMsgPassword2 =errorMsg.get(8);
        String actualErrorMsgEmptyPassword2 = actualErrorMsgPassword2.getText();
        Assertions.assertEquals(expectedErrorMsgEmptyPassword2, actualErrorMsgEmptyPassword2);
    }

    //Liczba znaków hasła <6,  hasła nie są zgodne
    //Hasło potwierdzające jest inne niż pierwotne -> komunikat "Podane hasła się nie zgadzają."
    //Użytkownik dostaje komunikat 'Hasło powinno zawierać przynajmniej 6 znaków.'
    @Test
    public void newPasswordValidaion () {
        openPage("https://stage.samplepage.pl/register");
        generateEmail();
        registrationForm("Testimię", "Testnazwisko", this.currentRegisteredUserEmail, "ul. Testowa 1",
                "01-164", "Testowe", "5e5fda3c239effb96cff1ec3", "559668377", "Em12", "Em123");

        clickRegisterButton();

        String URL = driver.getCurrentUrl();
        Assertions.assertEquals(URL, "https://stage.samplepage.pl/register");

        List<WebElement> errorMsg= driver.findElements(By.cssSelector(".field-validation-error"));
        String expectedErrorMsgPswToShort = "Hasło powinno zawierać przynajmniej 6 znaków.";
        var actualErrorMsgPsw =errorMsg.get(0);
        String actualErrorMsgPswToShort= actualErrorMsgPsw.getText();
        Assertions.assertEquals(expectedErrorMsgPswToShort, actualErrorMsgPswToShort);

        String expectedErrorMsgPswsDontMatch = "Podane hasła się nie zgadzają.";
        var actualErrorMsgPsws =errorMsg.get(1);
        String actualErrorMsggPswsDontMatch= actualErrorMsgPsws.getText();
        Assertions.assertEquals(expectedErrorMsgPswsDontMatch, actualErrorMsggPswsDontMatch);
    }


}
