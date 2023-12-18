/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package digital.crypto;

public class TestSignature {
    public static void main(String[] args) throws Exception {
        CryptoUtilImpl cryptoUtil=new CryptoUtilImpl();
        String secret="security";
        String document="Hello security project";
        String signature = cryptoUtil.hmacSign(document.getBytes(), secret);
        String signedDocument=document+"_.._"+signature;
        System.out.println(signedDocument);
        System.out.println("===========================");
        String signedDoc="Hello security project_.._uxO5DrsiiADc423+I7CrCj2nb8PfpUVcUhLR4ncvOpw=";
        String sec="security";
        System.out.println("Signature verification");
        boolean signatureVerifResult = cryptoUtil.hmacVerify(signedDoc, secret);
        System.out.println(signatureVerifResult==true?"Signature OK":"Signature Not OK");

    }
}