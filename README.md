# lombok demo

[![CircleCI](https://circleci.com/gh/rslvn/lombok.svg?style=svg)](https://circleci.com/gh/rslvn/lombok)
[![Build Status](https://travis-ci.org/rslvn/lombok.svg?branch=master)](https://travis-ci.org/rslvn/lombok)
[![Coverage Status](https://coveralls.io/repos/github/rslvn/lombok/badge.svg?branch=master)](https://coveralls.io/github/rslvn/lombok?branch=master)

## Build
```
mvn clean install
```

## Run
#### Docs
Follow:
- [withlombok](withlombok/README.md)
- [withoutlombok](withlombok/README.md)

#### Docker
> Firstly, execute Build step
```
docker-compose build
docker-compose down
docker-compose up -d
```

##### connect containers
###### withlombok
```
~$ docker exec -it withlombok /bin/sh

```
###### withoutlombok
```
~$ docker exec -it withoutlombok /bin/sh
```

##### container logs
###### withlombok
```
~$docker logs withlombok -f

```

###### withoutlombok
```
~$docker logs withoutlombok -f
```

## Test

#### container IPs
```
docker network inspect lombok_default
```

### User Resource

> learn the IPs from "container IPs" step. Replace the IP of the withlombok or withoutlombok with IP that is in following command's URL

- list users
```
curl -H "Content-Type: application/json" -X GET http://172.21.0.2:8080/user/list
```

- put user
```
curl -H "Content-Type: application/json" -X PUT http://172.21.0.2:8080/user/ -d '
{"id":1254265746892828672,"name":"sample name"}
'
```

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