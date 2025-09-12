package com.demoblaze.questions;

import com.demoblaze.ui.CartUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;


public class ProductsCartQuestion implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        int cantidad = actor.recall("cantidad");

        return cantidad == CartUI.TXT_NUM_PRODUCTOS.resolveAllFor(actor).size();
    }

    public static ProductsCartQuestion es(int cantidad) {
        return new ProductsCartQuestion();
    }
}
