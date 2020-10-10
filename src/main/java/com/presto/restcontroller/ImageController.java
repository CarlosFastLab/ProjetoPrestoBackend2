package com.presto.restcontroller;

import com.presto.model.ImageModel;
import com.presto.repository.ImageRepository;
import com.presto.service.ImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageServiceImp imageServiceImp;

    @PostMapping("/create")
    public ResponseEntity.BodyBuilder uploadImagem(@RequestParam("imageFile") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(), imageServiceImp.compressBytes(file.getBytes()));
        imageRepository.save(img);
        return ResponseEntity.status(HttpStatus.OK);
    };

    @GetMapping("/gettipo/{imageName}")
    public ResponseEntity<ImageModel> getImage(@PathVariable("imageName") String imageName) throws IOException {
        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(), imageServiceImp.decompressBytes(retrievedImage.get().getPicByte()));
        return new ResponseEntity<>(img, HttpStatus.OK);
    };

}
