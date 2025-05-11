package school.redrover.page.multibranch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class MultibranchPipelineLogScanningPage extends BasePage {

    @FindBy(tagName = "pre")
    private WebElement preElement;

    public MultibranchPipelineLogScanningPage(WebDriver driver)  {
        super(driver);
    }

    public boolean isSuccessSubstringAppeared() {
        try {
            getWait10().until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("pre a"), 6));
            getWait10().until(ExpectedConditions.textToBePresentInElement(preElement, "Finished"));

            return preElement.getText().contains("SUCCESS");
        } catch (Exception e) {
            return false;
        }
    }
}