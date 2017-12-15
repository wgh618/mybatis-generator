import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisGenerate {
    private static Logger logger = LoggerFactory.getLogger(MybatisGenerate.class);
    public void generator() throws Exception{
        logger.info("开始生成");
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指定逆向工程配置文件,以下两种方式都可以:getClassLoader()--不加"/";getClass()--加"/".
//        InputStream in = MybatisGenerate.class.getClassLoader().getResourceAsStream("generatorConfig.xml");
        InputStream in = MybatisGenerate.class.getResourceAsStream("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(in);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
        logger.info("生成成功");
    }
    public static void main(String[] args) {
        try {
            MybatisGenerate generator = new MybatisGenerate();
            generator.generator();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("生成失败:" + e.getMessage());
        }

    }
}
