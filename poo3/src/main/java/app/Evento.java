package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Classe abstrata Evento
public abstract class Evento implements Serializable {
    private String nome;
    private int visitantes;
    private Local local;
    private List<Artista> artistas;

    public Evento(String nome) {
        this.nome = nome;
        this.visitantes = 0;
        this.artistas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getVisitantes() {
        return visitantes;
    }

    public void adicionarVisitante() {
        this.visitantes++;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Local getLocal() {
        return local;
    }

    public void adicionarArtista(Artista artista) {
        this.artistas.add(artista);
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public abstract String getDetalhes();
}
