# URL Lookup Service

This application exposes an API to lookup if a URL is malicious.
The project is built in Java, Spring Boot and uses Redis as the datastore.

### Instructions to run using Docker Compose
```
  $ ./mvnw install dockerfile:build
  $ docker-compose up -d
```
---

### Instructions to run without Docker ( Requires Redis to be running locally)
```
 $ ./mvnw package && java -jar target/urllookupservice-0.0.1-SNAPSHOT.jar --redis.servername=localhost
```

---

### Instructions to deploy using Kubernetes (Minikube)
Make sure that minikube and kubectl is installed and running

[Install kubectl ](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
[Install minikube ](https://kubernetes.io/docs/setup/minikube/)

1. Create Redis Master Deployment

```
	$ kubectl create -f k8s/redis-master-deployment.yaml
```

2. Create Redis Master Service
```
	$ kubectl create -f k8s/redis-master-service.yaml
```

3. Create Redis Slave Deployment
```
	$ kubectl create -f k8s/redis-slave-deployment.yaml
```

4. Create Redis Slave Service 
```
	$ kubectl create -f k8s/redis-slave-service.yaml
```

5. Create the URLService Deployment ( deploys 3 instances)
```
	$ kubectl create -f k8s/urlservice-frontend-deployment.yaml
```

6. Create the URLService Service to expose the API
```
	$ kubectl create -f k8s/urlservice-frontend-service.yaml
```

To get the API URL via the NodePort
```
	$ minikube service frontend --url
```
should return a response similar to this
```
	http://192.168.99.100:31323
```
Open the returned URL in a browser to go to the URLService home page
