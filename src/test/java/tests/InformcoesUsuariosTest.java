package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "inforomacoesUsuarioTest.csv")

public class InformcoesUsuariosTest {
    private WebDriver navegador;

        @Rule
        public TestName test = new TestName();

    @Before
    public void setup(){
        navegador = Web.createChrome();

        //Clicar no link que possui o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        //Idendificar o formulario Login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com o name "login" que eesta dentro do formulario de id "signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo com o name "password" que eesta dentro do formulario de id "signinbox" o texto "123456"
        formularioSignInBox.findElement((By.name("password"))).sendKeys("123456");

        //Clicar no link com texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Validar que dentro do elemento com o class "me" esta o texto "Hi, Julio"
        WebElement me = navegador.findElement(By.className("me"));
        String TextoElementoMe = me.getText();
        assertEquals("Hi, Julio", TextoElementoMe);

        //Validacao
        assertEquals(1,1);

        // Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
            navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();


    }
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name = "tipo")String tipo, @Param(name = "contato")String contato, @Param(name = "mensagem")String mensagemEsperada)  {

        // Clicar no botao atraves do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // identificar o pop up onde esta o formulario de id addmoredata
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // Na combo de name type escolher a opcao "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        // No campo de name de contact digitar +551199941111
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        // Clicar no link de text "SAVE" que esta na pop-up

        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        // Na mensagem de id "toast-container" validar que o texto e "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText() ;
        assertEquals(mensagemEsperada, mensagem);

        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));


    }

    @Test
    public void removerUmContatoDeUmUsuario() {

        // Clicar no elemento pelo seu xpath //span[text()="+5511989891123"]/following-sibling::a

        navegador.findElement(By.xpath("//span[text()=\"+55119898666832\"]/following-sibling::a")).click();

        // Confirmar a janela jabascript
        navegador.switchTo().alert().accept();

        // Validar que a mensagem apresentada foi Rest in peace, dear phone!

        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        Screenshot.tirar(navegador, "C:\\Users\\Claudio\\IdeaProjects\\Imagens\\"+ Generator.dataHoraParaArquivo()+ test.getMethodName()+ ".png");

        // Aguardar ate 10 segundos para que a janela desa  pareca

        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // Clicar no link com o texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();

    }

    @After
    public void tearDown(){
        //Fechar o navegador.

        navegador.quit();


    }
}
