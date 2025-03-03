# API de Academia 🏋️‍♀️

## Rotas:

### 1. **POST /alunos**
   - **Descrição**: Cria um novo aluno no sistema.
   - **Corpo da requisição (Request Body)**: 
    - **AlunoDto** (dados do aluno), que deve conter informações como nome, CPF, data de nascimento, etc.
   - **Exemplo de input**:
     ```json
     {
       "message": "usuário criado"
     }
     ```

### 2. **GET /alunos/{id}**
   - **Descrição**: Busca os dados de um aluno pelo seu ID.
   - **Parâmetro**: O `id` do aluno a ser recuperado é passado pela URL.
   - **Resposta**:
     - Retorna os dados do aluno com status **202 (Accepted)**.
     - Se o aluno for encontrado, retorna um objeto JSON com as informações do aluno.
   - **Exemplo de resposta**:
     ```json
     {
       "id": 1,
       "nome": "João Silva",
       "cpf": "123.456.789-00",
       "dataNascimento": "2000-01-01"
     }
     ```

### 3. **PUT /alunos/{id}**
   - **Descrição**: Atualiza os dados de um aluno existente.
   - **Parâmetros**:
     - `id`: O ID do aluno a ser atualizado.
     - **AlunoDto**: O objeto com os dados atualizados do aluno.
   - **Resposta**:
     - Retorna os dados atualizados do aluno com status **202 (Accepted)**.
   - **Exemplo de resposta**:
     ```json
     {
       "id": 1,
       "nome": "João Silva",
       "cpf": "123.456.789-00",
       "dataNascimento": "2000-01-01"
     }
     ```

### 4. **DELETE /alunos/{id}**
   - **Descrição**: Deleta um aluno do sistema com base no seu ID.
   - **Parâmetro**:
     - `id`: O ID do aluno a ser deletado.
   - **Resposta**:
     - Retorna um mapa com a mensagem de sucesso da exclusão e status **202 (Accepted)**.
   - **Exemplo de resposta**:
     ```json
     {
       "message": "Aluno deletado com sucesso"
     }
     ```

### 5. **GET /alunos/list**
   - **Descrição**: Lista todos os alunos cadastrados no sistema.
   - **Resposta**:
     - Retorna uma lista de alunos com status **200 (OK)**.
   - **Exemplo de resposta**:
     ```json
     [
       {
         "id": 1,
         "nome": "João Silva",
         "cpf": "123.456.789-00",
         "dataNascimento": "2000-01-01"
       },
       {
         "id": 2,
         "nome": "Maria Oliveira",
         "cpf": "987.654.321-00",
         "dataNascimento": "1998-05-10"
       }
     ]
     ```

---

## Como funciona a lógica da API?

- A API está organizada em **Controllers** que são responsáveis por manipular as requisições HTTP. Cada endpoint (rota) corresponde a um método HTTP (GET, POST, PUT, DELETE) e está mapeado para uma ação específica, como criar, buscar, atualizar ou excluir dados.
- O **AlunoController** recebe as requisições HTTP, valida os dados e interage com o **AlunoService**, que contém a lógica de negócios.
- Os **DTOs** (Data Transfer Objects) são usados para validar os dados que são enviados nas requisições e evitar problemas como entradas inválidas.
