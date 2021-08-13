# Switch-Option-Checker

This library is made to collect multiple if options in a map and determine the appropriate return format for each case.

## Example

```java
// If a Supplier type case is desired, 
// SwitchOption.<Class type to be compared, Class type to be returned when a suitable case is found>

final Supplier<String> supplierName = () -> "muhammed";
final Supplier<String> supplierSurname = () -> "alagoz";

final var supplierNameOptionalValue = SwitchOption.<User, String>of()
        .match(user -> user.getName().equals("muhammed"))
        .then(supplierName)

        .match(user -> user.getName().equals("alagoz"))
        .then(supplierSurname)

        .get(user);

assertTrue(supplierNameOptionalValue.isPresent());
assertEquals(supplierNameOptionalValue.get(), "muhammed");

```

### Class Types that can be run in the appropriate case state
```java
//my obj 
final User user = new User("muhammed", "alagoz", 30, new Address("Gaziantep", "Şehitkamil"));
```

- Supplier: If a Supplier type case is desired. 
  then method code;
  ```java
    final Optional<String> result = SwitchOption.<User, String>of()
        .match(user -> user.getName().equals("muhammed"))
        .then(() -> "muhammed")
        .get(user);
  ```
- Runnable: If a Runnable type case is desired. 
  then method code;
  ```java
    SwitchOption.<User, String>of()
        .match(user -> user.getName().equals("muhammed"))
        .then(() -> { System.out.println("Task is running"); })
        .get(user);
  ```
- ReferenceResult: If a Reference object type case is desired. 
  then method code;
  ```java
    final var company = new Company("ALAGOZ LTD");

    final Optional<Company> result = SwitchOption.<User, Company>of()
        .match(user -> user.getName().equals("muhammed"))
        .then(company)
        .get(user);
- FunctionResult: If a FunctionResult type case is desired. 
  then method code;
  ```java
    final Function<User, String> userFullName = user -> user.getName() + " " + user.getSurname();

    final Optional<String> result = SwitchOption.<User, String>of()
        .match(user -> user.getName().equals("muhammed"))
        .then(userFullName)
        .get(user);
  ```
