package school.redrover;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class WelcomPageTest extends BaseTest {

    @Test
    public void testWelcome(){

        String text = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(text, "Welcome to Jenkins!");

    }
}
