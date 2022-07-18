FROM gitpod/workspace-full

USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install java 17.0.3-zulu && \
    sdk default java 17.0.3-zulu"

RUN docker run -d --name postgres --rm -p 5432:5432 -e POSTGRES_PASSWORD=password postgres