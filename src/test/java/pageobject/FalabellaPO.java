package pageobject;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class FalabellaPO {

    WebDriver driver;

    @FindBy(id = "searchQuestionSolr")
    WebElement inputBuscar;

    ////div[@id='all-pods']/div[1]
    @FindBy(xpath = "//div[@id='testId-searchResults-products']/div[1]")
    WebElement firstResultElement;

    @FindBy(xpath = "//div[@class='fb-overlay__inject']//div//a[@class='fb-btn fb-btn-primary fb-added-to-basket__cta fb-added-to-basket__cta--basket'][contains(text(),'Ver Bolsa de Compras')]")
    WebElement btnCarroDeCompra;

    @FindBy(xpath = "//div[@class='fb-product-cta__container']//button[contains(text(),'Agregar a la bolsa')]")
    WebElement btnAgregarALaBolsa;

    public FalabellaPO(){
        this.driver= DriverFactory.getDriver();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public void search(String busqueda){
        //aqui podria ir un wait, pero en si el driver esta configurada para que espere 30 segundos por cada objeto.
        inputBuscar.sendKeys(busqueda);
        inputBuscar.submit();
    }

    public void openFalabellaHomePage(){
        driver.get("http://www.falabella.com");
    }

    public void addFirstResultToCart(){
        firstResultElement.findElement(By.xpath("//button[@class='jsx-4123007394 button button-primary']")).click();

    }

    public void navigateTo(String url){
        driver.get(url);
    }

    public String getProductPriceOfFirstElement(){
        //
        ////div[@class='fb-price-list']/p[contains(text(),'Oferta')]
        String precioOferta = firstResultElement.findElement(By.xpath("(//div[@class='jsx-2017147411 cmr-icon-container']/span)[1]")).getText();
        return precioOferta.substring(0, precioOferta.indexOf(" ")).trim();
    }

    public String getSKUOfFirstElement(){
        String sku = firstResultElement.findElement(By.xpath("//a[@class='jsx-2743978790 jsx-3886284353 pod-summary pod-link pod-summary-4_GRID']")).getAttribute("href");
        return sku.substring(sku.lastIndexOf("/")+1).trim();
    }

    public String getPriceBySKU(String SKU){
        WebElement formulario = getElementFromCartBySKU(SKU);
        return formulario.findElement(By.xpath("(//p[@class='fb-price'])[2]")).getText().trim();
    }

    public WebElement getElementFromCartBySKU(String SKU){
        //
        ////form[@class='fb-product-item__container ' and //span[contains(@data-sku-value,'"+SKU+"')]]
        return driver.findElement(By.xpath("//form[@class='fb_product-form' and //span[contains(text(),'"+SKU+"')]]"));
    }

    public void clickBtnVerBolsaDeCompra(){
        btnCarroDeCompra.click();
    }

    public void clickBtnAgregarALaBolsa(){
        btnAgregarALaBolsa.click();
    }
}
