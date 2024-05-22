# spring-boot-template

## 環境構築

#### パッケージの更新

```console
sudo apt update && sudo apt upgrade -y && sudo apt autoremove
```

#### Dockerのインストール

[Install Docker Engine](https://docs.docker.com/engine/install/ubuntu/)

#### Gitのインストールと設定

```console
sudo apt install git && git config --global user.name "ORIKAMIxNEP" && git config --global user.email taiki.orikami@gmail.com
```

#### [SSHキーの生成と追加](https://github.com/settings/ssh/new)

```console
ssh-keygen -t ed25519 && cat ~/.ssh/id_ed25519.pub
```

#### リポジトリのクローン

```console
GIT_SSH_COMMAND='ssh -o StrictHostKeyChecking=no' git clone git@github.com:ORIKAMIxNEP/spring-boot-template.git
```

#### JDKのインストール

```console
sudo apt install openjdk-17-jdk -y
```

#### 環境変数の設定

```env
CSRF_TOKEN=
POSTGRES_USER=
POSTGRES_PASSWORD=
```

#### API仕様書

[Swagger UI](http://localhost:8080/swagger-ui/index.html)
