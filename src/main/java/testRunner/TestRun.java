package testRunner;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

// if we want to run all the feature files we created  , then there is no need to specify the name of the feature file , we just have to write "Features/"
// if we want to run for example 5 specific feature files , we will have to use the curly braces {} after the Equal sign and inside write the package name /and the feature file name inside the double quotes
// and use comma (,) to seperate them then open the double quotes and write inside the package name / and the other feature files name . and so on with the other feature files
@CucumberOptions(features = "Features/",
        glue = "stepDefinitions",
dryRun = false,
monochrome = false,
plugin = {"pretty","html:test-output"}
      //  tags = "@sanity" // the keyword tags is used to execute specific scenarios , by adding the @(name the scenario) , and they will based executed based on the tags we specified, we first have to add the tags to the feature file
        // if we want to run all the scenarios , we will have to remove the tags keyword
)

public class TestRun {
}
