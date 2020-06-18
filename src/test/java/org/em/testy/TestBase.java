package org.em.testy;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase  extends Universal {

    public String currentRegisteredUserEmail;
    public final String currentRegisteredUserPassword = "defaultpassword";


    public void akceptujWarunki() {
        driver.findElement(By.id("accept-terms")).click();
    }


    //Uzupełnianie formularza rejestracji
    public void registrationForm(String imie, String nazwisko, String email, String adres,
                                 String kod, String miasto, String kraj, String telefon,
                                 String haslo1, String haslo2) {
        driver
                .findElement(By.cssSelector("#FirstName"))
                .sendKeys(imie);
        driver
                .findElement(By.cssSelector("#LastName"))
                .sendKeys(nazwisko);
        driver
                .findElement(By.cssSelector("#Email"))
                .sendKeys(email);

        driver
                .findElement(By.cssSelector("#StreetAddress"))
                .sendKeys(adres);
        driver
                .findElement(By.cssSelector("#ZipPostalCode"))
                .sendKeys(kod);
        driver
                .findElement(By.cssSelector("#City"))
                .sendKeys(miasto);
        driver
                .findElement(By.cssSelector("#CountryId"))
                .click();

        Select country = new Select(driver.findElement(By.cssSelector("#CountryId")));
        country.selectByValue(kraj);
        driver
                .findElement(By.cssSelector("#Phone"))
                .sendKeys(telefon);
        driver
                .findElement(By.cssSelector("#Password"))
                .sendKeys(haslo1);
        driver
                .findElement(By.cssSelector("#ConfirmPassword"))
                .sendKeys(haslo2);


        // Jak mogę asercje zrobić dla wszystkich wymaganych pól za pomocą iteracji?

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void registerUserNew() {
        openPage("https://stage.samplepage.pl/register");
        clickRegisterButton();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlContains("https://stage.samplepage.pl/registerresult"));
        clickRegisterCountinueButton();
    }

    public void generateEmail () {
        this.currentRegisteredUserEmail = "testemail+" + System.currentTimeMillis() + "@gmail.com";
    }

    //Dodawanie nowego adresu
    public void newAddress(String imie, String nazwisko, String email, String kraj,
                           String miasto, String adres, String kod, String telefon) {
        var wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains("checkout"));

        driver
                .findElement(By.cssSelector("#BillingNewAddress_FirstName"))
                .sendKeys(imie);
        driver
                .findElement(By.cssSelector("#BillingNewAddress_LastName"))
                .sendKeys(nazwisko);
        driver
                .findElement(By.cssSelector("#BillingNewAddress_Email"))
                .sendKeys(email);
        driver
                .findElement(By.cssSelector("#BillingNewAddress_CountryId"))
                .click();

        Select country = new Select(driver.findElement(By.cssSelector("#BillingNewAddress_CountryId")));
        country.selectByValue(kraj);

        driver
                .findElement(By.cssSelector("#BillingNewAddress_City"))
                .sendKeys(miasto);
        driver
                .findElement(By.cssSelector("#BillingNewAddress_Address1"))
                .sendKeys(adres);
        driver
                .findElement(By.cssSelector("#BillingNewAddress_ZipPostalCode"))
                .sendKeys(kod);


        driver
                .findElement(By.cssSelector("#BillingNewAddress_PhoneNumber"))
                .sendKeys(telefon);

        findAndClick(".new-address-next-step-button");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    //Dodawanie produktu do koszyka
    //1. Wybór kategorii
    //2. Kliknięcie w 2 produkt z grida
    //3. Strona produktu
    //4. Dodanie produktu do koszyka
    //5. Zamknięcie popupa
    public void addToCartFromProductPage() {
        WebElement product = driver.findElements(By.cssSelector(".product-grid .picture")).get(1);
        Actions movetoaction = new Actions(driver);
        movetoaction.moveToElement(product).build().perform();
        product.click();
        var wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlContains("https://stage.samplepage.pl/produkt"));
        findAndClick(".btn.btn-info.add-to-cart-button.d-inline-flex");

        WebElement zamknijOverlay = driver.findElement(By.cssSelector(".close.text-white"));
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(zamknijOverlay));
        zamknijOverlay.click();
    }

    //Dodawanie produktu do koszyka
    //1. Wybór kategorii
    //2. Kliknięcie w 2 produkt z grida
    //3. Strona produktu
    //4. Dodanie produktu do koszyka
    //5. Wciśnięcie "Pokaż Koszyk" na popupie pojawiającym się po dodaniu produktu
    public void addToCartFromProductPageAndViewCart() {
        WebElement product = driver.findElements(By.cssSelector(".product-grid .picture")).get(1);
        Actions movetoaction = new Actions(driver);
        movetoaction.moveToElement(product).build().perform();
        product.click();
        var wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlContains("https://stage.samplepage.pl/produkt"));
        findAndClick(".btn.btn-info.add-to-cart-button.d-inline-flex");
        viewCartButton();
    }

    //Wciśnij przycisk "Pokaż Koszyk" na pop-upie pojawiającym się po dodaniu produktu
    public void viewCartButton () {
        findAndClickByXpath("//*[@id=\"ModalAddToCart\"]/div/div/div[2]/div[2]/div/button[2]");
    }

    //Idź do koszyka z poziomu panelu nawigacyjnego
    public void goToCartFromNavPanel() {
        findAndClick(".mdil.mdil-cart");
    }

    // Zapłać// Zaloguj się i zapłać
    public void goToCheckout() {
        findAndClick(".checkout-button");
    }

    //Wciśnij przycisk 'Zamówienie jako gość"
    public void buyAsGuestButton (){
    findAndClick(".checkout-as-guest-button");
    }

    //Składanie Zamówienia STEP1 - Adres
    //1. Pozostaw deafultowy adres
    //2. Przejdź Dalej, do następnego kroku (Metody Wysłki)
    public void orderFormDeafaultAddress() {
        findAndClick("#billing-address-select");
        findAndClick(".new-address-next-step-button");
    }

    //Składanie zamówenia STEP 2
    //Wciśjnij przycisk "Dalej"
    //Potwierdza wybraną metodę dostawy i idzie do kolejnego kroku składania zamówienia - Confirm order
    //Defaultowa metoda dostawy to Inpost
    public void orderFormConfirmShipping(){
        findAndClick(".shipping-method-next-step-button");
    }


    //Składanie zamówenia STEP 2 - Metoda wysyłki DHL
    //Znajdź Shipping Methode kurier DHL
    //Zaznacz radio button kurier Dhl
    //Wciśnij DALEJ, by przejść do kolejnego kroku
    public void orderFormShippingCourierDhl() {

        List<WebElement> shippingMethod = driver.findElements(By.name("shippingoption"));
        var dhl = shippingMethod.get(1);
        dhl.sendKeys(Keys.SPACE);
        orderFormConfirmShipping();
    }


    public void orderFormShippingParcelLocker(String miasto, String ulica, String blok) {

        List<WebElement> shippingMethod = driver.findElements(By.name("shippingoption"));
        var parcelLocker = shippingMethod.get(2);
        parcelLocker.sendKeys(Keys.SPACE);

        //Wpisz miasto, ulicę i bloku
        //Zatwierdź ENTERem
        WebElement findParcelLocker = driver.findElement(By.cssSelector("#easypack-search"));
        var wait = new WebDriverWait(driver, 25);
        wait.until(ExpectedConditions.elementToBeClickable(findParcelLocker));
        findParcelLocker.sendKeys(miasto, ulica, blok);
        findParcelLocker.sendKeys(Keys.ENTER);

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-point-link")));
        //Wybierz pierwszy paczkomat z listy znalezioncych paczkomatów
        List<WebElement> parcelLockerList = driver.findElements(By.cssSelector(".list-point-link"));
        var firstParcelLocker = parcelLockerList.get(0);
        firstParcelLocker.click();

        //Zatwierdź wybór paczkomatu klikając przycisk 'Wybierz'
        //findAndClick(".select-link a");
        findAndClickByXpath("//*[@id=\"map-leaflet\"]/div[1]/div[6]/div/div[1]/div/div/div[2]/a[3]");

        //Poczekaj aż pojawi się informacja o pomyślnym wyborze paczkomatu
        //np. Pomyślnie wybrano paczkomat: WAW171M: pl. Powstańców Warszawy 2A, 00-030 Warszawa
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#paczkomatInfo")));

        //Wciśnij 'Dalej'
        // Przejdź do następnego kroku, Potwierdzenia Zamówienia
        findAndClick(".shipping-method-next-step-button");
    }

    public void orderFormShippingPocztex(String adres) {
        //Wybierz opcję wysyłki paczkomat
        List<WebElement> shippingMethod = driver.findElements(By.name("shippingoption"));
        var pocztex = shippingMethod.get(3);
        pocztex.sendKeys(Keys.SPACE);

        //Przjedź do popupu (iframe)
        driver.switchTo().frame(driver.findElement(By.id("pp-widget-iframe")));

        //Poczekaj aż search box dla adresu będzie klikalny
        WebElement addressInput = driver.findElement(By.cssSelector("#pp-widget-address"));
        var wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(addressInput));

        //Zaznacz i usuń domyślny adres
        addressInput.sendKeys(Keys.CONTROL +"a");
        addressInput.sendKeys (Keys.DELETE);

        //Wpisz adres
        //Wybierz pierwszą pozycję z dropdownlisty podpowiedzi i zatwierdź ENTERem
        addressInput.sendKeys(adres);
        addressInput.sendKeys(Keys.ARROW_DOWN);
        addressInput.sendKeys(Keys.ENTER);

        //Wybierz pierwszy element z listy placówek pocztexu
        List<WebElement> pocztexList = driver.findElements(By.id("pp-list"));
        var firstPocztex = pocztexList.get(0);
        firstPocztex.click();

        //Wciśnij przycisk 'Wybierz' by zatwierdzić wybór placówki pocztex
        findAndClickByXpath("//*[@id=\"map\"]/div[1]/div[6]/div/div[1]/div/div/button[2]");

        //Wróć do formularza
        driver.switchTo().defaultContent();

        //Wciśnij 'Dalej' i przejdź do ostatniego kroku, Potwierdzenia Zamówienia
        orderFormConfirmShipping();
    }



    //Składanie zamówienia dla zalogowanego użytkownika
    //Domyślne ustawienia adresu i metody wysyłki (kurier inpost)
    public void defaultOrderPlacement () {
        orderFormDeafaultAddress();
        orderFormConfirmShipping();
        confirmOrder();
    }

    //Ostatni krok zakupowy
    // Aceptacja regulaminu i przycisk Potwierdz
    public void confirmOrder() {
        WebElement confirmButton = driver.findElement(By.cssSelector(".confirm-order-next-step-button"));
        var wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));

        WebElement termsOfService = driver.findElement(By.cssSelector("#termsofservice"));
        termsOfService.sendKeys(Keys.SPACE);

        confirmButton.click();

        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains("przelewy24"));
    }


    public void paymentMethodPekao() {
        paymentMethode("PEKAO", "");
    }

    public void paymentMethode (String name, String bankUrlContains) {
        List<WebElement> paymentMethodes = driver.findElements(By.cssSelector(".bank-item"));
        //dla  metody płatności o atrybucie zawierającym nazwę banku/płatności
        //przypisz element i kliknij
        for (WebElement element: paymentMethodes) {
            if (element.getAttribute("data-search").contains(name)) {
                element.click();
            }
        }
        //poczekaj aż nastapi przełdowanie strony i url będzie zawierał
        //część url danego banku
        var wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains(bankUrlContains));

    }



    //Logowanie
    //Uzupełnij useremail
    //Uzupełnij hasło
    //Przycisk "Zaloguj"
    public void login(String userEmail, String userPassword) {
        WebElement email = driver.findElement(By.id("Email"));
        var wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(email));
        email.sendKeys(userEmail);

        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(userPassword);
        findAndClick(".px-5");
    }

    //wciśnij link "Nie pamiętasz hasła?"
    //przejście do strony odzyskiwania hasła passwordrecovery
    //wpisz email na jaki ma przyść reset hasła
    //Zatwierdź przyciskiem "Odzyskaj"
    //

    public void resetPassword(String recoveryEmail) {
        findAndClick(".forgot-password");

        var wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlToBe("https://stage.samplepage.pl/passwordrecovery"));

        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys(recoveryEmail);
        findAndClick(".password-recovery-button");
    }

    //Kliknij w UserPanel
    //Wybierz Zaloguj
    public void getLogIn() {
        findAndClick(".user-panel-trigger");
        findAndClick(".btn-secondary");
        var wait=new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlToBe("https://stage.samplepage.pl/login"));
    }

    //Kliknij w User Panel
    //Wybierz Zarejestruj Się
    public void getRegister (){
        findAndClick(".user-panel-trigger");
        findAndClick(".btn-outline-secondary");
        var wait=new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlToBe("https://stage.samplepage.pl/register"));
    }

    //Kliknij w UserPanel
    //Wybierz Moje Konto
    public void getMyAccount() {
        findAndClick(".user-panel-trigger");
        List<WebElement> userPanel = driver.findElements(By.cssSelector(".btn-outline-secondary"));
        var myAccount = userPanel.get(0);
        myAccount.click();
        var wait=new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlToBe("https://stage.samplepage.pl/customer/info"));
    }

    //Kliknij w UserPanel
    //Wybierz Moje Konto
    public void getMyOrders (){ findAndClick(".user-panel-trigger");
        List <WebElement> userPanel = driver.findElements(By.cssSelector(".btn-outline-secondary"));
        var myOrders = userPanel.get(1);
        myOrders.click();
        var wait=new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlToBe("https://stage.samplepage.pl/order/history"));
    }

    //Kliknij w UserPanel
    //Wybierz Wyloguj
    public void getLogOut() {
        findAndClick(".user-panel-trigger");
        List<WebElement> userPanel = driver.findElements(By.cssSelector(".btn-outline-secondary"));
        var logOut = userPanel.get(2);
        logOut.click();
        var wait=new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.urlToBe("https://stage.samplepage.pl/"));
    }

    //1. Wybór kategorii
    //2. Kliknięcie w 2 produkt z grida
    //3. Dodanie do koszyka ze strony produktu
    //4. Zamknij popup
    //5. Wciśjni "Koszyk" z poziomu panelu nawigacyjnego
    //6. Wybierz Zaloguj i zapłać
    //7. Strona logowania
    public void addCartCheckout() {
        addToCartFromProductPage();
        goToCartFromNavPanel();
        goToCheckout();
    }

    //Wciśnij przycisk "Zarejestruj się" zatwierdzający proces rejestracji
    //Ostatni krok rejestracji
    public void clickRegisterButton() {
        findAndClick("#register-button");
    }

    //Wciśnij przycisk "Zarejestruj się" na stronie logowania
    public void clickRegisterNewUserButton () {
        findAndClick(".register-button");
    }

    //Wciśnij przycisk Dalej po zakończonym procesie rejestracji
    public void clickRegisterCountinueButton (){
        findAndClick(".register-continue-button");
    }

}
