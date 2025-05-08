package school.redrover.page.aboutjenkins;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

import java.util.List;

public class AboutJenkinsPage extends BasePage {

    @FindBy(xpath = "//div[@class='app-about-branding__aurora']/following-sibling::img")
    private WebElement logoImg;

    @FindBy(className = "app-about-version")
    private WebElement version;

    public AboutJenkinsPage(WebDriver driver) {super(driver);}

    public String getTextInAltLogoImg() {
        return logoImg.getAttribute("alt");
    }
    public String getCurrentVersion() {
        return version.getText();
    }
    public List<String> getMavenizedDependenciesList() {
        return getDriver().findElements(By.xpath("//h2[text()='Mavenized dependencies']/following-sibling::table/tbody")).stream()
                .map(WebElement::getText).toList();
    }
}
