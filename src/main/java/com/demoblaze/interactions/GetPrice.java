package com.demoblaze.interactions;

import com.demoblaze.ui.HomeUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetPrice implements Interaction {

    private final int index;

    public GetPrice(int index) {
        this.index = index;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String precioTexto = HomeUI.TXT_PRECIO(index).resolveFor(actor).getText(); // "$360"
        int precio = Integer.parseInt(precioTexto.replace("$", "").trim());


        actor.remember("precio_" + index, precio);
        // Recuperamos el total acumulado, si no existe lo iniciamos en 0
        Integer total = actor.recall("total");
        if (total == null) {
            total = 0;
        }

        // Sumamos el nuevo precio al acumulado
        total += precio;
        System.out.println("Total: " + total);
        System.out.println("Precio: " + precio);
        // Guardamos el acumulado actualizado
        actor.remember("total", total);
    }

    public static GetPrice delProducto(int index) {
        return instrumented(GetPrice.class, index);
    }
}

