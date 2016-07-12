# amath-ng
A command line calculator for many different uses, from the basic to the extreme, and more to come.

## Dependencies

+ Apfloat 1.8.2
+ JSON 20160212
+ Apache Commons Lang 3.4
+ Java Runtime Environment (JRE) 7 or later

Non-JRE dependencies are managed by Gradle, meaning you only have to have Java installed.

## Building

Windows: `gradlew build`

Linux: `./gradlew build`

## Running

The built jar file can be found in the build/libs directory. Its name is `amath-ng-VERSION.jar` with VERSION being the release version.

The built jar is portable. Once the jar is built, only a compatible JRE is needed to run it.

## Configuration

amath-ng now has a configuration file that can be edited to change the answer precision in significant figures and the certainty of the primality test. The configuration data is stored in JSON format.

Example location of configuration file for user 'arccotangent':

Windows: `C:/Users/arccotangent/amath-ng.conf`

Linux: `/home/arccotangent/amath-ng.conf`

## Contributing

You can contribute to the development of amath-ng by opening an issue on the GitHub issue tracker or by submitting a pull request.

## Why Port to Java?

+ To eliminate the problem that stood with cross-compilation. Some of the libraries required by the old C/C++ version were not easy to compile for other platforms, particularly Windows. Given only a Linux system, compiling for Windows is very time consuming.
+ To make building easier. Gradle can handle dependencies on its own while the old build system cannot, so users were forced to install dependencies on their own. The old build system was only compatible with \*nix-like operating systems such as Linux, while Gradle is compatible with both Windows and Linux.
+ To make the program more portable. The executable file built was a native executable. A native executable file is only compatible with the CPU architecture and the operating system it's built for. Java is compatible with all major operating systems and most if not all CPU architectures.
+ To allow for more functionality. The Apfloat library can handle complex numbers (a + bi), while Boost/GMP cannot. The user can customize the precision and certainty in a file in JSON format. This file is read every time amath-ng is executed.
