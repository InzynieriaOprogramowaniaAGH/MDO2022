FROM ubuntu

RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y curl git
ARG DEBIAN_FRONTEND=noninteractive

RUN curl -sL https://deb.nodesource.com/setup_14.x -o setup_14.sh
RUN sh ./setup_14.sh
RUN apt-get install -y nodejs
RUN npx create-react-app test-app-container

WORKDIR /test-app-container

CMD ["npm", "run", "build"]
