package com.Kashier.testcases;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import Data.JsonDataReader;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SignUpTest  {

    WebDriver  driver;

    public String generateRandomString() {
        Random rand=new Random();
        String aToZ = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzs";
        StringBuilder res=new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randIndex=rand.nextInt(aToZ.length());
            res.append(aToZ.charAt(randIndex));
        }
        return res.toString();
    }

    @Test( priority = 1)
     public void successfulSignUp() throws IOException, ParseException {
         JsonDataReader jsonReader = new JsonDataReader(1);
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
          WebElement fullNameField = driver.findElement(By.name("full_name"));
          WebElement storeNameField = driver.findElement(By.name("store_name"));
          WebElement checkBox = driver.findElement(By.className("transform-checkbox-icon"));
          WebElement phoneNumberField = driver.findElement(By.name("mobile_number"));
          WebElement emailField = driver.findElement(By.name("email"));
          WebElement passwordField = driver.findElement(By.name("password"));
          WebElement retypePasswordField = driver.findElement(By.name("rpassword"));
          WebElement signUpButton = driver.findElement(By.id("register-submit-btn"));
          fullNameField.sendKeys(jsonReader.fullNameField);
          storeNameField.sendKeys(jsonReader.storeNameField);
          checkBox.click();
          phoneNumberField.sendKeys(jsonReader.phoneNumberField);
          emailField.sendKeys(generateRandomString()+jsonReader.emailField);
          passwordField.sendKeys(jsonReader.passwordField);
          retypePasswordField.sendKeys(jsonReader.retypePasswordField);
          signUpButton.click();
 
          Boolean dashboard = new WebDriverWait(driver,Duration.ofSeconds(30))
                  .until(ExpectedConditions.titleIs("Kashier | Dashboard"));
          System.out.println(dashboard);
          Assert.assertTrue(dashboard);
 
    }
    @Test(priority = 2)
    public void checkThatAllFieldAreMandatory() throws IOException, ParseException {
        JsonDataReader jsonReader = new JsonDataReader(2);
        By SignUpButton = By.id("register-submit-btn");
        By fieldRequiredLocator = By.className("close");
        WebElement SignUpButtonLocator = driver.findElement(SignUpButton);
        SignUpButtonLocator.click();
        new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(fieldRequiredLocator));
    }

     @Test(priority = 3)
     public void checkPhoneNumberLessThan11Chars() throws IOException, ParseException {
         JsonDataReader jsonReader = new JsonDataReader(3);
         WebElement fullNameField = driver.findElement(By.name("full_name"));
         WebElement storeNameField = driver.findElement(By.name("store_name"));
         WebElement checkBox = driver.findElement(By.className("transform-checkbox-icon"));
         WebElement phoneNumberField = driver.findElement(By.name("mobile_number"));
         WebElement emailField = driver.findElement(By.name("email"));
         WebElement passwordField = driver.findElement(By.name("password"));
         WebElement retypePasswordField = driver.findElement(By.name("rpassword"));
         WebElement signUpButton = driver.findElement(By.id("register-submit-btn"));
         By invalidPhoneNumberErrorLocator = By.className("close");
         fullNameField.sendKeys(jsonReader.fullNameField);
         storeNameField.sendKeys(jsonReader.storeNameField);
         checkBox.click();
         phoneNumberField.sendKeys(jsonReader.phoneNumberField);
         emailField.sendKeys(generateRandomString()+jsonReader.emailField);
         passwordField.sendKeys(jsonReader.passwordField);
         retypePasswordField.sendKeys(jsonReader.retypePasswordField);
         signUpButton.click();
         new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(invalidPhoneNumberErrorLocator));
     }
     @Test(priority = 4)
    public void checkSignUpWithInvalidEmail() throws IOException, ParseException {
        JsonDataReader jsonReader = new JsonDataReader(4);
         WebElement fullNameField = driver.findElement(By.name("full_name"));
         WebElement storeNameField = driver.findElement(By.name("store_name"));
         WebElement checkBox = driver.findElement(By.className("transform-checkbox-icon"));
         WebElement phoneNumberField = driver.findElement(By.name("mobile_number"));
         WebElement emailField = driver.findElement(By.name("email"));
         WebElement passwordField = driver.findElement(By.name("password"));
         WebElement retypePasswordField = driver.findElement(By.name("rpassword"));
         WebElement signUpButton = driver.findElement(By.id("register-submit-btn"));
         fullNameField.sendKeys(jsonReader.fullNameField);
         storeNameField.sendKeys(jsonReader.storeNameField);
         By invalidemailErrorLocator = By.className("close");
         checkBox.click();
         phoneNumberField.sendKeys(jsonReader.phoneNumberField);
         emailField.sendKeys(jsonReader.emailField);
         passwordField.sendKeys(jsonReader.passwordField);
         retypePasswordField.sendKeys(jsonReader.retypePasswordField);
         signUpButton.click();
        new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(invalidemailErrorLocator));

    }
     @Test(priority = 5)
    public void checkThatRetypePasswordIsMandatory() throws IOException, ParseException {
        JsonDataReader jsonReader = new JsonDataReader(5);
        WebElement retypePasswordField = driver.findElement(By.name("rpassword"));
        retypePasswordField.click();
        boolean retypePasswordErrorMessage = driver.findElement(By.id("r-register-password-error")).isDisplayed();
        System.out.println(retypePasswordErrorMessage);
        Assert.assertTrue(retypePasswordErrorMessage);
    }



    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://merchant.kashier.io/signup");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}