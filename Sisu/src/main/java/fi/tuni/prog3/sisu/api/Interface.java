package fi.tuni.prog3.sisu.api;

import com.google.gson.JsonObject;
import java.util.ArrayList;

/**
 * Public interface to access information from the Sisu API from the rest of the program.
 */

public class Interface {
  public ArrayList<String> getDegreeProgrammeNames() {
    return DegreeProgrammeGetter.getDegreeProgramNamesFromApi("https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-2021&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit=1000");
  }

  /**
   * Returns a JsonObject that contains information about a degree programme. The program calls the
   * right API based on whether the id represents a course or a study module.

   * @param id id or groupId of the module being retrieved. 
   * @return JsonObject that contains the module information.
   */
  public static JsonObject getDegreeModuleById(String id) {
    String url = "";

    if (id.startsWith("otm")) {
      // The ID represents a module
      url = "https://sis-tuni.funidata.fi/kori/api/modules/" + id; 
    } else {
      // The ID represents a course
      url = "https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId=" + id + "&universityId=tuni-university-root-id";
    }

    JsonObject jsonObject = ApiJsonGetter.getJsonFromApi(url);

    return jsonObject;
  }
}
