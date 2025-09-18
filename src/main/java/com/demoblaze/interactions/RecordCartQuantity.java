package com.demoblaze.interactions;

import com.demoblaze.ui.CartUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class RecordCartQuantity implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        int cantidad = CartUI.TXT_NUM_PRODUCTOS.resolveAllFor(actor).size();
        actor.remember("cantidad", cantidad);
        System.out.println("Número de productos en carrito: " + cantidad);
    }

    public static RecordCartQuantity now() {
        return new RecordCartQuantity();
    }
}

