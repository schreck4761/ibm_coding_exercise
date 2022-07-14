./gradlew bootBuildImage --imageName=schreck/payrollservice/build
docker build -t schreck/payrollservice .
docker run -p 8080:8080 -t schreck/payrollservice