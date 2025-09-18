package com.demoblaze.tasks;

import com.demoblaze.ui.LoginUI;
import com.demoblaze.utils.Constants;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EnterWrongCredentialTask implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SendKeys.of(Constants.USERNAME).into(LoginUI.INPUT_USERNAME),
                SendKeys.of(Constants.PASSWORD).into(LoginUI.INPUT_PASSWORD),
                Click.on(LoginUI.BTN_LOGIN)
        );
    }

    public static EnterWrongCredentialTask go() {
        return instrumented(EnterWrongCredentialTask.class);
    }
}

