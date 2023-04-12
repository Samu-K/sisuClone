package fi.tuni.prog3.sisu.backend;

import java.util.ArrayList;

/**
 * DegreeProgramme class. StudyModules that belong to a degree programme are stored to 
 * studyModules ArrayList.
 */
public class DegreeProgramme extends DegreeModule {

  private ArrayList<StudyModule> studyModules;

  public DegreeProgramme(String name, String id, String groupId, int minCredits) {
    super(name, id, groupId, minCredits);
    //TODO Auto-generated constructor stub
  }

  public ArrayList<StudyModule> getStudyModules() {
    return studyModules;
  }

  public void addStudyModule(StudyModule studyModule) {
    studyModules.add(studyModule);
  }
}
