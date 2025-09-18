package com.demoblaze.stepdefinition;


import com.demoblaze.questions.LoginWrongQuestion;
import com.demoblaze.questions.SucessLoginQuestion;
import com.demoblaze.tasks.EnterWrongCredentialTask;
import com.demoblaze.tasks.LogInTask;
import com.demoblaze.tasks.SucessLoginTask;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class LoginStepDefinition {


    @Dado("el usuario ingresa en la opcion inicio de sesion Log in")
    public void elUsuarioIngresaEnLaOpcionInicioDeSesionSingUp() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                LogInTask.go()
        );
    }

    @Cuando("el usuario ingresa el usuario y contraseña")
    public void elUsuarioIngresaElUsuarioYContrasena() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                EnterWrongCredentialTask.go()
        );
    }

    @Entonces("el usuario deberia ver el mensaje de error {string}")
    public void elUsuarioDeberiaVerElMensajeDeError(String mensajeError) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El mensaje de error", LoginWrongQuestion.visible(), equalTo(mensajeError))
        );
    }


    @Cuando("el usuario ingresa el usuario y contraseña validos")
    public void elUsuarioIngresaElUsuarioYContrasenaValidos(DataTable dataTable) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SucessLoginTask.go(dataTable)
        );
    }

    @Entonces("el usuario deberia ver el mensaje del home {string}")
    public void elUsuarioDeberiaVerElMensajeDelHome(String menesajeHome) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El mensaje del home", SucessLoginQuestion.visible(), containsString(menesajeHome))
        );
    }
}