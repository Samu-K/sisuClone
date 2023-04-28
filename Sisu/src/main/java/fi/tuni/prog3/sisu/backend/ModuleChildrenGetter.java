package fi.tuni.prog3.sisu.backend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.tuni.prog3.sisu.api.Interface;
import java.util.ArrayList;

/**
 * Class for getting direct children modules and courses of a module from API.
 */
public class ModuleChildrenGetter {
    
  private JsonObject moduleJson;
  
  /**
   * Builder with DegreeModule parameter.

   * @param module DegreeModule whose children are fetched
   */
  public ModuleChildrenGetter(DegreeModule module) {
        
    String id = module.getId();    

    moduleJson = Interface.getDegreeModuleById(id);
  }
  
  /**
   * Builder with groupId parameter.

   * @param groupId the groupId of the StudyModule for getting data from API
   */
  public ModuleChildrenGetter(String groupId) {

    moduleJson = Interface.getDegreeModuleById(groupId);
  }
  
  /**
   * Creates an ArrayList with the module's  direct childrens' groupIds.

   * @return ArrayList the ArrayList with groupIds of the children modules and courses
   */
  public ArrayList<String> returnGroupIds() {
            
    JsonObject ruleObject = moduleJson.getAsJsonObject("rule");
    
    if (ruleObject.get("type").getAsString().equals("CreditsRule")) {
      ruleObject = ruleObject.getAsJsonObject("rule");
    }
    
    JsonElement rulesElement = ruleObject.get("rules");
    
    JsonArray rulesArray = rulesElement.getAsJsonArray();
    
    ArrayList<String> groupIdList = new ArrayList<>();
    
    for (JsonElement childRuleElement : rulesArray) {
      JsonObject childRuleObject = childRuleElement.getAsJsonObject();
      
      JsonElement groupIdElement;
      
      if (childRuleObject.get("type").getAsString().equals("CourseUnitRule")) {
          
        groupIdElement = childRuleObject.get("courseUnitGroupId");
      } else if (childRuleObject.get("type").getAsString().equals("ModuleRule")) {
          
        groupIdElement = childRuleObject.get("moduleGroupId");
      } else {
        continue;
      }
      
      String groupId = groupIdElement.getAsString();
      
      groupIdList.add(groupId);
    }
    
    return groupIdList; 
  }
    
}
