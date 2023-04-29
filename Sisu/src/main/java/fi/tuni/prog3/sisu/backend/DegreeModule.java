/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit template
 */

package fi.tuni.prog3.sisu.backend;

/**
 *An abstract class for storing information on Modules and Courses.
 */
public abstract class DegreeModule {
  private String name;
  private String id;
  private int minCredits;
    
  /**
   * A constructor for initializing the member variables.
   *
   * @param name name of the Module or Course.
   * @param id id or groupId of the Module or Course.
   * @param minCredits minimum credits of the Module or Course.
   */
  public DegreeModule(String name, String id, 
          int minCredits) {
      
    this.name = name;
    this.id = id;
    this.minCredits = minCredits;
  }
  
  /**
   * Returns the name of the Module or Course.
   *
   * @return name of the Module or Course.
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Returns the id or groupId of the Module or Course.
   *
   * @return id of the Module or Course.
   */
  public String getId() {
    return this.id;
  }
  
  
  /**
   * Returns the minimum credits of the Module or Course.
   *
   * @return minimum credits of the Module or Course.
   */
  public int getMinCredits() {
    return this.minCredits;
  }
}
