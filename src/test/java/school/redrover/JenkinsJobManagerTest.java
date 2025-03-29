package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;


public class JenkinsJobManagerTest extends BaseTest {

    private static final String HOME_PAGE = "http://localhost:8080/";
    private static final String BUTTON_CREATE_JOB = "//a[@href='newJob']";
    private static final String BUTTON_SUBMIT = "//button[@name='Submit']";
    private static final String BUTTON_OK = "//button[@id='ok-button']";

    @Test
    public void testAddDescription() {

        WebElement addDescription = getDriver().findElement(By.xpath("//a[@id='description-link']"));
        addDescription.click();

        WebElement textArea = getDriver().findElement(By.xpath("//textarea[@name='description']"));
        textArea.sendKeys("Hello");

        getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']")).click();

        WebElement actualTextElement = getDriver().findElement(By
                .xpath("//*[@id='description']/div[1]"));
        String actualText = actualTextElement.getText();

        Assert.assertEquals(actualText, "Hello");

    }

    @Ignore
    @Test
    public void testCreateFreestyleProject() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        WebElement clickCreateJob = getDriver().findElement(By.xpath(BUTTON_CREATE_JOB));
        clickCreateJob.click();

        WebElement sendText = getDriver().findElement(By.xpath("//input[@class='jenkins-input']"));
        sendText.sendKeys("Test Freestyle Project");
        getDriver().findElement(By.xpath("//span[text()='Freestyle project']")).click();
        getDriver().findElement(By.xpath(BUTTON_OK)).click();
        getDriver().findElement(By.xpath(BUTTON_SUBMIT)).click();

        getDriver().get(HOME_PAGE);

        WebElement actualNameJobElement = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href='job/Test%20Freestyle%20Project/']")));
        String actualTextJob = actualNameJobElement.getText();

        Assert.assertEquals(actualTextJob, "Test Freestyle Project");

    }
    @Ignore
    @Test
    public void testCreatePipelineProject() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        WebElement clickCreateJob = getDriver().findElement(By.xpath(BUTTON_CREATE_JOB));
        clickCreateJob.click();
        WebElement sendText = getDriver().findElement(By.xpath("//input[@class='jenkins-input']"));
        sendText.sendKeys("Test Pipeline");
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.xpath(BUTTON_OK)).click();
        getDriver().findElement(By.xpath(BUTTON_SUBMIT)).click();

        getDriver().get(HOME_PAGE);

        WebElement actualNameJobElement = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@href='job/Test%20Pipeline/']")));
        String actualTextJob = actualNameJobElement.getText();

        Assert.assertEquals(actualTextJob, "Test Pipeline");

    }
}