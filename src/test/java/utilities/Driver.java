package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {
    private static AndroidDriver appiumDriver;
    private static IOSDriver iosDriver;

    // Dışarıdan dosya yolunu parametre olarak alıyoruz
    public static AndroidDriver getAndroidDriver() {
        URL appiumServerURL = null;
        try {
            // Appium 2/3 standartlarında kök URL kullanılır (/wd/hub YOK)
            appiumServerURL = new URL("http://127.0.0.1:4723/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (appiumDriver == null) {
            if (ConfigReader.getProperty("platformName").equals("Android")) {

                UiAutomator2Options options = new UiAutomator2Options();
                options.setDeviceName("Pixel 4")
                        .setPlatformName("Android")
                        .setPlatformVersion("10.0")
                        .setAutomationName("UiAutomator2")
                        .setApp("C:\\Users\\elifk\\IdeaProjects\\Appium3\\App\\Kiwi.com - Book Cheap Flights_2023.14.0_Apkpure.apk") // KRİTİK: Feature dosyasından gelen dinamik APK yolu!
                        .setAppPackage("com.skypicker.main")
                        .setAppActivity("com.kiwi.android.feature.splash.impl.ui.SplashActivity")
                        .setAutoGrantPermissions(true) // İzinleri otomatik onaylasın
                        .setNoReset(false); // Uygulamayı her seferinde sıfırdan temiz yüklesin

                assert appiumServerURL != null;
                appiumDriver = new AndroidDriver(appiumServerURL, options);

                // ESKİ: TimeUnit.SECONDS -> YENİ: Duration.ofSeconds
                appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

            } else {
                throw new UnsupportedOperationException("Cihaz IOS veya platformName hatalı girilmiş!");
            }
        }

        return appiumDriver;
    }

    public static void quitAppiumDriver(){
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }

}
