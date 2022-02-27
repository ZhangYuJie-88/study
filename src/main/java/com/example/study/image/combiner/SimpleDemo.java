package com.example.study.image.combiner;

import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.OutputFormat;

import java.io.InputStream;

/**
 * <h3>study</h3>
 * <p>最简单的例子</p>
 *
 * @author : ZhangYuJie
 * @date : 2022-02-20 19:51
 **/

public class SimpleDemo {

    public static void main(String[] args) throws Exception {
        //合成器（指定背景图和输出格式，整个图片的宽高和相关计算依赖于背景图，所以背景图的大小是个基准）
        ImageCombiner combiner = new ImageCombiner("https://sjb-oss.oss-cn-hangzhou.aliyuncs.com/atd/material/7/image/7f0afcc14e60695f0f28f2bb9801fcf5", OutputFormat.JPG);

        //加图片元素
        combiner.addImageElement("https://sjb-oss.oss-cn-hangzhou.aliyuncs.com/atd/material/7/image/7f0afcc14e60695f0f28f2bb9801fcf5", 0, 300);

        //加文本元素
        combiner.addTextElement("周末大放送", 60, 100, 960);

        //执行图片合并
        combiner.combine();

        //可以获取流（并上传oss等）
        InputStream is = combiner.getCombinedImageStream();

        //也可以保存到本地
        combiner.save("d://image.jpg");
    }
}
