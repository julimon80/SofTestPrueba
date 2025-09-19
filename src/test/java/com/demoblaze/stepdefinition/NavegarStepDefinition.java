package com.demoblaze.stepdefinition;


import com.demoblaze.hooks.OpenWeb;
import io.cucumber.java.es.Dado;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.OpenUrl;
import net.serenitybdd.screenplay.actors.OnStage;

public class NavegarStepDefinition {


    @Dado("el usuario accede a la pagina web de la tienda")
    public void elUsuarioAccedeALaPaginaWebDeLaTienda() {
        // El actor en el spotlight abre directamente la URL
        OnStage.theActorInTheSpotlight().attemptsTo(
                Task.where("{0} abre la pagina de la tienda",
                        actor -> BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver().get("https://www.demoblaze.com/")
                )
        );
    }

}