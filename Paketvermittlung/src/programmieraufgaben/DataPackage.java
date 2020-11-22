package programmieraufgaben;

/**
 * Hier sollen die Nutzereingaben sowie die Resultate gespeichert werden.
 * Die Struktur der Klasse und die Variablen können frei gewählt werden.
 */
public class DataPackage {
    //maximale Datenteil-Länge
    private int dataPackageLength;
    private int version;
    private String sender;
    private String empfaenger;
    private String nachricht;
    private int packetLaufNummer;

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
    public int getDataPackageLength() {
        return dataPackageLength;
    }

    /**
     * Setzt die maximale Datenteil-Länge
     * @param dataPackageLength
     */
    public void setDataPackageLength(int dataPackageLength) {
        this.dataPackageLength = dataPackageLength;
    }
    public void setVersion (int version)
    {
        this.version = version;
    }
    public void setSender (String sender) { this.sender = sender;}
    public void setEmpfaenger (String empfaenger) { this.empfaenger = empfaenger;}
    public void setNachricht (String nachricht) {this.nachricht = nachricht;}
    public int getVersion() {return version;}
    public String getSender() {return sender;}
    public String getEmpfaenger() {return empfaenger;}
    public String getNachricht() {return nachricht;}
    public void setPacketLaufNummer (int packetLaufNummer) {this.packetLaufNummer= packetLaufNummer;}
    public int getPacketLaufNummer() { return packetLaufNummer;}
}
