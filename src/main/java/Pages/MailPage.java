package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MailPage {
    WebDriver driver ;
    private WebDriverWait wait;


    // Constructeur pour initialiser le driver et les éléments de la page
    public MailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Locateurs des éléments de la page

    // Locateur du champ de saisie de l'email
    @FindBy(xpath = "//input[@id='login']")
    WebElement emailInput;

    // Locateur du bouton pour vérifier la boîte de réception
    @FindBy(xpath = "//div[@id='refreshbut']/button")
    WebElement checkInboxButton;

    // Locateur du mail de "contact@hightest.nc"
    @FindBy(xpath = "(//span[@class='lmf' and text()='contact@hightest.nc'])[1]")
    WebElement verifyEmail;


    // Ouvrir un nouvel onglet pour naviguer vers Yopmail
    public void mailTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open();");
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        driver.get("https://yopmail.com/fr/");
    }


    // Vérifier la visibilité du champ email
    public boolean isEmailFieldVisible() {
        try {
            boolean isVisible = emailInput.isDisplayed(); // Vérifie si le champ email est visible
            System.out.println(isVisible ?
                    "Le champ de saisie de l'email est visible." :
                    "Le champ de saisie de l'email n'est pas visible.");
            return isVisible;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification de la visibilité du champ email : " + e.getMessage());
            return false;
        }
    }


    // Remplir le champ de saisi de l'email
    public void fillEmailField(String email) {
        try {
            emailInput.clear();
            emailInput.sendKeys(email);
            System.out.println("Le champ email a été rempli avec l'adresse : " + email);
        } catch (Exception e) {
            System.err.println("Erreur lors de la saisie de l'email dans le champ : " + e.getMessage());
        }
    }


    // Vérifier la visibilité du bouton Check Inbox
    public boolean isCheckInboxButtonVisible() {
        try {
            boolean isVisible = checkInboxButton.isDisplayed(); // Vérifie si le bouton est visible
            System.out.println(isVisible ?
                    "Le bouton 'Check Inbox' est visible." :
                    "Le bouton 'Check Inbox' n'est pas visible.");
            return isVisible;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification de la visibilité du bouton 'Check Inbox' : " + e.getMessage());
            return false;
        }
    }

    // Cliquer sur le bouton Check Inbox
    public void clickCheckInboxButton() {
        try {
            checkInboxButton.click(); // Simule un clic sur le bouton
            System.out.println("Le bouton 'Check Inbox' a été cliqué avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur lors du clic sur le bouton 'Check Inbox' : " + e.getMessage());
        }
    }


    // Verifier la visibilité du mail contact@hightest.nc
    public boolean isEmailVisible() {
        try {
            // Attendre que l'iframe soit visible et passer dedans
            WebElement iframeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ifinbox")));
            driver.switchTo().frame(iframeElement); // Basculer dans l'iframe

            // Vérifier si l'élément du mail 'contact@hightest.nc' est visible
            WebElement emailElement = wait.until(ExpectedConditions.visibilityOf(verifyEmail));

            if (emailElement.isDisplayed()) {
                System.out.println("L'email 'contact@hightest.nc' est visible.");
                return true;
            } else {
                System.out.println("L'email 'contact@hightest.nc' n'est pas visible.");
                return false;
            }

        } catch (TimeoutException e) {
            System.out.println("Erreur : L'iframe 'ifinbox' ou l'élément du mail n'a pas été trouvé.");
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("Erreur : L'élément du mail 'contact@hightest.nc' est introuvable dans l'iframe.");
            return false;
        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
            return false;
        } finally {
            driver.switchTo().defaultContent(); // Revenir au contexte principal
        }
    }


    // Cliquer sur le mail de contact@hightest.nc
    public void clickEmail() {
        try {
            // Attendre que l'iframe soit visible et passer dedans
            WebElement iframeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ifinbox")));
            driver.switchTo().frame(iframeElement); // Basculer dans l'iframe

            // Chercher et cliquer sur l'élément 'contact@hightest.nc' dans l'iframe
            WebElement emailElement = wait.until(ExpectedConditions.visibilityOf(verifyEmail));
            emailElement.click();
            System.out.println("Le clic est effectué sur l'email : 'contact@hightest.nc'");

        } catch (TimeoutException e) {
            System.out.println("Erreur : L'iframe 'ifinbox' ou l'email 'contact@hightest.nc' n'a pas été trouvé.");
        } catch (NoSuchElementException e) {
            System.out.println("Erreur : L'élément 'contact@hightest.nc' est introuvable dans l'iframe.");
        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
        } finally {
            driver.switchTo().defaultContent(); // Revenir au contexte principal
        }
    }


    // Verifier le contenu de l'email
    public boolean verifyEmailContent() {
        // Accéder à l'iframe
        WebElement iframe = driver.findElement(By.xpath("//iframe[@id='ifmail']"));
        driver.switchTo().frame(iframe); // Passer dans l'iframe

        // Récupérer le contenu du mail (dans l'élément body ou un autre élément selon la structure)
        WebElement bodyContent = driver.findElement(By.tagName("body"));
        String contentText = bodyContent.getText();

        // Vérifier si le contenu contient la phrase attendue
        String expectedPhrase = "Vous avez bien répondu à 20 question(s) sur 20, soit 100 % de réussite. Félicitations, vous avez obtenu le score maximal !";

        if (contentText.contains(expectedPhrase)) {
            System.out.println("Le mail contient la phrase attendue: " + expectedPhrase);
            return true;
        } else {
            System.out.println("Le mail ne contient pas la phrase attendue.");
            return false;
        }
    }



}
