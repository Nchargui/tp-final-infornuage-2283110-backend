package com.kriNad.backend.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTestEdge {

        private  WebDriver driver = new EdgeDriver();

        @BeforeEach
        public void setUp(){
                System.setProperty("webdriver.edge.driver", "data/geckodriver.exe");

        }


        @Test
        public void testEdge(){
                driver.get("http://localhost:9292/login");

                WebElement toggleRegister = driver.findElement(By.id("register-toggle"));
                toggleRegister.click();


                WebElement submitConnexion = driver.findElement(By.id("submit-register"));


                // animation prend du temps pour selenium, alors il faut attendre que le bouton submitConnexion est là
                Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(d -> submitConnexion.isDisplayed());



                WebElement fnameinput = driver.findElement(By.name("fname"));
                WebElement lnameInput = driver.findElement(By.name("lname"));
                WebElement phoneInput = driver.findElement(By.name("phone"));
                WebElement emailInput = driver.findElement(By.name("email"));
                WebElement password = driver.findElement(By.name("password"));


                fnameinput.sendKeys("edge");
                lnameInput.sendKeys("selenium");
                emailInput.sendKeys("222@gmail.com");
                phoneInput.sendKeys("123456798");
                password.sendKeys("123");

                ///  toggleBox : L'animation pour switcher de conenxion à register empêche Selenium de cliquer sur le submit
                try {
                        WebElement overlay = driver.findElement(By.className("toggleBox"));
                        if (overlay.isDisplayed()) {
                                overlay.click();
                        }
                } catch (NoSuchElementException e) {

                }


                /// un element (d'animtion) bloque le submit
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitConnexion);





        }





}
