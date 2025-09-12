package com.demoblaze.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CartUI {

    public static final Target BTN_HOME = Target.the("BTN HOME")
            .locatedBy("//a[@class='nav-link'][contains(.,'Home (current)')]");

    public static final Target BTN_ANADIR_CARRITO = Target.the("BTN ADD TO CART")
            .locatedBy("//a[contains(.,'Add to cart')]");

    public static final Target BTN_CARRITO = Target.the("BTN CARRITO")
            .locatedBy("//a[@class='nav-link'][contains(.,'Cart')]");

    public static final Target TXT_NUM_PRODUCTOS = Target.the("TXT NUM PRODUCTOS")
            .locatedBy("//tbody[@id='tbodyid']/tr");

    public static final Target TXT_TOTAL = Target.the("TXT TOTAL")
            .locatedBy("//h3[@id='totalp']");
}
