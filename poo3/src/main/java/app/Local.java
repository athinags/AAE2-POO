package app;

import java.io.Serializable;

// Classe abstrata Local
public abstract class Local implements Serializable {
    private String nome;

    public Local(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract String getDescricao();
}
