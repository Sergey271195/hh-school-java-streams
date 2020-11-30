package tests;

import common.Person;
import common.TestCaseClass;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TestCase6 extends TestCaseClass<Collection<Person>> {

  @Override
  public void execute(Collection<Person> persons) {
    persons.stream().sorted(
            Comparator.comparing(Person::getSecondName)
                    .thenComparing(Person::getFirstName)
                    .thenComparing(Person::getCreatedAt)
            ).collect(Collectors.toList());
  }
}
