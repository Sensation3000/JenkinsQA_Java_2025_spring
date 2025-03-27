package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewItem2Test extends BaseTest {
    private final String ITEM_NAME = "Some name for item";

    @Test
    public void testNotCreateFreestyleProjectWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Freestyle project']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testNotCreatePipelineItemWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Pipeline']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testNotCreateMultiСonfigurationItemWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Multi-configuration project']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testNotCreateFolderItemWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Folder']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testNotCreateMultibranchPipelineItemWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Multibranch Pipeline']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testNotCreateOrganizationFolderItemWithoutName() {
        getDriver().findElement(By.cssSelector("div section:nth-of-type(1) ul")).click();
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Organization Folder']/../../..")).click();

        WebElement errorMessage = getDriver().findElement(By.cssSelector("#itemname-required"));
        WebElement okButton = getDriver().findElement(By.cssSelector("#ok-button"));

        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "» This field cannot be empty, please enter a valid name");
        Assert.assertFalse(okButton.isEnabled());
    }

    @Test
    public void testCreateFreestyleProjectItem() {
        getDriver().findElement(By.xpath("//span[@class='task-link-text' and text()='New Item']/ancestor::span[@class='task-link-wrapper ']")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input")).sendKeys(ITEM_NAME);
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Freestyle project']/ancestor::li")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.cssSelector("#jenkins-home-link")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//td/a/span")).getText(), ITEM_NAME);
    }

    @Test
    public void testCreatePipelineItem() {
        getDriver().findElement(By.xpath("//span[@class='task-link-text' and text()='New Item']/ancestor::span[@class='task-link-wrapper ']")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input")).sendKeys(ITEM_NAME);
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Pipeline']/ancestor::li")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.cssSelector("#jenkins-home-link")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//td/a/span")).getText(), ITEM_NAME);
    }

    @Test
    public void testCreateMultiConfigurationItem() {
        getDriver().findElement(By.xpath("//span[@class='task-link-text' and text()='New Item']/ancestor::span[@class='task-link-wrapper ']")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input")).sendKeys(ITEM_NAME);
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Multi-configuration project']/ancestor::li")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.cssSelector("#jenkins-home-link")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//td/a/span")).getText(), ITEM_NAME);
    }

    @Test
    public void testCreateFolderItem() {
        getDriver().findElement(By.xpath("//span[@class='task-link-text' and text()='New Item']/ancestor::span[@class='task-link-wrapper ']")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input")).sendKeys(ITEM_NAME);
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Folder']/ancestor::li")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.cssSelector("#jenkins-home-link")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//td/a/span")).getText(), ITEM_NAME);
    }

    @Test
    public void testCreateMultibranchPipelineItem() {
        getDriver().findElement(By.xpath("//span[@class='task-link-text' and text()='New Item']/ancestor::span[@class='task-link-wrapper ']")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input")).sendKeys(ITEM_NAME);
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Multibranch Pipeline']/ancestor::li")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.cssSelector("#jenkins-home-link")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//td/a/span")).getText(), ITEM_NAME);
    }

    @Test
    public void testCreateOrganizationFolderItem() {
        getDriver().findElement(By.xpath("//span[@class='task-link-text' and text()='New Item']/ancestor::span[@class='task-link-wrapper ']")).click();
        getDriver().findElement(By.cssSelector(".jenkins-input")).sendKeys(ITEM_NAME);
        getDriver().findElement(By.xpath("//span[@class='label' and text()='Organization Folder']/ancestor::li")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();
        getDriver().findElement(By.cssSelector("#jenkins-home-link")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//td/a/span")).getText(), ITEM_NAME);
    }
}
