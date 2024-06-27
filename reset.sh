#!/bin/bash

container_ids=$(docker ps -aq)
docker stop $container_ids
docker rm $container_ids
docker system prune -af
sudo systemctl restart docker
