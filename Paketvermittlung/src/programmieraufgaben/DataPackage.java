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

    // setters und getter um die Werte der Attribute zu verwalten...

    public void setDataPackageLength(int dataPackageLength) { this.dataPackageLength = dataPackageLength; }

    public void setSender (String sender) { this.sender = sender;}
    public String getSender() {return sender;}

    public void setReceiver (String empfaenger) { this.receiver = empfaenger;}
    public String getReceiver() {return receiver;}

    public void setMessage(String message) {this.message = message;}
    public String getMessage() {return message;}

    public void setVersion (int version) { this.version = version; }
    public int getVersion() {return version;}

    public void setSerialNum(int serialNumber) { this.serialNumber = serialNumber;}
    public int getSerialNum() { return serialNumber;}

}
