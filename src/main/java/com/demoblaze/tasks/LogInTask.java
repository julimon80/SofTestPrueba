package com.demoblaze.tasks;

import com.demoblaze.ui.HomeUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LogInTask implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Click.on(HomeUI.BTN_LOGIN)
        );

    }

    public static LogInTask go() {
        return instrumented(LogInTask.class);
    }
}

