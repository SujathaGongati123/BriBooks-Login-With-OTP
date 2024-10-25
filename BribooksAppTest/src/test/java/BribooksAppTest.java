import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.*;

public class BribooksAppTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sujat\\Downloads\\chromedriver-win32 (1)\\chromedriver-win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bribooks.com/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement numInputEl = driver.findElement(By.xpath("//input[@placeholder = 'Phone Number']"));
        numInputEl.sendKeys("8555853245");

        WebElement getOtpBtnEl = driver.findElement(By.xpath("//button[text() = 'Get Otp']"));
        getOtpBtnEl.click();

        System.out.println("Please enter the OTP manually:");
        Scanner scanner = new Scanner(System.in);
        String otp = scanner.nextLine();  // Pausing the script and waiting for manual OTP input

        List<WebElement> otpDigitsEls = driver.findElements(By.xpath("//div[@class='otp']/div/input"));

        for (int i = 0; i < otpDigitsEls.size(); i++) {
            otpDigitsEls.get(i).sendKeys(String.valueOf(otp.charAt(i)));
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String expectedUrl = "https://www.bribooks.com/account/mybooks";

        if(driver.getCurrentUrl().equals(expectedUrl)){
            System.out.println("Login Successfull!");
        }else{
            System.out.println("Login failed.., please check!");
        }

        driver.quit();
    }
}
