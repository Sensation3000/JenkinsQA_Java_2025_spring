package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class OrganisationFolderTest extends BaseTest {

    @Test
    public void testCreateOrganizationFolderWithDefaultIcon() {
        getDriver().findElement(By.cssSelector("[href$='/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Organization_Folder");

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
}
