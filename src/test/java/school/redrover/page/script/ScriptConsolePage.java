package school.redrover.page.script;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

public class ScriptConsolePage extends BasePage {

    @FindBy(className = "CodeMirror")
    private WebElement codeMirror;

    @FindBy(name = "Submit")
    private WebElement buttonSubmit;

    public ScriptConsolePage(WebDriver driver) {super(driver);}

    public String runScriptConsole(String script) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].CodeMirror.setValue(arguments[1]);",
                codeMirror, script);
        buttonSubmit.click();

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(), 'Result')]/following-sibling::pre"))).getText();
    }
}
