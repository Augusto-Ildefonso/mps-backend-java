# backend-mps

Este projeto consiste em uma arquitetura de microsserviços desenvolvida com Spring Boot para gestão de usuários e autenticação.

## Estrutura do Projeto

O backend é dividido nos seguintes módulos:

- **`gateway`**: Atua como a porta de entrada do sistema. Responsável pelo roteamento de requisições, configuração de CORS e filtragem de tokens JWT para garantir que as requisições cheguem autenticadas aos microsserviços.
- **`auth-microservice`**: Responsável pela lógica de autenticação, geração de tokens JWT e validação de credenciais.
- **`user-microservice`**: Gerencia as entidades de Usuário e Endereço, lidando com a persistência de dados e regras de negócio relacionadas ao cadastro e manutenção de usuários.

## Como Rodar

### Pré-requisitos
- Java JDK (compatível com a versão definida no `pom.xml`)
- Maven

### Passo a Passo

1. **Build do projeto**:
   Navegue até a raiz de cada módulo e execute:
   ```bash
   mvn clean install
   ```

2. **Execução**:
   Você pode rodar as aplicações via IDE (executando a classe `...Application` de cada módulo) ou via terminal:

   - **Gateway** (Aviso: por conta de conflitos entre o Spring e o Spring-Cloud, roteamos diretamente o auth e o user, então no momento, para teste, não precisa rodar o gateway):
     ```bash
     mvn spring-boot:run -pl gateway
     ```
   - **Auth Microservice**:
     ```bash
     mvn spring-boot:run -pl auth-microservice
     ```
   - **User Microservice**:
     ```bash
     mvn spring-boot:run -pl user-microservice
     ```

*Nota: Certifique-se de iniciar os microsserviços (`auth` e `user`) antes do `gateway` para garantir a conectividade correta.*