package com.example.entregabletres.model.Pojo;

public class ArtistaObraRecycler {
    private Artista artista;
    private Obra obra;
    private Pinturas pinturas;

    public void setPinturas(Pinturas pinturas) {
        this.pinturas = pinturas;
    }

    public Pinturas getPinturas() {
        return pinturas;
    }

    public Artista getArtista() {
        return artista;
    }

    public Obra getObra() {
        return obra;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }
}
