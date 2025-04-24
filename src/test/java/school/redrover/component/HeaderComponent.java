package school.redrover.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BaseComponent;
import school.redrover.page.HomePage;

public class HeaderComponent extends BaseComponent {

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogoIcon() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("general")));
        getDriver().findElement(By.id("jenkins-home-link")).click();

        return new HomePage(getDriver());
    }
}
