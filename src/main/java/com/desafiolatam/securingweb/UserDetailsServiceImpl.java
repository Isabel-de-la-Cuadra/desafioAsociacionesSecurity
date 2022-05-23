package com.desafiolatam.securingweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.desafiolatam.models.Rol;
import com.desafiolatam.models.Usuario;
import com.desafiolatam.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UsuarioRepository usuarioRepository;
//1
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByCorreo(username);
		UserDetails userDetails = new User(usuario.getCorreo(),usuario.getPassword(),getAuthorities(usuario)); 
		return userDetails;
	}
//2
    private List<GrantedAuthority> getAuthorities(Usuario usuario){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Rol rol : usuario.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.getNombre());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}