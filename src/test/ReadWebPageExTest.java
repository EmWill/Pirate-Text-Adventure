package test;

import api.ReadWebPageEx;
import api.WeatherParser;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ReadWebPageExTest {
    private ReadWebPageEx testReader;
    private WeatherParser testParser;

    @BeforeEach
   public void initialize()
    {testReader = new ReadWebPageEx();
    testParser = new WeatherParser();}

@Test
    public void test(){
    try {
        testReader.main();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

//@Test
//    public void testTwo(){
//    try {
//        System.out.println(testParser.parseLibrary(testReader.returnData()));
//    } catch (JSONException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}

    @Test
    public void testThree(){
        try {
            System.out.println(testParser.parseArray(testReader.returnData()));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String testThreeReturn(){
        try {
            return (testParser.parseArray(testReader.returnData()));
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