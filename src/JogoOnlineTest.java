import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JogoOnlineTest {


    public JogoOnline jogoOnline = new JogoOnline();

    @Before
    public void setUp()  {
        jogoOnline.cadastrar("Pedro","2345");
        jogoOnline.cadastrar("Ana","4321");
        jogoOnline.cadastrar("Edu","123");
        jogoOnline.cadastrar("Maria","123");
        jogoOnline.cadastrar("Cara","1235");
    }

    @Test
    public void testeChecarUsername () {
        assertTrue("Testar se o checarUsername esta funcionando",
                jogoOnline.checarUsername("Ana"));
    }
    @Test
    public void testeCadastro () {
        assertFalse("Testar se é possivel cadastrar o mesmo username",
                jogoOnline.cadastrar("Pedro",
                        "pedrin"));
    }
    @Test
    public void testeLogin () {
        jogoOnline.login("Pedro","2345");

        assertTrue("Checar se o jogador esta online",
                jogoOnline.jogadores.get(0).isOnline());
    }
    @Test
    public void testeLogout () {
        jogoOnline.login("Pedro","2345");
        jogoOnline.logout("Pedro","2345");

        assertFalse("Checar se o jogador esta offline depois de fazer logout",
                jogoOnline.jogadores.get(0).isOnline());
    }
    @Test
    public void testeIniciarPartida () {
        jogoOnline.login("Pedro","2345");
        jogoOnline.login("Ana", "4321");

        Jogador jogador1 = jogoOnline.jogadores.get(0);
        Jogador jogador2 = jogoOnline.jogadores.get(1);

        jogoOnline.iniciarPartida(jogador1, jogador2);

        assertTrue("Checar se jogador1 esta jogando", jogador1.isJogando());
        assertTrue("Checar se jogador2 esta jogando", jogador2.isJogando());

    }
    @Test
    public void testeGetResultado () {
        jogoOnline.login("Pedro","2345");
        jogoOnline.login("Ana", "4321");

        Jogador jogador1 = jogoOnline.jogadores.get(0);
        Jogador jogador2 = jogoOnline.jogadores.get(1);

        Partida partida1;
        partida1 = jogoOnline.iniciarPartida(jogador1, jogador2);

        assertEquals("Ver se o vencedor é o esperado",
                1,
                partida1.getResultado());
    }
    @Test
    public void testeEscolherAdversario () {
        jogoOnline.login("Maria","123");
        jogoOnline.login("Pedro","2345");
        jogoOnline.login("Ana", "4321");
        jogoOnline.login("Edu","123");

        Jogador jogador1 = jogoOnline.jogadores.get(0);
        Jogador jogador2 = jogoOnline.jogadores.get(1);
        Jogador jogador3 = jogoOnline.jogadores.get(2);
        Jogador jogador4 = jogoOnline.jogadores.get(3);

        jogoOnline.iniciarPartida(jogador3,jogador2);

        Jogador jogadorEscolhido = jogoOnline.escolherAdversario(jogador1);

        assertEquals("Checar se vai escolher o jogador disponivel","Maria",jogadorEscolhido.getUsername());
    }
    @Test
    public void testeEncerraPartida () {
        jogoOnline.login("Pedro", "2345");
        jogoOnline.login("Ana", "4321");

        Jogador jogador1 = jogoOnline.jogadores.get(0);
        Jogador jogador2 = jogoOnline.jogadores.get(1);

        int pontuacaoInicial1 = jogador1.getPontuacao();
        int pontuacaoInicial2 = jogador2.getPontuacao();

        Partida partida = jogoOnline.iniciarPartida(jogador1, jogador2);

        jogoOnline.encerrarPartida(partida, 1);

        assertEquals("Checar a pontucao do vencedor", pontuacaoInicial1 + 1, jogador1.getPontuacao());
        assertEquals("Checar a pontucao do perdedor", pontuacaoInicial2 - 1, jogador2.getPontuacao());

        assertFalse("Checar se saiu da partida", jogador1.isJogando());
        assertFalse("Checar se saiu da partida", jogador2.isJogando());
    }
}