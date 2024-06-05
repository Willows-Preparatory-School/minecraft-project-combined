[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/liZ3sWt9)

## README!!!
NOTE: this probably isn't up to date, so before you do anything, ask me for an updated copy. <br>
so i kinda didn't use the TEALS mod loader for most of my items/blocks/entites/etc, <br>
cause i wanted more freedom with modding. <br>
so im going to just provide my entire project file. uhhhh, it shouldn't be that hard to figure out how to use it, <br>
but ill provide info below! <br>
1. Clone the repo <br>
2. Open the "PROJECT" folder in intellij <br>
3. it should show up as a gradle project <br>
4. it should run automatically, if not run "build.gradle" <br>
5. if you need more help, just ask me. <br>
<br>
ALSO!!! <br>
i had to paste the root project files into the root github directory, <br>
sorry if it looks messy, there really wasn't a way around this.

## Extra info
### What are mixins/asm?
Mixins are a way of modifying minecraft's code within a mod, for example I could hook into ItemBookEditable, <br>
and print a message everytime a book is opened. Or I could change the content of functions. <br>
ASM is similar to mixins, but not the same. ASM is older and was used before mixins were created, <br>
it does the same thing mixins do, but is less compatible between mods and Minecraft versions. <br>
Mixins let forge handle all of the injection, but ASM requires you yourself to handle all the Java bytcode <br>
modification, which can cause issues between mods. ASM is considered legacy now and mixins are the prefered <br>
method now. <br>

# Minecraft-Project
The template for GitHub classroom for the open-ended project submissions.

Submit your files with thorough documentation. Don't forget to register your changes in the corresponding module class (included in this comment). Don't add unnecessary files from the labs; solely add your summative concepts and their registration.
