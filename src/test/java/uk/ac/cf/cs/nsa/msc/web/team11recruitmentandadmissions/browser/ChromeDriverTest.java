package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.browser;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("browsertest")
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChromeDriverTest {

    @Value("${local.server.port}")
    private int port;

    WebDriver webDriver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-debugging-port=42227");
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        webDriver.quit();
    }

    @DisplayName("Use the chrome driver to verify that the user is successful after registering")
    @Test
    void verifyThatUserIsSuccessfulAfterRegistering() {
        //access the register page
        webDriver.get(format("http://localhost:%d/register", port));
        //provide a valid user name
        webDriver.findElement(By.name("username")).sendKeys("Fakeuser01");
        //provide a password
        webDriver.findElement(By.name("password")).sendKeys("password");
        //click the signup button
        webDriver.findElement(By.id("signup-btn")).click();
        //assert that the user registration is successful
        assertEquals(format("http://localhost:%d/login", port), webDriver.getCurrentUrl());
    }

    @DisplayName("Use the chrome driver to verify that the user is prompted to provide a valid username" +
            "and prevented from registering wth an invalid name")
    @Test
    public void verifyThatUserIsPromptedForValidUsername() {
        //access the register page
        webDriver.get(format("http://localhost:%d/register", port));
        //provide an invalid username
        webDriver.findElement(By.name("username")).sendKeys("R!chy");
        //provide the password
        webDriver.findElement(By.name("password")).sendKeys("password");
        //click the sign-up button
        webDriver.findElement(By.id("signup-btn")).click();
        //assert the user is prompted for a valid username
        String prompt = webDriver.findElement(By.name("username")).getAttribute("title");
        assertEquals(prompt, "Username must contain between 8 and 20 characters," +
                " with '_' or '.' at the beginning or end");
        //assert that the user is still in the register page
        assertEquals(webDriver.getTitle(), "Register");
        assertEquals(format("http://localhost:%d/register", port), webDriver.getCurrentUrl());
    }


    @DisplayName("Use chrome driver to verify if user logs in successfully")
    @Test
    public void verifyThatUserCanAccessTheWebsiteAppFromLogin() {
        //register a user
        webDriver.get(format("http://localhost:%d/register",port));
        //pass username
        webDriver.findElement(By.name("username")).sendKeys("AliceBane");
        //provide the password
        webDriver.findElement(By.name("password")).sendKeys("that-password");
        //click the 'sign up' button
        webDriver.findElement(By.id("signup-btn")).click();

        //now access the login page to gain access to the candidates page
        webDriver.get(format("http://localhost:%d/login", port));
        //provide username
        webDriver.findElement(By.name("username")).sendKeys("AliceBane");
        //provide password
        webDriver.findElement(By.name("password")).sendKeys("that-password");
        //click the login button
        webDriver.findElement(By.id("login-btn")).click();
        //assert that the user is now in the candidate page
        assertEquals(webDriver.getTitle(), "Candidates");
        assertEquals(format("http://localhost:%d/candidates", port), webDriver.getCurrentUrl());
    }

    @DisplayName("Use the chrome driver to verify that a user is restricted " +
            "from login in to the application with wrong credentials")
    @Test
    public void verifyThatUserCantLoginWithWrongCredential() {
        //access the login page with an invalid username
        webDriver.get(format("http://localhost:%d/login", port));
        //provide a wrong username
        webDriver.findElement(By.name("username")).sendKeys("Bob");
        //provide password
        webDriver.findElement(By.name("password")).sendKeys("password");
        //click the login button
        webDriver.findElement(By.id("login-btn")).click();

        //now access the login page with an invalid password
        webDriver.get(format("http://localhost:%d/login", port));
        //provide a username
        webDriver.findElement(By.name("username")).sendKeys("Admin");
        //provide wrong password
        webDriver.findElement(By.name("password")).sendKeys("p@ssword");
        //click the login button
        webDriver.findElement(By.id("login-btn")).click();
        //assert that the user is flagged for wrong credential
        String message = webDriver.findElement(By.xpath("/html/body/div/div/p")).getText();
        assertEquals(message, "Wrong Username or Password");
        //assert the user is still in the login page
        assertEquals(webDriver.getTitle(), "Login");
        assertEquals(format("http://localhost:%d/login-error", port), webDriver.getCurrentUrl());
    }

    @DisplayName("Verify that user can go to register page from login page")
    @Test
    public void verifyUserCanGoToRegisterFromLogin() {
        //access the login page
        webDriver.get(format("http://localhost:%d/login", port));
        //click the link 'create one!' to go to the register page
        webDriver.findElement(By.xpath("//a[@href='/register']")).click();
        assertEquals(webDriver.getTitle(), "Register");
        assertEquals(webDriver.getCurrentUrl(),format("http://localhost:%d/register",port));
    }

    @DisplayName("Verify that user can go to the login page from the register page")
    @Test
    public void verifyThatUserCanGoToLoginPageFromRegisterPage(){
        //access the register page
        String url = format("http://localhost:%d/register",port);
        webDriver.get(url);
        //click the 'Back to login!' link to go to the login page
        webDriver.findElement(By.xpath("//a[@href='/login']")).click();
        assertEquals(webDriver.getTitle(), "Login");
        assertEquals(webDriver.getCurrentUrl(), format("http://localhost:%d/login",port));
    }
}
