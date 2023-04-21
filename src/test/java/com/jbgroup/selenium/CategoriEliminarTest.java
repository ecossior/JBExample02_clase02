package com.jbgroup.selenium;

import java.util.List;

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

public class CategoriEliminarTest {
	
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
	void eliminarCategoriaTest() throws InterruptedException {
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
		
		/*
		Thread.sleep(2000);
		String itemToSearch = "GALLETAS";
		WebElement filtrarTxt = driver.findElement(By.id("txtFiltro"));
		filtrarTxt.clear();
		filtrarTxt.sendKeys(itemToSearch);
		*/
		
		WebElement filtrarBtn = driver.findElement(By.name("btnFiltrar"));
		filtrarBtn.click();
		
		Thread.sleep(2000);
		WebElement ultimaFila =driver.findElement(By.xpath("//tbody[@id='tablaCategorias_data']/tr[last()]"));
		ultimaFila.click();
						
		WebElement elimiarBtn = driver.findElement(By.id("btnEliminar"));
		elimiarBtn.click();
		
		Thread.sleep(2000);
		WebElement siBtn = driver.findElement(By.id("btnSi"));
		siBtn.click();

		
		Thread.sleep(2000);
		String valorEsperado = "Se eliminó de manera correcta la Categoría";
		String valorObtenido = driver.findElement(By.xpath("//span[@class='ui-messages-info-summary']")).getText();
		Assertions.assertEquals(valorEsperado, valorObtenido);		
	}
	
	
	@Test
	void eliminarCategoriaSinSeleccionarRegistroTest() throws InterruptedException {
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
		
		WebElement elimiarBtn = driver.findElement(By.id("btnEliminar"));
		elimiarBtn.click();
		
		Thread.sleep(2000);
		WebElement siBtn = driver.findElement(By.id("btnSi"));
		siBtn.click();
		
		Thread.sleep(2000);
		String valorEsperado = "No ha seleccionado una Categoría"; 
		String valorObtenido = driver.findElement(By.xpath("//span[@class='ui-messages-warn-summary']")).getText();
		Assertions.assertEquals(valorEsperado, valorObtenido);		
	}
	
	
	
	
	@AfterEach
	void cerrarDriver(){
		driver.quit();
	}

}
