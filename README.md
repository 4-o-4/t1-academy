# t1-academy

#### Method execution time tracking system ([task-aop](https://github.com/4-o-4/t1-academy/tree/main/task-aop))

#### Spring Boot Starter for HTTP request logging ([task-starter](https://github.com/4-o-4/t1-academy/tree/main/task-starter))
Dependency
```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>t1-academy-spring-boot-starter</artifactId>
</dependency>
```
> **Note:** automatic logging configuration (disabled by default) is activated using the **log.level** property

#### Monitoring system using Spring Kafka ([task-kafka](https://github.com/4-o-4/t1-academy/tree/main/task-kafka))
```makefile
# Start the application
make task-kafka

# Stop
make task-kafka-clean
```
API for viewing metrics [localhost:8081/metrics](http://localhost:8081/metrics)

#### Ressources
- [Аспектно-ориентированное программирование](http://www.k-press.ru/CS/2003/4/AOP2/AOP.asp)
