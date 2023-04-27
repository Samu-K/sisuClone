
package fi.tuni.prog3.sisu.backend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class ModuleChildrenGetter {
    
    private JsonObject moduleJson;
    
    /**
     * Builder with DegreeModule parameter
     * @param module DegreeModule whose children are fetched
     */
    public ModuleChildrenGetter(DegreeModule module) {
        
        
        String id = module.getId();
        
        String url = String.format("https://sis-tuni.funidata.fi/kori/api/modules/%s", id);
        
        ApiJsonGetter jsonGetter = new ApiJsonGetter();
        
  
        moduleJson = jsonGetter.getJsonFromApi(url);
        
        
    }
    
    /**
     * Builder with groupId parameter
     * @param groupId for getting data from API
     */
    public ModuleChildrenGetter(String groupId) {
        String url = String.format("https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=%s&universityId=tuni-university-root-id", groupId);
        
        ApiJsonGetter jsonGetter = new ApiJsonGetter();
        
  
        moduleJson = jsonGetter.getJsonFromApi(url);
    }
    
    /**
     * Creates an ArrayList with children GroupIds for the module 
     * @return ArrayList 
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
                
            
            }
            
            else if (childRuleObject.get("type").getAsString().equals("ModuleRule")) {
                
                 groupIdElement = childRuleObject.get("moduleGroupId");            }
            
            else {
                continue;
            }
            
            
            
            
            String groupId = groupIdElement.getAsString();
            
            groupIdList.add(groupId);
        }
        
        return groupIdList;
                
        
    }
    
}
