package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.loginPage;
import suporte.Web;

import static org.junit.Assert.assertEquals;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioPageObjectsTest.csv")


public class informcaoesUsuariosPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setup(){
        navegador = Web.createBrowserStack();

    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(
            @Param(name = "login")String login,
            @Param(name = "senha")String senha,
            @Param(name = "tipo")String tipo,
            @Param(name = "contato")String contato,
            @Param(name = "mensagem")String mensagemEsperada
        ){
        String textoToast = new loginPage(navegador)
                .clicarSignIn()
                .fazerLogin(login,senha)
                .clicarMe()
                .clicarNaAbaMoreDateAboutYou()
                .clicarNoBotaoAddMoreDateAboutYou()
                .adicionarContato(tipo, contato)
                .capturarTextoTost();
        assertEquals(mensagemEsperada, textoToast);
    }

    @After
    public void tearDown(){
        navegador.quit();

    }
}
