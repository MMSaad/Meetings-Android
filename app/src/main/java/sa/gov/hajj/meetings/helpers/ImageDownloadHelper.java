package sa.gov.hajj.meetings.helpers;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloadHelper {
    public Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap getLocalImage(String id) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/images/" + id + ".jpg");
        if (myDir.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return BitmapFactory.decodeFile(myDir.getAbsolutePath(), options);
        } else
            return null;
    }

    public boolean SaveImage(Bitmap finalBitmap, String id) {
        if (finalBitmap != null) {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/images");
            myDir.mkdirs();
            String fname = id + ".jpg";
            File file = new File(myDir, fname);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
