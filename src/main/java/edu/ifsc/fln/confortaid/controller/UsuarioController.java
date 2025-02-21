package edu.ifsc.fln.confortaid.controller;

import edu.ifsc.fln.confortaid.DTO.UsuarioDTO;
import edu.ifsc.fln.confortaid.model.Usuario;
import edu.ifsc.fln.confortaid.service.UsuarioService;
import edu.ifsc.fln.confortaid.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioAtualizado.getNome());
                    usuario.setEmail(usuarioAtualizado.getEmail());
                    usuario.setSenha(usuarioAtualizado.getSenha());
                    usuario.setTelefone(usuarioAtualizado.getTelefone());
                    usuario.setCep(usuarioAtualizado.getCep());
                    usuario.setComplementoEndereco(usuarioAtualizado.getComplementoEndereco());
                    usuario.setNumeroEndereco(usuarioAtualizado.getNumeroEndereco());
                    return ResponseEntity.ok(usuarioRepository.save(usuario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Integer id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuarioRepository.delete(usuario);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody Usuario usuario) {
        Usuario token = usuarioService.authenticateAndGetToken(usuario);
        if (token != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(token.getId());
            usuarioDTO.setNome(token.getNome());
            usuarioDTO.setEmail(token.getEmail());
            usuarioDTO.setTelefone(token.getTelefone());
            usuarioDTO.setTipo(token.getTipo());

            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}