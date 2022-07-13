plugins {
    java
    application
}

val vertxVersion = "4.3.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
    implementation("io.vertx:vertx-core")
    implementation("io.vertx:vertx-rx-java2")
    implementation("io.vertx:vertx-jdbc-client")
    runtimeOnly("mysql:mysql-connector-java:8.0.29")
}

application {
    mainClass.set("vertx.JdbcClientTest")
}
