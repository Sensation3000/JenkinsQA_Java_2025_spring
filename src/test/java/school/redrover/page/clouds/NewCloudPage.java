package school.redrover.page.clouds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class NewCloudPage extends BasePage {

    public NewCloudPage(WebDriver driver) {super(driver);}

    public NewCloudPage typeCloudName(String name) {
        getDriver().findElement(By.id("name")).sendKeys(name);

        return this;
    }

    public NewCloudPage selectCloudType() {
        getDriver().findElement(By.cssSelector(".jenkins-radio__label")).click();

        return this;
    }

    public NewCloudPage clickCreateButton() {
        getDriver().findElement(By.id("ok")).click();

        return this;
    }

    public NewCloudPage clickSaveButton() {
        getDriver().findElement(By.cssSelector(".jenkins-submit-button")).click();

        return this;
    }

    public String getCreatedCloudName() {

        return getWait10()
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/manage/cloud/CloudCreationTestName/']")))
                .getText();
    }
}
