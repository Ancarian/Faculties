package dev5.chermenin.service.impl;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import dev5.chermenin.service.api.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Override
    public String load(File file) {
        try {
            Map response= cloudinary.uploader().upload(file, Cloudinary.emptyMap());
            return response.get("url").toString();
        }catch (IOException exc){
            return null;
        }
    }
}
