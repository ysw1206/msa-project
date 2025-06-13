# MSA Project

Spring Boot와 Kotlin 기반의 마이크로서비스 예제 프로젝트입니다.

## 모듈 구조

- `auth`  – 인증 서비스를 담당합니다.
- `user`  – 사용자 관리를 담당합니다.
- `order` – 주문 처리를 담당합니다.
- `common` – 여러 서비스에서 공통으로 사용하는 코드가 위치합니다.

각 서비스는 독립적인 Gradle 프로젝트로 구성되어 있으며, `settings.gradle.kts`에서 모듈을 관리합니다.
