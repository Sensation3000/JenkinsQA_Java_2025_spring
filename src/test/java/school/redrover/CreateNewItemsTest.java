package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
public class CreateNewItemsTest extends BaseTest {
    private String name;
    public String getName() {
        return name;
    }
    public void setNewItemName (String name) {
        this.name = name;
        getDriver().findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(getName());
    }
    public void clickNewItemButton() {
        getDriver().findElement(By.linkText("New Item")).click();
    }
    public void selectFreestyleProject() {
        getDriver().findElement(By.xpath("//*[text()='Freestyle project']")).click();
    }
    public void selectPipeline() {
        getDriver().findElement(By.xpath("//*[text()='Pipeline']")).click();
    }
    public void selectMultiConfigurationProject() {
        getDriver().findElement(By.xpath("//*[text()='Multi-configuration project']")).click();
    }
    public void selectFolder() {
        getDriver().findElement(By.xpath("//*[text()='Folder']")).click();
    }
    public void selectMultiBranchPipeline() {
        getDriver().findElement(By.xpath("//*[text()='Multibranch Pipeline']")).click();
    }
    public void selectOrganizationFolder() {
        getDriver().findElement(By.xpath("//*[text()='Organization Folder']")).click();
    }
    public void clickOkButton() {
        getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]")).click();
    }
    public void openDashboard() {
        getDriver().findElement(By.xpath("//*[@id=\"jenkins-home-link\"]")).click();
    }
    public void verifyNewItemCreated() {
        Assert.assertTrue(getDriver().findElement(By.xpath("//*[text()='" + getName() + "']")).isDisplayed());
    }

    @Test
    public void testCreateFreestyleProject() throws InterruptedException {
        clickNewItemButton();
        setNewItemName("Freestyle Project");
        selectFreestyleProject();
        clickOkButton();
        Thread.sleep(2000);
        openDashboard();
        verifyNewItemCreated();
    }
    @Test
    public void testCreatePipeline() throws InterruptedException {
        clickNewItemButton();
        setNewItemName("Pipeline");
        selectPipeline();
        clickOkButton();
        Thread.sleep(2000);
        openDashboard();
        verifyNewItemCreated();
    }
    @Test
    public void testCreateMultiConfigurationProject() throws InterruptedException {
        clickNewItemButton();
        setNewItemName("MultiConfigurationProject");
        selectMultiConfigurationProject();
        clickOkButton();
        Thread.sleep(2000);
        openDashboard();
        verifyNewItemCreated();
    }
    @Test
    public void testCreateFolder() throws InterruptedException {
        clickNewItemButton();
        setNewItemName("New Folder");
        selectFolder();
        clickOkButton();
        Thread.sleep(2000);
        openDashboard();
        verifyNewItemCreated();
    }
    @Test
    public void testCreateMultiBranchPipeline() throws InterruptedException {
        clickNewItemButton();
        setNewItemName("MultiBranchPipeline");
        selectMultiBranchPipeline();
        clickOkButton();
        Thread.sleep(2000);
        openDashboard();
        verifyNewItemCreated();
    }
    @Test
    public void testCreateOrganizationFolder() throws InterruptedException {
        clickNewItemButton();
        setNewItemName("Organization Folder");
        selectOrganizationFolder();
        clickOkButton();
        Thread.sleep(2000);
        openDashboard();
        verifyNewItemCreated();
    }
}

