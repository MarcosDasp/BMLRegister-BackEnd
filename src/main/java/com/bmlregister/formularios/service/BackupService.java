package com.bmlregister.formularios.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BackupService {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    private final String dbName = "bmlregister";

    public String criarBackup() throws Exception {

        // Caminho /backups relativo ao projeto
        String backupFolder = "C:\\Program Files\\Microsoft SQL Server\\MSSQL16.SQLEXPRESS\\MSSQL\\Backup";
        String dir = backupFolder + "\\" + dbName;
        new File(dir).mkdirs();

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String arquivo = dir + "\\backup_" + timestamp + ".bak";

        String sql = "BACKUP DATABASE [" + dbName + "] " +
                     "TO DISK = '" + arquivo + "' WITH INIT";

        try (Connection conn = DriverManager.getConnection(dbUrl, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        }

        return arquivo;
    }

    public void restaurarBackup(String filePath) throws Exception {

        // Criar arquivo restore.bat
        String batPath = new File("backups/restore.bat").getAbsolutePath();

        try (FileWriter fw = new FileWriter(batPath)) {

            fw.write(
                "@echo off\n" +
                "sqlcmd -S localhost\\SQLEXPRESS -U " + user + " -P " + password + " -Q \"ALTER DATABASE " + dbName + " SET SINGLE_USER WITH ROLLBACK IMMEDIATE\"\n" +
                "sqlcmd -S localhost\\SQLEXPRESS -U " + user + " -P " + password + " -Q \"RESTORE DATABASE " + dbName + " FROM DISK='" + filePath + "' WITH REPLACE\"\n" +
                "sqlcmd -S localhost\\SQLEXPRESS -U " + user + " -P " + password + " -Q \"ALTER DATABASE " + dbName + " SET MULTI_USER\"\n"
            );
        }

        // Executar o .bat
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", batPath);
        pb.redirectErrorStream(true);
        pb.start();
    }
}
