# Switch-Option-Checker

This library is made to collect multiple if options in a map and determine the appropriate return format for each case.

## How to Implement

To get a Git project into your build:
###Gradle
```groovy
//Step 1. Add the JitPack repository to your build file
//Add it in your root build.gradle at the end of repositories:
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

//Step 2. Add the dependency
dependencies {
  implementation 'com.github.muhammedalagoz:switch-option-checker:Tag'
}
```

###Maven
```xml
//Step 1. Add the JitPack repository to your build file
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

//Step 2. Add the dependency
<dependency>
  <groupId>com.github.muhammedalagoz</groupId>
  <artifactId>switch-option-checker</artifactId>
  <version>Tag</version>
</dependency>
```
###sbt
```groovy
//Step 1. Add the JitPack repository to your build file
//Add it in your build.sbt at the end of resolvers:

resolvers += "jitpack" at "https://jitpack.io"


//Step 2. Add the dependency
libraryDependencies += "com.github.muhammedalagoz" % "switch-option-checker" % "Tag"

```
###leiningen
```groovy
//Step 1. Add the JitPack repository to your build file
//Add it in your project.clj at the end of repositories:

:repositories [["jitpack" "https://jitpack.io"]]


//Step 2. Add the dependency
:dependencies [[com.github.muhammedalagoz/switch-option-checker "Tag"]]

```


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
