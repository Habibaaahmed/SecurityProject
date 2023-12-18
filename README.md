# Security Project
# Project Requirements and Execution Steps

## Requirements

### Java Development Kit (JDK):

Ensure that you have the Java Development Kit (JDK) installed on your system. You can download and install the latest version of the JDK from the official Oracle website or use an alternative like AdoptOpenJDK.

## Execution Steps

1. **Navigate to the Project Directory:**

    Open a terminal or command prompt and navigate to the directory where your project files are located. For example:

    ```bash
    cd path/to/digital-crypto
    ```

2. **Compile the Java Files:**

    Execute the following command to compile the Java source files:

    ```bash
    javac -d . src/digital/crypto/CryptoUtilImpl.java src/digital/crypto/DigitalCrypto.java
    ```

    This command compiles both `CryptoUtilImpl.java` and `DigitalCrypto.java` and places the compiled `.class` files in the current directory (`.`).

3. **Run the Java Program:**

    Run the compiled Java program using the following command:

    ```bash
    java digital.crypto.DigitalCrypto
    ```

    Ensure that the necessary files, such as `myCertificate.cert`, are present in the `digital-crypto` directory.
