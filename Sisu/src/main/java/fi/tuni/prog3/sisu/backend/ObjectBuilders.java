package fi.tuni.prog3.sisu.backend;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.tuni.prog3.sisu.api.Interface;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 * Class for creating DegreeModule objects from JSON data.
 */
public class ObjectBuilders {
    
  /**
  * Chooses to create either Course or StudyModule object. Accepts both id and groupId.

  * @param id id of the DegreeModule. Can be either group id or id.
  * @return The object created as DegreeModule.
  */
  public static DegreeModule buildCourseOrStudyModule(String id) {
      
    Pair<JsonObject, Boolean> pair = Interface.getDegreeModuleAndIsCourseById(id);
    JsonObject degreeModuleJsonObject = pair.getKey();
    boolean isCourse = pair.getValue();
      
    if (isCourse) {
      return createCourse(degreeModuleJsonObject);

    } else {
      return createStudyModule(degreeModuleJsonObject);
    }
  }
  /**
   * Creates a new course object.

   * @param courseJsonObject JsonObject form of the course data
   * @return returns the created course object
   */
  private static DegreeModule createCourse(JsonObject courseJsonObject) {

    //get name as string
    JsonObject nameObject = courseJsonObject.getAsJsonObject("name");
    JsonElement nameElement = nameObject.get("fi");

    // All courses don't have a Finnish name, so check if it exists. If not, use English name
    if (nameElement == null) {
      nameElement = nameObject.get("en");
    }
    String name = nameElement.getAsString();
    
    //get id as string
    JsonElement idElement = courseJsonObject.get("id");
    String id = idElement.getAsString();
    
    
    //get minCredits int
    JsonObject creditsObject = courseJsonObject.getAsJsonObject("credits");
    JsonElement minCreditsElement = creditsObject.get("min");
    int minCredits = minCreditsElement.getAsInt();
    
    // TODO: Implement way of finding out if course is mandatory, all are false for now.
    Course newCourse = new Course(name, id, minCredits, false, false);
    
    return newCourse;
  }
    
  /**
   * Creates a new studyModule object.

   * @param studyModuleJsonObject JsonObject with StudyModule data
   * @return returns the created StudyModule object
   */
  private static DegreeModule createStudyModule(JsonObject studyModuleJsonObject) {

    //get name as string
    JsonObject nameObject = studyModuleJsonObject.getAsJsonObject("name");
    JsonElement nameElement = nameObject.get("fi");

    // All studyModules might not have a Finnish name, so check if it exists. If not, use English
    // name instead.
    if (nameElement == null) {
      nameElement = nameObject.get("en");
    }
    String name = nameElement.getAsString();

    //get id as string
    JsonElement idElement = studyModuleJsonObject.get("id");
    String id = idElement.getAsString();
  
    //get minCredits int
    int minCredits = getStudyModuleMinCredits(studyModuleJsonObject);
  
    //get info about mandatory 
    boolean isMandatory = isStudyModuleMandatory(studyModuleJsonObject);
      
    //get child groupids
    ArrayList<String> childGroupIds = new ArrayList<String>();
    childGroupIds = ModuleChildrenGetter.getChildrenIds(id);

    // If there is only one child and its of type, we skip this layer alltogether

    // TODO: Implement better way to check if the module is a course
    // This solution for checking if the module is a course is not very good as it causes an extra
    // API call
    if (childGroupIds.size() == 1
        && !Interface.getDegreeModuleAndIsCourseById(childGroupIds.get(0)).getValue()) {

      String childId = childGroupIds.get(0);
      StudyModule childModule = (StudyModule) buildCourseOrStudyModule(childId);
      return childModule;
    }
    
    StudyModule newStudyModule = new StudyModule(name, id, minCredits, isMandatory, childGroupIds);
  
    return newStudyModule;
  }

  private static int getStudyModuleMinCredits(JsonObject studyModuleJsonObject) {
    int minCredits = 0;
    if (studyModuleJsonObject.has("credits")) {
      JsonObject creditsObject = studyModuleJsonObject.getAsJsonObject("credits");
      if (creditsObject.has("min")) {
        JsonElement minCreditsElement = creditsObject.get("min");
        minCredits = minCreditsElement.getAsInt();
      }
    }
    return minCredits;
  }

  // TODO: CAUTION: this method is not tested and probably gives wrong results
  private static boolean isStudyModuleMandatory(JsonObject studyModuleJsonObject) {
    boolean isMandatory = false;
    if (studyModuleJsonObject.has("rule")) {
      JsonObject ruleObject = studyModuleJsonObject.getAsJsonObject("rule");
      if (ruleObject.has("type")) {
        JsonElement typeElement = ruleObject.get("type");
        if (typeElement.getAsString().equals("CompositeRule")) {
          JsonElement allMandatory = ruleObject.get("allMandatory");
          isMandatory = allMandatory.getAsBoolean();
        }
      }
    }
    return isMandatory;
  }
}
