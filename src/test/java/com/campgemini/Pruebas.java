package com.campgemini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.ImmutableList;

public class Pruebas {

	private EdgeDriver driver;

    @Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pgarciac\\Desktop\\Proyectos\\Biblioteca\\resource\\edgedriver\\msedgedriver.exe");
		this.driver = new EdgeDriver();
		this.driver.manage().window().maximize();
		this.driver.get("http://localhost:8080");
	}

	@After
	public void tearDown() {
		driver.quit();
	}

    
    //@Test
	public void testUsuarioPrestaryDevolver(){
    	this.driver = new EdgeDriver();
    	this.driver.get("http://localhost:8080");
    	
    	this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	this.driver.manage().window().maximize();
    	
    	WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));

        usernameField.sendKeys("pedro");
        passwordField.sendKeys("123");
		
        loginButton.click();

        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement boton = driver.findElement(By.id("botonPrestar"));
        assertTrue(boton.isEnabled());
        
        WebElement prestarButton = driver.findElement(By.xpath("//button[contains(text(), 'Prestar')]"));
        prestarButton.click();

        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        
        WebElement devolverButton = driver.findElement(By.xpath("//button[contains(text(), 'Devolver')]"));
        devolverButton.click();
        
        driver.quit();
	}
    
    //@Test
	public void testLoginLogout(){
    	this.driver = new EdgeDriver();
    	this.driver.get("http://localhost:8080");
    	
    	this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	this.driver.manage().window().maximize();
    	
    	WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));

        usernameField.sendKeys("pedro");
        passwordField.sendKeys("123");
		
        loginButton.click();

        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement logoutButton = driver.findElement(By.id("logoutButton"));
        logoutButton.click();
        
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String expectedLoginURL = "http://localhost:8080/login";
        String currentURL = driver.getCurrentUrl();
        assertEquals(expectedLoginURL, currentURL);
        
        driver.quit();
	}
    
    @Test
	public void testAdminCrearLibro(){
    	this.driver = new EdgeDriver();
    	this.driver.get("http://localhost:8080");
    	
    	this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	this.driver.manage().window().maximize();
    	
    	WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));

        usernameField.sendKeys("admin");
        passwordField.sendKeys("1234");
		
        loginButton.click();

        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement boton = driver.findElement(By.id("añadirLibro"));
        assertTrue(boton.isEnabled());
        
        boton.click();

        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        
        String expectedLoginURL = "http://localhost:8080/add/libro";
        String currentURL = driver.getCurrentUrl();
        assertEquals(expectedLoginURL, currentURL);
        
        WebElement tituloInput = driver.findElement(By.name("titulo"));
        tituloInput.sendKeys("Selenium");

        WebElement editorialInput = driver.findElement(By.name("editorial"));
        editorialInput.sendKeys("Editorial 1");

        WebElement anyoInput = driver.findElement(By.name("anyo"));
        anyoInput.sendKeys("2023");

        WebElement nombreAutorInput = driver.findElement(By.name("autor.nombre"));
        nombreAutorInput.sendKeys("Pedro");

        WebElement nacionalidadAutorInput = driver.findElement(By.name("autor.nacionalidad"));
        nacionalidadAutorInput.sendKeys("España");

        WebElement fechaNacimientoAutorInput = driver.findElement(By.name("autor.fechaNacimiento"));
        fechaNacimientoAutorInput.sendKeys("1990-01-01");

        List<WebElement> botonesDeRadio = driver.findElements(By.cssSelector("input[type='radio']"));

        for (WebElement boton2 : botonesDeRadio) {
        	String valorDelBoton = boton2.getAttribute("value");
        	if (valorDelBoton.equals("Novela")) {
        		boton2.click();
        		break;
        	}
        }

        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        
        WebElement botonGuardar = driver.findElement(By.id("guardarLibro"));
        assertTrue(botonGuardar.isEnabled());
        
        botonGuardar.click();
        
        expectedLoginURL = "http://localhost:8080/";
        currentURL = driver.getCurrentUrl();
        assertEquals(expectedLoginURL, currentURL);
        
        String tituloLibroAgregado = "Selenium"; 
        WebElement tablaLibros = driver.findElement(By.cssSelector("table.table-striped"));
        boolean libroEncontrado = tablaLibros.getText().contains(tituloLibroAgregado);

        assertTrue(libroEncontrado);
        
        driver.quit();
	}
    
    @Test
	public void testAdminEliminarLibro(){
    	this.driver = new EdgeDriver();
    	this.driver.get("http://localhost:8080");
    	
    	this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	this.driver.manage().window().maximize();
    	
    	WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));

        usernameField.sendKeys("admin");
        passwordField.sendKeys("1234");
		
        loginButton.click();

        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement filaLibroSelenium = driver.findElement(By.xpath("//td[text()='Selenium']/ancestor::tr"));

        // Busca el botón "Eliminar Libro" en la fila
        WebElement botonEliminar = filaLibroSelenium.findElement(By.linkText("Eliminar Libro"));

        // Haz clic en el botón "Eliminar Libro"
        botonEliminar.click();
        
       
        
        driver.quit();
	}
}
