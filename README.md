<p align="center">
  <img alt="Tamanho do Repositório" src="https://img.shields.io/github/repo-size/ErichComparin/App-Projeto-Aplicado-IGTI---Java-Android?style=flat-square" />
  
  <img alt="Último commit" src="https://img.shields.io/github/last-commit/ErichComparin/App-Projeto-Aplicado-IGTI---Java-Android?style=flat-square" />

  <a href="https://github.com/ErichComparin">
    <img alt="Feito por Erich Comparin" src="https://img.shields.io/badge/feito%20por-Erich%20Comparin-orange?style=flat-square" />
  </a>

  <a href="./LICENSE">
    <img href="Licença MIT" src="https://img.shields.io/apm/l/vim-mode?style=flat-square" />
  </a>
</p>

<h1 align="center">
    📷Solução para compartilhamento de fotos entre usuários🖼️
</h1>

<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#️-status">Status</a> •
 <a href="#️-funcionalidades">Funcionalidades</a> •
 <a href="#-layout">Layout</a> • 
 <a href="#-como-executar-o-projeto">Como executar</a> • 
 <a href="#-tecnologias">Tecnologias</a> •
 <a href="#️-autor">Autor</a> • 
 <a href="#-licença">Licença</a>
</p>

## 💻 Sobre o projeto

O produto pretende solucionar o desafio de manter acessíveis as fotos de um certo evento de forma compartilhada. Este compartilhamento deve ser restrito a um grupo de pessoas, e não público. O usuário poderá capturar uma foto, e esta será sincronizada com todos os usuários cujo álbum esteja compartilhado. Neste mesmo momento, a imagem será armazenada em um ambiente de nuvem, ficando disponível para futuras visualizações. O público alvo se trata de usuários finais que utilizam seus smartphones para capturar fotografias e desejam um armazenamento em nuvem, ao mesmo tempo que o conteúdo seja compartilhado com os usuários desejados.

A aplicação tem por objetivo propiciar a praticidade e economia de tempo em executar a tarefa de compartilhar fotografias com os amigos e a disponibilidade e facilidade de encontrar fotos antigas e suas respectivas informações. Além disto, a solução proporciona a privacidade do conteúdo, sendo que apenas as pessoas desejadas tem acesso ao mesmo. Provém economia no plano de dados, considerando que a comunicação com a nuvem para sincronismo poderá ser realizada apenas quando o dispositivo estiver conectado à uma rede Wi-Fi, e também no espaço de armazenamento do mesmo, possibilitando realizar o download apenas do conteúdo que o usuário escolher.

O diagrama a seguir representa a arquitetura da aplicação, que utiliza a plataforma Firebase do Google com as seguintes finalidades por serviço:
1. Firebase Realtime Database: banco de dados NoSQL hospedado na nuvem. Armazena os dados da aplicação em JSON e realiza o sincronismo entre os clientes em tempo real.
2. Firebase Authentication: realiza o cadastro e autenticação dos usuários.
3. Cloud Storage for Firebase: armazena os arquivos de imagem dos usuários.
<p align="center">
  <img alt="NextLevelWeek" title="#NextLevelWeek" src="./readme/arq.jpg?raw=true" />
</p>

---

## 🏃‍♂️ Status

- [x] Concluído

---

## ⚙️ Funcionalidades

- [x] Deve possibilitar que o usuário crie uma conta e faça login com a mesma
- [x] Deve possibilitar a criação de álbuns, que serão agrupadores de fotografias
- [x] Deve possibilitar a captura de fotografias com a câmera do dispositivo para inserção em um álbum
- [x] Deve possibilitar a escolha de usuários para realizar o compartilhamento de um álbum
- [x] Deve haver um sistema de armazenamento na nuvem para manter os dados e fotografias
- [x] Deve possibilitar o sincronismo das fotografias com o armazenamento na nuvem, enviando as capturadas pelo usuário e recebendo as capturadas por outros usuários que possuem álbuns compartilhados com ele

---

## 🎨 Layout

- Tela de login
<img width="300" alt="Landing Page" src="./readme/mobile1.jpg?raw=true">

- Tela de cadastro de usuário
<img width="300" alt="Cadastro de aulas" src="./readme/mobile2.jpg?raw=true">

- Lista de álbuns
<img width="300" alt="Consulta de aulas" src="./readme/mobile3.jpg?raw=true">

- Lista de fotos
<img width="300" alt="Consulta de aulas" src="./readme/mobile4.jpg?raw=true">

- Compartilhamento de álbuns
<img width="300" alt="Consulta de aulas" src="./readme/mobile5.jpg?raw=true">

---

## 🚀 Como executar o projeto

```bash

# Clone este repositório
$ git clone https://github.com/ErichComparin/App-Projeto-Aplicado-IGTI---Java-Android.git

# Adicionar o Firebase ao projeto (https://firebase.google.com/docs/android/setup?hl=pt-br)

# Execute o projeto no Android Studio
$ cd Proffy-NLW2---ReactJS

```

---

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

-   **[Android Studio](https://developer.android.com/studio)**

---

## 🧔 Autor

<img alt="Erich Comparin" src="https://avatars1.githubusercontent.com/u/49964553?s=460&u=cbfeb4a52528866ecd92b23fb86afa9bf1cc4ee2&v=4" width="120px"/>

**Erich Comparin**

[![Linkedin Badge](https://img.shields.io/badge/-Erich_Comparin-blue?style=flat-square&logo=Linkedin&logoColor=white&link=ttps://www.linkedin.com/in/erich-comparin-6923119b/)](https://www.linkedin.com/in/erich-comparin-6923119b/) [![Github Badge](https://img.shields.io/badge/-Erich_Comparin-000?style=flat-square&logo=Github&logoColor=white&link=https://github.com/ErichComparin)](https://github.com/ErichComparin) [![Gmail Badge](https://img.shields.io/badge/-erich.comparin@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:erich.comparin@gmail.com)](mailto:erich.comparin@gmail.com)

---

## 📝 Licença

Copyright © 2020 [Erich Comparin](https://github.com/ErichComparin).<br />
This project is [MIT](./LICENSE) licensed.

---

##  Versões do README

[Português 🇧🇷](./README.md)