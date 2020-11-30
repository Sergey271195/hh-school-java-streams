package tests;

import common.Area;
import common.Person;
import common.TestCaseForTask6;

import java.util.*;
import java.util.stream.Collectors;

public class TestCase9 extends TestCaseForTask6 {

  public TestCase9(Map<Integer, Set<Integer>> personAreaIds, List<Area> areas) {
    super(personAreaIds, areas);
  }

  @Override
  public void execute(Collection<Person> persons) {

    Map<Integer, String> areasMap = this.areas.stream().collect(Collectors.toMap(Area::getId, Area::getName));
    Map<Integer, String> personsMap = persons.stream().collect(Collectors.toMap(Person::getId, Person::getFirstName));
    Set<String> result = new HashSet<>();
    for (int key: personAreaIds.keySet()) {
      for (int areaId: personAreaIds.get(key)) {
          result.add(personsMap.get(key) + " - " + areasMap.get(areaId));
      }
    }
  }
}
