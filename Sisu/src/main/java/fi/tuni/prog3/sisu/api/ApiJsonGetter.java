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
   * Returns a JsonObject that is extracted from the Sisu API. If the connection fails, 
   * program is terminated
   *
   * @param urlString URL for retrieving information from the Sisu API.
   * @return JsonObject.
   */
  public static JsonObject getJsonFromApi(String urlString) {
    JsonObject jsonObject = null;
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
      connection.setRequestMethod("GET");
      int resposeCode = connection.getResponseCode();
      if (resposeCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();
        String json = response.toString();
        Gson gson = new Gson();
        jsonObject = gson.fromJson(json, JsonObject.class);

      } else {
        System.out.println("Error: " + resposeCode);
        System.exit(1);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      System.exit(1);
    }
    
    return jsonObject;
  }
}
