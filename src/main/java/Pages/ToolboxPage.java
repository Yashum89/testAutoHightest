package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;

public class ToolboxPage {
    WebDriver driver;

    // Constructeur pour initialiser le driver et les éléments de la page
    public ToolboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locateurs des éléments de la page

    // Locateur du lien : Quiz ISTQB niveau Foundation (version 2018)
    @FindBy(xpath = "//h1[contains(text(),'Toolbox')]")
    WebElement toolboxHeader;


    // Vérifier si le titre est visible
    public boolean isToolboxHeaderDisplayed() {
        if (toolboxHeader.isDisplayed()) {
            System.out.println("Le titre 'Toolbox' s'affiche correctement.");
            return true;
        } else {
            System.out.println("Erreur : Le titre 'Toolbox' ne s'affiche pas.");
            return false;
        }
    }


   // cliquer sur le Quiz ISTQB niveau Foundation (version 2018) : Francais
    public void clickOnQuiz() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            // Localiser le premier lien suivant un titre spécifique
            WebElement firstLinkInDiv = driver.findElement(By.xpath("//h2[text()='Quiz ISTQB niveau Foundation (version 2018)']/following-sibling::div//a[1]"));
            // Utilisation de JavaScript pour cliquer sur l'élément (si nécessaire, en cas d'élément dynamique ou masqué)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", firstLinkInDiv);

            switchToNewTab();
        } catch (Exception e) {
            System.out.println("Erreur lors du clic sur le lien Toolbox : " + e.getMessage());
        }
    }

    // Basculer vers le nouvel onglet
    private void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
            System.out.println("Le basculement vers le nouvel onglet a été effectué  : " + driver.getTitle());
        } else {
            System.out.println("Aucun nouvel onglet trouvé après le clic.");
        }
    }
}
