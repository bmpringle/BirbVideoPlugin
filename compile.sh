mkdir -p build
mkdir -p build/plugin

javac -cp spigot-1.17.1.jar -d build *.java
cp plugin.yml ./build/plugin.yml

cd build; jar cf plugin/BirbVideoPlugin.jar *

