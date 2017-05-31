package br.com.cwi.moses.service;

import java.util.ArrayList;
import java.util.List;

import br.com.cwi.moses.model.SituacaoTicket;
import br.com.cwi.moses.model.Ticket;
import br.com.cwi.moses.model.TipoTicket;

/**
 * Created by Murillo on 31/05/2017.
 */

public class TicketApiService {

    public List<Ticket> getAllTickets() {
        return this.getFakeList();
    }


    private List<Ticket> getFakeList() {
        List<Ticket> list = new ArrayList<>();

        list.add(new Ticket("Caronas da Empresa", "Poderiamos ter um sistema de gerenciamento de caronas para otimizar as vagas no estacionamento", TipoTicket.SUGESTAO, SituacaoTicket.ABERTO));
        list.add(new Ticket("Conectar Notebook na Rede", "Não  Consigo conectar minha máquina na Rede. Help-me!", TipoTicket.PROBLEMA, SituacaoTicket.ABERTO));
        list.add(new Ticket("Senha do Wifi", "Esqueci a senha do WIFI. Help-me!", TipoTicket.DUVIDA, SituacaoTicket.ABERTO));
        list.add(new Ticket("Videogame", "Podíamos ter videogames à disposição dos funcionários para os horários de lazer. Fica a dica.", TipoTicket.SUGESTAO, SituacaoTicket.ABERTO));
        list.add(new Ticket("Trabalhar de Bermuda", "Pode trabalhar de bermuda no verão?", TipoTicket.DUVIDA, SituacaoTicket.ABERTO));
        list.add(new Ticket("Caronas da Empresa", "Poderiamos ter um sistema de gerenciamento de caronas para otimizar as vagas no estacionamento", TipoTicket.SUGESTAO, SituacaoTicket.ABERTO));
        list.add(new Ticket("Conectar Notebook na Rede", "Não  Consigo conectar minha máquina na Rede. Help-me!", TipoTicket.PROBLEMA, SituacaoTicket.ABERTO));
        list.add(new Ticket("Senha do Wifi", "Esqueci a senha do WIFI. Help-me!", TipoTicket.DUVIDA, SituacaoTicket.ABERTO));
        list.add(new Ticket("Videogame", "Podíamos ter videogames à disposição dos funcionários para os horários de lazer. Fica a dica.", TipoTicket.SUGESTAO, SituacaoTicket.ABERTO));
        list.add(new Ticket("Trabalhar de Bermuda", "Pode trabalhar de bermuda no verão?", TipoTicket.DUVIDA, SituacaoTicket.ABERTO));

        return list;
    }
}
