package fi.tuni.prog3.sisu.backend;

import com.google.gson.JsonObject;
import fi.tuni.prog3.sisu.api.Interface;
import java.util.ArrayList;

/**
 * StudyModule class. Courses and other StudyModules that belong to a StudyModule are stored to 
 * coursesAndSubModules ArrayList.
 */
public class StudyModule extends DegreeModule {

  private ArrayList<DegreeModule> childModules;
  private boolean isMandatory;
  private ArrayList<String> childGroupIds;
  private boolean childrenHaveBeenAdded = false;

  private void getChildrenFromApi() {
    if (!childrenHaveBeenAdded) {
      for (String childGroupId : childGroupIds) {
        JsonObject childJsonObject = Interface.getDegreeModuleById(childGroupId);
        // TODO: Build the object in ObjectBuilders class
        // Create a mock object for now
        DegreeModule childModule = new Course("Johdatus todennäköisyyslaskentaan", "MATH.APP.210", "tut-xxx-xxx-xxx", 5, true, false); // = ObjectBuilders.buildDegreeModule(childJsonObject);
        childModules.add(childModule);
      }
      childrenHaveBeenAdded = true;
    }
  }

  /**
   * A constructor for initializing the member variables.

   * @param name name of the StudyModule.
   * @param id id of the StudyModule.
   * @param groupId group id of the StudyModule.
   * @param minCredits minimum credits of the StudyModule.
   * @param isMandatory whether the StudyModule is mandatory or not.
   * @param childGroupIds group ids of the children of this StudyModule.
   */
  public StudyModule(String name, String id, String groupId, int minCredits, boolean isMandatory,
      ArrayList<String> childGroupIds) {
    super(name, id, groupId, minCredits);
    
    this.isMandatory = isMandatory;
    this.childGroupIds = childGroupIds;
  }
  
  /**
   * Returns courses under this StudyModule.

   * @return ArrayList of courses under this StudyModule.
   */
  public ArrayList<Course> getCourses() {
    ArrayList<Course> courses = new ArrayList<Course>();
    for (DegreeModule dm : childModules) {
      if (dm instanceof Course) {
        courses.add((Course) dm);
      }
    }
    return courses;
  }

  /**
   * Returns StudyModules under this StudyModule.

   * @return ArrayList of StudyModules under this StudyModule.
   */
  public ArrayList<StudyModule> getSubModules() {
    ArrayList<StudyModule> subModules = new ArrayList<StudyModule>();
    for (DegreeModule dm : childModules) {
      if (dm instanceof StudyModule) {
        subModules.add((StudyModule) dm);
      }
    }
    return subModules;
  }

  public boolean isMandatory() {
    return isMandatory;
  }

  /**
   * Returns all children of this StudyModule recursively. If StudyModule is mandatory, all children
   * of its children are also returned.

   * @return ArrayList of all DegreeModules under this StudyModule.
   */
  public ArrayList<DegreeModule> getChildrenRecursively() {
    if (!childrenHaveBeenAdded) {
      getChildrenFromApi();
    }

    ArrayList<DegreeModule> children = new ArrayList<DegreeModule>();
    for (DegreeModule dm : childModules) {
      children.add(dm);
      if (dm instanceof StudyModule && ((StudyModule) dm).isMandatory) {
        children.addAll(((StudyModule) dm).getChildrenRecursively());
      }
    }
    return children;
  }

  /**
   * Returns children of this Studymodule. Courses are returned first, then StudyModules.

   * @return ArrayList of children of this StudyModule.
   */
  public ArrayList<DegreeModule> getChildren() {
    if (!childrenHaveBeenAdded) {
      getChildrenFromApi();
    }
    ArrayList<DegreeModule> children = new ArrayList<DegreeModule>();
    children.addAll(getCourses());
    children.addAll(getSubModules());
    return children;
  }
}