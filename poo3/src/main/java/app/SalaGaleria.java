package app;

// Classe SalaGaleria que herda de Local
public class SalaGaleria extends Local {
    public SalaGaleria(String nome) {
        super(nome);
    }

    @Override
    public String getDescricao() {
        return "Sala da Galeria: " + getNome();
    }
}
