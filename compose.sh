# Rebuild Docker images
docker build -f backend/notificationservice/src/main/docker/Dockerfile.jvm -t notificationservice:latest ./backend/notificationservice
docker build -f backend/projectservice/src/main/docker/Dockerfile.jvm -t projectservice:latest ./backend/projectservice
docker build -f backend/taskservice/src/main/docker/Dockerfile.jvm -t taskservice:latest ./backend/taskservice

# Restart the containers
docker-compose down
docker-compose up