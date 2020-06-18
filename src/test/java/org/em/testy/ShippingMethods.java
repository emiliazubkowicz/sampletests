package org.em.testy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShippingMethods extends TestBase {
    @BeforeEach
    public void before() {
        this.registerUserNew();
    }


    @Test
    public void defaultShipping () {
        addCartCheckout();
        orderFormDeafaultAddress();
        orderFormConfirmShipping();
        confirmOrder();
    }

    @Test
    public void dhlShipping  () {
        addCartCheckout();
        orderFormDeafaultAddress();
        orderFormShippingCourierDhl();
        confirmOrder();
    }

    @Test
    public void parcelLockerShipping () {
        addCartCheckout();
        orderFormDeafaultAddress();
        orderFormShippingParcelLocker("Warszawa ", "Zawiszy ", "16");
        confirmOrder();
    }

    @Test
    public void pocztexShipping () {
        addCartCheckout();
        orderFormDeafaultAddress();
        orderFormShippingPocztex("Warszawa Zawiszy 16");
    }





}




