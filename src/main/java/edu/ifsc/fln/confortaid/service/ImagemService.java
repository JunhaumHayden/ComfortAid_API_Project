package edu.ifsc.fln.confortaid.service;

import edu.ifsc.fln.confortaid.model.FotoUsuario;
import edu.ifsc.fln.confortaid.model.FotoServico;
import edu.ifsc.fln.confortaid.repository.FotoUsuarioRepository;
import edu.ifsc.fln.confortaid.repository.FotoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ImagemService {
    @Autowired
    private FotoUsuarioRepository fotoUsuarioRepository;

    @Autowired
    private FotoServicoRepository fotoServicoRepository;

    public Optional<byte[]> getPrimeiraFotoUsuario(Integer usuarioId) {
        return fotoUsuarioRepository.findByUsuarioId(usuarioId).stream()
                .findFirst()
                .map(FotoUsuario::getFoto);
    }

    public List<FotoUsuario> getFotosUsuario(Integer usuarioId) {
        return fotoUsuarioRepository.findByUsuarioId(usuarioId);
    }

    public void saveFotoUsuario(Integer usuarioId, byte[] foto) {
        FotoUsuario fotoUsuario = new FotoUsuario();
        fotoUsuario.setUsuarioId(usuarioId);
        fotoUsuario.setFoto(foto);
        fotoUsuarioRepository.save(fotoUsuario);
    }

    public List<FotoServico> getFotosServico(Integer servicoId) {
        return fotoServicoRepository.findByServicoId(servicoId);
    }

    public void saveFotoServico(Integer servicoId, byte[] foto) {
        FotoServico fotoServico = new FotoServico();
        fotoServico.setServicoId(servicoId);
        fotoServico.setFoto(foto);
        fotoServicoRepository.save(fotoServico);
    }

    public void deleteFotoUsuario(Integer usuarioId) {
        fotoUsuarioRepository.deleteByUsuarioId(usuarioId);
    }

    public void deleteFotoServico(Integer servicoId) {
        fotoServicoRepository.deleteByServicoId(servicoId);
    }

    public void deleteFotoUsuarioPorId(Integer fotoId) {
        fotoUsuarioRepository.deleteById(fotoId);
    }

    public void deleteFotoServicoPorId(Integer fotoId) {
        fotoServicoRepository.deleteById(fotoId);
    }
}
