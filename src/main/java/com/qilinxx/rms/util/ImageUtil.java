package com.qilinxx.rms.util;

import com.github.pagehelper.util.StringUtil;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class ImageUtil {

    private final static Integer CER_NO_X = 205;
    private final static Integer CER_NO_Y = 504;
    private final static String CER_NO_FONT = "宋体";
    private final static String CER_NO_BASE = "QL340600-";

    /**
     * 生成证书图片，返回服务器调用地址
     *
     * @param base64 图片base64格式
     * @param cerNo  证书编号
     * @return 生成图片的访问路径，生成失败返回null
     */
    public static String build(String base64, String cerNo) throws Exception {
        File dic = new File(UploadUtil.getUploadFilePath() + "/upload/name/");
        if (!dic.exists()) dic.mkdir();
        String out = UploadUtil.getUploadFilePath() + "upload/name/" + System.currentTimeMillis() + ".jpg";
        System.out.println("file out ==" + out);
        boolean flag = base64ToImage(base64, out);
        String result;
        if (flag) {
            result = create(out, cerNo);
        } else
            return "error";
        return result;
    }

    /**
     * 根据姓名花体图片，生成证书
     *
     * @param namePath 小图的本机地址
     * @param cerNo    证书编号
     * @return 生成证书的地址
     * @throws Exception
     */
    private static String create(String  namePath, String cerNo) throws Exception {
        File f = ResourceUtils.getFile("classpath:static/image/cer.jpg");
        File dic = new File(UploadUtil.getUploadFilePath() + "/upload/cer/");
        if (!dic.exists()) dic.mkdir();
        String base = UploadUtil.getUploadFilePath();
        String name = "/upload/cer/" + System.currentTimeMillis() + ".jpg";
        String out = base + name;
        ImageUtil.bigImgAddSmallImg(f.getAbsolutePath(), namePath, 372, 604, out, cerNo);
        return name;
    }

    /***
     * 在一张大图张添加小图
     * @param bigImgPath 大图的路径
     * @param smallImgPath 小图的路径
     * @param sx    小图在大图上x抽位置
     * @param sy    小图在大图上y抽位置
     * @param outPathWithFileName 结果输出路径
     */
    private static void bigImgAddSmallImg(String bigImgPath
            , String smallImgPath, int sx, int sy
            , String outPathWithFileName, String cerNo) throws IOException {
        //主图片的路径
        InputStream is = new FileInputStream(bigImgPath);

        BufferedImage buffImg = ImageIO.read(is);
        //得到画笔对象
        Graphics g = buffImg.getGraphics();

        //小图片的路径
        ImageIcon imgIcon = new ImageIcon(smallImgPath);
        //得到Image对象。
        Image img = imgIcon.getImage();
        //将小图片绘到大图片上,5,300 .表示你的小图片在大图片上的位置。
        g.drawImage(img, sx, sy, null);
        //设置颜色。
        g.setColor(Color.WHITE);

        Font f = new Font(CER_NO_FONT, Font.PLAIN, 25);
        Color myColor = new Color(255, 160, 34);
        g.setColor(myColor);
        g.setFont(f);
        g.drawString(cerNo, CER_NO_X, CER_NO_Y); //表示这段文字在图片上的位置(cx,cy) .第一个是你设置的内容。
        g.dispose();
        OutputStream os = new FileOutputStream(outPathWithFileName);
        ImageIO.write(buffImg, "jpg", os);
        is.close();
        os.close();
    }

    /**
     * base64字符串转换成图片
     *
     * @param imgStr      base64字符串
     * @param imgFilePath 图片存放路径
     * @return
     * @dateTime 2018-02-23 14:42:17
     */
    private static boolean base64ToImage(String imgStr, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片

        if (StringUtil.isEmpty(imgStr)) // 图像数据为空
            return false;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 生成证书编号
     *
     * @return 证书编号
     */
    public static String createCerNo() {
        return CER_NO_BASE + ((DateKit.getCurrentUnixTime()%1000000000));
    }
}
