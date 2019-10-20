package com.stefanini.hackaton.parsers;

import com.stefanini.hackaton.dto.HeroiDTO;
import com.stefanini.hackaton.entities.Heroi;

public class HeroiParserDTO extends AbstractParser<HeroiDTO, Heroi> {

	@Override
	public HeroiDTO toDTO(Heroi entity) {
		HeroiDTO dto = new HeroiDTO();
		dto.setAtaque(entity.getAtaque());
		dto.setDefesa(entity.getDefesa());
		dto.setForca(entity.getForca());
		dto.setId(entity.getId());
		dto.setInteligencia(entity.getInteligencia());
		dto.setNome(entity.getNome());
		dto.setPoder(entity.getPoder());
		dto.setVelocidade(entity.getVelocidade());
		dto.setVida(entity.getVida());
		return dto;
	}

	@Override
	public Heroi toEntity(HeroiDTO dto) {
		Heroi entity = new Heroi();
		entity.setAtaque(dto.getAtaque());
		entity.setDefesa(dto.getDefesa());
		entity.setForca(dto.getForca());
		entity.setId(dto.getId());
		entity.setInteligencia(dto.getInteligencia());
		entity.setNome(dto.getNome());
		entity.setPoder(dto.getPoder());
		entity.setVelocidade(dto.getVelocidade());
		entity.setVida(dto.getVida());
		return entity;
	}

}
