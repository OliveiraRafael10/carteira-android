# Carteira - Aplicativo de Controle Financeiro

## Descrição

Carteira é um aplicativo Android desenvolvido em Java no curso de aplicativos mobile da faculdade para controle financeiro pessoal. O aplicativo permite que os usuários gerenciem suas finanças através do cadastro de lançamentos financeiros, categorização de gastos e visualização de relatórios.

## Funcionalidades

### Principais Recursos

- **Cadastro de Lançamentos**: Registro de entradas e saídas financeiras com descrição, categoria, valor e data
- **Categorização**: Organização dos lançamentos por categorias personalizáveis
- **Visualização de Lançamentos**: Lista completa de todos os lançamentos cadastrados
- **Relatórios**: Visualização de dados financeiros organizados
- **Interface Intuitiva**: Design limpo e navegação por fragmentos

### Estrutura de Dados

- **Lançamentos**: ID, categoria, descrição, tipo (entrada/saída), valor, data (dia/mês/ano)
- **Categorias**: ID e nome da categoria

## Tecnologias Utilizadas

- **Linguagem**: Java
- **Plataforma**: Android (API 24+)
- **Banco de Dados**: SQLite
- **Interface**: Material Design
- **Arquitetura**: Fragment-based navigation

### Dependências

- AndroidX AppCompat
- Material Design Components
- ConstraintLayout
- CardView
- JUnit (testes)

## Estrutura do Projeto

```
app/src/main/java/com/example/carteira/
├── MainActivity.java                 # Activity principal
├── adapter/
│   └── LancamentoAdapter.java       # Adapter para lista de lançamentos
├── dao/
│   ├── CategoriaDAO.java            # Data Access Object para categorias
│   └── LancamentoDAO.java           # Data Access Object para lançamentos
├── model/
│   ├── Categoria.java               # Modelo de dados para categorias
│   └── Lancamento.java              # Modelo de dados para lançamentos
├── sqlite/
│   └── DbHelper.java                # Helper para gerenciamento do banco SQLite
└── util/
    ├── CadastrarLancamentoFragment.java  # Fragment para cadastro
    ├── Constantes.java              # Constantes da aplicação
    ├── LancamentosFragment.java     # Fragment para listagem
    └── RelatorioFragment.java       # Fragment para relatórios
```

## Configuração do Ambiente

### Pré-requisitos

- Android Studio (versão mais recente)
- JDK 11 ou superior
- Android SDK (API 24+)
- Dispositivo Android ou emulador

### Instalação

1. Clone o repositório:
```bash
git clone [URL_DO_REPOSITORIO]
```

2. Abra o projeto no Android Studio

3. Sincronize as dependências do Gradle

4. Execute o aplicativo em um dispositivo ou emulador

## Como Usar

### Navegação

O aplicativo possui três seções principais acessíveis através da barra de navegação:

1. **Lançamentos**: Visualiza todos os lançamentos cadastrados
2. **Cadastrar**: Adiciona novos lançamentos financeiros
3. **Relatório**: Acessa relatórios e análises financeiras

### Visualização de Dados

- Os lançamentos são exibidos em formato de lista
- Valores de saída aparecem com sinal negativo
- Data formatada no padrão DD/MM/AAAA
- Valores formatados em Real (R$)

## Banco de Dados

### Estrutura das Tabelas

**Tabela: lancamentos**
- id (INTEGER PRIMARY KEY)
- categoria (TEXT)
- descricao (TEXT)
- tipo (TEXT)
- valor (REAL)
- dia (INTEGER)
- mes (INTEGER)
- ano (INTEGER)

**Tabela: categorias**
- id (INTEGER PRIMARY KEY)
- nome (TEXT)


