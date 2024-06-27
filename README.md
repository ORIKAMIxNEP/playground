# Spring Boot Template

<br>

## 仕様

#### API仕様

- [Swagger UI](http://localhost:8080/swagger-ui/index.html)

#### Checkstyle定義

- [Google's Java Style](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml)

<br>

## 実行方法

#### サービスの起動

```console
sh compose.sh
```

#### サービスの停止（データベースのリセット）

```console
sh compose.sh -d
```

#### サービスへの接続

```console
sh compose.sh -e 「サービス名」
```

#### サービスのログ出力

```console
sh compose.sh -l 「サービス名」
```

<br>

## 環境構築（Ubuntu）

#### パッケージの更新

```console
sudo apt update && sudo apt upgrade -y && sudo apt autoremove
```

#### Dockerのインストール

- [Install Docker Engine on Ubuntu](https://docs.docker.com/engine/install/ubuntu/)

---

### Kubernetesのインストール（Docker Composeを使用しない場合）

#### Minikubeのインストール

- [minikube start](https://minikube.sigs.k8s.io/docs/start/?arch=%2Flinux%2Fx86-64%2Fstable%2Fbinary+download)

#### Kubectlのインストール

- [Install and Set Up kubectl on Linux](https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/)

---

#### JDKのインストール

```console
sudo apt install openjdk-17-jdk -y
```

#### Graphvizのインストール

```console
sudo apt install graphviz -y
```

#### Gitのインストールと設定

```console
sudo apt install git && git config --global user.name "「GitHubのアカウント名」" && git config --global user.email 「GitHubのメールアドレス」
```

#### SSHキーの生成と追加

```console
ssh-keygen -t ed25519 && cat ~/.ssh/id_ed25519.pub
```

- [Add new SSH Key](https://github.com/settings/ssh/new)

#### リポジトリのクローン

```console
GIT_SSH_COMMAND='ssh -o StrictHostKeyChecking=no' git clone git@github.com:ORIKAMIxNEP/spring-boot-template.git && cd spring-boot-template
```

#### 環境変数の設定

```env
POSTGRES_USER=「PostgreSQLのユーザー名」
POSTGRES_PASSWORD=「PostgreSQLのパスワード」
```
