package testngdemo;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Asserts {

    @Test
    public  void hardAssertExample(){

        String expectedResult= "Hard Assert" ;
        //int number2 = 10
        String actualResult = "Hard Assert";

        Assert.assertEquals(expectedResult, actualResult);

        System.out.println("Esta ejecución no puede ser ejecutada");

    }

    @Test
    public void softAssertExample(){

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(1,2,"los numero no son iguales");
        softAssert.assertEquals(1,3,"los numero no son iguales2");
        softAssert.assertEquals(1,5,"los numero no son iguale3s");
       // softAssert.fail("fallo al validar");

        softAssert.assertAll();

        System.out.println("Este Codigo puede ser ejecutado");


    }
}
