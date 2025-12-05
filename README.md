# 🏋️‍♀️ Gym ERP: 스마트 헬스장 통합 관리 시스템

> **데이터 무결성과 조회 성능을 최우선으로 고려한 헬스장 전용 ERP 솔루션입니다.**

<br>

## 📖 프로젝트 소개
**AI 기반의 매출/회원 수 예측 기능을 포함한 지능형 ERP 시스템**입니다.
단순한 관리 기능을 넘어, 대용량 트랜잭션 상황에서도 **데이터의 정합성(Data Integrity)** 을 보장하고, 사용자에게 **쾌적한 조회 속도**를 제공하는 안정적인 백엔드 시스템을 구축하는 데 집중했습니다.

* **개발 기간:** 2025.10 ~ 2025.11 (4주)
* **개발 인원:** 총 9명 (Backend 4명, Frontend 5명 / 기능 단위 풀스택 협업)
* **배포 주소:** [서비스 바로가기 (Click)](https://www.singing-potato.store/)
* **Frontend Repository:** [Github Link](https://github.com/aidnKim/GymErpFront)

<br>

## 🛠️ 기술 스택 (Tech Stack)

| 구분 | 기술 |
| :-- | :-- |
| **Backend** | Java 17, Spring Boot 3.x, MyBatis, Oracle 21c XE |
| **Frontend** | React, JavaScript(ES6+), Bootstrap |
| **DevOps** | AWS EC2, Docker, Nginx, GitHub Actions |
| **Tools** | Swagger, JUnit 5, Git, Notion |

<br>

## 🏗️ 시스템 아키텍처 (Architecture)
AWS EC2와 Docker를 활용하여 독립적인 배포 환경을 구축하였으며, Nginx를 Reverse Proxy로 두어 보안과 성능을 강화했습니다. GitHub Actions를 통해 CI/CD 파이프라인을 자동화했습니다.

![System Architecture](https://github.com/user-attachments/assets/9af224a0-8e2f-4a87-9a71-f5a3d9fd0aa5)

<br>

## 🔥 핵심 기술적 성과 (Troubleshooting)

### 1. 재고 조회 성능 86% 개선 (반정규화 적용)
* **문제:** 초기 설계 시 입/출고 로그를 실시간으로 집계(`SUM`)하여 재고를 산출했으나, 데이터 증가 시 **조회 속도가 평균 2.54s까지 지연**되는 문제 발생. (N+1 및 고비용 연산 문제)
* **해결:** 읽기(Read) 성능 확보를 위해 **반정규화(Denormalization)** 를 적용. `current_stock` 컬럼을 추가하여 연산 시점을 '조회'에서 '쓰기(입출고)' 시점으로 이동시키는 **Calculation on Write** 전략 채택.
* **결과:** 복잡한 Join과 연산을 단순 조회(O(1))로 변경하여 **응답 속도 0.35s 달성 (약 7배 단축)**.

### 2. 데이터 무결성 보장을 위한 트랜잭션 관리
* **문제:** 재고 입/출고와 동시에 재고 수량이 변경되어야 하는데, 중간에 오류 발생 시 데이터 불일치 위험 존재.
* **해결:** `Purchase(입고)` 및 `StockAdjustment(출고)` 서비스 로직에 **`@Transactional`** 어노테이션을 적용.
* **결과:** 예외 발생 시 전체 로직이 **Rollback**되도록 처리하여, 어떤 상황에서도 **데이터 오차 0%**를 보장하는 원자성(Atomicity) 확보.

<br>

## 📂 ERD (Database Design)
상품, 재고, 판매 데이터가 유기적으로 연결되도록 정규화된 모델링을 수행했습니다.

<a href="https://www.erdcloud.com/d/xvPxpRBEiL5KuEF8E" target="_blank">
  <img width="1154" height="815" alt="Image" src="https://github.com/user-attachments/assets/479a5c2f-fce3-4d6a-9f3c-5e622dde4dab" />
</a>
<br>

> 👆 **위 이미지를 클릭**하거나 <a href="https://www.erdcloud.com/d/xvPxpRBEiL5KuEF8E" target="_blank">여기(ERD Cloud)</a>를 누르면 상세 컬럼과 관계를 확인할 수 있습니다.

<br>

## 🚀 주요 기능 (Key Features)
* **상품/재고 관리:** 실시간 재고 트래킹 및 입출고 이력 관리
* **회원/직원 관리:** 회원권 등록 및 PT 배정 시스템
* **매출 통계:** 일별/월별 매출 시각화 및 AI 예측 데이터 제공 (Python 연동)

<br>

## 🧑‍💻 담당 역할 (My Contribution)
**상품 및 재고 관리 도메인 전담**
* **Database:** Oracle DB 설계 및 PL/SQL 튜닝, 반정규화 모델링
* **Backend:** RESTful API 설계 및 구현, 트랜잭션 및 동시성 제어 로직 구현
* **Frontend:** React 기반의 재고 관리 UI/UX 구현 및 API 연동
* **DevOps:** AWS EC2 서버 구축 및 Docker 컨테이너 배포

<br>

💡 협업 및 개발 프로세스 저희 팀은 체계적인 협업을 위해 Git Flow 전략과 엄격한 테스트 환경을 구축하여 운영했습니다. 👉 팀 협업 규칙 및 테스트 가이드 보러가기 [팀 가이드 보러가기(Click)](./TEAM_GUIDE.md)
