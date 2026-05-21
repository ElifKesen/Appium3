package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.time.Duration;

public class KiwiPage {
    public KiwiPage(){
        /* YENİLİK 1: (WebDriver) casting işlemini kaldırdık.
          YENİLİK 2: Mobil elementlerin daha kararlı yüklenmesi için AppiumFieldDecorator kullandık.
        */
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver(), Duration.ofSeconds(15)), this);
    }


    @FindBy(xpath = "//*[@text='Continue as a guest']")
    public WebElement ContinueAsButton;

    @FindBy(xpath = "//*[@text='One way']")
    public WebElement OneWayButton;

    @FindBy(xpath = " (//*[@class='android.view.View'])[5]")
    public WebElement secimButton;

    @FindBy(xpath = "//*[@text='From:']")
    public WebElement FromButton;

    @FindBy(xpath = "//*[@content-desc='Clear All']")
    public WebElement ClearButton;

    @FindBy(xpath = "//*[@class='android.widget.EditText']")
    public WebElement KalkisVarisYeriYazmaKutusu;

    @FindBy(xpath = "(//*[@class='android.widget.Button'])[2]")
    public WebElement sehirSecmeButonu;

    @FindBy(xpath = "//*[@text='Choose']")
    public WebElement ChooseButonu;

    @FindBy(xpath = "//*[@text='To:']")
    public WebElement ToButonu;

    @FindBy(xpath = "//*[@text='Departure:']")
    public WebElement DepartureButonu;

    @FindBy(xpath="//*[@text='Set date']")
    public WebElement setDatebutonu;

    @FindBy(xpath="/(//*[@text='Search'])[1]")
    public WebElement searchbutonu;

    @FindBy(xpath="//*[@text='Best']")
    public WebElement bestbutonu;

    @FindBy(xpath="//*[@text='Cheapest']")
    public WebElement enUcuzbutonu;

    @FindBy(xpath="//*[@text='Stops']")
    public WebElement Stopbutonu;

    @FindBy(xpath="//*[@text='Nonstop']")
    public WebElement nonStopbutonu;

    @FindBy(xpath = "(//*[@class='android.widget.TextView'])[11]")
    public  WebElement fiyatBilgisi;




}
