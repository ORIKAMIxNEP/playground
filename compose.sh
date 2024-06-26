#!/bin/bash

COMPOSE_FILE_PATH="docker-compose/compose.yml"

if [ $# -eq 0 ]; then
    docker compose -f COMPOSE_FILE_PATH build --no-cache
    docker compose -f COMPOSE_FILE_PATH up -d
    echo "Database IPAddress: $(docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' database)"
else
    while getopts "de:l:" option; do
        case $option in
            d)
                docker compose -f COMPOSE_FILE_PATH down -v
                ;;
            e)
                if [ -n "$OPTARG" ]; then
                    docker compose -f COMPOSE_FILE_PATH exec "$OPTARG" bash
                fi
                ;;
            l)
                if [ -n "$OPTARG" ]; then
                    docker compose -f COMPOSE_FILE_PATH logs "$OPTARG"
                fi
                ;;
            \?)
                echo "Invalid Option: -$OPTARG" >&2
                exit 1
                ;;
        esac
    done
fi
