package tests;

import common.Person;
import common.PersonService;
import common.TestCaseClass;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestCase1 extends TestCaseClass<List<Integer>> {

  @Override
  public void execute(List<Integer> personIds) {
    Set<Person> persons = PersonService.findPersons(personIds);
    Map<Integer, Person> map = persons.stream()
            .collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> a));
    personIds.stream().map(id -> map.get(id)).collect(Collectors.toList());
  }

}
