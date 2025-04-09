## 🔪 Como rodar a aplicação localmente com DynamoDB Local

Esta aplicação usa o **Amazon DynamoDB Local** para simular o banco de dados em ambiente de desenvolvimento. Siga os passos abaixo para configurar e executar localmente.

---

### 📆 1. Requisitos

- Java 8+ instalado (preferencialmente Java 17)
- [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html)
- Internet para baixar o DynamoDB Local

---

### 📁 2. Baixando o DynamoDB Local

1. Acesse: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html  
2. Baixe o `.zip` da seção **"Downloadable version of Amazon DynamoDB"**
3. Extraia o conteúdo em uma pasta, por exemplo:

```bash
A:\Downloads\dynamodb_local_latest\
```

---

### ▶️ 3. Rodando o DynamoDB Local

Execute o comando abaixo para iniciar o DynamoDB Local na porta `8000` e considere os caminhos específicos para seu caso:

```bash
"A:\Downloads\openjdk-17_windows-x64_bin\jdk-17\bin\java.exe" ^
  -Djava.library.path="A:\Downloads\dynamodb_local_latest\DynamoDBLocal_lib" ^
  -jar "A:\Downloads\dynamodb_local_latest\DynamoDBLocal.jar" ^
  -sharedDb
```

> 💭 **Dica:** você pode criar um script `.bat` para facilitar a execução.

---

### 🔧 4. Instalando e configurando o AWS CLI

#### 4.1 Instalar o AWS CLI

Baixe o instalador do AWS CLI v2 em:  
https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-windows.html

#### 4.2 Verificar instalação

```bash
aws --version
```

#### 4.3 Configurar com credenciais falsas (para uso local)

```bash
aws configure
```

Preencha os valores assim:

```
AWS Access Key ID [None]: fake
AWS Secret Access Key [None]: fake
Default region name [None]: us-east-1
Default output format [None]: json
```

---

### 🤕 5. Testando a conexão com o DynamoDB Local

Verifique se está funcionando com:

```bash
aws dynamodb list-tables --endpoint-url http://localhost:8000
```

Saída esperada (caso não tenha nenhuma tabela criada ainda):

```json
{
  "TableNames": []
}
```

---

### 🧑‍💻 6. Configuração do código

Certifique-se de que sua aplicação está criando o cliente do DynamoDB assim:

```java
DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
    .endpointOverride(URI.create("http://localhost:8000"))
    .region(Region.US_EAST_1)
    .credentialsProvider(StaticCredentialsProvider.create(
        AwsBasicCredentials.create("fake", "fake")
    ))
    .build();
```

---


