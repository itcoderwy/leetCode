package com.cars.leetCode;

import java.io.*;

/**
 * @Description 稀疏数组练习
 * @Author Wy005
 * @Date 2020/7/17 16:38
 * @Version 1.0
 *  目标：代码实现将稀疏数组保存到磁盘,再从磁盘读取恢复原始数组。
 **/
public class SparseArray {
    public static void main(String[] args) throws IOException {
          //先创建11*11得原始数组
        int[][] chessArray1 = new int[11][11];
          //0:表示没有棋子  1:表示黑色棋子  2:表示蓝色棋子
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;

        //遍历二维数组拿到有效值的个数  通过sum来存储
        int sum = 0;
        for (int i = 0; i < chessArray1.length; i++) {
            for (int j = 0; j < chessArray1.length; j++) {
                if(chessArray1[i][j] != 0){
                    sum++;
                }
            }
        }

        //拿到数组中的数据后开始创建稀疏数组 行数:sum+1 列数:3
        int[][] sparseArray = new int[sum+1][3];

        //稀疏数组的第一行用来存储原始数据的大小和原始数据的个数
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //将二维数组中的有效数据存入到除第一行外的有效稀疏数组当中
        int count = 1;//借助计数器存入数据  计数器的值从1开始
        for (int i = 0; i < chessArray1.length ; i++) {
            for (int j = 0; j < chessArray1.length; j++) {
                if(chessArray1[i][j] != 0){
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray1[i][j];
                    count++;
                }
            }
        }

        //稀疏数组创建成功后将其存入到硬盘
        File file = new File("C:\\Users\\Wy005\\Desktop\\map.data");
        if(file.exists()){//判断文件是否存在
            /*try {
                file.createNewFile();//如果不存在就创建文件
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }


        FileOutputStream os = new FileOutputStream(file);
        for (int i = 0; i < sparseArray.length; i++) {
            String msg = sparseArray[i][0]+"\t"+sparseArray[i][1]+"\t"+sparseArray[i][2]+"\n";

            try {
                os.write(msg.getBytes("utf-8"));
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //从磁盘中读取数据
        BufferedReader reader = new BufferedReader(new FileReader(file));
        //准备需要恢复的二维数组
        int[][] chessArray2 = null;
        int count1 = 0;//计数器
        String msg = "";


        try {
            while((msg = reader.readLine()) != null) {
                String[] str = msg.split("\t");
                Integer row = Integer.valueOf(str[0]);
                Integer col = Integer.valueOf(str[1]);
                Integer value = Integer.valueOf(str[2]);

                if(count1 == 0) {
                    chessArray2 = new int[row][col];
                    count1++;
                }else {
                    chessArray2[row][col] = value;
                    count1++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.println("恢复后的二维数组chessArray2~~");

        for(int[] a : chessArray2) {
            for(int b : a) {
                System.out.print(b+" ");
            }
            System.out.println();
        }


    }

}
