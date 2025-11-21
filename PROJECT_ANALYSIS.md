# LIMS Sample Project - 프로젝트 구조 및 유지보수 가이드

> **프로젝트명**: Laboratory Information Management System (LIMS) - Sample Module
> **도메인**: 유전체 검사 실험실 정보 관리 시스템
> **아키텍처**: Spring Boot + GWT 멀티 모듈 프로젝트
> **작성일**: 2025-11-21

---

## 📋 목차

1. [프로젝트 개요](#1-프로젝트-개요)
2. [기술 스택](#2-기술-스택)
3. [프로젝트 구조](#3-프로젝트-구조)
4. [각 모듈별 상세 분석](#4-각-모듈별-상세-분석)
5. [워크플로우 분석](#5-워크플로우-분석)
6. [빌드 및 배포](#6-빌드-및-배포)
7. [유지보수 가이드](#7-유지보수-가이드)
8. [트러블슈팅](#8-트러블슈팅)

---

## 1. 프로젝트 개요

### 1.1 프로젝트 목적
유전체 검사 실험실에서 발생하는 샘플, 검사, 요청 정보를 관리하는 웹 기반 정보 시스템입니다.

### 1.2 주요 기능
- **샘플 관리**: 검사 샘플 정보 조회 및 추적
- **서비스 관리**: 유전체 검사 서비스 관리 (NIPT, 패널 분석, MLPA 등)
- **요청 처리**: 검사 요청 및 대상자 정보 관리
- **형제 샘플 조회**: 동일 환자의 관련 샘플 추적

### 1.3 시스템 특징
- **멀티 모듈 아키텍처**: 코드 재사용성 및 모듈 독립성 확보
- **리액티브 프로그래밍**: WebFlux + R2DBC를 통한 비동기 처리
- **마이크로서비스 지향**: Zookeeper 기반 서비스 디스커버리
- **GWT 기반 SPA**: Java로 작성된 Single Page Application
- **도메인 주도 설계**: 유전체 검사 도메인 모델 중심 설계

---

## 2. 기술 스택

### 2.1 백엔드 (src/)

| 카테고리 | 기술 | 버전 | 용도 |
|---------|------|------|------|
| **언어** | Kotlin | 2.0.0 | 주 개발 언어 |
| | Java | 17 | DTO 및 레거시 코드 |
| **프레임워크** | Spring Boot | 3.3.2 | 애플리케이션 프레임워크 |
| | Spring WebFlux | - | 리액티브 웹 프레임워크 |
| | Spring Cloud Gateway | - | API 게이트웨이 |
| | Spring Cloud Zookeeper | - | 서비스 디스커버리 |
| **데이터베이스** | PostgreSQL | - | RDBMS |
| | R2DBC | - | 리액티브 DB 드라이버 |
| **보안** | Spring Security | - | 인증/인가 |
| **빌드** | Gradle | 8.2 | 빌드 도구 |

### 2.2 프론트엔드 (web/, panel/)

| 카테고리 | 기술 | 버전 | 용도 |
|---------|------|------|------|
| **언어** | Java | 17 | GWT 개발 언어 |
| **프레임워크** | GWT | 2.11.0 | 웹 UI 프레임워크 |
| **UI 라이브러리** | Elemento | 1.4.2 | GWT UI 빌더 |
| | Elemental2 SVG | 1.2.1 | SVG 지원 |
| | Material Components Web | - | Material Design (CDN) |
| **빌드** | Gradle | 8.9 | 빌드 도구 |
| | GWT Gradle Plugin | 1.1.18/1.1.19 | GWT 컴파일 플러그인 |

### 2.3 공유 라이브러리 (data/)

| 카테고리 | 기술 | 버전 | 용도 |
|---------|------|------|------|
| **언어** | Kotlin + Java | 2.0.0 / 17 | 도메인 모델 |
| **라이브러리** | Lombok | - | Java 보일러플레이트 제거 |
| | Jackson Annotations | - | JSON 직렬화 |
| **테스트** | JUnit | 5 | 단위 테스트 |

### 2.4 DevOps

| 카테고리 | 기술 | 용도 |
|---------|------|------|
| **CI/CD** | Jenkins | 빌드 및 배포 자동화 |
| | GitHub Actions | PR 자동 코드 리뷰 |
| **AI** | Google Gemini 1.5 Pro | 코드 리뷰 자동화 |
| **버전 관리** | Git | 소스 코드 관리 |
| **패키지 저장소** | GitHub Packages | Maven 아티팩트 저장 |
| **서비스 관리** | systemd | 서비스 실행 관리 |

---

## 3. 프로젝트 구조

### 3.1 디렉토리 트리

```
sample-project-claude/
├── .git/                          # Git 버전 관리
├── .github/
│   └── workflows/
│       └── code-review.yaml       # GitHub Actions - 자동 코드 리뷰
├── .idea/                         # IntelliJ IDEA 설정
├── data/                          # [모듈 1] 공유 데이터 모델 라이브러리
│   ├── src/main/
│   │   ├── java/                  # Java 인터페이스 (22개)
│   │   └── kotlin/                # Kotlin 데이터 클래스 (24개)
│   └── build.gradle.kts
├── panel/                         # [모듈 2] 재사용 GWT UI 컴포넌트
│   ├── src/main/java/
│   │   └── com/gcgenome/lims/
│   │       ├── Panel.gwt.xml      # GWT 모듈 정의
│   │       └── client/            # UI 컴포넌트 (6개)
│   └── build.gradle.kts
├── src/                           # [모듈 3] Spring Boot 백엔드 (메인)
│   └── main/
│       ├── java/                  # DTO (4개)
│       ├── kotlin/                # 엔티티, 서비스, 설정
│       └── resources/
│           └── application.yml    # Spring 설정
├── web/                           # [모듈 4] GWT 프론트엔드
│   ├── src/main/
│   │   ├── java/                  # GWT 클라이언트 코드
│   │   └── webapp/                # 정적 리소스 (HTML, CSS)
│   └── build.gradle.kts
├── .gitignore
├── Jenkinsfile                    # Jenkins 파이프라인
├── build.gradle.kts               # 루트 빌드 설정
└── settings.gradle.kts            # Gradle 멀티모듈 설정
```

### 3.2 모듈 구성

| 모듈 | artifactId | 출력 파일 | 배포 방식 |
|------|-----------|----------|----------|
| **data** | sample-data | JAR | GitHub Packages (Maven) |
| **panel** | sample-panel | JAR | GitHub Packages (Maven) |
| **src (root)** | - | sample-service.jar | 서버 배포 |
| **web** | - | sample-project-static.war | 정적 파일 배포 |

### 3.3 의존성 관계

```
web ──┐
      ├──> panel ──> data
src ──┘
```

- `data`: 독립 모듈 (의존성 없음)
- `panel`: `data`에 의존
- `src`, `web`: 모두 `data`에 의존
- `web`: `panel`에도 의존

---

## 4. 각 모듈별 상세 분석

### 4.1 data/ 모듈 - 공유 데이터 모델

#### 목적
유전체 검사 관련 도메인 모델 및 공통 인터페이스 제공

#### 주요 구성 요소

**Java 인터페이스 (22개)**
```
src/main/java/com/gcgenome/lims/test/
├── HasCode.java              # 코드 속성
├── HasName.java              # 이름 속성
├── HasCategory.java          # 카테고리 속성
├── HasDisplayName.java       # 표시명 속성
├── Reportable.java           # 보고 가능 여부
├── Interpretable.java        # 해석 가능 여부
├── IsPanelAnalysis.java      # 패널 분석 마커
├── IsNiptAnalysis.java       # NIPT 분석 마커
├── IsMlpaAnalysis.java       # MLPA 분석 마커
├── ContainsGenes.java        # 유전자 포함 정보
├── ContainsExons.java        # 엑손 포함 정보
└── ... (11개 더)
```

**Kotlin 검사 타입 데이터 클래스 (24개)**

| 파일명 | 크기 | 설명 |
|--------|------|------|
| `RareDiseasePanel.kt` | 175 KB | 희귀질환 패널 (가장 큰 파일) |
| `GenePlusPanel.kt` | 74 KB | 유전자 플러스 패널 |
| `BloodCancerPanel.kt` | 55 KB | 혈액암 패널 |
| `SolidTumorPanel.kt` | 42 KB | 고형암 패널 |
| `Wes.kt` | 34 KB | 전장 엑솜 시퀀싱 |
| `GenomeScreen.kt` | 33 KB | 전장 유전체 스크리닝 |
| `SingleGenePanel.kt` | 25 KB | 단일 유전자 패널 |
| `NIPT.kt` | 11 KB | 비침습적 산전 검사 |
| 기타 | - | Mlpa, Sanger, Mrd, Hrd 등 |

**분석 메소드 (4개)**
```
src/main/kotlin/com/gcgenome/lims/test/method/
├── NiptAnalysisMethod.kt         # NIPT 분석 방법
├── PanelAnalysisMethod.kt        # 패널 분석 방법
├── SomaticPanelAnalysisMethod.kt # 체세포 패널 분석 방법
└── MrdAnalysisMethod.kt          # MRD 분석 방법
```

#### 빌드 설정
- **버전**: 2025.08.25-2
- **Maven 발행**: GitHub Packages에 자동 배포
- **테스트 프레임워크**: JUnit 5

---

### 4.2 panel/ 모듈 - GWT UI 컴포넌트

#### 목적
재사용 가능한 GWT UI 컴포넌트 및 API 클라이언트 제공

#### 주요 컴포넌트

```
src/main/java/com/gcgenome/lims/client/
├── AbstractMain.java        # GWT 엔트리 포인트 추상 클래스
├── ServiceApi.java          # 서비스 API 클라이언트
├── WindowState.java         # 창 상태 관리
├── Section.java             # 섹션 UI 컴포넌트
├── ExpandElement.java       # 확장 가능 요소
└── CollapseElement.java     # 접기 가능 요소
```

---

### 4.3 src/ 모듈 - Spring Boot 백엔드

#### 목적
샘플, 서비스, 요청 데이터를 관리하는 REST API 서버

#### 디렉토리 구조

```
src/main/
├── java/com/gcgenome/lims/dto/
│   ├── Organization.java        # 조직 DTO
│   ├── Patient.java             # 환자 DTO
│   ├── Sample.java              # 샘플 DTO
│   └── Request.java             # 요청 DTO
└── kotlin/com/gcgenome/lims/
    ├── Application.kt           # Spring Boot 메인 클래스
    ├── SecurityConfig.kt        # 보안 설정
    ├── SecurityContextRepository.kt  # 커스텀 보안 컨텍스트
    ├── entity/                  # R2DBC 엔티티 (5개)
    │   ├── Sample.kt
    │   ├── Service.kt
    │   ├── Request.kt
    │   ├── User.kt
    │   └── Test.kt
    └── service/                 # 비즈니스 로직
        ├── Router.kt            # WebFlux 라우터
        ├── Handler.kt           # 요청 핸들러
        ├── Dao.kt               # 데이터 액세스
        ├── TestRepository.kt    # 테스트 저장소
        └── user/
            └── UserDao.kt       # 사용자 DAO
```

#### REST API 엔드포인트

| HTTP 메소드 | 경로 | Content-Type | 설명 |
|------------|------|--------------|------|
| GET | `/services` | - | 서비스 목록 조회 |
| GET | `/samples/{sample}` | application/json | 샘플 정보 조회 |
| GET | `/samples/{sample}/services` | application/vnd.lims.v1 | 샘플의 서비스 목록 |
| GET | `/samples/{sample}/services/{service}/requests` | application/vnd.lims.v1 | 요청 상세 조회 |
| GET | `/samples/{sample}/siblings` | application/json | 형제 샘플 조회 |
| GET | `/samples/{sample}/services/{service}/subjects` | application/json | 대상자 조회 |

**참조**: `src/main/kotlin/com/gcgenome/lims/service/Router.kt:14-21`

---

### 4.4 web/ 모듈 - GWT 프론트엔드

#### 목적
사용자가 샘플 정보를 조회하고 관리하는 웹 인터페이스

#### 디렉토리 구조

```
web/src/main/
├── java/com/gcgenome/lims/
│   ├── Sample.gwt.xml          # GWT 모듈 정의
│   ├── api/                    # REST API 클라이언트
│   │   ├── SampleApi.java
│   │   └── ServiceApi.java
│   ├── client/                 # GWT 클라이언트 코드
│   │   ├── Main.java           # 엔트리 포인트
│   │   ├── SampleElement.java  # 샘플 UI (5.4 KB)
│   │   ├── ServiceTabElement.java  # 서비스 탭 (5.7 KB)
│   │   ├── SubjectPanel.java   # 대상자 패널 (3.6 KB)
│   │   └── message/            # 메시지 처리 (6개 클래스)
│   └── dto/                    # 프론트엔드 DTO
└── webapp/                     # 정적 리소스
    ├── sample.html             # 메인 HTML
    └── css/
```

#### UI 워크플로우
1. 사용자가 `sample.html#12345` 접속
2. GWT가 해시에서 샘플 ID 파싱
3. `SampleApi.sample(12345)` 호출하여 샘플 정보 로드
4. `SampleElement`로 샘플 정보 표시
5. `ServiceApi.services(12345)` 호출하여 서비스 목록 로드
6. `ServiceTabElement`로 서비스 탭 생성

**참조**: `web/src/main/java/com/gcgenome/lims/client/Main.java:12-32`

---

## 5. 워크플로우 분석

### 5.1 전체 시스템 워크플로우

```
┌─────────────┐
│   사용자     │
└──────┬──────┘
       │
       │ HTTP Request (sample.html#12345)
       ↓
┌─────────────────────────────────────┐
│  웹 서버 (static files)             │
│  - sample.html                      │
│  - sample.nocache.js (GWT 컴파일)   │
│  - CSS, 이미지                      │
└──────┬──────────────────────────────┘
       │
       │ GWT 초기화 (Main.onModuleLoad)
       ↓
┌─────────────────────────────────────┐
│  GWT 프론트엔드 (web/)              │
│  1. URL 해시 파싱 (#12345)          │
│  2. SampleApi.sample(12345) 호출    │
│  3. ServiceApi.services(12345) 호출 │
└──────┬──────────────────────────────┘
       │
       │ REST API 호출
       │ GET /samples/12345
       │ GET /samples/12345/services
       ↓
┌─────────────────────────────────────┐
│  Spring Boot 백엔드 (src/)          │
│  ┌───────────────────────────────┐  │
│  │ Spring Security Filter        │  │
│  │ - CORS 허용                   │  │
│  │ - 인증/인가 확인              │  │
│  └───────────┬───────────────────┘  │
│              ↓                       │
│  ┌───────────────────────────────┐  │
│  │ Router (WebFlux)              │  │
│  │ - 엔드포인트 라우팅           │  │
│  └───────────┬───────────────────┘  │
│              ↓                       │
│  ┌───────────────────────────────┐  │
│  │ Handler                       │  │
│  │ - 비즈니스 로직 처리          │  │
│  └───────────┬───────────────────┘  │
│              ↓                       │
│  ┌───────────────────────────────┐  │
│  │ Dao (R2DBC)                   │  │
│  │ - 리액티브 DB 쿼리            │  │
│  └───────────┬───────────────────┘  │
└──────────────┼───────────────────────┘
               │
               │ R2DBC 쿼리
               ↓
       ┌───────────────┐
       │  PostgreSQL   │
       │  panel.sample │
       │  기타 테이블  │
       └───────────────┘
```

### 5.2 인증/인가 워크플로우

**Spring Security 설정** (`src/main/kotlin/com/gcgenome/lims/SecurityConfig.kt:60-66`)

```
[클라이언트 요청]
    │
    ↓
[경로별 처리]
    │
    ├─ /js/**, /css/**, /*.html
    │  -> permitAll (인증 불필요)
    │
    ├─ OPTIONS /**
    │  -> permitAll (CORS preflight)
    │
    ├─ /actuator/health/**
    │  -> permitAll (Health check)
    │
    └─ 기타 경로
       -> authenticated (인증 필요)
```

---

## 6. 빌드 및 배포

### 6.1 Jenkins 파이프라인 워크플로우

**파이프라인 개요** (`Jenkinsfile:8-106`)

```groovy
pipeline {
    parameters {
        choice(name: 'DEPLOY_ENV', choices: ['test', 'prod'])
    }

    stages {
        stage('Root Source Build')  // Spring Boot JAR
        stage('Panel Build')        // Maven Local 발행
        stage('Web Build')          // GWT 컴파일 및 배포
    }
}
```

#### Stage 1: Root Source Build

**목적**: Spring Boot 백엔드 빌드 및 배포

```bash
# 빌드 명령어
gradle clean build -x test --refresh-dependencies

# 출력 파일
build/libs/sample-service.jar
```

**배포 프로세스**

| 환경 | 배포 방식 | 대상 서버 | 서비스 재시작 |
|------|----------|----------|-------------|
| **test** | 로컬 복사 | Jenkins 서버 | `sudo systemctl restart lims-sample` |
| **prod** | SSH 전송 | Aries, Taurus | `sudo systemctl restart lims-sample` |

#### Stage 2: Panel Build

```bash
# panel 모듈을 Maven Local에 발행
gradle clean :panel:publishToMavenLocal

# 발행 위치
~/.m2/repository/com/gcgenome/sample-panel/1.0/
```

#### Stage 3: Web Build

```bash
# GWT 컴파일 및 정적 파일 배포
gradle :web:copyWebResources --refresh-dependencies

# 출력 디렉토리
web/build/static/
├── sample/
│   ├── sample.nocache.js      # GWT 부트스트랩
│   ├── *.cache.js             # GWT 컴파일 결과
│   └── ...
├── css/
└── sample.html
```

### 6.2 로컬 빌드 가이드

#### 사전 요구사항
```bash
# Java 17 설치 확인
java -version  # java version "17.x.x"

# Gradle 설치 확인
gradle --version  # Gradle 8.2+
```

#### 전체 빌드
```bash
# 루트 프로젝트 빌드 (Spring Boot JAR)
gradle clean build -x test

# panel 모듈 빌드 및 Maven Local 발행
gradle :panel:publishToMavenLocal

# web 모듈 빌드
gradle :web:copyWebResources
```

#### GWT 개발 서버 실행
```bash
# SuperDev 모드로 GWT 개발 서버 실행
gradle :web:gwtSuperDev

# 접속 정보
# - 개발 서버: http://localhost:9629
# - 코드서버: http://localhost:9630
```

#### Spring Boot 실행
```bash
# JAR 직접 실행
java -jar build/libs/sample-service.jar

# Gradle로 실행
gradle bootRun
```

### 6.3 배포 아키텍처

```
┌─────────────────────────────────────────────┐
│          Jenkins Server                     │
│                                             │
│  ┌─────────────────────────────────────┐   │
│  │  Pipeline Stages                    │   │
│  │  1. Root Build -> sample-service.jar│   │
│  │  2. Panel Build -> Maven Local      │   │
│  │  3. Web Build -> static files       │   │
│  └─────────────────────────────────────┘   │
└────────┬──────────────────────┬─────────────┘
         │                      │
         │ test 환경            │ prod 환경
         │ (로컬 복사)          │ (SSH 전송)
         ↓                      ↓
    ┌─────────┐        ┌─────────┐  ┌─────────┐
    │  Test   │        │  Aries  │  │ Taurus  │
    │  Server │        │  Server │  │ Server  │
    └─────────┘        └─────────┘  └─────────┘
```

---

## 7. 유지보수 가이드

### 7.1 프로젝트 인수인계 시 분석 순서

신규 개발자가 프로젝트를 인수받았을 때 다음 순서로 분석하는 것을 권장합니다.

#### 1단계: 프로젝트 개요 파악 (1-2일)

**목표**: 프로젝트가 무엇을 하는지, 어떤 기술을 사용하는지 파악

1. **문서 읽기**
   - 이 문서(`PROJECT_ANALYSIS.md`) 정독
   - `Jenkinsfile`의 주석 확인

2. **빌드 파일 분석**
   ```bash
   # 루트 build.gradle.kts 확인
   cat build.gradle.kts

   # settings.gradle.kts에서 모듈 구조 확인
   cat settings.gradle.kts
   ```

3. **디렉토리 구조 탐색**
   ```bash
   # 전체 구조 확인
   tree -L 3 -I '.git|.gradle|build|out'

   # 소스 파일 개수 확인
   find src -name "*.kt" | wc -l
   find src -name "*.java" | wc -l
   ```

#### 2단계: 코드 구조 이해 (2-3일)

**목표**: 각 모듈의 역할과 코드 흐름 이해

1. **백엔드 (src/) 분석**
   ```bash
   # 시작점 확인
   cat src/main/kotlin/com/gcgenome/lims/Application.kt

   # 라우팅 확인
   cat src/main/kotlin/com/gcgenome/lims/service/Router.kt

   # 보안 설정 확인
   cat src/main/kotlin/com/gcgenome/lims/SecurityConfig.kt
   ```

2. **프론트엔드 (web/) 분석**
   ```bash
   # 시작점 확인
   cat web/src/main/java/com/gcgenome/lims/client/Main.java

   # UI 컴포넌트 확인
   cat web/src/main/java/com/gcgenome/lims/client/SampleElement.java
   ```

#### 3단계: 로컬 환경 구축 (1일)

**목표**: 로컬에서 빌드 및 실행 가능한 환경 만들기

1. **환경 설정**
   ```bash
   # GitHub Packages 인증 설정
   echo "github_username=YOUR_USERNAME" >> ~/.gradle/gradle.properties
   echo "github_password=YOUR_GITHUB_TOKEN" >> ~/.gradle/gradle.properties
   ```

2. **빌드 테스트**
   ```bash
   # 전체 빌드
   gradle clean build -x test

   # panel 모듈 발행
   gradle :panel:publishToMavenLocal

   # web 모듈 빌드
   gradle :web:copyWebResources
   ```

3. **데이터베이스 설정**
   ```bash
   # PostgreSQL 설치 및 데이터베이스 생성
   sudo apt install postgresql
   sudo -u postgres psql
   > CREATE DATABASE lims;
   > CREATE SCHEMA panel;
   ```

4. **로컬 실행**
   ```bash
   # Spring Boot 실행
   gradle bootRun

   # GWT 개발 서버 실행 (별도 터미널)
   gradle :web:gwtSuperDev
   ```

#### 4단계: 배포 프로세스 이해 (1일)

1. **Jenkinsfile 분석**
   ```bash
   cat Jenkinsfile
   ```

2. **systemd 서비스 확인**
   ```bash
   sudo systemctl status lims-sample
   ```

#### 5단계: 테스트 및 디버깅 (2-3일)

1. **API 테스트**
   ```bash
   # 샘플 조회
   curl -X GET http://localhost:8080/samples/12345 \
     -H "Authorization: Bearer YOUR_TOKEN"
   ```

2. **로그 확인**
   ```bash
   # systemd 로그
   sudo journalctl -u lims-sample -f
   ```

### 7.2 일반적인 유지보수 작업

#### 새로운 API 엔드포인트 추가

**1. Handler에 비즈니스 로직 추가**
```kotlin
// src/main/kotlin/com/gcgenome/lims/service/Handler.kt
fun findSampleByBarcode(barcode: String): Mono<Sample> {
    return dao.findSampleByBarcode(barcode)
}
```

**2. Router에 라우팅 추가**
```kotlin
// src/main/kotlin/com/gcgenome/lims/service/Router.kt
@Bean
fun router() = router {
    // 기존 라우트...
    GET("/samples/barcode/{barcode}", ::sampleByBarcode)
}

private fun sampleByBarcode(request: ServerRequest): Mono<ServerResponse> {
    val barcode = request.pathVariable("barcode")
    return handler.findSampleByBarcode(barcode)
        .flatMap(ServerResponse.ok()::bodyValue)
        .switchIfEmpty(ServerResponse.notFound().build())
}
```

**3. 테스트 및 배포**
```bash
gradle build
# Jenkins 파이프라인 실행
```

#### GWT UI 컴포넌트 추가

**1. 새로운 Element 클래스 생성**
```java
// web/src/main/java/com/gcgenome/lims/client/NewElement.java
package com.gcgenome.lims.client;

import elemental2.dom.HTMLDivElement;
import static org.jboss.elemento.Elements.*;

public class NewElement {
    public static HTMLDivElement build(Sample sample) {
        return div()
            .add(h1().textContent(sample.getName()))
            .add(p().textContent("Barcode: " + sample.getBarcode()))
            .element();
    }
}
```

**2. Main.java에서 사용**
```java
div.add(NewElement.build(sample));
```

**3. 빌드 및 배포**
```bash
gradle :web:copyWebResources
```

### 7.3 주요 설정 파일 위치

#### Spring Boot 설정
```
src/main/resources/application.yml
```
- 데이터베이스 연결 정보
- Zookeeper 설정
- 서버 포트 설정
- 로깅 설정

#### Gradle 빌드 설정
```
build.gradle.kts              # 루트 프로젝트
settings.gradle.kts           # 모듈 설정
gradle.properties             # GitHub Packages 인증
data/build.gradle.kts         # data 모듈
panel/build.gradle.kts        # panel 모듈
web/build.gradle.kts          # web 모듈
```

#### GWT 설정
```
web/src/main/java/com/gcgenome/lims/Sample.gwt.xml
panel/src/main/java/com/gcgenome/lims/Panel.gwt.xml
```

#### CI/CD 설정
```
Jenkinsfile
.github/workflows/code-review.yaml
```

### 7.4 디버깅 가이드

#### 백엔드 디버깅

**IntelliJ IDEA 디버그 모드**
```
1. Application.kt 우클릭 -> Debug 'Application'
2. 브레이크포인트 설정:
   - Router.kt:23 (sample 메소드)
   - Handler.kt (비즈니스 로직)
3. 요청 보내기 (Postman, curl 등)
4. 브레이크포인트에서 변수 확인
```

**로그 레벨 조정**
```yaml
# application.yml
logging:
  level:
    com.gcgenome.lims: DEBUG
    org.springframework.web: DEBUG
```

#### 프론트엔드 디버깅

**GWT SuperDev 모드**
```bash
gradle :web:gwtSuperDev

# 브라우저에서 접속
# http://localhost:9629/sample.html#12345
```

**브라우저 개발자 도구**
- F12 -> Sources 탭
- GWT 소스맵 활성화
- Java 코드에 브레이크포인트 설정

**콘솔 로그 추가**
```java
// Main.java
DomGlobal.console.log("Sample ID:", sid);
```

---

## 8. 트러블슈팅

### 8.1 빌드 실패

#### 문제: GitHub Packages 인증 실패
```
Could not resolve com.gcgenome:sample-data:2025.08.25-2
```

**해결책**:
```bash
# gradle.properties에 GitHub 인증 정보 추가
echo "github_username=YOUR_USERNAME" >> ~/.gradle/gradle.properties
echo "github_password=YOUR_GITHUB_TOKEN" >> ~/.gradle/gradle.properties
```

#### 문제: GWT 컴파일 메모리 부족
```
OutOfMemoryError: Java heap space
```

**해결책**:
```bash
# gradle.properties에 힙 크기 증가
echo "org.gradle.jvmargs=-Xmx4096m" >> gradle.properties
```

#### 문제: 모듈 의존성 해결 실패
```
Cannot resolve external dependency com.gcgenome:sample-panel:1.0
```

**해결책**:
```bash
# panel 모듈을 먼저 Maven Local에 발행
gradle :panel:publishToMavenLocal

# 그 다음 web 빌드
gradle :web:build
```

### 8.2 런타임 에러

#### 문제: 데이터베이스 연결 실패
```
Connection to localhost:5432 refused
```

**해결책**:
```bash
# PostgreSQL 실행 확인
sudo systemctl status postgresql

# PostgreSQL 시작
sudo systemctl start postgresql
```

#### 문제: Zookeeper 연결 실패
```
Could not connect to Zookeeper
```

**해결책**:
```bash
# Zookeeper 실행 확인
sudo systemctl status zookeeper

# application.yml에서 연결 정보 확인
```

### 8.3 배포 이슈

#### 문제: systemd 서비스 시작 실패
```bash
sudo systemctl start lims-sample
# Job for lims-sample.service failed
```

**해결책**:
```bash
# 로그 확인
sudo journalctl -u lims-sample -n 50

# JAR 파일 권한 확인
ls -l /data/lims/sample-service.jar

# 실행 권한 부여
chmod +x /data/lims/sample-service.jar
```

---

## 9. 추가 개선 사항 제안

### 9.1 문서화
- [ ] README.md 작성
- [ ] API 문서 자동 생성 (Swagger/OpenAPI)
- [ ] 아키텍처 다이어그램 작성

### 9.2 테스트
- [ ] 단위 테스트 추가 (JUnit 5)
- [ ] 통합 테스트 추가 (Testcontainers)
- [ ] E2E 테스트 (Selenium)

### 9.3 코드 품질
- [ ] 정적 분석 도구 추가 (SonarQube, ktlint)
- [ ] 코드 커버리지 측정 (JaCoCo)
- [ ] 의존성 보안 스캔

### 9.4 CI/CD
- [ ] Jenkins 파이프라인 개선 (테스트 자동 실행)
- [ ] Docker 컨테이너화
- [ ] Kubernetes 배포

### 9.5 모니터링
- [ ] Prometheus + Grafana 연동
- [ ] 로그 수집 (ELK Stack)
- [ ] 알림 설정 (Slack, Email)

---

## 10. 참고 자료

### 10.1 기술 문서

- **Spring Boot**: https://docs.spring.io/spring-boot/docs/3.3.2/reference/html/
- **Spring WebFlux**: https://docs.spring.io/spring-framework/reference/web/webflux.html
- **R2DBC**: https://r2dbc.io/
- **GWT**: https://www.gwtproject.org/doc/latest/DevGuide.html
- **Elemento**: https://github.com/hal/elemento

### 10.2 주요 코드 위치 참조

- 라우터 설정: `src/main/kotlin/com/gcgenome/lims/service/Router.kt:14-21`
- 보안 설정: `src/main/kotlin/com/gcgenome/lims/SecurityConfig.kt:44-68`
- GWT 엔트리: `web/src/main/java/com/gcgenome/lims/client/Main.java:12-32`
- Jenkins 파이프라인: `Jenkinsfile:8-106`

---

**문서 작성자**: Claude AI
**최종 수정일**: 2025-11-21
**버전**: 1.0

이 문서는 프로젝트 분석을 위해 자동 생성되었습니다. 실제 운영 환경과 차이가 있을 수 있으므로, 팀원과 확인 후 사용하시기 바랍니다.
