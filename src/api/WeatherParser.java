package api;


// An example of a simple JSON parser to parse a json file representing a library of books

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherParser {

    /**
     * Prints library parsed from JSON data to console
     * @param jsonData  string containing JSON data
     * @throws JSONException when jsonData cannot be parsed as a JSONArray
     */
    public String parseLibrary(String jsonData) throws JSONException {
        JSONArray weatherData = new JSONArray(jsonData);

        for (int index = 0; index < weatherData.length(); index++) {
            JSONObject book = weatherData.getJSONObject(index);
            return parseBook(book);
        }
        return null;
    }

    /**
     * Prints book parsed from JSON object to console
     * @param book  a JSON object representing a book
     * @throws JSONException when book does not have a title or an author field
     */
    private String parseBook(JSONObject book) throws JSONException {

        JSONObject weather = book.getJSONObject("weather");
        String state = weather.getString("main");
        return state;
    }

    public String parseArray(String jsonData) throws JSONException{
        JSONArray array = new JSONArray(jsonData);
        JSONObject object = array.getJSONObject(0);
        JSONArray weather = object.getJSONArray("weather");
        JSONObject weatherObj = weather.getJSONObject(0);
        return weatherObj.getString("main");

    }
}
