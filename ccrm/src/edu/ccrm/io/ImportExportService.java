package edu.ccrm.io;

import edu.ccrm.domain.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class ImportExportService {

    private final Path dataDirectory = Paths.get("data");

    public ImportExportService() {
        try {
            Files.createDirectories(dataDirectory);
        } catch (IOException e) {
            System.err.println("Could not create data directory.");
        }
    }

    public void exportStudents(List<Student> students) throws IOException {
        Path filePath = dataDirectory.resolve("students_export.csv");
        List<String> lines = students.stream()
                .map(Student::toString)
                .collect(Collectors.toList());

        Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Successfully exported " + students.size() + " students to " + filePath);
    }
}