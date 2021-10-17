package com.sisvale.sowad.csv;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import com.sisvale.sowad.entity.Plato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
  @Autowired
  DeveloperTutorialRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Plato> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<Plato> tutorials = repository.findAll();

    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
    return in;
  }

  public List<Plato> getAllTutorials() {
    return repository.findAll();
  }
}
