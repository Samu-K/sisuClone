package fi.tuni.prog3.sisu.api;

import com.google.gson.JsonObject;
import java.util.TreeMap;
import javafx.util.Pair;


/**
 * Public interface to access information from the Sisu API from the rest of the program.
 */

public class Interface {

  /**
   * Returns a map of degree programmes names from the Sisu API. The keys are the names of the
   * degree programmes and the values are the ids of the degree programmes.

   * @return TreeMap of degree programmes names and ids.
   */
  public static TreeMap<String, String> getDegreeProgrammeNames() {
    return DegreeProgrammeGetter.getDegreeProgramNamesFromApi("https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-2021&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit=1000");
  }

  /**
   * Returns a JsonObject that contains information about a degree programme. The function calls the
   * the "modules" api first and then the course-units api if the first call fails.

   * @param id id or groupId of the module or being retrieved. 
   * @return JsonObject that contains the module information.
   */
  public static JsonObject getDegreeModuleById(String id) {
    Pair<JsonObject, Boolean> moduleAndIsCourse = getDegreeModuleAndIsCourseById(id);
    return moduleAndIsCourse.getKey();
  }

  /**
   * Returns a JsonObject that contains information about a degree programme. The function calls the
   * the "modules" api first and then the course-units api if the first call fails. The boolean 
   * tells if the module is a course or not.

   * @param id id or groupId of the module or being retrieved.
   * @return JsonObject that contains the module information and a boolean that tells if the module
   is a course or not.
   */
  public static Pair<JsonObject, Boolean> getDegreeModuleAndIsCourseById(String id) {
    String url;
    boolean isCourse = false;
    JsonObject jsonObject;

    // First try to get the module from the modules api
    url = generateModuleUrl(id);
    jsonObject = ApiJsonGetter.getJsonFromApi(url);


    if (jsonObject == null) {
      // If the module was not found, try to get it from the course-units api
      url = generateCourseUrl(id);
      isCourse = true;

      jsonObject = ApiJsonGetter.getJsonFromApi(url);
    }
    
    // If the jsonObject is still null, nothing was found with the given id
    if (jsonObject == null) {
      System.out.println("Error: Could not find module with id: " + id);
      System.exit(1);
    }

    return new Pair<JsonObject, Boolean>(jsonObject, isCourse);
  }

  private static String generateModuleUrl(String id) {
    String url = "";
    if (id.startsWith("otm")) {
      url = "https://sis-tuni.funidata.fi/kori/api/modules/" + id;
    } else {
      url = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=" + id + "&universityId=tuni-university-root-id";
    }
    return url;
  }

  private static String generateCourseUrl(String id) {
    String url = "";
    if (id.startsWith("otm")) {
      url = "https://sis-tuni.funidata.fi/kori/api/course-units/v1/" + id;
    } else {
      url = "https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId=" + id + "&universityId=tuni-university-root-id";
    }
    return url;
  }
}
