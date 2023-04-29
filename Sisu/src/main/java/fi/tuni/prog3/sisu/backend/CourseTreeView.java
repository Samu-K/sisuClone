package fi.tuni.prog3.sisu.backend;

import java.util.ArrayList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Class for creating the TreeView for the course structure.
 */
public class CourseTreeView {

  // Helper recursive function to build the TreeItems for each StudyModule and its children
  private static TreeItem<String> createTreeItem(StudyModule parentStudyModule) {
    /*
     * How this function works:
     * 
     * 1. Create a new TreeItem for the parentStudyModule
     * 2. Get the parentStudyModules children
     * 3. For each child:
     *   3.1 If the child is a Course, create a new TreeItem for it and add it to the parents tree
     *   3.2 If the child is a StudyModule, generate the TreeItems for it and its children
     *       requirsively
     * 4. Return the parent TreeItem 
     */

    TreeItem<String> parent = new TreeItem<>(parentStudyModule.getName());

    ArrayList<DegreeModule> children = parentStudyModule.getChildren();
    for (DegreeModule child : children) {
      
      if (child instanceof Course) {
        // If the child is a Course, add it as a new TreeItem to the parents tree
        parent.getChildren().add(new TreeItem<>(child.getName()));
      } else if (child instanceof StudyModule) {
        // If the child is a StudyModule, create a new TreeItem for it and add it to the parents
        // tree
        parent.getChildren().add(createTreeItem((StudyModule) child));
      }
    }

    return parent;
  }


  /**
   * Function to create the TreeView of all courses and modules under a module. The first module is
   * defined by startingModule.

   * @param startingModule the root of the tree
   * @return TreeView object
   */
  public static TreeView<String> createCourseTree(StudyModule startingModule) {
    TreeItem<String> root = createTreeItem(startingModule);
    TreeView<String> treeView = new TreeView<>(root);
    return treeView;
  }
}
