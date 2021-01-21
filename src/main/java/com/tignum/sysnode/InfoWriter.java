package com.tignum.sysnode;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class InfoWriter implements Plugin<Project> {
    /*
    /*class InfoWriter implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.with {
            task("info", description: "Gets Sysnode info", group: "other") {
                doLast {
                    new File("$buildDir/resources/main/ms.properties").withWriter { w ->
                        def cmd = "git rev-parse --short HEAD"
                        def proc = cmd.execute()
                        ext.revision = proc.text.trim()
                        ext.timestamp = (int) (new Date().getTime() / 1000)

                        Properties p = new Properties()
                        p['prj.version'] = project.version.toString()
                        p['prj.buildTime'] = Instant.now().toString()
                        p['prj.revision'] = ext.revision.toString()
                        def dateUnix = ext.timestamp
                        Date dateObj = new Date(((long) dateUnix) * 1000)
                        def cleanDate = new SimpleDateFormat('yyyy-MM-dd HH:mm').format(dateObj)
                        p['prj.revisionTime'] = cleanDate

                        p.store w, null
                    }
                }
            }
        }
    }
}*/
    @Override
    public void apply(Project project) {
        Task task = project.getTasks().create("info");
        task.doLast(new Action<Task>() {
            @Override
            public void execute(Task task) {
                try {
                    System.out.println(project.getBuildDir().getAbsolutePath());
                    final Properties properties = new Properties();
                    properties.put("prj.version", project.getVersion());
                    final FileOutputStream fr = new FileOutputStream(new File(project.getBuildDir().getAbsolutePath() + "/ms.properties"));
                    properties.store(fr, "Properties");
                    fr.close();
                } catch (Exception e) {
                    System.out.println("ERROR : " + e.getMessage());
                }
                System.out.println(project.getVersion());
            }
        });
    }
}
