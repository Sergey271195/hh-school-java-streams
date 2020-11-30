package tasks;

import common.*;
import tests.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Из коллекции компаний необходимо получить всевозможные различные названия вакансий
 */
public class Task7 implements Task {

  private Set<String> vacancyNames(Collection<Company> companies) {
    return companies.stream().flatMap(company -> company.getVacancies().stream())
            .map(Vacancy::getTitle).collect(Collectors.toSet());
  }

  @Override
  public boolean check() {
    System.out.println("\n-------- TASK 7 ----------\n");
    checkExecutionTime();
    Vacancy vacancy1 = new Vacancy(1, "vacancy 1");
    Vacancy vacancy2 = new Vacancy(2, "vacancy 2");
    Vacancy vacancy3 = new Vacancy(3, "vacancy 1");
    Company company1 = new Company(1, "company 1", Set.of(vacancy1, vacancy2));
    Company company2 = new Company(2, "company 2", Set.of(vacancy3));
    return vacancyNames(Set.of(company1, company2)).equals(Set.of("vacancy 1", "vacancy 2"));
  }

  public void checkExecutionTime() {

    List<Vacancy> vacancies = Stream.iterate(1, i -> i+1).limit(1_000_000)
            .map(id -> new Vacancy(id, "vacancy " + Math.random()*1000))
            .collect(Collectors.toList());

    List<Company> companies = Stream.iterate(1, i -> i+1).limit(500_000)
            .map(id -> new Company(id, "company " + id,
                    Set.of(vacancies.get(2 + (int)Math.random()*id), vacancies.get(0))
            )).collect(Collectors.toList());

    System.out.println("Testing execution time for companies list of size " + companies.size());
    new TestCase11().test(
            5, companies,
            "",
            "Using streams");
    new TestCase12().test(
            5, companies,
            "",
            "Using for loop");
  }
}

