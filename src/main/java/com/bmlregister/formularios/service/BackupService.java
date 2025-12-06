package com.bmlregister.formularios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
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

    @Autowired
    private HikariDataSource dataSource;

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

        // 2 — Trocar DB para master
        String masterUrl = dbUrl
                .replace("databaseName=" + dbName, "databaseName=master");

        // 3 — Escapar corretamente as barras para SQL Server
        String pathSql = filePath
                .replace("\n", "")
                .replace("\r", "")
                .replace("\t", "")
                .trim()
                .replace("\\", "\\\\");

        System.out.println("[RESTORE] Caminho convertido: " + pathSql);

        try (Connection conn = DriverManager.getConnection(masterUrl, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.execute("ALTER DATABASE " + dbName + " SET SINGLE_USER WITH ROLLBACK IMMEDIATE");
            stmt.execute("RESTORE DATABASE " + dbName + " FROM DISK = N'" + pathSql + "' WITH REPLACE");
            stmt.execute("ALTER DATABASE " + dbName + " SET MULTI_USER");
        }

        // 4 — Recria o pool
        dataSource.getHikariConfigMXBean().setMinimumIdle(1);
        dataSource.getHikariConfigMXBean().setMaximumPoolSize(10);
    }
}
