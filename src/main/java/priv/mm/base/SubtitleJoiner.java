package priv.mm.base;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 一个简单实用的小工具，可以把多张视频截图的底部字幕，按序拼接到第一张图片底下。
 *
 * @author maodh
 * @since 2018/9/3
 */
public class SubtitleJoiner {
    private static final String outputImagePath = "/Users/maomao/Desktop/image_final.png";

    public static void main(String[] args) throws IOException {
        // 指定字幕高度
        final int subtitleHeight = 120; // 单位 px

        // 指定视频截图
        String[] filePaths = new String[]{"/Users/maomao/Desktop/1.png", "/Users/maomao/Desktop/2.png", "/Users/maomao/Desktop/3.png", "/Users/maomao/Desktop/4.png", "/Users/maomao/Desktop/5.png"};
        ArrayList<File> fileList = Arrays.stream(filePaths).map(File::new).collect(Collectors.toCollection(ArrayList::new));

        // TODO 安全校验
        // 1. 图片类型判断：后缀名 && 魔术
        // 2. 图片大小 && 尺寸判断

        BufferedImage firstImage = ImageIO.read(fileList.get(0));

        final int finalImageWidth = firstImage.getWidth();
        final int finalImageHeight = firstImage.getHeight() + (fileList.size() - 1) * subtitleHeight;
        BufferedImage finalImage = new BufferedImage(finalImageWidth, finalImageHeight, firstImage.getType());

        // 合并第一张图片
        finalImage.createGraphics().drawImage(firstImage, 0, 0, null);
        for (int i = 0; i < fileList.size(); i++) {
            if (i == 0) continue;

            File thisFile = fileList.get(i);
            BufferedImage thisImage = ImageIO.read(thisFile);
            BufferedImage subtitle = thisImage.getSubimage(0, thisImage.getHeight() - subtitleHeight, thisImage.getWidth(), subtitleHeight);
            finalImage.createGraphics().drawImage(subtitle, 0, firstImage.getHeight() + (i - 1) * subtitleHeight, null);
        }

        ImageIO.write(finalImage, "png", new File(outputImagePath));
        System.out.println("done");
    }

    public static String getFileExtension(File file) {
        String fileName = file.getName();
        int lastIndexOf = fileName.lastIndexOf(".");
        return lastIndexOf == -1 ? "" : fileName.substring(lastIndexOf);
    }

    /**
     * JPG: FF D8 FF
     * PNG: 89 50 4E 47 0D 0A 1A 0A
     */
    public static String getFileMagicNumber(File file) {
        final int byteSize = 1 << 3;
        byte[] bytes = new byte[byteSize];
        try (FileInputStream in = new FileInputStream(file)) {
            in.read(bytes, 0, byteSize);
            return bytesToHex(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
