# Sainsbury's Technical Test

## Prerequisite
This makes the assumption that you are using Debian 9 - Stretch. (Though it should would with Debian derivatives.)

## Building
1. Install the following dependencies (running as root):
```sh
# apt update
# apt install openjdk-8-jre openjdk-8-jdk maven git
```

2. Create and move to the folder you want to download this git repo to. For example:
```sh
$ mkdir -p ~/Downloads/sourcecode/
$ cd ~/Downloads/sourcecode/
```

3. Download the sourcecode using git:
```sh
$ git clone https://github.com/paul-sainsburys-test/sainsburys-test.git
```

4. Change into the git repo's folder:
```sh
$ cd ./sainsburys-test
```

5. Compile the program, run the tests and package it into a jar: (Maven automatically downloads the dependencies needed.)
```sh
$ mvn clean test package
```

## Executing the program
1. If you look within the folder `./target` there will be two `.jar` files (`sainsburys-test-0.1.jar ` and `sainsburys-test-0.1-jar-with-dependencies.jar`):
```sh
$ ls ./target
```

2. Run the jar file which contains the dependencies (as the command is simpler) with the webpage you want to scrape items from. For example:
```sh
$ java -jar ./target/sainsburys-test-0.1-jar-with-dependencies.jar "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html"
```

