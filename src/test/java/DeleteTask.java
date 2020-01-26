import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class DeleteTask {

    AndroidDriver driver;
    WebDriverWait wait;

    /*
        To-do listden task çıkarılmasını sağlar.
        Önce listeye yeni bir task eklenir. Ardından bu task silir.
    */

    @Before
    public void setup() throws Exception{

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("udid","03549a18964500000000");
        caps.setCapability("appPackage","com.example.android.architecture.blueprints.todomvp.mock");
        caps.setCapability("appActivity","com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity");
        caps.setCapability("platformName","Android");
        caps.setCapability("deviceName","V Tab 7010");
        caps.setCapability("noReset",false);

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub/"),caps);
        wait = new WebDriverWait(driver,20);

    }

    @Test
    public void DeleteTask(){


        // Listeye ekleme yapmak için ekle butonuna tıklanır.
        MobileElement add_task_btn = (MobileElement) driver.findElementById("com.example.android.architecture.blueprints.todomvp.mock:id/fab_add_task");
        add_task_btn.click();

        //Task ekleme sayfasında başlık alanını bekler
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title")));

        //Taskın eklenmesi için gereken başlık ve detay stringleri
        String title = "silme çalışması";
        String description="silme çalışması detay";

        //Title'a değer girişi yapılır.
        MobileElement task_title = (MobileElement) driver.findElementById("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_title");
        task_title.sendKeys(title);

        //Detay kısmına değer girişi yapılır.
        MobileElement task_desc = (MobileElement) driver.findElementById("com.example.android.architecture.blueprints.todomvp.mock:id/add_task_description");
        task_desc.sendKeys(description);

        //Ekleme butonuna tıklanır.
        MobileElement done_btn = (MobileElement) driver.findElementById("com.example.android.architecture.blueprints.todomvp.mock:id/fab_edit_task_done");
        done_btn.click();

        //Liste ekranının yüklenmesini bekler.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/title")));

        //Eklemiş olduğumuz taskın silinmesini istiyoruz. Bu yüzden Title alanında giriş yaptığımız değeri bulup tıklar.
        driver.findElementByXPath("//android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout/android.widget.TextView[@text="+"'"+title+"'"+"]").click();

        //Silinecek task detayının yüklenmesini bekler.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.example.android.architecture.blueprints.todomvp.mock:id/menu_delete")));

        //Silme butonuna tıklar.
        MobileElement delete_btn = (MobileElement) driver.findElementById("com.example.android.architecture.blueprints.todomvp.mock:id/menu_delete");
        delete_btn.click();


    }

    @After
    public void tearDown() {

        driver.quit();

    }
}
