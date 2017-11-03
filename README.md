# FileEncryption

A JavaFX Application that encrypts a file using AES encryption.

## Preview

![First screenshot of the application](https://raw.githubusercontent.com/luisbraganca/file-encryption/master/Screenshots/preview1.png)

![Second screenshot of the application](https://raw.githubusercontent.com/luisbraganca/file-encryption/master/Screenshots/preview2.png)

![Third screenshot of the application](https://raw.githubusercontent.com/luisbraganca/file-encryption/master/Screenshots/preview3.png)

## Technical details

This application allows you to encrypt a file using the [AES](https://en.wikipedia.org/wiki/Advanced_Encryption_Standard) encryption algorithm, asking you for a password to use it as its key. During the file transformation process, it's guaranteed that no data is lost: It's never the original file that is encrypted but a copy of it. And during the name changes, if there's already a file with that same name, a new one is used. For example:
* We have the file: Example.pdf
* If we encrypt it, it becomes. Example.pdf.enc
* Now if we decrypt it back, it won't replace the original Example.pdf, it will create a new one named "(1) Example.pdf", incrementing the number inside the parentheses untill it finds an unused file name.

### Functionalities

* File encryption (or safely cancel it without crashing the application)
* File decryption (or safely cancel it without crashing the application)
* Select the file using a file chooser
* Friendly graphical user interface showing the progress of the task

## Getting started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

It's highly recommended that you edit this project using IntelliJ IDEA from JetBrains that can be downloaded [here](https://www.jetbrains.com/idea/) since it was developed using that same tool.

### Resources

* Icon

The icon used was made by:
[Fabián Alexis](https://github.com/fabianalexisinostroza/Antu) [CC BY-SA 3.0](https://creativecommons.org/licenses/by-sa/3.0), via Wikimedia Commons

## Notes

If you're only looking for the encryption/decryption code without the user interface, you can find it [here](https://github.com/luisbraganca/file-encryption/blob/master/file-encryption/src/security/FileEncryption.java), fully commented.
Usage example:
```
new FileEncryption("Example.pdf", "MyPaSsWoRd!123", FileEncryption.ENCRYPT_MODE).start();
```
The rest of the project isn't that much commented but it's mainly JavaFX operations.

## Authors

* **Luís Bragança Silva** - *Initial work*
