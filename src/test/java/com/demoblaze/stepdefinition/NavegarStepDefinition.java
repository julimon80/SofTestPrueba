package com.demoblaze.stepdefinition;


import com.demoblaze.hooks.OpenWeb;
import io.cucumber.java.es.Dado;
import net.serenitybdd.screenplay.actors.OnStage;

public class NavegarStepDefinition {


    @Dado("el usuario accede a la pagina web de la tienda")
    public void elUsuarioAccedeALaPaginaWebDeLaTienda() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenWeb.browserURL()
        );
    }
}