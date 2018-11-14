package api;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadWebPageEx {
    private Parser parser = new Parser();

    public void main() throws MalformedURLException, IOException {

        BufferedReader br = null;

        try {
            String apiKey = "a34b67d4a13a1fdd02008e3ef8b586cb";
                String weatherQuery = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&mode=json&APPID=";
            String theURL = weatherQuery+apiKey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    public String returnDataWeather() throws MalformedURLException, IOException {

        BufferedReader br = null;

        try {
            String apiKey = "a34b67d4a13a1fdd02008e3ef8b586cb";
            String weatherQuery = null;
            try {
                weatherQuery = "https://api.openweathermap.org/data/2.5/weather?q="+ parser.parseCity(returnLocationData()) +","+
                        parser.parseCountry(returnLocationData())+"&mode=json&APPID=";
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String theURL = weatherQuery+apiKey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            return  "[ " + sb.toString() + " ]";
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    public String returnLocationData() throws MalformedURLException, IOException {

        BufferedReader br = null;

        try {
            String theURL = "http://ip-api.com/json";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            return  "[ " + sb.toString() + " ]";
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }


}

