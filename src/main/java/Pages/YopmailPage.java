package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YopmailPage {
    private WebDriver driver;

    // Constructeur pour initialiser le WebDriver et les éléments de la page
    public YopmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locateurs des éléments de la page

    // Localise le titre affiché sur la page des résultats.
    // Utilisé pour vérifier si la page de résultats est correctement chargée.
    @FindBy(xpath = "//div[@id='header']/h1[contains(text(), 'Résultats du test en ligne')]")
    private WebElement resultTitle;

    // Locateur du champ de saisie pour entrer une adresse email.
    @FindBy(xpath = "//input[@id='email']")
    private WebElement inputField;

    // Locateur du bouton "OK" pour valider l'email saisi
    @FindBy(xpath = "//input[@id='submitMail']")
    private WebElement buttonOk;

    // vérifier la visibilité du titre des résultats
    public boolean isResultTitleVisible() {
        try {
            boolean isVisible = resultTitle.isDisplayed();
            System.out.println(isVisible ?
                    "Le titre 'Résultats du test en ligne' est visible." :
                    "Le titre 'Résultats du test en ligne' n'est pas visible.");
            return isVisible;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification de la visibilité du titre : " + e.getMessage());
            return false;
        }
    }

    // vérifier la visibilité du champ de saisie de l'email
    public boolean isEmailFieldVisible() {
        try {
            boolean isVisible = inputField.isDisplayed();
            System.out.println(isVisible ?
                    "Le champ de saisie de l'email est visible." :
                    "Le champ de saisie de l'email n'est pas visible.");
            return isVisible;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification de la visibilité du champ de saisie : " + e.getMessage());
            return false;
        }
    }

    // Saisir une adresse email
    public void enterEmail(String email) {
        try {
            inputField.clear();
            inputField.sendKeys(email);
            System.out.println("L'email saisi est : " + email);
        } catch (Exception e) {
            System.err.println("Erreur lors de la saisie de l'email : " + e.getMessage());
        }
    }

    //  Vérifier la visibilité du bouton OK
    public boolean isButtonOkVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(buttonOk));
            System.out.println("Le bouton 'OK' est visible.");
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification de la visibilité du bouton 'OK' : " + e.getMessage());
            return false;
        }
    }

    //  Cliquer sur le bouton OK
    public void clickOnButtonOk() {
        try {
            buttonOk.click();
            System.out.println("Le bouton 'OK' a été cliqué.");
        } catch (Exception e) {
            System.err.println("Erreur lors du clic sur le bouton 'OK' : " + e.getMessage());
        }
    }
}
