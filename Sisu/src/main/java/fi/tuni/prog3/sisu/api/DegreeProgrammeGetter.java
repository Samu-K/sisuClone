package fi.tuni.prog3.sisu.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;


/**
 * Used to get all degree programmes names from the Sisu API.
 */

public class DegreeProgrammeGetter {
    
  /**
   * Returns a list of degree programmes names from the Sisu API.
   *
   * @param urlString URL for retrieving information from the Sisu API.
   * @return ArrayList of degree programmes names.
   */
  public static ArrayList<String> getDegreeProgramNamesFromApi(String urlString) {
    ArrayList<String> degreeProgrammes = new ArrayList<String>();
    
    JsonObject jsonObject = ApiJsonGetter.getJsonFromApi(urlString);

    if (jsonObject == null) {
      // If no degreeProgrammes were found, the program is terminated
      System.out.println("Error: jsonObject for DegreeProgammes is null");
      System.exit(1);
    }

    JsonArray searchResults = jsonObject.getAsJsonArray("searchResults");

    for (JsonElement searchResult : searchResults) {
      JsonObject searchResultObject = searchResult.getAsJsonObject();
      String name = searchResultObject.get("name").getAsString();
      degreeProgrammes.add(name);
    }
    System.out.println("DegreeProgrammes: " + degreeProgrammes.toString());

    return degreeProgrammes;
  }
}