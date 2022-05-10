//? Importazione dipendenze
import java.io.*;

public class login4java{

    //? Variabili di istanza GLOBALI usate per i vari metodi
    private String inputTastiera;
    private boolean ConfigValue; //? usata dall metodo setup() per contenere il valore letto dai config
    private boolean loopMenu;
    private String lettura;

    //? Variabili di istanza GLOBALI usate per tenere in memoria i config da poi impostare alle variabili di classe
    private static boolean loadEmail;
    private static boolean loadPassword;
    private static boolean loadTelefono;
    private static boolean loadPaese;
    private static boolean loadCitta;
    private static boolean loadInfoBase;
    private static boolean loadInfoAggiuntive;
    private static boolean loadInfo;
    private String configParametro;
    private String configDato;

    //? Variabili di istanza dedicate a contenere il valore
    private String inputPass;
    private String inputEmail;
    private String inputTell;
    private String inputPaese;
    private String inputCitta;

    //? Variabili di istanza dedicate a contenere il settagio dei login di dati
    private boolean email;
    private boolean pass;
    private boolean telefono;
    private boolean paese;
    private boolean citta;
    private boolean infoBase;
    private boolean infoAggiuntive;
    private boolean info;
    
    //? Oggeti di input/output & scrittura/lettura
    private InputStreamReader A;
    private BufferedReader T;
    private BufferedWriter bw;
    private BufferedReader br;

    //? Costruttore cstruttore con inizializzazione degli input/output
    public login4java()throws IOException{
        A = new InputStreamReader(System.in);
        T = new BufferedReader(A);
    }

    //? Gestisce le impostazioni dei parametri e vari database
    public void setup()throws IOException{
        
        //? Crea le cartelle
        File DirConfig = new File("./configs");
        File DirDB = new File("./db");
        DirConfig.mkdir();
        DirDB.mkdir();

        //? Contiene il file dei config
        File configFile = new File("./configs/config.txt");
        if(configFile.exists()){

            //? Metodo che carica i config da file
            loadConfig();
        }else{

            System.out.println("Benvenuto nella configurazione verrano poste delle domande.");
            System.out.println("digita y per dire si o n per dire no");
            System.out.println("--------------------------------------------------------------------");

            //? Ciclo che in caso di errata battitura riesegue la richiesta di abilitare la mail
            do{
                
                System.out.print("1) abilitare il campo email (y/n): ");
                inputTastiera = T.readLine();

                //? Assegna il valore di ritorno dal metodo che controlla che l'input da tastiera sia correto
                inputTastiera = abilitaDisabilita(inputTastiera);

                //? Se correto trasforma il risultato del metodo in true/false boleano
                ConfigValue = Boolean.valueOf(inputTastiera);
                email = ConfigValue;
            }while(inputTastiera.equals("nak")); //? Se ritorna il metodo una risposta di errore riesegue la richiesta

            //? Ciclo che in caso di errata battitura riesegue la richiesta di abilitazione della password
            do{

                System.out.print("2) abilitare il campo password (y/n): ");
                inputTastiera = T.readLine();

                //? Assegna il valore di ritorno del metodo che controlla che l'input da tastiera sia correto
                inputTastiera = abilitaDisabilita(inputTastiera);

                //? Se correto trasforma il risultato del metodo in true/false boleano
                ConfigValue = Boolean.valueOf(inputTastiera);
                pass = ConfigValue;
            }while(inputTastiera.equals("nak")); //? Se ritorna il metodo una risposta di errore riesegue la richiesta
            
            //? Ciclo che in caso di errata battitura riesegue la richiesta di abilitazione del telefono
            do{
                System.out.print("3) abilitare il campo telefono (y/n): ");
                inputTastiera = T.readLine();

                //? Assegna il valore di ritorno del metodo che controlla che l'input da tastiera sia correto
                inputTastiera = abilitaDisabilita(inputTastiera);

                //? Se correto trasforma il risultato del metodo in true/false boleano
                ConfigValue = Boolean.valueOf(inputTastiera);
                telefono = ConfigValue;
            }while(inputTastiera.equals("nak")); //? Se ritorna il metodo una risposta di errore riesegue la richiesta

            //? Ciclo che in caso di errata battitura riesegue la richiesta di abilitazione del paese
            do{

                System.out.print("4) abilitare il campo del paese (y/n): ");
                inputTastiera = T.readLine();

                //? Assegna il valore di ritorno del metodo che controlla che l'input da tastiera sia correto
                inputTastiera = abilitaDisabilita(inputTastiera);

                //? Se correto trasforma il risultato del metodo in true/false boleano
                ConfigValue = Boolean.valueOf(inputTastiera);
                paese = ConfigValue;
            }while(inputTastiera.equals("nak")); //? Se ritorna il metodo una risposta di errore riesegue la richiesta

            //? Ciclo che in caso di errata battitura riesegue la richiesta di abilitazione della città
            do{

                System.out.print("5) abilitare il campo della città (y/n): ");
                inputTastiera = T.readLine();

                //? Assegna il valore di ritorno del metodo che controlla che l'input da tastiera sia correto
                inputTastiera = abilitaDisabilita(inputTastiera);

                //? Se correto trasforma il risultato del metodo in true/false boleano
                ConfigValue = Boolean.valueOf(inputTastiera);
                citta = ConfigValue;
            }while(inputTastiera.equals("nak")); //? Se ritorna il metodo una risposta di errore riesegue la richiesta
            
            //? Ciclo per impostare i vari database
            do{
                System.out.println("\nImpostazione della configurazione");
                System.out.println("1) database con informazioni base (mail e password)");
                System.out.println("2) database con informazioni aggiuntive (telefono, citta...)");
                System.out.println("3) database con tutte le informazioni insieme");
                System.out.println("4) Uscire");
                System.out.println("--------------------------------------------------------------------");

                inputTastiera = T.readLine();
                int opz = Integer.parseInt(inputTastiera);

                switch(opz){

                    //? Case che si occupa di creare un file con i dati di email e password
                    case 1:
                        if(email == true || pass == true){
                            bw = new BufferedWriter(new FileWriter("./db/infoBase.txt", true));
                            infoBase = true;
                        }else{
                            System.out.println("Impossibile creare il database: non sono state abilitate le relative impostazioni");
                        }
                    break;

                    //? Case che si occupa di creare un file con i dati ulteriori alla email e password
                    case 2:
                        if(telefono == true || paese == true || citta == true && infoBase == true){
                            bw = new BufferedWriter(new FileWriter("./db/infoAggountive.txt", true));
                            infoAggiuntive = true;
                        }else{
                            System.out.println("Impossibile creare il database: non sono state abilitate le relative impostazioni");
                        }
                    break;

                    //? Case che si occupa di creare un file con tutti i dati messi assieme
                    case 3:
                        if(infoBase == true || infoAggiuntive ==  true){
                            System.out.println("Non e' possibile creare il database poiche sono gia stati creati in database separati");
                            loopMenu = false;
                        }else{
                            bw = new BufferedWriter(new FileWriter("./db/info.txt", true));
                            info = true;
                        }
                    break;

                    //? Case che si occupa di Uscire dal ciclo
                    case 4:
                        System.out.println("---------------------------------------------------------------------------------------------");
                        System.out.println("Uscita in corso");
                        System.out.println("---------------------------------------------------------------------------------------------");
                        loopMenu = false;
                    break;

                    default:
                        System.out.println("Errore: selezione non riconosciuta");
                    break;
                }  
            }while(loopMenu == true);

            //? Scrittura delle impostazioni su file
            bw = new BufferedWriter(new FileWriter("./configs/config.txt"));
            bw.write("mail:"+ email + "\n");
            bw.write("pass:"+ pass + "\n");
            bw.write("tell:"+ telefono + "\n");
            bw.write("paese:"+ paese + "\n");
            bw.write("citta:"+ citta + "\n");
            bw.write("InfoBase:"+infoBase + "\n");
            bw.write("altre:"+infoAggiuntive + "\n");
            bw.write("tutto:"+info + "\n");
            bw.flush(); 
        }
    }

    //? Gestione della registrazione
    public void singup() throws IOException{

        if(infoBase == true){

            bw = new BufferedWriter(new FileWriter("./db/infoBase.txt", true));

            if(email == true){
                System.out.print("Scrivere la email: ");
                inputEmail = T.readLine();
                bw.write(inputEmail + " ");
            }

            if(pass ==  true){
                System.out.print("Scrivere la password: ");
                inputPass = T.readLine();
                bw.write(inputPass + " ");
            }

            bw.write("\n");
            bw.flush();

            if(infoAggiuntive == true){
                bw = new BufferedWriter(new FileWriter("./db/infoAggiuntive.txt", true));

                if(telefono == true){
                    System.out.print("Scrivere il telefono: ");
                    inputTell = T.readLine();
                    bw.write(inputTell + " ");
                }

                if(paese == true){
                    System.out.print("Scrivere il paese: ");
                    inputPaese = T.readLine();
                    bw.write(inputPaese + " ");
                }

                if(citta == true){
                    System.out.print("Scrivere la citta'");
                    inputCitta = T.readLine();
                    bw.write(inputCitta + " ");
                }

                bw.write("\n");
                bw.flush();
            }

        }else if(info){
            bw = new BufferedWriter(new FileWriter("./db/info.txt", true));
            
            if(email == true){
                System.out.print("Scrivere la email: ");
                inputEmail = T.readLine();
                bw.write(inputEmail + " ");
            }

            if(pass ==  true){
                System.out.print("Scrivere la password: ");
                inputPass = T.readLine();
                bw.write(inputPass + " ");
            }

            if(telefono == true){
                System.out.print("Scrivere il telefono: ");
                inputTell = T.readLine();
                bw.write(inputTell + " ");
            }

            if(paese == true){
                System.out.print("Scrivere il paese: ");
                inputPaese = T.readLine();
                bw.write(inputPaese + " ");
            }

            if(citta == true){
                System.out.print("Scrivere la citta': ");
                inputCitta = T.readLine();
                bw.write(inputCitta + " ");
            }

            bw.write("\n");
            bw.flush();
        }
    }

    //? Questo metodo viene usato nel metodo setup() per controllare che l'inserimento sia correto
    public String abilitaDisabilita(String scelta){

        if(scelta.equals("y") || scelta.equals("Y")){
            return "true";
        }else if(scelta.equals("n") || scelta.equals("N")){
            return "false";
        }else{
            return "nak";
        }
    }

    //? Carica i config nel programma
    public void loadConfig()throws IOException{

        configParametro = "";
        configDato = "";
        br = new BufferedReader(new FileReader("./configs/config.txt"));
        
        //? Legge tutto il file
        while((lettura = br.readLine()) != null){

            //? Cicla per tutta la lunchezza della riga appena letta dal ciclo while
            for(int i = 0;i<lettura.length();i++){

                //? Controlla che il carattere
                if(lettura.charAt(i) == 58 || i == (lettura.length()-1)){

                    if(configDato.equals("mail")){
                        configParametro = configDato;
                        configDato = "";
                    }else if(configDato.equals("pass")){
                        configParametro = configDato;
                        configDato = "";
                    }else if(configDato.equals("tell")){
                        configParametro = configDato;
                        configDato = "";
                    }else if(configDato.equals("paese")){
                        configParametro = configDato;
                        configDato = "";
                    }else if(configDato.equals("citta")){
                        configParametro = configDato;
                        configDato = "";
                    }else if(configDato.equals("InfoBase")){
                        configParametro = configDato;
                        configDato = "";
                    }else if(configDato.equals("altro")){
                        configParametro = configDato;
                        configDato = "";
                    }else if(configDato.equals("tutto")){
                        configParametro = configDato;
                        configDato = "";
                    }else if(configDato.equals("domande")){
                        configParametro = configDato;
                        configDato = "";
                    }else{

                        configDato += (char)lettura.charAt(i);

                        switch(configParametro){

                            //? Case che controllano il valore e lo impostano
                            case "mail" :
                                loadEmail = Boolean.valueOf(configDato); 
                                configDato = "";
                            break;

                            case "pass" :
                                loadPassword = Boolean.valueOf(configDato);
                                configDato = "";
                            break;

                            case "tell" :
                                loadTelefono = Boolean.valueOf(configDato);
                                configDato = "";
                            break;

                            case "paese" :
                                loadPaese = Boolean.valueOf(configDato);
                                configDato = "";
                            break;

                            case "citta" : 
                                loadCitta = Boolean.valueOf(configDato);
                                configDato = "";
                            break;

                            case "InfoBase" :
                                loadInfoBase = Boolean.valueOf(configDato);
                                configDato = "";
                            break;

                            case "altro" :
                                loadInfoAggiuntive = Boolean.valueOf(configDato);
                                configDato = "";
                            break;

                            case "tutto" :
                                loadInfo = Boolean.valueOf(configDato);
                                configDato = "";
                            break;
                        }
                        
                    }
                }else{
                    configDato += (char)lettura.charAt(i);
                }
            }
        }

        //? Imposta i valori
        setConfig(loadEmail, loadPassword,loadTelefono, loadPaese, loadCitta, loadInfoBase, loadInfoAggiuntive, loadInfo);
    }

    //? Metodo che permette di controlare se i dati passati esistano e in quel caso restituire un messagio di ok
    public String sigin(String emailText, String passText)throws IOException{

        //? Contiene il risultato di default che viene restituito al richiamo del metodo
        String check = "notacess";

        //? Contiene la Stringa che deve essere controllata all interno dei file
        String datiUtente = emailText + " "+ passText + " ";

        if(infoBase == true){
            br = new BufferedReader(new FileReader("./db/infoBase.txt"));

            if(pass == true){

                //? Lettura da file
                while((lettura = br.readLine()) != null){

                    if(datiUtente.equals(lettura)){
                        check = "ok";
                    }
                }
            }else{

                datiUtente = emailText + " ";

                //? Lettura da file
                while((lettura = br.readLine()) != null){

                    if(datiUtente.equals(lettura)){
                        check = "ok";
                    }
                }
            }
        }else{

            br = new BufferedReader(new FileReader("./db/info.txt"));

            if(pass == true){

                //? Lettura da file
                while((lettura = br.readLine()) != null){
                    if(datiUtente.contains(lettura)){
                        check = "ok";
                    }
                }
            }else{

                datiUtente = emailText + " ";

                //? Lettura da file
                while((lettura = br.readLine()) != null){

                    if(datiUtente.contains(lettura)){
                        check = "ok";
                    }
                }
            }
        }

        return check;
    }

    //? Imposta i valori del file di config
    public void setConfig(boolean email, boolean pass, boolean telefono, boolean paese, boolean citta, boolean infoBase, boolean infoAggiuntive, boolean info){
        this.email = email;
        this.pass = pass;
        this.telefono = telefono;
        this.paese = paese;
        this.citta = citta;
        this.infoBase = infoBase;
        this.infoAggiuntive = infoAggiuntive;
        this.info = info;
    }
}