FROM ubuntu:latest

RUN apt-get update &&\
    apt-get install -y nodejs &&\
    apt-get install -y npm &&\
    npx create-react-app my-app
    
WORKDIR /my-app

CMD ["npm","run","build"]
    
