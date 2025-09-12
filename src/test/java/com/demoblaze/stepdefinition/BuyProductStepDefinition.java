package com.demoblaze.stepdefinition;


import com.demoblaze.interactions.InteractionsTask;
import com.demoblaze.questions.ProductsCartQuestion;
import com.demoblaze.questions.ValueCartQuestion;
import com.demoblaze.tasks.AddtoCartTask;

import com.demoblaze.tasks.GoToCart;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;


public class BuyProductStepDefinition {

    @Dado("el usuario quiere agregar {} productos al carrito")
    public void elUsuarioQuiereAgregarProductosAlCarrito(int productos) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AddtoCartTask.go(productos)
        );
    }

    @Cuando("el usuario va al carrito de compras")
    public void elUsuarioVaAlCarritoDeCompras() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                GoToCart.go()
        );
    }

    @Entonces("el usuario deberia ver los {} productos en el carrito")
    public void elUsuarioDeberiaVerLosProductosEnElCarrito(int productos) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ProductsCartQuestion.es(productos), equalTo(true))
        );
    }

    @Y("el usuario deberia ver el total de los productos en el carrito")
    public void elUsuarioDeberiaVerElTotalDeLosProductosEnElCarrito() {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ValueCartQuestion.es(), equalTo(true))
        );
    }
}