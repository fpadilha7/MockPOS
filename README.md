# MockPos - Sistema POS Simulado para Android

## 📱 Visão Geral

O **MockPos** é um aplicativo Android que simula um sistema POS (Point of Sale) para demonstração e desenvolvimento. O projeto implementa um fluxo completo de pagamento com interface moderna usando Jetpack Compose.

## 🏗️ Arquitetura do Projeto

### **Padrão Arquitetural: MVVM + Clean Architecture**

O projeto segue o padrão **Model-View-ViewModel (MVVM)** combinado com princípios de **Clean Architecture**, organizando o código em camadas bem definidas:

```
┌─────────────────────────────────────────────────────────────┐
│                    Presentation Layer                       │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐ │
│  │   MainActivity  │  │ PaymentsScreen  │  │   ViewModel │ │
│  └─────────────────┘  └─────────────────┘  └─────────────┘ │
└─────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────┐
│                     Domain Layer                            │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │                Use Cases                                │ │
│  │  ┌─────────────────────────────────────────────────┐   │ │
│  │  │           ProcessPaymentUseCase                 │   │ │
│  │  └─────────────────────────────────────────────────┘   │ │
│  └─────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────┐
│                    Data Layer                               │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │                Repositories                             │ │
│  │  ┌─────────────────────────────────────────────────┐   │ │
│  │  │           (Futuras implementações)              │   │ │
│  │  └─────────────────────────────────────────────────┘   │ │
│  └─────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

### **Estrutura de Pacotes**

```
com.fpadilha.mockpos/
├── MainActivity.kt                    # Ponto de entrada da aplicação
├── ui/
│   ├── theme/                        # Sistema de temas e estilos
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── payments/                     # Módulo de pagamentos
│       ├── PaymentsScreen.kt         # Interface do usuário
│       └── PaymentsViewModel.kt      # Lógica de negócio da UI
└── domain/
    └── usecase/                      # Casos de uso da aplicação
        └── ProcessPaymentUseCase.kt  # Lógica de processamento de pagamentos
```

## 🎯 Decisões Arquiteturais

### **1. Jetpack Compose como UI Framework**

**Decisão**: Utilizar Jetpack Compose ao invés de Views tradicionais.

**Motivos**:
- **Declarativo**: Interface definida de forma mais intuitiva e legível
- **Moderno**: Framework oficial do Google para desenvolvimento Android
- **Performance**: Melhor renderização e menos overhead
- **Manutenibilidade**: Código mais limpo e fácil de manter
- **Reatividade**: Integração nativa com StateFlow e coroutines

### **2. MVVM + StateFlow**

**Decisão**: Implementar MVVM com StateFlow para gerenciamento de estado.

**Motivos**:
- **Separação de Responsabilidades**: ViewModel gerencia estado, View apenas exibe
- **Reatividade**: StateFlow notifica automaticamente mudanças na UI
- **Lifecycle Awareness**: Integração nativa com o ciclo de vida do Android
- **Testabilidade**: ViewModel pode ser testado independentemente da UI

### **3. Use Cases (Clean Architecture)**

**Decisão**: Implementar casos de uso para lógica de negócio.

**Motivos**:
- **Single Responsibility**: Cada use case tem uma responsabilidade específica
- **Reutilização**: Lógica pode ser reutilizada em diferentes partes da aplicação
- **Testabilidade**: Fácil de testar isoladamente
- **Escalabilidade**: Facilita adição de novas funcionalidades

### **4. Sealed Classes para Resultados**

**Decisão**: Usar sealed classes para representar resultados de operações.

**Motivos**:
- **Type Safety**: Compilador garante que todos os casos são tratados
- **Pattern Matching**: Uso eficiente de `when` expressions
- **Extensibilidade**: Fácil adicionar novos tipos de resultado
- **Clareza**: Código mais expressivo e legível

## 🔧 Tecnologias e Dependências

### **Core Android**
- **Kotlin**: Linguagem principal do projeto
- **Android SDK**: Versão mínima 24 (Android 7.0), alvo 36 (Android 14)
- **Java 11**: Versão de compilação

### **Jetpack Compose**
- **Compose BOM**: 2024.09.00 para gerenciamento de versões
- **Material 3**: Design system moderno
- **Lifecycle Compose**: Integração com ciclo de vida
- **ViewModel Compose**: Integração ViewModel + Compose

### **Arquitetura**
- **Lifecycle**: Gerenciamento de ciclo de vida
- **Coroutines**: Programação assíncrona
- **StateFlow**: Gerenciamento reativo de estado

## 📱 Funcionalidades Implementadas

### **Tela de Pagamentos**
- ✅ Campo para valor da transação
- ✅ Seleção de método de pagamento (Crédito/Débito)
- ✅ Validação de campos obrigatórios
- ✅ Estado de processamento com loading
- ✅ Simulação de processamento (2-3 segundos)
- ✅ Resultado aleatório (80% aprovada, 20% recusada)
- ✅ Dialog de resultado com feedback visual
- ✅ Botão para iniciar nova venda

### **Estados da UI**
- **Idle**: Tela pronta para entrada de dados
- **Loading**: Processando pagamento (campos desabilitados)
- **Success**: Pagamento processado com resultado
- **Error**: Tratamento de erros com feedback

## 🚀 Como Executar

### **Pré-requisitos**
- Android Studio Hedgehog ou superior
- JDK 11
- Android SDK 36
- Dispositivo/emulador Android 7.0+

### **Passos**
1. Clone o repositório
2. Abra o projeto no Android Studio
3. Sincronize as dependências Gradle
4. Execute o aplicativo

## 🎨 Build Variants

O projeto possui **3 build variants** configurados, cada um com seu próprio esquema de cores e branding para demonstração de diferentes temas:

### **Variants Disponíveis**

#### **1. RED BANK** 🔴
- **Cores**: Esquema baseado em vermelho
- **Application ID**: `com.fpadilha.mockpos.red`
- **Nome da App**: "RED BANK"
- **Cores Primárias**: 
  - Light: `#E60000` (vermelho forte)
  - Dark: `#FF6666` (vermelho claro intenso)

#### **2. PURPLE BANK** 🟣
- **Cores**: Esquema baseado em roxo
- **Application ID**: `com.fpadilha.mockpos.purple`
- **Nome da App**: "PURPLE BANK"
- **Cores Primárias**:
  - Light: `#8A2BE2` (roxo forte)
  - Dark: `#E066FF` (roxo claro intenso)

#### **3. ORANGE BANK** 🟠
- **Cores**: Esquema baseado em laranja
- **Application ID**: `com.fpadilha.mockpos.orange`
- **Nome da App**: "ORANGE BANK"
- **Cores Primárias**:
  - Light: `#FF6600` (laranja forte)
  - Dark: `#FFB366` (laranja claro intenso)

### **Como Usar as Variants**

#### **No Android Studio:**
1. Abra o projeto
2. No painel "Build Variants" (View → Tool Windows → Build Variants)
3. Selecione o variant desejado
4. Faça o build e instale no dispositivo

#### **Via Gradle:**
```bash
# Build específico
./gradlew assembleRedDebug      # RED BANK
./gradlew assemblePurpleDebug   # PURPLE BANK
./gradlew assembleOrangeDebug   # ORANGE BANK

# Build todos os variants
./gradlew assembleDebug

# Instalar específico
./gradlew installRedDebug       # RED BANK
./gradlew installPurpleDebug    # PURPLE BANK
./gradlew installOrangeDebug    # ORANGE BANK
```

### **Características Técnicas**
- ✅ **Instalação Simultânea**: Cada variant pode ser instalado no mesmo dispositivo
- ✅ **Tema Unificado**: Um único sistema de tema detecta automaticamente o flavor
- ✅ **Cores Centralizadas**: Todas as cores definidas em um único local
- ✅ **BuildConfig**: Constantes específicas geradas automaticamente para cada flavor
- ✅ **Sem Conflitos**: Resolução automática de cores baseada no flavor ativo

## 🧪 Testes

### **Estrutura de Testes**
```
src/
├── test/                    # Testes unitários
│   └── java/
│       └── com/fpadilha/mockpos/
│           ├── domain/usecase/
│           │   └── ProcessPaymentUseCaseTest.kt
│           └── ui/payments/
│               └── PaymentsViewModelTest.kt
└── androidTest/             # Testes instrumentados
    └── java/
        └── com/fpadilha/mockpos/
            └── ExampleInstrumentedTest.kt
```

### **Testes Implementados**
- ✅ **Testes Unitários**:
  - `ProcessPaymentUseCaseTest`: Testa a estrutura e criação de resultados de pagamento
  - `PaymentsViewModelTest`: Testa o estado inicial e operações de estado da UI
- ✅ **Testes Instrumentados**: Exemplo básico para testes de UI

### **Cobertura de Testes**
- **Domain Layer**: ✅ ProcessPaymentUseCase (estrutura e tipos)
- **Presentation Layer**: ✅ PaymentsViewModel (estado e operações)
- **UI Layer**: 🔄 Testes básicos de instrumentação

### **Detalhes dos Testes Unitários**

#### **ProcessPaymentUseCaseTest**
- **Teste 1**: Verifica se a instância do UseCase é criada corretamente
- **Teste 2**: Valida a estrutura dos tipos `PaymentResult.Approved` e `PaymentResult.Declined`
- **Foco**: Estrutura de dados e tipos, sem dependências de coroutines

#### **PaymentsViewModelTest**
- **Teste 1**: Verifica valores padrão do estado inicial (`PaymentsUiState`)
- **Teste 2**: Testa reset de estado para nova venda
- **Teste 3**: Valida limpeza de erros do estado
- **Foco**: Operações de estado e comportamento do data class

## 👨‍💻 Autor

**Felipe Padilha** - Desenvolvedor Android

---

*Este projeto foi criado como demonstração de arquitetura moderna para aplicações Android, servindo como base para projetos maiores e como referência para boas práticas de desenvolvimento.*
