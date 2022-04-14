package com.marinsim.similarity_join.controllers;

import com.marinsim.similarity_join.backEnd.*;
import com.marinsim.similarity_join.backEnd.ImgComparators.EuclideanComparator;
import com.marinsim.similarity_join.backEnd.ImgComparators.SqfdComparator;
import com.marinsim.similarity_join.backEnd.simFunctions.GuassianFunction;
import com.marinsim.similarity_join.backEnd.simFunctions.HeuristicFunction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @GetMapping
    public String displayMainScreen(ModelMap modelMap) {return "kkt";}

    @PostMapping
    public String showResult(
            ModelMap model,
            @RequestParam("images1") MultipartFile imgZip1,
            @RequestParam("images2") MultipartFile imgZip2,
            @RequestParam("minSimPer") Double minPer,
            @RequestParam("nofClusters") int nofClusters,
            @RequestParam("nofIter") int nofIter,
            @RequestParam("compType") String compType,
            @RequestParam("funType") String funType,
            @RequestParam("alphaConst") Double alhaConst,
            @RequestParam("disLim") Double distanceLimit
            )
    {

        Values values = new Values(nofClusters, nofIter, alhaConst, minPer, distanceLimit);

        ArrayList<MyImage> imgs1;
        ArrayList<MyImage> imgs2;

        try {
            ImgComparator comparisonFun;
            SimilarityFunction fun;
            imgs1 = UnZipper.unzip(imgZip1);
            imgs2 = UnZipper.unzip(imgZip2);

            if (compType.equals("SQFD")){
                if (funType.equals("Gaussian")) {
                    comparisonFun = new SqfdComparator(new GuassianFunction());
                } else {
                    comparisonFun = new SqfdComparator(new HeuristicFunction());
                }
            } else {
                comparisonFun = new EuclideanComparator();
            }

            PicturesComparator comparator = new PicturesComparator(imgs1, imgs2, comparisonFun);
            var result = comparator.getComparisons();

            model.addAttribute("result", result);



        } catch (IOException e) {
            e.printStackTrace();
            return "errorScreen";
        }


        return "ResultScreen";
    }

}
