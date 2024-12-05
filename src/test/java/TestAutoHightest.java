import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestAutoHightest {

    // Déclaration des variables de classe
    WebDriver driver;
    QuizPage quizPage;
    ToolboxPage toolboxPage;
    HomePage homePage;
    YopmailPage yopmailPage;
    MailPage mailPage;

    // Configuration initiale avant l'exécution des tests
    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://hightest.nc/");

        // Initialisation des objets représentant les pages
        quizPage = new QuizPage(driver);
        homePage = new HomePage(driver);
        toolboxPage = new ToolboxPage(driver);
        yopmailPage = new YopmailPage(driver);
        mailPage = new MailPage(driver);
    }

    // Test principal pour valider l'ensemble des étapes de navigation et d'interactions
    @Test
    public void openWebsite() {
        // *** ACCÈS À LA TOOLBOX DEPUIS LA PAGE D'ACCUEIL ***
        System.out.println("Étape 1 : Vérification de l'affichage et navigation vers la Toolbox.");
        homePage.isToolboxLinkDisplayed(); // Vérifier si le lien Toolbox est affiché
        homePage.clickOnToolbox(); // Cliquer sur le lien Toolbox

        // *** NAVIGATION VERS LA PAGE TOOLBOX ET DÉMARRAGE DU QUIZ ***
        System.out.println("Étape 2 : Vérification de l'accès à la Toolbox et lancement du quiz.");
        toolboxPage.isToolboxHeaderDisplayed(); // Vérifier si l'entête Toolbox est affichée
        toolboxPage.clickOnQuiz(); // Cliquer sur le Quiz ISTQB niveau Foundation (version 2018) : Français

        // *** EXÉCUTION DU QUIZ ***
        System.out.println("Étape 3 : Interaction avec le quiz.");
        quizPage.isQuizTitleVisible(); // Vérifier si le titre du quiz est visible
        quizPage.ClickAnswer(); // Sélectionner une réponse au quiz
        quizPage.isSubmitButtonVisible(); // Vérifier si le bouton de soumission est visible
        quizPage.clickOnSubmitButton(); // Soumettre le quiz

        // *** SOUMISSION D'UN EMAIL AVEC @YOPMAIL ***
        System.out.println("Étape 4 : Envoi de l'email via le champ dédié.");
        yopmailPage.isResultTitleVisible(); // Vérifier si le titre des résultats est affiché
        yopmailPage.isEmailFieldVisible(); // Vérifier si le champ email est visible
        yopmailPage.enterEmail("testhightest@yopmail.com"); // Remplir le champ email
        yopmailPage.isButtonOkVisible(); // Vérifier si le bouton OK est visible
        yopmailPage.clickOnButtonOk(); // Cliquer sur le bouton OK

        // *** VÉRIFICATION DE L'EMAIL REÇU SUR YOPMAIL ***
        System.out.println("Étape 5 : Accès à Yopmail pour vérifier l'email reçu.");
        mailPage.mailTab(); // Accéder à l'onglet
        mailPage.isEmailFieldVisible(); // Vérifier si le champ email est visible
        mailPage.fillEmailField("testhightest@yopmail.com"); // Remplir le champ email
        mailPage.isCheckInboxButtonVisible(); // Vérifier si le bouton "Check Inbox" est visible
        mailPage.clickCheckInboxButton(); // Cliquer sur le bouton "Check Inbox"
        mailPage.isEmailVisible(); // Vérifier si l'email contact@hightest est visible
        mailPage.clickEmail(); // Cliquer sur l'email contact@hightest.nc
        mailPage.verifyEmailContent(); // Vérifier le contenu de l'email

        // *** CONFIRMATION DE LA FIN DU TEST ***
        System.out.println("Scénario de test terminé avec succès.");
    }

    // Fermeture du navigateur et nettoyage après l'exécution des tests
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            System.out.println("Fermeture du navigateur.");
            driver.quit(); // Ferme le navigateur
        }
    }
}
