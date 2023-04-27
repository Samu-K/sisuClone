package fi.tuni.prog3.sisu.backend;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;


public class ObjectBuilders {
    
    /**
     * Chooses to create either Course or StudyModule object based on JSON data
     * @param object JsonObject to get information from
     * @return 
     */
    public DegreeModule chooseObjectType(JsonObject object) {
        
        JsonElement typeElement = object.get("type");
        
        if (typeElement != null) {
            
            String type = typeElement.getAsString();
            
            if (type.equals("StudyModule")
                    || type.equals("DegreeProgramme")
                    || type.equals("GroupingModule")) {
                return createStudyModule(object);
            }
            
            
        }
        
        else {
            
            if (object.get("rule") == null && object.get("code") != null) {
                return createCourse(object);
            }
        }
        
        return null;
    }
    /**
     * Creates a new course object
     * @param object JsonObject form of the course data
     * @return returns the created course object
     */
    public DegreeModule createCourse(JsonObject object) {
        
        
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
     * Creates a new studyModule object 
     * @param object JsonObject with StudyModule data
     * @return returns the created StudyModule object
     */
    public DegreeModule createStudyModule(JsonObject object) {
        
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
            JsonObject creditsObject = object.getAsJsonObject("targetCredits");
            JsonElement minCreditsElement = creditsObject.get("min");
            int minCredits = minCreditsElement.getAsInt();
        
        //get info about mandatory 
            JsonObject ruleObject = object.getAsJsonObject("rule");
            
            if (ruleObject.get("type").getAsString().equals("CreditsRule")) {
            ruleObject = ruleObject.getAsJsonObject("rule");
            }
            
            JsonElement typeElement = ruleObject.get("type");
        
            boolean isMandatory=false;
            
            if (typeElement.getAsString().equals("CompositeRule")) {
               
                JsonElement allMandatory = ruleObject.get("allMandatory");
                
                
                isMandatory = allMandatory.getAsBoolean();
            }
            
        
        //get child groupids
        ModuleChildrenGetter children = new ModuleChildrenGetter(groupId);
        ArrayList<String> childGroupIds = new ArrayList<>();
        childGroupIds = children.returnGroupIds();
        
        StudyModule newStudyModule = new StudyModule(name, id, groupId, minCredits, isMandatory, childGroupIds);
        
        return newStudyModule;
    }
}

