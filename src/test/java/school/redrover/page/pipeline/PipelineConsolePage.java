package school.redrover.page.pipeline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class PipelineConsolePage extends BasePage {

    public PipelineConsolePage(WebDriver driver) {
        super(driver);
    }

    public String getConsoleOutput() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'log-card--header')]"))).getText();
    }
}
