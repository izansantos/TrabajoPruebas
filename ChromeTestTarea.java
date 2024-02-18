package dev.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTestTarea{

    static ChromeDriver driver; //nos abre una ventana de google

    @BeforeAll //automatiza el método
    public static void start(){ //antes de cualquier test ejecuta esto de aquí
        ChromeOptions options = new ChromeOptions(); //opciones que queremos dentro del chrome (por ejemplo maximizado)
        options.addArguments("start-maximized"); //hace que la ventana se abra maximizada
        driver = new ChromeDriver(options); //abre el driver del chrome
        driver.get("https://www.mercadona.es/"); //le damos la ruta de la página web
    }

    @Test
    public void CPF1() throws InterruptedException{
        //buscamos cuantos input de tipo texto hay en la herramienta de inspeccionar: .//input[@type='text']
        List<WebElement> inputsCP = driver.findElements(By.cssSelector("input[type=text]")); //hacemos una lista de los dos elementos que tenemos con esa característica
        WebElement inputCP = inputsCP.get(0); //ahora seleccionamos el primero de ellos
        inputCP.sendKeys("00000");

        //seleccionamos ahora el input de entrar. Es un proceso similar al anterior, ya que nuevamente hay dos elementos iguales: .//input[@class='postal-code-form__button']
        List<WebElement> inputsIniciar = driver.findElements(By.className("postal-code-form__button"));
        WebElement inputIniciar = inputsIniciar.get(0);
        inputIniciar.click(); //clickamos el elemento seleccionado
        Thread.sleep(5000);
        //seleccionamos el párrafo que te explica un error
        WebElement avisoErrorCP = driver.findElement(By.className("input-text__message--error"));
        //realizamos las pruebas del elemento
        assertEquals("El código postal no es correcto.", avisoErrorCP.getText() );
    }
    
    @Test
    public void CPF2() throws InterruptedException{
        //buscamos cuantos li de clase o-menu__item hay en la herramienta inspeccionar: .//li[@class='o-menu__item']
        List<WebElement> liMenus = driver.findElements(By.className("o-menu__item"));
        WebElement liMenu = liMenus.get(3); //seleccionamos el que nos interesa
        liMenu.click(); 
        Thread.sleep(3000);
        //seleccionamos el elemento donde deseamos introducir nuestra duda e insertamos lo que deseamos poner
        WebElement MenuError = driver.findElement(By.className("busquedaInput"));
        MenuError.sendKeys("abcdefg");
        Thread.sleep(3000);
        //seleccionamos el cajon de búsqueda que nos indica una inexistencia. Tenemos que hacerlo con xPath ya que no tiene más elementos identificativos
        WebElement CajonError = driver.findElement(By.xpath(".//div[@class='FaqBusq']/a"));
        //realizamos las pruebas del elemento
        assertEquals("Lo sentimos, no existen resultados para tu búsqueda", CajonError.getText());
    }

    @Test
    public void CPF3() throws InterruptedException{
        //cogemos el elemento que nos interesa para acceder al formulario de notificaciones y le damos a click
        WebElement avisame = driver.findElement(By.className("section__link"));
        avisame.click();
        Thread.sleep(3000);
        //Vamos a seleccionar el apartado de CP, dejarlo en blanco seleccionando el de correo
        List <WebElement> avisameCP = driver.findElements(By.className("tooltip"));
        WebElement avisameEmail = avisameCP.get(1);
        avisameEmail.click();
        WebElement avisameCP1 = avisameCP.get(0);
        avisameCP1.click();
        Thread.sleep(3000);
        //seleccionamos el objeto de error que deseamos comparar
        WebElement errorCP1 = driver.findElement(By.className("active"));
        //comparamos los elementos para realizar la prueba
        assertEquals("Este campo no puede estar en blanco.", errorCP1.getText());
    }

    @Test
    public void CPF4() throws InterruptedException{
        //cogemos el botón de los idiomas
        WebElement idioma = driver.findElement(By.className("o-lang-selector__current"));
        idioma.click();
        //seleccionamos los distintos botones que tiene el boton de idiomas ya que comparten la misma clase. Seleccionamos el cuarto
        List <WebElement> idiomas = driver.findElements(By.className("o-lang-selector__button"));
        WebElement idiomaGallego = idiomas.get(3);
        idiomaGallego.click();
        Thread.sleep(3000);
        //comprobamos que la traducción de cualqueir elemento sea correcta. Como he localizado un error:
        List <WebElement> titulosSection = driver.findElements(By.className("section__title"));
        WebElement tituloSectionError = titulosSection.get(1);
        //hacemos el asserts diciendo que nalgunhas esta incorrecto
        assertEquals("Nova tenda en liña nalgunhas zonas", tituloSectionError.getText());
    }

    @Test 
    public void CPF5 () throws InterruptedException{
         //cogemos el botón de los idiomas
         WebElement idioma = driver.findElement(By.className("o-lang-selector__current"));
         idioma.click();
         //seleccionamos los distintos botones que tiene el boton de idiomas ya que comparten la misma clase. Seleccionamos el cuarto
         List <WebElement> idiomas = driver.findElements(By.className("o-lang-selector__button"));
         WebElement idiomaGallego = idiomas.get(3);
         idiomaGallego.click();
         Thread.sleep(3000);
         //Entramos en el apartado de supermercados
         List<WebElement> apartados = driver.findElements(By.className("o-menu__item"));
         WebElement supermercados = apartados.get(1);
         supermercados.click();
         Thread.sleep(3000);
         //Comprobamos que siga en gallego comparando con algún texto de la página
         WebElement tituloSupermercados = driver.findElement(By.className("supermercadosTitulo"));
         //Realizamos el asserts con la traducción y este texto
         assertEquals("Mercadona conta con 1.620 supermercados. Encontra o teu Mercadona máis próximo e o seu horario", tituloSupermercados.getText());
    }


    @AfterAll //automatiza el método
    public static void end(){ //después de cualquier test ejecuta esto de aquí
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace(); //imprime el error que te doy
        }
        driver.quit();
    }
}

