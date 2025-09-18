package com.demoblaze.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginUI {

    public static final Target INPUT_USERNAME = Target.the("Input username")
            .locatedBy("//input[contains(@id,'loginusername')]");

    public static final Target INPUT_PASSWORD = Target.the("Input password")
            .locatedBy("//input[contains(@id,'loginpassword')]");

    public static final Target BTN_LOGIN = Target.the("btn login")
            .locatedBy("//button[@type='button'][contains(.,'Log in')]");
}
