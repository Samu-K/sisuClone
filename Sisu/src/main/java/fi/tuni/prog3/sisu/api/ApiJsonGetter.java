package fi.tuni.prog3.sisu.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * General functions for getting data from an API in JSON format based on URL.
 */
public class ApiJsonGetter {
  /**
   * Returns a JsonObject that is extracted from the Sisu API. If the URL is not valid, the function
   * will return null.
   *
   * @param urlString URL for retrieving information from the Sisu API.
   * @return JsonObject.
   */
  public static JsonObject getJsonFromApi(String urlString) {
    JsonObject jsonObject = null;
    try {
      // Open connection to the URL
      HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
      connection.setRequestMethod("GET");
      int resposeCode = connection.getResponseCode();
      if (resposeCode == HttpURLConnection.HTTP_OK) {

        // If the connection is successful, read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();

        // Parse the response to a JsonObject
        String json = response.toString();
        Gson gson = new Gson();
        // The jsonObject might be wrapped in an array when using groupIds to call the api, so we
        // need to check for that
        if (json.startsWith("[")) {
          json = json.substring(1, json.length() - 1);
        }
        jsonObject = gson.fromJson(json, JsonObject.class);

      } else {
        // TODO: remove test print
        System.out.println("Error: " + resposeCode + " failed to reach url: " + urlString);
        return null;
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    
    return jsonObject;
  }
}
