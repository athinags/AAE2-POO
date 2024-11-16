package app;

// Classe Expressionismo que herda de Evento
public class Expressionismo extends Evento {
    public Expressionismo(String nome) {
        super(nome);
    }

    @Override
    public String getDetalhes() {
        return "Evento de Expressionismo: " + getNome();
    }
}
