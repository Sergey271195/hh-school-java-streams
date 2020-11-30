package tests;

import common.Person;
import common.TestCaseClass;

import java.util.List;
import java.util.stream.Collectors;

public class TestCase17 extends TestCaseClass<List<Person>> {

    public String convertPersonToString(Person person) {

        //Expecting middle name somewhere
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
    public void execute(List<Person> persons) {
        persons.forEach(person -> convertPersonToString(person));
    }
}

