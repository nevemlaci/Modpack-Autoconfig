# Modpack Autoconfig
A tool for automatically generating config files and kubejs scripts for Minecraft modpacks.

## Currently supported features:

* New GregTech materials(Dusts/Ingots, Wires, Blast Furnace recipes)



## Build
* Clone the repository
* Run `./gradlew build` in the root directory of the repository
* The jar file will be in `build/libs/`

#### Requirements:
* Java 22
* The tool is made for Minecraft 1.20.1
* KubeJS installed in the modpack

Older Java versions may work, but Java 22 was used during development.

## Usage
* The program first prompts you to select a working directory. This should be your .minecraft folder.
