**InternshipsManagementPlatform**

Applicazione per la gestione dei tirocini associati alle tesi di laurea. 
La piattaforma permette agli studenti di richiedere un tirocinio esterno o interno, controllare lo stato della richiesta, concludere il tirocinio e caricare i documenti necessari.
La commissione tesi può scaricare a inviare la valutazione della richiesta di tirocinio, controllare lo stato del tirocinio, verbalizzare il tirocinio.
Il relatore accademico può valutare il progetto formativo, confermare la disponibilità, confermare la validità del tirocinio.
L'azienda può registrare il profilo, caricare il progetto formativo e segnalare la conclusione del tirocinio.

Per l’implementazione dell’applicazione è stato scelto il design pattern RSC, per garantire una separazione dei compiti efficiente tra i componenti. Il livello Controller espone le funzioni dell’applicazione in modo che possano essere utilizzate da entità esterne, idealmente tramite una UI. Il livello Repository, è responsabile dell'accesso ai dati e dell'interazione con il livello di persistenza, fornisce metodi per eseguire operazioni CRUD (Create, Read, Update, Delete) sui dati dell'entità associata. Mentre il livello Service è responsabile di implementare la logica dell’applicazione, garantisce un’interazione corretta tra gli altri due livelli, accede ai servizi del livello repository, utilizzando le entità che modellano i dati persistenti, ugualmente fornisce dati al controller modellati tramite le entità

I dati vengono memorizzati su un database relazionale. Per ogni entità dell’applicazione viene generata una tabella.Per semplificare l’interazione con il database relazionale è stato utilizzato il framework java Hibernate, incluso tramite la dipendenza  al modulo spring-boot-starter-data-jpa del framework Spring java.
Viene importato anche la libreria Lombok per semplificare la scrittura del codice(costruttori, setter, getter..)
L’API Rest(HTTP) è implementata tramite la RestController di Spring

Il DBMS utilizzato è MariaDB

