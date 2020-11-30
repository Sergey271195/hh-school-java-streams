package tests;

import common.Person;
import common.TestCaseClass;

import java.util.List;
import java.util.stream.Collectors;

public class TestCase14 extends TestCaseClass<List<Person>> {

  @Override
  public void execute(List<Person> persons) {
    persons.remove(0);
    persons.stream().map(Person::getFirstName).collect(Collectors.toList());
  }
}
