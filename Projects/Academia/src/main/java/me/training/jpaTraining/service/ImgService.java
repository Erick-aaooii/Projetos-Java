package me.training.jpaTraining.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import me.training.jpaTraining.forms.AlunoDto;

@Service
public class ImgService {
    // Corrige o caminho base para a pasta imgs
    private final String baseDir = "src/main/resources/static/imgs/";

    public List<String> saveImgs(AlunoDto alunoDto) {
        var imgs = alunoDto.img();
        var dirProductImgName = alunoDto.nome();
        var urls = new ArrayList<String>();

        // Caminho completo para o diretório do produto
        Path productDir = Paths.get(baseDir + dirProductImgName);

        try {
            // Cria o diretório para o produto se não existir
            Files.createDirectories(productDir);

            for (MultipartFile img : imgs) {
                String fileName = img.getOriginalFilename();
                Path filePath = productDir.resolve(fileName);

                // Salva a imagem no diretório do produto
                Files.write(filePath, img.getBytes());

                // Cria a URL pública para acessar a imagem
                String url = "http://localhost:8080/imgs/" + dirProductImgName + "/" + fileName;
                urls.add(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar imagens: " + e.getMessage());
        }

        return urls;
    }

    public void deleteImgs(String nome) {
        Path productDir = Paths.get(baseDir + nome);
        
    }
}