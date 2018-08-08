# withlombok

- Getter
- Setter
- EqualsAndHashCode
- ToString
- Builder
- NoArgsConstructor
- AllArgsConstructor
- RequiredArgsConstructor
- Cleanup
- Value
- Data
- Log
- SneakyThrows

[lombok-website](https://projectlombok.org/)

## build

### ant
```
ant clean manual
```

### maven
```
mvn clean install
```

### gradle
```
gradle build
```

## run

### ant
```
java -jar target/withlombok-0.0.1-SNAPSHOT.jar 
```

### maven
```
java -jar target/withlombok-0.0.1-SNAPSHOT.jar
```

### gradle
```
java -jar build/libs/withlombok-0.0.1-SNAPSHOT.jar
```


## delombok
```
java -jar ~/.m2/repository/org/projectlombok/lombok/1.18.2/lombok-1.18.2.jar delombok src -d src-delomboked
```