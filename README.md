Used technologies:
kafka 3.1.2,
Spring boot 3.1.2,
mapstruct,
Spring Data JPA,
db: h2,
lombok

id infrastructure:
user microservice (kafka producer) ---message---> document microservice (kafka consumer) ------> grant id card ------> to do

market app:
authorized client --> commerce microservice --> storage microservice --> payment service | to guarantee stream processing used kafka topics instead kafka streams
