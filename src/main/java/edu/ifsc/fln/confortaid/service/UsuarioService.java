package edu.ifsc.fln.confortaid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifsc.fln.confortaid.model.Usuario;
import edu.ifsc.fln.confortaid.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario authenticateAndGetToken(Usuario usuario) {
        String login = usuario.getEmail();
        String senha = usuario.getSenha();
        usuario = usuarioRepository.findByEmailAndSenha(login, senha);
        if (usuario != null) {

            return usuario;
        }
        return null;
    }
}
