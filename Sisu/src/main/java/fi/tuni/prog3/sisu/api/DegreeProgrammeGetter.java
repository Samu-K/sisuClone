package fi.tuni.prog3.sisu.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.TreeMap;


/**
 * Used to get all degree programmes names from the Sisu API.
 */

public class DegreeProgrammeGetter {
    
  /**
   * Returns a map of degree programmes names from the Sisu API. The keys are the names of the
   * degree programmes and the values are the ids of the degree programmes.
   *
   * @param urlString URL for retrieving information from the Sisu API.
   * @return TreeMap of degree programmes names and ids.
   */
  public static TreeMap<String, String> getDegreeProgramNamesFromApi(String urlString) {
    TreeMap<String, String> degreeProgrammes = new TreeMap<String, String>();
    
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
      String id = searchResultObject.get("groupId").getAsString();
      degreeProgrammes.put(name, id);
    }

    //THIS IS FOR TESTING PURPOSES
    System.out.println("DegreeProgrammes: " + degreeProgrammes.toString());

    return degreeProgrammes;
  }
}