# Cook Assistant

'Cook Assistant'는 사용자 맞춤형 비건 레시피 변환 애플리케이션입니다. 이 애플리케이션은 Flutter를 사용하여 구현되었으며, 다양한 기능을 제공하여 사용자가 쉽게 접근하고 활용할 수 있도록 설계되었습니다.

## 주요 기능

![그림1](https://github.com/user-attachments/assets/2441862d-b3c8-4a6c-b191-6c3d70696477)

- **홈:** 최신 레시피와 추천 레시피를 한눈에 볼 수 있습니다.<br>
- **커뮤니티:** 사용자들이 레시피와 요리 팁을 공유하고 소통할 수 있는 공간입니다.<br>
- **레시피 제작:** RAG(Recipe Assembly Generator)를 사용하여 사용자가 원하는 재료로 레시피를 쉽게 생성할 수 있습니다.<br>
- **마이페이지:** 개인 정보 관리 및 즐겨찾기 레시피를 관리할 수 있습니다.<br>
- **나의 냉장고:** 집에 있는 재료를 등록하고 관리할 수 있습니다.<br>
- **식재료 등록:** 새로운 식재료를 쉽게 추가하고 업데이트할 수 있습니다.<br>

'Cook Assistant'는 사용자의 편의를 최우선으로 하여, 비건 레시피를 쉽게 접근하고 활용할 수 있도록 다채로운 기능을 제공합니다. 이를 통해 누구나 건강하고 맛있는 비건 요리를 손쉽게 만들 수 있습니다.

## RAG (Retriever-Augmented-Generator)

<img width="704" alt="image" src="https://github.com/user-attachments/assets/850fd42f-69de-4047-9acb-bab2ad2f5d68">
Cook Assistant에서는 NLP 모델에서 질문 응답 및 정보 검색의 능력을 향상시키기 위해 RAG(Retriever-Augmented-Generator) 기술을 사용합니다.<br><br>

RAG는 다음과 같은 특징을 가지고 있습니다:<br>
- **향상된 정보 정확성:** Context를 활용하여 정보의 정확성을 높입니다.<br>
- **다양한 응답 생성:** 단순히 훈련된 데이터뿐만 아니라 사용자가 원하는 정보를 추가하여 다양한 응답을 생성할 수 있습니다.<br><br>

### 작동 원리

1. **데이터 Embedding:** 사전에 VectorDB에 데이터를 Embedding하여 저장합니다.<br>
2. **쿼리 처리:** 사용자가 쿼리를 던지면, 프롬프팅과 Retrieve, Generate 과정을 통해 Augmented된 답변을 출력합니다.<br>
3. **Langchain 사용:** Cook Assistant에서는 이러한 과정들을 체인 형태로 연결해 작동시켜 주는 Langchain을 사용하여 RAG를 구현하였습니다.<br><br>

이 기술을 통해 Cook Assistant는 사용자에게 보다 정확하고 다양한 비건 레시피 정보를 제공합니다.

### System Architecture
<img width="749" alt="image" src="https://github.com/user-attachments/assets/fb6e5e5c-915c-45bf-8eb4-51d45467515d">



### Infra
- **EC2**: 애플리케이션 서버 호스팅
- **RDS**: 관계형 데이터베이스 서비스
- **Docker**: 도커를 이용하여 2개의 서버를 컨테이너로 배포 


### SpringBoot
- **Java**, **JPA**, **Spring Framework**, **Redis**: 유저 인증/인가 처리, 식재료 및 레시피 관리 등 전반적인 안드로이드 API를 관리

### Langchain
- **Python**, **Langchain**, **Langserve**, **Pinecone**: Langchain 프레임워크로 구현되어 있으며, 레시피 생성을 담당. 벡터 DB로부터 데이터를 검색해 RAG를 진행

### ERD
<img width="1043" alt="스크린샷 2024-07-15 오후 2 39 48" src="https://github.com/user-attachments/assets/6366570b-691f-4ccb-b8c4-8cc0fa54be04">



### Front
<img width="890" alt="스크린샷 2024-07-15 오후 2 41 57" src="https://github.com/user-attachments/assets/3326c274-8d06-4d10-b385-d64512ee8d5f">






