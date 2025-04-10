## 📦 Sistema de Controle de Estoque — Java + MySQL
Este projeto é um sistema simples de controle de estoque desenvolvido em Java, utilizando JDBC para conexão com banco de dados MySQL. Ele permite realizar operações CRUD (Create, Read, Update, Delete) em produtos armazenados no banco.

---

## 🧱 Estrutura do Projeto

src/ <br>
├── main/ <br>
│   ├── java/com/estoque/<br>
│   │   ├── dao/<br>
│   │   │   ├── ConexaoJava.java       # Gerencia a conexão com o banco de dados<br>
│   │   │   └── ProdutoDAO.java        # Operações de CRUD com os produtos<br>
│   │   ├── model/<br>
│   │   │   └── Produto.java           # Classe que representa o modelo de produto<br>
│   │   └── view/<br>
│   │       └── MenuPrincipal.java     # Interface via terminal (menu principal)<br>
│   └── resources/<br>
│       └── log4j2.xml                 # (Caso esteja utilizando log)

---

## 🧠 Funcionalidades
✅ Cadastrar novo produto

📋 Listar produtos cadastrados

✏️ Atualizar dados de um produto existente

❌ Excluir produto

🔎 Buscar produto por ID

---

## ⚙️ Tecnologias Utilizadas
Java (JDK 11+ recomendado)

JDBC

MySQL (ou adaptável para PostgreSQL)

IntelliJ IDEA (estrutura baseada em projeto Maven)

---

## 📁 Dependências
O projeto utiliza Maven. Dependências podem ser adicionadas no pom.xml. No momento, utiliza apenas bibliotecas padrão do Java e JDBC.

---

## 📌 Observações
Código modular e bem estruturado, separado por camadas (DAO, Model, View).

Flexível para extensão futura com interface gráfica (JavaFX, Swing etc.).

Pode ser facilmente adaptado para uso com PostgreSQL trocando o driver JDBC.

---

## 🔮 Futuras Melhorias
💻 Interface Gráfica com JavaFX
Substituir o menu de console por uma interface visual mais amigável.

🧪 Testes Automatizados (JUnit)
Adicionar testes de unidade para garantir a estabilidade das funcionalidades.

🔍 Filtro e Busca Avançada
Permitir pesquisar produtos por nome, preço mínimo/máximo, etc.

📦 Categoria de Produtos
Implementar categorias para organizar melhor os produtos (ex: Eletrônicos, Roupas, etc).

🧾 Relatórios em PDF ou CSV
Gerar relatórios de estoque exportáveis para PDF ou planilhas.

👤 Sistema de Usuários/Login
Adicionar autenticação para controlar acesso de usuários ao sistema.

🌐 Migração para aplicação Web (Spring Boot)
Evoluir o sistema para um backend com REST APIs e frontend React ou Angular.

☁️ Persistência em Nuvem
Integrar com serviços de banco de dados online (como AWS RDS ou Railway).

📝 Validações mais robustas
Validar entradas de usuário para evitar dados inconsistentes.
