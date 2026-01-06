#!/bin/bash

if [ -z "$1" ]; then
  echo -e "Error: No mode specified. Use 'up' or 'down'."
  exit 1
fi

MODE=$1

if [ "$MODE" == "up" ]; then
sudo docker compose \
  -f backend/docker/docker-compose.dev.yml \
  -f frontend/docker/docker-compose.dev.yml \
  up --build

elif [ "$MODE" == "down" ]; then
sudo docker compose \
  -f backend/docker/docker-compose.dev.yml \
  -f frontend/docker/docker-compose.dev.yml \
  down

else
  echo -e "Invalid mode: $MODE. Use 'up' or 'down'."
  exit 1
fi