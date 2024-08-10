Super minimalistic application built on the **Microservice Architecture**

There are six services so far
- Department Service
- Employee Service
- Organization Service
- Service Registry : This service registers all the other services, to enable service discovery.
- API Gateway - routes the client request to the appropriate microservice
- Config Server - Central hub for project specific configurations which are loaded by the microservices.

## Microservice Communication
In a Spring Context, microservices can communicate through different channels

1. **RestTemplate** - To make REST API calls from one microservice to another microservice
2. **Webclient** - Also makes REST API calls from one microservice to another microservice
3. **Spring Cloud OpenFeign** - A spring enabler used to make REST API calls from one microservice to another microservice


## Communication Styles
There are two main styles of communication

1. **Synchronous Communication**: The client sends a request and waits for a response from the service. The important point here is that the protocol(HTTP/HTTPS) is synchronous and the client code can only continue its task when it receives the HTTP server response. You can achieve this communication by using RestTemplate, Webclient and Spring Cloud Open Feign.

2. **Asynchronous Communication**: The client sends a request and does not wait for a response from the service. The client will continue executing its task, without waiting for a response from the service. This client does this using something called a Message Broker. You can achieve this communication by using RabbitMQ or Apache Kafka.

## Service Registry and Discovery
1. In a microservice, Service Registry and Discovery play an important role because we most likely run multiple instances of services, and we need a mechanism to call other services without hardcoding their hostnames or port numbers.

2. In addition to that, in Cloud environments, service instances may come up and go down at anytime. So we need some automatic service registration and discovery mechanism.

3. Spring Cloud addresses this problem by providing **Spring Cloud Netflix Eureka** project to enable Service Registry and Discovery.

Mostly, we have to create a separate spring boot project as a microservice(service-registry), which we will use Eureka server for, and we need to disable this server as the Eureka client. All the other services which will be registered in our service-registry need to be registered as `"eureka clients"`

## Load Balancing
Let's take this example. We have two instances of our Department Service running, and our Employee service makes an API call to the Department Service via Open Feign. But let's say one instance of the Department Service goes down, how do we ensure that all the incoming requests from the Employee service are automatically re-routed to the running instance of the Department Service. This is where load-balancing comes in. Spring Cloud Netflix comes with Spring Cloud Load Balancer out of the box. Whenever we use Open Feign in Conjunction with Eureka Server, Eureka Server comes with Spring Cloud Load Balancer. 

In order to implement load-balancing, we just have to change the url of our Open Feign with the service id that is provided in Eureka Server. For instance, we will make the Employee service's Open Feign client make a request to "DEPARTMENT-SERVICE" - since this is the Service ID provided by Eureka Server


## API Gateway
1. API Gateway provides a unified interface for a set of microservices so that clients don't need to know about all the details of microservices internals
2. API Gateway centralizes cross-cutting concerns like security, monitoring, rate limiting etc.
3. Spring Cloud provides **Spring Cloud Gateway** to create an API gateway. 

Let's take this example. We have multiple microservices. And each one has their own hostname and port. Now let's say our client makes a request to the API of each microservice. This means that our client needs to know the hostname and port of all the microservices, which is not ideal, especially in a large system. This is where an API gateway comes in, it sits between the client and the microservices, and the client makes a request through the API Gateway - which will then dynamically route the client's request to the appropriate microservice, based on some predefined rules. An API gateway can also be used for load balancing, and security.

Mostly in spring contexts, an API Gateway is a separate microservice which is registered as a Eureka Client to the Eureka Server(Service Registry). After setting up an API Gateway, all subsequent client requests should hit the Gateway endpoint for it to do the re-routing.

## Config Server
Consider that we have multiple services in our microservice project. And in order to scale our microservice we run multiple instances of the preferred service. Now, note that each microservice has their own configuration files. So microservice 1 has a config file, which is shared by its sub instances. If we want to change something in the said config file, we will have to restart microservice 1 as well as its instances, which is not really a good idea. Spring solves this problem with **Spring Cloud Config Server** such that we don't need to restart the microservice and its instances when we change the config file. We can do this for instance by calling the spring boot actuator `/refresh` API to reload the updated values from the config server. Basically we will restart the config server to load the new config of the related service, call the `/refresh`(POST) endpoint of the related service's actuator, and boom! the configuration will be loaded for the related service without us having to restart the service ourselves

Outside of this, Spring Cloud Config server allows us to externalize the config files of all our microservices in a central repository/central place(like git). That way, we don't need to go into each microservice to change the config file when new requirements are introduced, we can simply do that in the central repository. A config server is just a spring boot project with the spring cloud config server dependency, and as such, any service which will be using the config server needs to be registered as a config client(needs to have the spring cloud config client dependency).  

### Problems using Spring Cloud Config Server
1. In order to reload the config changes in Config Client services, we need to trigger the `/refresh` endpoint manually. This is not practical and viable if you have a large number of services.
2. **Spring Cloud Bus** provides a solution, such that it can be used to link multiple microservices with a **message broker**, and we can broadcast configuration changes. In order to load the configuration changes, we need to inform the message broker to trigger the broadcast event. For that, we need to call one endpoint called `bus refresh` to inform the message broker to broadcast the config changes to the related microservices. One endpoint for all.

## Distributed Tracing
One challenge in the Microservice Architecture is the ability to debug issues. When there are so many microservices, user requests will span many of them, and it will be difficult to trace the logs for a particular request when an issue occurs. One simple end-user request might trigger a chain of microservice calls, so there should be a mechanism to trace the related call chains. Distributed tracing allows us to trace/follow the request from start to finish. We can also use distributed tracing to see which microservice is taking too long and why

Spring Cloud provides us with the below services for Distributed Tracing
1. **Micrometer** for distributed tracing. Just add the dependency to your microservices and it will automatically enable distributed tracing.
2. **Zipkin** to visualize trace information through a UI

## Circuit Breaker
The main idea behind this design pattern is that let's say one service(A) calls another service(B) during each request. If for any reason, service B goes down, we want service A to return a default response, without having to rely on service B, ie a fallback. This is the Circuit Breaker pattern. In a spring context for instance, we can use Resilience4j to implement the circuit breaker pattern, and we can use the actuator's health endpoints to monitor the state of the circuit breaking.

Core states of a circuit breaker
1. **Closed**: All requests pass through. Transitions to open if failure rate exceeds the threshold.
2. **Open**: No requests pass through; an immediate fallback response is returned. Remains open for a specified timeout before transitioning to half-open.
3. **Half-Open**: Allows a limited number of test requests to check if the service has recovered. Transitions back to closed on success or back to open on failure.

Configuration Properties (with Resilience4j)
1. **Health Indicator**: Registers health status with Spring Boot Actuator (register-health-indicator=true).
2. **Failure Rate Threshold**: Percentage of failed requests to trigger open state (failure-rate-threshold=50).
3. **Minimum Calls**: Minimum number of requests before failure rate calculation (minimum-number-of-calls=5).
4. **Automatic Transition**: Enables automatic transition from open to half-open (automatic-transition-from-open-to-half-open-enabled=true).
5. **Wait Duration**: Time to wait in open state before transitioning to half-open (wait-duration-in-open-state=5s).
6. **Permitted Calls in Half-Open**: Number of test requests allowed in half-open state (permitted-number-of-calls-in-half-open-state=3).
7. **Sliding Window Size**: Number of requests to consider for failure rate calculation (sliding-window-size=10).
8. **Sliding Window Type**: Basis for sliding window, count-based in this case (sliding-window-type=COUNT_BASED).

There is also the **Retry Pattern** where, when microservice B is down, it allows microservice A to retry the request for a certain number of times.

## General Docker Workflow
It all starts with a **Dockerfile** which is simply a text file that contains all the instructions and commands needed to build a **docker image**. Once
you've created the Dockerfile, you can use the `docker build` command to build the docker image. A **docker image** is an executable package that runs in a container. A **docker container** is nothing but a running instance of a docker image. This entire process is known as `dockerization`. Once we have the docker image, we can push it to **docker hub**, which then can be run in every environment of choice.

below are some simple docker commands highlighting the workflow:

Dockerfile -> `docker build` -> Docker image -> `docker run` -> Docker container -> `Docker push` -> Docker hub

#### 1. Create a dockerfile
The dockerfile is just a bunch of instructions you will use to build your image. Before we get started, you need to package your application. This can be done by running the maven `clean` and `package` commands. Which will basically create a `jar` file for your app

Now, create a dockerfile. The first line of your dockerfile will define the docker image which will provide your java version. And then, you can add your own labels, for instance a maintainer. The third thing you need to do is to create your working directory. This directory will be created in your docker container to run your application.

Now, you need to copy the `jar` file that was created in your target directory, into the working directory you defined above.

Finally, you then specify an `entrypoint`, which is simply the commands to run the jar file

A sample docker file could look like this
```dockerfile
FROM eclipse-temurin:21

LABEL maintainer="thesarfo1@gmail.com"

WORKDIR /app

COPY target/service-registry-0.0.1-SNAPSHOT.jar /app/service-registry.jar

ENTRYPOINT["java", "-jar", "service-registry.jar"]
```
#### 2. Build a docker image from the dockerfile
First of all, make sure docker desktop is running on your pc.

Then, navigate to the root directory of your app. And build the image from the docker file. The command could look like this:
`docker build -t service-registry .`

The `-t` flag is used to tag our docker image, and the `.` at the end refers to where our dockerfile is located. We can tag our docker image like this `docker build -t service-registry:0.1.RELEASE .`

#### 3. Run the docker image in a docker container
It can be done with the docker run command. view a sample below
`docker run -p 8761:8761 service-registry`

The `-p` flag is used to map the ports between the host and the docker container. The host port doesn't have to be the same as the container port.


#### Extra - Running a docker container in detached mode(in the background)
`docker run -p 8761:8761 -d service-registry`

When this command is run, the container runs in the background(without any logs displaying etc, but a unique id is given to us). We can view the logs using the unique id(first four digits), with the below command

`docker logs -f 1424`

You can stop the container with:
`docker stop 1424`















