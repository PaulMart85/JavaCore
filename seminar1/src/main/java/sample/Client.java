package sample;

import regular.*;

/**
 https://www.docker.com/products/docker-desktop/
 https://hub.docker.com/

 javac -sourcepath ./java  -d out java/sample/Client.java
 *
 java -classpath ./out sample.Client
 *
 javadoc -encoding utf8 -d docs -sourcepath ./java -cp ./out -subpackages regular
 * */

/**

 FROM bellsoft/liberica-openjdk-alpine
 COPY ./java ./src
 RUN mkdir ./out
 RUN javac -sourcepath ./src -d out src/sample/Client.java
 CMD  java -classpath ./out sample.Client

 FROM bellsoft/liberica-openjdk-alpine as BuildProject
 WORKDIR /app
 COPY ./java ./src
 RUN mkdir ./out
 RUN javac -sourcepath ./src -d out ./src/sample/Client.java

 FROM scratch as OutputFiles
 COPY --from=BuildProject /app/out /bin

 >> docker buildx build --output type=local,dest=. .

 FROM bellsoft/liberica-openjdk-alpine
 WORKDIR /app
 COPY ./bin .
 CMD java -classpath . sample.Client

 >> docker build . -t runjavaapp:v1
 >> docker run runjavaapp:v1

 */
public class Client {

    public static void main(String[] args) {

        Shape circle = new Circle();
        System.out.println("\nJust a circle:");
        circle.draw();

        System.out.println("\nThe circle with red border:");
        Shape redCircle = new RedShapeDecor(circle);
        redCircle.draw();
        System.out.println("\nThe circle with green border:");
        Shape greenCircle = new GreenShapeDecor(circle);
        greenCircle.draw();


        System.out.println("\nThe triangle with red border:");
        new RedShapeDecor(new Triangle()).draw();
        System.out.println("\nThe triangle with green border:");
        new GreenShapeDecor(new Triangle()).draw();
    }
}
