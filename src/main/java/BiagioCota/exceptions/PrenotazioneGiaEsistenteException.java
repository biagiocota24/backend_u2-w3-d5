package BiagioCota.exceptions;

public class PrenotazioneGiaEsistenteException extends RuntimeException {
    public PrenotazioneGiaEsistenteException() {
        super("Hai già una prenotazione per questo evento");
    }
}
