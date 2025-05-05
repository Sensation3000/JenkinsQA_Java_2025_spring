package school.redrover.page.organizationfolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.common.BasePage;

public class OrganizationFolderConfigurePage extends BasePage {
    public OrganizationFolderConfigurePage(WebDriver driver) { super(driver); }

    public OrganizationFolderPage clickSave() {
        getDriver().findElement(By.cssSelector("button[name='Submit']")).click();

        return new OrganizationFolderPage(getDriver());
    }

    public OrganizationFolderConfigurePage clickAppearance() {
        getDriver().findElement(By.xpath("//button[@data-section-id='appearance']")).click();

        return this;
    }

    public OrganizationFolderConfigurePage selectIcon(String iconName) {
        getWait10().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector("select.jenkins-select__input.dropdownList"))));
        Select select = new Select(getDriver().findElement(By.cssSelector("select.jenkins-select__input.dropdownList")));
        select.selectByValue(iconName);

        return this;
    }
}
