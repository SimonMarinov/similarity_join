import com.marinsim.similarity_join.backEnd.MyImage;
import com.marinsim.similarity_join.backEnd.Pair;
import com.marinsim.similarity_join.backEnd.UnZipper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args){
        List<File> test = new ArrayList<>();
        test.add(new File("src/test/resources/test.zip"));
        test.add(new File("src/test/resources/test_grey.zip"));
        test.add(new File("src/test/resources/test_rot_10.zip"));
        test.add(new File("src/test/resources/test_rot_90.zip"));
        test.add(new File("src/test/resources/test_rot_180.zip"));

       // System.out.println(file.exists());
        nofKeypoints(test);


    }

    static void nofKeypoints( List<File> test){
        try {
            List<Pair<List<MyImage>, List<MyImage>>> res = new ArrayList<>();
            for (var t: test) {
                Pair<List<MyImage>, List<MyImage>>pair = new Pair<>(UnZipper.unzip(t,"SURF"), UnZipper.unzip(t,"SIFT"));
                res.add(pair);
            }

            List<Pair<Integer,Integer>> total = new ArrayList<>();
            for ( var r : res) {
                int t =0;
                int t2=0;
                for (int i = 0; i < 10; i++) {
                    r.first.get(i).calculatePoints();
                    r.second.get(i).calculatePoints();
                    t += r.first.get(i).nofExtractedPoints();
                    t2 += r.second.get(i).nofExtractedPoints();
                }
                total.add(new Pair<>(t,t2));
            }

            int nofLines=10;
            int nofFiles=5;

            System.out.print("test.zip;");
            System.out.print("grey;");
            System.out.print("rot 10;");
            System.out.print("rot 90;");
            System.out.print("rot 180;");
            System.out.println();

            System.out.print(";");
            for (var t:test) {
                System.out.print("surf;sift;");
            }
            System.out.println();

            for (int i = 0; i < nofLines; i++) {
                System.out.print(i+1 +".jpeg;");
                for (int j = 0; j < nofFiles ; j++) {
                    var n =res.get(j).first.get(i).nofExtractedPoints();
                    var n2 = res.get(j).second.get(i).nofExtractedPoints();
                    System.out.print(n + ";"+n2 + ";");
                }
                System.out.println();
            }
            System.out.print("total;");
            for (var t: total) {
                System.out.print(t.first + ";" + t.second+";");
            }





        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
