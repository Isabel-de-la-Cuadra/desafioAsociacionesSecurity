package com.desafiolatam.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolatam.models.Usuario;
import com.desafiolatam.repositories.RolRepository;
import com.desafiolatam.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RolRepository rolRepository;

	public boolean guardarUsuario(Usuario usuario) {
		Usuario usuarioRetorno = usuarioRepository.findByCorreo(usuario.getCorreo());
		if (usuarioRetorno == null) {
			// password encriptado
			String passHashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
			// 1234 -> $223tgf4vt45tvt534dsvhdj
			usuario.setPassword(passHashed);

			// BUSCAR ROL a DB; ASIGNAR ROL; ROLE_ADMIN, ROLE_USER
			usuario.setRoles(rolRepository.findByNombre("ROLE_USER"));

			usuarioRepository.save(usuario);
			return true;
		} else {
			return false;
		}
	}
	
	// Asignarle un nuevo rol a un usuario (recordar relación ManyToMany)
	public boolean agregarRol(Usuario usuario) {
		usuarioRepository.save(usuario);
		return true;
	}

	public boolean login(String correo, String password) {
		// buscar si existe con ese correo
		Usuario usuario = usuarioRepository.findByCorreo(correo);

		if (usuario != null) {// si existe o no el correo
			if (BCrypt.checkpw(password, usuario.getPassword())) { // si los password son iguales
				return true;
			} else {
				return false;// pasword distintos
			}
		} else {
			return false;// no existe ese correo
		}
	}

	public Usuario findByCorreo(String username) {
		Usuario usuario = usuarioRepository.findByCorreo(username);
		return usuario;
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
}
