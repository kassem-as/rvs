package programmieraufgaben;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Hier werden die Benutzer Eingaben abgefragt in einem DataPackage Object gespeichert und manipuliert.
 * Laengere Nachrichten werden in einzelne Datenpakete aufgeteilt.
 * Die einzelnen Datenpakete werden ausgegeben.
 */
public class PackageCreator {
    Scanner input = new Scanner(System.in); //scanner um die Benutzer Eingaben zu bekommen.

    /**
     * Hier sollen die Kommandozeilen-Abfragen abgefragt und die Antworten
     * gespeichert werden
     * Es sollte auf Fehlerbehandlung geachtet werden (falsche Eingaben, ...)
     *
     * @param dataPackage Hier wird das Objekt uebergeben in das die abgefragten Werte gespeichert werden sollen
     * @return Gibt das als Parameter uebergebene Objekt, dass mit den abgefragten Werten befuellt wurde zurueck
     */

    //Regex um die Korrektheit der IP Addresse (IP4) zu überprüfen.
    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

    //Regex um die Korrektheit der IP Addresse (IP6) zu überprüfen.
    private static final String IPV6_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final Pattern IPv6_PATTERN = Pattern.compile(IPV6_REGEX);

    public DataPackage fillParameters(DataPackage dataPackage) {

        System.out.print("Version: ");
        try {
            dataPackage.setVersion(input.nextInt());             //scanner benutzt fuer User Eingabe.
            //pruefen der Version Eingabe.
            if(dataPackage.getVersion()!=4 && dataPackage.getVersion()!=6) {
                System.out.println("Version ungültig. Bitte wählen Sie entweder Version 4 oder Version 6.");
                return null;
            }
        }
        catch (InputMismatchException e){
            System.out.println("Fehler: Bitte nur version 4 oder 6 eingeben.");
            return null;
        }



        System.out.print("Absender: ");
        dataPackage.setSender(input.next()); 				 //scanner benutzt fuer User Eingabe.

        //Pruefen der IP-Addresse der Absender.
        if (dataPackage.getVersion() == 4 &&(!IPv4_PATTERN.matcher(dataPackage.getSender()).matches() || dataPackage.getSender() == null )) {
            System.out.println("Die IP Addresse ist ungültig");
            return null;
        }else if(dataPackage.getVersion() == 6 &&(!IPv6_PATTERN.matcher(dataPackage.getSender()).matches() || dataPackage.getSender() == null )){
            System.out.println("Die IP Addresse ist ungültig");
            return null;
        }

        System.out.print("Empfänger: ");
        dataPackage.setReceiver(input.next()); 				//scanner benutzt fuer User Eingabe.

        //Pruefen der IP-Addresse der Empfaenger.
        if (dataPackage.getVersion() == 4 &&(!IPv4_PATTERN.matcher(dataPackage.getReceiver()).matches() || dataPackage.getReceiver()== null )) {
            System.out.println("Die IP Addresse ist ungültig");
            return null;
        }else if(dataPackage.getVersion() == 6 &&(!IPv6_PATTERN.matcher(dataPackage.getReceiver()).matches() || dataPackage.getReceiver() == null )){
            System.out.println("Die IP Addresse ist ungültig");
            return null;
        }

        String userMessage = "";
        dataPackage.setMessage(userMessage);
        System.out.println("Nachricht : ");

        //Um mehrzeilige Eingaben zu speichern, input.hasNextLine() ist benutzt in eine while Schleife.
        // und um die schleife zu beenden, die mit Zeilenumbruch.Zeilenumbruch abgeschlossen wird.
        while(input.hasNextLine()) {
            userMessage = input.nextLine();
            if(userMessage.equals(".")) {
                break;
            }

            //um new-lines spaeter zu codieren siehe Methode (split Methode).
            if(userMessage.isEmpty()){

                dataPackage.setMessage((dataPackage.getMessage() +" " + "\n"));
            }
            else {
                // um die special-characters zu finden und dann manipulieren.
                //replaceAll methode erzetzt jeden sonder-zeichen mit sich selbst vorangegangen und gefolgt mit einem leeren zeichen.
                Pattern p = Pattern.compile("[^a-z0-9 \\äöüß]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(userMessage);
                boolean b = m.find();
                if(b){
                    userMessage = userMessage.replaceAll("\\W"," $0 ");
                }

                //Message String konstruktion.
                dataPackage.setMessage(dataPackage.getMessage() + " " + userMessage + " " +"\n" + " ");

            }
        }
        //entfaernt die whitespaces(/n, " ") am Zeilenanfang und am Zeilenende mit hilfe von replaceAll methode und trim.
        dataPackage.setMessage(dataPackage.getMessage().replaceAll("^[\n\r]", ""));
        dataPackage.setMessage(dataPackage.getMessage().trim());
        dataPackage.setMessage(dataPackage.getMessage().replaceAll("[\n\r]", "\\\\n")); //ersetzt newlines mit string /n.
        if(dataPackage.getMessage().isEmpty())
        {
            System.out.println("Die Nachricht ist leer, bitte eine Nachricht eingeben.");
            return null;
        }

        return dataPackage;
    }

    /**
     * hier werden verschieden Faelle gecheckt die von methode splitPackage benutzt werden.
     *     fall 1: input oder extension  enthaelt /n und die gesamte laenge von beide ist kleiner als datapackagelength.
     *     fall 2: input oder extension enthaelt keine /n aber sonderzeichen  und  die gesamte laenge von beide + 1 (leerzeichen) ist kleiner als datapackagelength.
     *     fall 3: input oder extension enthaelt kein /n und kein Sonderzeichen und die gesamte laenge von beide + 1 (leerzeichen)  ist kleiner als datapackagelength.
     *     fall 0: keine der oben gennanten faelle ist erfuellt.
     *
     * @param input Hier wird ein Teil der Nachricht, der im Hilfsarray(result) gespeichert ist, uebergeben.
     * @param extension Hier wird ein anderer Teil der Nachricht uebergeben und zum ersten passend hinzugefuegt.
     * @param dataPackage Hier wird das Object uebergeben, der die maximale Datenteil-Laenge enthaelt.
     * @return Gibt den passenden Integer zurueck, der in Mothode splitPackage benutzt wird.
     */
    public int checkLength (String input, String extension, DataPackage dataPackage) {
        if(extension.equals("\\n") || input.endsWith("\\n")) {
            if((input.length() + extension.length())<=dataPackage.getDataPackageLength()) {
                return 1;
            }else {
                return 0;
            }
        }else {
            if((input.valueOf(input.charAt(input.length()-1)).matches("[^a-zA-Z0-9 \\äöüß]") || extension.valueOf(extension.charAt(0)).
                    matches("[^a-zA-Z0-9 \\äöüß]")) && input.length() + extension.length() <= dataPackage.getDataPackageLength()) {
                return 2;
            }else if ((input.length() + extension.length() + 1)<=dataPackage.getDataPackageLength()){
                return 3;
            } else {
                return 0;
            }
        }
    }
    /**
     * Aus dem als Parameter uebergebenen Paket sollen die Informationen
     * ausgelesen und in einzelne Datenpakete aufgeteilt werden
     *
     * @param dataPackage Hier wird das Objekt uebergeben in das das Resultat gespeichert werden soll
     * @return Gibt das als Parameter uebergebene Objekt mit den aufgeteiltet Datenpaketen zurueck
     */
    public List<DataPackage> splitPackage(DataPackage dataPackage) {

        //return null wenn die User Eingaben falsch sind, um die Nullpointer exceptions zu vermeiden, und den Vorgang zu wiederholen.
        if (dataPackage == null){
            return null;
        }
        List<DataPackage> dataPackages = new LinkedList<>();
        //split methode teilt jedes Wort alleine und setzt das Wort als eigenes Element in einem Hilfsarray result(entfernt leere zeichen).
        String [] result = dataPackage.getMessage().split(" +");

        //hier wird gecheckt ob jedes wort kleiner als datapackagelength ist. da keine wort > datapackagelength erlaubt ist.
        for(int i=0; i<result.length; i++) {
            if(result[i].length() > dataPackage.getDataPackageLength()) {
                System.out.println("Die Nachricht kann nicht versendet werden, da sie ein Wort (" + result[i] + ") mit Länge " + result[i].length() + " > " + dataPackage.getDataPackageLength() +  " enthält.");
                return null;
            }
        }

        //hier wird jeder Datenpaket nach die in Methode checkLength() gennanten faellen konstruiert.
        for(int i=0; i<result.length; i++) {
            String input=result[i];
            for(int j=i+1; j<result.length; j++) {
                String extension = result[j];
                int check = checkLength(input, extension, dataPackage);

                if(check == 1 || check ==2) {
                    input = input + extension;
                    if(j==result.length-1){i = j;}

                }else if(check== 3){
                    input = input + " " + extension;
                    if(j==result.length-1){i = j;}

                } else {
                    i=j-1;
                    break;

                }
            }
            //jeden paket wird in eine newPackage objekt mit seinem attribute gesetzt.
            DataPackage newPackage= new DataPackage(input.length());
            newPackage.setMessage(input);
            newPackage.setReceiver(dataPackage.getReceiver());
            newPackage.setSender(dataPackage.getSender());
            newPackage.setVersion(dataPackage.getVersion());
            dataPackages.add(newPackage);
        }
        return dataPackages;
    }



    /**
     * Diese Methode gibt den Inhalt der empfangenen Pakete in der Komandozeile aus
     *
     * @param dataPackages Hier wird die Liste uebergeben, deren Elemente in die Kommandozeile ausgegeben werden sollen
     */
    public void printOutPackage(List<DataPackage> dataPackages) {

        //return null wenn die User Eingaben falsch sind, um die Nullpointer exceptions zu vermeiden, und den Vorgang zu wiederholen.
        if(dataPackages == null){
            return;
        }
        System.out.println();
        System.out.println("Es sind " + dataPackages.size() + " Datenpakete notwendig.");
        System.out.println();
        for(int i = 0; i<dataPackages.size(); i++){
            dataPackages.get(i).setSerialNum(i+1);
            System.out.println("Version: " +dataPackages.get(i).getVersion());
            System.out.println("Absender: " +dataPackages.get(i).getSender());
            System.out.println("Empfänger: " +dataPackages.get(i).getReceiver());
            System.out.println("Paketlaufnummer: " +dataPackages.get(i).getSerialNum());
            System.out.println("Datenteil-Länge: " +dataPackages.get(i).getDataPackageLength());
            System.out.println("Datenteil: " +dataPackages.get(i).getMessage());
            System.out.println();

        }

    }
}
