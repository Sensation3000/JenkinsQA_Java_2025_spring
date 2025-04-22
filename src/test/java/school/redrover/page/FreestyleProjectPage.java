package school.redrover.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.common.ProjectUtils;

import java.time.Duration;
import java.util.List;

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

    public FreestyleProjectPage clickProjectBreadcrumbsDropDownMenu() {
        Actions actions = new Actions(getDriver());

        By arrowSelector = By.cssSelector(".jenkins-breadcrumbs__list-item:nth-child(3) .jenkins-menu-dropdown-chevron");

        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement arrowToHover = getWait5().until(ExpectedConditions.visibilityOfElementLocated(arrowSelector));

                actions.moveToElement(arrowToHover)
                        .pause(Duration.ofSeconds(1))
                        .perform();

                WebElement arrowToClick = getWait5().until(ExpectedConditions.elementToBeClickable(arrowSelector));
                arrowToClick.click();

                return this;
            } catch (StaleElementReferenceException e) {
                attempts++;
                ProjectUtils.log("Retrying due to StaleElementReferenceException, attempt: " + attempts);
            } catch (ElementClickInterceptedException e) {
                attempts++;
                ProjectUtils.log("Retrying due to ElementClickInterceptedException, attempt: " + attempts);
            }
        }

        throw new RuntimeException("Failed to click on breadcrumb arrow due to stale element. Attempts used: " + attempts);
    }

    public String[] getDropDownMenuItemsText() {
        List<WebElement> menuItems =  getWait5()
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".jenkins-dropdown__item")));

        String[] menuItemsText = new String[menuItems.size()];

        for (int i = 0; i < menuItems.size(); i++) {
            menuItemsText[i] = menuItems.get(i).getText();
        }

        return menuItemsText;
    }

    public String[] getMainMenuItemsText() {
        List<WebElement> menuItems =  getDriver().findElements(By.cssSelector(".task span:nth-of-type(2)"));

        // the first element found with the locator above is Status which is not in the drop-down menu
        // (and is not technically a menu item) so we need to reduce size by one
        String[] menuItemsText = new String[menuItems.size() - 1];

        // start with i = 1 since the first element found is Status (which is not a menu item)
        for (int i = 1; i < menuItems.size(); i++) {
            menuItemsText[i - 1] = menuItems.get(i).getText();
        }

        return menuItemsText;
    }
}
