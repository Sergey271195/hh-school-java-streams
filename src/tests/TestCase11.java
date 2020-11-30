package tests;

import common.Company;
import common.TestCaseClass;
import common.Vacancy;

import java.util.Collection;
import java.util.stream.Collectors;

public class TestCase11 extends TestCaseClass<Collection<Company>> {

  @Override
  public void execute(Collection<Company> companies) {
    companies.stream().flatMap(company -> company.getVacancies().stream())
            .map(Vacancy::getTitle).collect(Collectors.toSet());
  }
}
