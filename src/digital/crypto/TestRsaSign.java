/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package digital.crypto;


import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class TestRsaSign {
    public static void main(String[] args) throws Exception {
        CryptoUtilImpl cryptoUtil=new CryptoUtilImpl();
        PrivateKey privateKey= cryptoUtil.privateKeyFromJKS("security.jks","123456","security");
        String data="Hello world {}=>";
        String signature = cryptoUtil.rsaSign(data.getBytes(), privateKey);
        String signedDoc=data+"_.._"+signature;
        System.out.println(signedDoc);
        System.out.println("===================================");
        System.out.println("Signature verification");
        String signedDocRecived="Hello world {}=>_.._lIVxSOVA5Y4W1E7hX1fPUJ/bhugqq3iKFbsfTcWyW3wojDyKF5AauCn9oiLcsBZ8LL+LP7r92MMGJCFA6dDDJYR7xjRMEdbyianzFvcKQb2yyi7mQgKvqC9m6K23hGNXeCLRhzo2NBxRqaurJ2UUQPJCY2cliTxyUiEIfVZxrvpzMe4pYXiFgu0jouMNQ0gbwelwpYhpbWgs4Mo89sdIkORU3xDYucmZyiDPysZK16KLy2lQJ8wxmF1nhY3mLeBut1kW5gpx28KEONYjWrZ9+gK9T9rsM8TcoHh70Pm6jCZWKGhzOzlcAk4MP2OLkgnm9G7Q5Z3a0LwkQBGZEegQTw==";
        PublicKey publicKey=cryptoUtil.publicKeyFromCertificate("myCertificate.cert");
        boolean b = cryptoUtil.rsaSignVerify(signedDocRecived, publicKey);
        System.out.println(b?"Signature OK":"Signature Not OK");
    }
}