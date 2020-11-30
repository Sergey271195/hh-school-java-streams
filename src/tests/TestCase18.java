package tests;

import common.Person;
import common.TestCaseClass;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCase18 extends TestCaseClass<List<Person>> {

    public String convertPersonToString(Person person) {
        return Stream.of(person.getFirstName(), person.getSecondName(), person.getMiddleName())
                .filter(Objects::nonNull).collect(Collectors.joining(" "));
    }

    @Override
    public void execute(List<Person> persons) {
        persons.forEach(person -> convertPersonToString(person));
    }
}
