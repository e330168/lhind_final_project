package core.pages.wishlist_cart;

import core.elements.cart.CartElements;
import core.utils.BasePageObject;
import core.utils.UIActions;
import core.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePageObject {
    private CartElements cartPageElements;
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        cartPageElements=new CartElements();
    }

    public List<WebElement> getProductItems() {
        List<WebElement> items = new ArrayList<>();
        for (WebElement product : cartPageElements.productsOnCart) {
            items.add(product);
        }
        return items;
    }

    public WebElement getItem(int index) {
        By locator = By.xpath("(//table[@id='shopping-cart-table']//tbody//tr)[" + (index + 1) + "]");
        WebElement freshElement = driver.findElement(locator);
        return freshElement;
    }

    public void clickBin(){
//        WebElement f=getProductItems().get(0);
        WebElement f=getItem(0);

//        System.out.println(f.getText());
        WaitUtils.waitForVisible(driver, f);
        UIActions.click(driver,f.findElement(By.xpath("(//tbody//td[6]//a)[1]")));
    }
//
//    public void deleteFirstProduct() {
//        WebElement f=getProductItems().get(0);
//                f.clickBin();
//    }


    public int elementsInCart(){
     String nrC=cartPageElements.nrOfCartProd.getText();
     return Integer.parseInt(nrC);
    }



}
