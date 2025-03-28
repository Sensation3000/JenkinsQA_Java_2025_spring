package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class QA2025Test  extends BaseTest {
    @Test
    public void testAddDescriptionLink() {
        WebDriver driver = getDriver();

        WebElement addDescription = driver.findElement(By.xpath("//div/a[@class=\"jenkins-button\"]"));
        Assert.assertEquals(addDescription.getText(), "Add description");
    }
    @Test
    public void testAddDescriptionLinkAvailable() {

        WebElement addDescription = getDriver().findElement(By.xpath("//div/a[@class=\"jenkins-button\"]"));
        Assert.assertTrue(addDescription.isEnabled());
    }
}