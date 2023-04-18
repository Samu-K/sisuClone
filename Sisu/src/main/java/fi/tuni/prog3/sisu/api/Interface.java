package fi.tuni.prog3.sisu.api;

import java.util.TreeMap;

/**
 * Public interface to access information from the Sisu API from the rest of the program.
 */

public class Interface {
  public static TreeMap<String, String> getDegreeProgrammeNames() {
    return DegreeProgrammeGetter.getDegreeProgramNamesFromApi("https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-2021&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit=1000");
  }
}
