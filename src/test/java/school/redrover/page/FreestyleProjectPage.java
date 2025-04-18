package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;

public class FreestyleProjectPage extends BasePage {

    public FreestyleProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {
        return getDriver().findElement(By.className("page-headline")).getText();
    }

    public String getDescription() {
        return getDriver().findElement(By.cssSelector("#description > div")).getText();
    }

    public FreestyleProjectPage clickEditDescriptionButton() {
        getDriver().findElement(By.id("description-link")).click();

        return this;
    }

    public FreestyleProjectPage sendDescription(String text) {
        getDriver().findElement(By.cssSelector("textarea[name='description']")).clear();
        getDriver().findElement(By.cssSelector("textarea[name='description']")).sendKeys(text);

        return this;
    }

    public FreestyleProjectPage clickSave() {
        getDriver().findElement(By.name("Submit")).click();

        return this;
    }

    public FreestyleProjectPage clickLeftSideMenuRename() {
        getDriver().findElement(By.xpath("//span[text()='Rename']/..")).click();

        return this;
    }

    public FreestyleProjectPage sendName(String text) {
        getDriver().findElement(By.name("newName")).clear();
        getDriver().findElement(By.name("newName")).sendKeys(text);

        return this;
    }

    public FreestyleProjectPage clickRename() {
        getDriver().findElement(By.name("Submit")).click();

        return this;
    }

    public FreestyleProjectPage clickLeftSideMenuDelete() {
        getDriver().findElement(By.xpath("//span[text()='Delete Project']")).click();

        return this;
    }

    public HomePage clickPopUpYesDeleteProject() {
        getDriver().findElement(By.cssSelector("[data-id='ok']")).click();

        return new HomePage(getDriver());
    }
}