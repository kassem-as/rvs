package programmieraufgaben;

/**
 * Hier sollen die Nutzereingaben sowie die Resultate gespeichert werden.
 * Die Struktur der Klasse und die Variablen können frei gewählt werden.
 */
public class DataPackage {
    //maximale Datenteil-Länge
    private int dataPackageLength;
    private int version;
    //unsere benutzte Attribute.
    private String sender;
    private String receiver;
    private String message;
    private int serialNumber;
    /**
     * Erzeugt ein DataPackage Objekt und speichert beim erzeugen die maximale Datenteil-Länge
     * @param dataPackageLength
     */
    public DataPackage(int dataPackageLength) {
        this.dataPackageLength = dataPackageLength;
    }

    /**
     * Gibt die maximale Datenteil-Länge zurück
     * @return maximale Datenteil-Länge
     */
    public int getDataPackageLength() { return dataPackageLength; }

    /**
     * Setzt die maximale Datenteil-Länge
     * @param dataPackageLength
     */
    public void setDataPackageLength (int dataPackageLength) { this.dataPackageLength = dataPackageLength; }

    // setters und getter um die Werte der Attribute zu verwalten...


    /**
     * Setzt die IP-Adresse des Sender.
     * @param sender
     */
    public void setSender (String sender) { this.sender = sender;}

    /**
     * Gibt die IP-Adresse des Senders zurueck.
     * @return IP-Adresse des Sender.
     */
    public String getSender() {return sender;}

    /**
     * Setzt die IP-Adresse des Empfaengers.
     * @param empfaenger
     */
    public void setReceiver (String empfaenger) { this.receiver = empfaenger;}

    /**
     * Gibt die IP-Adresse der Empfaengers zurueck.
     * @return IP-Adresse des Empfaengers.
     */
    public String getReceiver() {return receiver;}

    /**
     * Setzt die Nachricht.
     * @param message
     */
    public void setMessage(String message) {this.message = message;}

    /**
     * Gibt die Nachricht zurueck.
     * @return Nachricht.
     */
    public String getMessage() {return message;}

    /**
     * Setzt die IP-Version.
     * @param version
     */
    public void setVersion (int version) { this.version = version; }

    /**
     * Gibt die IP-Version zurueck.
     * @return IP-Version
     */
    public int getVersion() {return version;}

    /**
     * Setzt die Paketlaufnummber.
     * @param serialNumber
     */
    public void setSerialNum(int serialNumber) { this.serialNumber = serialNumber;}

    /**
     * Gibt die Paketlaufnummer zurueck.
     * @return Paketlaufnummber.
     */
    public int getSerialNum() { return serialNumber;}

}
