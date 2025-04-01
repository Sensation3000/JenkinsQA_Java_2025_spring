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
    public void openDescriptionForm(){

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
    public void addDescription(){
        // Ввод текстового описания в поле описание
        String textDescription = "Text description";
        getDriver().findElement(By.xpath("//a[text()='Add description']")).click();
        getDriver().findElement(By.name("description")).sendKeys(textDescription);
        // Сохранение текста
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[text()='" + textDescription + "']")).isDisplayed());

    }
}
