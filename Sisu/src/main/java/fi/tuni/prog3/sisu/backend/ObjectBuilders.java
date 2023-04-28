package fi.tuni.prog3.sisu.backend;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.tuni.prog3.sisu.api.Interface;
import java.util.ArrayList;

/**
 * Class for creating DegreeModule objects from JSON data.
 */
public class ObjectBuilders {
    
  /**
  * Chooses to create either Course or StudyModule object. GroupIds are used to create courses and
  * ids are used to create StudyModules.

  * @param id id of the DegreeModule. Can be either group id or id.
  * @return DegreeModule object
  */
  public static DegreeModule buildCourseOrStudyModule(String id) {
      
    JsonObject degreeModuleJsonObject = Interface.getDegreeModuleById(id);
      
    if (id.startsWith("otm")) {
      return createStudyModule(degreeModuleJsonObject);

    } else {
      return createCourse(degreeModuleJsonObject);
    }
  }
  /**
   * Creates a new course object.

   * @param object JsonObject form of the course data
   * @return returns the created course object
   */
  private static DegreeModule createCourse(JsonObject object) {
       
    //get name as string
    JsonObject nameObject = object.getAsJsonObject("name");
    //Tarvitaankso myös englanniksi?
    JsonElement fiNameElement = nameObject.get("fi");
    String name = fiNameElement.getAsString();
    
    //get id as string
    JsonObject metadataObject = object.getAsJsonObject("metadata");
    JsonElement idElement = metadataObject.get("id");
    String id = idElement.getAsString();
    
    //get groupId as string
    JsonObject universityOrgIdsObject = object.getAsJsonObject("universityOrgIds");
    JsonElement groupIdElement = universityOrgIdsObject.get("groupId");
    String groupId = groupIdElement.getAsString();
    
    //get minCredits int
    JsonObject creditsObject = object.getAsJsonObject("credits");
    JsonElement minCreditsElement = creditsObject.get("min");
    int minCredits = minCreditsElement.getAsInt();
    
    //kurssien pakollisuuden saa modulen rules kohdasta?
    // rule->allMandatory
    //Pakollisuudeksi laitettu nyt false
    Course newCourse = new Course(name, id, groupId, minCredits, false, false);
    
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
    //Tarvitaankso myös englanniksi?
    JsonElement fiNameElement = nameObject.get("fi");
    String name = fiNameElement.getAsString();
    // TODO: Remove test print
    System.out.println("Name found: " + name);

    //get id as string
    JsonElement idElement = studyModuleJsonObject.get("id");
    String id = idElement.getAsString();
    // TODO: Remove test print
    System.out.println("Id found: " + id);
  
    //get groupId as string
    /*
    JsonObject universityOrgIdsObject = studyModuleJsonObject.getAsJsonObject("universityOrgIds");
    JsonElement groupIdElement = universityOrgIdsObject.get("groupId");
    String groupId = groupIdElement.getAsString();
    */
    String groupId = null;
  
    //get minCredits int
    JsonObject creditsObject = studyModuleJsonObject.getAsJsonObject("targetCredits");
    JsonElement minCreditsElement = creditsObject.get("min");
    int minCredits = minCreditsElement.getAsInt();
    // TODO: Remove test print
    System.out.println("Min credits found: " + minCredits);
  
    //get info about mandatory 
    JsonObject ruleObject = studyModuleJsonObject.getAsJsonObject("rule");
    
    if (ruleObject.get("type").getAsString().equals("CreditsRule")) {
      ruleObject = ruleObject.getAsJsonObject("rule");
    }
    
    JsonElement typeElement = ruleObject.get("type");

    boolean isMandatory = false;
    
    if (typeElement.getAsString().equals("CompositeRule")) {
        
      JsonElement allMandatory = ruleObject.get("allMandatory");
      
      isMandatory = allMandatory.getAsBoolean();
    }
          
      
    //get child groupids
    ModuleChildrenGetter children = new ModuleChildrenGetter(id);
    ArrayList<String> childGroupIds = new ArrayList<>();
    childGroupIds = children.returnGroupIds();
    // TODO: Remove test print
    System.out.println("Child group ids found: " + childGroupIds);
    
    StudyModule newStudyModule = 
        new StudyModule(name, id, groupId, minCredits, isMandatory, childGroupIds);
    
    return newStudyModule;
  }
}
