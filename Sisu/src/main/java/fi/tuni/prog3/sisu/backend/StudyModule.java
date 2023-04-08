package fi.tuni.prog3.sisu.backend;

import java.util.ArrayList;

/**
 * StudyModule class. Courses and other StudyModules that belong to a StudyModule are stored to 
 * coursesAndSubModules ArrayList.
 */
public class StudyModule extends DegreeModule {

  private ArrayList<DegreeModule> coursesAndSubModules;

  public StudyModule(String name, String id, String groupId, int minCredits) {
    super(name, id, groupId, minCredits);
    //TODO Auto-generated constructor stub
  }
  
  public ArrayList<DegreeModule> getCoursesAndSubModules() {
    return coursesAndSubModules;
  }
}
