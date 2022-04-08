package com.marinsim.similarity_join.controllers;

import com.marinsim.similarity_join.backEnd.MyImage;
import com.marinsim.similarity_join.backEnd.PictureComparator;
import com.marinsim.similarity_join.backEnd.SimilarityFunction;
import com.marinsim.similarity_join.backEnd.UnZipper;
import com.marinsim.similarity_join.backEnd.simFunctions.GuassianFunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class MainController {

    @GetMapping("/")
    void helloWorld(){
        System.out.println("Hello");
    }

    @PostMapping
    public String showResult(
            @RequestParam("images1") MultipartFile imgZip) {


        ArrayList<MyImage> imgsToComp;



        try {
            SimilarityFunction fun = new GuassianFunction();
            imgsToComp = UnZipper.unzip(imgZip);
            PictureComparator comparator = new PictureComparator(imgsToComp,fun);
            var result = comparator.getComparisons();



        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/result";
    }

}
