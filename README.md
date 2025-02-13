# TaskFlow Manager

TaskFlow Manager è un'applicazione per la gestione di task e progetti, progettata per aiutare gli utenti a creare, assegnare e tracciare lo stato dei task all'interno di progetti. L'applicazione è composta da un backend modulare basato su microservizi che comunicano tramite Kafka e un frontend realizzato con React.

---

## Architettura

L'applicazione segue un'**architettura esagonale** (Ports and Adapters), che separa chiaramente la logica di business dall'infrastruttura. I servizi backend sono modulari e indipendenti, con una chiara divisione tra i livelli di applicazione, dominio e infrastruttura.

### Diagramma dell'Architettura
Frontend (React) → API REST → Task Service → Kafka → Project Service / Notification Service

---

## Stack Tecnologico

### Backend
- **Framework**: Quarkus
- **Linguaggio**: Kotlin
- **Libreria reattiva**: Mutiny
- **Message Broker**: Kafka
- **Database**: MongoDB

### Frontend
- **Libreria**: React

---

## Servizi Backend

1. **Task Service**
   - Gestisce la creazione, modifica e cancellazione dei task.
   - Espone API REST per il frontend.
   - Pubblica eventi su Kafka quando un task viene creato o modificato.

2. **Project Service**
   - Gestisce la creazione e la gestione dei progetti.
   - Si sottoscrive agli eventi di Kafka emessi dal Task Service per aggiornare lo stato dei progetti in base ai task.

3. **Notification Service**
   - Si sottoscrive agli eventi di Kafka.
   - Invia notifiche (email, push notification) quando un task viene assegnato o completato.

---

## Frontend

Il frontend è realizzato con React e comunica con il backend tramite API REST. Le pagine principali includono:

- **Dashboard**: Visualizza una panoramica dei progetti e dei task assegnati.
- **Project Detail**: Mostra i dettagli di un progetto, inclusi i task associati.
- **Task Detail**: Mostra i dettagli di un task specifico, con la possibilità di modificarlo o assegnarlo a un altro utente.

---

## Flusso di Comunicazione

1. Un utente crea un task tramite il frontend, che invia una richiesta al Task Service.
2. Il Task Service salva il task su MongoDB e pubblica un evento su Kafka.
3. Il Project Service e il Notification Service si sottoscrivono all'evento e aggiornano lo stato del progetto e inviano notifiche di conseguenza.

---

## Piano di Lavoro

| Settimane | Attività |
|-----------|----------|
| 1-2       | Configurazione dell'ambiente di sviluppo, creazione del progetto Quarkus e configurazione di MongoDB. |
| 3-4       | Implementazione del Task Service con API REST e integrazione con Kafka. |
| 5-6       | Implementazione del Project Service con sottoscrizione agli eventi Kafka. |
| 7-8       | Implementazione del Notification Service. |
| 9-10      | Sviluppo del frontend con React, integrazione con le API REST. |
| 11-12     | Testing, ottimizzazione e documentazione del codice. |

---

## Risultato Finale

Alla fine dei 3 mesi, l'applicazione sarà completa e funzionante, dimostrando l'uso di tecnologie moderne come Quarkus, Kotlin, Mutiny, Kafka, MongoDB e React. Questo progetto sarà un'ottima aggiunta al tuo curriculum e ti fornirà esperienza pratica con strumenti richiesti nel settore.

---

## Estensioni Future (Opzionali)

- Aggiungere autenticazione e autorizzazione.
- Implementare un sistema di logging centralizzato.
- Aggiungere un servizio di reporting che genera report sui progetti e task completati.

---

## Come Avviare il Progetto

### Prerequisiti
- Java 17+
- Docker (per Kafka e MongoDB)
- Node.js (per il frontend React)

### Passi per l'Avvio

1. Clona il repository:
   ```bash
   git clone https://github.com/tuo-repo/taskflow-manager.git
   cd taskflow-manager

2. Avvia Kafka e MongoDB tramite Docker:
    bash
    Copy
    docker-compose up -d

3. Avvia i servizi backend:
    bash
    Copy
    ./mvnw quarkus:dev

4. Avvia il frontend:
    bash
    Copy
    cd frontend
    npm install
    npm start
    
5. Apri il browser e visita http://localhost:3000 per accedere all'applicazione.