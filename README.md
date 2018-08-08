# lombok demo

[![CircleCI](https://circleci.com/gh/rslvn/lombok.svg?style=svg)](https://circleci.com/gh/rslvn/lombok)
[![Build Status](https://travis-ci.org/rslvn/lombok.svg?branch=master)](https://travis-ci.org/rslvn/lombok)
[![Coverage Status](https://coveralls.io/repos/github/rslvn/lombok/badge.svg?branch=master)](https://coveralls.io/github/rslvn/lombok?branch=master)

## Build
```
mvn clean install
```

## Run

Follow:
- [withlombok](withlombok/README.md)
- [withoutlombok](withlombok/README.md)

## Links
- [lombok](https://projectlombok.org/)
- [java-docs](https://projectlombok.org/api/allclasses-noframe.html)
- [intro-to-project-lombok](https://www.baeldung.com/intro-to-project-lombok)
- [5-tips-for-using-lombok-in-production](https://dzone.com/articles/5-tips-for-using-lombok-in-production)

## Extras
- [lombok-pg](https://github.com/peichhorn/lombok-pg)
- [pojo-tester](https://www.pojo.pl/)


#### Some Hints

##### Skipping JaCoCo execution due to missing execution data file.
- Test file names should end with "Test" not "Tests". 
> Should be ApplicationTest.java, not ApplicationTests.java