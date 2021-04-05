package com.accenture.pruebatecnica.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.accenture.pruebatecnica.model.Usuario;

@Component
public class UsuariosService {
	
	private static Map<Long, Usuario> usuarios;
	
	static {
		usuarios = new HashMap<>();
		usuarios.put(1L,new Usuario(1,"fede@gmail.com","123456","Federico Reina", "Cra 10 # 15 - 23", 1000000));
		usuarios.put(2L,new Usuario(2,"juan@gmail.com","123456","Juan Parra", "Cra 11 # 14 - 08", 1000000));
	}
	
	public long validateUser(String email, String clave) {		
		for (Usuario usuario: usuarios.values()) {
			if (usuario.getEmail().equals(email)) {
				return usuario.getClave().equals(clave) ? usuario.getId() : -1;
			}
		}
		return -1;
	}
	
	public Usuario obtenerPorId(long idUsuario) {
		return usuarios.get(idUsuario);
	}
	
	public double obtenerSaldo(long idUsuario) {
		return usuarios.get(idUsuario).getSaldo();
	}
}
