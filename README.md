This is a minimalistic Employee Management Application built on the **Microservice Architecture**

There are three services so far
- Department Service
- Employee Service
- Service Registry : This service registers all the other services, to enable service discovery.

#### Microservice Communication
In a Spring Context, microservices can communicate through different channels

1. **RestTemplate** - To make REST API calls from one microservice to another microservice
2. **Webclient** - Also makes REST API calls from one microservice to another microservice
3. **Spring Cloud OpenFeign** - A spring enabler used to make REST API calls from one microservice to another microservice


#### Communication Styles
There are two main styles of communication

1. **Synchronous Communication**: The client sends a request and waits for a response from the service. The important point here is that the protocol(HTTP/HTTPS) is synchronous and the client code can only continue its task when it receives the HTTP server response. You can achieve this communication by using RestTemplate, Webclient and Spring Cloud Open Feign.

2. **Asynchronous Communication**: The client sends a request and does not wait for a response from the service. The client will continue executing its task, without waiting for a response from the service. This client does this using something called a Message Broker. You can achieve this communication by using RabbitMQ or Apache Kafka.

#### Service Registry and Discovery
1. In a microservice, Service Registry and Discovery play an important role because we most likely run multiple instances of services, and we need a mechanism to call other services without hardcoding their hostnames or port numbers.

2. In addition to that, in Cloud environments, service instances may come up and go down at anytime. So we need some automatic service registration and discovery mechanism.

3. Spring Cloud addresses this problem by providing **Spring Cloud Netflix Eureka** project to enable Service Registry and Discovery.

Mostly, we have to create a separate spring boot project as a microservice(service-registry), which we will use Eureka server for, and we need to disable this server as the Eureka client. All the other services which will be registered in our service-registry need to be registered as `"eureka clients"`

#### Load Balancing
Let's take this example. We have two instances of our Department Service running, and our Employee service makes an API call to the Department Service via Open Feign. But let's say one instance of the Department Service goes down, how do we ensure that all the incoming requests from the Employee service are automatically re-routed to the running instance of the Department Service. This is where load-balancing comes in. Spring Cloud Netflix comes with Spring Cloud Load Balancer out of the box. Whenever we use Open Feign in Conjunction with Eureka Server, Eureka Server comes with Spring Cloud Load Balancer. 

In order to implement load-balancing, we just have to change the url of our Open Feign with the service id that is provided in Eureka Server. For instance, we will make the Employee service's Open Feign client make a request to "DEPARTMENT-SERVICE" - since this is the Service ID provided by Eureka Server
