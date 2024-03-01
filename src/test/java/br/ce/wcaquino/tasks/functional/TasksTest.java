package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	public WebDriver acessarAplicacao() throws MalformedURLException {
		// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.11:9444/wd/hub"), cap);
		driver.navigate().to("http://192.168.1.11:8080/tasks");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add Todo (o botão)
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!", message);
			
			// fechar o browser
			driver.quit();
		}
		finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add Todo (o botão)
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
			
			// fechar o browser
			driver.quit();
		}
		finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add Todo (o botão)
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
			
			// fechar o browser
			driver.quit();
		}
		finally {
			// fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em Add Todo (o botão)
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
			
			// fechar o browser
			driver.quit();
		}
		finally {
			// fechar o browser
			driver.quit();
		}
	}
}
