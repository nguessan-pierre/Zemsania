## Using the Docker Image from Docker Hub

To pull and run the Docker image for this application, follow the steps below:

### Step 1: Pull the Image from Docker Hub
Download the latest version of the Docker image from Docker Hub:

```bash
docker pull nguessanpierre/franquicia-spring-boot-app:latest
```

### Step 2: Run the Docker Container
Start the application by running the Docker container and mapping the container's internal port to your local machine:

```bash
docker run -p 8080:8080 nguessanpierre/franquicia-spring-boot-app:latest
```
This maps port 8080 in the container to port 8080 on your host machine.

### Step 3: Access the Application
Once the container is running, you can access the application swagger by navigating to:

```
http://localhost:8080/swagger-ui/index.html
```
All the operations are explained in it