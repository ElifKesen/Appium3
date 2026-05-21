package stepDefinitions;

import io.cucumber.java.en.Given;
import pages.KiwiPage;
import utilities.Driver;

public class YuklemeStep {

    @Given("Kullanici dosyayolu verilen {string} uygulamayi cihaza yukler")
    public void kullanici_dosyayolu_verilen_uygulamayi_cihaza_yukler(String dosyaYolu) {
        // Feature dosyasındaki C:\Users\elifk... yolu doğrudan buraya gelir ve driver'a aktarılır
        Driver.getAndroidDriver();

    }
}
