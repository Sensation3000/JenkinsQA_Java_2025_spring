package school.redrover.page.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.HomePage;

public class NewViewPage extends BasePage {

    public NewViewPage(WebDriver driver) {
        super(driver);
    }

    public NewViewPage addName(String name) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(name);

        return this;
    }

    public NewViewPage clickListView() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("label[for='hudson.model.ListView']"))).click();

        return this;
    }

    public NewViewPage clickMyView() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("label[for='hudson.model.MyView']"))).click();

            return this;
    }

    public BasePage clickCreateButton() {
        BasePage nextPage = getDriver().findElement(By.id("hudson.model.ListView")).isSelected()
                ? new EditViewPage(getDriver())
                : new HomePage(getDriver());

        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok"))).click();
        return nextPage;
    }
}
