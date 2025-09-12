package com.demoblaze.questions;

import com.demoblaze.ui.CartUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;


public class ValueCartQuestion implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        int total = actor.recall("total");

        return total == Integer.parseInt(CartUI.TXT_TOTAL.resolveFor(actor).getText());
    }

    public static ValueCartQuestion es() {
        return new ValueCartQuestion();
    }
}
