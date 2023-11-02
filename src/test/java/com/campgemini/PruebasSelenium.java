package com.campgemini;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

class PruebasSelenium {

	 private WebDriver driver;
	    
	    @LocalServerPort
	    private int port;

	    @BeforeEach
	    public void setup() {
	        // Especifica la ubicación de ChromeDriver
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pgarciac\\Desktop\\Proyectos\\Biblioteca\\resource\\chromedriver\\chromedriver.exe");
	        
	        // Configura las opciones del navegador (puedes personalizar estas opciones)
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized"); // Inicia Chrome en modo maximizado

	        // Inicializa el controlador web de Chrome con las opciones
	        driver = new ChromeDriver(options);
	        
	        System.out.println("Ubicación de ChromeDriver: " + System.getProperty("webdriver.chrome.driver"));

	    }

	    // Realiza tus pruebas aquí

	    @AfterEach
	    public void teardown() {
	        // Cierra el navegador al finalizar cada prueba
	        driver.quit();
	    }
}
