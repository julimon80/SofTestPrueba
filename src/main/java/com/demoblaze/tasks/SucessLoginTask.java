package com.demoblaze.tasks;

import com.demoblaze.interactions.GetPrice;
import com.demoblaze.interactions.InteractionsTask;
import com.demoblaze.ui.CartUI;
import com.demoblaze.ui.HomeUI;
import com.demoblaze.ui.LoginUI;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SucessLoginTask implements Task {

    private DataTable data;

    public SucessLoginTask(DataTable data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, String>> values = data.asMaps(String.class, String.class);

        actor.attemptsTo(
                SendKeys.of(String.valueOf(values.get(0).get("username"))).into(LoginUI.INPUT_USERNAME),
                SendKeys.of(String.valueOf(values.get(0).get("password"))).into(LoginUI.INPUT_PASSWORD),
                Click.on(LoginUI.BTN_LOGIN)
        );
    }

    public static SucessLoginTask go(DataTable data) {
        return instrumented(SucessLoginTask.class, data);
    }
}

