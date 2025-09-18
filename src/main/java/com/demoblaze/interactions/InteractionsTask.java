package com.demoblaze.interactions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InteractionsTask {

    public static void waitForMilliseconds(int milliseconds) {
        Task.where(
                actor -> {
                    try {
                        Thread.sleep(milliseconds);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }


    public static Performable acceptAlert() {
        return Task.where("{0} handles the alert",
                actor -> {
                    WebDriver driver = BrowseTheWeb.as(actor).getDriver();

                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Usa Duration.ofSeconds()

                    try {
                        // Espera hasta que la alerta sea presente
                        InteractionsTask.waitForMilliseconds(1000);
                        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                        alert.accept(); // Acepta la alerta
                    } catch (Exception e) {
                        // Maneja el caso en que la alerta no esté presente
                        System.out.println("No alert found.");
                    }
                }
        );
    }

}
