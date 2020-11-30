package tasks;

import common.Person;
import common.PersonService;
import common.TestCaseClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class TestCase3 extends TestCaseClass<List<Integer>> {

  @Override
  public void execute(List<Integer> personIds) {
    Set<Person> persons = PersonService.findPersons(personIds);
    List<Person> result = new ArrayList<>();
    long start = System.currentTimeMillis();
    for (int id: personIds) {
      result.add(persons.stream().filter(person -> person.getId() == id).findFirst().get());
      if (System.currentTimeMillis() - start > 8_000) {
        System.out.println("You don't want to wait for this to finish...");
        System.out.println("Execution time -> infinity");
        break;
      }
    }
  }
}
