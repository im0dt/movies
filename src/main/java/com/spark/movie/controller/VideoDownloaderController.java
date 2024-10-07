package com.spark.movie.controller;

import com.spark.movie.model.Movie;
import com.spark.movie.service.VideoDownloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/videoDownloader")
public class VideoDownloaderController {
    @Autowired
    VideoDownloaderService videoDownloaderService;

    @GetMapping
    public ResponseEntity<Void> downloadAllMoviesVideos() {
        videoDownloaderService.downloadVideos();
        return ResponseEntity.noContent().build();
    }
}
