# ForumHub | Challenge Back End

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-green)
![Maven](https://img.shields.io/badge/Maven-3.8.8-orange)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)
![API Status](https://img.shields.io/badge/API-Online-brightgreen)
![License](https://img.shields.io/badge/License-MIT-lightgrey)
![GitHub last commit](https://img.shields.io/github/last-commit/wandersonjafe/forum-hub)
![GitHub issues](https://img.shields.io/github/issues/wandersonjafe/forum-hub)
![GitHub pull requests](https://img.shields.io/github/issues-pr/wandersonjafe/forum-hub)

---

## 🌟 Descrição

API RESTful para gerenciamento de tópicos de fórum, construída com **Spring Boot** e utilizando **MySQL** como banco de dados.  

Permite criar, listar, detalhar, atualizar e excluir tópicos de forma segura, validando duplicidades e garantindo consistência.

---

## 🚀 Funcionalidades

- 🆕 Cadastro de tópicos
- 📄 Listagem de tópicos (com paginação)
- 🔍 Detalhamento de tópicos por ID
- ✏️ Atualização de tópicos
- 🗑️ Exclusão de tópicos
- ✅ Validação de dados obrigatórios
- ⚠️ Evita duplicidade de tópicos (mesmo título e mensagem)

---

## 🛠 Tecnologias

- 💻 **Java 17** ![Java](https://img.shields.io/badge/Java-17-blue)
- 🌱 **Spring Boot 3.5.4** ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-green)
- 📦 **Maven 3.8.8** ![Maven](https://img.shields.io/badge/Maven-3.8.8-orange)
- 🗄️ **MySQL** ![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
- 🧪 **Insomnia / Postman** ![Insomnia](https://img.shields.io/badge/Insomnia-2023-purple)

---

## 🔗 Endpoints

| Método | URI | Descrição |
|--------|-----|-----------|
| 🟢 POST   | `/topicos` | Criar um novo tópico |
| 📄 GET    | `/topicos` | Listar todos os tópicos |
| 🔍 GET    | `/topicos/{id}` | Detalhar um tópico por ID |
| ✏️ PUT   | `/topicos/{id}` | Atualizar um tópico existente |
| 🗑️ DELETE | `/topicos/{id}` | Excluir um tópico por ID |

---

## ⚠️ Observações

- Todos os campos são obrigatórios ao cadastrar ou atualizar um tópico.
- A API valida duplicidade de tópicos pelo **título** e **mensagem**.
- Teste os endpoints usando **Insomnia** ou **Postman**.
- As badges mostram o status do projeto, últimos commits, issues e pull requests.

---
## 📝 Licença

Este projeto está licenciado sob a **MIT License**.
