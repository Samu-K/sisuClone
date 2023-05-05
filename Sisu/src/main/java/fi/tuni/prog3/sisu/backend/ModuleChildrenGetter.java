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
  
  /**
   * Creates an ArrayList with children GroupIds for the module.

   * @return ArrayList 
   */
  public static ArrayList<String> getChildrenIds(String id) {
    JsonObject sourceJson = Interface.getDegreeModuleById(id);
    ArrayList<String> childrenIds = new ArrayList<>();
    if (sourceJson.has("rule")) {
      JsonObject rule = sourceJson.getAsJsonObject("rule");
      processRule(rule, childrenIds);
    }
    return childrenIds;
  }

  // This function does not return anything as it modifies the childrenIds ArrayList passed to it.
  private static void processRule(JsonObject rule, ArrayList<String> childrenIds) {
    String type = rule.get("type").getAsString();

    switch (type) {
      case "ModuleRule":
        childrenIds.add(rule.get("moduleGroupId").getAsString());
        break;

      case "CourseUnitRule":
        childrenIds.add(rule.get("courseUnitGroupId").getAsString());
        break;

      // Recursively process subrules until a ModuleRule or CourseUnitRule is found.
      case "CompositeRule":
        JsonArray subRules = rule.getAsJsonArray("rules");
        for (JsonElement subRuleElement : subRules) {
          JsonObject subRule = subRuleElement.getAsJsonObject();
          processRule(subRule, childrenIds);
        }
        break;

      // Recursively process subrules until a ModuleRule or CourseUnitRule is found.
      case "CourseUnitCountRule":
        subRules = rule.getAsJsonArray("rules");
        for (JsonElement subRuleElement : subRules) {
          JsonObject subRule = subRuleElement.getAsJsonObject();
          processRule(subRule, childrenIds);
        }
        break;
      
      // Recursively process subrule until a ModuleRule or CourseUnitRule is found.
      case "CreditsRule":
        JsonObject subRule = rule.getAsJsonObject("rule");
        processRule(subRule, childrenIds);
        
        break;

      // At the moment these two rules are ignored.
      case "AnyCourseUnitRule":
        break;
        
      case "AnyModuleRule":
        break;

      default:
        throw new IllegalArgumentException("Unknown rule type: " + type);
    }
  }
    
}
