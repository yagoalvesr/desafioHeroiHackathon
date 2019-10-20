package com.stefanini.hackaton.parsers;

import com.stefanini.hackaton.dto.VencedorDTO;
import com.stefanini.hackaton.entities.Resultado;

public class VencedorParser extends AbstractParser<VencedorDTO, Resultado> {

	@Override
	public VencedorDTO toDTO(Resultado entity) {

		VencedorDTO dto = new VencedorDTO();

		dto.setNickname(entity.getNickname());

		return dto;
	}

	@Override
	public Resultado toEntity(VencedorDTO dto) {
		Resultado entity = new Resultado();
		entity.setNickname(dto.getNickname());

		return entity;
	}

}
