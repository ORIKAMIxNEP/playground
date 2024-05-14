#!/bin/bash

if [ $# -eq 0 ]; then
    docker compose build --no-cache
    docker compose up -d
else
    while getopts "e:l:r" option; do
        case $option in
            e)
                # コンテナへの接続
                if [ -n "$OPTARG" ]; then
                    docker compose exec "$OPTARG" bash
                fi
                ;;
            l)
                # サービスのログ出力
                if [ -n "$OPTARG" ]; then
                    docker compose logs "$OPTARG"
                fi
                ;;
            r)
                # コンテナのリスタート
                docker compose down -v
                docker compose build --no-cache
                docker compose up -d
                ;;
            \?)
                echo "Invalid Option: -$OPTARG" >&2
                exit 1
                ;;
        esac
    done
fi
