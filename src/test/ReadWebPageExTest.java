package test;

import api.ReadWebPageEx;
import api.Parser;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ReadWebPageExTest {
    private ReadWebPageEx testReader;
    private Parser testParser;

    @BeforeEach
   public void initialize()
    {testReader = new ReadWebPageEx();
    testParser = new Parser();}

@Test
    public void vancouverWeather(){
    try {
        testReader.main();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

//@Test
//    public void testTwo(){
//    try {
//        System.out.println(testParser.parseLibrary(testReader.returnDataWeather()));
//    } catch (JSONException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}

    @Test
    public void testReturnWeatherData(){
        try {
            System.out.println(testParser.parseArray(testReader.returnDataWeather()));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReturnCity(){
        try {
            System.out.println(testParser.parseCity(testReader.returnLocationData()));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReturnCountry(){
        try {
            System.out.println(testParser.parseCountry(testReader.returnLocationData()));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String testThreeReturn(){
        try {
            return (testParser.parseArray(testReader.returnDataWeather()));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "bah";
    }

    @Test
    public void rainSearch(){
        String weather = testThreeReturn();
        String search = "rain";

        if (weather.toLowerCase().indexOf(search.toLowerCase()) != -1 ){
            System.out.println("It's raining!");
        }
        else {
            System.out.println("It ain't raining!");
        }

    }


}