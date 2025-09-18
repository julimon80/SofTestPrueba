package com.demoblaze.tasks;

import com.demoblaze.interactions.InteractionsTask;
import com.demoblaze.interactions.GetPrice;
import com.demoblaze.ui.CartUI;
import com.demoblaze.ui.HomeUI;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;


import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddtoCartTask implements Task {

    private int productoIndex;

    public AddtoCartTask(int productoIndex) {
        this.productoIndex = productoIndex;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(HomeUI.BTN_PRODUCTO(1), isVisible()).forNoMoreThan(10).seconds()
        );


        for (int i = 1; i <= productoIndex; i++) {

            actor.attemptsTo(
                    GetPrice.delProducto(i),
                    Click.on(HomeUI.BTN_PRODUCTO(i)),
                    Click.on(CartUI.BTN_ANADIR_CARRITO),
                    InteractionsTask.acceptAlert(),
                    Click.on(CartUI.BTN_HOME)
            );
        }



    }

    public static AddtoCartTask go(int productoIndex) {
        return instrumented(AddtoCartTask.class, productoIndex);
    }
}

