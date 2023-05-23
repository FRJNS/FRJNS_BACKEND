# FRJNS_BACKEND_SERVER

본 프로젝트는 [FRJNS-Client](https://github.com/FRJNS/FRJNS_CLIENT)에 정보를 전달하는 API 서버입니다.

## Introduce
* 현재 달 기준 뉴진스의 스케줄을 제공합니다. (마지막 최신화 - 23년 5월)
* 관리자는 뉴진스의 스케줄을 관리할 수 있습니다.
* 자신의 MBTI로 어떤 뉴진스 멤버와 잘 맞는지 제공해줍니다.
* youtube Data API의 키워드 검색을 이용해 뉴진스 관련 유튜브 url을 제공 (구현 예정)

## DB ERD
![image](https://github.com/GDSC-SKHU/FRJNS-Backend/assets/63100425/ddb0335a-6efb-4460-b8b0-e865db9c82b2)

## Tech Stack
* JDK-17
* Spring Boot-3.0.5
* MySQL

## Deployment Environment
* AWS EC2
* AWS RDS
* Docker
* Nginx
* GitHub Action

## Try it
* [Server](https://frjns.duckdns.org/)

## API Docs
* API 문서화는 Swagger 사용
* [Docs](https://frjns.duckdns.org/swagger-ui/index.html)

## License
* [MIT License](LICENSE.md)
# FRJNS_BACKEND