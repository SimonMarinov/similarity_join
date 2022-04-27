package com.marinsim.similarity_join.controllers;

import com.marinsim.similarity_join.backEnd.*;
import com.marinsim.similarity_join.backEnd.ImgComparators.EuclideanComparator;
import com.marinsim.similarity_join.backEnd.ImgComparators.SqfdComparator;
import com.marinsim.similarity_join.backEnd.simFunctions.GuassianFunction;
import com.marinsim.similarity_join.backEnd.simFunctions.HeuristicFunction;
import com.marinsim.similarity_join.backEnd.simFunctions.MinusFunction;
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
    public String displayDefaultScreen(ModelMap modelMap) {
        return "default";
    }

    @PostMapping
    public String showResult(
            ModelMap model,
            @RequestParam("images1") MultipartFile imgZip1,
            @RequestParam("images2") MultipartFile imgZip2,
            @RequestParam("resType") String resType,
            @RequestParam("resValue") int resVal,
            @RequestParam("nofClusters") int nofClusters,
            @RequestParam("nofIter") int nofIter,
            @RequestParam("compType") String compType,
            @RequestParam("funType") String funType,
            @RequestParam("alphaConst") Double alhaConst,
            @RequestParam("disLimPer") Double distanceLimitPer,
            @RequestParam("libType") String libTipe
    ) {
        Values values = new Values(nofClusters, nofIter, alhaConst, distanceLimitPer);

        ArrayList<MyImage> imgs1;
        ArrayList<MyImage> imgs2;

        try {
            ImgComparator comparisonFun;
            imgs1 = UnZipper.unzipMult(imgZip1, libTipe);
            imgs2 = UnZipper.unzipMult(imgZip2, libTipe);

            if (compType.equals("SQFD")) {
                if (funType.equals("gaussian")) {
                    comparisonFun = new SqfdComparator(new GuassianFunction());
                } else if (compType.equals("heuristic")) {
                    comparisonFun = new SqfdComparator(new HeuristicFunction());
                } else {
                    comparisonFun = new SqfdComparator(new MinusFunction());
                }
            } else {
                comparisonFun = new EuclideanComparator();
            }

            PicturesComparator comparator = new PicturesComparator(imgs1, imgs2, comparisonFun);
            var result = comparator.getComparisons(resType, resVal);

            model.addAttribute("result", result);


        } catch (IOException e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "error";
        }


        return "result";
    }

}
