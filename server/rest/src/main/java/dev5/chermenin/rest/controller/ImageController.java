package dev5.chermenin.rest.controller;


import dev5.chermenin.rest.security.service.AuthenticationServiceImpl;
import dev5.chermenin.service.api.CloudinaryService;
import dev5.chermenin.service.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@CrossOrigin(origins = "${cross.origin}")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/image")
@Api(value = "images", description = "Image controller")
public class ImageController {
    private final CloudinaryService cloudinaryService;
    private final AuthenticationServiceImpl authenticationService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "load image")
    @PostMapping()
    public ResponseEntity<String> post(@RequestParam(value = "upload") MultipartFile aFile) {
        try {
            File f = Files.createTempFile("temp", aFile.getOriginalFilename()).toFile();
            aFile.transferTo(f);
            String url = cloudinaryService.load(f);
            if (url == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            userService.setPhotoToUser(authenticationService.getMyId(), url);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
