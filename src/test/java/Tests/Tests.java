package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dima on 15.03.2017.
 */
public class Tests {
    WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public void setUp() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//lib/chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://hotline.ua/");
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        synchronized (driver) {
            //driver.wait(100);
        }
        // driver.quit();
    }

    @Test
    public void a_LogInTest() throws InterruptedException {
        //Login
        Login.Methods loginM = new Login.Methods(driver);
        Login.Locators loginL = new Login.Locators();
        //Assert_False_#1
        loginM.ProvideEnter(loginL.Vhod);
        loginM.ProvideLogin("ooooohomkadb@gmail.com", loginL.LoginH);
        loginM.ProvidePass("Q36w21@", loginL.PassH);
        loginM.SubmitForm(loginL.Submit);
        Assert.assertFalse(loginM.findElement(loginL.LoginH).equals("assertFalse: #1 e-mail!"));
        Assert.assertFalse(loginM.findElement(loginL.PassH).equals("assertFalse: #1 pass!"));
        //Assert_False_#2
        loginM.Met_ClearForm(loginL.Loc_Clear_Pass);
        loginM.Met_ClearForm(loginL.Loc_Clear_Login);
        loginM.ProvideLogin("homkadb@gmail.com", loginL.LoginH);
        loginM.ProvidePass("Q36w21@@@@@", loginL.PassH);
        loginM.SubmitForm(loginL.Submit);
        Assert.assertFalse(loginM.findElement(loginL.LoginH).equals("assertFalse: #2 e-mail!"));
        Assert.assertFalse(loginM.findElement(loginL.PassH).equals("assertFalse: #2 pass!"));
        //Assert_False_#3
        loginM.Met_ClearForm(loginL.Loc_Clear_Pass);
        loginM.Met_ClearForm(loginL.Loc_Clear_Login);
        loginM.ProvideLogin("aaamkadb.gmail.com", loginL.LoginH);
        loginM.ProvidePass("Q36w21555444444222", loginL.PassH);
        loginM.SubmitForm(loginL.Submit);
        Assert.assertFalse(loginM.findElement(loginL.LoginH).equals("assertFalse: #3 e-mail!"));
        Assert.assertFalse(loginM.findElement(loginL.PassH).equals("assertFalse: #3 pass!"));
        //Assert_True
        loginM.Met_ClearForm(loginL.Loc_Clear_Pass);
        loginM.Met_ClearForm(loginL.Loc_Clear_Login);
        loginM.ProvideLogin("homkadb@gmail.com", loginL.LoginH);
        loginM.ProvidePass("Q36w21@", loginL.PassH);
        loginM.SubmitForm(loginL.Submit);
        Assert.assertTrue(loginM.findElement(loginL.LoginH).isDisplayed(), "assertTrue: The e-mail!");
        Assert.assertTrue(loginM.findElement(loginL.PassH).isDisplayed(), " assertTrue: The login");
        Assert.assertTrue(loginM.findElement(loginL.Submit).isDisplayed(), "assertTrue: The Submit");
        //Login_Quit
        loginM.ProvideEnter(loginL.Loc_Out_Login);
        loginM.ProvideEnter(loginL.Loc_Quit_Login);
    }

    @Test
    public void b_SearchTest() throws InterruptedException {
        //Search_Velik
        Search.Methods SearchM = new Search.Methods(driver);
        Search.Locators SearchL = new Search.Locators();
        SearchM.Met_Click(SearchL.Loc_Click_Search);
        SearchM.Met_Past_Search("Schwinn", SearchL.Loc_Past_Search);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Past_Search).isDisplayed(), "assertTrue: Past_Schwinn!");
        SearchM.Met_Submit(SearchL.Loc_Vel_Submit);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Vel_Submit).isDisplayed(), "assertTrue: Submit!");
        SearchM.Met_Click(SearchL.Loc_Vel_Schwinn);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Vel_Schwinn).isDisplayed(), "assertTrue: Vel_Schwinn!");
        SearchM.Met_Click(SearchL.Loc_Vel_Cat_CrossCountry);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Vel_Cat_CrossCountry).isDisplayed(), "assertTrue: Cat_CrossCountry!");
        SearchM.Met_Click(SearchL.Loc_Vel_Cat_Street);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Vel_Cat_Street).isDisplayed(), "assertTrue: Cat_Street!");
    }

    @Test
    public void c_SearchTest() throws InterruptedException {
        //Search_Tel
        Search.Methods SearchM = new Search.Methods(driver);
        Search.Locators SearchL = new Search.Locators();
        SearchM.Met_Clear(SearchL.Loc_Search_Clear);
        SearchM.Met_Past_Tel_Search("Asus", SearchL.Loc_Past_Tel_Search);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Past_Tel_Search).isDisplayed(), "assertTrue: The Past_Asus!");
        SearchM.Met_Submit(SearchL.Loc_Tel_Submit);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Tel_Submit).isDisplayed(), "assertTrue: The Submit!");
        SearchM.Met_Click(SearchL.Loc_Asus_ZeneFone);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Asus_ZeneFone).isDisplayed(), "assertTrue: Asus_ZeneFone!");
        SearchM.Met_Click(SearchL.Loc_ObzorA_Img);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_ObzorA_Img).isDisplayed(), "assertTrue: ObzorA_Img!");
        SearchM.Met_Click(SearchL.Loc_Close);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Close).isDisplayed(), "assertTrue: Close!");
        SearchM.Met_Click(SearchL.Loc_Komenty_Asus);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Komenty_Asus).isDisplayed(), "assertTrue: Komenty_Asus!");
        SearchM.Met_Click(SearchL.Loc_Back_GeneralPage);
        Assert.assertTrue(SearchM.findElement(SearchL.Loc_Back_GeneralPage).isDisplayed(), "assertTrue: Back_GeneralPage!");

    }

    @Test
    public void d_KatalogTest() throws InterruptedException {
        //Katalog_Tovara
        Katalog.Methods KatalogM = new Katalog.Methods(driver);
        Katalog.Locators KatalogL = new Katalog.Locators();
        KatalogM.Met_Click(KatalogL.Loc_AutoMoto);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_AutoMoto).isDisplayed(), "assertTrue: AutoMoto!");
        KatalogM.Met_Click(KatalogL.Loc_BtTehnica);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_BtTehnica).isDisplayed(), "assertTrue: BtTehnica!");
        KatalogM.Met_Click(KatalogL.Loc_AllforHouse);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_AllforHouse).isDisplayed(), "assertTrue: AllforHouse!");
        KatalogM.Met_Click(KatalogL.Loc_Samsung);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Samsung).isDisplayed(), "assertTrue: Samsung!");
        KatalogM.Met_Click(KatalogL.Loc_Garden);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Garden).isDisplayed(), "assertTrue: Garden!");
        KatalogM.Met_Click(KatalogL.Loc_Kids);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Kids).isDisplayed(), "assertTrue: Kids!");
        KatalogM.Met_Click(KatalogL.Loc_Zoo);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Zoo).isDisplayed(), "assertTrue: Zoo!");
        KatalogM.Met_Click(KatalogL.Loc_Tools);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Tools).isDisplayed(), "assertTrue: Tools!");
        KatalogM.Met_Click(KatalogL.Loc_Books);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Books).isDisplayed(), "assertTrue: Books!");
        KatalogM.Met_Click(KatalogL.Loc_Network);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Network).isDisplayed(), "assertTrue: Network!");
        KatalogM.Met_Click(KatalogL.Loc_MusTools);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_MusTools).isDisplayed(), "assertTrue: MusTools!");
        KatalogM.Met_Click(KatalogL.Loc_Office);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Office).isDisplayed(), "assertTrue: Office!");
        KatalogM.Met_Click(KatalogL.Loc_Fashion);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Fashion).isDisplayed(), "assertTrue: Fashion!");
        KatalogM.Met_Click(KatalogL.Loc_Cosmetics);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Cosmetics).isDisplayed(), "assertTrue: Cosmetics!");
        KatalogM.Met_Click(KatalogL.Loc_Building);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Building).isDisplayed(), "assertTrue: Building!");
        KatalogM.Met_Click(KatalogL.Loc_Telephone);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Telephone).isDisplayed(), "assertTrue: Telephone!");
        KatalogM.Met_Click(KatalogL.Loc_Sport);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Sport).isDisplayed(), "assertTrue: Sport!");
        KatalogM.Met_Click(KatalogL.Loc_Healthing);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Healthing).isDisplayed(), "assertTrue: Healthing!");
        KatalogM.Met_Click(KatalogL.Loc_Watch);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Watch).isDisplayed(), "assertTrue: Watch!");
        KatalogM.Met_Click(KatalogL.Loc_Gift);
        KatalogM.Met_Click(KatalogL.Loc_Back_GeneralPage);
        Assert.assertTrue(KatalogM.findElement(KatalogL.Loc_Gift).isDisplayed(), "assertTrue: Gift!");

    }

    @Test
    public void e_MenuTest() {
        //Foto_Video_Canon
        Menu.Methods MenuM = new Menu.Methods(driver);
        Menu.Locators MenuL = new Menu.Locators();
        MenuM.Met_Click(MenuL.Loc_MenuFotoVideo);
        Assert.assertTrue(MenuM.findElement(MenuL.Loc_MenuFotoVideo).isDisplayed(), "assertTrue: MenuFotoVideo!");
        MenuM.Met_Click(MenuL.Loc_PodMenuFotoVideo);
        Assert.assertTrue(MenuM.findElement(MenuL.Loc_PodMenuFotoVideo).isDisplayed(), "assertTrue: PodMenuFotoVideo!");
        MenuM.Met_Click(MenuL.Loc_Foto);
        Assert.assertTrue(MenuM.findElement(MenuL.Loc_Foto).isDisplayed(), "assertTrue: Foto!");
        MenuM.Met_Click(MenuL.Loc_Filter_Zerkalka);
        Assert.assertTrue(MenuM.findElement(MenuL.Loc_Filter_Zerkalka).isDisplayed(), "assertTrue: Filter_Zerkalka!");
        MenuM.Met_Click(MenuL.Loc_Filter_Canon);
        Assert.assertTrue(MenuM.findElement(MenuL.Loc_Filter_Canon).isDisplayed(), "assertTrue: Filter_Canon!");
        MenuM.Met_Click(MenuL.Loc_Back_GeneralPage);
        Assert.assertTrue(MenuM.findElement(MenuL.Loc_Back_GeneralPage).isDisplayed(), "assertTrue: Back_GeneralPage!");


    }
}

