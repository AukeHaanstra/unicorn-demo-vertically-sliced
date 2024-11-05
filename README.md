# Unicorn Demo

![Hexagonal Unicorn](img/HexagonalUnicorn.png)

## Initialize project

![Spring Initializr](img/SpringInitializr.png)

### Class diagrams

Simplified class diagram of the unicorn bounded context:

```mermaid
classDiagram
    direction TB
    UnicornController..>GetUnicornService
    GetUnicornService..>UnicornRepository
    UnicornLegController..>SetUnicornLegService
    UnicornLegController..>GetUnicornLegService
    SetUnicornLegService..>UnicornRepository
    GetUnicornLegService..>UnicornRepository

    class UnicornController {
        -GetUnicornService
        +getUnicorn(unicornId)
    }
    class UnicornLegController {
        -GetUnicornLegService
        -SetUnicornLegService
        +getLeg(unicornId, legPosition)
        +putLeg(unicornId, legPosition, legView)
    }
    class GetUnicornService {
        -UnicornRepository
        +getUnicorn(unicornId)
    }
    class SetUnicornLegService {
        -UnicornRepository
        +setLeg(setLegDto)
    }
    class GetUnicornLegService {
        -unicornRepository
        +getLeg(queryLegDto)
    }
    class UnicornRepository {
        +find(unicornId)
        +updateTransactionally(updateLogic)
    }
```

Class diagram showing event messaging between the unicorn and magic bounded contexts:

```mermaid
classDiagram
    direction TB
    UnicornController..>GetUnicornService
    GetUnicornService..>UnicornRepository
    UnicornLegController..>SetUnicornLegService
    UnicornLegController..>GetUnicornLegService
    SetUnicornLegService..>UnicornRepository
    GetUnicornLegService..>UnicornRepository
    SetUnicornLegService-->MagicAbilityActivationService : newLegObtained event
    MagicAbilityActivationService..>MagicAbilityRepository
    SetUnicornLegService-->WalkingUnicorn : newLegObtained event
    MagicAbilityActivationService-->WalkingUnicorn : magicAbilityObtained event

    namespace Unicorn {
        class UnicornController {
            -GetUnicornService
            +getUnicorn(unicornId)
        }
        class UnicornLegController {
            -UnicornLegService
            +getLeg(unicornId, legPosition)
            +putLeg(unicornId, legPosition, legView)
        }
        class GetUnicornService {
            -UnicornRepository
            +getUnicorn(unicornId)
        }
        class SetUnicornLegService {
            -UnicornRepository
            +setLeg(setLegDto)
        }
        class GetUnicornLegService {
            -unicornRepository
            +getLeg(queryLegDto)
        }
        class UnicornRepository {
            +find(unicornId)
            +updateTransactionally(updateLogic)
        }
        class WalkingUnicorn {
            +on(newLegObtained)
            +on(magicAbilityObtained)
            +printUnicorn()
        }
    }
    namespace Magic {
        class MagicAbilityActivationService {
            -magicAbilityRepository
            +activateMagicAbilityFor(newLegObtained)
        }
        class MagicAbilityRepository {
            +find(unicornId)
        }
    }

```