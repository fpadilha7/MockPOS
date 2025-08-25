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

## 🧪 Testes

### **Estrutura de Testes**
```
src/
├── test/                    # Testes unitários
│   └── java/
│       └── com/fpadilha/mockpos/
│           └── ExampleUnitTest.kt
└── androidTest/             # Testes instrumentados
    └── java/
        └── com/fpadilha/mockpos/
            └── ExampleInstrumentedTest.kt
```

### **Testes Implementados**
- ✅ Testes unitários básicos
- ✅ Testes instrumentados básicos

### **Próximos Passos para Testes**
- [ ] Testes unitários para ViewModel
- [ ] Testes unitários para Use Cases
- [ ] Testes de UI para Compose
- [ ] Testes de integração

## 🔮 Roadmap e Melhorias Futuras

### **Curto Prazo**
- [ ] Implementar persistência local com Room
- [ ] Adicionar validação de formato de valor
- [ ] Implementar histórico de transações
- [ ] Adicionar animações e transições

### **Médio Prazo**
- [ ] Integração com APIs de pagamento reais
- [ ] Sistema de usuários e autenticação
- [ ] Relatórios e analytics
- [ ] Múltiplas moedas

### **Longo Prazo**
- [ ] Versão web com Compose Multiplatform
- [ ] Integração com sistemas ERP
- [ ] Machine Learning para detecção de fraudes
- [ ] Suporte offline completo

## 📚 Padrões e Boas Práticas

### **Código**
- **Kotlin First**: Uso de recursos nativos do Kotlin
- **Immutabilidade**: Preferência por dados imutáveis
- **Null Safety**: Uso de tipos não-nulos quando possível
- **Extension Functions**: Para funcionalidades específicas

### **UI/UX**
- **Material Design 3**: Seguindo guidelines oficiais
- **Responsividade**: Interface adaptável a diferentes tamanhos
- **Acessibilidade**: Suporte a leitores de tela
- **Feedback Visual**: Estados claros e informativos

### **Performance**
- **Lazy Loading**: Composables carregados sob demanda
- **State Hoisting**: Estado gerenciado no nível apropriado
- **Recomposition**: Minimização de recomposições desnecessárias
- **Memory Management**: Uso adequado de remember e derivedStateOf

## 🤝 Contribuição

### **Padrões de Commit**
- `feat:` Nova funcionalidade
- `fix:` Correção de bug
- `docs:` Documentação
- `style:` Formatação de código
- `refactor:` Refatoração
- `test:` Adição de testes

### **Code Review**
- Todas as mudanças devem passar por review
- Testes devem ser incluídos para novas funcionalidades
- Documentação deve ser atualizada quando necessário

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Felipe Padilha** - Desenvolvedor Android

---

*Este projeto foi criado como demonstração de arquitetura moderna para aplicações Android, servindo como base para projetos maiores e como referência para boas práticas de desenvolvimento.*
