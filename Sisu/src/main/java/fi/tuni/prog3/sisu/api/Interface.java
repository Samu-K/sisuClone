package fi.tuni.prog3.sisu.api;

import java.util.ArrayList;

/**
 * Public interface to access information from the Sisu API from the rest of the program.
 */

public class Interface {
  public ArrayList<String> getDegreeProgrammeNames() {
    return DegreeProgrammeGetter.getDegreeProgramNamesFromApi("https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-2021&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit=1000");
  }
}
