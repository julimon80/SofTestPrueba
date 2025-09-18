package com.demoblaze.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TotalCart implements Question<Integer> {

    private final int productos;

    public TotalCart(int productos) {
        this.productos = productos;
    }

    @Override
    public Integer answeredBy(Actor actor) {
        int total = 0;
        for (int i = 1; i <= productos; i++) {
            Integer precio = actor.recall("precio_" + i); // 👈 casteo automático
            total += precio;
        }
        return total;
    }

    public static TotalCart de(int productos) {
        return new TotalCart(productos);
    }
}
