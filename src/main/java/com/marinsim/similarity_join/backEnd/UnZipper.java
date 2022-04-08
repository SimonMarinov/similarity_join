package com.marinsim.similarity_join.backEnd;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnZipper {

    public static String imagePath =System.getProperty("java.io.tmpdir") + "/similiarityjoin/tmp/images/";

    public static ArrayList<MyImage> unzip(MultipartFile multipartFileile) throws IOException {

        File file = new File(System.getProperty("java.io.tmpdir") + "/similiarityjoin/tmp/" + multipartFileile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(multipartFileile.getInputStream(), file);

        ZipFile zip = new ZipFile(file);
        ArrayList<MyImage> ret = new ArrayList<>();

        for (var zEntries = zip.entries(); zEntries.hasMoreElements();  ) {

            ZipEntry zEntry = zEntries.nextElement();

            File img = new File( imagePath + File.separator + zEntry.getName());
            FileUtils.copyInputStreamToFile(zip.getInputStream(zEntry), img);

            ret.add(new MyImage(ImageIO.read(zip.getInputStream(zEntry)), zEntry.getName()));

        }

        zip.close();
        return ret;

    }
}
