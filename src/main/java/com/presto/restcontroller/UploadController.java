package com.presto.restcontroller;

import com.presto.model.Imagem;
import com.presto.model.Produto;
import com.presto.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("image")

public class UploadController {
    @Autowired
    ImagemRepository imagemRepository;

    private static String UPLOADER_FOLDER = "C:/Users/charlinho/Documents/UNIFOR/ADS/PA 2/ProjetoPresto Front/ProjetoPresto/Angular/Presto/src/assets/imagens/";

    @GetMapping("/images")
    public String index() {
        return "upload";
    }

    @PostMapping("/create")
    public String singleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select file to upload");
            return "Arquivo vazio";
        }
        try {
            Imagem imagem = new Imagem();
            byte[] bytes = file.getBytes();
            imagem.setBytes(bytes);
            Path path = Paths.get(UPLOADER_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            imagemRepository.save(imagem);

            redirectAttributes.addFlashAttribute("message", "You succesfully uploaded" + file.getOriginalFilename() + "'");
            return "Upload bem sucedido";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Retorno depois do catch";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
