package com.stefanini.hackaton.service;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import com.stefanini.hackaton.dto.HeroiDTO;
import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.entities.Resultado;
import com.stefanini.hackaton.parsers.HeroiParserDTO;
import com.stefanini.hackaton.parsers.JogadorParserDTO;
import com.stefanini.hackaton.persistence.HeroiDAO;
import com.stefanini.hackaton.persistence.JogadorDAO;

public class BatalhaService {

	@Inject
	JogadorDAO dao;
	
	@Inject
	HeroiDAO heroiDao;
	
	@Inject
	HeroiParserDTO heroiParser;

	@Inject
	JogadorParserDTO parser;


	public Resultado batalha(String nickNameJogador, String nickNameAdversario) {

		JogadorDTO jogadorDto = parser.toDTO(dao.findByNickname(nickNameJogador));
		JogadorDTO adversarioDto = parser.toDTO(dao.findByNickname(nickNameAdversario));

		HeroiDTO heroiJogador = jogadorDto.getHeroiDto();
		HeroiDTO heroiAdversario = adversarioDto.getHeroiDto();

		int round = 0;
		int turno = 0;

		for (round = 0; round <= 10;) {

			if (turno % 2 == 0) {

				double dano = 0;

				dano = atacar(heroiJogador);

				double danoRecebido = 0;

				double novaVida = receberAtaque(heroiAdversario, dano);

				heroiAdversario.setVida(novaVida);

				turno++;

			} else {

				double dano = 0;

				dano = atacar(heroiAdversario);

				double danoRecebido = 0;

				double novaVida = receberAtaque(heroiJogador, dano);

				heroiJogador.setVida(novaVida);

				turno++;
				round++;

			}

			if (heroiJogador.getVida() < 0 || heroiAdversario.getVida() < 0) {
				round = 11;
			}

		}

		Resultado resultado = new Resultado();

		if (heroiAdversario.getVida() <= 0) {
			resultado.setNickname(jogadorDto.getNickname());
			return resultado;
		} else if (heroiJogador.getVida() <= 0) {
			resultado.setNickname(adversarioDto.getNickname());
			return resultado;
		} else {
			resultado.setNickname("Empate");
			return resultado;
		}

	}

	private double atacar(HeroiDTO heroi) {

		double dano = 0;

		double chance = heroi.getInteligencia() * 0.8;
		double porcentagem = Math.random() * 100;

		if (chance > porcentagem) {
			dano = ((heroi.getAtaque() * heroi.getForca()) * 250) * 2;
			return dano;
		} else {
			dano = (heroi.getAtaque() * heroi.getForca() * 250);
			return dano;
		}

	}

	private double receberAtaque(HeroiDTO heroi, double dano) {

		double danoRecebido = 0;

		double chance = heroi.getInteligencia() * 0.4;
		double porcentagem = Math.random() * 100;

		if (chance > porcentagem) {
			danoRecebido = heroi.getVida();
			return danoRecebido;
		} else {
			dano = dano - (heroi.getDefesa() * 2.3);
			danoRecebido = heroi.getVida() - dano;
		}

		return danoRecebido;

	}

}
