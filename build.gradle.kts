import org.gradle.internal.declarativedsl.parsing.main
import sun.jvmstat.monitor.MonitoredVmUtil.mainClass

plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.13"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.jayway.jsonpath:json-path:2.8.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
javafx {
    version= "21"
    modules("javafx.controls", "javafx.fxml")
}
application {
    mainClass.set("edu.bsu.cs.GUI")
}