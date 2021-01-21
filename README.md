# gradle-plugin-sysnode

./gradlew publishToMavenLocal -> To publish to local maven repo

How to use
1. Add this to build.gradle
buildscript {
    repositories {
        mavenLocal()
        dependencies {
            classpath 'com.sk:sysnode:1.0.0'
        }
    }
}

apply plugin: 'com.sk.sysnode.plugin'
