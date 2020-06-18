package org.em.testy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaceOrderAuthenticated extends TestBase {


    @BeforeEach
    public void before() {
        this.registerUserNew();
    }

    @Test
    //1. Zarejestruj
    //2. Wyloguj
    //3. Zaloguj
    //4. Wybór kategorii
    //5. Kliknięcie w 2 produkt z grida
    //6. Dodanie do koszyka ze strony produktu
    //7. Idź do koszyka
    //8. Wybierz zapłać
    //9. Idź do kasy (Zapłać)
    //10. Formularz zamówienia step1:Adres wysyłki - defaultowy - kliknij Dalej
    //11. Formularz zamówienia step2: Metoda wysyłki - defaultowa pierwsza - kliknij Dalej
    //12. Formularz zamówienia step3: Zakaceptuj Warunki i potwierdź zamówienie
    //13. Przejdź do Przelewy 24
    public void authenticatedFromProductPage() {
        getLogOut();
        getLogIn();
        login(this.currentRegisteredUserEmail, this.currentRegisteredUserPassword);
        addCartCheckout();
        orderFormDeafaultAddress();
        orderFormConfirmShipping();
        confirmOrder();
    }

    @Test
    //1. Zarejestruj
    //2. Wyloguj
    //3. Zaloguj
    //4. Wybór kategorii
    //5. Najedź do 2 produkt z grida i wciśnij "Dodaj do koszyka"
    //6. Dodanie do koszyka ze strony produktu
    //7. Idź do koszyka
    //8. Wybierz zapłać
    //9. Idź do kasy (Zapłać)
    //10. Formularz zamówienia step1:Adres wysyłki - defaultowy - kliknij Dalej
    //11. Formularz zamówienia step2: Metoda wysyłki - defaultowa pierwsza - kliknij Dalej
    //12. Formularz zamówienia step3: Zakaceptuj Warunki i potwierdź zamówienie
    //13. Przejdź do Przelewy 24
    public void authenticatedFromGrid () {}


}
