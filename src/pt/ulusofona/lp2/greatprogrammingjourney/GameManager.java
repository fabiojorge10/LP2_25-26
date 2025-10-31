package pt.ulusofona.lp2.greatprogrammingjourney;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GameManager {

    public List<Player> listaPlayers = new ArrayList<>();
    public HashMap<Integer,Player> allInfoPlayers = new HashMap<>();


    public int tamanhoTabuleiro;
    public int numJogadores;
    public int[] currentPlayer;
    public int atual = 0;
    public int rondas = 0;
    public String vencedor;


    public boolean createInitialBoard(String[][] playerInfo, int worldSize){
        numJogadores = playerInfo.length;
        currentPlayer = new int[numJogadores];
        List<String> cores = new ArrayList<>(Arrays.asList("Purple", "Green", "Blue", "Red"));
        List<Integer> idJogadores= new ArrayList<>();
        int cont=0;
        for(int i=0;i<numJogadores;i++){
            String[] dados = playerInfo[i];


            int id= Integer.parseInt(dados[0]);
            if(id<0 || idJogadores.contains(id)){return false;}

            String nome = dados[1];
            if(nome.isBlank() || nome.isEmpty()){return false;}

            String linguagens = dados[2];

            String cor = dados[3];
            if(!cores.contains(cor)){return false;}
            cores.remove(cor);

            currentPlayer[cont]=id;

            Player p = new Player(id, 0, nome, cor, linguagens);
            listaPlayers.add(p);
            allInfoPlayers.put(id, p);
            idJogadores.add(id);
            cont++;



        }
        if(worldSize<numJogadores*2){return false;}

        tamanhoTabuleiro= worldSize;
        return true;
    }


    public String getSlotInfo(int pos){
        if(pos<0 || pos>tamanhoTabuleiro-1){return null;}

        int cont=0;
        ArrayList<Integer> lista = new ArrayList<>();
        for(Player p : listaPlayers){
            if(p.getPosicao()==pos){lista.add(p.getId());}
        }

        if (lista.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (Integer num: lista) {
            cont++;
            result.append(num.toString());
            if (cont != lista.size()) {
                result.append(",");
            }
        }
        return result.toString();
    }

    public int getCurrentPlayerID(){
        return currentPlayer[atual];

    }


    public String getImagePng(int nrSquare){
        return null;
    }

    public String[] getProgrammerInfo(int id){
        if(!allInfoPlayers.containsKey(id)){return null;}

        Player p = allInfoPlayers.get(id);
        String[] result = new String[4];

        result[0]=String.valueOf(p.getId());
        result[1]=p.getNome();
        result[2]=p.getLinguagens();
        result[3]=p.getCor();

        return result;

    }

    public String getProgrammerInfoAsStr(int id){


        if(!allInfoPlayers.containsKey(id)){return "";}
        return allInfoPlayers.get(id).toString();
    }


    public boolean moveCurrentPlayer(int nrSpaces){
        if(nrSpaces<1||nrSpaces>6){return false;}
        Player p= allInfoPlayers.get(currentPlayer[atual]);
        if(p.getPosicao()+nrSpaces>tamanhoTabuleiro){p.setPosicao(tamanhoTabuleiro-1);return true;}
        p.setPosicao(p.getPosicao()+nrSpaces);
        atual = (atual + 1) % numJogadores;
        rondas++;
        return true;

    }


    public boolean gameIsOver(){
        for(Player p: listaPlayers){
            if(p.getPosicao()==tamanhoTabuleiro-1){
                vencedor=p.getNome();
                rondas++;
                return true;
            }

        }

        return false;
    }

    public ArrayList<String> getGameResults(){
        ArrayList<String> str = new ArrayList<>();

        str.add("THE GREAT PROGRAMMING JOURNEY");
        str.add("");
        str.add("NR. DE TURNOS");
        str.add(String.valueOf(rondas));
        str.add("");
        str.add("VENCEDOR");
        str.add(vencedor);
        str.add("");
        str.add("RESTANTES");
        str.addAll(restantes());

        return str;
    }

    public ArrayList<String> restantes(){
        listaPlayers.sort((p1, p2) -> Integer.compare(p2.getPosicao(), p1.getPosicao()));
        ArrayList<String> resultado = new ArrayList<>();

        for(Player p : listaPlayers){
            if(p.getPosicao()!=tamanhoTabuleiro-1){
                resultado.add(p.getNome() + " " + p.getPosicao());
            }
        }

        return resultado;
    }

    public JPanel getAuthorsPanel(){
        JPanel panel = new JPanel();
        panel.add(new JLabel("Nome: FJ"));
        return panel;
    }


    public HashMap<String, String> customizeBoard(){
        return new HashMap<>();
    }

}
