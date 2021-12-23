FROM ubuntu:latest

RUN apt-get update &&\
    apt-get upgrade &&\
    curl -sL https://deb.nodesource.com/setup_16.x -onodesource_setup.sh &&\
    apt-get install nodejs &&\
    npm cache clean -f
    npm install -g n
    n stable
    n latest
    npx create-react-app my-app
    
WORKDIR /my-app

CMD ["npm","run","build"]
    
