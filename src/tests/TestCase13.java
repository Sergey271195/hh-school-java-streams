package tests;

import common.Person;
import common.TestCaseClass;

import java.util.List;
import java.util.stream.Collectors;

public class TestCase13 extends TestCaseClass<List<Person>> {

  @Override
  public void execute(List<Person> persons) {
    persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }
}
