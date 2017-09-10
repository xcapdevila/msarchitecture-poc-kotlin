# PROJECT OVERVIEW

**Microservices architecture example based on Spring Boot, Spring Cloud & Netflix OSS with the following components:**
- **EurekaServer** (alternatives: Consul)
- **ConfigServer** (alternatives: Archaius)
- **ConfigFiles** (git repo with configuration files @see (https://github.com/xcapdevila/msarchitecture-poc-config)
- **AdminServer** (with email notifications @see (https://gist.github.com/raelgc/6031274)
- **HystrixDashboard**
- **ZuulServer**
- **DtoLib**
- **MsDemoA**
- **MsDemoB**

# REQUIREMENTS

1) Clone **ConfigFiles**(https://github.com/xcapdevila/msarchitecture-poc-config) repo to tmp folder in your home (change properties files if you clone it somewhere else)
2) Install & start **RabbitMQ** in localhost (change properties files if you use a non default configuration)

# MICROSERVICE ARCHITECTURE BEHAVIOUR

## 1) Configuration First

**application.yml**

```
eureka:
  client:
    serviceUrl:
      defaultZone: http://${EUREKA_SERVER_ENDPOINT:localhost:1111}/eureka/
```

**bootstrap.yml**

```
spring:
  application:
    name: ${APPLICATION_NAME:adminserver}
  cloud:
    config:
      failFast: true
      retry:
        initialInterval: ${CLOUD_CONFIG_RETRY_INITIAL_INTERVAL:1000}
        maxAttempts: ${CLOUD_CONFIG_RETRY_MAX_ATTEMPTS:10}
      uri: http://${CONFIG_SERVER_ENDPOINT:localhost:8888}
```

## 2) Discovery First

**application.yml**

```
*[Nothing required]*
```

**bootstrap.yml**

```
eureka:
  client:
    serviceUrl:
      defaultZone: http://${EUREKA_SERVER_ENDPOINT:localhost:1111}/eureka/

spring:
  application:
    name: ${APPLICATION_NAME:hystrixdashboard}
  cloud:
    config:
      failFast: true
      retry:
        initialInterval: ${CLOUD_CONFIG_RETRY_INITIAL_INTERVAL:1000}
        maxAttempts: ${CLOUD_CONFIG_RETRY_MAX_ATTEMPTS:10}
      discovery:
        enabled: true
        service-id: ${CONFIG_SERVER_NAME:configserver}
```