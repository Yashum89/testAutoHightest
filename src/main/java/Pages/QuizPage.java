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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizPage {
    WebDriver driver;

    // Constructeur pour initialiser le driver et les éléments de la page
    public QuizPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locateurs des éléments de la page

    // Locateur pour le loader (élément de chargement de la page)
    @FindBy(id = "loader")
    private WebElement loader;

    // Locateur du titre du quiz
    @FindBy(xpath = "//h1[contains(.,'Test ISTQB Foundation en ligne')]")
    WebElement QuizTitle;

    // Locateur des questions du quiz
    @FindBy(xpath = "//form//div")
    List<WebElement> questions;

    // Locateur du bouton 'Terminé'
    @FindBy(xpath = "//input[@id='submit']")
    WebElement submitButton;



    // Map pour les choix de réponses pour chaque question
    private static final Map<Integer, Integer> choices = new HashMap<>() {{
        put(1, 1); put(2, 2); put(3, 1); put(4, 2); put(5, 2);
        put(6, 3); put(7, 2); put(8, 4); put(9, 1); put(10, 3);
        put(11, 4); put(12, 2); put(13, 3); put(14, 2); put(15, 4);
        put(16, 3); put(17, 3); put(18, 1); put(19, 2); put(20, 2);
    }};

    // vérifier la visibilité du titre
    public boolean isQuizTitleVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOf(QuizTitle));

            if (QuizTitle.isDisplayed()) {
                System.out.println("Le titre du quiz est visible.");
                return true;
            } else {
                System.out.println("Le titre du quiz n'est pas visible.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la vérification du titre du quiz : " + e.getMessage());
            return false;
        }
    }


    // Sélectionner les réponses pour chaque question en fonction des choix prédéfinis
    public void ClickAnswer() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOf(loader));

        int questionIndex = 1;
        // Parcourir chaque question et sélectionner la réponse appropriée
        for (WebElement question : questions) {
            scrollToElement(question);
            wait.until(ExpectedConditions.visibilityOf(question));
            // Localiser toutes les options de réponse sous forme de boutons radio
            List<WebElement> options = question.findElements(By.cssSelector("input[type='radio']"));

            // Vérifier si des options sont disponibles pour la question
            if (options.isEmpty()) {
                System.out.println("Aucune option trouvée pour la question " + questionIndex);
                continue;
            }

            // Vérifier si un choix est défini pour cette question
            if (choices.containsKey(questionIndex) && options.size() >= choices.get(questionIndex)) {
                // Sélectionner l'option spécifiée pour la question
                WebElement selectedOption = options.get(choices.get(questionIndex) - 1);
                selectedOption.click();
                System.out.println("Question " + questionIndex + ": Option " + choices.get(questionIndex) + " sélectionnée.");
            } else {
                // Message d'erreur si le choix n'est pas défini
                System.out.println("Pas assez d'options pour la question " + questionIndex);
            }

            // Attendre la fin du chargement avant de passer à la question suivante
            wait.until(ExpectedConditions.invisibilityOf(loader));
            questionIndex++; // Passer à la question suivante
        }
    }

    // vérifier la visibilité du button 'Terminé'
    public boolean isSubmitButtonVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOf(submitButton));
            if (submitButton.isDisplayed()) {
                System.out.println("Le bouton 'Soumettre' est visible.");
                return true;
            } else {
                System.out.println("Le bouton 'Soumettre' n'est pas visible.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la vérification de la visibilité du bouton 'Soumettre' : " + e.getMessage());
            return false;
        }
    }
    // cliquer sur le bouton 'Terminé'
    public void clickOnSubmitButton() {
        scrollToElement(submitButton);
        submitButton.click();
        System.out.println("Le bouton 'Soumettre' a été cliqué.");
    }

    // Défiler la page jusqu'à un élément spécifique
    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
