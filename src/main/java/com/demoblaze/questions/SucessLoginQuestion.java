package com.demoblaze.questions;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static com.demoblaze.ui.HomeUI.TXT_WELCOME;


public class SucessLoginQuestion implements Question<String> {
    @Override
    public String answeredBy(Actor actor) {


        return TXT_WELCOME.resolveFor(actor).getText();
    }

    public static SucessLoginQuestion visible() {
        return new SucessLoginQuestion();
    }
}
