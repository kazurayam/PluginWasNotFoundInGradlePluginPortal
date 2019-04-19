package com.kazurayam.visualtestinginks.gradle

import java.nio.file.Path
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class DependenciesDownloaderPlugin implements Plugin<Project> {

    private void applyPlugins(Project project) {
        project.getPlugins().apply('de.undercouch.download')
    }

    public void apply(Project project) {
        applyPlugins(project)
        Task downloadJunit4ks = project.getTasks().create('downloadJunit4ks') {
            doLast {
                Path dir = projectDir.toPath().resolve('Drivers')
                Path file = dir.resolve('junit4ks-all.jar')
                Files.createDirectories(dir)
                download {
                    src 'https://github.com/kazurayam/junit4ks/releases/download/1.6/junit4ks-all.jar'
	            dest file.toFile()
	            overwrite true
                }
	        assert Files.exists(file)
	    }
	}
    }
}
