package priv.mm.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DataStreamDemo
 * 使用 <code>xxd -b &lt;file&gt;</code>查看二进制文件
 *
 * @author maodh
 * @see DataOutputStream#writeBoolean(boolean)
 * @see DataOutputStream#writeChar(int)
 * @see DataOutputStream#writeByte(int)
 * @see DataOutputStream#writeShort(int)
 * @see DataOutputStream#writeInt(int)
 * @see DataOutputStream#writeLong(long)
 * @see DataOutputStream#writeFloat(float)
 * @see DataOutputStream#writeDouble(double)
 * @since 04/02/2018
 */
public class DataStreamDemo {

    public static void main(String[] args) {
        File booleanFile = new File("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/temp/Boolean.txt");
        File charFile = new File("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/temp/Char.txt");
        File byteFile = new File("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/temp/Byte.txt");
        File shortFile = new File("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/temp/Short.txt");
        File intFile = new File("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/temp/Int.txt");
        File longFile = new File("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/temp/Long.txt");
        File floatFile = new File("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/temp/Float.txt");
        File doubleFile = new File("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/temp/Double.txt");
        try (DataOutputStream booleanOut = new DataOutputStream(new FileOutputStream(booleanFile));
             DataOutputStream charOut = new DataOutputStream(new FileOutputStream(charFile));
             DataOutputStream byteOut = new DataOutputStream(new FileOutputStream(byteFile));
             DataOutputStream shortOut = new DataOutputStream(new FileOutputStream(shortFile));
             DataOutputStream intOut = new DataOutputStream(new FileOutputStream(intFile));
             DataOutputStream longOut = new DataOutputStream(new FileOutputStream(longFile));
             DataOutputStream floatOut = new DataOutputStream(new FileOutputStream(floatFile));
             DataOutputStream doubleOut = new DataOutputStream(new FileOutputStream(doubleFile))) {
            booleanOut.writeBoolean(true);
            charOut.writeChar('a');
            byteOut.writeByte(1);
            shortOut.writeShort(1);
            intOut.writeInt(1);
            longOut.writeLong(1);
            floatOut.writeFloat(1.0F);
            doubleOut.writeDouble(1.0D);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
