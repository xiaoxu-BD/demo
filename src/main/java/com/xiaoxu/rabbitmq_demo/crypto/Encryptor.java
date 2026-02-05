package com.xiaoxu.rabbitmq_demo.crypto;

public interface Encryptor {
    String encrypt(String plainText);

    String decrypt(String cipherText);
}
