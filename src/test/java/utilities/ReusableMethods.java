package utilities;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static org.openqa.selenium.By.xpath;

public class ReusableMethods {

    //APPIUM 2/3 UYUMLU: W3C standartlarında PointerInput kullanıyor,
    public static void koordinatTiklamaMethodu(int x, int y, int beklemeSuresi) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerMove(Duration.ofMillis(beklemeSuresi), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Driver.getAndroidDriver().perform(Collections.singletonList(tap));
    }

    //APPIUM 2/3 UYUMLU: Static import (androidUIAutomator ve xpath) sayesinde sorunsuz çalışır.
    public static void scrollWithUiScrollableAndClick(String elementText) {
        AndroidDriver driver = (AndroidDriver) Driver.getAndroidDriver();
        driver.findElement(androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"))"));
        driver.findElement(xpath("//*[@text='" + elementText + "']")).click();
    }

    public static void scrollWithUiScrollable(String elementText){
        AndroidDriver driver = (AndroidDriver) Driver.getAndroidDriver();
        driver.findElement(androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"))"));
    }

    // APPIUM 2/3 UYUMLU: Standart Selenium ekran görüntüsü alma mekanizması
    public static String getScreenshot(String name) throws IOException {
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) Driver.getAndroidDriver();

        File source = ts.getScreenshotAs(OutputType.FILE);
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    // DÜZENLENDİ (En Kritik Değişiklik):
    // Eski TouchAction tamamen SİLİNDİ! Yerine Appium 2/3 standartlarına uygun,
    // arka planda çok daha hızlı ve kararlı çalışan "W3C mobile: swipeGesture" getirildi.
    public static void ekranKaydirmaMethodu(int xbaslangic, int ybaslangic, int xbitis, int ybitis, int beklemesuresi) {
        AndroidDriver driver = (AndroidDriver) Driver.getAndroidDriver();

        // Genişlik ve yükseklik hesaplanarak kaydırma mesafesi belirleniyor
        int width = Math.abs(xbitis - xbaslangic);
        int height = Math.abs(ybitis - ybaslangic);

        // Kaydırma yönü belirleniyor (Yukarı mı, aşağı mı vs.)
        String direction = "down";
        if (ybitis > ybaslangic) {
            direction = "down";
        } else if (ybitis < ybaslangic) {
            direction = "up";
        }

        driver.executeScript("mobile: swipeGesture", Map.of(
                "left", xbaslangic,
                "top", ybaslangic,
                "width", width == 0 ? 100 : width,
                "height", height == 0 ? 100 : height,
                "direction", direction,
                "percent", 1.0,
                "speed", beklemesuresi // Hız parametresi bekleme süresiyle optimize edildi
        ));
    }

    // APPIUM 2/3 UYUMLU
    public static void dikeyKaydirma(RemoteWebDriver driver, double baslangicYuzdesi, double bitisYuzdesi, double sabitYuzde, int sure) {
        Dimension boyut = driver.manage().window().getSize();
        int sabitNokta = (int) (boyut.width * sabitYuzde);
        int baslangicNoktasi = (int) (boyut.height * baslangicYuzdesi);
        int bitisNoktasi = (int) (boyut.height * bitisYuzdesi);

        PointerInput parmak = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence kaydirma = new Sequence(parmak, 1)
                .addAction(parmak.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sabitNokta, baslangicNoktasi))
                .addAction(parmak.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(parmak.createPointerMove(Duration.ofMillis(sure), PointerInput.Origin.viewport(), sabitNokta, bitisNoktasi))
                .addAction(parmak.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(kaydirma));
    }

    // APPIUM 2/3 UYUMLU
    public static void bekle(int saniye){
        try {
            Thread.sleep(saniye * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // APPIUM 2/3 UYUMLU: Element lokasyon ve boyut alma metotları güncel sürümde de geçerlidir.
    public static void screenShotElement(String text) throws IOException {
        WebElement element = Driver.getAndroidDriver().findElement(xpath("//*[@text='" + text + "']"));
        Point location = element.getLocation();
        Dimension size = element.getSize();

        File screenshot = Driver.getAndroidDriver().getScreenshotAs(OutputType.FILE);
        BufferedImage fullImage = ImageIO.read(screenshot);
        BufferedImage croppedImage = fullImage.getSubimage(location.getX(), location.getY(), size.getWidth(), size.getHeight());

        File output = new File("kırpılmış_screenshot.png");
        ImageIO.write(croppedImage, "png", output);

        Driver.quitAppiumDriver();
    }
}

