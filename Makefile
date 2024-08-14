SRC = ./task

.PHONY: task-aop
task-aop:
	@mvn package -pl task-aop && \
	cd $(SRC)-aop && mvn spring-boot:run

.PHONY: task-kafka
task-kafka:
	@mvn package -pl task-kafka/metrics-consumer,task-kafka/metrics-producer && \
	cd $(SRC)-kafka && docker-compose up -d

.PHONY: task-security
task-security:
	@mvn package -pl task-security && \
	cd $(SRC)-security && docker build -t t1-academy-security . && \
	docker run -d --name postgres -p 5432:5432 t1-academy-security && \
	mvn spring-boot:run

clean:
	@mvn clean

task-kafka-clean: clean
	-cd $(SRC)-kafka && docker-compose down
	-docker rm `docker ps -qa`
	-docker rmi -f `docker images -qa`
	-docker volume rm `docker volume ls -q`

task-security-clean: clean
	-cd $(SRC)-security && mvn spring-boot:stop && docker stop postgres
	-docker rm `docker ps -qa`
	-docker rmi -f `docker images -qa`
	-docker volume rm `docker volume ls -q`
