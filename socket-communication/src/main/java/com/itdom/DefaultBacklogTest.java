package com.itdom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在不更改参数backlo g 设置的情况下，其默认值是50 。
 * 需要注意的是， backlog 限制的连接数量是由操作系统进行
 * 处理的，因为backlog 最终会传递给用native 声明的方法
 */
public class DefaultBacklogTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Thread.sleep(5000);
        for (int i = 0; i < 100; i++) {
            System.out.println("acceptl begin " + (i + 1));
            Socket accept = serverSocket.accept();
            System.out.println("acceptl end " + (i + 1));
        }
/**
 * acceptl begin 1
 * acceptl end 1
 * acceptl begin 2
 * acceptl end 2
 * acceptl begin 3
 * acceptl end 3
 * acceptl begin 4
 * acceptl end 4
 * acceptl begin 5
 * acceptl end 5
 * acceptl begin 6
 * acceptl end 6
 * acceptl begin 7
 * acceptl end 7
 * acceptl begin 8
 * acceptl end 8
 * acceptl begin 9
 * acceptl end 9
 * acceptl begin 10
 * acceptl end 10
 * acceptl begin 11
 * acceptl end 11
 * acceptl begin 12
 * acceptl end 12
 * acceptl begin 13
 * acceptl end 13
 * acceptl begin 14
 * acceptl end 14
 * acceptl begin 15
 * acceptl end 15
 * acceptl begin 16
 * acceptl end 16
 * acceptl begin 17
 * acceptl end 17
 * acceptl begin 18
 * acceptl end 18
 * acceptl begin 19
 * acceptl end 19
 * acceptl begin 20
 * acceptl end 20
 * acceptl begin 21
 * acceptl end 21
 * acceptl begin 22
 * acceptl end 22
 * acceptl begin 23
 * acceptl end 23
 * acceptl begin 24
 * acceptl end 24
 * acceptl begin 25
 * acceptl end 25
 * acceptl begin 26
 * acceptl end 26
 * acceptl begin 27
 * acceptl end 27
 * acceptl begin 28
 * acceptl end 28
 * acceptl begin 29
 * acceptl end 29
 * acceptl begin 30
 * acceptl end 30
 * acceptl begin 31
 * acceptl end 31
 * acceptl begin 32
 * acceptl end 32
 * acceptl begin 33
 * acceptl end 33
 * acceptl begin 34
 * acceptl end 34
 * acceptl begin 35
 * acceptl end 35
 * acceptl begin 36
 * acceptl end 36
 * acceptl begin 37
 * acceptl end 37
 * acceptl begin 38
 * acceptl end 38
 * acceptl begin 39
 * acceptl end 39
 * acceptl begin 40
 * acceptl end 40
 * acceptl begin 41
 * acceptl end 41
 * acceptl begin 42
 * acceptl end 42
 * acceptl begin 43
 * acceptl end 43
 * acceptl begin 44
 * acceptl end 44
 * acceptl begin 45
 * acceptl end 45
 * acceptl begin 46
 * acceptl end 46
 * acceptl begin 47
 * acceptl end 47
 * acceptl begin 48
 * acceptl end 48
 * acceptl begin 49
 * acceptl end 49
 * acceptl begin 50
 * acceptl end 50
 * acceptl begin 51
 */
    }
}
