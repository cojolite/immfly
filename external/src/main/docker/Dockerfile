FROM openjdk:11
RUN mkdir /opt/app
COPY @project.build.finalName@.jar /opt/app
CMD ["java", "-jar", "/opt/app/@project.build.finalName@.jar"]
