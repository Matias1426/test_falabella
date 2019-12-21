package stepdef;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.SharedDriver;
import org.junit.Assert;
import pageobject.FalabellaPO;

public class stepDef {

    FalabellaPO falabellaPO;

    String precioProducto;
    String SKU;


    public stepDef(SharedDriver driver, FalabellaPO falabellaPO){
        this.falabellaPO=falabellaPO;
    }

    @Given("i am on falabella main page")
    public void i_am_on_falabella_main_page(){
        falabellaPO.openFalabellaHomePage();
    }

    @When("i search for {string}")
    public void when_i_search_for_string(String word){
        falabellaPO.search(word);
    }

    @When("i add it to the cart")
    public void i_add_it_to_the_cart(){
        //TODO: agregar el precio y sku si no no funcionaran los flujos que recorren este producto
        falabellaPO.clickBtnAgregarALaBolsa();
    }

    @When("i add it the first result to the cart")
    public void i_add_it_the_first_result_to_the_cart(){
        this.precioProducto=falabellaPO.getProductPriceOfFirstElement();
        this.SKU= falabellaPO.getSKUOfFirstElement();
        falabellaPO.addFirstResultToCart();
        falabellaPO.clickBtnAgregarALaBolsa();
    }

    @Then("i go to checkout")
    public void i_go_to_checkout(){
        falabellaPO.clickBtnVerBolsaDeCompra();
    }

    @Then("the price should be the same that in item's description")
    public void the_price_should_be_the_same_that_in_items_description(){
        System.out.println("El precio del producto es: " + this.precioProducto);
        System.out.println("El precio en el carrito es: " + falabellaPO.getPriceBySKU(this.SKU));
        Assert.assertTrue(this.precioProducto.equals(falabellaPO.getPriceBySKU(this.SKU)));
    }
}
