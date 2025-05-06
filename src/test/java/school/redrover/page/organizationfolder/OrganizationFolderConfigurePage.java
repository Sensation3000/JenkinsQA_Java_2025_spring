package school.redrover.page.organizationfolder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.common.BasePage;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> getAvailableIcons() {
        Select select = new Select(getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//select[contains(@class, 'dropdownList')])[2]"))));

        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public OrganizationFolderConfigurePage selectIcon(String iconName) {
        new Select(getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//select[contains(@class, 'dropdownList')])[2]")))).selectByVisibleText(iconName);

        return this;
    }
}
