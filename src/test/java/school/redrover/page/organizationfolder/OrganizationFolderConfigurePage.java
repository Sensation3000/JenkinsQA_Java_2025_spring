package school.redrover.page.organizationfolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        new Select(getDriver().findElement(By.xpath("(//select[contains(@class, 'dropdownList')])[2]")))
                .selectByVisibleText(iconName);

        return this;
    }
}
