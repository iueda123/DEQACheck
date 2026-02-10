# DEQACheckAll

  * Derived from SpringAndVaadinWithGradle
  * Spring Boot + Vaadin sample managed with Gradle, mirroring `SpringAndVaadin` (Maven).

--------

## Requirements
- Java 17+
- Gradle 8.x (or generate wrapper with `gradle wrapper`)

--------

## 起動方法

### 開発モードで起動する

起動
```bash
./gradlew bootRun
```

停止方法
```bash
fuser -k 8080/tcp  # 起動したターミナルを見失ったとき用
```

再起動
```bash
fuser -k 8080/tcp
./gradlew bootRun
```

### 本番モードで起動する

```bash
./gradlew -Pvaadin.productionMode=true bootRun
```
  * This enables the Vaadin Gradle plugin to build the frontend and place `index.html` on the classpath.
  * `http://localhost:8080`

### JAR ビルド

```bash
./gradlew clean bootJar -Pvaadin.productionMode=true
```
  * build/libs 下 にjarファイルが作られる
  * 実行は `java -jar build/libs/xxx.jar` 

--------

## SpringBoot+Vaadinアプリケーションを Production Modeで jar化 や 起動する際の チェックポイント

* [ ] Javaのバージョンは開発時に想定されたものか？（2025.11現在Java17が安定）
* [ ] application.properties において vaadin.productionMode=true となっているか？（これをしていないと例えjar化できたとしても起動に失敗する。）
* [ ] index.html が クラスパス上（META-INF/resources/index.html など）に置いてあるか。
  * 本番モードで起動しないと作られない。
  * これをしていないと例えjar化できたとしても起動に失敗する。
  * src/main/frontend/index.html ではない。
  * build/libs/DEQACheckAll-0.0.1-SNAPSHOT.jar 内の META-INF/resources/ に index.html が置かれる。
 
--------

## サーバー上へ配備するときのポイント

```bash
# バックグラウンド実行
java -jar XXX.jar &

# プロセスID（PID）を調べて停止
ps aux | grep XXX..jar

# 停止
kill 12345

# 強制終了
kill -9 12345
```