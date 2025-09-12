package com.demoblaze.interactions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

public class InteractionsTask {

    public static Performable waitForMilliseconds(int milliseconds) {
        return Task.where(
                actor -> {
                    try {
                        Thread.sleep(milliseconds);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    public static Performable validElement(Target message) {
        return Task.where(

                WaitUntil.the(message, isEnabled()).forNoMoreThan(10).seconds()
//                Used to validate that an element does contain x text or characteristic
//                WaitUntil.the(LoginView.TXT_USER_LOGIN1, containsText("1234")).forNoMoreThan(10).seconds()
        );
    }

    public static Performable clickElement(Target element) {
        return Task.where(
                WaitUntil.the(element, isEnabled()).forNoMoreThan(60).seconds()
                , Click.on(element)
        );
    }

    public static Performable scroll(int x1, int y1, int x2, int y2) {
        return Task.where(
                actor -> {

                    WebDriverFacade facade = (WebDriverFacade) BrowseTheWeb.as(actor).getDriver();
                    AppiumDriver appiumDriver = (AppiumDriver) facade.getProxiedDriver();

                    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
                    Sequence touchSequence = new Sequence(finger, 1);
                    touchSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x1, y1)); // Primer pointerMove
                    touchSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    touchSequence.addAction(finger.createPointerMove(Duration.ofMillis(750), PointerInput.Origin.viewport(), x2, y2)); // Segundo pointerMove
                    touchSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                    appiumDriver.perform(Collections.singletonList(touchSequence));

                }
        );
    }

    public static Performable click(int x1, int y1) {
        return Task.where(
                actor -> {

                    WebDriverFacade facade = (WebDriverFacade) BrowseTheWeb.as(actor).getDriver();
                    AppiumDriver appiumDriver = (AppiumDriver) facade.getProxiedDriver();

                    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
                    Sequence touchSequence = new Sequence(finger, 1);
                    touchSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x1, y1)); // Primer pointerMove
                    touchSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    touchSequence.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), x1, y1));
                    touchSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                    appiumDriver.perform(Collections.singletonList(touchSequence));

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

    public static Performable scrollByPixels(int pixels) {
        return Task.where("{0} scrolls by " + pixels + " pixels",
                actor -> {
                    WebDriver driver = BrowseTheWeb.as(actor).getDriver();
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

                    // Desplazar la pantalla hacia abajo en la cantidad de píxeles especificada
                    jsExecutor.executeScript("window.scrollBy(0, " + pixels + ");");

                    // Esperar brevemente para asegurarse de que el desplazamiento haya terminado
                    BrowseTheWeb.as(actor).waitFor(500).milliseconds();
                }
        );
    }

    public static Performable webScrollElement(Target target) {
        return Task.where("{0} scrolls to element and clicks it",
                actor -> {
                    WebDriver driver = BrowseTheWeb.as(actor).getDriver();
                    Actions actions = new Actions(driver);

                    // Move to the element using Actions
                    actions.moveToElement(target.resolveFor(actor).getElement()).perform();


                    // Perform the click action on the target element
                    actor.attemptsTo(
                            InteractionsTask.waitForMilliseconds(2000),
                            InteractionsTask.scrollDown(9),
                            InteractionsTask.waitForMilliseconds(2000),
                            Click.on(target));
                }
        );

    }


    public static Performable scrollDown(int pixels) {
        return Task.where("{0} scrolls down the page",
                actor -> {
                    WebDriver driver = BrowseTheWeb.as(actor).getDriver();
                    Actions actions = new Actions(driver);
                    actions.moveByOffset(0, pixels).perform();
                }
        );
    }


    public static Performable scrollElement2(Target element) {

        return Task.where(
                actor -> {
                    WebDriverFacade facade = (WebDriverFacade) BrowseTheWeb.as(actor).getDriver();
                    AppiumDriver appiumDriver = (AppiumDriver) facade.getProxiedDriver();

                    // Obtener tamaño de la pantalla
                    Dimension screenSize = appiumDriver.manage().window().getSize();
                    int width = screenSize.getWidth();
                    int height = screenSize.getHeight();

                    // Calcular coordenadas de desplazamiento (porcentajes)
                    int startX = width / 2; // Centro horizontal
                    int startY = (int) (height * 0.7); // 80% de la altura (parte inferior)
                    int endY = (int) (height * 0.3); // 20% de la altura (parte superior)

                    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
                    Sequence touchSequence = new Sequence(finger, 1);
                    touchSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
                    touchSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    touchSequence.addAction(finger.createPointerMove(Duration.ofMillis(750), PointerInput.Origin.viewport(), startX, endY));
                    touchSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                    appiumDriver.perform(Collections.singletonList(touchSequence));

                    // Esperar para que la UI se actualice
                    try {
                        Thread.sleep(2000); // Ajustar según sea necesario
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // Verificar si el elemento está presente
                    if (element.resolveFor(actor).isPresent()) {
                        actor.attemptsTo(Click.on(element));
                    } else {
                        throw new RuntimeException("El elemento no se encontró después del desplazamiento.");
                    }
                }
        );
    }


    public static Performable enterTextBox() {
        return Task.where(
                actor -> {
                    WebDriverFacade facade = (WebDriverFacade) BrowseTheWeb.as(actor).getDriver();
                    AndroidDriver androidDriver = (AndroidDriver) facade.getProxiedDriver();
                    androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
                    waitForMilliseconds(1000);

                }
        );
    }

    public static Performable BackButton() {
        return Task.where(
                actor -> {
                    WebDriverFacade facade = (WebDriverFacade) BrowseTheWeb.as(actor).getDriver();
                    AndroidDriver androidDriver = (AndroidDriver) facade.getProxiedDriver();
                    androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
                    waitForMilliseconds(1000);

                }
        );
    }


    public static Performable scrollUp(int pixels) {
        return Task.where(
                "Scroll up on the page"
                , (actor) -> {
                    ((JavascriptExecutor) BrowseTheWeb.as(actor).getDriver()).executeScript(
                            "window.scrollBy(0, -" + pixels + ");"
                    );
                });
    }

    public static Performable signatureCWRobot() {
        return Task.where(
                actor -> {
                    WebDriverFacade facade = (WebDriverFacade) BrowseTheWeb.as(actor).getDriver();
                    AppiumDriver appiumDriver = (AppiumDriver) facade.getProxiedDriver();

                    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

                    // C
                    Sequence touchSequence1 = new Sequence(finger, 1);
                    touchSequence1.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 180, 900));
                    touchSequence1.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    touchSequence1.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 100, 900));
                    touchSequence1.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 100, 1200));
                    touchSequence1.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 180, 1200));
                    touchSequence1.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                    appiumDriver.perform(Collections.singletonList(touchSequence1));

                    // W
                    Sequence touchSequence2 = new Sequence(finger, 1);
                    touchSequence2.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 200, 1050));
                    touchSequence2.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    touchSequence2.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 220, 1200));
                    touchSequence2.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 240, 1050));
                    touchSequence2.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 260, 1200));
                    touchSequence2.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 280, 1050));
                    touchSequence2.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                    appiumDriver.perform(Collections.singletonList(touchSequence2));

                    // R
                    Sequence touchSequence3 = new Sequence(finger, 1);
                    touchSequence3.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 320, 1200));
                    touchSequence3.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    touchSequence3.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 320, 900));
                    touchSequence3.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 420, 900));
                    touchSequence3.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 420, 1050));
                    touchSequence3.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 320, 1050));
                    touchSequence3.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 420, 1200));
                    touchSequence3.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                    appiumDriver.perform(Collections.singletonList(touchSequence3));

                    // O
                    Sequence touchSequence4 = new Sequence(finger, 1);
                    touchSequence4.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 440, 1050));
                    touchSequence4.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    touchSequence4.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 440, 1200));
                    touchSequence4.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 540, 1200));
                    touchSequence4.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 540, 1050));
                    touchSequence4.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 440, 1050));
                    touchSequence4.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                    appiumDriver.perform(Collections.singletonList(touchSequence4));

                    // B
                    Sequence touchSequence5 = new Sequence(finger, 1);
                    touchSequence5.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 560, 900));
                    touchSequence5.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    touchSequence5.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 560, 1200));
                    touchSequence5.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 660, 1200));
                    touchSequence5.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 660, 1050));
                    touchSequence5.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 560, 1050));
//                    touchSequence5.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 560, 1050));
//                    touchSequence5.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 660, 1050));
                    touchSequence5.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                    appiumDriver.perform(Collections.singletonList(touchSequence5));

                    // O
                    Sequence touchSequence6 = new Sequence(finger, 1);
                    touchSequence6.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 680, 1050));
                    touchSequence6.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    touchSequence6.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 680, 1200));
                    touchSequence6.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 780, 1200));
                    touchSequence6.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 780, 1050));
                    touchSequence6.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 680, 1050));
                    touchSequence6.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                    appiumDriver.perform(Collections.singletonList(touchSequence6));

                    // T
                    Sequence touchSequence7 = new Sequence(finger, 1);
                    touchSequence7.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 800, 1050));
                    touchSequence7.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    touchSequence7.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 900, 1050));
                    touchSequence7.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 850, 1050));
                    touchSequence7.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 850, 900));
                    touchSequence7.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 850, 1200));
                    touchSequence7.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                    appiumDriver.perform(Collections.singletonList(touchSequence7));
                }
        );
    }

    //Ingresa a un iframe
    public static Performable enterIframe(Target iframe) {
        return Task.where(
                actor -> {
                    WebDriver driver = BrowseTheWeb.as(actor).getDriver();
                    WebElement iframeElement = iframe.resolveFor(actor).getWrappedElement();
                    driver.switchTo().frame(iframeElement);
                }
        );
    }

    public static Performable exitIframe() {
        return Task.where(
                actor -> {
                    WebDriver driver = BrowseTheWeb.as(actor).getDriver();
                    driver.switchTo().defaultContent(); // Regresa al contenido principal del documento
                }
        );
    }

    public static Performable switchToNewWindowOrIframe() {
        return Task.where(
                actor -> {
                    WebDriver driver = BrowseTheWeb.as(actor).getDriver();
                    String currentWindow = driver.getWindowHandle();
                    Set<String> allWindows = driver.getWindowHandles();

                    // Cambiar a la nueva ventana o iframe que no sea la actual
                    for (String window : allWindows) {
                        if (!window.equals(currentWindow)) {
                            driver.switchTo().window(window);
                        }
                    }
                }
        );
    }

    public static Performable clickOnDOMPosition(int x, int y) {
        return Task.where("{0} clicks on a specific position in the DOM",
                actor -> {
                    WebDriverFacade webDriverFacade = (WebDriverFacade) BrowseTheWeb.as(actor).getDriver();
                    JavascriptExecutor js = (JavascriptExecutor) webDriverFacade.getProxiedDriver();

                    // Ejecutar el script para hacer clic en una posición X, Y
                    String script = String.format("document.elementFromPoint(%d, %d).click();", x, y);
                    js.executeScript(script);
                }
        );
    }

    public static Question<String> getTextFrom(Target elemento) {
        return actor -> elemento.resolveFor(actor).getText();  // Obtiene el texto del elemento
    }

    public static String getTextFromElement(Target element) {
        // Usamos un objeto para almacenar el resultado
        final String[] text = {""};

        // Creamos una tarea para obtener el texto
        Task.where("{0} obtiene el texto del elemento",
                actor -> {
                    text[0] = actor.asksFor(getTextFrom(element));
                }
        ).performAs(null);  // Asumiendo que el actor se maneja en otro contexto, reemplaza `null` si es necesario

        // Devolvemos el texto obtenido
        return text[0];
    }


    // Nueva tarea para manejar la alerta de SweetAlert y hacer clic en el botón "OK"
    public static Performable handleSweetAlertOk(Target target) {
        return Task.where(
                "{0} clicks the SweetAlert OK button",
                actor -> {
                    WebDriver driver = BrowseTheWeb.as(actor).getDriver();
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                    // Find the element using the target
                    WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(target.resolveFor(actor)));
                    okButton.click();
                }
        );
    }

    //Metodo para hacer clic en aceptar en una alerta
    public static Performable handleConfirmation() {
        return Task.where("{0} maneja el cuadro de confirmación",
                actor -> {

                    WebDriver driver = BrowseTheWeb.as(actor).getDriver();
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


                    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                    alert.accept();
                }
        );
    }

    public static Performable uploadFile(Target target, String path) {
        return Task.where(
                "{0} uploads the file to the target field",
                SendKeys.of(path).into(target),
                WaitUntil.the(target, WebElementStateMatchers.isVisible()).forNoMoreThan(5).seconds(),
                InteractionsTask.waitForMilliseconds(2000)
        );
    }

    // Metodo para cambiar de pestaña por índice
    public static Performable switchToTab(int tabIndex) {
        return Task.where("{0} switches to tab #" + tabIndex, actor -> {
            WebDriver driver = BrowseTheWeb.as(actor).getDriver();
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

            if (tabIndex < tabs.size()) {
                driver.switchTo().window(tabs.get(tabIndex));
            } else {
                throw new IllegalArgumentException("Tab index " + tabIndex + " does not exist.");
            }
        });
    }

    // Metodo para cambiar de pestaña por título
    public static Performable switchToTabByTitle(String title) {
        return Task.where("{0} switches to tab with title: " + title, actor -> {
            WebDriver driver = BrowseTheWeb.as(actor).getDriver();
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

            for (String tab : tabs) {
                driver.switchTo().window(tab);
                if (driver.getTitle().equals(title)) {
                    break;
                }
            }
        });
    }

    public static int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("El valor máximo debe ser mayor que el valor mínimo.");
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }


    public static Performable scrollToElementAndClick(Target button, Target container) {
        return Task.where("Scroll until the element is visible inside the container and click", actor -> {
            WebElementFacade containerElement = BrowseTheWeb.as(actor).find(container);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

            while (true) {
                try {
                    // Intentar encontrar el elemento
                    WebElementFacade buttonElement = BrowseTheWeb.as(actor).find(button);

                    // Verificar si el elemento es visible
                    if (buttonElement.isVisible()) {
                        buttonElement.click(); // Hacer clic si el elemento ya está visible
                        break;
                    }
                } catch (Exception e) {
                    // Manejar la excepción si el elemento aún no está en el DOM
                }

                // Hacer scroll un poco hacia abajo
                jsExecutor.executeScript("arguments[0].scrollTop += 100;", containerElement);

                // Esperar un poco antes de intentar de nuevo
                InteractionsTask.waitForMilliseconds(500);
            }
        });
    }


}
