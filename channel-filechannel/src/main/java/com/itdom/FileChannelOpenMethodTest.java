package com.itdom;

import sun.security.krb5.internal.PAData;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * FileChannel open(Path path, OpenOption ... options)
 * 方法的作用是打开一个文件，以便对这个文件进行后期
 * 处理。
 * 参数Path 代表一个文件在文件系统中的路径。Path
 * 接口的信息如图2-53 所示。
 * Path 接口的实现类可以使用多种方式进行获取，在
 * 本章节中通过调用F ile 类的to Path （）方法进行获取。
 * 参数Open Option 代表以什么样的方式打开或创建
 * 一个文件。Open Option 也是一个接口， OpenOpt i on 接
 * 口的信息如图2 -54 所示。
 * Open Option 接口的实现类通常由StandardOpenOption
 * 枚举进行代替。枚举S tandardOpenOption 信息如图2-55
 * 所示。
 * <p>
 * 1 . 枚举常量CREATE 和WR I TE 的使用
 * 枚举常量CREATE 的作用：创建一个新文件（如
 * 果它不存在）。如果还设置了CREATE NEW 选项，
 * 则忽略此选项。此选项只是一个创建文件的意图，
 * 并不能真正地创建文件，因此， CREATE 不能单独使
 * 用，那样就会出现j ava. nio.file.NoSuchFileException
 * 异常。
 * 2 . 枚举常量APPEND 的使用
 * 枚举常量APPEND 的作用：如果打开文件以进行写入访问，则字节将写入文件末尾而
 * 不是开始处。
 */
public class FileChannelOpenMethodTest {
    public static void main(String[] args) throws IOException, InterruptedException {
//        File file = new File("./FileChannelOpenMethodTest.txt");
//        Path path = file.toPath();
//        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        /**
         * Exception in thread "main" java.nio.file.NoSuchFileException: .\FileChannelOpenMethodTest.txt
         * 	at sun.nio.fs.WindowsException.translateToIOException(WindowsException.java:79)
         * 	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:97)
         * 	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:102)
         * 	at sun.nio.fs.WindowsFileSystemProvider.newFileChannel(WindowsFileSystemProvider.java:115)
         * 	at java.nio.channels.FileChannel.open(FileChannel.java:287)
         * 	at java.nio.channels.FileChannel.open(FileChannel.java:335)
         * 	at com.itdom.FileChannelOpenMethodTest.main(FileChannelOpenMethodTest.java:37)
         */
//        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE);
//        fileChannel.close();
        /**
         * 2 . 枚举常量APPEND 的使用
         * 枚举常量APPEND 的作用：如果打开文件以进行写入访问，则字节将写入文件末尾而
         * 不是开始处。
         */

//        File file = new File("./FileChannelOpenMethodTest.txt");
//        Path path = file.toPath();
//        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.APPEND);
//        fileChannel.write(ByteBuffer.wrap("123".getBytes()));
//        fileChannel.close();


        /**
         * 3 . 枚举常量READ 的使用
         * 枚举常量READ 的作用：打开以进行读取访问。
         */

//        File file = new File("./FileChannelOpenMethodTest.txt");
//        Path path = file.toPath();
//        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);
//        byte[] byteArray = new byte[(int) file.length()];
//        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
//        fileChannel.read(byteBuffer);
//        fileChannel.close();
//        byte[] array = byteBuffer.array();
//        for (int i = 0; i < array.length; i++) {
//            System.out.println((char)byteArray[i]);
//        }


        /**
         * 4.枚举常量TRUNCATE EXISTING 的作用：如果该文件已存在并且为写入访问而打开，
         * 则其长度将被截断为0 。如果只为读取访问打开文件，则忽略此选项。
         */
//        File file = new File("./FileChannelOpenMethodTest.txt");
//        Path path = file.toPath();
//        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.WRITE);
//        fileChannel.close();
        /**
         * 执行结果：原本文件FileChannelOpenMethodTest.txt是有内容123，执行程序之后就变成空白文件了。
         */


        /**
         * 5 枚举常量CREATE NEW 的使用
         * 枚举常量CREATE NEW 的作用： 创建一个新文件，如果该文件己存在，则失败。
         */
//        File file = new File("./FileChannelOpenMethodTest.txt");
//        Path path = file.toPath();
//        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
//        fileChannel.close();
        /**
         * 执行结果:如果要创建的文件已经存在，那么就执行出错.
         * Exception in thread "main" java.nio.file.FileAlreadyExistsException: .\FileChannelOpenMethodTest.txt
         * 	at sun.nio.fs.WindowsException.translateToIOException(WindowsException.java:81)
         * 	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:97)
         * 	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:102)
         * 	at sun.nio.fs.WindowsFileSystemProvider.newFileChannel(WindowsFileSystemProvider.java:115)
         * 	at java.nio.channels.FileChannel.open(FileChannel.java:287)
         * 	at java.nio.channels.FileChannel.open(FileChannel.java:335)
         * 	at com.itdom.FileChannelOpenMethodTest.main(FileChannelOpenMethodTest.java:103)
         * 	否则就创建该文件
         */


        /**
         * 6. 枚举常量DELETE ON CLOSE 的使用
         * 枚举常量DELETE ON CLOSE 的作用： 关闭时删除。
         * 当此选项存在时， 实现会尽最大努力尝试在关闭时通过适当的close（）方法删除该文
         * 件。如果未调用close （）方法， 则在Java 虚拟机终止时尝试删除该文件。此选项主要用于仅
         * 由Java 虚拟机的单个实例使用的工作文件。在打开由其他实体并发打开的文件时－，建议不
         * 要使用此选项。有关何时以及如何删除文件的许多详细信息都是特定于实现的，因此没有指
         * 定。特别是，实现可能无法保证当文件打开或攻击者替换时，它将删除预期的文件。因此，
         * 安全敏感的应用程序在使用此选项时应小心。
         */

//        File file = new File("./FileChannelOpenMethodCloseDeleteTest.txt");
//        Path path = file.toPath();
//        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.DELETE_ON_CLOSE, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
//        Thread.sleep(20000);
//        fileChannel.close();

        /**
         *7 . 枚举常量SPARSE 的使用
         * 枚举常量SPARSE 的作用： 稀疏文件。与C R EATE NEW 选项一起使用时，此选项
         * 提供了一个提示，表明新文件将是稀疏的。当文件系统不支持创建稀疏文件时，将忽略该
         * 选项。
         * 什么是稀疏文件呢？在介绍稀疏文件之前先来看看普通文件存储时硬盘空间占用的
         * 情况。
         */
//        File file = new File("./FileChannelOpenMethodSPARSETest.txt");
//        Path path = file.toPath();
//        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
//        int fileSize = Integer.MAX_VALUE;
//        fileSize = fileSize*3;
//        fileChannel.position(fileSize);
//        fileChannel.write(ByteBuffer.wrap("a".getBytes()));
//        fileChannel.close();
        /**
         * 8文件只是写入了一个字符，但是却占据了很大的空间，所以是要稀疏文件会占据大量的空间
         * 9. 枚举常量DS Y NC 的使用
         * 枚举常量DSYNC 的作用： 要求对文件内容的每次更新都同步写入底层存储设备。
         * 枚举常量SYNC 与DSYNC 的区别： SYNC 更新内容与元数据，而DSYNC 只更新内
         * 容，与force(boolean）方法作用一样。
         */
        File file = new File("./FileChannelTest.txt");
        Path path = file.toPath();
        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.SYNC, StandardOpenOption.WRITE);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            fileChannel.write(ByteBuffer.wrap("a".getBytes()));
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
        fileChannel.close();


    }
}
