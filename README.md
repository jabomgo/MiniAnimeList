# MiniAniList

O **MiniAniList** é uma aplicação acadêmica em Java com Spring Boot que simula um catálogo de animes inspirado no MyAnimeList.
Permite criar usuários, adicionar animes à lista pessoal, avaliar obras e escrever reviews.

Explorando conceitos:

* REST APIs
* Spring Data JPA
* Validações
* DTOs
* Autenticação

> ~Usamos um banco H2 em arquivo para simplicidade~. Agora a aplicação utiliza um **banco PostgreSQL**.

---

## Pré-requisitos

* Java 17+
* Maven
* PostgreSQL
* IntelliJ IDEA (recomendado para facilitar execução)

---

## Executando o projeto

1. **Clonar o repositório**

```bash
git clone https://github.com/jabomgo/MiniAnimeList
cd MiniAniList
```

2. **Configurar variáveis de ambiente**

* Copie o arquivo de template `.env.template` para `.env`:

```bash
cp .env.template .env
```

* Abra o `.env` e adicione suas credenciais do PostgreSQL (usuário, senha, host, porta, etc.).

> ⚠️ Isso garante que a aplicação consiga acessar o banco corretamente.

3. **Executar no IntelliJ**

* Abra o projeto no IntelliJ.
* Isso evita a necessidade de depender das variáveis do `.env` diretamente no terminal, tornando mais fácil testar endpoints e funcionalidades locais.

4. **Ou executar via terminal**

⚠️ Ao rodar pelo terminal, certifique-se de que as variáveis do arquivo .env estão corretamente configuradas no terminal, pois a aplicação depende delas para se conectar ao banco.

> O IntelliJ consegue se resolver quando usamos o `.env`

```bash
mvn spring-boot:run
```

---

## Base de dados utilizada

* Dataset para popular o banco: [Top Anime Dataset 2024 - Kaggle](https://www.kaggle.com/datasets/bhavyadhingra00020/top-anime-dataset-2024)
