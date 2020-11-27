package com.itdom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *void force(boolean metaData） 方法的作用是强制将所有对此通道的文件更新写入包含该
 * 文件的存储设备中。如果此通道的文件驻留在本地存储设备上，则此方法返回时可保证：在
 * 此通道创建后或在最后一次调用此方法后，对该文件进行的所有更改都已写入该设备中。这
 * 对确保在系统崩溃时不会丢失重要信息特别有用。如果该文件不在本地设备上，则无法提供
 * 这样的保证。
 * metaData 参数可用于限制此方法必须执行的1/0 操作数量。在为此参数传人false 时，
 * 只需将对文件内容的更新写入存储设备；在传入true 时·，则必须写人对文件内容和元数据
 * 的更新，这通常需要一个以上的1/0 操作。此参数是否实际有效，取决于底层操作系统，因
 * 此是未指定的。
 * 调用此方法可能导致发生1/0 操作，即使该通道仅允许进行读取操作时也是如此。例
 * 如，某些操作系统将最后一次访问的时间作为元数据的一部分进行维护，每当读取文件时就
 * 更新此时间。实际是否执行操作是与操作系统相关的，因此是未指定的。
 * 此方法只保证强制进行通过此类中已定义的方法对此通道的文件所进行的更改。此方
 * 法不一定强制进行那些通过修改己映射字节缓冲区（通过调用map（）方法获得）的内容所进
 * 行的更改。调用己映射字节缓冲区的fore巳（）方法将强行对要写人缓冲区的内容进行更改。
 * 以上文字是JDK API 文档对该方法的解释，并不能完全反映出该方法的使用意图与作
 * 用，因此，在此着重说明一下，其实在调用FileChannel 类的write（）方法时，操作系统为了
 * 运行的效率，先是把那些将要保存到硬盘上的数据暂时放入操作系统内核的缓存中，以减少
 * 硬盘的读写次数，然后在某一个时间点再将内核缓存中的数据批量地同步到硬盘中，但同步
 * 的时间却是由操作系统决定的，因为时间是未知的，这时就不能让操作系统来决定，所以要
 * 显式地调用force(boo lean） 方法来强制进行同步，这样做的目的是防止在系统崩溃或断电时
 * 缓存中的数据丢失而造成损失。但是， force(boolean）方法并不能完全保证数据不丢失，如
 * 正在执行force（）方法时出现断电的情况，那么硬盘上的数据有可能就不是完整的，而且由
 * 于断电的原因导致内核缓存中的数据也丢失了，最终造成的结果就是force(boolean） 方法执
 * 行了，数据也有可能丢失。既然调用该方法也有可能造成数据的丢失，那么该方法的最终目
 * 的是什么呢？其实forc e( boo lea n）方法的最终目的是尽最大的努力减少数据的丢失。例如，
 * 内核缓存中有lOKB 的数据需要同步，那么可以每2阻就执行l 次force( bo olean）方法来同
 * 步到硬盘上， 也就不至于缓存中有l OKB 数据，在突然断电时，这lOKB 数据全部丢失的情
 * 况发生，因此， for ce(bo o l ean）方法的目的是尽可能少地丢失数据，而不是保证完全不丢失
 * 数据。
 */
public class ForceMethodTest {
    public static void main(String[] args) throws IOException {
        File file = new File("./ForceMethodTest.txt");
        if (!file.exists()){
            file.createNewFile();
        }else {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel streamChannel = fileOutputStream.getChannel();
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            streamChannel.write(ByteBuffer.wrap("abcde".getBytes()));
            //加上这个强制写入的存储设备之后的语句性能会急剧的下降,没加之前是24秒和22秒之间，加了之后时间直接变成了128465左右.
            streamChannel.force(false);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-beginTime);
        streamChannel.close();
        fileOutputStream.close();
    }
}
