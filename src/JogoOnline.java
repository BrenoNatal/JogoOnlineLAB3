import java.util.ArrayList;
import java.util.List;

public class JogoOnline {

    public ArrayList<Jogador> jogadores;


    public JogoOnline() {
        this.jogadores = new ArrayList<>();
    }

    public boolean cadastrar(String username, String senhaDoJogador) {
        if (checarUsername(username)) {
            return false;
        }
        Jogador usuario;
        usuario = new Jogador(username, senhaDoJogador);
        this.jogadores.add(usuario);
        return true;
    }

    public boolean login(String username, String senhaDoJogador) {
        for (Jogador jogador : this.jogadores) {
            if (jogador.getUsername().equals(username) && jogador.getSenha().equals(senhaDoJogador)) {
                jogador.setOnline(true);
                return true;
            }
        }
        return false;
    }

    public boolean logout(String username, String senhaDoJogador) {
        for (Jogador jogador : this.jogadores) {
            if (jogador.getSenha().equals(senhaDoJogador)) {
                jogador.setOnline(false);
                return true;
            }
        }
        return false;
    }

    public Partida iniciarPartida(Jogador jogador1, Jogador jogador2) {
        if(jogador1.isOnline() && jogador2.isOnline()){
            if(!(jogador1.isJogando() && jogador2.isJogando())){
                Partida partida = new Partida(jogador1, jogador2);
                jogador1.setJogando(true);
                jogador2.setJogando(true);
                return partida;
            }
            return null;
        }
        return null;
    }

    public Jogador escolherAdversario(Jogador solicitante) {
        for (Jogador jogador : this.jogadores) {
            if (jogador.isOnline() && !(jogador.isJogando()) && !(jogador.getUsername().equals(solicitante.getUsername()))) {
                return jogador;
            }
        }
        return null;
    }

    public boolean checarUsername(String usuario) {
        for (Jogador jogador : this.jogadores) {
            if (jogador.getUsername().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    public void encerrarPartida(Partida partida, int resultado) {
        partida.getJogador1().setJogando(false);
        partida.getJogador2().setJogando(false);
        if (resultado == 1) {
            partida.getJogador1().setPontuacao(1);
            partida.getJogador2().setPontuacao(-1);
            partida.setResultado(1);
        }
        if (resultado == 2) {
            partida.getJogador1().setPontuacao(-1);
            partida.getJogador2().setPontuacao(1);
            partida.setResultado(2);
        }
        if (resultado == 0) {]
            partida.setResultado(0);
        }
        partida.getJogador1().minhaPartidas.add(partida);
        partida.getJogador2().minhaPartidas.add(partida);
    }

}

