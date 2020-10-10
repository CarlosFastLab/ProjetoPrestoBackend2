package com.presto.service;

import org.springframework.http.ResponseEntity;

public interface ImageService {

    byte[] compressBytes(byte[] data);
    byte[] decompressBytes(byte[] data);

}
