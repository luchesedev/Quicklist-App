
# QuickList 📱

O **QuickList** é um aplicativo mobile desenvolvido para Android com o objetivo de gerenciar cadastros de clientes de forma dinâmica e persistente. O projeto foca em uma interface intuitiva e na aplicação de boas práticas de Engenharia de Software.

## 🚀 Tecnologias Utilizadas

- **Linguagem:** Java
- **Layout:** XML (Material Design)
- **Banco de Dados:** SQLite (Persistência local)
- **Ferramenta de Desenvolvimento:** Android Studio
- **Arquitetura:** MVC (Model-View-Controller)

## 🏗️ Arquitetura e Organização

O projeto foi estruturado utilizando o padrão **MVC**, garantindo a separação de responsabilidades:

- **Model:** Representação da entidade `Cliente` e lógica de dados.
- **View:** Interfaces XML e atividades que interagem com o usuário.
- **Controller (DAO):** Camada de acesso a dados (`ClienteDao`), responsável por todas as operações no banco de dados SQLite.



## 🛠️ Funcionalidades (CRUD)

O aplicativo permite o ciclo completo de gerenciamento de informações:

* **Inserção:** Cadastro de novos clientes com validação de dados.
* **Consulta:** Listagem em tempo real utilizando `ListView` e `ArrayAdapter`.
* **Alteração:** Atualização de dados existentes com feedback visual ao usuário.
* **Exclusão:** Remoção de registros com mensagens de confirmação via `Snackbar`.

## 📈 Conceitos Aplicados

Durante o desenvolvimento, foram consolidados conhecimentos fundamentais para a formação em Análise e Desenvolvimento de Sistemas (ADS):

1.  **POO (Programação Orientada a Objetos):** Abstração, encapsulamento e modularização.
2.  **Ciclo de Vida da Activity:** Gerenciamento de estados da aplicação (onCreate, onResume, etc.).
3.  **Persistência de Dados:** Manipulação de tabelas, cláusulas SQL e `ContentValues`.
4.  **UX (User Experience):** Implementação de feedbacks visuais, tratamento de listas vazias e design responsivo.

---
