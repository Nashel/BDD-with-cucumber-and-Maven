package StepDefinitions;


import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class GoogleSearchSteps {

	WebDriver driver = null;
	
	@Given("browser is open")
	public void browser_is_open() {
	    System.out.println("Inside Step - browser is open");
	    
	    String projectPath = System.getProperty("user.dir");
	    System.out.println("Project path is: "+ projectPath);
	    
	    System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe");
	    
	    driver = new ChromeDriver();
	    
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@And("user is on google search page")
	public void user_is_on_google_search_page() {
		System.out.println("Inside Step - user is on google search page");
		
		driver.navigate().to("https://google.com");
	}
	
	@And("user accepts terms")
	public void user_accept_terms() {
		driver.findElement(By.xpath("//div[contains(text(), 'Acepto')  and @class=\"QS5gu sy4vM\"]")).click();		// For Spanish
		//driver.findElement(By.xpath("//div[contains(text(), 'Accept')  and @class=\"QS5gu sy4vM\"]")).click();	// For English
	}

	@When("user enters a text in search box {string}")
	public void user_enters_a_text_in_search_box(String user) throws InterruptedException {
		System.out.println("Inside Step - user enters a text in search box");
		
		driver.findElement(By.name("q")).sendKeys("github nashel " + user);
		
		Thread.sleep(2000);
	}

	@And("hits enters")
	public void hits_enters() throws InterruptedException {
		System.out.println("Inside Step - hits enters");
		
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
	}

	@Then("user is navigated to search results")
	public void user_is_navigated_to_search_results() {
		System.out.println("Inside Step - user is navigated to search results");
		
		driver.getPageSource().contains("Online Courses");
	}
	
	@And("user clicks on page")
	public void user_hits_link() throws InterruptedException {
		System.out.println("Inside Step - click on github page");
		
		driver.findElement(By.xpath("//h3[contains(text(), 'Nashel (Jordi Campoy) · GitHub')  and @class=\"LC20lb MBeuO DKV0Md\"]")).click();
		
		Thread.sleep(2000);
	}
	
	@Then("the user showed should be {string}")
	public void check_user(String expectedUser) {
		System.out.println("Inside Step - checking user");
		
		String username = driver.findElement(By.xpath("//span[@class='p-name vcard-fullname d-block overflow-hidden' and @itemprop='name']")).getText();

		assertEquals(expectedUser, username);
	}
	
	@Then("close navigator")
	public void close_navigator() {
		System.out.println("Inside Step - closing navigator");
		
		driver.close();
		driver.quit();
	}
}
