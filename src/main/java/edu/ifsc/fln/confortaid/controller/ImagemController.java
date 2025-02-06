package edu.ifsc.fln.confortaid.controller;

import edu.ifsc.fln.confortaid.model.FotoCliente;
import edu.ifsc.fln.confortaid.model.FotoProfissional;
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
@RequestMapping("/api/imagens")
public class ImagemController {

    @Autowired
    private ImagemService imagemService;

    @GetMapping("/cliente/{id}/foto")
    public ResponseEntity<byte[]> getPrimeiraFotoCliente(@PathVariable Integer id) {
        return imagemService.getPrimeiraFotoCliente(id)
                .map(foto -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
                    return new ResponseEntity<>(foto, headers, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/profissional/{id}/foto")
    public ResponseEntity<byte[]> getPrimeiraFotoProfissional(@PathVariable Integer id) {
        return imagemService.getPrimeiraFotoProfissional(id)
                .map(foto -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
                    return new ResponseEntity<>(foto, headers, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cliente/{id}/fotos")
    public ResponseEntity<List<FotoCliente>> getFotosClienteJson(@PathVariable Integer id) {
        List<FotoCliente> fotos = imagemService.getFotosCliente(id);
        if (fotos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fotos, HttpStatus.OK);
    }

    @GetMapping("/profissional/{id}/fotos")
    public ResponseEntity<List<FotoProfissional>> getFotosProfissionalJson(@PathVariable Integer id) {
        List<FotoProfissional> fotos = imagemService.getFotosProfissional(id);
        if (fotos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fotos, HttpStatus.OK);
    }

    @PostMapping("/cliente/{id}")
    public ResponseEntity<Void> uploadFotoCliente(@PathVariable Integer id, @RequestParam("foto") MultipartFile file) {
        try {
            imagemService.saveFotoCliente(id, file.getBytes());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/profissional/{id}")
    public ResponseEntity<Void> uploadFotoProfissional(@PathVariable Integer id, @RequestParam("foto") MultipartFile file) {
        try {
            imagemService.saveFotoProfissional(id, file.getBytes());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<byte[]>> getFotosCliente(@PathVariable Integer id) {
        List<FotoCliente> fotos = imagemService.getFotosCliente(id);
        if (fotos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<byte[]> fotosBytes = fotos.stream().map(FotoCliente::getFoto).collect(Collectors.toList());
        return new ResponseEntity<>(fotosBytes, HttpStatus.OK);
    }

    @GetMapping("/profissional/{id}")
    public ResponseEntity<List<byte[]>> getFotosProfissional(@PathVariable Integer id) {
        List<FotoProfissional> fotos = imagemService.getFotosProfissional(id);
        if (fotos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<byte[]> fotosBytes = fotos.stream().map(FotoProfissional::getFoto).collect(Collectors.toList());
        return new ResponseEntity<>(fotosBytes, HttpStatus.OK);
    }
}