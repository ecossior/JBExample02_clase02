package com.jbgroup.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CategoriInsertarTest {
	
	WebDriver driver;
	
	@BeforeAll
	static void setupDriverMangement() {
		WebDriverManager.chromedriver().setup();
		
	}
	@BeforeEach
	void instanciarDriver() {
		driver = new ChromeDriver();
		
	}
	
	@Test
	void registrarCategoriaExitodaTest() throws InterruptedException {
		driver.get("http://localhost:8080/VisorWeb/index.xhtml");
		WebElement userTxt = driver.findElement(By.id("txtUsuario"));
		userTxt.clear();
		userTxt.sendKeys("admin");
		WebElement pwdTxt = driver.findElement(By.id("txtClave"));
		pwdTxt.clear();
		pwdTxt.sendKeys("clave");
		WebElement sesionBtn = driver.findElement(By.name("btnIniciarSesion"));
		sesionBtn.click();
		
		Thread.sleep(2000);		
		String expecTitle ="Bienvenido(a) al Sistema Visor de Almacén";
		WebElement actualTitle = driver.findElement(By.xpath("//div[@class='title-main']"));		
		Assertions.assertEquals(expecTitle, actualTitle.getText());
		
		
		Thread.sleep(2000);
		WebElement menu = driver.findElement(By.xpath("//div[@class='menu']/div"));
		menu.click();
		
		Thread.sleep(2000);
		WebElement menuModAlmacen = driver.findElement(By.xpath("//span[contains(.,'Mod. de Almacén')]"));
		menuModAlmacen.click();
		
		
		Thread.sleep(2000);
		WebElement menuCategoria = driver.findElement(By.xpath("//a[.='Mnt. de Categoría']"));
		menuCategoria.click();
		
		Thread.sleep(5000);
		WebElement nuevoBtn = driver.findElement(By.name("btnNuevo"));
		nuevoBtn.click();
		
		Thread.sleep(2000);
		WebElement nombreTxt = driver.findElement(By.id("txtNombre"));
		nombreTxt.sendKeys("HELADO DE CHOCOLATES");
		
		
		Thread.sleep(2000);
		WebElement guardarBtn = driver.findElement(By.name("btnGuardar"));
		guardarBtn.click();
		
		
		//way 1
		Thread.sleep(2000);
		WebElement infoIcon = driver.findElement(By.className("ui-messages-info-summary"));
		String actualMesasge =infoIcon.getText();
		String expecMessage = "Se guardó de manera correcta la Categoría";		
		System.out.println("actual label :" + actualMesasge);		
		Assertions.assertEquals(expecMessage, actualMesasge);
		
		
		//way 2
		Thread.sleep(2000);
		WebElement infoMessage = driver.findElement(By.xpath("//span[.='Se guardó de manera correcta la Categoría']"));
		Boolean resp =infoMessage.isDisplayed();				
		System.out.println("actual label :" + resp);		
		Assertions.assertTrue(resp);
	}
	
	
	@Test
	void registrarCategoriaConDatosVaciosTest() throws InterruptedException {
		
		driver.get("http://localhost:8080/VisorWeb/index.xhtml");
		WebElement userTxt = driver.findElement(By.id("txtUsuario"));
		userTxt.clear();
		userTxt.sendKeys("admin");
		WebElement pwdTxt = driver.findElement(By.id("txtClave"));
		pwdTxt.clear();
		pwdTxt.sendKeys("clave");
		WebElement sesionBtn = driver.findElement(By.name("btnIniciarSesion"));
		sesionBtn.click();
		
		Thread.sleep(2000);		
		String expecTitle ="Bienvenido(a) al Sistema Visor de Almacén";
		WebElement actualTitle = driver.findElement(By.xpath("//div[@class='title-main']"));		
		Assertions.assertEquals(expecTitle, actualTitle.getText());
		
		
		Thread.sleep(2000);
		WebElement menu = driver.findElement(By.xpath("//div[@class='menu']/div"));
		menu.click();
		
		Thread.sleep(2000);
		WebElement menuModAlmacen = driver.findElement(By.xpath("//span[contains(.,'Mod. de Almacén')]"));
		menuModAlmacen.click();
		
		
		Thread.sleep(2000);
		WebElement menuCategoria = driver.findElement(By.xpath("//a[.='Mnt. de Categoría']"));
		menuCategoria.click();
		
		Thread.sleep(5000);
		WebElement nuevoBtn = driver.findElement(By.name("btnNuevo"));
		nuevoBtn.click();
		
		Thread.sleep(2000);
		WebElement nombreTxt = driver.findElement(By.id("txtNombre"));
		nombreTxt.sendKeys("");
		
		
		Thread.sleep(2000);
		WebElement guardarBtn = driver.findElement(By.name("btnGuardar"));
		guardarBtn.click();
		
		
		//way 1
		Thread.sleep(2000);
		WebElement infoIcon = driver.findElement(By.className("ui-messages-error-summary"));
		String actualMesasge =infoIcon.getText();
		String expecMessage = "Nombre: Validation Error: Value is required.";		
		System.out.println("actual label :" + actualMesasge);		
		Assertions.assertEquals(expecMessage, actualMesasge);
		
		
		//way 2
		Thread.sleep(2000);
		WebElement infoMessage = driver.findElement(By.xpath("//span[.='Nombre: Validation Error: Value is required.']"));
		Boolean resp =infoMessage.isDisplayed();				
		System.out.println("actual label :" + resp);		
		Assertions.assertTrue(resp);		
	}
	@AfterEach
	void cerrarDriver(){
		driver.quit();
	}

}
