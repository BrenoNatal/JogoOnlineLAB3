import java.util.ArrayList;
import java.util.List;

public class Jogador {

    private String username;
    private String senha;
    private int pontuacao;
    private static final int PONTUCAO_INICIAL = 1000;
    public ArrayList<Partida> minhaPartidas;
    private boolean Online;
    private boolean Jogando;

    public Jogador(String usernameDoJogador, String senhaDoJogador) {
        this.username = usernameDoJogador;
        this.senha = senhaDoJogador;
        this.pontuacao = PONTUCAO_INICIAL;
        this.minhaPartidas = new ArrayList<>();
        setJogando(false);
    }

    public void setOnline(boolean online) {
        Online = online;
    }

    public boolean isOnline() {
        return Online;
    }

    public void setPontuacao(int pontos) {
        this.pontuacao = pontuacao + pontos;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setJogando(boolean jogando) {
        Jogando = jogando;
    }

    public boolean isJogando() {
        return Jogando;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

}
