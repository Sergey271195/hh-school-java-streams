package tasks;

import common.Area;
import common.Person;
import common.TestCaseForTask6;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class TestCase8 extends TestCaseForTask6 {

  public TestCase8(Map<Integer, Set<Integer>> personAreaIds, List<Area> areas) {
    super(personAreaIds, areas);
  }

  @Override
  public void execute(Collection<Person> persons) {

    Map<Integer, String> areasMap = this.areas.stream().collect(Collectors.toMap(Area::getId, Area::getName));
    Map<Integer, String> personsMap = persons.stream().collect(Collectors.toMap(Person::getId, Person::getFirstName));
    this.personAreaIds.keySet().stream().flatMap(key -> this.personAreaIds.get(key)
            .stream().map(value -> personsMap.get(key) + " - " + areasMap.get(value))).collect(Collectors.toSet());
  }
}
