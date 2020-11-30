package tasks;

import common.Area;
import common.Person;
import common.TestCaseForTask6;

import java.util.*;
import java.util.stream.Collectors;

class TestCase10 extends TestCaseForTask6 {

  public TestCase10(Map<Integer, Set<Integer>> personAreaIds, List<Area> areas) {
    super(personAreaIds, areas);
  }

  @Override
  public void execute(Collection<Person> persons) {

    Set<String> result = new HashSet<>();
    long start = System.currentTimeMillis();
    for (int key: personAreaIds.keySet()) {
      for (int areaId: personAreaIds.get(key)) {
        String personName = persons.stream().filter(person -> person.getId() == key).findFirst().get().getFirstName();
        String areaName = this.areas.stream().filter(area -> area.getId() == areaId).findFirst().get().getName();
        result.add(personName + " - " + areaName);
      }
      if (System.currentTimeMillis() - start > 8_000) {
        System.out.println("You don't want to wait for this to finish...");
        System.out.println("Execution time -> infinity");
        break;
      }
    }
  }
}
