package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static org.testng.AssertJUnit.assertTrue;


public class StudentUnionTest extends BaseTest {

    @Test
    public void openDescriptionForm() {

        WebDriver driver = getDriver();

        // Поиск кнопки по ID
        WebElement descriptionLink = driver.findElement(By.xpath("//*[@id=\"description-link\"]"));

        // Проверка, что кнопка отображается
        assertTrue("Кнопка description-link не отображается", descriptionLink.isDisplayed());
        //System.out.println("Кнопка description-link отображается");

        // Проверка, что кнопка кликабельна
        assertTrue("Кнопка description-link не кликабельна", descriptionLink.isEnabled());
        //System.out.println("Кнопка description-link кликабельна");

        // Клик по кнопке
        descriptionLink.click();

    }

    @Test
    public void addDescription() {
        // Ввод текстового описания в поле описание
        String textDescription = "Text description";
        getDriver().findElement(By.xpath("//a[text()='Add description']")).click();
        getDriver().findElement(By.name("description")).sendKeys(textDescription);
        // Сохранение текста
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[text()='" + textDescription + "']")).isDisplayed());

    }

    @Test
    public void createDescription() {
        WebDriver driver = getDriver();

        driver.findElement(By.id("description-link")).click();
        driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("My new description");
        driver.findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='description']/div")).getText(), "My new description");
    }

    @Test
    public void createNewProject() throws InterruptedException {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//a[@href='newJob']")).click();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("My new project");
        driver.findElement(By.cssSelector(".jenkins_branch_OrganizationFolder")).click();

        driver.findElement(By.cssSelector(".jenkins-button")).click();

        driver.findElement(By.xpath("//input[@name='_.displayNameOrNull']")).sendKeys("Name");
        driver.findElement(By.xpath("//textarea[@name='_.description']")).sendKeys("Description");
        driver.findElement(By.xpath("//button[@name='Submit']")).click();

        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Name");
    }

    @Test
    public void navigateToSettingPage() {
        WebDriver driver = getDriver();

        driver.findElement(By.xpath("(//*[@class='icon-md'])[3]")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Account']")).getText(), "Account");
    }

}
