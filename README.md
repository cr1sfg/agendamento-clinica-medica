# 🏥 Sistema de Agendamento - Clínica Médica

Projeto desenvolvido em **Java** durante a graduação em **Análise e Desenvolvimento de Sistemas** na **Unisinos**, como parte da integralização do grau A.  
A proposta foi fixar conceitos de **Programação Orientada a Objetos (POO)** através da criação de um sistema com tema sorteado pelos alunos desenvolvido individualmente.

---

## 🎯 Objetivo
- Aplicar conceitos de **POO** em um projeto prático.  
- Criar um sistema de agendamento com múltiplas funcionalidades.  
- Implementar validações e regras de negócio em Java.  
- Utilizar arquivos `.txt` para leitura e persistência de dados.  

---

## 🛠️ Tecnologias Utilizadas
- **Java**  
- **POO** 
- **Arquivos TXT** 

---

## ✨ Funcionalidades
O sistema conta com diversas opções de gerenciamento, algumas sendo:

- **Cadastro de Paciente**  
  - Validações: nome não pode estar em branco ou ter menos de 3 caracteres.  
  - Data de nascimento não pode ser posterior ao dia atual.  

- **Cadastro de Médico**  
  - Verificação se o médico já existe no arquivo antes de cadastrar.  

- **Agendamento de Consultas**  
  - Realizar novo agendamento.  
  - Consultar agendamentos existentes.  
  - Cancelar agendamento.  
  - Reagendar agendamento.  
  - Listar todas as consultas de um paciente.  

- **Listagens e Impressões**  
  - Lista de pacientes.  
  - Lista de médicos.  
  - Lista de agendamentos realizados.  

---

## 📂 Estrutura do Projeto
O sistema foi organizado em diferentes classes para aplicar os conceitos de **Programação Orientada a Objetos**:

- **GerenciadorClinica.java** → contém os métodos principais de gerenciamento da clínica (cadastros, listagens, agendamentos).  
- **MainMenu.java** → contém o `main` com opções em `switch case`, incluindo todas as validações de entrada.  
- **Medico.java** → classe que representa o médico, com propriedades como:
  - `crm`  
  - `nome`  
  - `especialidade`  
  Inclui também os métodos referentes à classe.  
- **Paciente.java** → classe que representa o paciente, com atributos e seus respectivos **getters e setters**, como:
  - `nome`  
  - `dataNascimento`  
  - outros dados relevantes.  
- **Agendamento.java** → classe que representa o agendamento de consultas, com atributos:
  - `LocalDateTime data`  
  - `Paciente paciente`  
  - `Medico medico`  

Essa estrutura garante separação de responsabilidades e facilita a manutenção e evolução do sistema.

---

## 📚 Alguns exemplos das Validações Implementadas
- Nome do paciente/médico não pode ser vazio ou menor que 3 caracteres.  
- Data de nascimento do paciente não pode ser futura.  
- Datas de agendamento não podem ser em finais de semana.  
- Agendamentos só podem ser feitos dentro do horário comercial.  

---

## 📜 Status
✅ Projeto concluído como exercício acadêmico.  

---

## 🤝 Contribuição
Este projeto é de caráter acadêmico e pessoal, mas feedbacks e sugestões são sempre bem-vindos!
