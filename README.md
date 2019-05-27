# WarApp
WarApp: A Desktop Application Container for Web Application WAR Files


There are technologies that are able to run a web application as a desktop application, a famous example is the electron.js, however this tool has focus on javascript applications.
The need however, was a way to run a java web application as a desktop application. In a search I found someone who had found a way to run the application by encapsulating it in a Java SE application. [David Gotz](https://github.com/davegotz), shows step by step a way for this to be possible in this repository: [WarApp](https://github.com/VACLab/WarApp).
David uses JxBrowser, which runs a web browser, however the same is paid and I decided to look for a free version of some tool that would give me something similar.

I copied much of the readme of your project :stuck_out_tongue_winking_eye:, after all I made a few changes. Thank's David :clap:.

## Step One: Build Your Web Application ##
The first step is to build your web application and package it as a WAR file. The good news is that you don’t need to do anything special for this step. You can build it for the web, deploy it with Tomcat, debug it in your web browser. All the same steps you’d normally take for a traditional Java-based web application.


## Step Two: Get the Tomcat webapp-runner Library ##
To begin creating your desktop app, next want to download the webapp-runner library created by John Simone. You should grab it as a jar, which I downloaded directly from the Maven website.

The webapp-running jar is designed to let you launch Tomcat with a specific WAR file from the command line, without having to install Tomcat first. This is useful on its own, but it’s really a critical part of running your WAR file into a desktop ap

## Step Three: Create a New Java Application that “Wraps” your WAR File ##
Believe it or not, we’re almost done. The next step is to code up a very small single-file Java application that will serve as a “wrapper” around your WAR file. It will bundle the WAR file, with the Tomcat webapp-runner library, together with the JavaFx WebView component into a single Java application.

I created a new Java project in my IDE, then created a subdirectory called “war” into which I copied the WAR file I created in step one. Then I created a subdirectory called “lib” in which I placed the JxBrowser and webapp-runner jars.

Finally, I created a new Java class which I've placed in this github repo: WarApp.java. You'll see that it's pretty simple. Just one big main() method within which there are four high-level steps.

The code has just a few key steps:

The war file is launched on a locally-running server using exec.
A JavaFx Java desktop application is created with just a single WebView as the only UI component.
A listener is added to the Java application to detect when the program is closed. In that callback (which only gets called when the app is closed), the locally-running Tomcat server is terminated.
The final step is to point the browser to the local web server you just started and make the parent Swing frame visible to the user.
The port and WAR filename are hardcoded as local variables in the main method. You could, if you wanted to make the code more configurable, put these values into a configuration file. That would make this a truly generic “wrapper” that could be re-used for any WAR file without rebuilding.

## Step Four: Bundle the Java Application for Your Operating System ## 
Your software will run locally after step 4. However, it will feel like a Java application, not a native app. To get over this last hurdle, you can use the Oracle Appbundler. I followed these instructions to build a native-looking OS X app for my Macbook Pro. It sits on the Dock, has its own icon, and come bundled with its own JRE so it runs even on laptops without Java installed.


***
This worked for me, I hope it helps you in some way, just as David's project helped me.
