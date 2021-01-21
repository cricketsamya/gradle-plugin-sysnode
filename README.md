# gradle-plugin-sysnode
This plugin will get you information from the module its being applied. 
e.g. 
```properties
#Properties
prj.version=2021-SNAPSHOT
core.version=2021-SNAPSHOT
prj.buildTime=2021-01-21T09\:40\:47.657460Z
prj.revision=aedc7ea
group=com.example.test

```

```shell
./gradlew publishToMavenLocal 
``` 
-> To publish to local maven repo

How to use
1. Add this to build.gradle
```gradle
buildscript {
    repositories {
        mavenLocal()
        dependencies {
            classpath 'com.sk:sysnode:1.0.0'
        }
    }
}

apply plugin: 'com.sk.sysnode.plugin'
```
