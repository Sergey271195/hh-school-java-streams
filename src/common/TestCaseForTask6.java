package common;

import common.Area;
import common.Person;
import common.TestCaseClass;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class TestCaseForTask6 extends TestCaseClass<Collection<Person>> {

  public Map<Integer, Set<Integer>> personAreaIds;
  public List<Area> areas;

  public TestCaseForTask6(Map<Integer, Set<Integer>> personAreaIds, List<Area> areas) {
    this.personAreaIds = personAreaIds;
    this.areas = areas;
  }

}
