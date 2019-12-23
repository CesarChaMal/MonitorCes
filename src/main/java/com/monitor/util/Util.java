package com.monitor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.monitor.model.Campana;
import com.monitor.model.CampanaId;
import com.monitor.model.CliPro;
import com.monitor.model.Plaza;
import com.monitor.model.Sitio;
import com.monitor.model.Usuario;
import com.monitor.model.dto.CampanaDTO;
import com.monitor.model.dto.CliProDTO;
import com.monitor.model.dto.PlazaDTO;
import com.monitor.model.dto.SitioDTO;
import com.monitor.model.dto.UsuarioDTO;

public class Util {

	public ArrayList<PlazaDTO> getPlazasDTO(ArrayList<Object[]> plazasList) {
		ArrayList<PlazaDTO> plazaDTOList = new ArrayList<PlazaDTO>();
		for (Object[] result : plazasList) {
			PlazaDTO plazaDTO = new PlazaDTO();
			plazaDTO.setNumFotos((Long) result[0]);
			plazaDTO.setCvePlaza((String) result[1]);
			plazaDTO.setNombre((String) result[2]);
			plazaDTOList.add(plazaDTO);
		}
		return plazaDTOList;

	}

	public <T> ArrayList<PlazaDTO> getPlazaDTO(ArrayList<T> plazasList) {
		ArrayList<PlazaDTO> plazaDTOList = new ArrayList<PlazaDTO>();
		for (T result : plazasList) {
			Plaza plaza = (Plaza) result; 
			PlazaDTO plazaDTO = new PlazaDTO();
			plazaDTO.setCvePlaza(plaza.getCvePlaza());
			plazaDTO.setNombre(plaza.getNombre());
			plazaDTOList.add(plazaDTO);
		}
		return plazaDTOList;
	}

	public ArrayList<CampanaDTO> getCampanasDTO(ArrayList<Object[]> campanasList) {
		ArrayList<CampanaDTO> campanaDTOList = new ArrayList<CampanaDTO>();
		for (Object[] result : campanasList) {
			Campana campana = (Campana) result[0];
			CampanaId id = (CampanaId) campana.getId();
			CliPro clipro = (CliPro) result[1];
			
			CampanaDTO campanaDTO = new CampanaDTO();
			campanaDTO.setCveCampana(id.getCveCampana());
			campanaDTO.setNombre(campana.getNombre());
			campanaDTO.setFechaalta(campana.getFechaalta());
			campanaDTO.setInicia(campana.getInicia());
			campanaDTO.setTermina(campana.getTermina());
			campanaDTO.setStatus(campana.getStatus());
			
			CliProDTO cliproDTO = new CliProDTO();
			cliproDTO.setCveClipro(clipro.getCveClipro());
			cliproDTO.setNombre(clipro.getNombre());
			cliproDTO.setTipo(clipro.getTipo());
			cliproDTO.setPadre(clipro.getPadre());
			
			campanaDTO.setClipro(cliproDTO);
			campanaDTOList.add(campanaDTO);
		}
		return campanaDTOList;
	}

	public ArrayList<CampanaDTO> getCampanaDTO(ArrayList<Object[]> campanaList) {
		ArrayList<CampanaDTO> campanaDTOList = new ArrayList<CampanaDTO>();
		for (Object[] result : campanaList) {
			CampanaDTO campanaDTO = new CampanaDTO();
			campanaDTO.setNumFotos((Long) result[0]);
			campanaDTO.setCveCampana((String) result[1]);
			campanaDTO.setNombre((String) result[2]);
			campanaDTOList.add(campanaDTO);
		}
		return campanaDTOList;

	}

	public <T> ArrayList<CampanaDTO> getCampana2DTO(ArrayList<T> campanaList) {
		ArrayList<CampanaDTO> campanaDTOList = new ArrayList<CampanaDTO>();
		for (T result : campanaList) {
			Campana campana = (Campana) result;
			CampanaDTO campanaDTO = new CampanaDTO();
			campanaDTO.setCveCampana(campana.getId().getCveCampana());
			campanaDTO.setNombre(campana.getNombre());
			campanaDTOList.add(campanaDTO);
		}
		return campanaDTOList;
		
	}
	
	public ArrayList<SitioDTO> getSitioDTO(ArrayList<Object[]> sitioList) {
		ArrayList<SitioDTO> sitioDTOList = new ArrayList<SitioDTO>();
		for (Object[] result : sitioList) {
			SitioDTO sitioDTO = new SitioDTO();
			sitioDTO.setNumFotos((Long) result[0]);
			sitioDTO.setCveSitio((String) result[1]);
			sitioDTO.setUbicacion((String) result[2]);
			sitioDTOList.add(sitioDTO);
		}
		return sitioDTOList;

	}

	public <T> ArrayList<SitioDTO> getSitiosDTO(ArrayList<T[]> sitiosList) {
		ArrayList<SitioDTO> sitioDTOList = new ArrayList<SitioDTO>();
		for (T[] result : sitiosList) {
			CliPro clipro = (CliPro) result[0];
			Sitio sitio = (Sitio) result[1];
			Campana campana = (Campana) result[2];
			Plaza plaza = (Plaza) result[3];

			SitioDTO sitioDTO = new SitioDTO();
			sitioDTO.setCveSitio(sitio.getId().getCveSitio());
			sitioDTO.setCveCampana(sitio.getId().getCveCampana());
			sitioDTO.setCvePlaza(sitio.getId().getCvePlaza());
			sitioDTO.setCveClipro(sitio.getId().getCveClipro());
			sitioDTO.setUbicacion(sitio.getUbicacion());
			sitioDTO.setIluminacion(sitio.getIluminacion());
			sitioDTO.setStatus(sitio.getStatus());

			CliProDTO cliproDTO = new CliProDTO();
			cliproDTO.setCveClipro(clipro.getCveClipro());
			cliproDTO.setNombre(clipro.getNombre());

			CampanaDTO campanaDTO = new CampanaDTO();
			campanaDTO.setCveCampana(campana.getId().getCveCampana());
			campanaDTO.setClipro(cliproDTO);
			campanaDTO.setNombre(campana.getNombre());

			PlazaDTO plazaDTO = new PlazaDTO();
			plazaDTO.setCveClipro(clipro.getCveClipro());
			plazaDTO.setCvePlaza(plaza.getCvePlaza());
			plazaDTO.setNombre(plaza.getNombre());

			sitioDTO.setClipro(cliproDTO);
			sitioDTO.setCampana(campanaDTO);
			sitioDTO.setPlaza(plazaDTO);
			sitioDTOList.add(sitioDTO);
		}
		return sitioDTOList;

	}

	public <T> ArrayList<SitioDTO> getSitiosTreeDTO(ArrayList<T[]> sitiosList) {
		ArrayList<SitioDTO> sitioDTOList = new ArrayList<SitioDTO>();
		for (T[] result : sitiosList) {

			SitioDTO sitioDTO = new SitioDTO();
			sitioDTO.setCveSitio(result[6].toString());
			sitioDTO.setCveCampana(result[4].toString());
			sitioDTO.setCvePlaza(result[2].toString());
			sitioDTO.setCveClipro(result[0].toString());
			
			CliProDTO cliproDTO = new CliProDTO();
			cliproDTO.setCveClipro(sitioDTO.getCveClipro());
			cliproDTO.setNombre(result[1].toString());
			
			CampanaDTO campanaDTO = new CampanaDTO();
			campanaDTO.setClipro(cliproDTO);
			campanaDTO.setCveCampana(sitioDTO.getCveCampana());
			campanaDTO.setNombre(result[5].toString());
			
			PlazaDTO plazaDTO = new PlazaDTO();
			plazaDTO.setCveClipro(cliproDTO.getCveClipro());
			plazaDTO.setCvePlaza(sitioDTO.getCvePlaza());
			plazaDTO.setNombre(result[3].toString());
			
			sitioDTO.setClipro(cliproDTO);
			sitioDTO.setCampana(campanaDTO);
			sitioDTO.setPlaza(plazaDTO);
			sitioDTOList.add(sitioDTO);
		}
		return sitioDTOList;
		
	}
	
	public <T> ArrayList<UsuarioDTO> getUsuariosDTO(ArrayList<T[]> usuariosList) {
		ArrayList<UsuarioDTO> usuarioDTOList = new ArrayList<UsuarioDTO>();
		// for (Object[] result : usuariosList) {
		for (T[] result : usuariosList) {
			Usuario usuario = (Usuario) result[0];
			CliPro clipro = (CliPro) result[1];

			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setEmail(usuario.getEmail());
			usuarioDTO.setNombre(usuario.getNombre());
			usuarioDTO.setApellidos(usuario.getApellidos());
			usuarioDTO.setContrasena(usuario.getContrasena());
			usuarioDTO.setTipo(usuario.getTipo());
			usuarioDTO.setFechaalta(usuario.getFechaalta());
			usuarioDTO.setStatus(usuario.getStatus());

			CliProDTO cliproDTO = new CliProDTO();
			cliproDTO.setCveClipro(clipro.getCveClipro());
			cliproDTO.setNombre(clipro.getNombre());
			cliproDTO.setTipo(clipro.getTipo());
			cliproDTO.setPadre(clipro.getPadre());

			usuarioDTO.setClipro(cliproDTO);
			usuarioDTOList.add(usuarioDTO);
		}
		return usuarioDTOList;
	}

	public Date formatDate(Date date) throws Exception {
		if(date!=null){
			SimpleDateFormat dt = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);			
			String dateString =dt.format(date);
			System.out.println("dateString " +dateString);
		}
		return date;
	}
	
	public static boolean isParsable(String input){
	    boolean parsable = true;
	    try{
	        Integer.parseInt(input);
	    }catch(NumberFormatException e){
	        parsable = false;
	    }
	    return parsable;
	}	
}
