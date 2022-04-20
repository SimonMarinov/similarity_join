package com.marinsim.similarity_join.controllers;

import com.marinsim.similarity_join.backEnd.Values;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.marinsim.similarity_join.backEnd.UnZipper;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Controller for images
 */
@Controller
@RequestMapping(value = "images/")
public class ImageController {

    @GetMapping("{imgName}")
    public void getImage(HttpServletResponse response, @PathVariable String imgName) throws IOException {

        imgName = imgName.replaceAll("&","/");

        File img = new File(Values.IMG_PATH + imgName);
        //System.out.println(img.getAbsolutePath());
        InputStream in = new FileInputStream(img);
        OutputStream out = response.getOutputStream();
        IOUtils.copy(in,out);
        in.close();
        out.close();
    }


}
