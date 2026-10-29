# CodexShelf 📚

API REST para gerenciar livros de uma biblioteca pessoal, desenvolvida em **Java 17 + Spring Boot 3.5 + Maven**.

## Funcionalidades
- Cadastrar livro (`POST /api/livros`)
- Listar livros (`GET /api/livros`)
- Consultar por ID (`GET /api/livros/{id}`)
- Atualizar livro (`PUT /api/livros/{id}`)
- Excluir livro (`DELETE /api/livros/{id}`)
- Validar campos obrigatórios e responder com erro 400; ID inexistente retorna 404.

## Tecnologias
Java 17, Spring Boot, Spring Web, Spring Data JPA, Bean Validation, H2 (arquivo local), JUnit 5 e MockMvc.

## Como executar no IntelliJ IDEA
1. Abra a pasta do projeto no IntelliJ (opção **Open**).
2. Configure o **JDK 17 ou superior** em Project SDK.
3. Aguarde importar as dependências Maven.
4. Execute `CodexShelfApplication.java` (botão Run).
5. Teste os endpoints no Postman ou IntelliJ HTTP Client em `http://localhost:8080`.

**Pelo terminal (com Maven instalado):** `mvn spring-boot:run`.

## JSON para cadastrar ou atualizar
```json
{
  "titulo": "Dom Casmurro",
  "autor": "Machado de Assis",
  "genero": "Romance",
  "anoPublicacao": 1899,
  "statusLeitura": "LIDO"
}
```

Os valores aceitos para `statusLeitura` são: `NAO_LIDO`, `LENDO`, `LIDO`. O `id` é gerado automaticamente.

## Testes
Execute `mvn test` ou, no IntelliJ, clique com o botão direito em `LivroControllerIntegrationTest` e selecione **Run**. São 6 testes automatizados para CRUD, 404 e validação.

**Observação:** os testes estão escritos mas devem ser executados no ambiente com Maven e dependências disponíveis antes da entrega.

## Banco de dados
O H2 armazena dados localmente na pasta `data/` (criada ao executar). A pasta está no `.gitignore`. Console H2: `http://localhost:8080/h2-console`, JDBC URL `jdbc:h2:file:./data/biblioteca`, usuário `sa`, senha vazia. O console é adequado apenas para desenvolvimento local.

## Estrutura
- `model`: entidade `Livro` e enum de status
- `repository`: acesso ao banco de dados
- `service`: regras e operações de CRUD
- `controller`: endpoints REST
- `exception`: respostas padronizadas para recursos não encontrados e erros de validação
- `src/test`: testes de integração

## Publicar no GitHub
```bash
git init
git add .
git commit -m "feat: implementa API REST de gerenciamento de livros"
git branch -M main
git remote add origin https://github.com/SEU-USUARIO/codexshelf.git
git push -u origin main
```
Crie o repositório no GitHub antes do `git push` e substitua `SEU-USUARIO` pelo login correto.
