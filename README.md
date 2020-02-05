# Pessoas APP

## Projeto Java - pessoas-api

O projeto java foi concebido com intuito de facilitar a subida da aplicação.
Foi escolhido a seguinte plataforma arquitetural:
- Gradle
- Springboot Suite (Spring Rest, JPA, Spring Data, Spring Security)
- JaCoCo - Cobertura de Tests
- Lombok
- JUnit5
- H2 Database.

Os motivos foram:

### Gradle
\A suite de utilização do Gradle une os melhores dos mundos: Ant e Maven.
\A aplicação torna-se muito mais legível que o maven, pois não temos aquela quantidade gigantesca de XMLs do pom

### Springboot
\A aplicação utiliza springboot em seu core. A suite spring proporciona facilidade de desenvolver a aplicação com uma plataforma completamente estável e fácil de utilizar, está no mercado a mais de 15 anos e é padrão na utilização de microserviços

### JaCoCo
\A aplicação utiliza a cobertura de testes com Jacoco que dá uma cobertura do código utilizado.
\Atualmente a aplicação está com 49% de cobertura, porém o restante da cobertura é, basicamente, em métodos Equals e HashCode, que não tem vinculo quando relacionado a cobertura de testes.
\Todo o core negocial está coberto por testes.

### Lombok
\O projeto lombok facilita as tarefas diarias como: Criar Getters, Setters, Buuilder, classes imutáveis, injeção de loggers com SL4J e Log4j
\É necessário que, caso a aplicação seja importada em uma IDE, a mesma tenha o plugin do gradle instalado.

### JUnit5
\JUnit 5 veio com várias novidades, dentre elas a possibilidade de ordenação dos testes, melhorias em performance e facilidade no desenvolvimento de testes unitários.

### H2 Database
\Com Springboot fica fácil desenvolver a aplicação de forma sustentável

### Como efetuar o build e dockerizar a aplicação java
    ´´´
        cd pessoas-app
        ./gradlew build jacocoTestReport
        docker image build .
    ´´´

    Caso queira ver o resultado dos testes, acesse a pasta pessoas-api/build/jacocoHtml

### Projeto Java - extras
\A aplicação possui funcinalidades extras:
- Swagger UI - http://localhost:8080/swagger-ui.html
- Teste de integração utilizando JUnit (Testes dos Endpoints rest)
- Versão 2 da API
- Filtros da api de pessoas utilizando o VO como RequestPram
 
