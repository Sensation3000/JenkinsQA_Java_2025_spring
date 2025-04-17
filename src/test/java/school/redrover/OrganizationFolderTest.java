package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class OrganizationFolderTest extends BaseTest {

    private static final String ORGANIZATION_FOLDER_NAME = "Organization_Folder2";

    @Test
    public void testCreateOrganizationFolderWithDefaultIcon() {
        getDriver().findElement(By.cssSelector("[href$='/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(ORGANIZATION_FOLDER_NAME);

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        getDriver().findElement(By.cssSelector("[class$='OrganizationFolder']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        JavascriptExecutor js2 = (JavascriptExecutor) getDriver();
        js2.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        new Select(getDriver().findElement(By.xpath("(//select[contains(@class, 'dropdownList')])[2]")))
                .selectByVisibleText("Default Icon");

        getDriver().findElement(By.name("Submit")).click();

        String organizationFolderCurrentIcon = getDriver().findElement(By.cssSelector("h1 > svg")).getAttribute("title");
        Assert.assertEquals(organizationFolderCurrentIcon, "Folder");
    }

    @Test (dependsOnMethods = "testCreateOrganizationFolderWithDefaultIcon")
    public void testCancelFolderDeletion(){
        getDriver().findElement(By.xpath("//td/a[@href='job/" + ORGANIZATION_FOLDER_NAME + "/']")).click();
        getDriver().findElement(By.xpath("//a[@data-title='Delete Organization Folder']")).click();
        getDriver().findElement(By.xpath("//button[@data-id='cancel']")).click();

        Assert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOf(
                        getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")))).getText(), ORGANIZATION_FOLDER_NAME);
    }
}
