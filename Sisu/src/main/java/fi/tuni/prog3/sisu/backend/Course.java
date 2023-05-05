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
   * @param id id or groupId of the course.
   * @param minCredits minimum credits of the Course.
   * @param isMandatory whether the Course is mandatory or not.
   * @param isCompleted whether the Course is completed or not.
   */
  public Course(String name, String id, int minCredits, boolean isMandatory,
      boolean isCompleted) {
    super(name, id, minCredits);
    this.isMandatory = isMandatory;
    this.isCompleted = isCompleted;

  }
  /**
  * Returns boolean for if the course is completed.

  * @return true or false based on if the course is completed or not
  */
  public boolean isCompleted() {
    return isCompleted;
  }

  /**
  * Switches the completion state for the course.
  
  */
  public void toggleCompleted() {
    isCompleted = !isCompleted;
  }

  /**
  * Returns boolean for is the course mandatory.

  * @return boolean value is the course mandatory or not
  */
  public boolean isMandatory() {
    return isMandatory;
  }
}
