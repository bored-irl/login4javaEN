![](./login4java.png)
# Generale

*Questo programma a come obbiettivo di sviluppare un algoritmo <br> per gestire gli accessi ai propri software tramite l’utilizzo di poche <br> righe di codice per implementare un manager di autenticazione*

# Funzioni

- *`setup()`: esegue il setup del applicazione creando i vari settaggi (se viene eseguito e esiste già un file di confiurazione carica il file e le relative impostazioni).*
- *`singup()`: esegue la registrazine del utente secondo i config*
- *`loadConfig()`: carica i config da file (questa operazione viene eseguita da setup) in caso contrario può essere eseguita manualmente richiamando il metodo*
- *`sigin(email, password)`: esegue il login passandoli email, password e ritorna: "notacces"(in caso di login errato o utente inesistente) o "ok" come conferma*

# Installazione e configurazione

- *per prima cosa bisogna scaricare i file e metterli in una cartella nota: si può inserire i propri file del programma nella root della cartella contenente i file*
- *creare l'oggetto nel proprio file con parametri vuoti*
- *fatto richiamare i metodi citati sopra*

<img src="./install.png" width=700>

# In arrivo
- *reset della password*
- *gestione dei permessi*
- *pannello admin*