# Cook Assistant

'Cook Assistant'는 사용자 맞춤형 비건 레시피 변환 애플리케이션입니다. 이 애플리케이션은 Flutter를 사용하여 구현되었으며, 다양한 기능을 제공하여 사용자가 쉽게 접근하고 활용할 수 있도록 설계되었습니다.

## 주요 기능

- **홈:** 최신 레시피와 추천 레시피를 한눈에 볼 수 있습니다.<br>
- **커뮤니티:** 사용자들이 레시피와 요리 팁을 공유하고 소통할 수 있는 공간입니다.<br>
- **레시피 제작:** RAG(Recipe Assembly Generator)를 사용하여 사용자가 원하는 재료로 레시피를 쉽게 생성할 수 있습니다.<br>
- **마이페이지:** 개인 정보 관리 및 즐겨찾기 레시피를 관리할 수 있습니다.<br>
- **나의 냉장고:** 집에 있는 재료를 등록하고 관리할 수 있습니다.<br>
- **식재료 등록:** 새로운 식재료를 쉽게 추가하고 업데이트할 수 있습니다.<br>

'Cook Assistant'는 사용자의 편의를 최우선으로 하여, 비건 레시피를 쉽게 접근하고 활용할 수 있도록 다채로운 기능을 제공합니다. 이를 통해 누구나 건강하고 맛있는 비건 요리를 손쉽게 만들 수 있습니다.

## RAG (Retriever-Augmented-Generator)

<img src="https://private-user-images.githubusercontent.com/39723498/348604921-850fd42f-69de-4047-9acb-bab2ad2f5d68.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjEwMjE4NjIsIm5iZiI6MTcyMTAyMTU2MiwicGF0aCI6Ii8zOTcyMzQ5OC8zNDg2MDQ5MjEtODUwZmQ0MmYtNjlkZS00MDQ3LTlhY2ItYmFiMmFkMmY1ZDY4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA3MTUlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNzE1VDA1MzI0MlomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTllNWIwYWNkMjdiN2FkZWQxN2E1ZDI4MTRhNjRlZDczZjNlZGQ0YmQyZDJlZmEzNzkzNTljOTA2NGJhMTdmZWQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.BJButDfTPoBJ0VOR2f1zzEY7Sv4nE3j53i8S-MkyWBo ">
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
<img src="https://private-user-images.githubusercontent.com/39723498/348605774-fb6e5e5c-915c-45bf-8eb4-51d45467515d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MjEwMjE1OTAsIm5iZiI6MTcyMTAyMTI5MCwicGF0aCI6Ii8zOTcyMzQ5OC8zNDg2MDU3NzQtZmI2ZTVlNWMtOTE1Yy00NWJmLThlYjQtNTFkNDU0Njc1MTVkLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA3MTUlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNzE1VDA1MjgxMFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTM1YWI3MzBjZTg4Y2IxZWMxMDMyNzgxNjY0Yjc0ZDFmYzIxYTY2NTFiMmYzZjUyMTlhZTliNTUyMjRjMTA1NmQmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.UuCWGrnrTB3t82mw27tDdgqW5YGLL_rDIL93X8WghHo ">


