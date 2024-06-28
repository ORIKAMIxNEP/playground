#!/bin/bash

cd container

if [ $# -eq 0 ]; then
    docker compose build --no-cache
    docker compose up -d mst-app db
    echo "Database IPAddress: $(docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' database)"
else
    while getopts "de:l:" option; do
        case $option in
            d)
                docker compose down -v
                docker network prune -f
                ;;
            e)
                if [ -n "$OPTARG" ]; then
                    docker compose exec "$OPTARG" bash
                fi
                ;;
            l)
                if [ -n "$OPTARG" ]; then
                    docker compose logs "$OPTARG"
                fi
                ;;
            \?)
                echo "Invalid Option: -$OPTARG" >&2
                exit 1
                ;;
        esac
    done
fi
