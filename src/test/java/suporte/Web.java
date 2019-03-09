package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {

    public static final String USERNAME = "claudioferreiras1";
    public static final String AUTOMATE_KEY = "6TjR8nn3srSkpYUvANQf";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


    public static WebDriver createChrome(){

        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Claudio\\IdeaProjects\\Driver\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //maximizando o navegador
        navegador.manage().window().maximize();

        // navegando para a pagina do taskit!
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }

    public static WebDriver createBrowserStack(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Edge");
        caps.setCapability("browser_version", "18.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1280x800");
        caps.setCapability("browserstack.debug", "true");

        WebDriver navegador = null;
        try {
            navegador = new RemoteWebDriver(new URL(URL), caps);
            navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            //maximizando o navegador
            navegador.manage().window().maximize();

            // navegando para a pagina do taskit!
            navegador.get("http://www.juliodelima.com.br/taskit");
        }catch (MalformedURLException e){
            System.out.println("Houveram problemas com a URL: " + e.getMessage());
        }
        return navegador;

    }
}
