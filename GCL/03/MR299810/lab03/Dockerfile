FROM ubuntu:latest

RUN apt-get update &&\
    apt-get upgrade &&\
    apt-get install nodejs &&\
    npx create-react-app my-app
    
WORKDIR /my-app

CMD ["npm","run","build"]
    
