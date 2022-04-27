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

    public static ArrayList<MyImage> unzipMult(MultipartFile multipartFileile, String imgPointExtractionLib) throws IOException {
        File file = new File(Values.IMG_PATH + multipartFileile.getOriginalFilename());
        file.deleteOnExit();
        FileUtils.copyInputStreamToFile(multipartFileile.getInputStream(), file);
        return unzip(file, imgPointExtractionLib);
    }

    public static ArrayList<MyImage> unzip(File file, String imgPointExtractionLib) throws IOException {
        if (imgPointExtractionLib.equals("SIFT")) {
            nu.pattern.OpenCV.loadShared();
        }

        ZipFile zip = new ZipFile(file);
        ArrayList<MyImage> ret = new ArrayList<>();

        for (var zEntries = zip.entries(); zEntries.hasMoreElements(); ) {

            ZipEntry zEntry = zEntries.nextElement();
            File img = new File(Values.IMG_PATH + zEntry.getName());
            img.deleteOnExit();
            if (zEntry.isDirectory()) continue;

            FileUtils.copyInputStreamToFile(zip.getInputStream(zEntry), img);

            if (imgPointExtractionLib.equals("SURF")) {

                ret.add(new MyImageSurf(ImageIO.read(zip.getInputStream(zEntry)), zEntry.getName()));
            } else {
                Mat image = Imgcodecs.imread(img.getPath());

                if (image.empty()) {
                    throw new IllegalArgumentException("Could not read the image: ");
                }


                ret.add(new MyImageSift(image, zEntry.getName()));
            }

        }

        zip.close();
        return ret;

    }
}
