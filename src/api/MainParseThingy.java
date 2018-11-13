package api;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


import java.io.*;

public class MainParseThingy {

    /**
     * Parse JSON library file and print data to console
     */
    private void parse() {
        try {
            InputStream is = new FileInputStream("./library.json");
            String jsonData =  readSource(is);
            WeatherParser libParser = new WeatherParser();
            libParser.parseLibrary(jsonData);
        } catch (IOException e) {
            System.out.println("Error reading file...");
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("Error parsing JSON data");
            e.printStackTrace();
        }

    }

    /**
     * Read source data from input stream as string
     *
     * @param is  input stream connected to source data
     * @return  source data as string
     * @throws IOException  when error occurs reading data from file
     */
    private String readSource(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;

        while((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        br.close();

        return sb.toString();
    }



}
