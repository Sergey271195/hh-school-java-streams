package tests;

import common.Person;
import common.TestCaseClass;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCase20 extends TestCaseClass<Collection<Person>> {

    public String convertPersonToString(Person person) {

        String result = "";
        if (person.getSecondName() != null) {
            result += person.getSecondName();
        }

        if (person.getFirstName() != null) {
            result += " " + person.getFirstName();
        }

        if (person.getSecondName() != null) {
            result += " " + person.getSecondName();
        }
        return result;
    }

    @Override
    public void execute(Collection<Person> persons) {
        persons.stream().collect(Collectors.toMap(
                Person::getId, person -> convertPersonToString(person), (a, b) -> a));
    }
}
