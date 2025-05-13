package school.redrover.page.view;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.page.HomePage;

public class EditViewPage extends BasePage {

    @FindBy(name = "_.description")
    private WebElement textAreaDescription;

    public EditViewPage(WebDriver driver) {
        super(driver);
    }
    public JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

    public String getName() {
        return getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//*[@id=\"main-panel\"]/form/div[1]/section[1]/div[1]/div[2]/input"))).getText();
    }

    public EditViewPage fillDescription(String description) {
        getWait5().until(ExpectedConditions.visibilityOf(textAreaDescription)).sendKeys(description);

        return this;
    }

    public EditViewPage JobsCheckTestItem(String jobName) {
        findElementContainsTextAndClick("label", jobName, this.getClass());

        return this;
    }

    public EditViewPage clickAddJobFilter() {
        findElementContainsTextAndClick("button", "Add Job Filter", EditViewPage.class);

        return this;
    }

    public EditViewPage clickStatusFilterOfJobFilter() {
        findElementContainsTextAndClick("button","Status Filter", EditViewPage.class);

        return this;
    }

    public EditViewPage clickAddColumn() {
       findElementContainsTextAndClick("button", "Add column", EditViewPage.class);

       return this;
    }

    public EditViewPage checkProjectDescriptionColumn() {
        findElementContainsTextAndClick("button", "\n" +
                "          \n" +
                "          Project description\n" +
                "                    \n" +
                "          \n" +
                "      ", EditViewPage.class);

        return this;
    }

    public HomePage clickSaveButton() {
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.name("Submit"))).click();

        return new HomePage(getDriver());
    }

    private <T extends BasePage> void findElementContainsTextAndClick(String element, String text, Class<T> clazz) {
        WebElement el = getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//" + element + "[contains(text(),'" + text + "')]")));
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", el);
        getWait10().until(ExpectedConditions.elementToBeClickable(el));
        jsExecutor.executeScript("arguments[0].click();", el);

        try {
            clazz.getDeclaredConstructor(WebDriver.class).newInstance(getDriver());
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate page object: " + clazz.getName(), e);
        }
    }

}
