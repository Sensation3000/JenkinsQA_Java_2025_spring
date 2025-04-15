package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CheckNewItemCopyFromTest extends BaseTest {

    @Ignore
    // https://github.com/RedRoverSchool/JenkinsQA_Java_2025_spring/actions/runs/14424350561/job/40450841829?pr=1103
    @Test
    public void successfulCopy() {
        WebDriver driver = getDriver();
        final String input = "666";
        final String input2 = "777";

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")).click();
        driver.findElement(By.id("name")).sendKeys(input);
        driver.findElement(By.xpath("//*[@id=\"j-add-item-type-standalone-projects\"]/ul/li[1]")).click();
        driver.findElement(By.id("ok-button")).click();

        WebElement description = getWait5().until(ExpectedConditions.visibilityOf(driver.findElement(By.name("description"))));
        description.sendKeys("test");
        driver.findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/section[1]/div[12]/div[1]/div/span/label")).click();
        WebElement timePeriod = getWait5().until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("_.durationName"))));
        timePeriod.click();

        Select select = new Select(timePeriod);
        select.selectByValue("minute");

        driver.findElement(By.name("Submit")).click();

        WebElement homePage = getWait10()
                .until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", homePage);

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")).click();
        driver.findElement(By.id("name")).sendKeys(input2);
        driver.findElement(By.xpath("//*[@id=\"j-add-item-type-standalone-projects\"]/ul/li[1]")).click();
        WebElement from = driver.findElement(By.id("from"));
        from.sendKeys(input);
        driver.findElement(By.id("ok-button")).click();

        WebElement description2 = getWait10().until(ExpectedConditions.visibilityOf(driver.findElement(By.name("description"))));
        WebElement timePeriod2 = driver.findElement(By.name("_.durationName"));
        Select select2 = new Select(timePeriod2);
        String selectValue = select2.getFirstSelectedOption().getText();

        //Проверка того, что скопированы параметры
        Assert.assertEquals(description2.getText(), "test");
        Assert.assertTrue(driver.findElement(By.name("_.throttle")).isSelected());
        Assert.assertEquals(selectValue, "Minute");

        driver.findElement(By.name("Submit")).click();
        WebElement resultName = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[1]/div[1]/h1"));
        getWait5().until(ExpectedConditions.visibilityOf(resultName));

        //Проверка сохранения
        Assert.assertEquals(resultName.getText(), input2);
    }

    @Test
    public void unsuccessfulCopy() {
        WebDriver driver = getDriver();

        final String input = "666";
        final String input2 = "777";
        final String input3 = "999";
        final String errorText = "No such job: " + input3;

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")).click();
        driver.findElement(By.id("name")).sendKeys(input);
        driver.findElement(By.xpath("//*[@id=\"j-add-item-type-standalone-projects\"]/ul/li[1]")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.name("Submit")).click();

        WebElement homePage = getWait10()
                .until(ExpectedConditions.elementToBeClickable(By.id("jenkins-home-link")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", homePage);

        driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a")).click();
        driver.findElement(By.id("name")).sendKeys(input2);
        driver.findElement(By.xpath("//*[@id=\"j-add-item-type-standalone-projects\"]/ul/li[1]")).click();
        WebElement from = driver.findElement(By.id("from"));
        from.sendKeys(input3);
        driver.findElement(By.id("ok-button")).click();
        getWait5();

        Assert.assertEquals(
                driver.findElement(By.xpath("//*[@id=\"main-panel\"]/h1")).getText(), "Error");
        Assert.assertEquals(
                driver.findElement(By.xpath("//*[@id=\"main-panel\"]/p")).getText(), errorText);
    }
}
