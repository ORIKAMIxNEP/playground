#!/bin/bash

if [ $# -eq 0 ]; then
    docker compose build --no-cache
    docker compose up -d
else
    while getopts "de:l:" option; do
        case $option in
            d)
                # コンテナの停止（docker/database/initdbの更新）
                docker compose down -v
                ;;
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
            \?)
                echo "Invalid Option: -$OPTARG" >&2
                exit 1
                ;;
        esac
    done
fi
