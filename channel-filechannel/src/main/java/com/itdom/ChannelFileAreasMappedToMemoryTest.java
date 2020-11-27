package com.itdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer map(FileChannel.MapMode mode, long position, long size）方法的作
 * 用是将此通道的文件区域直接映射到内存中。可以通过下列3 种模式将文件区域映射到内
 * 存中。
 * I ）只读：试图修改得到的缓冲区将导致抛出ReadOnly BufferException 异常。（ MapMode.
 * READ ONLY)
 * 2 ）读取／写人：对得到的缓冲区的更改最终将传播到文件；该更改对映射到同一文件
 * 的其他程序不一定是可见的。（ MapMode.READ_ WRITE)
 * 3 ） 专用：对得到的缓冲区的更改不会传播到文件，并且该更改对映射到同一文件的其
 * 他程序也不是可见的；相反，会创建缓冲区已修改部分的专用副本。（ MapMode.PRIVATE)
 * 对于只读映射关系，此通道必须可以进行读取操作；对于读取／写入或专用映射关系，
 * 此通道必须可以进行读取和写人操作。
 * 此方法返回的已映射字节缓冲区位置为零，限制和容量为size ；其标记是不确定的。在
 * 缓冲区本身被作为垃圾回收之前，该缓冲区及其表示的映射关系都是有效的。
 * 映射关系一经创建，就不再依赖于创建它时所用的文件通道。特别是关闭该通道对映
 * 射关系的有效性没有任何影响。
 * 很多内存映射文件的细节从根本上是取决于底层操作系统的，因此是未指定的。当所
 * 请求的区域没有完全包含在此通道的文件中时，此方法的行为是未指定的： 未指定是否将此
 * 程序或另一个程序对底层文件的内容或大小所进行的更改传播到缓冲区；未指定将对缓冲区
 * 的更改传播到文件的频率。
 * 对于大多数操作系统而言，与通过普通的read（）和l write （）方法读取或写入数千字节的
 * 数据相比，将文件映射到内存中开销更大。从性能的观点来看，通常将相对较大的文件映射
 * 到内存中才是值得的。
 * 该方法的3 个参数的说明如下。
 * 1) mode ：根据只读、读取／写入或专用（写入时复制）来映射文件，分别为FileChannel.
 * MapMode 类中所定义的READ ONLY 、READ_WRITE 和PRIVATE;
 * 2) position ： 文件中的位置，映射区域从此位置开始；
 * 必须为非负数。
 * 3 ) size ： 要映射的区域大小；必须为非负数且不大于
 * Integer.MAX VALUE 。
 */
public class ChannelFileAreasMappedToMemoryTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("./ChannelFileAreasMappedToMemoryTest.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = accessFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, 5);
        System.out.println((char)mappedByteBuffer.get()+" position="+mappedByteBuffer.position());
        System.out.println((char)mappedByteBuffer.get()+" position="+mappedByteBuffer.position());
        System.out.println((char)mappedByteBuffer.get()+" position="+mappedByteBuffer.position());
        System.out.println((char)mappedByteBuffer.get()+" position="+mappedByteBuffer.position());
        System.out.println((char)mappedByteBuffer.get()+" position="+mappedByteBuffer.position());
        System.out.println();
        mappedByteBuffer = accessFileChannel.map(FileChannel.MapMode.READ_ONLY,2,2);
        System.out.println((char)mappedByteBuffer.get()+" position="+mappedByteBuffer.position());
        System.out.println((char)mappedByteBuffer.get()+" position="+mappedByteBuffer.position());

        Thread.sleep(500);
        System.out.println();
        System.out.println((char)mappedByteBuffer.get()+" position="+mappedByteBuffer.position());

        accessFileChannel.close();
        randomAccessFile.close();
        /**
         * 执行结果:
         * Exception in thread "main" java.nio.BufferUnderflowException
         * 	at java.nio.Buffer.nextGetIndex(Buffer.java:500)
         * 	at java.nio.DirectByteBuffer.get(DirectByteBuffer.java:249)
         * 	at com.itdom.ChannelFileAreasMappedToMemoryTest.main(ChannelFileAreasMappedToMemoryTest.java:59)
         */
    }
}
