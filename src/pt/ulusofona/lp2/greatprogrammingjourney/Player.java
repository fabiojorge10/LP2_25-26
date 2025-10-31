package pt.ulusofona.lp2.greatprogrammingjourney;

public class Player {
    int id, posicao;
    String nome, cor, linguagens;
    Boolean emJogo;

    public Player() {
    }

    public Player(int id, int posicao, String nome, String cor, String linguagens) {
        this.id = id;
        this.posicao = posicao;
        this.nome = nome;
        this.cor = cor;
        this.linguagens = linguagens;
        this.emJogo = true;
    }

    public int getId() {
        return id;
    }

    public int getPosicao() {
        return posicao;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public String getLinguagens() {
        return linguagens;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }



    @Override
    public String toString() {
        String estado = (emJogo)?"Em Jogo":"Derrotado";
        return id + " | " + nome + " | " + posicao  +  " | " + linguagens + " | " + estado;
    }
}
