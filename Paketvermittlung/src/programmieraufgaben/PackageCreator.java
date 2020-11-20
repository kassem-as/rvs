package programmieraufgaben;

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
        System.out.print("Version : ");
        dataPackage.setVersion(input.nextInt());
        if(dataPackage.getVersion()!=4 && dataPackage.getVersion()!=6)
        {
            System.out.println("Version ungueltig. Bitte waehlen Sie entweder Version 4 oder Version 6.");
            return null;
        }

        System.out.print("Absender : ");
        dataPackage.setSender(input.next());

        if(dataPackage.getSender() == null) {
            System.out.println("Bitte geben sie eine richtige ipv4");
            return null;
        }

        String [] ipSender;
        if(dataPackage.getVersion()==4) {
            ipSender = dataPackage.getSender().split("\\.");


            if(ipSender.length < 4|| ipSender.length >4) {
                System.out.println("Bitte geben sie eine richtige format des IPv4");
                return null;
            }
        }else {
            ipSender = dataPackage.getSender().split("\\.");
            if(ipSender.length < 6 || ipSender.length >6 ) {
                System.out.println("Bitte geben sie eine richtige format des IPv6");
                return null;
            }
        }

        System.out.print("Empfaenger : ");
        dataPackage.setEmpfaenger(input.next());

        if(dataPackage.getSender() == null) {
            System.out.println("Bitte geben sie eine richtige ipv4");
            return null;
        }

        String [] ipReceiver;
        if(dataPackage.getVersion()==4) {
            ipReceiver = dataPackage.getEmpfaenger().split("\\.");


            if(ipReceiver.length < 4|| ipReceiver.length >4) {
                System.out.println("Bitte geben sie eine richtige format des IPv4");
                return null;
            }
        }else {
            ipReceiver = dataPackage.getEmpfaenger().split("\\.");
            if(ipReceiver.length < 6 || ipReceiver.length >6 ) {
                System.out.println("Bitte geben sie eine richtige format des IPv6");
                return null;
            }
        }

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
                sanad = "\n";
            }

            dataPackage.setNachricht(dataPackage.getNachricht() + sanad + "\n" + " ");


        }
        dataPackage.setNachricht(dataPackage.getNachricht().replaceAll("^[\n\r]", "").replaceAll("^[\n\r]", ""));
        dataPackage.setNachricht(dataPackage.getNachricht().trim());
        dataPackage.setNachricht(dataPackage.getNachricht().replaceAll("[\n\r]", "\\\\n"));

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
    public List<DataPackage> splitPackage(DataPackage dataPackage) {
        List<DataPackage> dataPackages = new LinkedList<>();

        return dataPackages;
    }

    /**
     * Diese Methode gibt den Inhalt der empfangenen Pakete in der Komandozeile aus
     *
     * @param dataPackages Hier wird die Liste uebergeben, deren Elemente in die Kommandozeile ausgegeben werden sollen
     */
    public void printOutPackage(List<DataPackage> dataPackages) {

    }
}