package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.KiwiPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.Map;

public class KiwiStepDef {
    KiwiPage page=new KiwiPage();
    @Given("Continue as a guest butonuna tiklanir")
    public void continue_as_a_guest_butonuna_tiklanir() {
        page.ContinueAsButton.click();

    }
    @When("Acilan sayfalarda {string} ve {string}  tiklanir")
    public void acilan_sayfalarda_ve_tiklanir(String Continue, String Exploretheapp ) throws InterruptedException {
        Thread.sleep(3000);
        for (int i = 0; i < 3; i++) {
            ReusableMethods.scrollWithUiScrollableAndClick(Continue);
            Thread.sleep(3000);
        }
        Thread.sleep(3000);
        ReusableMethods.scrollWithUiScrollableAndClick(Exploretheapp);


    }
    @When("Trip type one way olarak secilir")
    public void trip_type_one_way_olarak_secilir() throws InterruptedException {
        Thread.sleep(3000);
        page.secimButton.click();
        Thread.sleep(4000);
        page.OneWayButton.click();

    }
    @When("Kalkıs sehri secenegine tiklanir ve default olan sehir kaldirilir")
    public void kalkıs_sehri_secenegine_tiklanir_ve_default_olan_sehir_kaldirilir() throws InterruptedException {
        Thread.sleep(3000);
        page.FromButton.click();
        page.ClearButton.click();

    }
    @When("Kalkıs sehri alanina {string} yazilir ve Choose butonuna tiklanir")
    public void kalkıs_sehri_alanina_yazilir_ve_choose_butonuna_tiklanir(String kalkisSehri) {
        ReusableMethods.bekle(3);
        page.KalkisVarisYeriYazmaKutusu.click();
        page.KalkisVarisYeriYazmaKutusu.sendKeys(kalkisSehri);
        ReusableMethods.bekle(3);
        page.sehirSecmeButonu.click();
        page.ChooseButonu.click();


    }
    @When("Varış sehri alanina {string} yazilir ve Choose butonuna tiklanir")
    public void varış_sehri_alanina_yazilir_ve_choose_butonuna_tiklanir(String gidilenSehir) {
        page.ToButonu.click();
        ReusableMethods.bekle(3);
        page.KalkisVarisYeriYazmaKutusu.sendKeys(gidilenSehir);
        ReusableMethods.bekle(3);
        page.sehirSecmeButonu.click();
        page.ChooseButonu.click();


    }
    @When("Gidiş tarihi alanindan {int} Nisan secilir ve Set Date butonuna tiklanir")
    public void gidiş_tarihi_alanindan_nisan_secilir_ve_set_date_butonuna_tiklanir(Integer int1) {
        page.DepartureButonu.click();
        ReusableMethods.bekle(3);
        ReusableMethods.koordinatTiklamaMethodu(392,1445,300);
        //ReusableMethods.bekle(3);
        page.setDatebutonu.click();

    }
    @When("Search butonuna tiklanir")
    public void search_butonuna_tiklanir() {
        ReusableMethods.bekle(3);
        page.searchbutonu.click();

    }
    @When("En ucuz ve Aktarmasiz filtrelemeleri yapilir")
    public void en_ucuz_ve_aktarmasiz_filtrelemeleri_yapilir() {
        ReusableMethods.bekle(3);
        page.bestbutonu.click();
        page.enUcuzbutonu.click();
        page.Stopbutonu.click();
        ReusableMethods.bekle(3);
        page.nonStopbutonu.click();
        ReusableMethods.bekle(3);

    }
    @Then("Gelen ilk bilet fiyati kaydedilir ve kullanicinin telefonuna SMS olarak gonderilir")
    public void gelen_ilk_bilet_fiyati_kaydedilir_ve_kullanicinin_telefonuna_sms_olarak_gonderilir() {
        String biletfiyati =page.fiyatBilgisi.getText();
        String biletfiyatiAppium3= page.fiyatBilgisi.getText();
// Appium 3 / W3C uyumlu güncel SMS gönderme komutu:
        Driver.getAndroidDriver().executeScript("mobile: sendSms", Map.of(
                "phoneNumber", "1111",
                "message", "Bilet fiyat bilgisi: " + biletfiyatiAppium3
        ));
        Driver.getAndroidDriver().sendSMS("1111","Bilet fiyat bilgisi: "+biletfiyati);
    }
}
