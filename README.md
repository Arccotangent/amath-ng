# amath-ng
A command line calculator for many different uses, from the basic to the extreme, and more to come.

At first, amath-ng (at that time I called it MCTC01) was a small Java program I created in math class in the beginning of my freshman year of high school to help me with problems I didn't fully understand. The first few versions of MCTC01 were never uploaded to the internet until it was ported to C++ and renamed to amath.

Over time, I added every new topic I learned in any math class that I took to amath-ng, and I plan to continue to do so.

My ultimate goal here is to create a program capable of solving many different types of problems while showing any appropriate work.

## Dependencies

+ Apfloat 1.8.2
+ JSON 20160212
+ Apache Commons Lang 3.4
+ Java Runtime Environment (JRE) 7 or later

Non-JRE dependencies are managed by Gradle, meaning you only have to have Java installed.

## C++ Version

The old discontinued C++ version of amath-ng can be found here: https://github.com/Arccotangent/amath-ng-cpp

But be aware that a fair amount of bugs have been found in the C++ version that are fixed in the Java version.

## Building

Navigate to the source directory.

Windows: `gradlew build`

Linux: `./gradlew build`

## Running

The built jar file can be found in the build/libs directory. Its name is `amath-ng-VERSION.jar` with VERSION being the release version.

The built jar is portable. Once the jar is built, only a compatible JRE is needed to run it.

From the source directory (all platforms): `java -jar build/libs/amath-ng-VERSION.jar <operation> <arguments>`

To run from any directory (Linux only):

```
sudo ./install
amath-ng
```

### Examples

The jar is in the working directory and has been renamed to amath-ng.jar

Multiplying i * i: `java -jar amath-ng.jar mul i i` returns -1

Adding 2+3i + 5+4i: `java -jar amath-ng.jar add 2+3i 5+4i` returns 7 + 7i

### Argument Format & Complex Numbers

amath-ng can take complex numbers as arguments in the form of -a+bi, -a-bi, a+bi, a-bi, bi, -bi, i, and -i without spaces. Do not put a plus sign in front of the real part of a complex number if it is positive. This is not supported by amath-ng.

Examples of valid arguments: `-2+3i -2-3i 2+3i 2-3i 3i -3i i -i`

Examples of invalid arguments: `+2+3i +2-3i 2+-3i 2-+3i`

Any quoted arguments with spaces will throw an exception. Any unquoted arguments with spaces will be treated as 2 separate arguments.

### Constants

Currently, two constants are built in to amath-ng. These constants can be passed to amath-ng as arguments.

e - 2.718281828...

pi - 3.14159265358979...

## Configuration

amath-ng now has a configuration file that can be edited to change the answer precision in significant figures and the certainty of the primality test. The configuration data is stored in JSON format.

Example location of configuration file for user 'arccotangent':

Windows: `C:/Users/arccotangent/amath-ng.conf`

Linux: `/home/arccotangent/amath-ng.conf`

The default config will look like this:

```
{
    "precision": 200,
    "certainty": 100
}
```

precision = Number precision in significant figures

certainty = Certainty on the prime test. Higher values yield more certainty at the cost of more processing power

### Answer precision

amath-ng's mathematics library (Apfloat) handles answer precision differently as compared to Boost/GMP. Answer precision is strictly defined as the value in significant figures in the config.

## To Do List

Here is a list of features to be added, bugs to be fixed, etc

+ Add unit conversions (ex. inch to cm, liter to gallon, celsius to fahrenheit)
+ Show formulas where appropriate
+ Show work where appropriate

## Contributing

You can contribute to the development of amath-ng by opening an issue on the GitHub issue tracker or by submitting a pull request.

## Why Port to Java?

+ To eliminate the problem that stood with cross-compilation. Some of the libraries required by the old C/C++ version were not easy to compile for other platforms, particularly Windows. Given only a Linux system, compiling for Windows is very time consuming.
+ To make building easier. Gradle can handle dependencies on its own while the old build system cannot, so users were forced to install dependencies on their own. The old build system was only compatible with \*nix-like operating systems such as Linux, while Gradle is compatible with both Windows and Linux.
+ To make the program more portable. The executable file built was a native executable. A native executable file is only compatible with the CPU architecture and the operating system it's built for. Java is compatible with all major operating systems and most if not all CPU architectures.
+ To allow for more functionality. The Apfloat library can handle complex numbers (a + bi), while Boost/GMP cannot. The user can customize the precision and certainty in a file in JSON format. This file is read every time amath-ng is executed.
