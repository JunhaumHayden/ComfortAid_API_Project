package edu.ifsc.fln.confortaid.controller;

import edu.ifsc.fln.confortaid.model.FotoServico;
import edu.ifsc.fln.confortaid.model.FotoUsuario;
import edu.ifsc.fln.confortaid.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private ImagemService imagemService;

    @GetMapping("/usuario/foto/{id}")
    public ResponseEntity<byte[]> getPrimeiraFotoUsuario(@PathVariable Integer id) {
        return imagemService.getPrimeiraFotoUsuario(id)
                .map(foto -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
                    return new ResponseEntity<>(foto, headers, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/usuario/fotos/{id}")
    public ResponseEntity<List<byte[]>> getFotosUsuario(@PathVariable Integer id) {
        List<FotoUsuario> fotos = imagemService.getFotosUsuario(id);
        if (fotos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<byte[]> fotosBytes = fotos.stream().map(FotoUsuario::getFoto).collect(Collectors.toList());
        return new ResponseEntity<>(fotosBytes, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioid}")
    public ResponseEntity<List<FotoUsuario>> listarFotosPorUsuario(@PathVariable Integer usuarioid) {
        List<FotoUsuario> fotos = imagemService.getFotosUsuario(usuarioid);
        if (fotos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fotos, HttpStatus.OK);
    }

    @PostMapping("/usuario/{id}")
    public ResponseEntity<Void> uploadFotoUsuario(@PathVariable Integer id, @RequestParam("foto") MultipartFile file) {
        try {
            imagemService.saveFotoUsuario(id, file.getBytes());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/usuario/foto/{fotoId}")
    public ResponseEntity<Void> deleteFotoPorId(@PathVariable Integer fotoId) {
        imagemService.deleteFotoUsuarioPorId(fotoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Fotos de Servicos

    @GetMapping("/servico/{servicoid}")
    public ResponseEntity<List<FotoServico>> listarFotosPorServico(@PathVariable Integer servicoid) {
        List<FotoServico> fotos = imagemService.getFotosServico(servicoid);
        if (fotos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fotos, HttpStatus.OK);
    }


    @GetMapping("/servico/fotos/{id}")
    public ResponseEntity<List<byte[]>> getFotosServico(@PathVariable Integer id) {
        List<FotoServico> fotos = imagemService.getFotosServico(id);
        if (fotos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<byte[]> fotosBytes = fotos.stream().map(FotoServico::getFoto).collect(Collectors.toList());
        return new ResponseEntity<>(fotosBytes, HttpStatus.OK);
    }



    @PostMapping("/servico/{id}")
    public ResponseEntity<Void> uploadFotoServico(@PathVariable Integer id, @RequestParam("foto") MultipartFile file) {
        try {
            imagemService.saveFotoServico(id, file.getBytes());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/servico/foto/{fotoId}")
    public ResponseEntity<Void> deleteFotoServico(@PathVariable Integer fotoId) {
        imagemService.deleteFotoServicoPorId(fotoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}