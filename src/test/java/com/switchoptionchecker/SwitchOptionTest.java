package com.switchoptionchecker;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Muhammed ALAGOZ
 */
class SwitchOptionTest {
    User user = new User("muhammed", "alagoz", 30, new Address("Gaziantep", "Şehitkamil"));

    @Test
    void shouldMatchCaseReturnThenSupplierName() {
        final Supplier<String> supplierName = () -> "muhammed";
        final Supplier<String> supplierSurname = () -> "alagoz";

        final var supplierNameValue = SwitchOption.<User, String>of()
                .match(user -> user.getName().equals("muhammed"))
                .then(supplierName)

                .match(user -> user.getName().equals("alagoz"))
                .then(supplierSurname)

                .get(user);

        assertTrue(supplierNameValue.isPresent());
        assertEquals(supplierNameValue.get(), "muhammed");
    }

    @Test
    void shouldMatchCaseReturnThenSupplierSurname() {
        final Supplier<String> supplierName = () -> "muhammed";
        final Supplier<String> supplierSurname = () -> "alagoz";

        final var supplierSurnameValue = SwitchOption.<User, String>of()
                .match(user -> user.getSurname().equals("muhammed"))
                .then(supplierName)

                .match(user -> user.getSurname().equals("alagoz"))
                .then(supplierSurname)

                .get(user);

        assertTrue(supplierSurnameValue.isPresent());
        assertEquals(supplierSurnameValue.get(), "alagoz");
    }

    @Test
    void shouldMatchBooleanCaseReturnThenSupplierSurname() {
        final Supplier<String> supplierName = () -> "muhammed";
        final Supplier<String> supplierSurname = () -> "alagoz";

        final var supplierSurnameValue = SwitchOption.<User, String>of()
                .match(user.getSurname().equals("muhammed"))
                .then(supplierName)

                .match(user.getSurname().equals("alagoz"))
                .then(supplierSurname)

                .get(user);

        assertTrue(supplierSurnameValue.isPresent());
        assertEquals(supplierSurnameValue.get(), "alagoz");
    }

    @Test
    void shouldMatchCaseReturnThenReferenceResult() {
        final var company = new Company("ALAGOZ LTD");
        final var company2 = new Company("ALI LTD");

        final var optionalCompany = SwitchOption.<User, Company>of()
                .match(user -> user.getName().equals("muhammed"))
                .then(company)

                .match(user -> user.getName().equals("ali"))
                .then(company2)

                .get(user);

        assertTrue(optionalCompany.isPresent());
        assertEquals(optionalCompany.get().getName(), "ALAGOZ LTD");
    }

    @Test
    void shouldMatchCaseReturnThenRunnable() {
        final Function<User, String> userFullName = user -> user.getName() + " " + user.getSurname();
        final Function<User, String> userFullNameAndAge = user -> user.getName() + " " + user.getSurname() + " " + user.getAge();

        final var optionalCompany = SwitchOption.<User, String>of()
                .match(user -> user.getName().equals("ali"))
                .then(userFullNameAndAge)

                .match(user -> user.getName().equals("muhammed"))
                .then(userFullName)

                .get(user);

        assertTrue(optionalCompany.isPresent());
        assertEquals(optionalCompany.get(), "muhammed alagoz");
    }
}


class User {
    private String name;
    private String surname;
    private Integer age;
    private Address address;

    public User(String name, String surname, Integer age, Address address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }
}

class Address {
    private String city;
    private String district;

    public Address(String city, String district) {
        this.city = city;
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }
}

class Company {
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
