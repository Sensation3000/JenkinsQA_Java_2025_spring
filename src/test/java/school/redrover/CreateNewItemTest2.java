package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class CreateNewItemTest2 extends BaseTest {
    @Test
    public void testNotCreateNewFreestyleProjectWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Freestyle project']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testNotCreateNewPipelineItemWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Pipeline']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testNotCreateNewMultiСonfigurationItemWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Multi-configuration project']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testNotCreateNewFolderItemWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Folder']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testNotCreateNewMultibranchPipelineItemWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Multibranch Pipeline']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testNotCreateNewOrganizationFolderItemWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Organization Folder']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }
}
