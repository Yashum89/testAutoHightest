package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    private WebDriverWait wait;

    // Constructeur pour initialiser le driver et les éléments de la page
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    // Locateurs des éléments de la page

    // Locateur du lien "Toolbox"
    @FindBy(xpath = "//a[@title='Toolbox']")
    WebElement toolboxLink;

    // Vérifier si le lien "Toolbox" est visible
    public boolean isToolboxLinkDisplayed() {
        try {
            waitForPageToLoad();
            wait.until(ExpectedConditions.visibilityOf(toolboxLink));
            if (toolboxLink.isDisplayed()) {
                System.out.println("Le lien 'Toolbox' est visible.");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Erreur : Le lien 'Toolbox' n'est pas visible.");
            return false;
        }
    }


    // Cliquer sur le lien "Toolbox"
    public void clickOnToolbox() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(toolboxLink));
            toolboxLink.click();
            System.out.println("Le lien 'Toolbox' a été cliqué.");
        } catch (Exception e) {
            System.out.println("Erreur : Impossible de cliquer sur le lien 'Toolbox'.");
        }
    }

    // Attendre que la page soit complètement chargée
    private void waitForPageToLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
        System.out.println("La page est complètement chargée.");
    }
}
