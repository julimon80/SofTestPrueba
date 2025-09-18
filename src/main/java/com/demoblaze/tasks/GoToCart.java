package com.demoblaze.tasks;

import com.demoblaze.interactions.RecordCartQuantity;
import com.demoblaze.ui.CartUI;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;


import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class GoToCart implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CartUI.BTN_CARRITO, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CartUI.BTN_CARRITO),
                WaitUntil.the(CartUI.TXT_NUM_PRODUCTOS, isVisible()).forNoMoreThan(10).seconds(),
                RecordCartQuantity.now()
        );
    }

    public static GoToCart go() {
        return instrumented(GoToCart.class);
    }
}
