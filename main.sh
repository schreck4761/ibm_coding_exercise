./gradlew bootBuildImage --imageName=schreck/payrollservice
# ./gradlew build
# docker build -t schreck/payrollservice .
docker run -p 8080:8080 -t schreck/payrollservice