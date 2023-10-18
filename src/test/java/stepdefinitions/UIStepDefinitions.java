package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

import static utility.utilities.scrollPageToViewElement;

public class UIStepDefinitions {
    private WebDriver driver;

    @Given("I navigate to the home page")
    public void navigateToHomePage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://reqres.in/");
        driver.manage().window().maximize();
    }

    @Then("the home page should list different request types and endpoints")
    public void verifyHomePage() {
        // Display/print request types and endpoints
        List<WebElement> requestElements = driver.findElements(By.cssSelector("li[data-id][data-key]"));
        for (WebElement requestElement : requestElements) {
            String requestType = requestElement.getAttribute("data-http");
            String endpoint = requestElement.findElement(By.cssSelector("a")).getAttribute("href");
            System.out.println("Request Type: " + requestType + ", Endpoint: " + endpoint);
        }
    }

    @When("I select {string}")
    public void selectRequest(String requestType) {
        // Find and click on a specific request element
        WebElement specificRequest = driver.findElement(By.xpath("//li/a[normalize-space(text())='" + requestType + "']"));
        scrollPageToViewElement(driver, specificRequest);
        specificRequest.click();
        System.out.println("Clicked On SINGLE USER NOT FOUND");
    }

    @Then("the page should display the request {string} and response {string}")
    public void verifyRequestAndResponse(String expectedRequest, String expectedResponse) {
        // Wait for the page to load (you may need to add explicit waits here)
        WebElement requestDetails = driver.findElement(By.cssSelector(".try-out pre code"));
        WebElement responseDetails = driver.findElement(By.cssSelector(".response pre code"));

        String actualRequest = requestDetails.getText();
        String actualResponse = responseDetails.getText();

        // Assert the actual and expected values
        Assert.assertEquals(expectedRequest, actualRequest);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @When("I navigate to the support page")
    public void navigateToSupportPage() {
        // Code to navigate to the support page
    }

    @Then("the support page should list options for one-time & monthly support")
    public void verifySupportOptions() {
        // Code to verify support page options
    }

    @Then("the support page should provide Upgrade details")
    public void verifyUpgradeDetails() {
        // Code to verify upgrade details on the support page
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}