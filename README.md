# BorderGuessr

BorderGuessr è una applicazione progettata per favorire l'apprendimento della geografia in maniera divertente. 

Trattasi di un gioco innovativo e coinvolgente, basato sulla geografia, progettato per mettere alla prova le conoscenze degli utenti in modo divertente e interattivo. In questo gioco l’utente e il computer si
sfidano a turno nel riconoscere e identificare i confini degli Stati di tutto il mondo.
L’obiettivo è semplice ma intrigante: dimostrare di essere più abili del computer nel decifrare le linee che separano le Nazioni. Ogni partita di BorderGuessr combina elementi di apprendimento e competizione,
offrendo una sfida avvincente, sia per gli appassionati di geografia che per chi vuole semplicemente migliorare le proprie conoscenze sui confini mondiali.

## Autori
### Il BorderGuessr Team

- Sebastiano Caliendo - [Sebastiano-Caliendo](https://github.com/Sebastiano-Caliendo)
- Niccolò Pio Tancredi - [neacc](https://github.com/neacc)
- Gianfranco Vitiello - [gianf03](https://github.com/gianf03)

## Installazione

### Prerequisiti

Per eseguire il progetto, assicurarsi di avere installato i seguenti strumenti:
- **IntelliJ IDEA** 2024.3.1

### Step by step

1. **Scaricare il progetto**:  
   Scaricare il file `.zip` del progetto dalla repository GitHub e aprirlo tramite IntelliJ IDEA.

2. **Avvio del progetto**:
   Recarsi sul file BorderGuessrApplication, situato nel package GUI, ed avviare il progetto.

**NOTA**:
Nel package data sono presenti molteplici file JSON:
- **allCountriesVeryShort.json**: contiene solo 6 paesi, utile per capire come funzionano i vari algoritmi senza perdere la testa dietro l'albero di gioco;
- **allCountriesShort.json**: contiene i 26 membri UE, solo confini terrestri, usato durante lo sviluppo per fare debugging;
- **allCountries43.json**: contiente 43 paesi dell'Europa continentale, solo confini terrestri. Utile per testare il funzionamento del Minimax se si vogliono risposte in un tempo accettabile;
- **allCountries82.json**: contiene 82 paesi sparsi per il mondo, solo confini terrestri. Utile per testare il funzionamento della potatura alfabeta se si vogliono risposte in un tempo accettabile;
- **allCountries.json**: contiene 157 paesi, ovvero tutti quelli che possiedono almeno un confine terrestre. Impensabile usare su di esso Minimax standard e potatura alfabeta;
- **allCountriesFull.json**: contiene tutti i 199 paesi del mondo, sia confini terrestri che marittimi. Impensabile usare su di esso Minimax standard e potatura alfabeta.


## Built With

- **Java** - Linguaggio di programmazione per lo sviluppo back-end.
