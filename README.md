# numbers-to-words

## How to run
### Requirements 
- Java 19 
- Maven 3.9

### How to run
- Navigate to root directory and run `mvn compile`
- After compiling, package the JAR solution using `mvn package`
- Run the application by using the following command: ` java -jar target/number-to-words-2-1.0-SNAPSHOT.jar {number}`

### How to run unit tests
- Navigate to root directory and run `mvn test`

## Code Structure
- Interface `INumberConvertor` is used to help handle the conversion of the numbers
- Conversion is broken down in to the following pattern
  - If number == 0, return `zero`
  - If number is < 20, lookup in the `Helpers.UNITS` to find the number in string format
  - If number is < 100 lookup in the `Helpers.TENS` to find the tens place and then look for the one's place
    - If the tens place is not divisible by 10, add the '-'
  - If the number is < 1000 lookup in the `Helpers.TENS` to get the thousand number value
    - If the number is not divisible by 100 add the `and`
    - Call the function again for the remaining places
  - If the number is < 100000
    - Call the same function again for the 1000 > places
    - If the number is not divisible by 1000 & the number % 1000 < 100 put the `and` otherwise put the ',' 
    - Call the function again for the remaining places
- Primitive testing is done in `NumberConvertorTest` to check for numbers > 1000, numbers < 1000, provided sample
input cases and exception handling
- Basic exception handling is done in `NumbersToWordsCLI`
