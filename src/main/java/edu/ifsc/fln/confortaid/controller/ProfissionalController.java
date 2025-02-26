package edu.ifsc.fln.confortaid.controller;

import edu.ifsc.fln.confortaid.model.Profissional;
import edu.ifsc.fln.confortaid.DTO.ProfissionalDTO;
import edu.ifsc.fln.confortaid.repository.ProfissionalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @GetMapping("/nodto")
    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

    @GetMapping("/nodto/{id}")
    public ResponseEntity<Profissional> buscarPorId(@PathVariable Integer id) {
        return profissionalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Profissional profissional) {
        if (profissionalRepository.findByEmail(profissional.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: O email já está cadastrado.");
        }

        if (profissionalRepository.findByRegistroProfissional(profissional.getRegistroProfissional()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: O registro profissional já está cadastrado.");
        }

        Profissional novoProfissional = profissionalRepository.save(profissional);
        ProfissionalDTO profissionalDTO = convertToDTO(novoProfissional);
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalDTO> atualizar(@PathVariable Integer id, @RequestBody Profissional profissionalAtualizado) {
        return profissionalRepository.findById(id)
                .map(profissional -> {
                    profissional.setNome(profissionalAtualizado.getNome());
                    profissional.setEmail(profissionalAtualizado.getEmail());
                    profissional.setTelefone(profissionalAtualizado.getTelefone());
                    profissional.setCep(profissionalAtualizado.getCep());
                    profissional.setComplementoEndereco(profissionalAtualizado.getComplementoEndereco());
                    profissional.setNumeroEndereco(profissionalAtualizado.getNumeroEndereco());
                    profissional.setEspecialidade(profissionalAtualizado.getEspecialidade());
                    profissional.setRegistroProfissional(profissionalAtualizado.getRegistroProfissional());
                    Profissional atualizado = profissionalRepository.save(profissional);
                    return ResponseEntity.ok(convertToDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Integer id) {
        return profissionalRepository.findById(id)
                .map(profissional -> {
                    profissionalRepository.delete(profissional);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ProfissionalDTO> listarDTO() {
        return profissionalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalDTO> buscarPorIdDTO(@PathVariable Integer id) {
        return profissionalRepository.findById(id)
                .map(profissional -> ResponseEntity.ok(convertToDTO(profissional)))
                .orElse(ResponseEntity.notFound().build());
    }

    private ProfissionalDTO convertToDTO(Profissional profissional) {
        return new ProfissionalDTO(
                profissional.getId(),
                profissional.getNome(),
                profissional.getEmail(),
                profissional.getTelefone(),
                profissional.getEspecialidade(),
                profissional.getRegistroProfissional()
        );
    }
}

