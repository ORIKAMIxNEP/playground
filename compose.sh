#!/bin/bash

cd container || exit

if [ $# -eq 0 ]; then
    # Start Database
    docker compose up -d db
    while ! docker inspect -f '{{.State.Health.Status}}' database | grep -q "healthy"; do
        sleep 1
    done

    # Generate jOOQ Code
    cd ../application || exit
    chmod +x gradlew
    ./gradlew :jooqCodegen
    cd ../container || exit

    # Start Application
    docker compose build --no-cache app
    docker compose up -d app

    # Display Database's IP Address
    echo "Database's IP Address: $(docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' database)"
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
