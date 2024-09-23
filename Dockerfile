ARG RUN_IMAGE=eclipse-temurin:21-jre-jammy
FROM ${RUN_IMAGE}

ARG TARGET_DIR=/opt/app
ARG SOURCE_DIR=build/distributions

COPY $SOURCE_DIR/*.tar application.tar
RUN mkdir $TARGET_DIR
RUN tar -xf application.tar -C $TARGET_DIR
RUN rm application.tar

ARG DOCKER_USER=app
RUN groupadd -r $DOCKER_USER && useradd -rg $DOCKER_USER $DOCKER_USER
USER $DOCKER_USER

ENV JAVA_OPTS="-Dfile.encoding=UTF-8"

EXPOSE 8080/tcp
EXPOSE 8085/tcp
CMD [ "/opt/app/application/bin/application" ]