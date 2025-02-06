package edu.ifsc.fln.confortaid.service;

import edu.ifsc.fln.confortaid.model.FotoCliente;
import edu.ifsc.fln.confortaid.model.FotoProfissional;
import edu.ifsc.fln.confortaid.repository.FotoClienteRepository;
import edu.ifsc.fln.confortaid.repository.FotoProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ImagemService {

    @Autowired
    private FotoClienteRepository fotoClienteRepository;

    @Autowired
    private FotoProfissionalRepository fotoProfissionalRepository;

    public Optional<byte[]> getPrimeiraFotoCliente(Integer clienteId) {
        List<FotoCliente> fotos = fotoClienteRepository.findByClienteId(clienteId);
        if (fotos.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(fotos.get(0).getFoto());
    }

    public List<FotoCliente> getFotosCliente(Integer clienteId) {
        return fotoClienteRepository.findByClienteId(clienteId);
    }

    public Optional<byte[]> getPrimeiraFotoProfissional(Integer profissionalId) {
        List<FotoProfissional> fotos = fotoProfissionalRepository.findByProfissionalId(profissionalId);
        if (fotos.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(fotos.get(0).getFoto());
    }

    public List<FotoProfissional> getFotosProfissional(Integer profissionalId) {
        return fotoProfissionalRepository.findByProfissionalId(profissionalId);
    }

    public FotoCliente saveFotoCliente(Integer clienteId, byte[] foto) {
        FotoCliente fotoCliente = new FotoCliente();
        fotoCliente.setClienteId(clienteId);
        fotoCliente.setFoto(foto);
        return fotoClienteRepository.save(fotoCliente);
    }

    public FotoProfissional saveFotoProfissional(Integer profissionalId, byte[] foto) {
        FotoProfissional fotoProfissional = new FotoProfissional();
        fotoProfissional.setProfissionalId(profissionalId);
        fotoProfissional.setFoto(foto);
        return fotoProfissionalRepository.save(fotoProfissional);
    }
}
