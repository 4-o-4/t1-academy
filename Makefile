SRC_KAFKA = ./task-kafka/

task-kafka:
	@mvn package -pl task-kafka/metrics-consumer,task-kafka/metrics-producer
	cd $(SRC_KAFKA) && docker-compose up -d

clean:
	@mvn clean

task-kafka-clean: clean
	-cd $(SRC_KAFKA) && docker-compose down
	-docker rm `docker ps -qa`
	-docker rmi -f `docker images -qa`
	-docker volume rm `docker volume ls -q`

.PHONY: task-kafka
