package app;

// Classe Renascentismo que herda de Evento
public class Renascentismo extends Evento {
    public Renascentismo(String nome) {
        super(nome);
    }

    @Override
    public String getDetalhes() {
        return "Evento de Renascentismo: " + getNome();
    }
}
