package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BackupService {

    public void createBackup(Path sourceDir) throws IOException {
        if (!Files.exists(sourceDir) || !Files.isDirectory(sourceDir)) {
            System.out.println("Source data directory does not exist. Nothing to back up.");
            return;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupRoot = Paths.get("backups");
        Path backupDest = backupRoot.resolve("backup_" + timestamp);
        Files.createDirectories(backupDest);

        try (Stream<Path> walk = Files.walk(sourceDir)) {
            walk.forEach(source -> {
                try {
                    Path destination = backupDest.resolve(sourceDir.relativize(source));
                    if (Files.isDirectory(source)) {
                        if (!Files.exists(destination)) {
                            Files.createDirectory(destination);
                        }
                    } else {
                        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    System.err.println("Unable to copy: " + source);
                }
            });
        }
        System.out.println("Backup created at: " + backupDest);
        System.out.println("Total size of backup: " + calculateDirectorySize(backupDest) + " bytes.");
    }

    public long calculateDirectorySize(Path path) throws IOException {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
        }
    }
}