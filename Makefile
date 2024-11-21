build-docker:
	./gradlew distTar
	docker build -t "vacxe/airports-info-api" .