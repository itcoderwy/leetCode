package com.cars.leetCode;

import java.util.Scanner;

/**
 * @Description 数组模拟队列
 * @Author Wy005
 * @Date 2020/7/23 10:03
 * @Version 1.0
 **/
public class ArrayQueueDemo {

    public static void main(String[] args) {
        //测试
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(5);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中获取数据");
            System.out.println("h(head):查看队列头部数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查询队列头部
                    try {
                        int res = queue.headQueue();
                        System.out.printf("取出的头部数据是%d\n",res);
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");

    }
}

    //编写一个类   使用数组模拟队列
    class ArrayQueue{
        private int maxSize;//表示数组的最大容量
        private int front;//队列头
        private int rear;//队列尾
        private int[] arr;//该数组用于存放数据模拟的队列

        //创建队列构造器
        public ArrayQueue(int arrmaxSize){
            this.maxSize = arrmaxSize;
            arr = new int[arrmaxSize];
            front = -1;//指向队列头部 前一个位置
            rear = -1;//指向队列尾   具体的位置(就是队列最后的数据)
        }
        //判断队列是否满
        public boolean isFull(){
            return rear == maxSize-1;
        }
        //判断队列是否为空
        public boolean isEmpty(){
            return rear == front;
        }
        //添加队列数据
        public void addQueue(int n){
            //判断队列是否满
            if(this.isFull()){
                System.out.println("队列满,不能加入数据~~");
                return;
            }
            rear++;//让rear 后移
            arr[rear] = n;
        }
        //获取队列的数据 出队列
        public int getQueue(){
            //判断数据是否为空
            if(isEmpty()){
                //通过抛出异常处理
                throw  new RuntimeException("队列为空,不能取出");
            }
            front++;//让front后移
            return arr[front];
        }
        //展示队列的所有数据
        public void showQueue(){
            if(isEmpty()){
                System.out.println("队列为空不能遍历");
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }
        }
        //显示队列的头数据是多少 注意不是取出数据只是显示
        public int headQueue(){
            //判断是否为空
            if(isEmpty()){
                throw new RuntimeException("队列为空,没有数据~~");
            }
            return arr[front+1];
        }
    }

