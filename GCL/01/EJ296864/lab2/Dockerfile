FROM ubuntu:20.04 AS development

RUN apt-get update && apt-get upgrade
RUN apt-get install -yq nodejs npm
RUN npx create-react-app my-app-container

WORKDIR /my-app-container

CMD ["npm", "run", "build"]
