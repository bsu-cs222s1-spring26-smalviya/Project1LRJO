plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "edu.bsu.cs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    version = "17.0.2"
    modules("javafx.controls", "javafx.fxml")
}
dependencies {
    implementation("com.jayway.jsonpath:json-path:2.8.0")

    implementation("org.openjfx:javafx-controls:17.0.2")

    implementation("org.openjfx:javafx-fxml:17.0.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
application{
    mainClass.set("edu.bsu.GUI")

}