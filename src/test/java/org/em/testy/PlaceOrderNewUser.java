package org.em.testy;

import org.junit.jupiter.api.Test;

public class PlaceOrderNewUser extends TestBase {
    @Test
    //Wybierz kategorię
    //Wybierz produkt z grida
    //Dodaj produkt ze strony produktowej
    //Wciśnij przycisk "Koszyk" z poziomu panelu
    //Wciśnij Kup jako niezarejestrowany użytkownik
    //Metoda wysyłki Dhl
    public void buyAsGuest () throws InterruptedException {
        openPage("https://stage.samplepage.pl");
        addCartCheckout();

        buyAsGuestButton();

        newAddress("Testimię", "Testnazwiskoa", "testemail@wp.pl", "5e5fda3c239effb96cff1ec4",
                "Testowe", "ul. Testowa 1", "01-160", "566565959");

        orderFormConfirmShipping();
        confirmOrder();
    }

    //1. Wybór kategorii
    //2. Kliknięcie w produkt z grida
    //3. Dodanie do koszyka ze strony produktu
    //4. Idź do koszyka
    //5. Wybierz zaloguj i zapłać
    //6. Strona logowania; Zaloguj poprawnymi danymi
    //7. Idź do kasy (Zapłać)
    //8. Adres wysyłki zostaje defaultowy
    //9. Metoda wysyłki zostaje defaultowa
    //10. Potwierdź zamówienie
    //11. Przejdź do strony przelewy24


    @Test
    public void buyAndRegister() {
        openPage("https://stage.samplepage.pl/");
        //Wybierz kategorię
        //Wybierz 1 produkt z grida
        //Dodaj produkt ze strony produktowej
        //Wciśnij Zaloguj i zapłać przechodząc do strony logowania
        addCartCheckout();

        //Wciśniij przycisk Kup jako niezarejestrowany użytkownik
        clickRegisterNewUserButton();

        //Wygeneruj adres e-mail do formularza
        generateEmail();

        //Wypełnij formularz rejestracji all valid data
        registrationForm("Em", "C", this.currentRegisteredUserEmail,
                "djoadjoa 15/15", "10-431", "Wawa",
                "5e5fda3c239effb96cff1ec3", "668522459", this.currentRegisteredUserPassword,
                this.currentRegisteredUserPassword);
        //Potwierdź rejestrację
        clickRegisterButton();

        //Po zakończonej rejestracji wciśnij Dalej ->przenosi do Loszyka spowrotem
        clickRegisterCountinueButton();

        //Wciśnij Zapłać ->przenosi do Kasy
        goToCheckout();

        //Złóż zamówienie: Defaultowy adres i metodda wysyłki
        //->Przenosi do strony Przelewy24
        defaultOrderPlacement();
    }

}


