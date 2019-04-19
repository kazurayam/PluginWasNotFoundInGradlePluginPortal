package com.kazurayam.visualtestinginks.gradle

import java.nio.file.Path
import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.*
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class DependenciesDownloaderPlugin extends Specification {

    @Rule public final TemporaryFolder testProjectDir = new TemporaryFolder()
    
    private File buildFile

    def setup() {
        buildFile = testProjectDir.newFile("build.gradle")
    }

    def "downloadJunit4ks task downloads a jar"() {
        setup:
	    buildFile << '''
                plugins {
                    id 'de.undercouch.download' version '3.4.3'
                }
            '''
	when:
            def result = GradleRunner.create()
	        .withProjectDir(testProjectDir.root)
		.withPluginClasspath()
		.withArguments('downloadJunit4ks')
		.build()
	    Path downloadedJar =  testProjectDir.getRoot().toPath().resolve('Drivers/junit4ks-all.jar')
        then:
	    Files.exists(downloadedJar)
	    result.task(":downloadJunit4ks").outcome == SUCCESS
    }
}