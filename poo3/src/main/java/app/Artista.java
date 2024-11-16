package app;

import java.io.Serializable;

// Classe Artista que implementa a interface Influencia
public class Artista implements Influencia, Serializable {
    private String nome;

    public Artista(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String getInfluencias() {
        return "Influências do artista " + nome;
    }
}
