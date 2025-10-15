# 🛠️ CP5 Java - Comunicação Cliente-Servidor com Criptografia RSA

## 👥 Integrantes do Grupo
- **Felipe Ulson Sora** – RM555462  
- **Augusto Lopes Lyra** – RM558209
- **Vinicius Ribeiro Nery Costa** – RM559165

## 💻 IDE Utilizada
Projeto desenvolvido em **IntelliJ IDEA**.

---

## 🧩 Descrição do Projeto
Este projeto implementa uma **comunicação segura Cliente-Servidor** em Java, utilizando **Sockets TCP/IP** e **criptografia RSA** para troca de mensagens.

O cliente envia uma mensagem criptografada com a chave pública e o servidor a descriptografa usando a chave privada, retornando uma resposta.

---

## ⚙️ Funcionamento

### 🔸 Etapas do processo
1. **Geração das chaves RSA** com os valores:
   - p = 271  
   - q = 397  
   - e = 65537  
   - n = p × q = 107587  
   - φ(n) = (p-1)(q-1) = 106920  
   - d = 24473  

2. **Conexão TCP/IP**
   - Servidor escuta na porta 12345  
   - Cliente conecta a `localhost:12345`

3. **Troca de mensagens**
   - Cliente → Envia mensagem criptografada  
   - Servidor → Descriptografa e confirma o recebimento  

4. **Desconexão segura**

---

## 📂 Classes de Teste e Validação RSA

### 🔹 RSA_MSW

Esta classe implementa **um exemplo simples de criptografia e descriptografia RSA** usando os mesmos valores de p, q e e.

- Permite verificar manualmente a geração de `n`, `φ(n)` e `d`.

- Exibe no console a mensagem original, a mensagem cifrada e a mensagem decifrada.

- Serve como referência didática da criptografia usada no projeto.

### 🔹 RSA_MSW_File

Esta classe **lê a mensagem de um arquivo** `.txt` e aplica a criptografia e descriptografia RSA.

- Permite testar entrada de dados externos sem precisar alterar o código.

- Também imprime no console a mensagem cifrada e decifrada.

- Ajuda a validar os resultados comparando com o simulador Drexel.

> Ambas as classes reforçam a confiabilidade da implementação principal (`RSA.java`) usada na comunicação Cliente-Servidor.

---

## 🧮 Exemplo de Execução

### 🔹 Cliente

**Mensagem original**: `Essa e nossa mensagem utilizada como teste para o cp 5`

**Mensagem criptografada**: `45431 3348 99812 ...`

**Resposta do servidor**: `Mensagem recebida e descriptografada com sucesso!`

### 🔹 Servidor

**Servidor conectado**.

**Mensagem criptografada recebida**: `45431 3348 99812 ...`

**Mensagem descriptografada**: `Essa e nossa mensagem utilizada como teste para o cp 5`

---

## 🧾 Planilha RSA
O arquivo **Dados RSA.xlsx** contém todos os valores de configuração usados no algoritmo RSA.
