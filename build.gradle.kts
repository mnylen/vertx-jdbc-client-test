plugins {
    java
    application
}

val vertxVersion = "3.9.13"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
    implementation("io.vertx:vertx-core")
    implementation("io.vertx:vertx-rx-java2")
    implementation("io.vertx:vertx-jdbc-client")
    implementation("io.vertx:vertx-sql-common")
    runtimeOnly("mysql:mysql-connector-java:8.0.29")
}

application {
    mainClass.set("vertx.JdbcClientTest")
}
