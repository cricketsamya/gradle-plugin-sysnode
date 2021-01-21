package com.sk.sysnode;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.Properties;
import java.util.logging.Logger;

public class InfoWriter implements Plugin<Project> {
    private final static Logger LOGGER = Logger.getLogger(InfoWriter.class.getName());

    @Override
    public void apply(final Project project) {
        project.task("info", new Action<Task>() {
            @Override
            public void execute(Task task) {
                try {
                    final FileOutputStream fr = new FileOutputStream(project.getProjectDir().getAbsolutePath() + new File("/ms.properties"));
                    final Properties properties = new Properties();
                    String cmd = "git rev-parse --short HEAD";
                    Runtime run = Runtime.getRuntime();
                    Process pr = run.exec(cmd);
                    pr.waitFor();
                    BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    String line = "";
                    properties.put("prj.version", project.getVersion());
                    while ((line = buf.readLine()) != null) {
                        properties.put("prj.revision", line.trim());
                    }
                    properties.put("prj.buildTime", Instant.now().toString());
                    properties.put("core.version", project.findProperty("coreversion"));
                    properties.put("prj.group", project.findProperty("group"));
                    properties.store(fr, "Properties");
                    fr.close();
                } catch (Exception e) {
                    LOGGER.severe("Error while generating ms.properties " + e.getMessage());
                }
            }
        });
    }
}
