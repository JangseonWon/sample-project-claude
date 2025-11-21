# 신규 패널 추가 완벽 가이드

> **대상**: 유전체 검사 패널을 추가해야 하는 개발자
> **난이도**: 중급
> **소요 시간**: 첫 패널 추가 시 2-4시간, 숙련 후 30분-1시간
> **작성일**: 2025-11-21

---

## 📋 목차

1. [패널 추가 개요](#1-패널-추가-개요)
2. [기존 패널 구조 분석](#2-기존-패널-구조-분석)
3. [패널 타입별 구현 방법](#3-패널-타입별-구현-방법)
4. [신규 패널 추가 10단계 체크리스트](#4-신규-패널-추가-10단계-체크리스트)
5. [실전 예제: NewCancerPanel 추가](#5-실전-예제-newcancerpanel-추가)
6. [테스트 및 검증](#6-테스트-및-검증)
7. [흔한 실수 및 주의사항](#7-흔한-실수-및-주의사항)
8. [트러블슈팅](#8-트러블슈팅)

---

## 1. 패널 추가 개요

### 1.1 패널이란?

유전체 검사 패널은 **특정 질환 또는 목적에 맞는 유전자 집합**을 정의하는 데이터 모델입니다.

예시:
- **혈액암 패널** (BloodCancerPanel): 급성골수성백혈병(AML) 관련 유전자 검사
- **희귀질환 패널** (RareDiseasePanel): 부정맥, 심근병증 등 희귀질환 유전자 검사
- **단일 유전자 패널** (SingleGenePanel): 특정 유전자 하나에 집중

### 1.2 패널 추가 시 수정해야 할 파일

| 번호 | 파일 경로 | 역할 | 필수 여부 |
|------|----------|------|----------|
| 1 | `data/src/main/kotlin/.../test/XXXPanel.kt` | 패널 정의 (신규 생성) | ✅ 필수 |
| 2 | `data/build.gradle.kts` | 버전 업데이트 | ✅ 필수 |
| 3 | `src/main/kotlin/.../service/Handler.kt` | 비즈니스 로직 (필요 시) | 선택 |
| 4 | `web/src/main/java/.../client/XXXElement.java` | UI 컴포넌트 (필요 시) | 선택 |

**중요**: 이 가이드는 **1번 (패널 정의)**에 집중합니다. 2-4번은 프로젝트 요구사항에 따라 추가 작업이 필요할 수 있습니다.

### 1.3 패널 추가 워크플로우

```
┌─────────────────────────────────────┐
│  Step 1: 요구사항 분석              │
│  - 패널 타입 결정                  │
│  - 유전자 목록 확보                │
│  - 검사 방법 정의                  │
└─────────────┬───────────────────────┘
              ↓
┌─────────────────────────────────────┐
│  Step 2: Kotlin 파일 생성           │
│  - data class 정의                 │
│  - companion object에 인스턴스 추가 │
└─────────────┬───────────────────────┘
              ↓
┌─────────────────────────────────────┐
│  Step 3: 빌드 및 테스트             │
│  - 컴파일 확인                     │
│  - 인스턴스 생성 검증              │
└─────────────┬───────────────────────┘
              ↓
┌─────────────────────────────────────┐
│  Step 4: 버전 업데이트 및 배포      │
│  - data 모듈 버전 업                │
│  - Maven 발행                      │
└─────────────────────────────────────┘
```

---

## 2. 기존 패널 구조 분석

### 2.1 패널 타입 비교

| 패널 타입 | 파일명 | 라인 수 | 주요 특징 | 사용 사례 |
|----------|--------|---------|----------|----------|
| **NIPT** | NIPT.kt | 130 | 염색체 이상 검사 | 산전 검사 |
| **SingleGenePanel** | SingleGenePanel.kt | 243 | 단일 유전자 집중 | 특정 유전자 변이 검사 |
| **BloodCancerPanel** | BloodCancerPanel.kt | 983 | Exon 기반 구조 | 혈액암 패널 |
| **RareDiseasePanel** | RareDiseasePanel.kt | 1,195 | genes + addendum | 희귀질환 패널 |
| **SolidTumorPanel** | SolidTumorPanel.kt | 239 | 고형암 검사 | 암 패널 |

### 2.2 공통 구조 패턴

모든 패널은 다음 패턴을 따릅니다:

```kotlin
data class XXXPanel(
    val code: String,              // 패널 코드 (예: "N064")
    val name: String,              // 패널 이름 (한글)
    val serialGroup: String,       // 일련번호 그룹
    val method: XXXAnalysisMethod, // 검사 방법
    val genes: List<String>,       // 유전자 목록
    // ... 기타 필드
): HasCode, HasName, IsPanelAnalysis by method, Reportable {
    // 인터페이스 구현...

    companion object {
        val N001 = XXXPanel(code="N001", name="패널 1", ...)
        val N002 = XXXPanel(code="N002", name="패널 2", ...)

        fun values() = listOf(N001, N002, ...)
    }
}
```

### 2.3 핵심 인터페이스

**필수 인터페이스**

| 인터페이스 | 메소드 | 설명 |
|-----------|--------|------|
| `HasCode` | `code()` | 패널 코드 (예: "N064") |
| `HasName` | `name()` | 패널 이름 |
| `HasCategory` | `category()` | 카테고리 (Cancer, RareDisease 등) |
| `IsPanelAnalysis` | `panel()`, `region()`, `probe()` 등 | 검사 방법 정보 |
| `Reportable` | `reportCategory()`, `i18n()` | 보고서 관련 |

**선택적 인터페이스**

| 인터페이스 | 메소드 | 사용 패널 |
|-----------|--------|----------|
| `ContainsGenes` | `genes()` | RareDiseasePanel, SingleGenePanel |
| `ContainsExons` | `exons(group)` | BloodCancerPanel, SolidTumorPanel |
| `ContainsAddendumGenes` | `addendum()` | RareDiseasePanel (추가 유전자) |
| `HasReferralDefault` | `referralDefault()` | 대부분의 패널 |
| `MayBeNationalInsurance` | `isNationalInsuranceTest()` | 건강보험 적용 패널 |

### 2.4 실제 예제 분석

#### 예제 1: NIPT (가장 간단한 구조)

**파일**: `data/src/main/kotlin/com/gcgenome/lims/test/NIPT.kt:12-24`

```kotlin
data class NIPT (
    val code: String,
    val name: String,
    val serialGroup: String = "GN",
    val method: NiptAnalysisMethod,
    val nationalInsurance: Boolean = true,
    val i18n: Reportable.I18N = Reportable.I18N.KoKr,
    val report: Reportable.Category = Reportable.Category.NIPT,
    val limitation: List<String>,
    val references: List<String> = REFERENCES
): HasCode, HasName, HasSerialGroup, HasCategory,
   MayBeNationalInsurance, IsNiptAnalysis by method, Reportable {

    val category: HasCategory.Category = HasCategory.Category.NIPT
    override fun code() = code
    override fun name() = name
    override fun serialGroup(): String = serialGroup
    override fun title() = name
    override fun category() = category
    override fun isNationalInsuranceTest() = nationalInsurance
    override fun i18n(): Reportable.I18N = i18n
    override fun reportCategory(): Reportable.Category = report
}
```

**특징**:
- `method` 필드로 분석 방법을 위임 (`by method`)
- `limitation` (제한사항), `references` (참고문헌) 포함
- 인터페이스 메소드 직접 구현

**인스턴스 예제**: `NIPT.kt:65-74`

```kotlin
companion object {
    private val N006 = NIPT(
        code="N006",
        name="비침습적 산전 염색체 이상 선별검사 Prime",
        limitation = LIMITATION_KOKR,
        method = NiptAnalysisMethod(
            performance = performanceKo,
            target = Target(
                trisomies = allWithoutT13T18T21,
                monosomies = all,
                cnvs = cnvTargetPrime
            )
        )
    )

    fun values() = listOf(N006, N019, N128, O001, O002, O003)
}
```

---

#### 예제 2: RareDiseasePanel (유전자 목록 기반)

**파일**: `data/src/main/kotlin/com/gcgenome/lims/test/RareDiseasePanel.kt:7-35`

```kotlin
data class RareDiseasePanel(
    val code: String,
    val name: String,
    val displayName: String? = null,
    val serialGroup: String,
    val referralDefault: String,
    val method: PanelAnalysisMethod,
    val genes: List<String>,              // 필수 유전자
    val addendum: List<String> = listOf(), // 추가 유전자
    val nationalInsurance: Boolean = true,
    val category: HasCategory.Category = HasCategory.Category.RareDisease,
    val interpretationCategory: Interpretable.Category = Interpretable.Category.RareDisease,
    val reportCategory: Reportable.Category = Reportable.Category.RareDisease,
    val i18n: Reportable.I18N = Reportable.I18N.KoKr
): HasCode, HasCategory, HasName, HasDisplayName, HasSerialGroup,
   ContainsGenes, ContainsAddendumGenes, IsPanelAnalysis by method,
   HasReferralDefault, MayBeNationalInsurance, Interpretable, Reportable {

    override fun code() = code
    override fun category() = category
    override fun name() = name
    override fun displayName() = displayName
    override fun genes() = genes
    override fun addendum() = addendum
    override fun referralDefault() = referralDefault
    override fun isNationalInsuranceTest() = nationalInsurance
}
```

**특징**:
- `genes`: 필수 유전자 목록
- `addendum`: 추가 유전자 목록 (선택적)
- `displayName`: 표시용 이름 (선택적)
- `referralDefault`: 의뢰 기본값

**인스턴스 예제**: `RareDiseasePanel.kt:37-44`

```kotlin
companion object {
    val N037 = RareDiseasePanel(
        code ="N037",
        name ="부정맥 유전자 패널검사",
        displayName = "ARH_부정맥 유전자 패널검사",
        referralDefault ="유전성 부정맥",
        serialGroup = "WES",
        method =RareDiseaseAnalysisMethod(
            panel="Arrhythmia NGS panel",
            region="19 genes (191,040 bp)",
            probe="Hybridization with oligonucleotide probes(ST v.2201.1)"
        ),
        genes ="ANK2,CACNA1C,CALM1,CALM2,CALM3,CASQ2,KCNE1,KCNE2,KCNH2,KCNJ2,KCNQ1,RYR2,SCN5A,TRDN,CACNA2D1,CACNB2,SLC4A3,TANGO2,TECRL"
            .split(",").sorted().toList()
    )
}
```

**중요**: 유전자 목록은 반드시 `.split(",").sorted().toList()`로 정렬!

---

#### 예제 3: BloodCancerPanel (Exon 기반)

**파일**: `data/src/main/kotlin/com/gcgenome/lims/test/BloodCancerPanel.kt:6-16`

```kotlin
data class BloodCancerPanel (
    val code: String,
    val name: String,
    val serialGroup: String,
    val referralDefault: String,
    val method: SomaticPanelAnalysisMethod,
    val essential: List<Exon>,   // 필수 Exon
    val selective: List<Exon>,   // 선택 Exon
    val nationalInsurance: Boolean,
    val i18n: Reportable.I18N = Reportable.I18N.KoKr
): SomaticCancerPanel, IsPanelAnalysis by method,
   ContainsExons, Interpretable, Reportable {
    // ...
}
```

**Exon 객체 구조**:

```kotlin
Exon("CEBPA", "All coding exons", "NM_004364")
Exon("FLT3", "14-20", "NM_004119")
Exon("IDH1", "4", "NM_005896")
```

**인스턴스 예제**: `BloodCancerPanel.kt:59-73`

```kotlin
companion object {
    private val N064 = BloodCancerPanel(
        code="N064",
        name="급성골수성백혈병 유전자 패널검사",
        serialGroup = "AML",
        referralDefault="AML",
        panel="Acute Myeloid Leukemia (AML) Panel",
        abbreviation = "HEMA",
        subpanel = "AML",
        essential=listOf(
            Exon("CEBPA", "All coding exons", "NM_004364"),
            Exon("FLT3", "14-20", "NM_004119"),
            Exon("IDH1", "4", "NM_005896"),
            // ... 더 많은 Exon
        ),
        selective=listOf(
            Exon("ABL1","Exon 4-8","NM_005157"),
            // ... 더 많은 Exon
        ),
        nationalInsurance = true
    )
}
```

---

## 3. 패널 타입별 구현 방법

### 3.1 타입 선택 가이드

| 질문 | YES면 | NO면 |
|------|-------|------|
| 단일 유전자만 검사하나요? | SingleGenePanel | 다음 질문 |
| Exon 단위로 관리하나요? | BloodCancerPanel 또는 SolidTumorPanel | 다음 질문 |
| 희귀질환 패널인가요? | RareDiseasePanel | 다음 질문 |
| 산전 검사인가요? | NIPT | 다른 타입 검토 |

### 3.2 타입별 필수 필드

#### 3.2.1 RareDiseasePanel (가장 흔한 타입)

**필수 필드**:
```kotlin
code: String              // "N037"
name: String              // "부정맥 유전자 패널검사"
serialGroup: String       // "WES", "CAN", "AML" 등
referralDefault: String   // "유전성 부정맥"
method: PanelAnalysisMethod
genes: List<String>       // 유전자 목록
```

**선택 필드**:
```kotlin
displayName: String? = null
addendum: List<String> = listOf()
nationalInsurance: Boolean = true
```

#### 3.2.2 BloodCancerPanel

**필수 필드**:
```kotlin
code: String
name: String
serialGroup: String
referralDefault: String
panel: String             // "Acute Myeloid Leukemia (AML) Panel"
abbreviation: String      // "HEMA"
subpanel: String          // "AML"
essential: List<Exon>     // 필수 Exon
selective: List<Exon>     // 선택 Exon
nationalInsurance: Boolean
```

#### 3.2.3 SingleGenePanel

**필수 필드**:
```kotlin
code: String
gene: String              // "SLC26A4" (단일 유전자명)
serialGroup: String
method: SingleGeneAnalysisMethod
```

**선택 필드**:
```kotlin
name: String?             // 지정하지 않으면 자동 생성
addendum: List<String> = listOf()
referralDefault: String?
```

---

## 4. 신규 패널 추가 10단계 체크리스트

### ✅ Step 1: 요구사항 수집

**수집해야 할 정보**:
- [ ] 패널 코드 (예: "N999")
- [ ] 패널 이름 (한글)
- [ ] 검사 대상 질환
- [ ] 유전자 목록 (또는 Exon 목록)
- [ ] 검사 방법 (NGS, WES, Sanger 등)
- [ ] 건강보험 적용 여부
- [ ] 참조 유전체 버전 (GRCh37/hg19, GRCh38 등)

**예시**:
```
패널 코드: N999
패널 이름: 신경퇴행성질환 유전자 패널검사
질환: 알츠하이머병, 파킨슨병 등
유전자: APP, PSEN1, PSEN2, SNCA, LRRK2, PARK7, PINK1, PRKN
검사 방법: NGS
건강보험: 비급여
```

---

### ✅ Step 2: 패널 타입 결정

**결정 플로우차트**:
```
단일 유전자인가?
  YES → SingleGenePanel
  NO  ↓
Exon 단위 관리 필요?
  YES → BloodCancerPanel 또는 SolidTumorPanel
  NO  ↓
희귀질환인가?
  YES → RareDiseasePanel
  NO  → 새로운 타입 생성 필요
```

**예시 결정**: 신경퇴행성질환 → 여러 유전자, Exon 단위 불필요 → **RareDiseasePanel**

---

### ✅ Step 3: Kotlin 파일 생성 (또는 기존 파일 수정)

**3.1 새로운 타입인 경우 (예: NeuroDegenerativePanel.kt 생성)**

```bash
# 파일 생성
touch data/src/main/kotlin/com/gcgenome/lims/test/NeuroDegenerativePanel.kt
```

**3.2 기존 타입 사용 (예: RareDiseasePanel.kt 수정)**

기존 파일에 새로운 인스턴스만 추가

---

### ✅ Step 4: data class 정의

**4.1 RareDiseasePanel 타입 사용 시**

기존 data class 재사용 (정의 불필요)

**4.2 새로운 타입 생성 시**

```kotlin
package com.gcgenome.lims.test

import com.gcgenome.lims.test.method.PanelAnalysisMethod

data class NeuroDegenerativePanel(
    val code: String,
    val name: String,
    val serialGroup: String,
    val referralDefault: String,
    val method: PanelAnalysisMethod,
    val genes: List<String>,
    val nationalInsurance: Boolean = false,
    val i18n: Reportable.I18N = Reportable.I18N.KoKr
): HasCode, HasCategory, HasName, HasSerialGroup,
   ContainsGenes, IsPanelAnalysis by method,
   HasReferralDefault, MayBeNationalInsurance,
   Interpretable, Reportable {

    val category: HasCategory.Category = HasCategory.Category.RareDisease
    val interpretationCategory: Interpretable.Category = Interpretable.Category.RareDisease
    val reportCategory: Reportable.Category = Reportable.Category.RareDisease

    override fun code() = code
    override fun category() = category
    override fun name() = name
    override fun serialGroup() = serialGroup
    override fun title() = name
    override fun genes() = genes
    override fun referralDefault() = referralDefault
    override fun isNationalInsuranceTest() = nationalInsurance
    override fun interpretationCategory() = interpretationCategory
    override fun reportCategory() = reportCategory
    override fun i18n() = i18n

    companion object {
        // 인스턴스는 Step 5에서 추가
    }
}
```

---

### ✅ Step 5: companion object에 인스턴스 추가

**예시 1: RareDiseasePanel에 추가**

파일: `data/src/main/kotlin/com/gcgenome/lims/test/RareDiseasePanel.kt`

```kotlin
companion object {
    // 기존 패널들...
    val N037 = RareDiseasePanel(...)
    val N038 = RareDiseasePanel(...)

    // ✨ 새로운 패널 추가
    val N999 = RareDiseasePanel(
        code = "N999",
        name = "신경퇴행성질환 유전자 패널검사",
        displayName = "NEURO_신경퇴행성질환 유전자 패널검사",
        referralDefault = "알츠하이머병, 파킨슨병",
        serialGroup = "WES",
        method = RareDiseaseAnalysisMethod(
            panel = "Neurodegenerative Disease Panel",
            region = "8 genes (120,000 bp)",
            probe = "Hybridization with oligonucleotide probes(NEURO v.2201.1)"
        ),
        genes = "APP,LRRK2,PARK7,PINK1,PRKN,PSEN1,PSEN2,SNCA"
            .split(",").sorted().toList(),
        nationalInsurance = false
    )

    fun values() = listOf(
        N037, N038, ..., N999  // ✨ values()에도 추가!
    )
}
```

**주의사항**:
1. ⚠️ `genes` 필드는 **반드시 알파벳 순서**로 정렬! `.split(",").sorted().toList()`
2. ⚠️ `values()` 함수에 새 인스턴스 추가 필수!
3. ⚠️ `code`는 기존 패널과 중복되지 않도록!

---

### ✅ Step 6: PanelAnalysisMethod 정의

**RareDiseaseAnalysisMethod 헬퍼 함수 사용**:

```kotlin
method = RareDiseaseAnalysisMethod(
    panel = "Neurodegenerative Disease Panel",  // 영문 패널명
    region = "8 genes (120,000 bp)",            // 유전자 수 + 총 염기쌍
    probe = "Hybridization with oligonucleotide probes(NEURO v.2201.1)"  // 프로브 방식
)
```

**직접 정의** (고급):

```kotlin
method = PanelAnalysisMethod(
    panel = "Neurodegenerative Disease Panel",
    region = "8 genes (120,000 bp)",
    probe = "Hybridization with oligonucleotide probes(NEURO v.2201.1)",
    sequencing = "Sequencing by synthesis (Illumina)",
    reference = "GRCh37/hg19",
    pipeline = "BI_GRM v2.2 (Alignment: BWA, Variant calling: GATK)",
    limitation = "본 검사는 2개 이상의 exon에 걸쳐서 발생한 large deletion/duplication은 대부분 검출 가능하나..."
)
```

---

### ✅ Step 7: 빌드 테스트

```bash
# data 모듈 빌드
gradle :data:build

# 컴파일 에러 확인
# - Syntax 오류
# - 인터페이스 미구현
# - Import 누락
```

**예상 에러 및 해결**:

| 에러 메시지 | 원인 | 해결 방법 |
|------------|------|----------|
| `Unresolved reference: RareDiseaseAnalysisMethod` | Import 누락 | `import com.gcgenome.lims.test.method.PanelAnalysisMethod.Companion.RareDiseaseAnalysisMethod` 추가 |
| `Data class must have at least one primary constructor parameter` | data class 필드 없음 | 필수 필드 추가 |
| `Class 'XXXPanel' is not abstract and does not implement abstract member` | 인터페이스 메소드 미구현 | `override fun xxx()` 추가 |

---

### ✅ Step 8: 인스턴스 생성 검증

**Kotlin REPL 또는 테스트 코드 작성**:

```kotlin
// Test.kt (임시 테스트 파일)
package com.gcgenome.lims.test

fun main() {
    val panel = RareDiseasePanel.N999

    println("Code: ${panel.code()}")
    println("Name: ${panel.name()}")
    println("Genes: ${panel.genes()}")
    println("Panel: ${panel.panel()}")
    println("Region: ${panel.region()}")

    // 기대 출력:
    // Code: N999
    // Name: 신경퇴행성질환 유전자 패널검사
    // Genes: [APP, LRRK2, PARK7, PINK1, PRKN, PSEN1, PSEN2, SNCA]
    // Panel: Neurodegenerative Disease Panel
    // Region: 8 genes (120,000 bp)
}
```

---

### ✅ Step 9: data 모듈 버전 업데이트

**파일**: `data/build.gradle.kts`

```kotlin
// 변경 전
version = "2025.08.25-2"

// 변경 후 (날짜 + 시퀀스 증가)
version = "2025.11.21-1"
```

**버전 명명 규칙**:
- 형식: `YYYY.MM.DD-N`
- `YYYY.MM.DD`: 수정 날짜
- `N`: 같은 날짜 내 시퀀스 번호 (1부터 시작)

---

### ✅ Step 10: Maven 발행 및 확인

```bash
# 1. Maven Local에 발행
gradle :data:publishToMavenLocal

# 2. 발행 확인
ls ~/.m2/repository/com/gcgenome/sample-data/2025.11.21-1/

# 기대 출력:
# sample-data-2025.11.21-1.jar
# sample-data-2025.11.21-1.pom
# sample-data-2025.11.21-1-sources.jar

# 3. (선택) GitHub Packages에 발행
gradle :data:publish
```

---

## 5. 실전 예제: NewCancerPanel 추가

### 5.1 시나리오

**요구사항**:
- 패널 코드: `N888`
- 패널 이름: `췌장암 유전자 패널검사`
- 질환: 췌장암 (Pancreatic Cancer)
- 유전자: `BRCA1`, `BRCA2`, `CDKN2A`, `KRAS`, `MLH1`, `MSH2`, `PALB2`, `TP53`
- 검사 방법: NGS
- 건강보험: 비급여
- 패널 타입: RareDiseasePanel 타입 재사용

### 5.2 Step-by-Step 구현

#### Step 1-2: 요구사항 확인 및 타입 결정

✅ 타입: **RareDiseasePanel** (여러 유전자, Exon 단위 불필요)

#### Step 3-4: 기존 파일 사용 (RareDiseasePanel.kt)

새 파일 생성 불필요 ✅

#### Step 5: companion object에 인스턴스 추가

**파일**: `data/src/main/kotlin/com/gcgenome/lims/test/RareDiseasePanel.kt`

**변경 전**:
```kotlin
companion object {
    val N037 = RareDiseasePanel(...)
    val N038 = RareDiseasePanel(...)
    // ... (생략)

    fun values() = listOf(N037, N038, ...)
}
```

**변경 후**:
```kotlin
companion object {
    val N037 = RareDiseasePanel(...)
    val N038 = RareDiseasePanel(...)
    // ... (생략)

    // ✨ 새로운 췌장암 패널 추가
    val N888 = RareDiseasePanel(
        code = "N888",
        name = "췌장암 유전자 패널검사",
        displayName = "PANC_췌장암 유전자 패널검사",
        referralDefault = "췌장암",
        serialGroup = "CAN",  // 암 관련이므로 CAN
        method = RareDiseaseAnalysisMethod(
            panel = "Pancreatic Cancer Panel",
            region = "8 genes (95,000 bp)",
            probe = "Hybridization with oligonucleotide probes(PANC v.2201.1)"
        ),
        genes = "BRCA1,BRCA2,CDKN2A,KRAS,MLH1,MSH2,PALB2,TP53"
            .split(",").sorted().toList(),
        nationalInsurance = false,  // 비급여
        category = HasCategory.Category.Cancer,  // 암 카테고리
        interpretationCategory = Interpretable.Category.Cancer,
        reportCategory = Reportable.Category.Cancer
    )

    fun values() = listOf(N037, N038, ..., N888)  // ✨ 추가!
}
```

#### Step 6: 유전자 목록 정렬 확인

```kotlin
genes = "BRCA1,BRCA2,CDKN2A,KRAS,MLH1,MSH2,PALB2,TP53"
    .split(",").sorted().toList()

// 결과: [BRCA1, BRCA2, CDKN2A, KRAS, MLH1, MSH2, PALB2, TP53]
// ✅ 알파벳 순서로 정렬됨
```

#### Step 7: 빌드 테스트

```bash
gradle :data:build

# 성공 출력:
# BUILD SUCCESSFUL in 12s
# 3 actionable tasks: 3 executed
```

#### Step 8: 검증 코드 작성

```kotlin
fun main() {
    val panel = RareDiseasePanel.N888

    println("=== Pancreatic Cancer Panel ===")
    println("Code: ${panel.code()}")
    println("Name: ${panel.name()}")
    println("Display: ${panel.displayName()}")
    println("Serial Group: ${panel.serialGroup()}")
    println("Referral: ${panel.referralDefault()}")
    println("Category: ${panel.category()}")
    println("Genes (${panel.genes().size}): ${panel.genes().joinToString(", ")}")
    println("National Insurance: ${panel.isNationalInsuranceTest()}")
    println("\n=== Analysis Method ===")
    println("Panel: ${panel.panel()}")
    println("Region: ${panel.region()}")
    println("Probe: ${panel.probe()}")
}

/* 기대 출력:
=== Pancreatic Cancer Panel ===
Code: N888
Name: 췌장암 유전자 패널검사
Display: PANC_췌장암 유전자 패널검사
Serial Group: CAN
Referral: 췌장암
Category: Cancer
Genes (8): BRCA1, BRCA2, CDKN2A, KRAS, MLH1, MSH2, PALB2, TP53
National Insurance: false

=== Analysis Method ===
Panel: Pancreatic Cancer Panel
Region: 8 genes (95,000 bp)
Probe: Hybridization with oligonucleotide probes(PANC v.2201.1)
*/
```

#### Step 9-10: 버전 업데이트 및 발행

```bash
# 1. 버전 업데이트
# data/build.gradle.kts
version = "2025.11.21-1"

# 2. 빌드
gradle :data:build

# 3. Maven Local 발행
gradle :data:publishToMavenLocal

# 4. 확인
ls ~/.m2/repository/com/gcgenome/sample-data/2025.11.21-1/
```

### 5.3 완성된 코드 전체

```kotlin
// data/src/main/kotlin/com/gcgenome/lims/test/RareDiseasePanel.kt

package com.gcgenome.lims.test

import com.gcgenome.lims.test.method.PanelAnalysisMethod
import com.gcgenome.lims.test.method.PanelAnalysisMethod.Companion.RareDiseaseAnalysisMethod

data class RareDiseasePanel(
    // ... (기존 정의 동일)
) {
    // ... (기존 구현 동일)

    companion object {
        // ... (기존 패널들)

        val N888 = RareDiseasePanel(
            code = "N888",
            name = "췌장암 유전자 패널검사",
            displayName = "PANC_췌장암 유전자 패널검사",
            referralDefault = "췌장암",
            serialGroup = "CAN",
            method = RareDiseaseAnalysisMethod(
                panel = "Pancreatic Cancer Panel",
                region = "8 genes (95,000 bp)",
                probe = "Hybridization with oligonucleotide probes(PANC v.2201.1)"
            ),
            genes = "BRCA1,BRCA2,CDKN2A,KRAS,MLH1,MSH2,PALB2,TP53"
                .split(",").sorted().toList(),
            nationalInsurance = false,
            category = HasCategory.Category.Cancer,
            interpretationCategory = Interpretable.Category.Cancer,
            reportCategory = Reportable.Category.Cancer
        )

        fun values() = listOf(
            N037, N038, ..., N888
        )
    }
}
```

---

## 6. 테스트 및 검증

### 6.1 컴파일 테스트

```bash
# 1. 클린 빌드
gradle :data:clean :data:build

# 2. 에러 없이 빌드 성공 확인
# BUILD SUCCESSFUL in Xs
```

### 6.2 단위 테스트 작성 (권장)

**파일**: `data/src/test/kotlin/com/gcgenome/lims/test/PancreatricCancerPanelTest.kt`

```kotlin
package com.gcgenome.lims.test

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class PancreaticCancerPanelTest {

    @Test
    fun `N888 패널이 올바르게 생성되는지 확인`() {
        val panel = RareDiseasePanel.N888

        assertNotNull(panel)
        assertEquals("N888", panel.code())
        assertEquals("췌장암 유전자 패널검사", panel.name())
        assertEquals("CAN", panel.serialGroup())
    }

    @Test
    fun `유전자 목록이 정렬되어 있는지 확인`() {
        val panel = RareDiseasePanel.N888
        val genes = panel.genes()

        assertEquals(8, genes.size)
        assertEquals(genes, genes.sorted())  // 정렬 확인
        assertTrue(genes.contains("BRCA1"))
        assertTrue(genes.contains("TP53"))
    }

    @Test
    fun `카테고리가 Cancer로 설정되었는지 확인`() {
        val panel = RareDiseasePanel.N888

        assertEquals(HasCategory.Category.Cancer, panel.category())
        assertEquals(Interpretable.Category.Cancer, panel.interpretationCategory())
        assertEquals(Reportable.Category.Cancer, panel.reportCategory())
    }

    @Test
    fun `건강보험 비급여 확인`() {
        val panel = RareDiseasePanel.N888

        assertFalse(panel.isNationalInsuranceTest())
    }

    @Test
    fun `values() 메소드에 포함되어 있는지 확인`() {
        val allPanels = RareDiseasePanel.values()

        assertTrue(allPanels.any { it.code() == "N888" })
    }
}
```

**테스트 실행**:
```bash
gradle :data:test --tests PancreaticCancerPanelTest
```

### 6.3 통합 테스트

**시나리오**: 백엔드 API에서 N888 패널 조회

```bash
# 1. Spring Boot 실행
gradle bootRun

# 2. API 호출 (예시)
curl http://localhost:8080/panels/N888

# 기대 응답:
# {
#   "code": "N888",
#   "name": "췌장암 유전자 패널검사",
#   "genes": ["BRCA1", "BRCA2", "CDKN2A", "KRAS", "MLH1", "MSH2", "PALB2", "TP53"],
#   "category": "Cancer"
# }
```

### 6.4 체크리스트

- [ ] 컴파일 에러 없음
- [ ] 단위 테스트 통과
- [ ] `values()` 메소드에 포함됨
- [ ] 유전자 목록 정렬 확인
- [ ] 카테고리 올바름
- [ ] 건강보험 여부 올바름
- [ ] 버전 업데이트 완료
- [ ] Maven 발행 성공

---

## 7. 흔한 실수 및 주의사항

### 7.1 흔한 실수 TOP 10

| 순위 | 실수 | 증상 | 해결 방법 |
|------|------|------|----------|
| 1 | 유전자 목록 미정렬 | 검색 시 누락 | `.split(",").sorted().toList()` 필수 |
| 2 | `values()`에 추가 안 함 | 패널이 조회되지 않음 | companion object의 `values()`에 추가 |
| 3 | 코드 중복 | 빌드 에러 | 기존 코드와 중복 확인 |
| 4 | Import 누락 | Unresolved reference 에러 | 필요한 클래스 import |
| 5 | 인터페이스 미구현 | Abstract member 에러 | 모든 인터페이스 메소드 `override` |
| 6 | 버전 업데이트 안 함 | 변경사항 반영 안 됨 | `data/build.gradle.kts` 버전 증가 |
| 7 | 잘못된 카테고리 | 보고서 분류 오류 | 질환에 맞는 Category 사용 |
| 8 | serialGroup 오타 | 일련번호 생성 오류 | "WES", "CAN", "AML" 등 정확히 |
| 9 | method 설정 누락 | NullPointerException | PanelAnalysisMethod 필수 설정 |
| 10 | 한글 인코딩 깨짐 | 패널 이름 깨짐 | 파일 인코딩 UTF-8 확인 |

### 7.2 주의사항

#### 7.2.1 유전자 목록 정렬

**❌ 잘못된 예**:
```kotlin
genes = "TP53,BRCA1,KRAS,BRCA2".split(",").toList()
// 결과: [TP53, BRCA1, KRAS, BRCA2] - 정렬 안 됨!
```

**✅ 올바른 예**:
```kotlin
genes = "TP53,BRCA1,KRAS,BRCA2".split(",").sorted().toList()
// 결과: [BRCA1, BRCA2, KRAS, TP53] - 정렬됨!
```

**이유**: 데이터베이스 검색 및 비교 시 정렬된 목록 필요

#### 7.2.2 values() 메소드 업데이트

**❌ 잘못된 예**:
```kotlin
companion object {
    val N888 = RareDiseasePanel(...)

    fun values() = listOf(N037, N038)  // N888 누락!
}
```

**✅ 올바른 예**:
```kotlin
companion object {
    val N888 = RareDiseasePanel(...)

    fun values() = listOf(N037, N038, N888)  // 추가!
}
```

#### 7.2.3 카테고리 일관성

**카테고리 선택 가이드**:

| 질환 타입 | HasCategory.Category | Interpretable.Category | Reportable.Category |
|----------|---------------------|------------------------|---------------------|
| 암 관련 | `Cancer` | `Cancer` | `Cancer` |
| 희귀질환 | `RareDisease` | `RareDisease` | `RareDisease` |
| 혈액암 | `BloodCancer` | `BloodCancer` | `BloodCancer` |
| NIPT | `NIPT` | - | `NIPT` |

**예시**:
```kotlin
// 췌장암은 Cancer 카테고리
category = HasCategory.Category.Cancer,
interpretationCategory = Interpretable.Category.Cancer,
reportCategory = Reportable.Category.Cancer
```

#### 7.2.4 serialGroup 값

**표준 serialGroup 목록**:

| serialGroup | 설명 | 사용 패널 |
|------------|------|----------|
| `WES` | Whole Exome Sequencing | 대부분의 희귀질환 패널 |
| `CAN` | Cancer | 암 관련 패널 |
| `AML` | Acute Myeloid Leukemia | 급성골수성백혈병 |
| `GN` | Genetic | NIPT 등 |

---

## 8. 트러블슈팅

### 8.1 컴파일 에러

#### 에러 1: Unresolved reference

**에러 메시지**:
```
Unresolved reference: RareDiseaseAnalysisMethod
```

**원인**: Import 누락

**해결**:
```kotlin
import com.gcgenome.lims.test.method.PanelAnalysisMethod.Companion.RareDiseaseAnalysisMethod
```

---

#### 에러 2: Data class must have parameters

**에러 메시지**:
```
Data class must have at least one primary constructor parameter
```

**원인**: data class에 필드 없음

**해결**:
```kotlin
// ❌ 잘못
data class XXXPanel() { }

// ✅ 올바름
data class XXXPanel(
    val code: String,
    val name: String
) { }
```

---

#### 에러 3: Abstract member not implemented

**에러 메시지**:
```
Class 'XXXPanel' is not abstract and does not implement abstract member public abstract fun code(): String
```

**원인**: 인터페이스 메소드 미구현

**해결**:
```kotlin
data class XXXPanel(...): HasCode {
    override fun code() = code  // ✅ 추가
}
```

---

### 8.2 런타임 에러

#### 에러 1: NullPointerException

**에러 메시지**:
```
java.lang.NullPointerException: panel.panel() must not be null
```

**원인**: `method` 필드 미설정

**해결**:
```kotlin
val panel = XXXPanel(
    // ...
    method = RareDiseaseAnalysisMethod(...)  // ✅ 필수!
)
```

---

#### 에러 2: 패널이 조회되지 않음

**증상**: API 호출 시 404 Not Found

**원인**: `values()` 메소드에 추가 안 함

**해결**:
```kotlin
companion object {
    val N888 = XXXPanel(...)

    fun values() = listOf(..., N888)  // ✅ 추가!
}
```

---

### 8.3 빌드 이슈

#### 이슈 1: 버전 충돌

**에러 메시지**:
```
Could not resolve com.gcgenome:sample-data:2025.11.21-1
```

**원인**: 이전 버전 캐시

**해결**:
```bash
# Gradle 캐시 삭제
rm -rf ~/.gradle/caches/

# 의존성 새로고침
gradle :data:build --refresh-dependencies
```

---

#### 이슈 2: Maven 발행 실패

**에러 메시지**:
```
Task :data:publishToMavenLocal FAILED
```

**원인**: 권한 문제 또는 경로 문제

**해결**:
```bash
# Maven Local 디렉토리 권한 확인
ls -ld ~/.m2/repository/

# 권한 수정
chmod -R u+w ~/.m2/repository/

# 재시도
gradle :data:publishToMavenLocal
```

---

## 9. 추가 리소스

### 9.1 참고 파일

- **NIPT 패널**: `data/src/main/kotlin/com/gcgenome/lims/test/NIPT.kt`
- **희귀질환 패널**: `data/src/main/kotlin/com/gcgenome/lims/test/RareDiseasePanel.kt`
- **혈액암 패널**: `data/src/main/kotlin/com/gcgenome/lims/test/BloodCancerPanel.kt`
- **인터페이스 정의**: `data/src/main/java/com/gcgenome/lims/test/`

### 9.2 관련 문서

- **프로젝트 전체 분석**: `PROJECT_ANALYSIS.md`
- **빌드 가이드**: `PROJECT_ANALYSIS.md` 섹션 6
- **유지보수 가이드**: `PROJECT_ANALYSIS.md` 섹션 7

### 9.3 도움 요청

**팀 내 문의**:
1. 패널 코드 부여: 관리자 확인
2. 유전자 목록 검증: 생물정보학팀
3. 검사 방법 세부사항: 검사실

---

## 10. 요약 체크리스트

### 신규 패널 추가 전 확인사항

- [ ] 패널 코드 확보 (기존 코드와 중복 없음)
- [ ] 패널 이름 (한글, 영문)
- [ ] 유전자 목록 (정확한 Gene Symbol)
- [ ] 검사 방법 정보
- [ ] 카테고리 결정
- [ ] 건강보험 적용 여부

### 코딩 시 확인사항

- [ ] 올바른 패널 타입 선택
- [ ] data class 또는 기존 타입 사용
- [ ] companion object에 인스턴스 추가
- [ ] `values()` 메소드에 추가
- [ ] 유전자 목록 `.sorted()` 적용
- [ ] Import 문 추가
- [ ] 인터페이스 메소드 구현

### 빌드 및 배포 확인사항

- [ ] 컴파일 에러 없음
- [ ] 단위 테스트 작성 및 통과
- [ ] data 모듈 버전 업데이트
- [ ] Maven Local 발행 성공
- [ ] (선택) GitHub Packages 발행

---

**문서 작성자**: Claude AI
**최종 수정일**: 2025-11-21
**버전**: 1.0

이 가이드를 따라하면 신규 패널을 안전하게 추가할 수 있습니다. 추가 질문이나 문제가 있으면 팀원에게 문의하세요!
