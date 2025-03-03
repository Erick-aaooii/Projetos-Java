# <img src=""> Desafio de POO com Java da Dio

### Para este Desafio foi proposto a criação de um objeto que representasse todas as ações de um bootcamp.

Este projeto teve como objetivo aplicar os conceitos de Programação Orientada a Objetos (POO) com Java, utilizando os pilares fundamentais da POO: **Abstração**, **Encapsulamento**, **Herança** e **Polimorfismo**. Durante o desenvolvimento, fui desafiado a modelar diferentes entidades e interações de um bootcamp, como cursos, mentorias e desenvolvedores, em um ambiente de aprendizado.

## Detalhes do Projeto

O desafio consistiu em criar uma aplicação que simulasse as principais ações e interações dentro de um bootcamp. Para isso, criei objetos (classes) que representaram conceitos do mundo real, como:

- **Curso**: Representação de um curso oferecido pelo bootcamp, com propriedades como nome, descrição, carga horária, etc.
- **Mentoria**: Representação de sessões de mentoria com mentores do bootcamp, com informações sobre o mentor, tema da mentoria e horário.
- **Bootcamp**: Representação do próprio bootcamp, onde agrupei os cursos e mentorias, e gerenciei inscrições de participantes.
- **Dev**: Representação de um desenvolvedor que participa do bootcamp, com a possibilidade de se inscrever nos cursos e mentorias.

Durante o desenvolvimento, implementei funcionalidades que envolvem a interação entre essas entidades, como inscrição em cursos e mentorias, e a visualização de conteúdos completados.

## Estrutura de Pastas

O espaço de trabalho contém duas pastas por padrão, onde:

- `src`: a pasta para manter os fontes.
- `lib`: a pasta para manter as dependências.

Enquanto isso, os arquivos de saída compilados foram gerados na pasta `bin` por padrão.

## O que eu aprendi

Durante esse desafio, aprendi a aplicar os conceitos de **Programação Orientada a Objetos (POO)** de forma prática. Os principais aprendizados foram:

### 1. **Abstração**
   - Eu aprendi a **abstrair** conceitos do mundo real em classes e objetos. Ao criar as classes `Curso`, `Mentoria`, `Bootcamp` e `Dev`, fui capaz de identificar os elementos essenciais de cada conceito e representá-los de forma simples e eficiente em código, sem me preocupar com os detalhes desnecessários.

### 2. **Encapsulamento**
   - Eu utilizei o **encapsulamento** para proteger os dados, criando atributos privados e controlando seu acesso através de **métodos getters e setters**. Isso garantiu que os dados dos objetos fossem manipulados de maneira segura e controlada, respeitando o princípio da ocultação de informações e permitindo que as alterações no estado dos objetos fossem feitas de forma segura.

### 3. **Herança**
   - Através da **herança**, fui capaz de criar classes reutilizáveis e mais específicas. Por exemplo, ao criar uma classe `Pessoa` com atributos e métodos comuns a várias entidades, como `Dev` e `Mentor`, consegui evitar duplicação de código e tornar meu código mais eficiente e organizado. A herança também ajudou a compartilhar comportamentos entre diferentes classes.

### 4. **Polimorfismo**
   - O **polimorfismo** me permitiu tratar objetos de diferentes tipos de forma uniforme. Isso foi útil quando precisei implementar métodos que se comportavam de maneira diferente dependendo do tipo de objeto, como ao exibir informações de cursos e mentorias, mas de forma genérica. O polimorfismo aumentou a flexibilidade e a extensibilidade do meu código.

## Como Executar

1. Clone este repositório.
2. Importe o projeto no Visual Studio Code.
3. Certifique-se de que todas as dependências estejam corretamente configuradas.

## Contribuição

Se desejar contribuir com este projeto, sinta-se à vontade para abrir pull requests ou relatar problemas.

## Licença

Este projeto está licenciado sob os termos da licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.
