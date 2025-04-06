package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.util.List;


public class AddPluginTest extends BaseTest {

    @Test
    public void testAddPlugin() {

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("(//span[@class='task-link-wrapper '])[3]")).click();
        String manageJenkins = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(manageJenkins, "Manage Jenkins");
        driver.findElement(By.xpath("//dt[text()='Plugins']")).click();
        String availablePlugins = driver.findElement(By.xpath("//a[@href='/manage/pluginManager/available']"))
                .getText();
        Assert.assertEquals(availablePlugins, "Available plugins");
        WebElement available = driver.findElement(By.xpath("//a[@href='/manage/pluginManager/available']"));

/*Acceptance Criteria:
1. The “Available plagins” page should be available in the side menu on the “Plagins” page;
 */
        // Проверяем, что элемент видим
        Assert.assertTrue(available.isDisplayed(), "Ссылка 'Available plugins' не отображается");
        // Проверяем, что элемент кликабелен
        Assert.assertTrue(available.isEnabled(), "Ссылка 'Available plugins' не активна");

        /*
        Acceptance Criteria:
        2. On the “Available plugins” page, there should be a search field available for installing the plugin;
         */
        WebElement search = driver.findElement(By.id("filter-box"));
        Assert.assertTrue(search.isDisplayed());
        Assert.assertTrue(search.isEnabled());
        search.click();

/*
Acceptance Criteria:
3. The “Available plugins” page should display a list of plugins available for installation.
 */
            WebElement pluginsTable = driver.findElement(By.id("plugins"));

            Assert.assertTrue(pluginsTable.isDisplayed(), "Таблица с плагинами не отображается");

        List<WebElement> pluginRows = driver.findElements(
                By.cssSelector("table#plugins tbody tr.plugin"));
        Assert.assertTrue(pluginRows.size() > 0, "Нет доступных плагинов в таблице");

        // Проверяем, что у каждого плагина есть название и чекбокс
        for (WebElement row : pluginRows) {
            WebElement nameElement = row.findElement(By.cssSelector("td.details a.jenkins-table__link"));
            Assert.assertTrue(nameElement.isDisplayed(), "Название плагина не отображается");
            Assert.assertFalse(nameElement.getText().isEmpty(), "Название плагина пустое");

            WebElement checkbox = row.findElement(By.cssSelector("input.app-checkbox-install-plugin"));
            Assert.assertTrue(checkbox.isDisplayed(), "Чекбокс плагина не отображается");
            Assert.assertTrue(checkbox.isEnabled(), "Чекбокс плагина не активен");
        }

            WebElement update = driver.findElement(By.id("button-update"));
        // Проверяем что кнопка отображается
        Assert.assertTrue(update.isDisplayed(), "Кнопка Update не отображается");

        // Проверяем что кнопка НЕ активна (disabled)
        Assert.assertFalse(update.isEnabled(), "Кнопка Update активна, хотя должна быть disabled");

        // Проверяем атрибут disabled напрямую
        Assert.assertEquals(update.getAttribute("disabled"), "true",
                "Атрибут disabled не равен 'true'");
//выбираем плагин
     driver.findElement(By.xpath("(//span[@class='jenkins-checkbox'])[1]")).click();

/*
Acceptance Criteria:
1.4 On the “Available plugins” page, the plugin installation button should be displayed, which is inactive until the plugin is selected;
 */
        // 3. Проверяем отсутствие атрибута disabled
        Assert.assertNull(update.getAttribute("disabled"),
                "У активной кнопки не должно быть атрибута disabled");
        }
    }


