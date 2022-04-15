package com.marinsim.similarity_join.backEnd;

import com.marinsim.similarity_join.backEnd.MyImages.MyImageSift;
import com.marinsim.similarity_join.backEnd.MyImages.MyImageSurf;
import org.apache.commons.io.FileUtils;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnZipper {

    public static String imagePath =System.getProperty("java.io.tmpdir") + "/similiarityjoin/tmp/images/";

    public static ArrayList<MyImage> unzip(MultipartFile multipartFileile, String imgPointExtractionLib) throws IOException {

        File file = new File(System.getProperty("java.io.tmpdir") + "/similiarityjoin/tmp/" + multipartFileile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(multipartFileile.getInputStream(), file);

        ZipFile zip = new ZipFile(file);
        ArrayList<MyImage> ret = new ArrayList<>();

        for (var zEntries = zip.entries(); zEntries.hasMoreElements();  ) {

            ZipEntry zEntry = zEntries.nextElement();

            if (imgPointExtractionLib == "SURF"){
                File img = new File( imagePath + File.separator + zEntry.getName());
                FileUtils.copyInputStreamToFile(zip.getInputStream(zEntry), img);
                ret.add(new MyImageSurf(ImageIO.read(zip.getInputStream(zEntry)), zEntry.getName()));
            } else {
                Mat image = Imgcodecs.imread(imagePath + File.separator + zEntry.getName());
                Mat grayImg = new Mat();

                if (image.empty()) {
                    throw new IllegalArgumentException("Could not read the image: ");
                }

                // Making the picture gray
                Imgproc.cvtColor(image, grayImg, Imgproc.COLOR_BGR2GRAY);

                ret.add(new MyImageSift(grayImg, zEntry.getName()));
            }

        }

        zip.close();
        return ret;

    }
}
