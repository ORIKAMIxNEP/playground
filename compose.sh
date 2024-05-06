#!/bin/bash

if [ $# -eq 0 ]; then
    docker compose build --no-cache
    docker compose up -d
else
    while getopts ":d:e:l:" opt; do
        case $opt in
            d)
                # docker-entrypoint-initdb.dの更新
                docker compose down -v
                ;;
            e)
                # コンテナへの接続
                if [ -n "$OPTARG" ]; then
                    docker compose exec "$OPTARG" bash
                else
                    echo "A container name is required" >&2
                    exit 1
                fi
                ;;
            l)
                # サービスのログ出力
                if [ -n "$OPTARG" ]; then
                    docker compose logs "$OPTARG"
                else
                    docker compose logs
                fi
                ;;
            \?)
                echo "Invalid Option: -$OPTARG" >&2
                exit 1
                ;;
        esac
    done
fi
