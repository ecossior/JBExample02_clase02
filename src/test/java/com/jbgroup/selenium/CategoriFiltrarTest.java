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

public class CategoriFiltrarTest {
	
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
	void buscarCategoriasSinFiltroTest() throws InterruptedException {
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
				
		Thread.sleep(2000);
		WebElement filtrarBtn = driver.findElement(By.name("btnFiltrar"));
		filtrarBtn.click();
		
		Thread.sleep(2000);
		List<WebElement> filas = driver.findElements(By.xpath("//tbody[@id='tablaCategorias_data']/tr"));
		Integer num = filas.size();		
		Assertions.assertTrue(num>1);		
		
		String header= "Code               Name";
		System.out.println(header);
		
		for (WebElement webElement : filas) {
			List<WebElement> celdas = webElement.findElements(By.tagName("td"));
			String code = celdas.get(0).getText();
			String name =celdas.get(1).getText();
			
			String s = String.format("%s                  %s", code, name);
			System.out.println(s);
		}
	}
	
	
	@Test
	void buscarCategoriasConFiltroTest() throws InterruptedException {
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
		
		Thread.sleep(2000);
		String itemToSearch = "GALLETAS";
		WebElement filtrarTxt = driver.findElement(By.id("txtFiltro"));
		filtrarTxt.sendKeys(itemToSearch);
		
		Thread.sleep(2000);
		WebElement filtrarBtn = driver.findElement(By.name("btnFiltrar"));
		filtrarBtn.click();
		
		Thread.sleep(2000);
		String locator = String.format("//tbody[@id='tablaCategorias_data']/tr/td[contains(.,'%s')]", itemToSearch);
		WebElement cell = driver.findElement(By.xpath(locator));
		String expecName = cell.getText();		
		Assertions.assertEquals(itemToSearch, expecName);
	}
	
	
	@Test
	void buscarCategoriasInexistenteFiltroTest() throws InterruptedException {
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
		
		Thread.sleep(2000);
		String itemToSearch = "PANETON";
		WebElement filtrarTxt = driver.findElement(By.id("txtFiltro"));
		filtrarTxt.sendKeys(itemToSearch);
		
		Thread.sleep(5000);
		WebElement filtrarBtn = driver.findElement(By.name("btnFiltrar"));
		filtrarBtn.click();
		
		Thread.sleep(2000);
		WebElement message = driver.findElement(By.xpath("//tbody[@id='tablaCategorias_data']/tr/td[contains(.,'No existe registros para la consulta')]"));
		Boolean resp2 = message.isDisplayed();
		Assertions.assertTrue(resp2);

	}
	
	@AfterEach
	void cerrarDriver(){
		driver.quit();
	}

}
