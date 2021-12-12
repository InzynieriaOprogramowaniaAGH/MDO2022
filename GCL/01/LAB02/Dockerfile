FROM gradle:jdk11
RUN apt-get install git
RUN git clone https://github.com/howtoprogram/junit5-examples.git
WORKDIR junit5-examples/junit5-assertions-examples
RUN gradle build -x test
