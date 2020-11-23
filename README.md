# クイズ出題サイト

## Development environment

* OpenJDK 11
* Kotlin 1.4.20
* Spring Boot 2.4.0
* Spring Security 5.4.1
* H2
* Gradle 6.7.1

## Build & Run

build

```
gradlew clean build --warning-mode all
```

run

```
gradlew bootRun
```

or

```
java -jar .\build\libs\demo.jar
```

## Demo simple application top page

```
http://localhost:8080/demo/
```

**top page**

![top](https://raw.githubusercontent.com/rubytomato/demo-spring-security-simple/master/images/top.png)

**after login**

![top](https://raw.githubusercontent.com/rubytomato/demo-spring-security-simple/master/images/top_after_login.png)


## database

using h2

```
DROP TABLE IF EXISTS login_user;
CREATE TABLE login_user (
  id BIGINT AUTO_INCREMENT                    COMMENT 'ユーザーID',
  name VARCHAR(60) NOT NULL                   COMMENT 'ユーザー名',
  email VARCHAR(120) NOT NULL                 COMMENT 'メールアドレス',
  password VARCHAR(120) NOT NULL              COMMENT 'パスワード',
  roles VARCHAR(120)                          COMMENT 'ロール',
  enable_flag TINYINT(1) NOT NULL DEFAULT 1   COMMENT '1:有効 0:無効',
  PRIMARY KEY (id),
  UNIQUE KEY (email)
)
ENGINE = INNODB
DEFAULT CHARSET = UTF8MB4;
```

h2-console

```
http://localhost:8080/demo/h2-console
```

## オープンソースライセンス / Open source licenses

このソフトウェアには以下のオープンソースソフトウェアが含まれています。

* [Spring Security With Spring Boot 2.2 Demo simple application](https://github.com/rubytomato/demo-spring-security-simple) / [MIT License](https://github.com/rubytomato/demo-spring-security-simple/blob/master/LICENSE)  
  Copyright (c) 2020 [Watanabe Shin](https://github.com/rubytomato)

## ライセンス / License

    Copyright 2020 P Hackathon

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
