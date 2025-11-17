# Prática

## 🧠 Descrição
API REST em Spring Boot para gerenciar produtos, com:
- Operações CRUD completas (Create, Read, Update, Delete)
- Testes unitários com Mockito
- Testes de desempenho com JMeter

## ⚙️ Tecnologias
- Java 17
- Spring Boot 3
- JPA / H2 Database
- JUnit 5
- Mockito
- Apache JMeter

## 🚀 Como executar a aplicação
```bash
mvnw.cmd spring-boot:run

Acesse: http://localhost:8080/produtos
🧪 Testes unitários

mvnw.cmd test

📊 Testes de desempenho (JMeter)

    Abra o arquivo jmeter/pratica06_plan.jmx no Apache JMeter.

    Adicione Listeners:

        Summary Report

        Aggregate Report

        View Results Tree

    Execute o teste e gere relatórios (CSV ou capturas de tela).

📬 Exemplos de requisição (curl)

Criar produto:

curl -X POST http://localhost:8080/produtos \
 -H "Content-Type: application/json" \
 -d '{"nome":"Caneta","descricao":"Azul","preco":2.5,"estoque":10}'

Listar todos:

curl http://localhost:8080/produtos

Buscar por ID:

curl http://localhost:8080/produtos/1

📂 Estrutura do Projeto

    src/main/java → código-fonte

    src/test/java → testes com Mockito

    jmeter/ → plano de testes de desempenho
