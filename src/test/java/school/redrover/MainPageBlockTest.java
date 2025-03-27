package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MainPageBlockTest extends BaseTest {

        @Test
        public void testFirstSectionClass() {
            WebElement sectionClass = getDriver().findElement(By.xpath("//h2[@class='h4' and text()='Start building your software project']"));

            Assert.assertEquals(sectionClass.getText(), "Start building your software project");
        }

        @Test
        public void testSecondSectionClass() {
            WebElement sectionClass = getDriver().findElement(By.xpath("//h2[@class='h4' and text()='Set up a distributed build']"));

            Assert.assertEquals(sectionClass.getText(), "Set up a distributed build");
        }

        @Test
        public void testFirstSectionClassContentBlock() {
            WebElement newJob = getDriver().findElement(By.cssSelector("a[href='newJob'].content-block__link"));

            Assert.assertEquals(newJob.getText(), "Create a job");
        }
    }


