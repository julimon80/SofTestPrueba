package com.demoblaze.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features/",
        glue = {"com.demoblaze.stepdefinition", "com.demoblaze.hooks"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@loginIncorrecto"
)
public class GlobalRunner {
}