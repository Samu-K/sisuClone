package fi.tuni.prog3.sisu.backend;

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
      childModules = new ArrayList<DegreeModule>();
      for (String childGroupId : childGroupIds) {
        DegreeModule childModule = ObjectBuilders.buildCourseOrStudyModule(childGroupId);
        childModules.add(childModule);
      }
      childrenHaveBeenAdded = true;
    }
  }
 
  private ArrayList<Course> getCourses() {
    ArrayList<Course> courses = new ArrayList<Course>();
    for (DegreeModule dm : childModules) {
      if (dm instanceof Course) {
        courses.add((Course) dm);
      }
    }
    return courses;
  }

  private ArrayList<StudyModule> getSubModules() {
    ArrayList<StudyModule> subModules = new ArrayList<StudyModule>();
    for (DegreeModule dm : childModules) {
      if (dm instanceof StudyModule) {
        subModules.add((StudyModule) dm);
      }
    }
    return subModules;
  }


  /**
   * A constructor for initializing the member variables.

   * @param name name of the StudyModule.
   * @param id id or groupId of the StudyModule.
   * @param minCredits minimum credits of the StudyModule.
   * @param isMandatory whether the StudyModule is mandatory or not.
   * @param childGroupIds group ids of the children of this StudyModule.
   */
  public StudyModule(String name, String id, int minCredits, boolean isMandatory,
      ArrayList<String> childGroupIds) {
    super(name, id, minCredits);
    
    this.isMandatory = isMandatory;
    this.childGroupIds = childGroupIds;
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