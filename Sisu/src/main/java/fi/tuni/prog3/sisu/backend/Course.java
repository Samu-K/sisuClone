package fi.tuni.prog3.sisu.backend;

/**
 * A class for storing information on Courses.
 */
public class Course extends DegreeModule {

  private boolean isCompleted;
  private boolean isMandatory;

  /**
   * A constructor for initializing a Course object.

   * @param name name of the Course.
   * @param id Course code.
   * @param groupId group id of the Course.
   * @param minCredits minimum credits of the Course.
   * @param isMandatory whether the Course is mandatory or not.
   * @param isCompleted whether the Course is completed or not.
   */
  public Course(String name, String id, String groupId, int minCredits, boolean isMandatory,
      boolean isCompleted) {
    super(name, id, groupId, minCredits);
    this.isMandatory = isMandatory;
    this.isCompleted = isCompleted;

  }
  
  public boolean isCompleted() {
    return isCompleted;
  }

  public void toggleCompleted() {
    isCompleted = !isCompleted;
  }

  public boolean isMandatory() {
    return isMandatory;
  }
}
