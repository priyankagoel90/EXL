package com.qa.EXL.Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/com/qa/EXL/Features", glue = { "StepDefinition" })

public class TestRunner {

}
