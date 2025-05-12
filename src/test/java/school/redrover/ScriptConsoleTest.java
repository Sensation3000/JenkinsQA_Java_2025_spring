package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class ScriptConsoleTest extends BaseTest {
    private static final String SCRIPT_CONSOLE = "println('Hello from Selenium')";
    private static final String ERROR_SCRIPT_CONSOLE = "123$*";

    @Test
    public void testСheckScriptExecutionInConsole() {
        String scriptResult = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickScriptConsole()
                .runScriptConsole(SCRIPT_CONSOLE);

        Assert.assertEquals(scriptResult, "Hello from Selenium");
    }

    @Test
    public void testСheckErrorScriptExecutionInConsole() {
        String errorScriptResult = new HomePage(getDriver())
                .clickManageJenkinsOnLeftSidePanel()
                .clickScriptConsole()
                .runScriptConsole(ERROR_SCRIPT_CONSOLE);

        Assert.assertTrue(errorScriptResult.contains("org.codehaus.groovy.control.MultipleCompilationErrorsException: startup failed:"));
    }
}
