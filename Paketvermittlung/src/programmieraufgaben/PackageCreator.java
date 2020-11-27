package programmieraufgaben;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class PackageCreator {
    Scanner input = new Scanner(System.in);


    /**
     * Hier sollen die Kommandozeilen-Abfragen abgefragt und die Antworten
     * gespeichert werden
     * Es sollte auf Fehlerbehandlung geachtet werden (falsche Eingaben, ...)
     *
     * @param dataPackage Hier wird das Objekt uebergeben in das die abgefragten Werte gespeichert werden sollen
     * @return Gibt das als Parameter uebergebene Objekt, dass mit den abgefragten Werten befuellt wurde zurueck
     */
    public DataPackage fillParameters(DataPackage dataPackage) {

       System.out.print("Version : "); dataPackage.setVersion(input.nextInt());
        if(dataPackage.getVersion()!=4 && dataPackage.getVersion()!=6) { System.out.
                println("Version ungültig. Bitte wählen Sie entweder Version 4 oder Version 6."
                ); return null; }

        System.out.print("Absender : "); dataPackage.setSender(input.next());

        if(dataPackage.getSender() == null) {
            System.out.println("Bitte geben sie eine richtige ipv4"); return null; }

        String [] ipSender; if(dataPackage.getVersion()==4) { ipSender =
                dataPackage.getSender().split("\\.");


            if(ipSender.length < 4|| ipSender.length >4) {
                System.out.println("Bitte geben sie eine richtige format des IPv4"); return
                        null; } }else { ipSender = dataPackage.getSender().split("\\.");
            if(ipSender.length < 6 || ipSender.length >6 ) {
                System.out.println("Bitte geben sie eine richtige format des IPv6"); return
                        null; } }

        System.out.print("Empfänger : "); dataPackage.setReceiver(input.next());

        if(dataPackage.getSender() == null) {
            System.out.println("Bitte geben sie eine richtige ipv4"); return null; }

        String [] ipReceiver; if(dataPackage.getVersion()==4) { ipReceiver =
                dataPackage.getReceiver().split("\\.");


            if(ipReceiver.length < 4|| ipReceiver.length >4) {
                System.out.println("Bitte geben sie eine richtige format des IPv4"); return
                        null; } }else { ipReceiver = dataPackage.getReceiver().split("\\.");
            if(ipReceiver.length < 6 || ipReceiver.length >6 ) {
                System.out.println("Bitte geben sie eine richtige format des IPv6"); return
                        null; } }

        String sanad = "";
        String str = "";
        dataPackage.setNachricht(str);
        System.out.println("Nachricht : ");

        while(input.hasNextLine()) {
            sanad = input.nextLine();
            if(sanad.equals(".")) {
                break;
            }

            if(sanad.isEmpty()){

                dataPackage.setNachricht((dataPackage.getNachricht() + "\n"));
            }
            else {
                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(sanad);
                boolean b = m.find();
                if(b){
                    sanad = sanad.replaceAll("\\W"," $0 ");
                }

                dataPackage.setNachricht(dataPackage.getNachricht() + " " + sanad + " " +"\n" + " ");

            }
        }
        dataPackage.setNachricht(dataPackage.getNachricht().replaceAll("^[\n\r]", "").replaceAll("^[\n\r]", ""));
        dataPackage.setNachricht(dataPackage.getNachricht().trim());
        dataPackage.setNachricht(dataPackage.getNachricht().replaceAll("[\n\r]", "\\\\n"));
        dataPackage.setNachricht(dataPackage.getNachricht().trim());
        System.out.println(dataPackage.getNachricht());
        return dataPackage;
    }


    /**
     * Aus dem als Parameter uebergebenen Paket sollen die Informationen
     * ausgelesen und in einzelne Datenpakete aufgeteilt werden
     *
     * @param dataPackage Hier wird das Objekt uebergeben in das das Resultat gespeichert werden soll
     * @return Gibt das als Parameter uebergebene Objekt mit den aufgeteiltet Datenpaketen zurueck
     */

    public int checkLength (String input, String extension, DataPackage dataPackage) {
        if(extension.equals("\\n") || input.endsWith("\\n")) {
            if((input.length() + extension.length())<=dataPackage.getDataPackageLength()) {
                return 1; //fall /n und geht
            }else {
                return 0; //nicht geht
            }
        }else {
            if((input.valueOf(input.charAt(input.length()-1)).matches("[^a-zA-Z0-9]") || extension.valueOf(extension.charAt(0)).
                    matches("[^a-zA-Z0-9]")) &&
                    input.length() + extension.length() <= dataPackage.getDataPackageLength()) {
                return 3;
            }else if ((input.length() + extension.length() + 1)<=dataPackage.getDataPackageLength()){
                return 2;
            } else {
                return 0; // nicht geht
            }
        }
    }


    public List<DataPackage> splitPackage(DataPackage dataPackage) {
        List<DataPackage> dataPackages = new LinkedList<>();
        String [] result = dataPackage.getNachricht().split(" +");
        for(int j = 0;j<result.length;j++){
            System.out.println(result[j]);
        }
        for(int i=0; i<result.length; i++) {
            String input=result[i];
            for(int j=i+1; j<result.length; j++) {
                String extension = result[j];
                if(checkLength(input, extension, dataPackage) == 1) {
                    input = input + extension;
                    if(j==(result.length-1)) {
                        i = j;
                    }
                }else if(checkLength(input, extension, dataPackage) == 2) {
                    input = input + " " + extension;
                    if((j==result.length-1)) {
                        i = j;
                    }
                }else if(checkLength(input, extension, dataPackage) == 3){
                    input = input + extension;
                    if(j==result.length-1){
                        i=j;
                    }
                } else {
                    i=j-1;
                    break;
                }
            }
            DataPackage newPackage= new DataPackage(input.length());
            newPackage.setNachricht(input);
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
        System.out.println();
        System.out.println("Es sind " + dataPackages.size() + " Datenpakete notwendig.");
        System.out.println();
        for(int i = 0; i<dataPackages.size(); i++){
            dataPackages.get(i).setPacketLaufNummer(i+1);
            System.out.println("Version: " +dataPackages.get(i).getVersion());
            System.out.println("Absender: " +dataPackages.get(i).getSender());
            System.out.println("Empfänger: " +dataPackages.get(i).getReceiver());
            System.out.println("Paketlaufnummer: " +dataPackages.get(i).getPacketLaufNummer());
            System.out.println("Datenteil-Länge: " +dataPackages.get(i).getDataPackageLength());
            System.out.println("Datenteil: " +dataPackages.get(i).getNachricht());
            System.out.println();

        }

    }
}
